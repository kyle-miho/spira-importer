package com.spira.component;

import com.constants.SpiraConstants;
import com.exception.InvalidProjectIdException;
import com.exception.NullNameException;
import com.thread.local.ComponentThreadLocal;
import com.util.JSONUtil;
import com.util.SpiraUtil;
import com.util.Validator;
import org.json.JSONArray;
import org.json.JSONObject;

public class ComponentUtil {

    public static Component addComponent(int projectId, String name) throws Exception {
        _validateComponent(projectId, name);

        JSONObject params = _buildParams(projectId, name);

        JSONObject jsonObject =
                SpiraUtil.postAPIRequest(_getAddEndPoint(projectId), params);

        String componentId = jsonObject.get("ComponentId").toString();

        Component component = new Component();

        component.setComponentId(Integer.valueOf(componentId));
        component.setName(name);
        component.setProjectId(projectId);

        return component;
    }

    public static Component fetchComponent(int projectId, String name) throws Exception {
        _validateComponent(projectId, name);

        JSONArray jsonArray =
                SpiraUtil.getAPIRequest(_getGetEndPoint(projectId));

        JSONObject componentJSON = JSONUtil.findJSONObject(jsonArray, name, "Name");

        if (componentJSON == null) {
            return null;
        }

        String componentId = componentJSON.get("ComponentId").toString();
        String componentName = componentJSON.get("Name").toString();
        String componentProjectId = componentJSON.get("ProjectId").toString();

        Component component = new Component();

        component.setName(componentName);
        component.setComponentId(Integer.valueOf(componentId));
        component.setProjectId(Integer.valueOf(componentProjectId));

        return component;
    }

    private static JSONObject _buildParams(
            int projectId,  String name) {

        JSONObject jsonObject =
                new JSONObject()
                        .put("ProjectId", Integer.toString(projectId))
                        .put("Name", name);

        return jsonObject;
    }

    private static String _getAddEndPoint(int projectId) {
        return SpiraConstants.BASE_ENDPOINT + "projects/" + projectId + "/components";
    }

    private static String _getGetEndPoint(int projectId) {
        return SpiraConstants.BASE_ENDPOINT + "projects/" + projectId +
                "/components?active_only=true&include_deleted=false";
    }

    private static void _validateComponent(int projectId, String name)
            throws Exception {

        if (Validator.isNotValidProjectId(projectId)) {
            throw new InvalidProjectIdException();
        }

        if (Validator.isNull(name)) {
            throw new NullNameException();
        }

        return;
    }
}
