package com.qaprosoft.carina.work.mobile.gui.pages.android.signUpPages;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.signUpPages.ActivitySignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.signUpPages.GoalsSignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.Goals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = GoalsSignUpPageBase.class)
public class GoalsSignUpPage extends GoalsSignUpPageBase {

    @FindBy(id = "com.myfitnesspal.android:id/nextButton")
    private ExtendedWebElement nextButton;

    @FindBy(xpath = "//*[contains(@text, '%s') and @class = 'android.widget.CheckedTextView']")
    private ExtendedWebElement checkedTextView;

    public GoalsSignUpPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isGoalChecked(Goals goal) {
        return checkedTextView.format(goal.getName()).isChecked();
    }

    @Override
    public void checkGoal(Goals goal, boolean check) {
        if (check) {
            checkedTextView.format(goal.getName()).check();
        } else {
            checkedTextView.format(goal.getName()).uncheck();
        }
    }

    @Override
    public ActivitySignUpPageBase clickNext() {
        if (checkedTextView.format(Goals.GAIN_MUSCLE.getName()).isElementPresent(THREE_SECONDS)) {
            nextButton.click(ONE_SECOND);
            nextButton.click(ONE_SECOND);
            checkedTextView.format(Goals.LACK_OF_TIME.getName()).click(ONE_SECOND);
            nextButton.click(ONE_SECOND);
            nextButton.click(ONE_SECOND);
        }
        return initPage(getDriver(), ActivitySignUpPageBase.class);
    }
}
