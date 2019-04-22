package io.codelair.coffeeshop.shop.service;

import io.codelair.coffeeshop.shop.persistence.model.CoffeeOrder;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderManagerService
{
    List<CoffeeOrder> orders = new ArrayList<>();

    public static boolean placeOrder(CoffeeOrder order)
    {
        return true;
    }

}
