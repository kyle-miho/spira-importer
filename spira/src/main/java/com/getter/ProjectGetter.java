package com.getter;

import com.constants.SpiraConstants;
import com.spira.project.Project;
import com.spira.project.ProjectUtil;

public class ProjectGetter {

    public static Project get() throws Exception {
        if (_project == null) {
            _project = ProjectUtil.fetchProject(SpiraConstants.PROJECT_ID);
        }

        return _project;
    }

    private static Project _project;
}
