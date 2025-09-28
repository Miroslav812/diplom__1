package com.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
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
    @DisplayName("Расчёт цены бургера")
    @Description("Проверяем, что цена считается правильно: булка * 2 + ингредиенты")
    public void testBurgerPriceCalculation() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient);

        float expectedPrice = 100f * 2 + 50f + 50f;
        checkBurgerPrice(expectedPrice);
    }

    @Test
    @DisplayName("Чек содержит все элементы")
    @Description("Проверяем, что в чеке есть название булочки и ингредиента")
    public void testReceiptContent() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String receipt = burger.getReceipt();
        checkReceiptContains(receipt, "Mock Bun");
        checkReceiptContains(receipt, "Mock Ingredient");
    }

    @Test(expected = IllegalArgumentException.class)
    @DisplayName("setBuns(null) выбрасывает исключение")
    @Description("Передача null в setBuns должна приводить к IllegalArgumentException")
    public void testSetBunsNullThrows() {
        burger.setBuns(null);
    }

    @Test(expected = IllegalArgumentException.class)
    @DisplayName("addIngredient(null) выбрасывает исключение")
    @Description("Передача null в addIngredient должна приводить к IllegalArgumentException")
    public void testAddIngredientNullThrows() {
        burger.addIngredient(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    @DisplayName("Удаление ингредиента по неверному индексу")
    @Description("При удалении по несуществующему индексу ожидаем IndexOutOfBoundsException")
    public void testRemoveIngredientInvalidIndexThrows() {
        burger.removeIngredient(0);
    }

    @Test
    @DisplayName("Удаление ингредиента по индексу")
    @Description("Проверяем, что ингредиент удаляется корректно и список становится пустым")
    public void testRemoveIngredientWorks() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);

        checkIngredientsEmpty();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    @DisplayName("Перемещение ингредиента по неверным индексам")
    @Description("Ожидаем IndexOutOfBoundsException при передаче неправильных индексов")
    public void testMoveIngredientInvalidIndexesThrows() {
        burger.addIngredient(mockIngredient);
        burger.moveIngredient(0, 2);
    }

    @Test
    @DisplayName("Перемещение ингредиента по индексам")
    @Description("Проверяем, что ингредиент перемещается корректно")
    public void testMoveIngredientValidIndexes() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient);

        burger.moveIngredient(0, 1);

        checkIngredientNameAtIndex(1, "Mock Ingredient");
    }

    // ---------- Allure steps ----------

    @Step("Проверяем, что цена бургера = {expectedPrice}")
    private void checkBurgerPrice(float expectedPrice) {
        assertThat(burger.getPrice(), is(expectedPrice));
    }

    @Step("Проверяем, что чек содержит '{expectedText}'")
    private void checkReceiptContains(String receipt, String expectedText) {
        assertThat(receipt, containsString(expectedText));
    }

    @Step("Проверяем, что список ингредиентов пуст")
    private void checkIngredientsEmpty() {
        assertThat(burger.ingredients, is(empty()));
    }

    @Step("Проверяем, что ингредиент на позиции {index} имеет имя '{expectedName}'")
    private void checkIngredientNameAtIndex(int index, String expectedName) {
        assertThat(burger.ingredients.get(index).getName(), is(expectedName));
    }
}