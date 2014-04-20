import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Inbox;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.ArrayList;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Main {
    public static class Message implements Serializable {}

    public static class Ponger extends UntypedActor {
        public void onReceive(Object message) {
            getSender().tell(new Message(), getSelf());
        }
    }

    public static int random(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        final ActorSystem system = ActorSystem.create("akka");
        final Inbox inbox = Inbox.create(system);

        ArrayList actors = new ArrayList();

        for(int i = 0; i < 100; i++) {
            actors.add(system.actorOf(Props.create(Fib.class), "generator" + i));
        }

        for(int i = 0; i < 1000000; i++) {
            ActorRef actor = actors.get(random(0, 100 - 1));

            inbox.send(actor, new Message());
            inbox.receive(Duration.create(5, TimeUnit.SECONDS));
        }

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
