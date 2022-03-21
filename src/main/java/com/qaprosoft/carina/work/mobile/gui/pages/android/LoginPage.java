package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.LoginPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {

    @FindBy(id = "com.myfitnesspal.android:id/email_address_edit")
    private ExtendedWebElement emailField;

    @FindBy(id = "com.myfitnesspal.android:id/password_edit")
    private ExtendedWebElement passwordField;

    @FindBy(id = "com.myfitnesspal.android:id/login_button")
    private ExtendedWebElement loginButton;

    @FindBy(id = "com.myfitnesspal.android:id/progressPleaseWait")
    private ExtendedWebElement progressPleaseWait;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void typeEmail(String email) {
        emailField.type(email);
    }

    @Override
    public String getEmail() {
        return emailField.getText();
    }

    @Override
    public void typePassword(String password) {
        passwordField.type(password);
    }

    @Override
    public String getPassword() {
        return passwordField.getText();
    }

    @Override
    public void clickLoginButton() {
        loginButton.click(ONE_SECOND);
    }

    @Override
    public boolean isProgressPleaseWaitPresent() {
        return progressPleaseWait.isElementPresent(THREE_SECONDS);
    }
}
