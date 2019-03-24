package codelair;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/todos")
public class TodoServlet extends HttpServlet {

    @Inject
    private TodoService todoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Stream<String> todos = todoService
                .getTodos()
                .map(List::stream)
                .orElse(Stream.empty());


        String   response = todos
            .distinct()
            .map(String::toUpperCase)
            .collect(Collectors.joining("\n"));

        // Create response
        resp.setStatus(200);
        resp.setContentType("text/plain");
        resp.getWriter().write("resp : " + response);
        resp.getWriter().flush();
    }
}
