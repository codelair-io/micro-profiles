package io.codelair.ping;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.time.LocalTime;

public class Pinger
{

    private static final Logger log = LoggerFactory.getLogger( Pinger.class.getName() );

    @PostConstruct
    public void startPinger()
    {
        log.debug( "Pinger class Startup..." );

        var count = 10;
        while ( count-- > 0 )
        {
            pingService();
        }
    }

    @Outgoing( value = "ping-sink" )
    public String pingService()
    {
        log.debug( "Pinging!" );
        return "Hello World" + LocalTime.now();
    }
}
