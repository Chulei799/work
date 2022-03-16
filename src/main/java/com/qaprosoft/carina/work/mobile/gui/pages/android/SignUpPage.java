package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.GoalsSignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.SignUpPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = SignUpPageBase.class)
public class SignUpPage extends SignUpPageBase {

    @FindBy(id = "com.myfitnesspal.android:id/login_button")
    private ExtendedWebElement continueButton;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isContinueButtonPresent() {
        return waitUntil(ExpectedConditions.visibilityOf(continueButton.getElement()), THREE_TIMEOUT);
    }

    @Override
    public GoalsSignUpPageBase clickContinue() {
        continueButton.click(ONE_SECOND);
        return initPage(getDriver(), GoalsSignUpPageBase.class);
    }
}
