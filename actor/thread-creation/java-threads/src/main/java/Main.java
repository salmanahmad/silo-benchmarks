
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
            while(true) {
                
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Starting!");
        long startTime = System.nanoTime();

        Runtime rt = new Runtime();

        for(int i = 0; i < 1000000; i++) {
            rt.spawn(new Service());
        }

        await();

        System.out.println("Done!");

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
