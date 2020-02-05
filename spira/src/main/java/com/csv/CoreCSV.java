package com.csv;

import com.constants.SpiraConstants;
import com.getter.SpiraTestTypeGetter;
import com.constants.SpiraWorkflowConstants;
import com.getter.TestCasePriorityGetter;
import com.spira.component.Component;
import com.spira.component.ComponentUtil;
import com.spira.project.Project;
import com.spira.project.ProjectUtil;
import com.spira.testcase.TestCaseUtil;
import com.spira.testcase.folder.TestCaseFolder;
import com.spira.testcase.folder.TestCaseFolderUtil;
import com.spira.testcase.priority.TestCasePriority;
import com.spira.testcase.priority.TestCasePriorityUtil;
import com.util.Validator;

import java.util.List;

public class CoreCSV extends BaseCSV {

    public CoreCSV(String filePath) {
        super(filePath);
    }

    @Override
    public TestCaseFolder getRootFolder() throws Exception {
        if (_rootTestCaseFolder == null) {
            _rootTestCaseFolder =
                TestCaseFolderUtil.addTestCaseFolder(SpiraConstants.PROJECT_ID,
        0, "Core Infrastructure", "Core Infrastructure Test Map");
        }

        return _rootTestCaseFolder;
    }

    public void importRows() throws Exception {
        TestCaseFolder currentFolder = null;
        TestCaseFolder currentSubFolder = null;

        Component currentComponent = null;

        for (List<String> row : getRows()) {
            if (row.get(0).equals("Component")) {
                continue;
            }

            if (_isFolder(row)) {
                String componentName = row.get(1);

                currentFolder =
                    TestCaseFolderUtil.addTestCaseFolder(
                    SpiraConstants.PROJECT_ID, _rootTestCaseFolder.getFolderId(),
                    componentName, "");

                currentComponent =
                    ComponentUtil.fetchComponent(SpiraConstants.PROJECT_ID, componentName);

                if (currentComponent == null) {
                    currentComponent =
                        ComponentUtil.addComponent(SpiraConstants.PROJECT_ID, componentName);
                }
            } else if (_isSubFolder(row)) {
                currentSubFolder =
                        TestCaseFolderUtil.addTestCaseFolder(
                                SpiraConstants.PROJECT_ID, currentFolder.getFolderId(),
                                row.get(2), "");
            } else {
                TestCaseUtil.addTestCase(SpiraConstants.PROJECT_ID,
                        row.get(TESTCASE_NAME), row.get(TESTCASE_DESCRIPTION),
                        row.get(TESTCASE_STEPS), currentComponent.getComponentId(),
                        _buildPriorityId(row), currentSubFolder.getFolderId(), SpiraWorkflowConstants.APPROVED,
                        _getTestcaseType(row.get(TESTCASE_TYPE)));
            }
        }

        return;
    }

    private int _buildPriorityId(List<String> row) throws Exception {
        String priorityString = row.get(TESTCASE_PRIORITY);

        int priorityId = 0;

        if (Validator.isNotNull(priorityString)) {
           int priorityScore = Integer.valueOf(priorityString);

            TestCasePriority testCasePriority =
                    TestCasePriorityGetter.get(priorityScore);

            priorityId = testCasePriority.getPriorityId();
        }

        return priorityId;
    }

    private int _getTestcaseType(String type) throws Exception {
        String key = type;

        if (type.equals("Manual")) {
            key = "Exploratory";
        }

        return Integer.valueOf(SpiraTestTypeGetter.get(key));
    }

    private boolean _isFolder(List<String> row) {
        if (Validator.isNull(row.get(1))) {
            return false;
        }

        for (int i = 2; i < row.size(); ++i) {
            if (Validator.isNotNull(row.get(i))) {
                return false;
            }
        }

        return true;
    }

    private boolean _isSubFolder(List<String> row) {
        if (Validator.isNull(row.get(2))) {
            return false;
        }

        for (int i = 3; i < row.size(); ++i) {
            if (Validator.isNotNull(row.get(i))) {
                return false;
            }
        }

        return true;
    }

    private static final int TESTCASE_NAME = 2;
    private static final int TESTCASE_TYPE = 4;
    private static final int TESTCASE_PRIORITY = 6;
    private static final int TESTCASE_DESCRIPTION = 11;
    private static final int TESTCASE_STEPS = 12;

    private TestCaseFolder _rootTestCaseFolder;
}
