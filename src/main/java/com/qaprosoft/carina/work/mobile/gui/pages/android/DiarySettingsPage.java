package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.DiaryPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.DiarySettingsPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = DiarySettingsPageBase.class)
public class DiarySettingsPage extends DiarySettingsPageBase {

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/toolbar']/*[contains(@class, 'android.widget.ImageButton')]")
    private ExtendedWebElement backButton;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/settings_food_timestamps']")
    private ExtendedWebElement showFoodTimestampSetting;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/settings_food_timestamps']/*[contains(@class, 'android.widget.CheckBox')]")
    private ExtendedWebElement showFoodTimestampSettingCheckBox;

    public DiarySettingsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public DiaryPageBase clickBackButton() {
        backButton.click(ONE_SECOND);
        return initPage(getDriver(), DiaryPageBase.class);
    }

    @Override
    public boolean isShowFoodTimestampPresent() {
        return showFoodTimestampSetting.isElementPresent(THREE_SECONDS);
    }

    @Override
    public boolean isShowFoodTimestampChecked() {
        return showFoodTimestampSettingCheckBox.isChecked();
    }

    @Override
    public boolean checkShowFoodTimestamp(boolean check) {
        if(check) {
            showFoodTimestampSettingCheckBox.check();
        } else {
            showFoodTimestampSettingCheckBox.uncheck();
        }
        return showFoodTimestampSettingCheckBox.isChecked();
    }
}
