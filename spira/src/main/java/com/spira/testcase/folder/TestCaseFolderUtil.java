package com.spira.testcase.folder;

import com.constants.SpiraConstants;
import com.exception.InvalidProjectIdException;
import com.exception.NullNameException;
import com.util.HttpUtil;
import com.util.SpiraUtil;
import com.util.Validator;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class TestCaseFolderUtil {

    public static TestCaseFolder addTestCaseFolder(int projectId, int parentFolderId,
           String name, String description)
        throws Exception {

        _validateTestCaseFolder(projectId, name);

        JSONObject params = _buildParams(
                projectId, parentFolderId, name, description);

        HttpResponse httpResponse =
            SpiraUtil.postAPIRequest(
                    _getAddEndPoint(projectId), params,"POST");

        String json = EntityUtils.toString(httpResponse.getEntity());
        JSONObject jsonObject = new JSONObject(json);
        String testCaseFolderId = jsonObject.get("TestCaseFolderId").toString();

        TestCaseFolder testCaseFolder = new TestCaseFolder();

        testCaseFolder.setFolderId(Integer.valueOf(testCaseFolderId));
        testCaseFolder.setProjectId(projectId);
        testCaseFolder.setParentFolderId(parentFolderId);
        testCaseFolder.setName(name);
        testCaseFolder.setDescription(description);

        return testCaseFolder;
    }

    private static void _validateTestCaseFolder(int projectId, String name)
        throws Exception {

        if (Validator.isNotValidProjectId(projectId)) {
            throw new InvalidProjectIdException();
        }

        if (Validator.isNull(name)) {
            throw new NullNameException();
        }

        return;
    }

    private static JSONObject _buildParams(
        int projectId, int parentFolderId, String name, String description) {

        JSONObject jsonObject =
            new JSONObject()
                .put("Description", description)
                .put("ProjectId", Integer.toString(projectId))
                .put("Name", name);

        if (parentFolderId != 0) {
            jsonObject.put("ParentTestCaseFolderId",parentFolderId);
        }
        return jsonObject;
    }

    private static String _getAddEndPoint(int projectId) {
        return SpiraConstants.BASE_ENDPOINT + "projects/" + projectId + "/test-folders";
    }

}
