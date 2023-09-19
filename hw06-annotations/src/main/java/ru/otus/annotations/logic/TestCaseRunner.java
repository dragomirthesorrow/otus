package ru.otus.annotations.logic;

import java.lang.reflect.InvocationTargetException;

public interface TestCaseRunner {

    void runCase(Class testingClass) throws InstantiationException, IllegalAccessException, InvocationTargetException;
}
