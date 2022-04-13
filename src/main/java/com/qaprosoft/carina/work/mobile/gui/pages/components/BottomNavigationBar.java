package com.qaprosoft.carina.work.mobile.gui.pages.components;

import com.qaprosoft.carina.core.foundation.utils.factory.ICustomTypePageFactory;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.BottomBarElements;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class BottomNavigationBar extends AbstractUIObject implements ICustomTypePageFactory, IConstants {

    @FindBy(xpath = "//*[@content-desc='%s']")
    private ExtendedWebElement itemByText;

    public BottomNavigationBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public AbstractPage clickOn(BottomBarElements element) {
        itemByText.format(element.getName()).click(THREE_SECONDS);
        return initPage(getDriver(), element.getPageClass());
    }

}
