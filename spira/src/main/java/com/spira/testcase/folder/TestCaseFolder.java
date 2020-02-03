package com.spira.testcase.folder;

import com.spira.base.model.BaseModel;
import com.spira.testcase.TestCase;

import java.util.List;

public class TestCaseFolder extends BaseModel {

    public int getFolderId() {
        return _folderId;
    }

    public int getParentFolderId() {
        return _parentFolderId;
    }

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public List<TestCaseFolder> getTestCaseFolders() {
        return _testCaseFolders;
    }

    public List<TestCase> getTestCases() {
        return _testCases;
    }

    public void setFolderId(int folderId) {
        _folderId = folderId;
    }

    public void setParentFolderId(int parentFolderId) {
        _parentFolderId = parentFolderId;
    }

    public void setName(String name) {
        _name = name;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public void addTestCaseFolder(TestCaseFolder testCaseFolder) {
        _testCaseFolders.add(testCaseFolder);
    }

    public void removeTestCaseFolder(TestCaseFolder testCaseFolder) {
        _testCaseFolders.remove(testCaseFolder);
    }

    public void addTestCase(TestCase testCase) {
        _testCases.add(testCase);
    }

    public void remmoveTestCase(TestCase testCase) {
        _testCases.remove(testCase);
    }

    private int _folderId;
    private int _parentFolderId;
    private String _name;
    private String _description;
    private List<TestCaseFolder> _testCaseFolders;
    private List<TestCase> _testCases;
}
