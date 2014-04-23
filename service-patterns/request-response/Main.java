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
    public static class Get implements Serializable {
        String key;
    }

    public static class Put implements Serializable {
        String key;
        Object value;
    }

    public static class Response implements Serializable {
        Object value;
    }

    public static class Service extends UntypedActor {
        HashMap data = new HashMap()

        public void onReceive(Object message) {
            if(message instanceof Get) {
                Get get = (Get)message;

                Response r = new Response();
                r.value = data.get(get.key);

                getSender().tell(r, getSelf());
            } else if(message instanceof Put) {
                Put put = (Put)message;

                Response r = new Response();
                r.value = data.put(put.key, put.value);

                getSender().tell(r, getSelf());
            } else {
                // Unknown Case
            }
        }
    }
}
