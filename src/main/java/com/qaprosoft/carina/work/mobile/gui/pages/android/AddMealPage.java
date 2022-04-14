package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.AddMealPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.DiaryPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = AddMealPageBase.class)
public class AddMealPage extends AddMealPageBase {

    @FindBy(xpath = "//*[@content-desc='Add']")
    private ExtendedWebElement submitButton;

    @FindBy(xpath = "//android.widget.Button[@content-desc='NO THANKS']")
    private ExtendedWebElement noThanksButton;

    @FindBy(xpath = "//android.view.View[@content-desc='Feature Highlight']")
    private ExtendedWebElement featureHighlight;

    public AddMealPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public DiaryPageBase submitMeal() {
        if (featureHighlight.isElementPresent(ONE_SECOND)) {
            featureHighlight.click(ONE_SECOND);
        }
        if(noThanksButton.isElementPresent(ONE_SECOND)) {
            noThanksButton.click(ONE_SECOND);
        }
        submitButton.click(ONE_SECOND);
        return initPage(getDriver(), DiaryPageBase.class);
    }
}
