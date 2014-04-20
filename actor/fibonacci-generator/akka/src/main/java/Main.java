import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Creator;
import akka.actor.Inbox;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Main {
    public static class Start implements Serializable {}

    public static class Stop implements Serializable {}

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

    public static class Tester extends UntypedActor {
        public static Props props(final ActorRef generator) {
            return Props.create(new Creator<Tester>() {
                private static final long serialVersionUID = 1L;
 
                @Override
                public Tester create() throws Exception {
                  return new Tester(generator);
                }
            });
        }

        final ActorRef generator;

        ActorRef starter;
        int count = 1000000;

        public Tester(ActorRef generator) {
            this.generator = generator;
        }

        public void onReceive(Object message) {
            if(message instanceof Start) {
                starter = getSender();
                generator.tell(new Message(0), getSelf());
            } else if(message instanceof Message) {
                if(count == 0) {
                    System.out.println("Stopping!");
                    starter.tell(new Stop(), getSelf());
                } else {
                    this.count = this.count - 1;
                    generator.tell(new Message(0), getSelf());
                }
            }
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        final ActorSystem system = ActorSystem.create("akka");
        final Inbox inbox = Inbox.create(system);

        ActorRef generator = system.actorOf(Props.create(Fib.class), "generator");
        ActorRef tester = system.actorOf(Tester.props(generator), "tester");

        System.out.println("Starting!");

        inbox.send(tester, new Start());
        inbox.receive(Duration.create(5, TimeUnit.MINUTES));

        System.out.println("Done!");

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Total Duration: " + (estimatedTime / 1000000));
    }
}