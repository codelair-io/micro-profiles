package io.codelair.ping;

import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.annotations.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Pinger
{

    private static final Logger log = LoggerFactory.getLogger( Pinger.class.getName() );

    @Inject
    @Stream( "ping-sink" )
    Emitter< String > pingEmitter;

    @PostConstruct
    public void startPinger()
    {
        log.debug( "Pinger class Startup..." );
        AtomicInteger counter = new AtomicInteger();
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate( () -> pingEmitter.send( "Hello World #" + counter.getAndIncrement() )
                        , 1, 2, TimeUnit.SECONDS );
    }
}
