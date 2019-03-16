package io.codelair.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

@Health
@ApplicationScoped
public class TodoServletHealth implements HealthCheck {
    private static final String HEALTH_CHECK_NAME = "TodoServletHealth";

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named(HEALTH_CHECK_NAME)
                .up()
                .build();
    }


}
