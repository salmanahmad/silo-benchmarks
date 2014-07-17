package main

import "fmt"
import "math/rand"
import "time"

func random(min, max int) int {
    //rand.Seed(time.Now().Unix())
    return rand.Intn(max - min) + min
}


type Message struct {
    Name string
    Sender chan Message
}

func ponger(mailbox chan Message) {
    for true {
        var message Message = <- mailbox
        message.Sender <- Message{"Pong", mailbox}
    }
}

func pinger(mailbox chan Message, actors []chan Message, done chan int) {
    var i int = 0

    for i = 0; i < 1000000; i++ {
        var actor chan Message = actors[random(0, len(actors) - 1)]
        actor <- Message{"Ping", mailbox}

        <- mailbox
    }

    done <- 0
}

func main() {
    fmt.Printf("Starting\n");
    start := time.Now()

    var i int = 0
    var actors []chan Message
    var mailbox chan Message

    for i = 0; i < 100; i++ {
        mailbox = make(chan Message)
        actors = append(actors, mailbox)

        go ponger(mailbox)
    }

    var done chan int = make(chan int)

    mailbox = make(chan Message)
    go pinger(mailbox, actors, done)

    <- done

    elapsed := time.Since(start)

    fmt.Printf("Done: %s\n", elapsed);
}
