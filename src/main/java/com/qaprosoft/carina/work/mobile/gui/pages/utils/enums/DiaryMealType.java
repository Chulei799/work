package com.qaprosoft.carina.work.mobile.gui.pages.utils.enums;

public enum DiaryMealType {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACKS("Snacks");

    private String name;

    DiaryMealType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
