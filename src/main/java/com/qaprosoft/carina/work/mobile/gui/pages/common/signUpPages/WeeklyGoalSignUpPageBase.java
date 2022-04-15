package com.qaprosoft.carina.work.mobile.gui.pages.common.signUpPages;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.common.signUpPages.CreateAccountPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.WeeklyGoal;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.WeightType;
import org.openqa.selenium.WebDriver;

public abstract class WeeklyGoalSignUpPageBase extends AbstractPage implements IConstants {

    public WeeklyGoalSignUpPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void typeWeight(double weight, WeightType type);

    public abstract String getWeight();

    public abstract void checkWeeklyGoal(WeeklyGoal goal);

    public abstract boolean isWeeklyGoalChecked(WeeklyGoal goal);

    public abstract CreateAccountPageBase clickNextButton();
}
