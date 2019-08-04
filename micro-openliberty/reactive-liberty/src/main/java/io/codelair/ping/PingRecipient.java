package io.codelair.ping;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bugs: none known
 *
 * @author Hassan Nazar, hassenasse @ github (2019)
 */
public class PingRecipient
{
    private static final Logger log = LoggerFactory.getLogger( PingRecipient.class.getName() );

    @Incoming( value = "ping-sink" )
    public void ping( String message )
    {
        log.debug( "Ping Message Received: %s", message );
    }
}
