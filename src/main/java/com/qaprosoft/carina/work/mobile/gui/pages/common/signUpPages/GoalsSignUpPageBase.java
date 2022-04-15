package com.qaprosoft.carina.work.mobile.gui.pages.common.signUpPages;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.common.signUpPages.ActivitySignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.Goals;
import org.openqa.selenium.WebDriver;

public abstract class GoalsSignUpPageBase extends AbstractPage implements IConstants {

    public GoalsSignUpPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isGoalChecked(Goals goal);

    public abstract void checkGoal(Goals goal, boolean check);

    public abstract ActivitySignUpPageBase clickNext();
}
