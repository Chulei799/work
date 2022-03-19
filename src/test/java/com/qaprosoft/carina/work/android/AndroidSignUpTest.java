package com.qaprosoft.carina.work.android;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.work.mobile.gui.pages.common.ActivitySignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.CreateAccountPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.GoalsSignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.SignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.WeeklyGoalSignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.WelcomePageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.YouSignUpPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import com.qaprosoft.carina.work.mobile.gui.pages.utils.enums.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AndroidSignUpTest implements IAbstractTest, IConstants {

    public static final String JUST_NUMBERS = "0123456789";
    public static final String JUST_LOWER_CASE_LETTERS = "itriedsohard";
    public static final String JUST_UPPER_CASE_LETTERS = "ANDGOTSOFAR";
    public static final String JUST_SPECIAL_SYMBOLS = "!@#$%^&*()_+=";
    public static final String MIXED_PASSWORD = "Cs50!hU&Y2)";
    public static final String SHORT_PASSWORD = "short";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";

    public String convertDoubleToStr(double d, String type) {
        String[] str = String.valueOf(d).split("\\.");
        if (type == HEIGHT) {
            return str[0] + " ft, " + str[1] + " in";
        } else {
            return str[0] + " st, " + str[1] + " lbs";
        }
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00001")
    public void goalAndActivityValidationTest() {
        // Case 00001
        SoftAssert softAssert = new SoftAssert();

        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        SignUpPageBase signUpPage = welcomePage.clickSignUp();
        GoalsSignUpPageBase goalsSignUpPage = signUpPage.clickContinue();
        goalsSignUpPage.checkGoal(Goals.LOSE_WEIGHT, TRUE);
        softAssert.assertTrue(goalsSignUpPage.isGoalChecked(Goals.LOSE_WEIGHT),
                "[Goals Sigh Up Page] Goal(Lose weight) isn't checked!");
        goalsSignUpPage.checkGoal(Goals.LOSE_WEIGHT, FALSE);
        softAssert.assertFalse(goalsSignUpPage.isGoalChecked(Goals.LOSE_WEIGHT),
                "[Goals Sigh Up Page] Goal(Lose weight) isn't unchecked!");

        //Case 00002
        goalsSignUpPage.checkGoal(Goals.MAINTAIN_WEIGHT, TRUE);
        ActivitySignUpPageBase activitySignUpPage = goalsSignUpPage.clickNext();
        YouSignUpPageBase youSignUpPage = activitySignUpPage.selectActivityLevel(ActivityLevel.NOT_VERY_ACTIVE);
        softAssert.assertTrue(youSignUpPage.isPageOpened(), "[Activity Page] You page isn't opened!");

        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00003")
    public void genderAndAgeTest() {
        SoftAssert softAssert = new SoftAssert();

        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        SignUpPageBase signUpPage = welcomePage.clickSignUp();
        GoalsSignUpPageBase goalsSignUpPage = signUpPage.clickContinue();
        goalsSignUpPage.checkGoal(Goals.LOSE_WEIGHT, TRUE);
        ActivitySignUpPageBase activitySignUpPage = goalsSignUpPage.clickNext();
        YouSignUpPageBase youSignUpPage = activitySignUpPage.selectActivityLevel(ActivityLevel.NOT_VERY_ACTIVE);

        youSignUpPage.selectGender(MALE);
        softAssert.assertTrue(youSignUpPage.isGenderSelected(MALE),
                "[You Sign Up Page] Male gender isn't selected!");
        youSignUpPage.selectGender(FEMALE);
        softAssert.assertTrue(youSignUpPage.isGenderSelected(FEMALE),
                "[You Sign Up Page] Female gender isn't selected!");
        softAssert.assertFalse(youSignUpPage.isGenderSelected(MALE),
                "[You Sign Up Page] Male gender is selected, but should not!");
        //Case 00004
        youSignUpPage.typeAge(TWENTY_YEARS_OLD);
        softAssert.assertEquals(youSignUpPage.getAge(), TWENTY_YEARS_OLD,
                "[You Sign Up Page] Age isn't equals to what was entered!");

        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00005")
    public void countryValidationTest() {
        SoftAssert softAssert = new SoftAssert();

        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        SignUpPageBase signUpPage = welcomePage.clickSignUp();
        GoalsSignUpPageBase goalsSignUpPage = signUpPage.clickContinue();
        goalsSignUpPage.checkGoal(Goals.LOSE_WEIGHT, TRUE);
        ActivitySignUpPageBase activitySignUpPage = goalsSignUpPage.clickNext();
        YouSignUpPageBase youSignUpPage = activitySignUpPage.selectActivityLevel(ActivityLevel.NOT_VERY_ACTIVE);

        youSignUpPage.changeCountry(Country.UNITED_KINGDOM);
        softAssert.assertEquals(youSignUpPage.getCountryText(), Country.UNITED_KINGDOM.getName(),
                String.format("[You Sign Up Page] Country '" + "%s' isn't selected!", Country.UNITED_KINGDOM.getName()));

        youSignUpPage.changeCountry(Country.UKRAINE);
        softAssert.assertEquals(youSignUpPage.getCountryText(), Country.UKRAINE.getName(),
                String.format("[You Sign Up Page] Country '" + "%s' isn't selected!", Country.UKRAINE.getName()));

        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00006")
    public void heightAndCurrentWeightValidationTest() {
        SoftAssert softAssert = new SoftAssert();

        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        SignUpPageBase signUpPage = welcomePage.clickSignUp();
        GoalsSignUpPageBase goalsSignUpPage = signUpPage.clickContinue();
        goalsSignUpPage.checkGoal(Goals.LOSE_WEIGHT, TRUE);
        ActivitySignUpPageBase activitySignUpPage = goalsSignUpPage.clickNext();
        YouSignUpPageBase youSignUpPage = activitySignUpPage.selectActivityLevel(ActivityLevel.NOT_VERY_ACTIVE);
        youSignUpPage.selectGender(MALE);
        youSignUpPage.typeAge(TWENTY_YEARS_OLD);
        youSignUpPage.typeZipCode(ZIP_CODE);
        youSignUpPage.clickToNextPage();

        youSignUpPage.typeHeight(CORRECT_HEIGHT_IN_CM, HeightType.CENTIMETERS);
        softAssert.assertEquals(youSignUpPage.getHeight(), String.format("%s" + " cm", (int)CORRECT_HEIGHT_IN_CM),
                "[You Sign Up Page] Height isn't entered correct!(centimeters)");
        youSignUpPage.typeHeight(CORRECT_HEIGHT_IN_FEET, HeightType.FEET_INCHES);
        softAssert.assertEquals(youSignUpPage.getHeight(), convertDoubleToStr(CORRECT_HEIGHT_IN_FEET, HEIGHT),
                "[You Sign Up Page] Height isn't entered correct!(feet & inches)");

        //Case 00007
        youSignUpPage.typeWeight(CURRENT_WEIGHT_IN_KG, WeightType.KILOGRAMS);
        softAssert.assertEquals(youSignUpPage.getWeight(), String.format("%s" + " kg", CURRENT_WEIGHT_IN_KG),
                "[You Sign Up Page] Weight isn't entered correct!(kilograms)");
        youSignUpPage.typeWeight(IConstants.CURRENT_WEIGHT_IN_POUNDS, WeightType.POUNDS);
        softAssert.assertEquals(youSignUpPage.getWeight(), String.format("%s" + " lbs", (int)CURRENT_WEIGHT_IN_POUNDS),
                "[You Sign Up Page] Weight isn't entered correct!(pounds)");
        youSignUpPage.typeWeight(CURRENT_WEIGHT_IN_STONE, WeightType.STONE);
        softAssert.assertEquals(youSignUpPage.getWeight(), convertDoubleToStr(CURRENT_WEIGHT_IN_STONE, WEIGHT),
                "[You Sign Up Page] Weight isn't entered correct!(stone)");

        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00008")
    public void weeklyGoalWeightValidationTest() {
        SoftAssert softAssert = new SoftAssert();

        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        SignUpPageBase signUpPage = welcomePage.clickSignUp();
        GoalsSignUpPageBase goalsSignUpPage = signUpPage.clickContinue();
        goalsSignUpPage.checkGoal(Goals.LOSE_WEIGHT, TRUE);
        ActivitySignUpPageBase activitySignUpPage = goalsSignUpPage.clickNext();
        YouSignUpPageBase youSignUpPage = activitySignUpPage.selectActivityLevel(ActivityLevel.NOT_VERY_ACTIVE);
        youSignUpPage.selectGender(MALE);
        youSignUpPage.typeAge(TWENTY_YEARS_OLD);
        youSignUpPage.typeZipCode(ZIP_CODE);
        youSignUpPage.clickToNextPage();
        youSignUpPage.typeHeight(CORRECT_HEIGHT_IN_CM, HeightType.CENTIMETERS);
        youSignUpPage.typeWeight(CURRENT_WEIGHT_IN_KG, WeightType.KILOGRAMS);
        WeeklyGoalSignUpPageBase weeklyGoalPage = youSignUpPage.clickToWeeklyGoalPage();

        weeklyGoalPage.typeWeight(GOAL_WEIGHT_IN_KG, WeightType.KILOGRAMS);
        softAssert.assertEquals(weeklyGoalPage.getWeight(), String.format("%s" + " kg", (int)GOAL_WEIGHT_IN_KG),
                "[You Sign Up Page] Weight isn't entered correct!(kilograms)");
        weeklyGoalPage.typeWeight(GOAL_WEIGHT_IN_POUNDS, WeightType.POUNDS);
        softAssert.assertEquals(weeklyGoalPage.getWeight(), String.format("%s" + " lbs", (int)GOAL_WEIGHT_IN_POUNDS),
                "[You Sign Up Page] Weight isn't entered correct!(pounds)");
        weeklyGoalPage.typeWeight(GOAL_WEIGHT_IN_STONE, WeightType.STONE);
        softAssert.assertEquals(weeklyGoalPage.getWeight(), convertDoubleToStr(GOAL_WEIGHT_IN_STONE, WEIGHT),
                "[You Sign Up Page] Weight isn't entered correct!(stone)");

        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00009")
    public void weeklyGoalSwitchTest() {
        SoftAssert softAssert = new SoftAssert();

        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        SignUpPageBase signUpPage = welcomePage.clickSignUp();
        GoalsSignUpPageBase goalsSignUpPage = signUpPage.clickContinue();
        goalsSignUpPage.checkGoal(Goals.LOSE_WEIGHT, TRUE);
        ActivitySignUpPageBase activitySignUpPage = goalsSignUpPage.clickNext();
        YouSignUpPageBase youSignUpPage = activitySignUpPage.selectActivityLevel(ActivityLevel.NOT_VERY_ACTIVE);
        youSignUpPage.selectGender(MALE);
        youSignUpPage.typeAge(TWENTY_YEARS_OLD);
        youSignUpPage.typeZipCode(ZIP_CODE);
        youSignUpPage.clickToNextPage();
        youSignUpPage.typeHeight(CORRECT_HEIGHT_IN_CM, HeightType.CENTIMETERS);
        youSignUpPage.typeWeight(CURRENT_WEIGHT_IN_KG, WeightType.KILOGRAMS);
        WeeklyGoalSignUpPageBase weeklyGoalPage = youSignUpPage.clickToWeeklyGoalPage();
        weeklyGoalPage.typeWeight(GOAL_WEIGHT_IN_KG, WeightType.KILOGRAMS);

        weeklyGoalPage.checkWeeklyGoal(WeeklyGoal.ONE_QUARTER_KG_PW);
        softAssert.assertTrue(weeklyGoalPage.isWeeklyGoalChecked(WeeklyGoal.ONE_QUARTER_KG_PW),
                String.format("[Weekly Goal Page] %s not checked!", WeeklyGoal.ONE_QUARTER_KG_PW));
        weeklyGoalPage.checkWeeklyGoal(WeeklyGoal.TWO_QUARTER_KG_PW);
        softAssert.assertTrue(weeklyGoalPage.isWeeklyGoalChecked(WeeklyGoal.TWO_QUARTER_KG_PW),
                String.format("[Weekly Goal Page] %s not checked!", WeeklyGoal.TWO_QUARTER_KG_PW));
        weeklyGoalPage.checkWeeklyGoal(WeeklyGoal.THREE_QUARTER_KG_PW);
        softAssert.assertTrue(weeklyGoalPage.isWeeklyGoalChecked(WeeklyGoal.THREE_QUARTER_KG_PW),
                String.format("[Weekly Goal Page] %s not checked!", WeeklyGoal.THREE_QUARTER_KG_PW));
        weeklyGoalPage.checkWeeklyGoal(WeeklyGoal.ONE_KG_PW);
        softAssert.assertTrue(weeklyGoalPage.isWeeklyGoalChecked(WeeklyGoal.ONE_KG_PW),
                String.format("[Weekly Goal Page] %s not checked!", WeeklyGoal.ONE_KG_PW));

        weeklyGoalPage.typeWeight(GOAL_WEIGHT_IN_POUNDS, WeightType.POUNDS);
        weeklyGoalPage.checkWeeklyGoal(WeeklyGoal.ONE_QUARTER_KG_PW);
        softAssert.assertTrue(weeklyGoalPage.isWeeklyGoalChecked(WeeklyGoal.ONE_QUARTER_KG_PW),
                String.format("[Weekly Goal Page] %s not checked!", WeeklyGoal.ONE_QUARTER_KG_PW));
        weeklyGoalPage.checkWeeklyGoal(WeeklyGoal.TWO_QUARTER_KG_PW);
        softAssert.assertTrue(weeklyGoalPage.isWeeklyGoalChecked(WeeklyGoal.TWO_QUARTER_KG_PW),
                String.format("[Weekly Goal Page] %s not checked!", WeeklyGoal.TWO_QUARTER_KG_PW));
        weeklyGoalPage.checkWeeklyGoal(WeeklyGoal.THREE_QUARTER_KG_PW);
        softAssert.assertTrue(weeklyGoalPage.isWeeklyGoalChecked(WeeklyGoal.THREE_QUARTER_KG_PW),
                String.format("[Weekly Goal Page] %s not checked!", WeeklyGoal.THREE_QUARTER_KG_PW));
        weeklyGoalPage.checkWeeklyGoal(WeeklyGoal.ONE_KG_PW);
        softAssert.assertTrue(weeklyGoalPage.isWeeklyGoalChecked(WeeklyGoal.ONE_KG_PW),
                String.format("[Weekly Goal Page] %s not checked!", WeeklyGoal.ONE_KG_PW));

        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00010")
    public void passwordValidationTest() {
        SoftAssert softAssert = new SoftAssert();

        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        SignUpPageBase signUpPage = welcomePage.clickSignUp();
        GoalsSignUpPageBase goalsSignUpPage = signUpPage.clickContinue();
        goalsSignUpPage.checkGoal(Goals.LOSE_WEIGHT, TRUE);
        ActivitySignUpPageBase activitySignUpPage = goalsSignUpPage.clickNext();
        YouSignUpPageBase youSignUpPage = activitySignUpPage.selectActivityLevel(ActivityLevel.NOT_VERY_ACTIVE);
        youSignUpPage.selectGender(MALE);
        youSignUpPage.typeAge(TWENTY_YEARS_OLD);
        youSignUpPage.typeZipCode(ZIP_CODE);
        youSignUpPage.clickToNextPage();
        youSignUpPage.typeHeight(CORRECT_HEIGHT_IN_CM, HeightType.CENTIMETERS);
        youSignUpPage.typeWeight(CURRENT_WEIGHT_IN_KG, WeightType.KILOGRAMS);
        WeeklyGoalSignUpPageBase weeklyGoalPage = youSignUpPage.clickToWeeklyGoalPage();
        weeklyGoalPage.typeWeight(GOAL_WEIGHT_IN_KG, WeightType.KILOGRAMS);
        CreateAccountPageBase createAccountPage = weeklyGoalPage.clickNextButton();
        createAccountPage.typeEmail(CORRECT_EMAIL);

        //Case 00010
        createAccountPage.typePassword(JUST_NUMBERS);
        createAccountPage.clickNextButton();
        softAssert.assertTrue(createAccountPage.isEmailInUseMessagePresent(),
                "[Create Account] User should be able sign in with just number in password");
        createAccountPage.closeEmailInUseMessage();
        createAccountPage.typePassword(JUST_LOWER_CASE_LETTERS);
        createAccountPage.clickNextButton();
        softAssert.assertTrue(createAccountPage.isEmailInUseMessagePresent(),
                "[Create Account] User should be able sign in with just lower case letters in password");
        createAccountPage.closeEmailInUseMessage();
        createAccountPage.typePassword(JUST_UPPER_CASE_LETTERS);
        createAccountPage.clickNextButton();
        softAssert.assertTrue(createAccountPage.isEmailInUseMessagePresent(),
                "[Create Account] User should be able sign in with just upper case letters in password");
        createAccountPage.closeEmailInUseMessage();
        createAccountPage.typePassword(JUST_SPECIAL_SYMBOLS);
        createAccountPage.clickNextButton();
        softAssert.assertTrue(createAccountPage.isEmailInUseMessagePresent(),
                "[Create Account] User should be able sign in with just special symbols in password");
        createAccountPage.closeEmailInUseMessage();
        createAccountPage.typePassword(MIXED_PASSWORD);
        createAccountPage.clickNextButton();
        softAssert.assertTrue(createAccountPage.isEmailInUseMessagePresent(),
                "[Create Account] User should be able sign in with mixed symbols in password");
        createAccountPage.closeEmailInUseMessage();

        //Case 00011
        createAccountPage.typePassword(SHORT_PASSWORD);
        createAccountPage.clickNextButton();
        softAssert.assertTrue(createAccountPage.isPasswordErrorPresent(),
                "[Create Account] User can’t sign up with password less then 10 symbols!");

        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "chulei799")
    @TestRailCases(testCasesId = "00012")
    public void emailValidationTest() {
        SoftAssert softAssert = new SoftAssert();

        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        SignUpPageBase signUpPage = welcomePage.clickSignUp();
        GoalsSignUpPageBase goalsSignUpPage = signUpPage.clickContinue();
        goalsSignUpPage.checkGoal(Goals.LOSE_WEIGHT, TRUE);
        ActivitySignUpPageBase activitySignUpPage = goalsSignUpPage.clickNext();
        YouSignUpPageBase youSignUpPage = activitySignUpPage.selectActivityLevel(ActivityLevel.NOT_VERY_ACTIVE);
        youSignUpPage.selectGender(MALE);
        youSignUpPage.typeAge(TWENTY_YEARS_OLD);
        youSignUpPage.typeZipCode(ZIP_CODE);
        youSignUpPage.clickToNextPage();
        youSignUpPage.typeHeight(CORRECT_HEIGHT_IN_CM, HeightType.CENTIMETERS);
        youSignUpPage.typeWeight(CURRENT_WEIGHT_IN_KG, WeightType.KILOGRAMS);
        WeeklyGoalSignUpPageBase weeklyGoalPage = youSignUpPage.clickToWeeklyGoalPage();
        weeklyGoalPage.typeWeight(GOAL_WEIGHT_IN_KG, WeightType.KILOGRAMS);
        CreateAccountPageBase createAccountPage = weeklyGoalPage.clickNextButton();
        createAccountPage.typePassword(MIXED_PASSWORD);

        createAccountPage.typeEmail(INCORRECT_EMAIL);
        createAccountPage.clickNextButton();
        softAssert.assertTrue(createAccountPage.isInvalidEmailMessagePresent(),
                "[Create Account] User can’t sign up with invalid email!");

        softAssert.assertAll();
    }
}