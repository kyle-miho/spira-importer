package com.spira.testcase;

import com.constants.SpiraConstants;
import com.exception.InvalidProjectIdException;
import com.exception.NullNameException;
import com.getter.UserGetter;
import com.util.SpiraUtil;
import com.util.Validator;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestCaseUtil {

    public static TestCase addTestCase(int projectId, String name, String description,
            String testSteps, int componentId,
            int priorityId, int folderId, int status, int type)
        throws Exception {

        _validateTestCase(projectId, name, testSteps, folderId);

        JSONObject params = _buildParams(folderId, name, description, testSteps, componentId,
                priorityId, type, status);

        JSONObject jsonObject =
                SpiraUtil.postAPIRequest(_getAddEndPoint(projectId), params);

        String testCaseId = jsonObject.get("TestCaseId").toString();

        TestCase testCase = new TestCase();

        testCase.setName(name);
        testCase.setDescription(description);
        testCase.setTestCaseId(Integer.valueOf(testCaseId));
        testCase.setTestSteps(testSteps);
        testCase.setProjectId(projectId);
        testCase.setPriority(priorityId);
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

        if (folderId == 0) {
            throw new Exception("a testcase must have a folder attached to it, please set folderId");
        }

        return;
    }

    private static JSONObject _buildParams(
            int folderId, String name, String description, String testSteps,
            int componentId, int priorityId, int type, int status)
        throws Exception {

        JSONArray componentIdArray = new JSONArray().put(Integer.toString(componentId));

        JSONObject jsonObject =
            new JSONObject()
                .put("Description", description)
                .put("Name", name)
                .put("TestCaseFolderId", Integer.toString(folderId))
                .put("TestCaseTypeId", Integer.toString(type))
                .put("TestCaseStatusId", Integer.toString(status))
                .put("OwnerId", UserGetter.getCurrentUser().getUserId());

        if (componentId != 0) {
            jsonObject.put("ComponentIds" , componentIdArray);
        }

        if (priorityId != 0) {
            jsonObject.put("TestCasePriorityId" , Integer.toString(priorityId));
        }

        return jsonObject;
    }

    private static String _getAddEndPoint(int projectId) {
        return SpiraConstants.BASE_ENDPOINT + "projects/" + projectId + "/test-cases";
    }
}
