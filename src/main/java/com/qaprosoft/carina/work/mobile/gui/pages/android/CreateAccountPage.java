package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.CreateAccountPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CreateAccountPageBase.class)
public class CreateAccountPage extends CreateAccountPageBase {

    @FindBy(id = "com.myfitnesspal.android:id/emailEdit")
    private ExtendedWebElement emailField;

    @FindBy(id = "com.myfitnesspal.android:id/passwordEdit")
    private ExtendedWebElement passwordField;

    @FindBy(xpath = "//*[@class = 'android.widget.Button' and @text = 'NEXT']")
    private ExtendedWebElement nextButton;

    @FindBy(xpath = "//*[@resource-id = 'android:id/message' and @text = 'Email address already in use.')]")
    private ExtendedWebElement emailAlreadyInUseMessage;

    @FindBy(xpath = "//*[@resource-id = 'android:id/message' and contains(@text, 'email address you entered is invalid')]")
    private ExtendedWebElement invalidEmailMessage;

    @FindBy(xpath = "//*[@class = 'android.widget.Button' and @text = 'Cancel']")
    private ExtendedWebElement closeEmailAlreadyInUseMessageButton;

    @FindBy(xpath = "//*[@class = 'android.widget.Button' and @text = 'OK']")
    private ExtendedWebElement closeInvalidEmailMessageButton;

    @FindBy(xpath = "//*[@resource-id = 'android:id/message' and @text = 'Please Wait…'")
    private ExtendedWebElement waitMessage;

    @FindBy(xpath = "//*[@class = 'android.widget.ImageButton' and @content-desc = 'Error']")
    private ExtendedWebElement passwordError;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getEmail() {
        return emailField.getText();
    }

    @Override
    public String getPassword() {
        return passwordField.getText();
    }

    @Override
    public void typeEmail(String email) {
        emailField.type(email);
    }

    @Override
    public void typePassword(String password) {
        passwordField.type(password);
    }

    @Override
    public void clickNextButton() {
        nextButton.click(ONE_SECOND);
    }

    @Override
    public boolean isEmailInUseMessagePresent() {
        return emailAlreadyInUseMessage.isElementPresent(FIVE_SECONDS);
    }

    @Override
    public boolean isInvalidEmailMessagePresent() {
        return invalidEmailMessage.isElementPresent(FIVE_SECONDS);
    }

    @Override
    public void closeEmailInUseMessage() {
        closeEmailAlreadyInUseMessageButton.click(FIVE_SECONDS);
    }

    @Override
    public void closeInvalidEmailMessage() {
        closeInvalidEmailMessageButton.click(FIVE_SECONDS);
    }

    @Override
    public boolean isPasswordErrorPresent() {
        return passwordError.isElementPresent(THREE_SECONDS);
    }
}