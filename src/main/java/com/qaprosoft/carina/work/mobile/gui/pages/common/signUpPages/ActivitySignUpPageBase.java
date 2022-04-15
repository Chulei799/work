package com.qaprosoft.carina.work.mobile.gui.pages.common.signUpPages;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.ActivityLevel;
import org.openqa.selenium.WebDriver;

public abstract class ActivitySignUpPageBase extends AbstractPage implements IConstants {

    public ActivitySignUpPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract YouSignUpPageBase selectActivityLevel(ActivityLevel level);
}
