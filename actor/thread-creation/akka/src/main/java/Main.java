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
    public static class Message implements Serializable {}
    public static class WhoToGreet implements Serializable {
        public final String who;
        public WhoToGreet(String who) {
            this.who = who;
        }
    }
    public static class Greeting implements Serializable {
        public final String message;
        public Greeting(String message) {
            this.message = message;
        }
    }

    public static class Service extends UntypedActor {
        public void onReceive(Object message) {
            unhandled(message);
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        final ActorSystem system = ActorSystem.create("akka");

        for(int i = 0; i < 1000000; i++) {
            ActorRef greeter = system.actorOf(Props.create(Service.class), "greeter" + i);
            greeter.tell(new Message(), ActorRef.noSender());
        }

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }

    public static class GreetPrinter extends UntypedActor {
        public void onReceive(Object message) {
            if (message instanceof Greeting)
                System.out.println(((Greeting) message).message);
        }
    }
}
