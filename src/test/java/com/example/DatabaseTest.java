package com.example;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DatabaseTest {

    @Test
    public void testAvailableBunsNotEmpty() {
        Database db = new Database();
        List<Bun> buns = db.availableBuns();
        assertThat("Список булочек не  должен быть пустым", buns, is(not(empty())));
    }

    @Test
    public void testAvailableIngredientsNotEmpty() {
        Database db = new Database();
        List<Ingredient> ingredients = db.availableIngredients();
        assertThat("Список ингредиентов  не должен быть пустым", ingredients, is(not(empty())));
    }

    @Test
    public void testClearDatabase() {
        Database db = new Database();
        db.clear();

        assertThat("После очистки список булочек пуст", db.availableBuns(), is(empty()));
        assertThat("После очистки список ингредиентов пуст", db.availableIngredients(), is(empty()));
    }
}