package com.example;

public class Bun {

    public String name;
    public float price;

    public Bun(String name, float price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Bun name cannot be null or empty");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Bun{name='%s', price=%f}", name, price);
    }
}