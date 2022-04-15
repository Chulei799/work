package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.common.DiaryPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.MealPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.components.BottomNavigationBar;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.DiaryMealType;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.DiaryOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;


@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = DiaryPageBase.class)
public class DiaryPage extends DiaryPageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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
    private ExtendedWebElement addFoodButton;

    @FindBy(xpath = "//*[@text = '%s']/parent::*/parent::*/following-sibling::android.widget.LinearLayout[1]/descendant::*[contains(@resource-id, 'txtItemDescription')]")
    private ExtendedWebElement mealNameInMealType;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/txtSectionHeader']")
    private List<ExtendedWebElement> listOfMealTypes;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/diary_recycler_view']")
    private ExtendedWebElement scrollView;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    private ExtendedWebElement moreOptions;
    
    @FindBy(xpath = "//*[@text = '%s']")
    private ExtendedWebElement itemByText;

    @FindBy(xpath = "(//*[@resource-id = 'com.myfitnesspal.android:id/entry_timestamp'])[1]")
    private ExtendedWebElement firstFoundTimestamp;

    @FindBy(id = "com.myfitnesspal.android:id/setTime")
    private ExtendedWebElement setTimeButton;

    @FindBy(xpath = "//android.widget.Button[@content-desc='Switch to text input mode for the time input.']")
    private ExtendedWebElement switchTextInputMode;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/material_hour_text_input']")
    private ExtendedWebElement hoursInput;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/material_minute_text_input']")
    private ExtendedWebElement minutesInput;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/material_timepicker_edit_text']")
    private ExtendedWebElement timeInput;

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
        swipe(addFoodButton.format(mealType.getName()), scrollView, Direction.DOWN, FIVE_SWIPES, SLOW_SWIPES);
        addFoodButton.format(mealType.getName()).click(ONE_SECOND);
        return initPage(getDriver(), MealPageBase.class);
    }

    @Override
    public boolean isMealInMealType(String meal, DiaryMealType mealType) {
        int mealTypeIndex = -1;
        boolean isMealIn = false;
        //Swipe to meal type and then to add button of this meal type
        swipe(itemByEatTime.format(mealType.getName()), scrollView, Direction.VERTICAL_DOWN_FIRST, FIVE_SWIPES, SLOW_SWIPES);
        swipe(addFoodButton.format(mealType.getName()), scrollView, Direction.UP, FIVE_SWIPES, SLOW_SWIPES);

        //Here we're looking for index of meal type in list, with index soon we will find block with food list of our meal type
        for(int i = 0; i < listOfMealTypes.size(); i++) {
            if (listOfMealTypes.get(i).getText().equals(mealType.getName())) {
                mealTypeIndex = i;
                break;
            }
        }
        //If we found block of food of searched meal type
        if (mealTypeIndex >= 0) {
            //Here we get list of food of this meal type by index we founded before
            List<ExtendedWebElement> listOfMealsInMealType = findExtendedWebElements(By.xpath(
                    String.format("//*[contains(@resource-id, 'sectionHeaderRelative')]/following-sibling::android.widget.LinearLayout" +
                            "[count(preceding-sibling::android.widget.RelativeLayout)=%s]/descendant::*" +
                            "[contains(@resource-id, 'txtItemDescription')]", mealTypeIndex + 1)));
            for (ExtendedWebElement extendedWebElement : listOfMealsInMealType) {
                if (extendedWebElement.getText().equals(meal)) {
                    isMealIn = true;
                    break;
                }
            }
        }
        return isMealIn;
    }

    @Override
    public AbstractPage openOptionPage(DiaryOptions option) {
        moreOptions.click(ONE_SECOND);
        itemByText.format(option.getName()).click(ONE_SECOND);
        return initPage(getDriver(), option.getPageClass());
    }

    @Override
    public boolean isTimestampActive() {
        return firstFoundTimestamp.isElementPresent(ONE_SECOND);
    }

    @Override
    public String getTimestampOfFoodInMealType(String meal, DiaryMealType mealType) {
        int mealTypeIndex = -1;
        boolean isMealIn = false;
        swipe(itemByEatTime.format(mealType.getName()), scrollView, Direction.VERTICAL_DOWN_FIRST, FIVE_SWIPES, SLOW_SWIPES);
        swipe(addFoodButton.format(mealType.getName()), scrollView, Direction.UP, FIVE_SWIPES, SLOW_SWIPES);
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
            List<ExtendedWebElement> listOfTimestampOfMeals = findExtendedWebElements(By.xpath(
                    String.format("//*[contains(@resource-id, 'sectionHeaderRelative')]/" +
                            "following-sibling::android.widget.LinearLayout[count(preceding-sibling::android.widget.RelativeLayout)=%s]/" +
                            "descendant::*[contains(@resource-id, 'entry_timestamp')]", mealTypeIndex + 1)));
            for(int i = 0; i < listOfMealsInMealType.size(); i++) {
                if (listOfMealsInMealType.get(i).getText().equals(meal)) {
                    return listOfTimestampOfMeals.get(i).getText();
                }
            }
        }
        return null;
    }

    private void setTime(String time) {
        int index1 = time.indexOf(':');
        int index2 = time.indexOf(' ');
        //Divide time on hours, minutes and timeFormat(AM/PM)
        String hours = time.substring(0, index1);
        String minutes = time.substring(index1 + 1, index2);
        String timeFormat = time.substring(index2 + 1);
        hoursInput.click(ONE_SECOND);
        timeInput.type(hours);
        minutesInput.click(ONE_SECOND);
        timeInput.type(minutes);
        itemByText.format(timeFormat).check();
        itemByText.format("OK").click(ONE_SECOND);
    }

    @Override
    public void setTimestampOfFoodInMealType(String meal, DiaryMealType mealType, String time) {
        int mealTypeIndex = -1;
        //Swipe to meal type and then to add button of this meal type
        swipe(itemByEatTime.format(mealType.getName()), scrollView, Direction.VERTICAL_DOWN_FIRST, FIVE_SWIPES, SLOW_SWIPES);
        swipe(addFoodButton.format(mealType.getName()), scrollView, Direction.UP, FIVE_SWIPES, SLOW_SWIPES);

        //Here we're looking for index of meal type in list, with index soon we will find block with food list of our meal type
        for(int i = 0; i < listOfMealTypes.size(); i++) {
            if (listOfMealTypes.get(i).getText().equals(mealType.getName())) {
                mealTypeIndex = i;
                break;
            }
        }
        //If we found block of food of searched meal type
        if (mealTypeIndex >= 0) {
            //Here we get list of food of this meal type by index we founded before
            List<ExtendedWebElement> listOfMealsInMealType = findExtendedWebElements(By.xpath(
                    String.format("//*[contains(@resource-id, 'sectionHeaderRelative')]/following-sibling::android.widget.LinearLayout" +
                            "[count(preceding-sibling::android.widget.RelativeLayout)=%s]/descendant::*" +
                            "[contains(@resource-id, 'txtItemDescription')]", mealTypeIndex + 1)));
            //And here we get list of foods timestamps for previous list of food
            List<ExtendedWebElement> listOfTimestampOfMeals = findExtendedWebElements(By.xpath(
                    String.format("//*[contains(@resource-id, 'sectionHeaderRelative')]/" +
                            "following-sibling::android.widget.LinearLayout[count(preceding-sibling::android.widget.RelativeLayout)=%s]/" +
                            "descendant::*[contains(@resource-id, 'entry_timestamp')]", mealTypeIndex + 1)));
            //Click on timestamp which meal name equals to searched meal name
            for(int i = 0; i < listOfMealsInMealType.size(); i++) {
                if (listOfMealsInMealType.get(i).getText().equals(meal)) {
                    listOfTimestampOfMeals.get(i).click(ONE_SECOND);
                }
            }
        }
        setTimeButton.click(ONE_SECOND);
        switchTextInputMode.click(ONE_SECOND);
        setTime(time);
    }
}
