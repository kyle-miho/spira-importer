package com.csv.parser;

import com.spira.teststep.TestStepUtil;
import com.util.Validator;

import java.util.Arrays;
import java.util.List;

public class TestStepParser {
    public static void createTestSteps(
            int projectId, String testSteps, int testCaseId)
        throws Exception {

        if (Validator.isNull(testSteps)) {
            return;
        }

        List<String> testStepDescriptions = getTestStepDescriptions(testSteps);

        for (String testStepDescription : testStepDescriptions) {
            TestStepUtil.addTestStep(projectId, testCaseId, testStepDescription,
                    "", "");
        }

        return;
    }

    private static List<String> getTestStepDescriptions(String testSteps) {
        String[] splitTestSteps = testSteps.split("\n");

        return Arrays.asList(splitTestSteps);
    }

}
