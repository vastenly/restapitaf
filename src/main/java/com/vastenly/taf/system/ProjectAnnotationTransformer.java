package com.vastenly.taf.system;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectAnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation testannotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {

        //Add a "negative" group to all tests that are not in "positive" group
        String[] groups = testannotation.getGroups();
        List<String> groupsList = new ArrayList<>(Arrays.asList(groups));
        if (!groupsList.contains("positive")) {
            groupsList.add("negative");
            testannotation.setGroups((String[]) groupsList.toArray(new String[0]));
        }

        //Add a RetryAnalyzer
        IRetryAnalyzer retry = testannotation.getRetryAnalyzer();
        if (retry == null) {
            testannotation.setRetryAnalyzer(TestRetryAnalyzer.class);
        }
    }
}
