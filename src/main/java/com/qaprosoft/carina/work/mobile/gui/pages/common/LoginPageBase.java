package com.qaprosoft.carina.work.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import org.openqa.selenium.WebDriver;

public abstract class LoginPageBase extends AbstractPage implements IConstants {

    public LoginPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void typeEmail(String email);

    public abstract String getEmail();

    public abstract void typePassword(String password);

    public abstract String getPassword();

    public abstract void clickLoginButton();

    public abstract boolean isProgressPleaseWaitPresent();
}
