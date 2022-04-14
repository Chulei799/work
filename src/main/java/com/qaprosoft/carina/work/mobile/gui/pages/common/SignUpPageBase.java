package com.qaprosoft.carina.work.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import org.openqa.selenium.WebDriver;

public abstract class SignUpPageBase extends AbstractPage implements IConstants {

    public SignUpPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isContinueButtonPresent();

    public abstract GoalsSignUpPageBase clickContinue();
}
