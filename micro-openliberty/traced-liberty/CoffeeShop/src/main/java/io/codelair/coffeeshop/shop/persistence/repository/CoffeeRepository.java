package io.codelair.coffeeshop.shop.persistence.repository;

import io.codelair.coffeeshop.shop.persistence.model.Coffee;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import java.util.List;
import java.util.UUID;

/**
 * Represents a startup bean which fills a in-memory DS
 * with available CoffeeTypes. In a real-world scenario
 * this data would be retrieved from a downstream service
 * or some persistence DB.
 * <p>
 * Bugs: none known
 *
 * @author Hassan Nazar, hassenasse @ github (2019)
 * @see also     Coffee
 */
@ApplicationScoped
public class CoffeeRepository
{
    private List<Coffee> coffees;

    public List<Coffee> getCoffees()
    {
        return coffees;
    }

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init)
    {
        // Populate in memory DS with available coffee types
        coffees = List.of(
                new Coffee.Builder()
                        .setId(UUID.randomUUID())
                        .setCoffeeName("Espresso")
                        .setPrice(2)
                        .build(),
                new Coffee.Builder()
                        .setId(UUID.randomUUID())
                        .setCoffeeName("Café Latte")
                        .setPrice(2.5)
                        .build(),
                new Coffee.Builder()
                        .setId(UUID.randomUUID())
                        .setCoffeeName("Irish Coffee")
                        .setPrice(1.5)
                        .build(),
                new Coffee.Builder()
                        .setId(UUID.randomUUID())
                        .setCoffeeName("Double Espresso")
                        .setPrice(3.5)
                        .build(),
                new Coffee.Builder()
                        .setId(UUID.randomUUID())
                        .setCoffeeName("Macchiato")
                        .setPrice(2.99)
                        .build(),
                new Coffee.Builder()
                        .setId(UUID.randomUUID())
                        .setCoffeeName("Cappuccino")
                        .setPrice(1.99)
                        .build(),
                new Coffee.Builder()
                        .setId(UUID.randomUUID())
                        .setCoffeeName("Americano")
                        .setPrice(3)
                        .build()
        );


    }
}
