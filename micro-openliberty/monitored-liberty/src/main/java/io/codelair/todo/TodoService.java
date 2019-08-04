package io.codelair.todo;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/todo")
@ApplicationScoped
@Timed(name = "TodoService.responsetime", unit = MetricUnits.MILLISECONDS)
public class TodoService {

    private List<Todo> todoList = new ArrayList<>();

    @PostConstruct
    public void postConstruct(){
        todoList.add(new Todo()
                .setId(1)
                .setTask("Write some code")
                .setDescription("Self-explanatory")
                .setFinished(false)
        );
        todoList.add(new Todo()
                .setId(2)
                .setTask("Scrub my beard")
                .setDescription("Sooo soothing........ mmmmh")
                .setFinished(false)
        );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "getAllTodos.count",
            absolute = true, //Use absolute name of metrics, w.o prepending package
            description = "sample-description",
            tags = {"app=myval", "metric=customCounterMetric"})
    public Response getAllTodos(){
        return Response.ok(todoList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTodo(Todo todo){
        // Adjust new task id
        todo.setId(todoList.size() + 1);
        todoList.add(todo);
        return Response.ok().build();
    }

}
