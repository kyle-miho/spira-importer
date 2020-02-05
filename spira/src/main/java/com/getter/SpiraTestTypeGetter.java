package com.getter;

import com.constants.SpiraConstants;
import com.spira.project.Project;
import com.spira.testcase.type.TestCaseType;
import com.spira.testcase.type.TestCaseTypeUtil;

import java.util.HashMap;
import java.util.Map;

public class SpiraTestTypeGetter {

    public static int get(String name) throws Exception {
        if (!_testTypes.containsKey(name)) {
            Project project = ProjectGetter.get();

            TestCaseType testCaseType =
                TestCaseTypeUtil.fetchTestCaseType(
                        project.getProjectTemplateId(), name);

            if (testCaseType != null) {
                _testTypes.put(name, testCaseType.getTestCaseTypeId());
            } else {
                throw new Exception(name + " not found as a valid test type.");
            }
        }

        return _testTypes.get(name);
    }

    private static Map<String,Integer> _testTypes = new HashMap<>();
}
