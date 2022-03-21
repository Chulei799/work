package com.qaprosoft.carina.work.android;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.commons.SpecialKeywords;
import com.qaprosoft.carina.core.foundation.crypto.CryptoTool;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.work.mobile.gui.pages.common.LoginPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.WelcomePageBase;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.regex.Pattern;

public class AndroidLogInTest implements IAbstractTest {
    private static final CryptoTool cryptoTool = new CryptoTool("./.settings/crypto.key");
    private static final Pattern CRYPTO_PATTERN = Pattern.compile(SpecialKeywords.CRYPT);
    private static final String USER_EMAIL = cryptoTool.decryptByPattern(R.EMAIL.get("user1.email"), CRYPTO_PATTERN);
    private static final String USER_PASSWORD = cryptoTool.decryptByPattern(R.EMAIL.get("user1.password"), CRYPTO_PATTERN);

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00013")
    public void logInTest() {
        SoftAssert softAssert = new SoftAssert();

        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickLogin();
        loginPage.typeEmail(USER_EMAIL);
        softAssert.assertEquals(loginPage.getEmail(), USER_EMAIL, "[Login Page] Email isn't entered correct!");
        loginPage.typePassword(USER_PASSWORD);
        loginPage.clickLoginButton();
        softAssert.assertTrue(loginPage.isProgressPleaseWaitPresent(),
                "[Login Page] Progress Please Wait isn't present!");

        softAssert.assertAll();
    }
}
