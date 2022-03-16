package com.qaprosoft.carina.work.mobile.gui.pages.utils.enums;

public enum Goals {
    LOSE_WEIGHT("Lose Weight"),
    MAINTAIN_WEIGHT("Maintain Weight"),
    GAIN_WEIGHT("Gain Weight"),
    GAIN_MUSCLE("Gain Muscle"),
    MODIFY_MY_DIET("Modify My Diet"),
    MANAGE_STRESS("Manage Stress"),
    INCREASE_MY_STEP_COUNT("Increase My Step Count"),

    LACK_OF_TIME("Lack of time");

    String name;

    Goals(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
