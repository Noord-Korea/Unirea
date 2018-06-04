package com.restserver.buildings.resource.models;

import com.models.Building;

public abstract class BaseNormalBuilding extends Building {
    private String name;
    private int level;

    public BaseNormalBuilding(){

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
