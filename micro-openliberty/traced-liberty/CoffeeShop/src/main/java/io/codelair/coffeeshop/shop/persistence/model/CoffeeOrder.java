package io.codelair.coffeeshop.shop.persistence.model;

public class CoffeeOrder {
    private CoffeeType coffeeType;
    private int quantity;

    public CoffeeOrder(Builder builder) {
        coffeeType = builder.coffeeType;
        quantity = builder.quantity;
    }

    // Getters
    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public int getQuantity() {
        return quantity;
    }

    // Builder
    public static class Builder {
        private CoffeeType coffeeType;
        private int quantity;

        // build
        public CoffeeOrder build() {
            return new CoffeeOrder(this);
        }

        public Builder setCoffeeType(CoffeeType coffeeType) {
            this.coffeeType = coffeeType;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }
    }
}
