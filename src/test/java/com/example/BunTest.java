package com.example;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BunTest {

    @Test
    public void testBunCreation() {
        Bun bun = new Bun("White Bun", 150);
        assertThat("Проверка  имени булочки", bun.getName(), is("White Bun"));
        assertThat("Проверка  цены булочки", bun.getPrice(), is(150f));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBunNameNull() {
        new Bun(null, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBunNameEmpty() {
        new Bun("", 100);
    }

    @Test
    public void testBunToString() {
        Bun bun = new Bun("Black Bun", 200);
        assertThat("Проверка метода toString", bun.toString(), containsString("Black Bun"));
    }
}