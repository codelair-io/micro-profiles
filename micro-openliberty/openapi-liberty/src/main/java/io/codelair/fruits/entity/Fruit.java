package io.codelair.fruits.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Objects;
import java.util.UUID;

/**
 * Fruit entity, describes a fruit.
 * <p>
 * Bugs: none known
 *
 * @author Hassan Nazar, hassenasse @ github (2019)
 */
@Schema( name = "Fruit", description = "POJO that represents a fruit" )
public class Fruit
{
    @Schema( required = false, example = "03bfc86c-e1e1-4aad-89d9-f9d7cf6bff67" )
    private UUID id;
    @Schema( required = true, example = "apple" )
    private String name;
    @Schema( required = true, example = "red" )
    private String color;
    @Schema( required = true, example = "true" )
    private boolean isSour;
    @Schema( required = false, example = "2" )
    private int quantity;

    public UUID getId()
    {
        return id;
    }

    public Fruit setId( final UUID id )
    {
        this.id = id;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public Fruit setName( final String name )
    {
        this.name = name;
        return this;
    }

    public String getColor()
    {
        return color;
    }

    public Fruit setColor( final String color )
    {
        this.color = color;
        return this;
    }

    public boolean isSour()
    {
        return isSour;
    }

    public Fruit setSour( final boolean sour )
    {
        isSour = sour;
        return this;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public Fruit setQuantity( final int quantity )
    {
        this.quantity = quantity;
        return this;
    }

    @Override
    public boolean equals( final Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        final Fruit fruit = ( Fruit ) o;
        return isSour() == fruit.isSour() &&
                getName().equals( fruit.getName() ) &&
                getColor().equals( fruit.getColor() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( getName(), getColor(), isSour() );
    }

    @Override
    public String toString()
    {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", isSour=" + isSour +
                ", quantity=" + quantity +
                '}';
    }
}
