package ru.otus.annotations.logic;

import ru.otus.annotations.TargetTest;
import ru.otus.annotations.annotations.After;
import ru.otus.annotations.annotations.Before;
import ru.otus.annotations.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestCaseRunnerImpl implements TestCaseRunner {

    @Override
    public void runCase(Class testingClass) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Method beforeMethod = null;
        Method afterMethod = null;
        List<Method> testMethods = new ArrayList<>();
        for (Method method : testingClass.getMethods()) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Before){
                    beforeMethod = method;
                    break;
                }
                if (annotation instanceof After){
                    afterMethod = method;
                    break;
                }
                if (annotation instanceof Test){
                    testMethods.add(method);
                    break;
                }
            }
        }
        runTests(testingClass, beforeMethod, afterMethod, testMethods);
    }

    private void runTests(Class testingClass, Method before, Method after, List<Method> tests) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if (tests.isEmpty()) {
            return;
        }
        int succeed = 0;
        int failed = 0;
        for (Method testingMethod : tests) {
            Object testingClassObj = testingClass.newInstance();
            if (before != null) before.invoke(testingClassObj);
            try {
                testingMethod.invoke(testingClassObj);
                succeed++;
            } catch (Exception e) {
                System.out.println(testingMethod.getName() + " was failed with error: \n" + e.getMessage());
                failed++;
            }
            if (after != null) after.invoke(testingClassObj);
        }
        System.out.println(succeed + " тестов успешно выполнено, " + failed + " тестов провалено.");
    }

}
