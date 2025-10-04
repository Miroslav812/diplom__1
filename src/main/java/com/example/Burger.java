package com.example;

import java.util.ArrayList;
import java.util.List;

public class Burger {

    public Bun bun;
    public List<Ingredient> ingredients = new ArrayList<>();

    public void setBuns(Bun bun) {
        if (bun == null) throw new IllegalArgumentException("Bun  cannot be null");
        this.bun = bun;
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredient == null) throw new IllegalArgumentException("Ingredient cannot be null");
        ingredients.add(ingredient);
    }

    public void removeIngredient(int index) {
        if (index < 0 || index >= ingredients.size())
            throw new IndexOutOfBoundsException("Invalid  ingredient index");
        ingredients.remove(index);
    }

    public void moveIngredient(int index, int newIndex) {
        if (index < 0 || index >= ingredients.size() || newIndex < 0 || newIndex > ingredients.size())
            throw new IndexOutOfBoundsException("Invalid  ingredient index");
        ingredients.add(newIndex, ingredients.remove(index));
    }

    public float getPrice() {
        float price = bun.getPrice() * 2;
        for (Ingredient ingredient : ingredients) {
            price += ingredient.getPrice();
        }
        return price;
    }

    public String getReceipt() {
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));
        for (Ingredient ingredient : ingredients) {
            receipt.append(String.format("= %s %s =%n",
                    ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }
        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", getPrice()));
        return receipt.toString();
    }
}
