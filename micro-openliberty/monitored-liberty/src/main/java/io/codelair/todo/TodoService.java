package io.codelair.todo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/todo")
@RequestScoped
public class TodoService {

    private List<Todo> todoList = new ArrayList<>();

    @PostConstruct
    public void postConstruct(){
        todoList.add(new Todo.Builder()
                .setId(1)
                .setTask("Write some code")
                .setDescription("Self-explanatory")
                .setFinished(false)
                .build()
        );
        todoList.add(new Todo.Builder()
                .setId(2)
                .setTask("Scrub my beard")
                .setDescription("Sooo soothing........ mmmmh")
                .setFinished(false)
                .build()
        );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTodos(){
        return Response.ok(todoList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTodo(Todo todo){
        if (todo.getId() < todoList.size() + 1)
            return Response.status(400).build();
        else
            todoList.add(todo);
        return Response.ok().build();
    }

}
