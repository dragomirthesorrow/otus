package ru.otus.annotations;

import ru.otus.annotations.annotations.After;
import ru.otus.annotations.annotations.Before;
import ru.otus.annotations.annotations.Test;
import ru.otus.annotations.annotations.TestClass;

@TestClass
public class TargetTest {

    private String testClassField;

    @Before
    public void initDefaults() {
        testClassField = "TEST_DATA";
        System.out.println("preparing class data for testing.");
    }

    @After
    public void resetDefaults() {
        testClassField = null;
        System.out.println("resetting class data for testing.");
    }

    @Test
    public void testCase1() {
        System.out.println("ru.otus.annotations.annotations.Test case 1. DATA:" + testClassField);
    }

    @Test
    public void testCase2() {
        System.out.println("ru.otus.annotations.annotations.Test case 2. DATA:" + testClassField);
    }
}
