package com.qaprosoft.carina.work.mobile.gui.pages.utils.enums;

public enum WeeklyGoal {
    ONE_QUARTER_KG_PW("Lose 0.25 kg per week", "Lose 0.5 lbs per week"),
    TWO_QUARTER_KG_PW("Lose 0.5 kg per week", "Lose 1 lbs per week"),
    THREE_QUARTER_KG_PW("Lose 0.75 kg per week", "Lose 1.5 lbs per week"),
    ONE_KG_PW("Lose 1 kg per week", "Lose 2 lbs per week");

    String kg;
    String lbs;

    WeeklyGoal(String kg, String lbs) {
        this.kg = kg;
        this.lbs = lbs;
    }

    public String getKg() {
        return kg;
    }

    public String getLbs() {
        return lbs;
    }
}
