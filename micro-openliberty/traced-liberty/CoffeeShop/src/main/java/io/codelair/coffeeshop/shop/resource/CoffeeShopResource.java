package io.codelair.coffeeshop.shop.resource;

import io.codelair.coffeeshop.shop.persistence.model.Coffee;
import io.codelair.coffeeshop.shop.persistence.model.CoffeeOrder;
import io.codelair.coffeeshop.shop.persistence.repository.CoffeeRepository;
import io.codelair.coffeeshop.shop.service.OrderManagerService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/coffee")
@Tag(name = "CoffeeShop API", description = "Find out about available coffee products and place orders")
public class CoffeeShopResource
{

    @Inject
    private CoffeeRepository coffeeRepository;

    @Inject
    private OrderManagerService orderManager;

    @PUT
    @Path("/order")
    @Operation(summary = "Place a coffee order")
    public Response putOrder(
            @RequestBody(description = "Place a coffee order",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = CoffeeOrder.class))) CoffeeOrder order, @Context UriInfo uriInfo)
    {
        var isOrderPlaced = orderManager.placeOrder(order);

        if (!isOrderPlaced)
        {
            return Response.status(404).build();
        } else
        {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(123));
            return Response.created(uriBuilder.build()).build();

        }
    }

    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds all possible Coffees that can be ordered")
    public List<Coffee> getCoffeeTypes()
    {
        // Retrieve all possible coffeeTypes
        return coffeeRepository.getCoffees();
    }
}
