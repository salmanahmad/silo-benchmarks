
import java.util.*;
import java.util.concurrent.*;
import java.io.Serializable;

public class Main {
    static Object lock = new Object();
    static boolean done = false;

    public static void await() throws Exception {
        synchronized(lock) {
            while(!done) {
                System.out.println("Waiting!");
                lock.wait();
            }
        }
    }

    public static void mark() {
        synchronized(lock) {
            done = true;
            System.out.println("Notifying!");
            lock.notifyAll();
        }
    }

    public static class Message implements Serializable {
        public Actor sender;
        public Vector path;

        public Message(Actor sender, Vector path) {
            this.sender = sender;
            this.path = path;
        }
    }

    public static class Runtime {
        ConcurrentHashMap actors = new ConcurrentHashMap();

        public Actor spawn(Actor actor) {
            actor.runtime = this;
            actors.put(actor.id, actor);

            new Thread(actor).start();

            return actor;
        }
    }

    public static class Actor implements Runnable {
        public Runtime runtime = null;
        public String id = UUID.randomUUID().toString();
        ArrayBlockingQueue queue = new ArrayBlockingQueue(100000);

        public void run() {
            
        }

        public void send(Object message) {
            try {
                queue.put(message);
            } catch(InterruptedException e) {
                System.out.println("Error!");
            }
        }

        public Object read() {
            try {
                return queue.take();
            } catch(InterruptedException e) {
                System.out.println("Error!");
                return null;
            }
        }
    }

    public static class Service extends Actor {

        public void run() {
            int h = 20;
            int k = 2;

            while(true) {
                Message m = (Message)read();

                if(m.path.size() == h) {
                    m.sender.send(new Vector());
                } else {
                    Vector children = new Vector();

                    for(int i = 0; i < k; i++) {
                        Vector child = new Vector(m.path);
                        child.add(new Integer(i));

                        children.add(child);
                    }

                    m.sender.send(children);
                }
            }
        }
    }

    public static class Traverse extends Actor {
        public Vector initial = null;
        public Actor service = null;

        public Traverse(Actor service, Vector initial) {
            this.initial = initial;
            this.service = service;
        }

        public int traverse(Vector path) {
            int count = 1;

            service.send(new Message(this, path));
            Vector children = (Vector)read();

            for(int i = 0; i < children.size(); i++) {
                Vector child = (Vector)children.get(i);
                count = count + traverse(child);
            }

            return count;
        }

        public void run() {
            traverse(initial);
            mark();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Starting!");
        long startTime = System.nanoTime();

        Runtime rt = new Runtime();
        int count = 1000;

        Service service = (Service)rt.spawn(new Service());
        Traverse traverse = (Traverse)rt.spawn(new Traverse(service, new Vector()));

        await();

        System.out.println("Done!");

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
