package com.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BunTest {

    @Test
    @DisplayName("Создание  булочки с корректными данными")
    @Description("Проверка  имени и цены булочки при создании")
    public void testBunCreation() {
        Bun bun = new Bun("White Bun", 150);
        checkBunName(bun, "White Bun");
        checkBunPrice(bun, 150f);
    }

    @Test(expected = IllegalArgumentException.class)
    @DisplayName("Создание  булочки с null-именем")
    @Description("Ожидаем  IllegalArgumentException при передаче null в name")
    public void testBunNameNull() {
        new Bun(null, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    @DisplayName("Создание булочки с пустым именем")
    @Description("Ожидаем IllegalArgumentException при передаче пустой строки в name")
    public void testBunNameEmpty() {
        new Bun("", 100);
    }

    @Test
    @DisplayName("Метод toString возвращает строку с именем булочки")
    @Description("Проверяем, что в строковом представлении содержится название булочки")
    public void testBunToString() {
        Bun bun = new Bun("Black Bun", 200);
        checkToStringContainsName(bun, "Black Bun");
    }

    // ---------- Allure steps ----------

    @Step("Проверяем, что имя булочки = {expectedName}")
    private void checkBunName(Bun bun, String expectedName) {
        assertThat(bun.getName(), is(expectedName));
    }

    @Step("Проверяем, что цена булочки = {expectedPrice}")
    private void checkBunPrice(Bun bun, float expectedPrice) {
        assertThat(bun.getPrice(), is(expectedPrice));
    }

    @Step("Проверяем, что toString содержит '{expectedName}'")
    private void checkToStringContainsName(Bun bun, String expectedName) {
        assertThat(bun.toString(), containsString(expectedName));
    }
}
