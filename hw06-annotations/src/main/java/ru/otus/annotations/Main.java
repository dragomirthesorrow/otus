package ru.otus.annotations;

import ru.otus.annotations.logic.TestCaseRunnerImpl;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        TestCaseRunnerImpl runner = new TestCaseRunnerImpl();
        //component scan for class annotation TestClass or:
        try {
            runner.runCase(TargetTest.class);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException ex) {
            System.out.println("There is a problem to run test case: " + ex.getMessage());
        }
    }
}
