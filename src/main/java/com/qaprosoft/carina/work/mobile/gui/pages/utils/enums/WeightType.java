package com.qaprosoft.carina.work.mobile.gui.pages.utils.enums;

public enum WeightType {
    KILOGRAMS("Kilograms"),
    POUNDS("Pounds"),
    STONE("Stone");

    String name;

    WeightType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
