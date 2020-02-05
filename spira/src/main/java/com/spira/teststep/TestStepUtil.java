package com.spira.teststep;

import com.constants.SpiraConstants;
import com.spira.testcase.priority.TestCasePriority;
import com.util.SpiraUtil;
import com.util.Validator;
import org.json.JSONObject;

public class TestStepUtil {

    public static TestStep addTestStep(
            int projectId, int testCaseId, String description, String expectedResult,
            String sampleData)
        throws Exception {

        JSONObject params = _buildParams(description, expectedResult, sampleData);

        JSONObject jsonObject =
                SpiraUtil.postAPIRequest(_getAddEndPoint(projectId, testCaseId), params);

        String testStepTestCaseId = jsonObject.get("TestStepId").toString();
        String testStepId = jsonObject.get("TestStepId").toString();
        String testStepDescription = jsonObject.get("Description").toString();
        String testStepExpectedResult = jsonObject.get("ExpectedResult").toString();
        String testStepSampleData = jsonObject.get("SampleData").toString();

        TestStep testStep = new TestStep();

        testStep.setDescription(testStepDescription);
        testStep.setExpectedResult(testStepExpectedResult);
        testStep.setSampleData(testStepSampleData);
        testStep.setTestCaseId(Integer.valueOf(testStepTestCaseId));
        testStep.setTestStepId(Integer.valueOf(testStepId));

        return testStep;
    }

    private static JSONObject _buildParams(
            String description, String expectedResult, String sampleData)
        throws Exception {

        JSONObject jsonObject = new JSONObject();

        if (Validator.isNotNull(description)) {
            jsonObject.put("Description", description);
        }

        if (Validator.isNotNull(expectedResult)) {
            jsonObject.put("ExpectedResult", expectedResult);
        }

        if (Validator.isNotNull(sampleData)) {
            jsonObject.put("SampleData", sampleData);
        }

        return jsonObject;
    }

    private static String _getAddEndPoint(int projectId, int testCaseId) {
        return SpiraConstants.BASE_ENDPOINT + "projects/" + projectId + "/test-cases/" + testCaseId + "/test-steps";
    }
}
