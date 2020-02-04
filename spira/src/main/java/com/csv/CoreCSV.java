package com.csv;

import com.constants.SpiraConstants;
import com.constants.SpiraTestTypeConstants;
import com.constants.SpiraWorkflowConstants;
import com.spira.testcase.TestCase;
import com.spira.testcase.TestCaseUtil;
import com.spira.testcase.folder.TestCaseFolder;
import com.spira.testcase.folder.TestCaseFolderUtil;
import com.util.Validator;
import sun.security.provider.ConfigFile;

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

        for (List<String> row : getRows()) {
            if (row.get(0).equals("Component")) {
                continue;
            }

            if (_isFolder(row)) {
                currentFolder =
                    TestCaseFolderUtil.addTestCaseFolder(
                    SpiraConstants.PROJECT_ID, _rootTestCaseFolder.getFolderId(),
                    row.get(1), "");
            } else if (_isSubFolder(row)) {
                currentSubFolder =
                        TestCaseFolderUtil.addTestCaseFolder(
                                SpiraConstants.PROJECT_ID, currentFolder.getFolderId(),
                                row.get(2), "");
            } else {
                TestCaseUtil.addTestCase(SpiraConstants.PROJECT_ID,
                        row.get(TESTCASE_NAME), row.get(TESTCASE_DESCRIPTION),
                        row.get(TESTCASE_STEPS), _buildPriority(row),
                        currentSubFolder.getFolderId(), SpiraWorkflowConstants.APPROVED,
                        _getTestcaseType(row.get(TESTCASE_TYPE)));
            }
        }

        return;
    }

    private int _buildPriority(List<String> row) {
        String priorityString = row.get(6);

        int priority = 0;

        if (Validator.isNotNull(priorityString)) {
            priority = Integer.valueOf(priorityString);
        }

        return priority;
    }

    private int _getTestcaseType(String type) throws Exception {
        if (type.equals("Functional")) {
            return SpiraTestTypeConstants.FUNCTIONAL;
        } else if (type.equals("Integration")) {
            return SpiraTestTypeConstants.INTEGRATION;
        } else if (type.equals("Manual")) {
            return SpiraTestTypeConstants.EXPLORATORY;
        } else if (type.equals("Unit")) {
            return SpiraTestTypeConstants.UNIT;
        } else {
            throw new Exception("Invalid type");
        }
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
