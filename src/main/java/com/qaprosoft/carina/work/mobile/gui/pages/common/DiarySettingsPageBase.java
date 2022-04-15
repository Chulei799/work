package com.qaprosoft.carina.work.mobile.gui.pages.common;

import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import org.openqa.selenium.WebDriver;

public abstract class DiarySettingsPageBase extends AbstractPage implements IConstants, IMobileUtils {

    public DiarySettingsPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract DiaryPageBase clickBackButton();

    public abstract boolean isShowFoodTimestampPresent();

    public abstract boolean isShowFoodTimestampChecked();

    public abstract boolean checkShowFoodTimestamp(boolean check);
}
