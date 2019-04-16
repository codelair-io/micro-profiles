package io.codelair.coffeeshop.shop.persistence.repository;

import io.codelair.coffeeshop.shop.persistence.model.CoffeeType;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import java.util.List;

/**
 * Represents a startup bean which fills a in-memory DS
 * with available CoffeeTypes. In a real-world scenario
 * this data would be retrieved from a downstream service
 * or some persistence DB.
 * <p>
 * Bugs: none known
 *
 * @author Hassan Nazar, hassenasse @ github (2019)
 * @see also     CoffeeType
 */
@ApplicationScoped
public class CoffeeRepository {
    private List<CoffeeType> coffeeTypes;

    public List<CoffeeType> getCoffeeTypes() {
        return coffeeTypes;
    }

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        // Populate in memory DS with available coffee types
        coffeeTypes = List.of(
                new CoffeeType.Builder()
                        .setCoffeeName("Espresso")
                        .setPrice(2)
                        .build(),
                new CoffeeType.Builder()
                        .setCoffeeName("Café Latte")
                        .setPrice(2.5)
                        .build(),
                new CoffeeType.Builder()
                        .setCoffeeName("Irish Coffee")
                        .setPrice(1.5)
                        .build(),
                new CoffeeType.Builder()
                        .setCoffeeName("Double Espresso")
                        .setPrice(3.5)
                        .build(),
                new CoffeeType.Builder()
                        .setCoffeeName("Macchiato")
                        .setPrice(2.99)
                        .build(),
                new CoffeeType.Builder()
                        .setCoffeeName("Cappuccino")
                        .setPrice(1.99)
                        .build(),
                new CoffeeType.Builder()
                        .setCoffeeName("Americano")
                        .setPrice(3)
                        .build()
        );


    }
}
