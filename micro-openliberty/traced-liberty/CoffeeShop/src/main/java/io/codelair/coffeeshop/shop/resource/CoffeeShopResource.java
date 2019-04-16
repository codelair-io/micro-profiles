package io.codelair.coffeeshop.shop.resource;

import io.codelair.coffeeshop.shop.persistence.model.CoffeeOrder;
import io.codelair.coffeeshop.shop.persistence.model.CoffeeType;
import io.codelair.coffeeshop.shop.persistence.repository.CoffeeRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/coffee")
public class CoffeeShopResource {

    @Inject
    CoffeeRepository coffeeRepository;

    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds all possible Coffees that can be ordered")
    public List<CoffeeType> getCoffeeTypes() {
        // Retrieve all possible coffeeTypes
        return coffeeRepository.getCoffeeTypes();
    }

    @PUT
    @Path("/order")
    @Operation(summary = "Place a coffee order")
    public Response putOrder(
            @RequestBody(description = "Place a coffee order",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = CoffeeOrder.class))) CoffeeOrder order, @Context UriInfo uriInfo) {
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(123));
        return Response.created(uriBuilder.build()).build();
    }
}
