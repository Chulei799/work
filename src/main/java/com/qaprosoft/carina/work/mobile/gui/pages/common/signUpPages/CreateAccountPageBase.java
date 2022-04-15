package com.qaprosoft.carina.work.mobile.gui.pages.common.signUpPages;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import org.openqa.selenium.WebDriver;

public abstract class CreateAccountPageBase extends AbstractPage implements IConstants {

    public CreateAccountPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract String getEmail();

    public abstract String getPassword();

    public abstract void typeEmail(String email);

    public abstract void typePassword(String password);

    public abstract void clickNextButton();

    public abstract boolean isEmailInUseMessagePresent();

    public abstract boolean isInvalidEmailMessagePresent();

    public abstract void closeEmailInUseMessage();

    public abstract void closeInvalidEmailMessage();

    public abstract boolean isPasswordErrorPresent();
}
