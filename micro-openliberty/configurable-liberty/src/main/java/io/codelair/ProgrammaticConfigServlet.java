package io.codelair;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet("/config/programmatic")
public class ProgrammaticConfigServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get access to the Config instance
        var optionalConfig= Optional.ofNullable(ConfigProvider.getConfig());

        // fetch config-values if instance was found
        var responseMessage = optionalConfig
                .map(this::createResponseMessage)
                .orElse("No ConfigProvider found");

        // prepare response message
        resp.setStatus(200);
        resp.setContentType(MediaType.TEXT_PLAIN);
        resp.getWriter().write(responseMessage);
        resp.getWriter().flush();
    }

    private String createResponseMessage(final Config config){
        var strBuilder = new StringBuilder();
        return strBuilder
                .append("Name: " + config.getValue("io.codelair.person.name", String.class) + "\n")
                .append("Surname: " + config.getValue("io.codelair.person.surname", String.class) + "\n")
                .append("Location: " + config.getValue("io.codelair.person.location", String.class) + "\n")
                .append("Age: " + config.getValue("io.codelair.person.age", Integer.class) + "\n")
                .append("Pets: " + Arrays.toString(config.getValue("io.codelair.person.pets", String[].class)) + "\n")
                .append("Account Balance: " + config.getValue("io.codelair.person.balance", Double.class) + "\n")
                .append("Gender: " + config.getValue("io.codelair.person.gender", String.class) + "\n")
                .append("Profession: " + config.getValue("io.codelair.person.profession", String.class) + "\n")
                .toString();
    }
}
