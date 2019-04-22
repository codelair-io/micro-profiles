package io.codelair.coffeeshop.shop.persistence.model;

public class CoffeeOrder
{
    private Coffee coffee;
    private int quantity;

    public CoffeeOrder()
    {
    }

    CoffeeOrder(Builder builder)
    {
        coffee = builder.coffee;
        quantity = builder.quantity;
    }

    // Getters
    public Coffee getCoffee()
    {
        return coffee;
    }

    public int getQuantity()
    {
        return quantity;
    }

    // Builder
    public static class Builder
    {
        private Coffee coffee;
        private int quantity;

        // build
        public CoffeeOrder build()
        {
            return new CoffeeOrder(this);
        }

        public Builder setCoffee(Coffee coffee)
        {
            this.coffee = coffee;
            return this;
        }

        public Builder setQuantity(int quantity)
        {
            this.quantity = quantity;
            return this;
        }
    }
}
