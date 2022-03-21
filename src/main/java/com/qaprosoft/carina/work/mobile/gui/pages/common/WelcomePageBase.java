package com.qaprosoft.carina.work.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import org.openqa.selenium.WebDriver;

public abstract class WelcomePageBase extends AbstractPage implements IConstants {

    public WelcomePageBase(WebDriver driver) {
        super(driver);
    }

    public abstract SignUpPageBase clickSignUp();

    public abstract LoginPageBase clickLogin();
}
