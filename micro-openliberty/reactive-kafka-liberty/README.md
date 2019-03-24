# MicroProfile Reactive Messaging + Reactive stream Operators

This project uses Microprofile Reactive Operators v1.0 (from SmallRye impl v1.0.2). In addition, it utilizes Microprofile Reactive messaging spec v1.0-SNAPSHOT (from SmallRye impl v0.0.4) to talk with with Kafka. Further information regarding both spec's and their implementation is provided below.

## Important Notice

As none of these specifications are currently a part of any MicroProfile release, we need to do some work-around to get the applications to run.

## MicroProfile Reactive Streams Operators v1.0

SmallRye Reactive Streams Operators is based on RXJava 2. The idea behind this specification is to provide a similar streaming API as that of `java.util.stream`, however, making it fully reactive. Some of the differences being:

| java.util.stream                         |        io.smallrye.reactive.streams        |
| ---------------------------------------- | :----------------------------------------: |
| Synchronous                              |                Asynchronous                |
| Data only channel                        |      3 channels, data+error+complete       |
| No possible exception handling technique | Deals with it downstream (Circuit Breaker) |

Example (taken from `https://smallrye.io/smallrye-reactive-streams-operators/`):

```
// java.util.stream version:
Stream.of("hello", "world")
        .filter(word -> word.length() <= 5)
        .map(String::toUpperCase)
        .findFirst()
        .ifPresent(s -> System.out.println("Regular Java stream result: " + s));
// reactive stream operator version:
ReactiveStreams.of("hello", "world")
        .filter(word -> word.length() <= 5)
        .map(String::toUpperCase)
        .findFirst()
        .run() // Run the stream (start publishing)
        // Retrieve the result asynchronously, using a CompletionStage
        .thenAccept(res -> res
                .ifPresent(s -> System.out.println("Reactive Stream result: " + s)));
```

## MicroProfile Reactive Messaging 1.0-SNAPSHOT

Reactive Messaging provides:

1. a development model to build data streaming applications
2. connections for Apache Kafka, AMQP 1.0, MQTT, Apache Camel…​
3. a way to inject manipulated streams into regular CDI beans and JAX-RS resources

Reactive messaging promises to work with any implementation of the Reactive Streams Operator, any CDI 2.x implementation.
