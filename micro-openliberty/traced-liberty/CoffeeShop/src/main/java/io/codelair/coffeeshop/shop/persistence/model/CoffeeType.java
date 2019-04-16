package io.codelair.coffeeshop.shop.persistence.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "CoffeeType", description = "Object which presents a coffee type")

public class CoffeeType {

    private String coffeeName;
    private double price;

    public CoffeeType(Builder builder) {
        coffeeName = builder.coffeeName;
        price = builder.price;
    }

    // Getter
    public String getCoffeeName() {
        return coffeeName;
    }
    public double getPrice() {
        return price;
    }

    // Builder
    public static class Builder {
        private String coffeeName;
        private double price;

        // build
        public CoffeeType build(){
            return new CoffeeType(this);
        }

        public Builder setCoffeeName(String coffeeName) {
            this.coffeeName = coffeeName;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }
    }
}
