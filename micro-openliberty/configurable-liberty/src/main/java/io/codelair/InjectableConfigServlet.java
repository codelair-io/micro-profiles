package io.codelair;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@RequestScoped
@WebServlet("/config/injectable")
public class InjectableConfigServlet extends HttpServlet {

    @Inject
    @ConfigProperty(name = "io.codelair.person.name")
    private String name;

    @Inject
    @ConfigProperty(name = "io.codelair.person.surname")
    private Optional<String> optionalSurname; // optionals are supported

    @Inject
    @ConfigProperty(
            name = "io.codelair.person.location",
            defaultValue = "unknown") // default value-mappings are also supported
    private String location;

    @Inject
    @ConfigProperty(name = "io.codelair.person.age", defaultValue = "0")
    private int age;

    @Inject
    @ConfigProperty(name = "io.codelair.person.pets")
    private String[] pets;

    @Inject
    @ConfigProperty(name = "io.codelair.person.balance")
    private double balance;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // create response message
        final var responseMessage = new StringBuilder()
                .append("Name : " + name + "\n")
                .append("Surname : " + optionalSurname.orElse("unknown") + "\n")
                .append("Append : " + location + "\n")
                .append("Age : " + age + "\n")
                .append("Pets : " + Arrays.toString(pets) + "\n")
                .append("Account balance : " + balance + "\n")
                .toString();

        // prepare response message
        resp.setStatus(200);
        resp.setContentType(MediaType.TEXT_PLAIN);
        resp.getWriter().write(responseMessage);
        resp.getWriter().flush();
    }
}
