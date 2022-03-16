package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.ActivitySignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.YouSignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.ActivityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ActivitySignUpPageBase.class)
public class ActivitySignUpPage extends ActivitySignUpPageBase {

    @FindBy(xpath = "//*[@text = '%s']")
    private ExtendedWebElement activityLevel;

    public ActivitySignUpPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public YouSignUpPageBase selectActivityLevel(ActivityLevel level) {
        activityLevel.format(level.getName()).click(ONE_SECOND);
        return initPage(getDriver(), YouSignUpPageBase.class);
    }
}
