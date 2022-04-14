package com.qaprosoft.carina.work.mobile.gui.pages.utils.enums;

public enum ActivityLevel {
    NOT_VERY_ACTIVE("Not Very Active"),
    LIGHTLY_ACTIVE("Lightly Active"),
    ACTIVE("Active"),
    VERY_ACTIVE("Very Active");

    String name;

    ActivityLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}