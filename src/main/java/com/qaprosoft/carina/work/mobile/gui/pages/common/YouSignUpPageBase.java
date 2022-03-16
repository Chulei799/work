package com.qaprosoft.carina.work.mobile.gui.pages.common;

import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.Country;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.HeightType;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.WeightType;
import org.openqa.selenium.WebDriver;

public abstract class YouSignUpPageBase extends AbstractPage implements IMobileUtils, IConstants {

    public YouSignUpPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract ActivitySignUpPageBase clickBackButton();

    public abstract boolean isGenderSelected(String gender);

    public abstract void selectGender(String gender);

    public abstract void typeAge(int age);

    public abstract int getAge();

    public abstract String getCountryText();

    public abstract void changeCountry(Country country);

    public abstract int getZipCode();

    public abstract void clickToNextPage();

    public abstract WeeklyGoalSignUpPageBase clickToWeeklyGoalPage();

    public abstract void typeZipCode(int zipCode);

    public abstract void typeHeight(int centimeters);

    public abstract void typeHeight(int feet, int inches);

    public abstract String getHeight();

    public abstract void typeWeight(int kilogramsOrPounds, WeightType type);

    public abstract void typeWeight(int stones, int pounds);

    public abstract String getWeight();
}
