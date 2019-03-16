package io.codelair;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TodoService {
    private List<String> todos;

    void onStart(@Observes StartupEvent ev) {
        System.out.println("TodoService starting...");
        todos = Arrays.asList("Get groceries", "Design stickers", "Scratch beard");
    }

    public Optional<List<String>> getTodos(){
        return Optional.of(todos);
    }


    void onStop(@Observes ShutdownEvent ev) {
        System.out.println("TodoService starting...");
    }
}
