package com.qaprosoft.carina.work.mobile.gui.pages.android.signUpPages;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.signUpPages.ActivitySignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.signUpPages.WeeklyGoalSignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.signUpPages.YouSignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.Country;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.HeightType;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.WeightType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = YouSignUpPageBase.class)
public class YouSignUpPage extends YouSignUpPageBase {

    @FindBy(xpath = "//*[contains(@resource-id, 'toolbar')]/android.widget.ImageButton")
    private ExtendedWebElement backButton;

    @FindBy(xpath = "//*[contains(@text, '%s') and @class = 'android.widget.RadioButton']")
    private ExtendedWebElement genderRadioButton;

    @FindBy(id = "com.myfitnesspal.android:id/birthDate")
    private ExtendedWebElement ageField;

    @FindBy(id = "com.myfitnesspal.android:id/textCountry")
    private ExtendedWebElement countryText;

    @FindBy(id = "com.myfitnesspal.android:id/recyclerView")
    private ExtendedWebElement countryList;

    @FindBy(xpath = "//*[contains(@text, '%s') and @class = 'android.widget.TextView']")
    private ExtendedWebElement textView;

    @FindBy(id = "com.myfitnesspal.android:id/zipcode")
    private ExtendedWebElement zipCodeField;

    @FindBy(id = "com.myfitnesspal.android:id/gender_group")
    private ExtendedWebElement genderGroupElement;

    @FindBy(id = "com.myfitnesspal.android:id/buttonNext")
    private ExtendedWebElement firstPageNextButton;

    @FindBy(id = "com.myfitnesspal.android:id/nextButton")
    private ExtendedWebElement secondPageNextButton;

    @FindBy(id = "com.myfitnesspal.android:id/height")
    private ExtendedWebElement heightMenu;

    @FindBy(id = "com.myfitnesspal.android:id/current_weight")
    private ExtendedWebElement currentWeightMenu;

    @FindBy(id = "com.myfitnesspal.android:id/entry_one")
    private ExtendedWebElement entryOne;

    @FindBy(id = "com.myfitnesspal.android:id/entry_two")
    private ExtendedWebElement entryTwo;

    @FindBy(id = "com.myfitnesspal.android:id/units")
    private ExtendedWebElement units;

    @FindBy(xpath = "//*[@resource-id = 'android:id/button1' and @text = 'Set']")
    private ExtendedWebElement setButton;

    //Height and weight option ex. Feet & Inches, Kilograms, ...
    @FindBy(xpath = "//*[contains(@text, '%s')]")
    private ExtendedWebElement itemByText;

    public YouSignUpPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return genderGroupElement.isElementPresent(ONE_SECOND) || countryText.isElementPresent(ONE_SECOND);
    }

    @Override
    public ActivitySignUpPageBase clickBackButton() {
        backButton.click(ONE_SECOND);
        return initPage(getDriver(), ActivitySignUpPageBase.class);
    }

    @Override
    public boolean isGenderSelected(String gender) {
        return genderRadioButton.format(gender).isChecked();
    }

    @Override
    public void selectGender(String gender) {
        genderRadioButton.format(gender).click(ONE_SECOND);
    }

    @Override
    public void typeAge(int age) {
        ageField.type(String.valueOf(age));
    }

    @Override
    public int getAge() {
        return Integer.parseInt(ageField.getText());
    }

    @Override
    public String getCountryText() {
        return countryText.getText();
    }

    @Override
    public void changeCountry(Country country) {
        if (countryList.isElementNotPresent(ONE_SECOND)) {
            countryText.click(ONE_SECOND);
        }
        ExtendedWebElement element = textView.format(country.getName());
        if (element.isPresent(THREE_SECONDS)) {
            swipe(element, countryList, Direction.VERTICAL, TEN_SWIPES, NORMAL_SWIPES);
        } else {
            Assert.fail(String.format("[You Sign Up Page] No such country name (" + "%s)!", country.getName()));
        }
        element.click(ONE_SECOND);
    }

    @Override
    public int getZipCode() {
        return Integer.parseInt(zipCodeField.getText());
    }

    @Override
    public void clickToNextPage() {
        firstPageNextButton.click(ONE_SECOND);
    }

    @Override
    public WeeklyGoalSignUpPageBase clickToWeeklyGoalPage() {
        secondPageNextButton.click(ONE_SECOND);
        return initPage(getDriver(), WeeklyGoalSignUpPageBase.class);
    }

    @Override
    public void typeZipCode(int zipCode) {
        zipCodeField.type(String.valueOf(zipCode));
    }

    @Override
    public void typeHeight(double height, HeightType type) {
        heightMenu.click(ONE_SECOND);
        units.click(ONE_SECOND);
        itemByText.format(type.getName()).click(ONE_SECOND);
        if (type == HeightType.CENTIMETERS) {
            entryOne.type(String.valueOf((int)height));
        } else if (type == HeightType.FEET_INCHES) {
            String[] str = String.valueOf(height).split("\\.");
            entryOne.type(str[0]);
            entryTwo.type(str[1]);
        }
        setButton.click(THREE_SECONDS);
    }

    @Override
    public String getHeight() {
        return heightMenu.getText();
    }

    @Override
    public void typeWeight(double weight, WeightType type) {
        currentWeightMenu.click(ONE_SECOND);
        units.click(ONE_SECOND);
        itemByText.format(type.getName()).click(ONE_SECOND);
        if (type == WeightType.KILOGRAMS || type == WeightType.POUNDS) {
            entryOne.type(String.valueOf(weight));
        } else if (type == WeightType.STONE) {
            String[] str = String.valueOf(weight).split("\\.");
            entryOne.type(str[0]);
            entryTwo.type(str[1]);
        }
        setButton.click(THREE_SECONDS);
    }

    @Override
    public String getWeight() {
        return currentWeightMenu.getText();
    }
}
