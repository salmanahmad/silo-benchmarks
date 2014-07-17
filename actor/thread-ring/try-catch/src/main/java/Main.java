
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

    public static class First extends Actor { 
        public String next = null;

        public void run() {
            int a = 0;
            int b = 0;

            if(index == 9999) {
                Random r = new Random();
                int c = 0;
                int d = 5;

                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();

                a = c;
                b = d;
            }

            switch(index) {
                case 0:
                    try {
                        read();
                    } catch(YieldException e) {
                        this.index = 0;
                        this.state = new Object[]{};
                        throw e;
                    }
                case 1:
                    while(true) {
                        int i = 0;

                        try{
                            i = ((Integer)read()).intValue();
                        } catch(YieldException e) {
                            this.index = 1;
                            this.state = new Object[]{};
                    
                            throw e;
                        }

                        if(i == 1000000) {
                            mark();
                            break;
                        } else {
                            ((Actor)runtime.actors.get(next)).send(new Integer(i + 1));
                        }
                    }
            }

            index = a + b + 100000;
        }
    }

    public static class Service extends Actor {
        public String next = null;

        public Service(String next) {
            this.next = next;
        }

        public void run() {
            int a = 0;
            int b = 0;

            if(index == 9999) {
                Random r = new Random();
                int c = 0;
                int d = 5;

                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                c = d + r.nextInt();
                d = c + r.nextInt();
                c = d - r.nextInt();
                d = c - r.nextInt();
                

                a = c;
                b = d;
            }

            while(true) {
                int i = 0;

                try{
                    i = ((Integer)read()).intValue();
                } catch(YieldException e) {
                    this.index = 1;
                    this.state = new Object[]{};
                    
                    throw e;
                }

                if(i == 1000000) {
                    mark();
                    break;
                } else {
                    ((Actor)runtime.actors.get(next)).send(new Integer(i + 1));
                }
            }

            index = 99999 + a + b;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Starting!");
        long startTime = System.nanoTime();

        Runtime rt = new Runtime();
        int count = 1000;

        First first = (First)rt.spawn(new First());
        String previous = first.id;

        for(int i = count - 1; i > 0; i--) {
            previous = rt.spawn(new Service(previous)).id;
        }

        first.next = previous;
        first.send("Start");
        first.send(new Integer(0));

        await();

        System.out.println("Done!");

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
