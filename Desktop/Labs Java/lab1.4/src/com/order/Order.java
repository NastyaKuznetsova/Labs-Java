package com.order;

public class Order {
    private String name;
    private int price;
    private String id;

    public Order(String name, int price) {
        this.name = name;
        this.price = price;
        id = String.valueOf(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Price: " + price + "\n" +
                "Id: " + id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
