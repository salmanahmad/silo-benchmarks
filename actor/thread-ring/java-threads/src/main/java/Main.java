
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

    public static int random(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static class Message implements Serializable {
        public Object payload;

        public Message(Object payload) {
            this.payload = payload;
        }
    }

    public static class Pong implements Serializable {}

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

    public static class First extends Actor {
        public Actor next = null;

        public void run() {
            while(true) {
                int i = ((Integer)read()).intValue();

                if(i == 1000000) {
                    mark();
                } else {
                    next.send(new Integer(i + 1));
                }
            }
        }
    }

    public static class Service extends Actor {
        public Actor next = null;

        public Service(Actor next) {
            this.next = next;
        }

        public void run() {
            while(true) {
                int i = ((Integer)read()).intValue();

                if(i == 1000000) {
                    mark();
                } else {
                    next.send(new Integer(i + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Starting!");
        long startTime = System.nanoTime();

        Runtime rt = new Runtime();
        int count = 1000;

        First first = (First)rt.spawn(new First());
        Actor previous = first;

        for(int i = count - 1; i > 0; i--) {
            previous = rt.spawn(new Service(previous));
        }

        first.next = previous;
        first.send(new Integer(0));

        await();

        System.out.println("Done!");

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
