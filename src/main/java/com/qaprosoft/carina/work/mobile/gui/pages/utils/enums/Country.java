package com.qaprosoft.carina.work.mobile.gui.pages.utils.enums;

public enum Country {
    UNITED_STATES("United States"),
    UNITED_KINGDOM("United Kingdom"),
    UKRAINE("Ukraine");

    String name;

    Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
