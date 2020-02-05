package com.getter;

import com.constants.SpiraConstants;
import com.spira.project.Project;
import com.spira.project.ProjectUtil;
import com.spira.testcase.priority.TestCasePriority;
import com.spira.testcase.priority.TestCasePriorityUtil;

import java.util.HashMap;
import java.util.Map;

public class TestCasePriorityGetter {

    public static TestCasePriority get(int score) throws Exception {
        if (!_testCasePriorityMap.containsKey(score)) {
            Project project = ProjectUtil.fetchProject(SpiraConstants.PROJECT_ID);

            int projectTemplateId = project.getProjectTemplateId();

            TestCasePriority testCasePriority =
                TestCasePriorityUtil.fetchTestCasePriority(
                    projectTemplateId, score);

            _testCasePriorityMap.put(score, testCasePriority);

        }

        return _testCasePriorityMap.get(score);
    }

    private static Map<Integer, TestCasePriority> _testCasePriorityMap =
        new HashMap<>();
}
