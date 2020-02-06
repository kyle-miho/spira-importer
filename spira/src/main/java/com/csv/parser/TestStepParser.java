package com.csv.parser;

import com.spira.teststep.TestStepUtil;
import com.util.Validator;
import sun.swing.StringUIClientPropertyKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestStepParser {
    public static void createTestSteps(
            int projectId, String testSteps, int testCaseId)
        throws Exception {

        if (Validator.isNull(testSteps)) {
            return;
        }

        List<String> testStepDescriptions = _parseByPlainText(testSteps);

        for (String testStepDescription : testStepDescriptions) {
            TestStepUtil.addTestStep(projectId, testCaseId, testStepDescription,
                    "", "");
        }

        return;
    }

    private static List<String> _parseByNewLine(String testSteps) {
        String[] splitTestSteps = testSteps.split("\n");

        return Arrays.asList(splitTestSteps);
    }

    private static List<String> _parseByPlainText(String testSteps) {
        //used to get last match, wont affect string
        testSteps += "1. ";

        Pattern pattern = Pattern.compile("\\d+\\.\\s(.|\\n)*?(?=\\d+\\.\\s)");

        Matcher matcher = pattern.matcher(testSteps);

        List<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group());
        }

        return matches;

    }

}
