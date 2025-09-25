package com.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BurgerTest {

    private Burger burger;
    private Bun mockBun;
    private Ingredient mockIngredient;

    @Before
    public void setUp() {
        burger = new Burger();

        // Создаём мок для булочки
        mockBun = Mockito.mock(Bun.class);
        Mockito.when(mockBun.getName()).thenReturn("Mock Bun");
        Mockito.when(mockBun.getPrice()).thenReturn(100f);

        // Создаём мок для ингредиента
        mockIngredient = Mockito.mock(Ingredient.class);
        Mockito.when(mockIngredient.getPrice()).thenReturn(50f);
        Mockito.when(mockIngredient.getName()).thenReturn("Mock Ingredient");
        Mockito.when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
    }

    @Test
    public void testBurgerPriceCalculation() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient);

        float expectedPrice = 100f * 2 + 50f + 50f; // булка * 2 + ингредиенты
        assertThat("Проверка  расчёта цены бургера", burger.getPrice(), is(expectedPrice));
    }

    @Test
    public void testReceiptContent() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String receipt = burger.getReceipt();
        assertThat("Чек  содержит название булочки", receipt, containsString("Mock Bun"));
        assertThat("Чек  содержит название ингредиента", receipt, containsString("Mock Ingredient"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBunsNullThrows() {
        burger.setBuns(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddIngredientNullThrows() {
        burger.addIngredient(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientInvalidIndexThrows() {
        burger.removeIngredient(0);
    }

    @Test
    public void testRemoveIngredientWorks() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);

        assertThat("Список ингредиентов пуст после удаления", burger.ingredients, is(empty()));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientInvalidIndexesThrows() {
        burger.addIngredient(mockIngredient);
        burger.moveIngredient(0, 2);
    }

    @Test
    public void testMoveIngredientValidIndexes() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient);

        burger.moveIngredient(0, 1);

        assertThat("Ингредиент перемещён корректно",
                burger.ingredients.get(1).getName(), is("Mock Ingredient"));
    }
}