import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Inbox;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Main {
    public static class Message implements Serializable {
        public final int value;
        public Message(int value) {
            this.value = value;
        }
    }

    public static class Fib extends UntypedActor {
        int a = 0;
        int b = 1;

        public void onReceive(Object message) {
            getSender().tell(new Message(a), getSelf());

            int temp = b;
            b = a;
            a = a + temp;
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        final ActorSystem system = ActorSystem.create("akka");
        final Inbox inbox = Inbox.create(system);

        ActorRef generator = system.actorOf(Props.create(Fib.class), "generator");

        for(int i = 0; i < 1000000; i++) {
            inbox.send(generator, new Message(0));
            Message greeting1 = (Message)inbox.receive(Duration.create(5, TimeUnit.SECONDS));
        }

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}
