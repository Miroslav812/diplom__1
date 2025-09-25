package com.example;

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

    @Parameterized.Parameters(name = "{index}: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Ingredient(IngredientType.SAUCE, "Hot Sauce", 100), "Hot Sauce", 100f, IngredientType.SAUCE},
                {new Ingredient(IngredientType.FILLING, "Cutlet", 200), "Cutlet", 200f, IngredientType.FILLING}
        });
    }

    @Test
    public void testIngredientProperties() {
        assertThat("Проверка имени ингредиента", ingredient.getName(), is(expectedName));
        assertThat("Проверка цены ингредиента", ingredient.getPrice(), is(expectedPrice));
        assertThat("Проверка типа ингредиента", ingredient.getType(), is(expectedType));
    }

    @Test
    public void testIngredientToString() {
        assertThat("Проверка метода toString ингредиента", ingredient.toString(), containsString(expectedName));
    }
}