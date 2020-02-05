package com.spira.testcase.type;

public class TestCaseType {

    public String getName() {
        return _name;
    }

    public int getTestCaseTypeId() {
        return _testCaseTypeId;
    }

    public void setName(String name) {
        _name = name;
    }

    public void setTestCaseTypeId(int testCaseTypeId) {
        _testCaseTypeId = testCaseTypeId;
    }

    private String _name;
    private int _testCaseTypeId;
}
