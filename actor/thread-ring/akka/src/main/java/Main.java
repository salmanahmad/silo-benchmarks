import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Creator;
import akka.actor.Inbox;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.ArrayList;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Main {
    public static class Start implements Serializable {
        final ActorRef next;

        public Start(ActorRef next) {
            this.next = next;
        }
    }

    public static class Stop implements Serializable {}

    public static class Service extends UntypedActor {
        public static Props props(final ActorRef next, final ActorRef home, final String tag) {
            return Props.create(new Creator<Service>() {
                private static final long serialVersionUID = 1L;
 
                @Override
                public Service create() throws Exception {
                  return new Service(next, home, tag);
                }
            });
        }

        ActorRef next;
        ActorRef home;
        String tag;

        public Service(ActorRef next, ActorRef home, String tag) {
            this.next = next;
            this.home = home;
            this.tag = tag;
        }

        public void onReceive(Object message) {
            if(message instanceof Integer) {
                int i = ((Integer)message).intValue();

                if(i == 1000000) {
                    home.tell(new Stop(), getSelf());
                } else {
                    next.tell(new Integer(i + 1), getSelf());
                }
            } else if(message instanceof Start) {
                next = ((Start)message).next;
            }
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        final ActorSystem system = ActorSystem.create("akka");
        final Inbox inbox = Inbox.create(system);

        ActorRef start = system.actorOf(Service.props(null, inbox.getRef(), "0"), "0");
        ActorRef previous = start;

        for(int i = 1; i < 1000; i++) {
            previous = system.actorOf(Service.props(previous, inbox.getRef(), "0"), "" + i);
        }

        System.out.println("Starting!");

        start.tell(new Start(previous), ActorRef.noSender());
        start.tell(new Integer(0), ActorRef.noSender());
        inbox.receive(Duration.create(5, TimeUnit.MINUTES));

        System.out.println("Done!");

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
