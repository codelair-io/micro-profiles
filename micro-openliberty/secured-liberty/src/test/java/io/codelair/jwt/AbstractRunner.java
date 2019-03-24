package io.codelair.jwt;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

public class AbstractRunner {

    /**
     * Creates a basic archive containing commonly needed libs and
     * classes.
     *
     * @return
     */
    public static WebArchive createBaseDeployment(){

        var restAssured = "io.rest-assured:rest-assured:3.3.0";
        var assertj = "org.assertj:assertj-core:3.11.1";
        var resolver = Maven.resolver().loadPomFromFile("pom.xml");

        return ShrinkWrap.create(WebArchive.class)
                .addClass(AbstractRunner.class)
                .addAsLibraries(resolver.resolve(assertj).withTransitivity().asFile())
                .addAsLibraries(resolver.resolve(restAssured).withTransitivity().asFile());
    }
}
