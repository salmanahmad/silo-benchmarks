
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

    public static class Fib extends Actor {
        public void run() {
            int a = 0;
            int b = 1;

            while(true) {
                Object o = read();
                if(o instanceof Message) {
                    Message p = (Message)o;
                    Actor actor = (Actor)this.runtime.actors.get(p.payload.toString());
                    actor.send(new Message(new Integer(a)));

                    int temp = b;
                    b = a;
                    a = a + temp;
                }
            }
        }
    }

    public static class Tester extends Actor {
        public Fib fibber = null;

        public void run() {
            for(int i = 0; i < 1000000; i++) {
                fibber.send(new Message(this.id));
                read();
            }

            mark();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Starting!");
        long startTime = System.nanoTime();

        Runtime rt = new Runtime();

        Fib fibber = (Fib)rt.spawn(new Fib());
        Tester tester = new Tester();
        tester.fibber = fibber;
        rt.spawn(tester);

        await();

        System.out.println("Done!");

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
