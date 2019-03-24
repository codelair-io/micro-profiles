package codelair.health;

import codelair.health.event.TodoServiceStartUpEvent;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@Health
@ApplicationScoped
public class StartUpHealth implements HealthCheck {
    private static final String HEALTH_CHECK_NAME = "StartUp";
    private boolean startUpEventReceived;

    @Override
    public HealthCheckResponse call() {
        return startUpEventReceived ? HealthCheckResponse
                .named(HEALTH_CHECK_NAME)
                .up()
                .build() : HealthCheckResponse
                .named(HEALTH_CHECK_NAME)
                .down()
                .build();
    }

    private void startUpEventListener(@Observes TodoServiceStartUpEvent ev){
        startUpEventReceived = ev.isStarted();
    }
}
