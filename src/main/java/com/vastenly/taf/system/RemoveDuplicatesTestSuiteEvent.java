package com.vastenly.taf.system;

import ru.yandex.qatools.allure.events.TestSuiteEvent;
import ru.yandex.qatools.allure.model.Label;
import ru.yandex.qatools.allure.model.TestCaseResult;
import ru.yandex.qatools.allure.model.TestSuiteResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RemoveDuplicatesTestSuiteEvent implements TestSuiteEvent {
    private String uid;

    public RemoveDuplicatesTestSuiteEvent(String uid) {
        this.uid = uid;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void process(TestSuiteResult testSuiteResult) {
        System.out.println("Running Allure suite event");
        try {
            List<TestCaseResult> testCases = testSuiteResult.getTestCases();

            //Create a map on test names to list of the test cases with that name
            HashMap<String, List<TestCaseResult>> testMap = getTestsMap(testSuiteResult.getTestCases());
            testMap.forEach((testName, testCaseList) -> {
                //If there is more than one test case with the same name
                //  and one of them has status != cancelled
                //      and one of them has status == cancelled
                if (testCaseList.size() > 1 && hasNonCanceledTests(testCaseList) && hasCanceledTests(testCaseList)) {
                    for (TestCaseResult t : getCancelledTest(testCaseList)) {
                        System.out.println("Removing duplicate failed tests from the suite results: " + testName);
                        testCases.remove(t);
                    }
                }
            });

            testSuiteResult.setTestCases(testCases);
        } catch (Exception e) {
            System.out.println("Caught a big exception while processing Allure test suite event: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private Boolean isTestCaseCanceled(TestCaseResult res) {
        return res.getStatus().value().equals("canceled");
    }

    private Boolean hasNonCanceledTests(List<TestCaseResult> list) {
        for (TestCaseResult res : list) {
            if (!isTestCaseCanceled(res)) {
                return true;
            }
        }
        return false;
    }

    private Boolean hasCanceledTests(List<TestCaseResult> list) {
        for (TestCaseResult res : list) {
            if (isTestCaseCanceled(res)) {
                return true;
            }
        }
        return false;
    }

    private List<TestCaseResult> getCancelledTest(List<TestCaseResult> testCaseResults) {
        List<TestCaseResult> out = new ArrayList<TestCaseResult>();
        for (TestCaseResult t : testCaseResults) {
            if (isTestCaseCanceled(t)) {
                out.add(t);
            }
        }
        return out;
    }

    private String getTestCaseResultName(TestCaseResult t) throws Exception {
        return getTestCaseResultLabel("testClass", t) + "#" + getTestCaseResultLabel("testMethod", t);
    }

    private String getTestCaseResultLabel(String labelName, TestCaseResult t) throws Exception {
        for (Label l : t.getLabels()) {
            if (l.getName().equals(labelName)) {
                return l.getValue();
            }
        }
        throw new Exception(String.format("Test case didn't have a label '%s'. Name: '%s'; Title: '%s'",
                labelName, t.getName(), t.getTitle()));
    }

    private HashMap<String, List<TestCaseResult>> getTestsMap(List<TestCaseResult> testCaseResults) {
        HashMap<String, List<TestCaseResult>> res = new HashMap<>();
        for (TestCaseResult t : testCaseResults) {
            try {
                //Skip this test if it's pending (enabled=false)
                if (t.getStatus().value().equals("pending")) {
                    continue;
                }
                //Otherwise, get the test's name and add it to the map
                String name = getTestCaseResultName(t);
                if (res.containsKey(name)) {
                    res.get(name).add(t);
                } else {
                    List<TestCaseResult> list = new ArrayList<>();
                    list.add(t);
                    res.put(name, list);
                }
            } catch (Exception e) {
                System.out.println("Caught an exception while listing test cases in Allure report: " + e.getMessage());
            }
        }
        return res;
    }
}
