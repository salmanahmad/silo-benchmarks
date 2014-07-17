
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

    public static class Frame implements Serializable {
        public int index;
        public Object[] state;
    }

    public static class Message implements Serializable {
        public String sender;
        public Vector path;

        public Message(String sender, Vector path) {
            this.sender = sender;
            this.path = path;
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

        public synchronized void sendAddress(String address, Object message) {
            ((Actor)this.runtime.actors.get(address)).send(message);
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

    public static class Service extends Actor {

        public void run() {
            int h = 0;
            int k = 0;

            switch(index) {
                case 0:
                    h = 20;
                    k = 2;
                case 1:
                    if(index == 1) {
                        h = ((Integer)state[0]).intValue();
                        k = ((Integer)state[1]).intValue();
                    }

                    while(true) {
                        Message m = null;
                        try {
                            m = (Message)read();
                        } catch(YieldException e) {
                            index = 1;
                            state = new Object[]{ new Integer(h), new Integer(k)};
                        }

                        if(m.path.size() == h) {
                            ((Actor)runtime.actors.get(m.sender)).send(new Vector());
                        } else {
                            Vector children = new Vector();
                            for(int i = 0; i < k; i++) {
                                Vector child = new Vector(m.path);
                                child.add(new Integer(i));

                                //System.out.println("Old: " + m.path);
                                //System.out.println("New: " + child);

                                children.add(child);
                            }

                            ((Actor)runtime.actors.get(m.sender)).send(children);
                        }
                    }
            }
        }
    }

    public static class Traverse extends Actor { 
        String service = null;
        int stackPointer = 0;
        Stack<Frame> stack = new Stack<Frame>();
        Vector initial;

        public void ensureSize(int size) {
            stack.ensureCapacity(size);
            while (stack.size() < size) {
                stack.add(null);
            }
        }

        public int traverse(Vector path) {
            int count = 1;
            int i = 0;
            Vector children = null;
            Vector child = null;

            Frame frame = null;

            try{
                frame = stack.get(stackPointer);
            } catch(Exception e) {
                e.printStackTrace();
            }
            
            if(frame == null) {
                frame = new Frame();
            }

            //System.out.println("\n\n");
            ////System.out.println(stackPointer);
            ////System.out.println(path);
            ////System.out.println(frame.index);


            switch(frame.index) {
                case 0:
                    sendAddress(service, new Message(this.id, path));
                case 1:
                    //System.out.println("Hi!");

                    try{
                        children = (Vector)read();
                    } catch(YieldException e) {
                        frame = new Frame();
                        frame.index = 1;
                        frame.state = null;
                        stack.set(stackPointer, frame);
                        throw e;
                    }

                    i = 0;
                    if(children.size() > 0) {
                        child = (Vector)children.get(i);
                    }
                case 2:
                    if(frame.index == 2) {
                        i = ((Integer)frame.state[0]).intValue();
                        count = ((Integer)frame.state[1]).intValue();
                        children = (Vector)frame.state[2];
                        child = (Vector)frame.state[3];

                        //System.out.println("Resuming: " + i + " Vector: " + children);
                    } else {
                        i = 0;
                        count = 1;

                        //System.out.println("Not resuming: " + i + " Vector: " + children);
                    }

                    while(i < children.size()) {
                        child = (Vector)children.get(i);

                        stackPointer++;
                        ensureSize(stackPointer + 1);

                        try {
                            //System.out.println("Traversing: " + i);
                            count = count + traverse(child);
                        } catch(YieldException e) {
                            frame = new Frame();
                            frame.index = 2;
                            frame.state = new Object[] {new Integer(i), new Integer(count), children, child};

                            stackPointer--;
                            stack.set(stackPointer, frame);

                            throw e;
                        }

                        stackPointer--;

                        try {
                            i++;
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
            }

            stack.set(stackPointer, null);

            return count;
        }

        public void run() {
            stackPointer = 0;
            ensureSize(stackPointer + 1);
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
        Traverse traverse = (Traverse)rt.spawn(new Traverse());

        traverse.service = service.id;
        traverse.initial = new Vector();

        rt.pool.submit(traverse);

        await();

        System.out.println("Done!");

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
