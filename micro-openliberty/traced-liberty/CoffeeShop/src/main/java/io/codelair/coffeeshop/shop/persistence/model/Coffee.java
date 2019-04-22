package io.codelair.coffeeshop.shop.persistence.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "Coffee", description = "Object which presents a coffee type")
public class Coffee
{

    private UUID id;
    private String coffeeName;
    private double price;

    public Coffee()
    {
    }

    Coffee(Builder builder)
    {
        id = builder.id;
        coffeeName = builder.coffeeName;
        price = builder.price;
    }

    // Getter
    public String getCoffeeName()
    {
        return coffeeName;
    }

    public double getPrice()
    {
        return price;
    }

    public UUID getId()
    {
        return id;
    }

    // Builder
    public static class Builder
    {
        private UUID id;
        private String coffeeName;
        private double price;

        // build
        public Coffee build()
        {
            return new Coffee(this);
        }

        public Builder setId(UUID id)
        {
            this.id = id;
            return this;
        }

        public Builder setCoffeeName(String coffeeName)
        {
            this.coffeeName = coffeeName;
            return this;
        }

        public Builder setPrice(double price)
        {
            this.price = price;
            return this;
        }
    }
}
