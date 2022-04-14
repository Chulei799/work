package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.DiaryPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.MealPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.components.BottomNavigationBar;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.DiaryMealType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;


@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = DiaryPageBase.class)
public class DiaryPage extends DiaryPageBase {

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/txtSectionHeader' and @text = '%s']")
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

    @FindBy(xpath = "//*[@text = '%s']/parent::*/parent::*/following-sibling::android.widget.LinearLayout[1]/descendant::*[contains(@resource-id, 'txtItemDescription')]")
    private ExtendedWebElement mealNameInMealType;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/txtSectionHeader']")
    private List<ExtendedWebElement> listOfMealTypes;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/diary_recycler_view']")
    private ExtendedWebElement scrollView;

    public DiaryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return itemByEatTime.format("Breakfast").isElementPresent(THREE_SECONDS);
    }

    @Override
    public BottomNavigationBar getBottomNavigationBar() {
        return bottomNavigationBar;
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
    public MealPageBase clickAddToMealType(DiaryMealType mealType) {
        swipe(itemByText.format(mealType.getName()), scrollView, Direction.DOWN, FIVE_SWIPES, SLOW_SWIPES);
        itemByText.format(mealType.getName()).click(ONE_SECOND);
        return initPage(getDriver(), MealPageBase.class);
    }

    @Override
    public boolean isMealInMealType(String meal, DiaryMealType mealType) {
        int mealTypeIndex = -1;
        boolean isMealIn = false;
        swipe(itemByEatTime.format(mealType.getName()), scrollView, Direction.VERTICAL_DOWN_FIRST, FIVE_SWIPES, SLOW_SWIPES);
        swipe(itemByText.format(mealType.getName()), scrollView, Direction.UP, FIVE_SWIPES, SLOW_SWIPES);
        for(int i = 0; i < listOfMealTypes.size(); i++) {
            if (listOfMealTypes.get(i).getText().equals(mealType.getName())) {
                mealTypeIndex = i;
                break;
            }
        }
        if (mealTypeIndex >= 0) {
            List<ExtendedWebElement> listOfMealsInMealType = findExtendedWebElements(By.xpath(
                    String.format("//*[contains(@resource-id, 'sectionHeaderRelative')]/following-sibling::android.widget.LinearLayout" +
                            "[count(preceding-sibling::android.widget.RelativeLayout)=%s]/descendant::*" +
                            "[contains(@resource-id, 'txtItemDescription')]", mealTypeIndex + 1)));
            for(int i = 0; i < listOfMealsInMealType.size(); i++) {
                if (listOfMealsInMealType.get(i).getText().equals(meal)) {
                    isMealIn = true;
                    break;
                }
            }
        }
        return isMealIn;
    }
}
