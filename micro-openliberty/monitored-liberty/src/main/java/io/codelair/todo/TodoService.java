package io.codelair.todo;

import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.RegistryType;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/todo")
@ApplicationScoped
@Timed(name = "TodoService.responsetime", unit = MetricUnits.MILLISECONDS)
public class TodoService {

    private List<Todo> todoList = new ArrayList<>();

    @Inject
    @RegistryType(type = MetricRegistry.Type.APPLICATION)
    MetricRegistry registry;

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTodo(Todo todo){
        var resp = Response
            .created(URI.create("/todos/" + todo.getId()))
            .build();
        // Adjust new task id
        todo.setId(todoList.size() + 1);
        if (todoList.contains(todo))
                resp = Response.status(409).build();
        else todoList.add(todo);

        registry.counter("create_todo_" + resp.getStatus()).inc();
        return resp;
    }

}
