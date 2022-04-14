package com.qaprosoft.carina.work.android;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.commons.SpecialKeywords;
import com.qaprosoft.carina.core.foundation.crypto.CryptoTool;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.work.mobile.gui.pages.common.HomePageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.LoginPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.WelcomePageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.regex.Pattern;

public class AndroidLogInTest implements IAbstractTest, IConstants {
    private static final CryptoTool cryptoTool = new CryptoTool("./.settings/crypto.key");
    private static final Pattern CRYPTO_PATTERN = Pattern.compile(SpecialKeywords.CRYPT);
    private static final String USER_EMAIL = cryptoTool.decryptByPattern(R.EMAIL.get("user1.email"), CRYPTO_PATTERN);
    private static final String USER_PASSWORD = cryptoTool.decryptByPattern(R.EMAIL.get("user1.password"), CRYPTO_PATTERN);
    private static final String POST_TEXT = "Share post";
    private static final String COMMENT_TEXT = "Share comment";

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

    public HomePageBase login() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickLogin();
        loginPage.typeEmail(USER_EMAIL);
        loginPage.typePassword(USER_PASSWORD);
        return loginPage.clickLoginButton();
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00014")
    public void likeAndDislikeAPostTest() {
        SoftAssert softAssert = new SoftAssert();

        HomePageBase homePage = login();
        homePage.waitPageToLoad();
        homePage.clickLikeAPost(POST_TEXT);
        softAssert.assertTrue(homePage.isPostLikesCounterPresent(POST_TEXT),
                "[Login Page] Post like counter isn't present!");
        softAssert.assertTrue(homePage.isPostLiked(POST_TEXT), "[Login Page] Post isn't liked!");
        //Case 00017
        softAssert.assertEquals(homePage.getPostLikesCount(POST_TEXT), 1,
                "[Login Page] Counter works incorrectly");
        //Case 00016
        homePage.clickLikeAPost(POST_TEXT);
        softAssert.assertFalse(homePage.isPostLikesCounterPresent(POST_TEXT),
                "[Login Page] Post like counter is present, but should not!");
        softAssert.assertFalse(homePage.isPostLiked(POST_TEXT), "[Login Page] Post isn't disliked!");

        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00015")
    public void likeACommentTest() {
        SoftAssert softAssert = new SoftAssert();

        HomePageBase homePage = login();
        homePage.waitPageToLoad();
        homePage.openComments(POST_TEXT);
        homePage.likeAComment(COMMENT_TEXT, TRUE);
        softAssert.assertTrue(homePage.isCommentLiked(COMMENT_TEXT), "[Login Page] Comment isn't liked!");
        homePage.likeAComment(COMMENT_TEXT, FALSE);
        softAssert.assertFalse(homePage.isCommentLiked(COMMENT_TEXT), "[Login Page] Comment isn't disliked!");

        softAssert.assertAll();
    }
}
