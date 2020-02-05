package com.spira.component;

import com.spira.base.model.BaseModel;

public class Component extends BaseModel {

    public int getComponentId () {
        return _componentId;
    }

    public String getName() {
        return _name;
    }

    public void setComponentId(int componentId) {
        _componentId = componentId;
    }

    public void setName(String name) {
        _name = name;
    }

    private int _componentId;
    private String _name;
}
