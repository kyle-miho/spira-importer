package com.spira.project;

import com.constants.SpiraConstants;
import com.util.JSONUtil;
import com.util.SpiraUtil;
import org.json.JSONArray;
import org.json.JSONObject;

public class ProjectUtil {

    public static Project fetchProject(int projectId) throws Exception {
        JSONObject projectJSON =
                SpiraUtil.getAPIRequestJSONObject(_getGetEndPoint(projectId));

        if (projectJSON == null) {
            return null;
        }

        String projectName = projectJSON.get("Name").toString();
        String projectProjectId = projectJSON.get("ProjectId").toString();
        String projectProjectTemplateId = projectJSON.get("ProjectTemplateId").toString();
        String projectProjectGroupId = projectJSON.get("ProjectGroupId").toString();

        Project project = new Project();

        project.setName(projectName);
        project.setProjectGroupId(Integer.valueOf(projectProjectGroupId));
        project.setProjectTemplateId(Integer.valueOf(projectProjectTemplateId));
        project.setProjectId(Integer.valueOf(projectProjectId));

        return project;
     }

    private static String _getGetEndPoint(int projectId) {
        return SpiraConstants.BASE_ENDPOINT + "projects/" + projectId;
    }
}
