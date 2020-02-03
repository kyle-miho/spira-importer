package com.csv;

import com.constants.SpiraConstants;
import com.spira.testcase.folder.TestCaseFolder;
import com.spira.testcase.folder.TestCaseFolderUtil;
import com.util.Validator;

import java.util.List;

public class CoreCSV extends BaseCSV {

    public CoreCSV(String filePath) {
        super(filePath);
    }

    @Override
    public TestCaseFolder getRootFolder() throws Exception {
        TestCaseFolder baseFolder;
        for (List<String> row : getRows()) {
            if (_getFolderType(row) == FolderType.FOLDER) {
                    baseFolder = TestCaseFolderUtil.addTestCaseFolder(
                            SpiraConstants.PROJECT_ID, 0, row.get(1), null);
            }
        }

        return null;
    }

    private FolderType _getFolderType(List<String> row) {
        if (_isFolder(row)) {
            return FolderType.FOLDER;
        } else if (_isSubFolder(row)) {
            return FolderType.SUBFOLDER;
        }

        return FolderType.TESTCASE;
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

    enum FolderType {
        FOLDER, SUBFOLDER, TESTCASE
    }
}
