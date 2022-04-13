package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.DiaryPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.components.BottomNavigationBar;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.DiaryMealType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;


@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = DiaryPageBase.class)
public class DiaryPage extends DiaryPageBase {

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/txtSectionHeader' and @text = 'Breakfast']")
    private ExtendedWebElement itemByEatTime;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/bottomNavigationBar']")
    private BottomNavigationBar bottomNavigationBar;

    @FindBy(xpath = "//*[@content-desc='Edit Diary']")
    private ExtendedWebElement editDiary;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/select_all']")
    private ExtendedWebElement selectAll;

    @FindBy(xpath = "//*[@content-desc='View Nutrition']")
    private ExtendedWebElement viewNutrition;

    @FindBy(xpath = "//*[@class = 'android.widget.Button' and @text = 'Delete']")
    private ExtendedWebElement deleteButton;

    @FindBy(xpath = "//*[@text = '%s']/parent::*/parent::*/following-sibling::android.view.ViewGroup[1]/descendant::*[contains(@text, 'ADD FOOD')]")
    private ExtendedWebElement itemByText;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/searchEditTextOld']")
    private ExtendedWebElement searchField;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/searchForTextView']")
    private ExtendedWebElement searchForTextView;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/text_primary']")
    private List<ExtendedWebElement> foundMealList;

    @FindBy(xpath = "//*[@content-desc='Add']")
    private ExtendedWebElement addButton;

    @FindBy(xpath = "//*[@text = '%s']/parent::*/parent::*/following-sibling::android.widget.LinearLayout[1]/descendant::*[contains(@resource-id, 'txtItemDescription')]")
    private ExtendedWebElement mealNameInMealType;

    @FindBy(xpath = "//android.widget.Button[@content-desc='NO THANKS']")
    private ExtendedWebElement noThanksButton;

    public DiaryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return itemByEatTime.isElementPresent(THREE_SECONDS);
    }

    @Override
    public BottomNavigationBar getBottomNavigationBar() {
        return bottomNavigationBar;
    }

    @Override
    public boolean isEditDiaryVisible() {
        return editDiary.isVisible(ONE_SECOND);
    }

    @Override
    public void clearDiary() {
        if(editDiary.isVisible(ONE_SECOND)) {
            editDiary.click(ONE_SECOND);
            selectAll.check();
            viewNutrition.click(ONE_SECOND);
            if(deleteButton.isVisible(ONE_SECOND)) {
                deleteButton.click(ONE_SECOND);
            }
        }
    }

    @Override
    public void clickAddToMealTime(DiaryMealType mealType) {
        itemByText.format(mealType.getName()).click(ONE_SECOND);
    }

    @Override
    public void searchForMeal(String meal) {
        searchField.type(meal);
        searchForTextView.click(ONE_SECOND);
    }

    @Override
    public void selectMealByIndex(int index) {
        foundMealList.get(index).click(ONE_SECOND);
    }

    @Override
    public void clickAddButton() {
        if(noThanksButton.isElementPresent(ONE_SECOND)) {
            noThanksButton.click(ONE_SECOND);
        }
        addButton.click(ONE_SECOND);
    }

    @Override
    public String getFirstMealNameInMealType(DiaryMealType mealType) {
        return mealNameInMealType.format(mealType.getName()).getText();
    }
}
