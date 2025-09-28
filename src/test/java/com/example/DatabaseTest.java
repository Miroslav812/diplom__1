package com.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DatabaseTest {

    @Test
    @DisplayName("Список булочек не пустой")
    @Description("Проверяем, что метод availableBuns возвращает непустой список")
    public void testAvailableBunsNotEmpty() {
        Database db = new Database();
        List<Bun> buns = db.availableBuns();
        checkBunsNotEmpty(buns);
    }

    @Test
    @DisplayName("Список ингредиентов не пустой")
    @Description("Проверяем, что метод availableIngredients возвращает непустой список")
    public void testAvailableIngredientsNotEmpty() {
        Database db = new Database();
        List<Ingredient> ingredients = db.availableIngredients();
        checkIngredientsNotEmpty(ingredients);
    }

    @Test
    @DisplayName("Очистка базы данных")
    @Description("После вызова clear списки булочек и ингредиентов должны быть пустыми")
    public void testClearDatabase() {
        Database db = new Database();
        db.clear();
        checkBunsEmpty(db.availableBuns());
        checkIngredientsEmpty(db.availableIngredients());
    }

    // ---------- Allure steps ----------

    @Step("Проверяем, что список булочек не пустой")
    private void checkBunsNotEmpty(List<Bun> buns) {
        assertThat(buns, is(not(empty())));
    }

    @Step("Проверяем, что список ингредиентов не пустой")
    private void checkIngredientsNotEmpty(List<Ingredient> ingredients) {
        assertThat(ingredients, is(not(empty())));
    }

    @Step("Проверяем, что список булочек пустой")
    private void checkBunsEmpty(List<Bun> buns) {
        assertThat(buns, is(empty()));
    }

    @Step("Проверяем, что список ингредиентов пустой")
    private void checkIngredientsEmpty(List<Ingredient> ingredients) {
        assertThat(ingredients, is(empty()));
    }
}