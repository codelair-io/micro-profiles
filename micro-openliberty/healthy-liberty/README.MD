# MicroProfile Health Check 1.0
Defines a container runtime mechanism for validating the `availability` and `status` of an application.
Intended for M2M (Machine to Machine) communication as used in e.g. Kubernetes Liveness and Readiness Probes.

Used to determine if a computing node needs to be discarded and replaced. 

#### Procedure
The health of an application
is evaluated by calling the `http(s)://<HOST>:<PORT>/health` endpoint, which in turn calls all Health check procedures (custom HealthCheck `call()`
methods). The returned `HealthCheckResponse`'s are evaluated as a logical AND to represent the health of an application.

`http(s)://<HOST>:<PORT>/health` returns:
- `200`: Health check with positive outcome, all checks passed.
- `503`: Health check with negative outcome, all or some checks failed.
- `500`: Consumer unable to process health check requests.

#### Sample HealthCheck
```
@Health
@ApplicationScoped
public class SuccessfulCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call(){
        return HealthCheckResponse.named("successfull-check").up();
    }
}
``` 