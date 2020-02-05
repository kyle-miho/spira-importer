package com.spira.project;

public class Project {

    public int getProjectId() {
        return _projectId;
    }

    public int getProjectTemplateId() {
        return _projectTemplateId;
    }

    public int getProjectGroupId() {
        return _projectGroupId;
    }

    public String getName() {
        return _name;
    }

    public void setProjectId(int projectId) {
        _projectId = projectId;
    }

    public void setProjectTemplateId(int projectTemplateId) {
        _projectTemplateId = projectTemplateId;
    }

    public void setProjectGroupId(int projectGroupId) {
        _projectGroupId = projectGroupId;
    }

    public void setName(String name) {
        _name = name;
    }

    private int _projectId;
    private int _projectTemplateId;
    private int _projectGroupId;
    private String _name;
}
