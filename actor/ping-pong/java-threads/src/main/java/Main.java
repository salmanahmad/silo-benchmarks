
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

    public static class Start implements Serializable {}

    public static class Stop implements Serializable {}

    public static class Ping implements Serializable {
        public String id;

        public Ping(String id) {
            this.id = id;
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

        public synchronized void run() {
            
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

    public static class Ponger extends Actor {
        public void run() {
            while(true) {
                Object o = read();
                if(o instanceof Ping) {
                    Ping p = (Ping)o;
                    Actor a = (Actor)this.runtime.actors.get(p.id);
                    a.send(new Pong());
                }
            }
        }
    }

    public static class Pinger extends Actor {
        public ArrayList actors = null;

        public void run() {
            int i = 0;

            while(true) {
                Object o = read();
                if(o instanceof Start) {
                    break;
                }
            }

            while(i < 1000000) {
                i = i + 1;

                Actor a = (Actor)actors.get(random(0, actors.size() - 1));
                a = (Actor)this.runtime.actors.get(a.id);
                a.send(new Ping(this.id));

                Object o = read();
            }

            System.out.println("Pinger!");
            mark();
        }
    }

    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();

        Runtime rt = new Runtime();

        ArrayList list = new ArrayList();

        for(int i = 0; i < 100; i++) {
            list.add(rt.spawn(new Ponger()));
        }

        System.out.println("Starting!");

        Pinger actor = new Pinger();
        actor.actors = list;
        rt.spawn(actor);
        actor.send(new Start());

        await();

        System.out.println("Done!");

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
