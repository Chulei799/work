package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.ActivitySignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.GoalsSignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.Goals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = GoalsSignUpPageBase.class)
public class GoalsSignUpPage extends GoalsSignUpPageBase {

    @FindBy(id = "com.myfitnesspal.android:id/nextButton")
    private ExtendedWebElement nextButton;

    @FindBy(xpath = "//*[contains(@text, '%s')]")
    private ExtendedWebElement goalRadioButton;

    public GoalsSignUpPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isGoalChecked(Goals goal) {
        return goalRadioButton.format(goal.getName()).isElementPresent(ONE_SECOND);
    }

    @Override
    public void checkGoal(Goals goal, boolean check) {
        if (check) {
            goalRadioButton.format(goal.getName()).check();
        } else {
            goalRadioButton.format(goal.getName()).uncheck();
        }
    }

    @Override
    public ActivitySignUpPageBase clickNext() {
        if (goalRadioButton.format(Goals.GAIN_MUSCLE.getName()).isElementPresent(THREE_SECONDS)) {
            nextButton.click(ONE_SECOND);
            nextButton.click(ONE_SECOND);
            goalRadioButton.format(Goals.LACK_OF_TIME.getName()).click(ONE_SECOND);
            nextButton.click(ONE_SECOND);
            nextButton.click(ONE_SECOND);
        }
        return initPage(getDriver(), ActivitySignUpPageBase.class);
    }
}
