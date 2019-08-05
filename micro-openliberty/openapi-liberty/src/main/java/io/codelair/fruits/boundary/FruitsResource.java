package io.codelair.fruits.boundary;

import io.codelair.fruits.entity.Fruit;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Fruits Resource API
 * <p>
 * Bugs: none known
 * <p>
 * Limitations:
 * - Cannot track quantity, currently
 *
 * @author Hassan Nazar, hassenasse @ github (2019)
 */
@Path( "/fruits" )
@Tag( name = "Fruits", description = "Fruits Resource" )
public class FruitsResource
{

    private static final Logger log = LoggerFactory.getLogger( FruitsResource.class.getName() );
    private static final List< Fruit > fruitBasket = new ArrayList<>();

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    @Operation( summary = "Get all fruits", description = "Fetch all fruits in the basket" )
    @APIResponse( description = "All fruits", responseCode = "200",
            content = @Content( mediaType = MediaType.APPLICATION_JSON ) )
    @APIResponse( description = "No fruits found", responseCode = "204" )
    public Response getAllFruits()
    {
        log.debug( "Getting all fruits from basket!" );

        if ( fruitBasket.isEmpty() )
        {
            log.debug( "no fruits found" );
            return Response.noContent().build();
        }

        return Response.ok( fruitBasket ).build();
    }

    @GET
    @Path( "/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Operation( summary = "Get a fruit by Id" )
    @APIResponse( description = "A particular fruit based on ID", responseCode = "200",
            content = @Content( mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema( implementation = Fruit.class ) ) )
    @APIResponse( description = "No fruit found", responseCode = "404" )
    public Response getFruitById( @PathParam( "id" ) final UUID id )
    {
        log.debug( "getFruitById called for id #" + id );
        final var optionalFruit = fruitBasket.stream()
                .filter( fruit -> fruit.getId().equals( id ) )
                .findFirst();

        return Response
                .ok( optionalFruit
                        .orElseThrow( () ->
                                new WebApplicationException( Response.Status.NOT_FOUND ) )
                )
                .build();
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Operation( summary = "Create a new fruit" )
    @APIResponse( description = "Fruit created", responseCode = "201" )
    @SecurityRequirement( name = "oauth2", scopes = "post:fruits" )
    public Response createFruit( final Fruit fruit,
                                 @Context final UriInfo uriInfo )
    {
        log.debug( "createFruit: " + fruit.toString() );
        fruit.setId( UUID.randomUUID() );
        fruit.setQuantity( 1 );
        fruitBasket.add( fruit );


        return Response.created( URI.create( "/fruits/" + fruit.getId() ) )
                .build();
    }
}
