package io.codelair;

import io.codelair.ping.Pinger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * Bugs: none known
 *
 * @author Hassan Nazar, hassenasse @ github (2019)
 */
@ApplicationScoped
public class Startup
{
    @Inject
    Pinger pinger;

    public void init( @Observes @Initialized( ApplicationScoped.class ) Object init )
    {
    }

}
