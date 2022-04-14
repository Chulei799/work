package com.qaprosoft.carina.work.mobile.gui.pages.services;

import com.qaprosoft.carina.core.foundation.commons.SpecialKeywords;
import com.qaprosoft.carina.core.foundation.crypto.CryptoTool;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.common.HomePageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.LoginPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.WelcomePageBase;
import org.openqa.selenium.WebDriver;

import java.util.regex.Pattern;

public class AuthorizationService extends AbstractPage {
    private static final CryptoTool cryptoTool = new CryptoTool("./.settings/crypto.key");
    private static final Pattern CRYPTO_PATTERN = Pattern.compile(SpecialKeywords.CRYPT);
    private static final String USER_EMAIL = cryptoTool.decryptByPattern(R.EMAIL.get("user1.email"), CRYPTO_PATTERN);
    private static final String USER_PASSWORD = cryptoTool.decryptByPattern(R.EMAIL.get("user1.password"), CRYPTO_PATTERN);

    public AuthorizationService(WebDriver driver) {
        super(driver);
    }

    public HomePageBase logIn() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickLogin();
        loginPage.typeEmail(USER_EMAIL);
        loginPage.typePassword(USER_PASSWORD);
        //loginPage.clickLoginButton();
        return loginPage.clickLoginButton();
    }
}
