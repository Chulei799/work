package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.CreateAccountPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.WeeklyGoalSignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.WeeklyGoal;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.WeightType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = WeeklyGoalSignUpPageBase.class)
public class WeeklyGoalSignUpPage extends WeeklyGoalSignUpPageBase {

    @FindBy(id = "com.myfitnesspal.android:id/inputGoalWeight")
    private ExtendedWebElement goalWeightMenu;

    @FindBy(id = "com.myfitnesspal.android:id/entry_one")
    private ExtendedWebElement entryOne;

    @FindBy(id = "com.myfitnesspal.android:id/entry_two")
    private ExtendedWebElement entryTwo;

    @FindBy(id = "com.myfitnesspal.android:id/units")
    private ExtendedWebElement units;

    @FindBy(xpath = "//*[@resource-id = 'android:id/button1' and @text = 'Set']")
    private ExtendedWebElement setButton;

    @FindBy(xpath = "//*[@class = 'android.widget.RadioButton' and contains(@text, '%s')]")
    private ExtendedWebElement weeklyGoalRadioButton;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/units']//*[@text = '%s']")
    private ExtendedWebElement unit;

    @FindBy(xpath = "//*[@class = 'android.widget.Button' and @text = 'NEXT']")
    private ExtendedWebElement nextButton;

    public WeeklyGoalSignUpPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void typeWeight(int kilogramsOrPounds, WeightType type) {
        goalWeightMenu.click(ONE_SECOND);
        units.click(ONE_SECOND);
        unit.format(type.getName()).click(ONE_SECOND);
        entryOne.type(String.valueOf(kilogramsOrPounds));
        setButton.click(ONE_SECOND);
    }

    @Override
    public void typeWeight(int stones, int pounds) {
        goalWeightMenu.click(ONE_SECOND);
        units.click(ONE_SECOND);
        unit.format(WeightType.STONE.getName()).click(ONE_SECOND);
        entryOne.type(String.valueOf(stones));
        entryTwo.type(String.valueOf(pounds));
        setButton.click(ONE_SECOND);
    }

    @Override
    public String getWeight() {
        return goalWeightMenu.getText();
    }

    @Override
    public void checkWeeklyGoal(WeeklyGoal goal) {
        if (weeklyGoalRadioButton.format(goal.getKg()).isElementPresent(ONE_SECOND)) {
            weeklyGoalRadioButton.format(goal.getKg()).click(ONE_SECOND);
        } else if (weeklyGoalRadioButton.format(goal.getLbs()).isElementPresent(ONE_SECOND)) {
            weeklyGoalRadioButton.format(goal.getLbs()).click(ONE_SECOND);
        } else {
            Assert.fail(String.format("Can't find goal with text (" + "%s)", goal));
        }
    }

    @Override
    public boolean isWeeklyGoalChecked(WeeklyGoal goal) {
        if (weeklyGoalRadioButton.format(goal.getKg()).isElementPresent(ONE_SECOND)) {
            return weeklyGoalRadioButton.format(goal.getKg()).isChecked();
        } else if (weeklyGoalRadioButton.format(goal.getLbs()).isElementPresent(ONE_SECOND)) {
            return weeklyGoalRadioButton.format(goal.getLbs()).isChecked();
        } else {
            Assert.fail(String.format("Can't find goal with text (" + "%s)", goal));
            return false;
        }
    }

    @Override
    public CreateAccountPageBase clickNextButton() {
        nextButton.click(ONE_SECOND);
        return initPage(getDriver(), CreateAccountPageBase.class);
    }
}
