package codelair;

import codelair.health.event.TodoServiceStartUpEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TodoService {

    @Inject
    private Event<TodoServiceStartUpEvent> startUpEvent;

    private List<String> todos;

    void onStart(@Observes @Initialized(ApplicationScoped.class) Object init) {
        System.out.println("TodoService starting...");
        todos = Arrays.asList("Get groceries", "Design stickers", "Scratch beard");

        startUpEvent.fire(new TodoServiceStartUpEvent(true));
    }

    public Optional<List<String>> getTodos(){
        return Optional.of(todos);
    }


    void onStop(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        System.out.println("TodoService starting...");
    }
}
