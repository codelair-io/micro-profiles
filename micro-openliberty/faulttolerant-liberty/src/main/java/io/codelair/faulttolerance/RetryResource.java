package io.codelair.faulttolerance;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/retry")
@RequestScoped
@Retry(maxRetries = 5, delay = 1000, jitter = 500)
public class RetryResource {
    private final String responseJson = "{\"greeting\": \"hello friend!\"}";
    private final String fallbackRespJson = "{\"greeting\": \"hello friend from fallback!\"}";
    private int ticker = 0;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRetry(){
        if(++ticker < 5){
            System.out.println("Retry #" + ticker);
            throw new RuntimeException();
        }

        return Response
                .ok(responseJson)
                .build();
    }

    @GET
    @Path("/fallback")
    @Fallback(fallbackMethod = "fallbackMethod")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRetryFallBack(){
        System.out.println("Retry #" + ++ticker);
        throw new RuntimeException();
    }

    /**
     * Called whenever the `maxRetries` is reached
     * @return a custom fallbackResponse
     */
    private Response fallbackMethod(){
        return Response
                .ok(fallbackRespJson)
                .build();
    }

}
