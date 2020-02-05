package com.spira.teststep;

public class TestStep {

    public String getDescription() {
        return _description;
    }

    public String getExpectedResult() {
        return _expectedResult;
    }

    public String getSampleData() {
        return _sampleData;
    }

    public int getTestCaseId() {
        return _testCaseId;
    }

    public int getTestStepId() {
        return _testStepId;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public void setExpectedResult(String expectedResult) {
        _expectedResult = expectedResult;
    }

    public void setSampleData(String sampleData) {
        _sampleData = sampleData;
    }

    public void setTestCaseId(int testCaseId) {
        _testCaseId = testCaseId;
    }

    public void setTestStepId(int testStepId) {
        _testStepId = testStepId;
    }

    private String _description;
    private String _expectedResult;
    private String _sampleData;

    private int _testCaseId;
    private int _testStepId;
}
