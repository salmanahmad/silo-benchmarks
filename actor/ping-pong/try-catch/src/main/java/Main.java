
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
        public boolean done = false;
        public Runtime runtime = null;

        public int index = 0;
        public Object[] state = null;
        public ArrayDeque mailbox = new ArrayDeque();

        public synchronized void resume() {
            try {
                run();
            } catch(YieldException e) {
                return;
            }

            done = true;
        }

        public synchronized void run() {
            
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

    public static class Ponger extends Actor {
        public void run() {
            Object o = read();
            if(o instanceof Ping) {
                Ping p = (Ping)o;
                Actor a = (Actor)this.runtime.actors.get(p.id);
                a.send(new Pong());
            }
        }
    }

    public static class Pinger extends Actor {
        public ArrayList actors = null;

        public void run() {
            int i = 0;

            switch(index) {
                case 0:
                    while(true) {
                        try {
                            Object o = read();
                            if(o instanceof Start) {
                                break;
                            }
                        } catch(YieldException e) {
                            throw e;
                        }
                    }
                case 1:
                    if(index == 1) {
                        i = ((Integer)state[0]).intValue();
                    }

                    while(i < 1000000) {
                        i = i + 1;

                        Actor a = (Actor)actors.get(random(0, actors.size()));
                        a = (Actor)this.runtime.actors.get(a.id);
                        a.send(new Ping(this.id));

                        try {
                            Object o = read();
                        } catch(YieldException e) {
                            Object[] state = new Object[] { new Integer(i) };
                            this.state = state;
                            this.index = 1;
                            throw e;
                        }
                    }
                case 2:
                    if(index == 2) {
                        return;
                    } else {
                        index = 2;
                    }
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
