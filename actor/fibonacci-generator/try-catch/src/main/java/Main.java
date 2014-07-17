
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
        public Object payload;

        public Message(Object payload) {
            this.payload = payload;
        }
    }

    public static class YieldException extends RuntimeException {}

    public static class Runtime {
        ConcurrentHashMap actors = new ConcurrentHashMap();
        ExecutorService pool = Executors.newFixedThreadPool(1);

        public Actor spawn(Actor actor) {
            actor.runtime = this;
            this.actors.put(actor.id, actor);

            return actor;
        }
    }

    public static class Actor implements Runnable {
        public String id = UUID.randomUUID().toString();
        public Runtime runtime = null;

        public int index = 0;
        public Object[] state = null;
        public ArrayDeque mailbox = new ArrayDeque();

        public void run() {
            
        }

        public synchronized void send(Object message) {
            mailbox.addFirst(message);
            this.runtime.pool.submit(this);
        }

        public synchronized Object read() {
            if(mailbox.size() == 0) {
                throw new YieldException();
            } else {
                return mailbox.removeLast();
            }
        }
    }

    public static class Fib extends Actor {
        public void run() {
            int a = 0;
            int b = 0;

            switch(index) {
                case 0:
                case 1:
                if(index == 1) {
                    a = ((Integer)state[0]).intValue();
                    b = ((Integer)state[1]).intValue();
                }

                while(true) {
                    try {
                        Message m = (Message)read();
                        Actor actor = (Actor)this.runtime.actors.get(m.payload.toString());
                        actor.send(new Message(new Integer(a)));
                    } catch(YieldException e) {
                        index = 1;
                        state = new Object[] {new Integer(a), new Integer(b)};
                        throw e;
                    }

                    int temp = b;
                    b = a;
                    a = a + temp;
                }
            }
        }
    }

    public static class Tester extends Actor {
        public Fib fib = null;

        public void run() {
            int i = 0;

            switch(index) {
                case 0:
                    read();
                case 1:
                    if(index == 1) {
                        i = ((Integer)state[0]).intValue();
                    }

                    while(i < 1000000) {
                        i = i + 1;

                        try {
                            fib.send(new Message(this.id));
                            Message m = (Message)read();
                        } catch(YieldException e) {
                            Object[] state = new Object[] { new Integer(i) };
                            this.state = state;
                            this.index = 1;
                            throw e;
                        }
                    }
            }

            System.out.println("Pinger!");
            mark();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Starting!");
        long startTime = System.nanoTime();

        Runtime rt = new Runtime();

        Fib fib = (Fib)rt.spawn(new Fib());

        Tester actor = new Tester();
        actor.fib = fib;
        rt.spawn(actor);

        actor.send(new Message("start"));

        await();

        System.out.println("Done!");

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
