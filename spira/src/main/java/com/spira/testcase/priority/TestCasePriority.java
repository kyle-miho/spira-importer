package com.spira.testcase.priority;

public class TestCasePriority {

    public int getPriorityId() {
        return _priorityId;
    }

    public String getName() {
        return _name;
    }

    public int getScore() {
        return _score;
    }

    public void setPriorityId(int priorityId) {
        _priorityId = priorityId;
    }

    public void setName(String name) {
        _name = name;
    }

    public void setScore(int score) {
        _score = score;
    }

    private int _priorityId;
    private int _score;
    private String _name;
}
