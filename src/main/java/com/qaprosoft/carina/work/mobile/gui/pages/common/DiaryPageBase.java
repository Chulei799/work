package com.qaprosoft.carina.work.mobile.gui.pages.common;

import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.components.BottomNavigationBar;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.DiaryMealType;
import org.openqa.selenium.WebDriver;

public abstract class DiaryPageBase extends AbstractPage implements IConstants, IMobileUtils {

    public DiaryPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isPageOpened();

    public abstract BottomNavigationBar getBottomNavigationBar();

    public abstract void clearDiary();

    public abstract MealPageBase clickAddToMealType(DiaryMealType mealType);

    public abstract boolean isMealInMealType(String meal, DiaryMealType mealType);
}
