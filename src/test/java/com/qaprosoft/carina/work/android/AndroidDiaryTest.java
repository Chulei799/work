package com.qaprosoft.carina.work.android;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.work.mobile.gui.pages.common.*;
import com.qaprosoft.carina.work.mobile.gui.pages.components.BottomNavigationBar;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import com.qaprosoft.carina.work.mobile.gui.pages.services.AuthorizationService;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.BottomBarElements;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.DiaryMealType;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.DiaryOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AndroidDiaryTest implements IAbstractTest, IConstants {
    private static AuthorizationService authService;
    private static final String MEAL1 = "Bread";
    private static final String MEAL2 = "Donut";
    private static final String NINE_O_CLOCK_AM = "9:00 AM";
    private static final String TEN_O_CLOCK_AM = "10:00 AM";
    private static final String TWO_O_CLOCK_PM = "2:00 PM";
    private static final String THREE_O_CLOCK_PM = "3:00 PM";

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00018")
    public void addMealToBreakfastTest() {
        authService = new AuthorizationService(getDriver());
        SoftAssert softAssert = new SoftAssert();

        HomePageBase homePage = authService.logIn();
        BottomNavigationBar bottomNavigationBar = homePage.getBottomNavigationBar();
        DiaryPageBase diaryPage = (DiaryPageBase) bottomNavigationBar.clickOn(BottomBarElements.DIARY);
        diaryPage.clearDiary();
        MealPageBase mealPage = diaryPage.clickAddToMealType(DiaryMealType.BREAKFAST);
        mealPage.searchForMeal(MEAL1);
        AddMealPageBase addMealPage = mealPage.clickMealByIndex(0);
        diaryPage = addMealPage.submitMeal();
        mealPage = diaryPage.clickAddToMealType(DiaryMealType.LUNCH);
        mealPage.searchForMeal(MEAL2);
        addMealPage = mealPage.clickMealByIndex(0);
        addMealPage.submitMeal();
        softAssert.assertTrue(diaryPage.isMealInMealType(MEAL1, DiaryMealType.BREAKFAST));
        softAssert.assertTrue(diaryPage.isMealInMealType(MEAL2, DiaryMealType.LUNCH));

        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00019")
    public void onAndOffTimestampTest() {
        authService = new AuthorizationService(getDriver());
        SoftAssert softAssert = new SoftAssert();

        HomePageBase homePage = authService.logIn();
        BottomNavigationBar bottomNavigationBar = homePage.getBottomNavigationBar();
        DiaryPageBase diaryPage = (DiaryPageBase) bottomNavigationBar.clickOn(BottomBarElements.DIARY);
        DiarySettingsPageBase diarySettingsPage = (DiarySettingsPageBase) diaryPage.openOptionPage(DiaryOptions.DIARY_SETTINGS);
        softAssert.assertTrue(diarySettingsPage.isShowFoodTimestampPresent(), "[Diary Settings Page] Timestamp isn't present!");

        //Case 00020
        diarySettingsPage.checkShowFoodTimestamp(true);
        softAssert.assertTrue(diarySettingsPage.isShowFoodTimestampChecked(), "[Diary Settings Page] Timestamp setting isn't turned on!");
        diarySettingsPage.checkShowFoodTimestamp(false);
        softAssert.assertFalse(diarySettingsPage.isShowFoodTimestampChecked(), "[Diary Settings Page] Timestamp setting isn't turned off!");

        //Case 00021
        diarySettingsPage.checkShowFoodTimestamp(true);
        diaryPage = diarySettingsPage.clickBackButton();
        softAssert.assertTrue(diaryPage.isTimestampActive(), "[Diary Page] Timestamp isn't active!");
        diarySettingsPage = (DiarySettingsPageBase) diaryPage.openOptionPage(DiaryOptions.DIARY_SETTINGS);
        diarySettingsPage.checkShowFoodTimestamp(false);
        diaryPage = diarySettingsPage.clickBackButton();
        softAssert.assertFalse(diaryPage.isTimestampActive(), "[Diary Page] Timestamp still active, but should not!");

        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00022")
    public void changeTimestampTest() {
        authService = new AuthorizationService(getDriver());
        SoftAssert softAssert = new SoftAssert();

        HomePageBase homePage = authService.logIn();
        BottomNavigationBar bottomNavigationBar = homePage.getBottomNavigationBar();
        DiaryPageBase diaryPage = (DiaryPageBase) bottomNavigationBar.clickOn(BottomBarElements.DIARY);
        DiarySettingsPageBase diarySettingsPage = (DiarySettingsPageBase) diaryPage.openOptionPage(DiaryOptions.DIARY_SETTINGS);
        diarySettingsPage.checkShowFoodTimestamp(true);
        diaryPage = diarySettingsPage.clickBackButton();
        diaryPage.setTimestampOfFoodInMealType(MEAL1, DiaryMealType.BREAKFAST, TEN_O_CLOCK_AM);
        softAssert.assertEquals(diaryPage.getTimestampOfFoodInMealType(MEAL1, DiaryMealType.BREAKFAST),
                TEN_O_CLOCK_AM, String.format("[Diary Page] Time entered incorrectly for meal(%s)!", MEAL1));
        diaryPage.setTimestampOfFoodInMealType(MEAL2, DiaryMealType.LUNCH, THREE_O_CLOCK_PM);
        softAssert.assertEquals(diaryPage.getTimestampOfFoodInMealType(MEAL2, DiaryMealType.LUNCH),
                THREE_O_CLOCK_PM, String.format("[Diary Page] Time entered incorrectly for meal(%s)!", MEAL2));

        softAssert.assertAll();
    }
}
