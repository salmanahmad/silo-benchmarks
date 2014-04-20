import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Creator;
import akka.actor.Inbox;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.ArrayList;
import java.util.ArrayDeque;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Main {
    public static class Start implements Serializable {}

    public static class Stop implements Serializable {}

    public static class Service extends UntypedActor {
        int count = 0;

        public void onReceive(Object message) {
            int h = 20;
            int k = 2;

            count = count + 1;

            ArrayList path = (ArrayList)message;
            
            if(path.size() == h) {
                getSender().tell(new ArrayList(), getSelf());
            } else {
                ArrayList children = new ArrayList();

                for(int i = 0; i < k; i++) {
                    ArrayList child = new ArrayList(path);
                    child.add(new Integer(i));
                    children.add(child);
                }

                getSender().tell(children, getSelf());
            }
        }
    }

    public static class Client extends UntypedActor {
        public static Props props(final ActorRef service, final ActorRef finish, final ArrayList path) {
            return Props.create(new Creator<Client>() {
                private static final long serialVersionUID = 1L;
 
                @Override
                public Client create() throws Exception {
                  return new Client(service, finish, path);
                }
            });
        }

        ActorRef service;
        ActorRef finish;
        ArrayDeque<ArrayList> paths;

        ArrayList children;
        int index = 0;
        int count = 1;

        public Client(ActorRef service, ActorRef finish, ArrayList path) {
            this.service = service;
            this.finish = finish;

            this.paths = new ArrayDeque<ArrayList>();
            this.paths.addLast(path);
        }

        public void onReceive(Object message) {
            if(message instanceof Start) {
                // Starting out...
                service.tell(this.paths.removeFirst(), getSelf());
            } else if(message instanceof ArrayList) {
                // Service got me my data back...
                children = (ArrayList)message;
                count = count + children.size();

                for(int i = 0; i < children.size(); i++) {
                    this.paths.addLast((ArrayList)children.get(i));
                }

                //workerRouter = getContext().actorOf(Client.props(...));

                if(this.paths.size() == 0) {
                    finish.tell(new Integer(count), getSelf());
                } else {
                    service.tell(this.paths.removeFirst(), getSelf());
                }
            }
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        final ActorSystem system = ActorSystem.create("akka");
        final Inbox inbox = Inbox.create(system);

        ActorRef service = system.actorOf(Props.create(Service.class), "service");
        ActorRef client = system.actorOf(Client.props(service, inbox.getRef(), new ArrayList()), "client");

        System.out.println("Starting!");

        client.tell(new Start(), ActorRef.noSender());
        Object o = inbox.receive(Duration.create(5, TimeUnit.MINUTES));

        System.out.println("Count: " + o);
        System.out.println("Done!");

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
