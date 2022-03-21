package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.LoginPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.SignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.WelcomePageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = WelcomePageBase.class)
public class WelcomePage extends WelcomePageBase {

    @FindBy(id = "com.myfitnesspal.android:id/buttonSignUp")
    private ExtendedWebElement signUpButton;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/buttonLogIn' and @text = 'LOG IN']")
    private ExtendedWebElement logInButton;

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SignUpPageBase clickSignUp() {
        waitUntil(ExpectedConditions.visibilityOf(signUpButton.getElement()), TWENTY_TIMEOUT);
        signUpButton.click(ONE_SECOND);
        return initPage(getDriver(), SignUpPageBase.class);
    }

    @Override
    public LoginPageBase clickLogin() {
        waitUntil(ExpectedConditions.visibilityOf(logInButton.getElement()), TWENTY_TIMEOUT);
        logInButton.click(ONE_SECOND);
        return initPage(getDriver(), LoginPageBase.class);
    }
}
