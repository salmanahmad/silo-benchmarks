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
    public static class Start implements Serializable {}

    public static class Stop implements Serializable {}

    public static class Ping implements Serializable {}

    public static class Pong implements Serializable {}

    public static class Pinger extends UntypedActor {
        public static Props props(final ArrayList actors) {
            return Props.create(new Creator<Pinger>() {
                private static final long serialVersionUID = 1L;
 
                @Override
                public Pinger create() throws Exception {
                  return new Pinger(actors);
                }
            });
        }

        public static int random(int min, int max) {
            return min + (int)(Math.random() * ((max - min) + 1));
        }

        final ArrayList actors;
        int count = 1000000;
        ActorRef starter = null;

        public Pinger(ArrayList actors) {
            this.actors = actors;
        }

        public void onReceive(Object message) {
            if(message instanceof Pong) {
                if(count == 0) {
                    System.out.println("Stopping!");
                    starter.tell(new Stop(), getSelf());
                } else {
                    this.count = this.count - 1;

                    ActorRef actor = (ActorRef)actors.get(random(0, actors.size() - 1));
                    actor.tell(new Ping(), getSelf());
                }
            } else if(message instanceof Start) {
                starter = getSender();

                ActorRef actor = (ActorRef)actors.get(random(0, actors.size() - 1));
                actor.tell(new Ping(), getSelf());
            }
        }
    }

    public static class Ponger extends UntypedActor {
        public void onReceive(Object message) {
            if(message instanceof Ping) {
                getSender().tell(new Pong(), getSelf());
            }
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        final ActorSystem system = ActorSystem.create("akka");
        final Inbox inbox = Inbox.create(system);

        ArrayList actors = new ArrayList();

        for(int i = 0; i < 100; i++) {
            actors.add(system.actorOf(Props.create(Ponger.class), "ponger" + i));
        }

        ActorRef actor = system.actorOf(Pinger.props(actors), "pinger");

        System.out.println("Starting!");

        inbox.send(actor, new Start());
        inbox.receive(Duration.create(5, TimeUnit.MINUTES));

        System.out.println("Done!");

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
