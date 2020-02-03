package com.spira.testcase;

import com.constants.SpiraConstants;
import com.exception.InvalidProjectIdException;
import com.exception.NullNameException;
import com.util.HttpUtil;
import com.util.SpiraUtil;
import com.util.Validator;
import org.apache.http.HttpResponse;
import org.json.JSONObject;

public class TestCaseUtil {

    public static TestCase addTestCase(int projectId, String name, String description,
            String testSteps, int priority, int folderId, int status, int type)
        throws Exception {

        _validateTestCase(projectId, name, testSteps, folderId);

        TestCase testCase = new TestCase();

        JSONObject params = _buildParams(folderId, name, description, type, status);

        HttpResponse httpResponse =
                SpiraUtil.postAPIRequest(
                        _getAddEndPoint(projectId), params,"POST");

        HttpUtil.printHttpResponse(httpResponse);

        testCase.setName(name);
        testCase.setDescription(description);
        testCase.setTestSteps(testSteps);
        testCase.setProjectId(projectId);
        testCase.setPriority(priority);
        testCase.setFolderId(folderId);
        testCase.setStatus(status);
        testCase.setType(type);

        return testCase;
    }

    private static void _validateTestCase(int projectId, String name, String testSteps,
           int folderId)
        throws Exception {

        if (Validator.isNotValidProjectId(projectId)) {
            throw new InvalidProjectIdException();
        }

        if (Validator.isNull(name)) {
            throw new NullNameException();
        }

        if (Validator.isNull(testSteps)) {
            throw new Exception("testSteps must not be null or empty");
        }

        if (folderId == 0) {
            throw new Exception("a testcase must have a folder attached to it, please set folderId");
        }

        return;
    }

    private static JSONObject _buildParams(
            int folderId, String name, String description, int type, int status)
        throws Exception{

        JSONObject jsonObject =
            new JSONObject()
                .put("Description", description)
                .put("Name", name)
                .put("TestCaseFolderId", Integer.toString(folderId))
                .put("TestCaseTypeId", Integer.toString(type))
                .put("TestCaseStatusId", Integer.toString(status));

        return jsonObject;
    }

    private static String _getAddEndPoint(int projectId) {
        return SpiraConstants.BASE_ENDPOINT + "projects/" + projectId + "/test-cases";
    }
}
