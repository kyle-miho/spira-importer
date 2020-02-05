package com.spira.testcase.type;

import com.constants.SpiraConstants;
import com.exception.InvalidProjectIdException;
import com.exception.NullNameException;
import com.util.JSONUtil;
import com.util.SpiraUtil;
import com.util.Validator;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestCaseTypeUtil {

    public static TestCaseType fetchTestCaseType(int projectTemplateId, String name) throws Exception {
        _validateTestCaseType(name);

        JSONArray jsonArray = SpiraUtil.getAPIRequest(_getGetEndPoint(projectTemplateId));

        JSONObject testCaseTypeJSON = JSONUtil.findJSONObject(jsonArray, name, "Name");

        if (testCaseTypeJSON == null) {
            return null;
        }

        String testCaseTypeName = testCaseTypeJSON.get("Name").toString();
        String testCaseTypeId = testCaseTypeJSON.get("TestCaseTypeId").toString();

        TestCaseType testCaseType = new TestCaseType();

        testCaseType.setName(testCaseTypeName);
        testCaseType.setTestCaseTypeId(Integer.valueOf(testCaseTypeId));

        return testCaseType;
    }

    private static String _getGetEndPoint(int projectTemplateId) {
        return SpiraConstants.BASE_ENDPOINT + "project-templates/" + projectTemplateId + "/test-cases/types";
    }

    private static void _validateTestCaseType(String name)
            throws Exception {

        if (Validator.isNull(name)) {
            throw new NullNameException();
        }

        return;
    }
}
