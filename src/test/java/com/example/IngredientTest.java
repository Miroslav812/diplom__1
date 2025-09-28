package com.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final Ingredient ingredient;
    private final String expectedName;
    private final float expectedPrice;
    private final IngredientType expectedType;

    public IngredientTest(Ingredient ingredient, String expectedName, float expectedPrice, IngredientType expectedType) {
        this.ingredient = ingredient;
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters(name = "{index}: Проверка ингредиента {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Ingredient(IngredientType.SAUCE, "Hot Sauce", 100), "Hot Sauce", 100f, IngredientType.SAUCE},
                {new Ingredient(IngredientType.FILLING, "Cutlet", 200), "Cutlet", 200f, IngredientType.FILLING}
        });
    }

    @Test
    @DisplayName("Проверка свойств ингредиента")
    @Description("Убедимся, что у ингредиента корректно заданы имя, цена и тип")
    public void testIngredientProperties() {
        checkIngredientName();
        checkIngredientPrice();
        checkIngredientType();
    }

    @Test
    @DisplayName("Метод toString содержит имя ингредиента")
    @Description("Проверяем, что метод toString возвращает строку с именем ингредиента")
    public void testIngredientToString() {
        checkToStringContainsName();
    }

    // ---------- Allure steps ----------

    @Step("Проверяем, что имя ингредиента = {expectedName}")
    private void checkIngredientName() {
        assertThat(ingredient.getName(), is(expectedName));
    }

    @Step("Проверяем, что цена ингредиента = {expectedPrice}")
    private void checkIngredientPrice() {
        assertThat(ingredient.getPrice(), is(expectedPrice));
    }

    @Step("Проверяем, что тип ингредиента = {expectedType}")
    private void checkIngredientType() {
        assertThat(ingredient.getType(), is(expectedType));
    }

    @Step("Проверяем, что toString содержит '{expectedName}'")
    private void checkToStringContainsName() {
        assertThat(ingredient.toString(), containsString(expectedName));
    }
}