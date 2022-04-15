package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.DiaryPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.EditMealPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = EditMealPageBase.class)
public class EditMealPage extends EditMealPageBase {

    @FindBy(xpath = "//*[@content-desc='Add']")
    private ExtendedWebElement submitButton;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/constraintLayoutTimestampRowContainer']")
    private ExtendedWebElement timeContainer;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/textTimeValue']")
    private ExtendedWebElement timestamp;

    @FindBy(id = "com.myfitnesspal.android:id/setTime")
    private ExtendedWebElement setTimeButton;

    @FindBy(xpath = "//android.widget.Button[@content-desc='Switch to text input mode for the time input.']")
    private ExtendedWebElement switchTextInputMode;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/material_hour_text_input']")
    private ExtendedWebElement hoursInput;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/material_minute_text_input']")
    private ExtendedWebElement minutesInput;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/material_timepicker_edit_text']")
    private ExtendedWebElement timeInput;

    @FindBy(xpath = "//*[@text = '%s']")
    private ExtendedWebElement itemByText;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/toolbar']/*[contains(@class, 'android.widget.ImageButton')]")
    private ExtendedWebElement backButton;

    @FindBy(xpath = "//android.widget.Button[@content-desc='NO THANKS']")
    private ExtendedWebElement noThanksButton;

    @FindBy(xpath = "//android.view.View[@content-desc='Feature Highlight']")
    private ExtendedWebElement featureHighlight;

    public EditMealPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isTimestampPresent() {
        return timestamp.isElementPresent(THREE_SECONDS);
    }

    @Override
    public void setTime(String time) {
        if (featureHighlight.isElementPresent(ONE_SECOND)) {
            featureHighlight.click(ONE_SECOND);
        }
        if(noThanksButton.isElementPresent(ONE_SECOND)) {
            noThanksButton.click(ONE_SECOND);
        }
        timeContainer.click(ONE_SECOND);
        setTimeButton.click(ONE_SECOND);
        switchTextInputMode.click(ONE_SECOND);
        int index1 = time.indexOf(':');
        int index2 = time.indexOf(' ');
        //Divide time on hours, minutes and timeFormat(AM/PM)
        String hours = time.substring(0, index1);
        String minutes = time.substring(index1 + 1, index2);
        String timeFormat = time.substring(index2 + 1);
        hoursInput.click(ONE_SECOND);
        timeInput.type(hours);
        minutesInput.click(ONE_SECOND);
        timeInput.type(minutes);
        itemByText.format(timeFormat).check();
        itemByText.format("OK").click(ONE_SECOND);
    }

    @Override
    public String getTime() {
        return timestamp.getText();
    }

    @Override
    public DiaryPageBase submitChanges() {
        submitButton.click(ONE_SECOND);
        return initPage(getDriver(), DiaryPageBase.class);
    }

    @Override
    public DiaryPageBase clickBackButton() {
        backButton.click(ONE_SECOND);
        return initPage(getDriver(), DiaryPageBase.class);
    }
}
