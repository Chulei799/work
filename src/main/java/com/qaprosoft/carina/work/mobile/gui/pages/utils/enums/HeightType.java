package com.qaprosoft.carina.work.mobile.gui.pages.utils.enums;

public enum HeightType {
    FEET_INCHES("Feet & Inches"),
    CENTIMETERS("Centimeters");

    String name;

    HeightType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
