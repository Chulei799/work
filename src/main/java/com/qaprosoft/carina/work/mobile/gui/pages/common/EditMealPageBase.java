package com.qaprosoft.carina.work.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import org.openqa.selenium.WebDriver;

public abstract class EditMealPageBase extends AbstractPage implements IConstants {

    public EditMealPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isTimestampPresent();

    public abstract void setTime(String time);

    public abstract String getTime();

    public abstract DiaryPageBase submitChanges();

    public abstract DiaryPageBase clickBackButton();
}
