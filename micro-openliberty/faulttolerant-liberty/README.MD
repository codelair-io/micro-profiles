# MicroProfile FaultTolerance 2.0
Provides resiliency and fault-tolerance to applications using retry-policies, bulkheads and circuit-breakers. 
Defines the following aspects:  

  - `TimeOut`: Duration granted for an *operation* to finish before it times out
  - `Retry`: Define a criteria on when to retry the *operation*
  - `FallBack`: Alternative solution for a failed *operation*
  - `CircuitBreaker`: Fail-fast on faulty *operation* to prevent system overloading, indefinite wait or timeouts by client.
  - `BulkHead`: Isolate Failures in part of the system, shielding it from other parts.
  
The spec aims to define a common terminology and a standard API for handling fault-tolerance.  


CDI Interceptor-bindings are utilized so that fault-tolerance policies can be applied to the method invocation, and thus, 
adhere to the interceptor binding rule specifications of CDI.
  
  
### @Asynchronous
Used on operations which are executed on a separate thread.  
Methods have to return `Future<T>` or `CompletionStage<T>`.

### @CircuitBreaker
```
@FallBack(fallbackMethod = "myFallBackMethod")
@CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 4, failureRatio = 0.75, delay = 1000)
public ...
```
In the above scenario, if the operation fails 3 times (4x0.75) within the given requestVolumeThreshold, 
circuit breaker will be transitioned to an `Open` state, and all calls are routed to the fallback. After the delay of 1000ms, the circuit breaker
state is transitioned to `Half-open`. And if calls are then completed successfully 10 times (`successThreshold`)
, the breaker state is transitioned to `Closed`.

#### Circuit Breaker states:
- `Closed`: A circuit is closed in normal operations. Allowing calls to enter the business method.
- `Open`: When a `failureRatio` is achieved, the circuit is open, failing fast and throwing a `CircuitBreakerOpenException`
- `Half-open`: Re-evaluation stage of the operation after being previously open. Allows trial-executions (by default one execution, managed by `successThreshold`)
If the trial fails, the breaker is kept open, otherwise it is transitioned to closed.

### @BulkHead
Prevents faults in one part of the system to cascade to all dependants, possibly bringing down the whole system.
`@BulkHead` is only effective when a component can be accessed from multiple contexts. Defines fault-tolerance
by limiting the number of concurrent requests. 