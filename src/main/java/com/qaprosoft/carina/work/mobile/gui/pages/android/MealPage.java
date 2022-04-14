package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.AddMealPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.MealPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = MealPageBase.class)
public class MealPage extends MealPageBase {

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/searchEditTextOld']")
    private ExtendedWebElement searchField;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/searchForTextView']")
    private ExtendedWebElement searchForTextView;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/text_primary']")
    private List<ExtendedWebElement> foundMealList;

    public MealPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void searchForMeal(String meal) {
        searchField.type(meal);
        searchForTextView.click(ONE_SECOND);
    }

    @Override
    public AddMealPageBase clickMealByIndex(int index) {
        foundMealList.get(index).click(ONE_SECOND);
        return initPage(getDriver(), AddMealPageBase.class);
    }
}
