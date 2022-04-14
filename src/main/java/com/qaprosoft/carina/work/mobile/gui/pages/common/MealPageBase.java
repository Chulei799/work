package com.qaprosoft.carina.work.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import org.openqa.selenium.WebDriver;

public abstract class MealPageBase extends AbstractPage implements IConstants {

    public MealPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void searchForMeal(String meal);

    public abstract AddMealPageBase clickMealByIndex(int index);
}
