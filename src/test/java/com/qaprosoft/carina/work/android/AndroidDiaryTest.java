package com.qaprosoft.carina.work.android;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.work.mobile.gui.pages.common.AddMealPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.DiaryPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.HomePageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.MealPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.components.BottomNavigationBar;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import com.qaprosoft.carina.work.mobile.gui.pages.services.AuthorizationService;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.BottomBarElements;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.DiaryMealType;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AndroidDiaryTest implements IAbstractTest, IConstants {
    private static AuthorizationService authService;
    private static final String MEAL1 = "Bread";
    private static final String MEAL2 = "Donut";

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
}
