package com.order;

import com.utils.Generator;

public class OrderingSystem {
    private Order[] ordersAvailable;

    public OrderingSystem() {
        ordersAvailable = Generator.generateOrders();
    }

    public Order[] getOrders() {
        return ordersAvailable;
    }

    public Order getOrderById(String id) {
        for (Order order : ordersAvailable) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }
}
