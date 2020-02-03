package com.spira.testcase;

import com.constants.SpiraConstants;
import com.spira.base.model.BaseModel;

import java.io.IOException;

public class TestCase extends BaseModel {

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public String getTestSteps() {
        return _testSteps;
    }

    public int getPriority() {
        return _priority;
    }

    public int getFolderId() {
        return _folderId;
    }

    public int getType() {
        return _type;
    }

    public int getStatus() {
        return _status;
    }

    public void setName(String name) {
        _name = name;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public void setTestSteps(String testSteps) {
        _testSteps = testSteps;
    }

    public void setPriority(int priority) {
        _priority = priority;
    }

    public void setFolderId(int folderId) {
        _folderId = folderId;
    }

    public void setStatus(int status) {
        _status = status;
    }

    public void setType(int type) {
        _type = type;
    }

    private String _name;
    private String _description;
    private String _testSteps;
    private int _priority;
    private int _folderId;
    private int _status;
    private int _type;
}
