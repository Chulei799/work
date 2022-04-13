package com.qaprosoft.carina.work.android;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.commons.SpecialKeywords;
import com.qaprosoft.carina.core.foundation.crypto.CryptoTool;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.work.mobile.gui.pages.common.DiaryPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.HomePageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.LoginPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.WelcomePageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.components.BottomNavigationBar;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.BottomBarElements;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.DiaryMealType;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.regex.Pattern;

public class AndroidDiaryTest implements IAbstractTest, IConstants {
    private static final CryptoTool cryptoTool = new CryptoTool("./.settings/crypto.key");
    private static final Pattern CRYPTO_PATTERN = Pattern.compile(SpecialKeywords.CRYPT);
    private static final String USER_EMAIL = cryptoTool.decryptByPattern(R.EMAIL.get("user1.email"), CRYPTO_PATTERN);
    private static final String USER_PASSWORD = cryptoTool.decryptByPattern(R.EMAIL.get("user1.password"), CRYPTO_PATTERN);

    public DiaryPageBase logIn() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickLogin();
        loginPage.typeEmail(USER_EMAIL);
        loginPage.typePassword(USER_PASSWORD);
        HomePageBase homePage = loginPage.clickLoginButton();
        homePage.waitPageToLoad();
        BottomNavigationBar bottomNavigationBar = homePage.getBottomNavigationBar();
        return (DiaryPageBase) bottomNavigationBar.clickOn(BottomBarElements.DIARY);
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00018")
    public void addMealToBreakfastTest() {
        SoftAssert softAssert = new SoftAssert();

        DiaryPageBase diaryPage = logIn();
        softAssert.assertTrue(diaryPage.isPageOpened(), "[Diary Page] Page isn't opened!");
        diaryPage.clearDiary();
        softAssert.assertFalse(diaryPage.isEditDiaryVisible(), "[Diary Page] Diary isn't cleared!");
        diaryPage.clickAddToMealTime(DiaryMealType.BREAKFAST);
        diaryPage.searchForMeal("Bread");
        diaryPage.selectMealByIndex(0);
        diaryPage.clickAddButton();
        softAssert.assertEquals(diaryPage.getFirstMealNameInMealType(DiaryMealType.BREAKFAST), "Bread",
                String.format("[Diary Page] %s isn't equal to the meal that was searched (%s)!",
                        diaryPage.getFirstMealNameInMealType(DiaryMealType.BREAKFAST), "Bread"));

        softAssert.assertAll();
    }
}
