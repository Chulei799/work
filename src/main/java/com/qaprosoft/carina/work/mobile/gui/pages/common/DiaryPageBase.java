package com.qaprosoft.carina.work.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.components.BottomNavigationBar;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.DiaryMealType;
import org.openqa.selenium.WebDriver;

public abstract class DiaryPageBase extends AbstractPage implements IConstants {

    public DiaryPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isPageOpened();

    public abstract BottomNavigationBar getBottomNavigationBar();

    public abstract boolean isEditDiaryVisible();

    public abstract void clearDiary();

    public abstract void clickAddToMealTime(DiaryMealType mealType);

    public abstract void searchForMeal(String meal);

    public abstract void selectMealByIndex(int index);

    public abstract void clickAddButton();

    public abstract String getFirstMealNameInMealType(DiaryMealType mealType);
}
