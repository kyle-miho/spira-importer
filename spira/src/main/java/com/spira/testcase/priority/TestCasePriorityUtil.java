package com.spira.testcase.priority;

import com.constants.SpiraConstants;
import com.util.JSONUtil;
import com.util.SpiraUtil;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestCasePriorityUtil {

    public static TestCasePriority fetchTestCasePriority(
            int projectTemplateId, int score) throws Exception {

        _validateTestCasePriority(projectTemplateId, score);

        JSONArray jsonArray = SpiraUtil.getAPIRequest(_getGetEndPoint(projectTemplateId));

        JSONObject testCasePriorityJSON = JSONUtil.findJSONObject(jsonArray, score, "Score");

        if (testCasePriorityJSON == null) {
            return null;
        }

        String testCasePriorityName = testCasePriorityJSON.get("Name").toString();
        String testCasePriorityScore = testCasePriorityJSON.get("Score").toString();
        String testCasePriorityId = testCasePriorityJSON.get("PriorityId").toString();

        TestCasePriority testCasePriority = new TestCasePriority();

        testCasePriority.setName(testCasePriorityName);
        testCasePriority.setScore(Integer.valueOf(testCasePriorityScore));
        testCasePriority.setPriorityId(Integer.valueOf(testCasePriorityId));

        return testCasePriority;
    }

    public static String _getGetEndPoint(int projectTemplateId) {
        return SpiraConstants.BASE_ENDPOINT + "project-templates/" + projectTemplateId +
            "/test-cases/priorities";
    }

    private static void _validateTestCasePriority(int projectTemplateId, int score) {
        return;
    }
}
