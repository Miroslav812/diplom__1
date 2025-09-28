package com.example;

import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BunTest.class,
        BurgerTest.class,
        DatabaseTest.class,
        IngredientTest.class
})
public class AllureTestRunner {
}