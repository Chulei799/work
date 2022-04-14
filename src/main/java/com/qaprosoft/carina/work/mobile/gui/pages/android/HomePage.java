package com.qaprosoft.carina.work.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.work.mobile.gui.pages.common.HomePageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.components.BottomNavigationBar;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(id = "com.myfitnesspal.android:id/news_feed")
    private ExtendedWebElement scrollView;

    //Find like button by post text
    @FindBy(xpath = "//*[@text = '%s']/parent::*[contains(@class, 'RelativeLayout')]/following-sibling::*/descendant::*[contains(@resource-id, 'textLikeButton')]")
    private ExtendedWebElement likeButton;

    //Find comment button by post text
    @FindBy(xpath = "//*[@text = '%s']/parent::*[contains(@class, 'RelativeLayout')]/following-sibling::*/descendant::*[contains(@resource-id, 'layoutCommentButton')]")
    private ExtendedWebElement commentButton;

    //Find likes counter by post text
    @FindBy(xpath = "//*[@text = '%s']/parent::*[contains(@class, 'RelativeLayout')]/following-sibling::*/descendant::*[contains(@resource-id, 'textNumberOfLikes')]")
    private ExtendedWebElement likesCounter;

    //Find comment like check by comment text
    @FindBy(xpath = "//*[@text = 'Share comment']/following-sibling::*/descendant::*[contains(@resource-id, 'checkLiked')]")
    private ExtendedWebElement commentLikeCheck;

    //Find comment like counter by comment text
    @FindBy(xpath = "//*[@text = 'Share comment']/following-sibling::*/descendant::*[contains(@resource-id, 'textLikeCount')]")
    private ExtendedWebElement commentLikesCounter;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/toolbar']//android.widget.ImageButton")
    private ExtendedWebElement backButton;

    @FindBy(id = "com.myfitnesspal.android:id/progressPleaseWait")
    private ExtendedWebElement progressPleaseWait;

    @FindBy(xpath = "//android.view.View[@content-desc='Feature Highlight']")
    private ExtendedWebElement featureHighlight;

    @FindBy(xpath = "//*[@text = 'Water']")
    private ExtendedWebElement itemByText;

    @FindBy(id = "com.myfitnesspal.android:id/floating_button_fg")
    private ExtendedWebElement floatingButton;

    @FindBy(xpath = "//*[@resource-id = 'com.myfitnesspal.android:id/bottomNavigationBar']")
    private BottomNavigationBar bottomNavigationBar;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitPageToLoad() {
        waitUntil(ExpectedConditions.visibilityOf(progressPleaseWait.getElement()), TEN_TIMEOUT);
        waitUntil(ExpectedConditions.or(ExpectedConditions.visibilityOf(featureHighlight.getElement()),
                ExpectedConditions.visibilityOf(scrollView.getElement())), ONE_MINUTE_TIMEOUT);
        while (featureHighlight.isElementPresent(ONE_SECOND)) {
            featureHighlight.click(ONE_SECOND);
        }
    }

    @Override
    public void clickLikeAPost(String postText) {
        ExtendedWebElement element = likeButton.format(postText);
        if (swipe(element, scrollView, Direction.UP, TWENTY_SWIPES, NORMAL_SWIPES)) {
            element.click(ONE_SECOND);
        } else {
            Assert.fail("[Login Page] Can't swipe to element(Like Button)!");
        }
    }

    @Override
    public boolean isPostLikesCounterPresent(String postText) {
        return likesCounter.format(postText).isElementPresent(THREE_SECONDS);
    }

    @Override
    public boolean isPostLiked(String postText) {
        return Boolean.parseBoolean(likeButton.format(postText).getAttribute("selected"));
    }

    @Override
    public int getPostLikesCount(String postText) {
        String str = likesCounter.format(postText).getText();
        String[] result = str.split("\\D+");
        return Integer.parseInt(String.join("", result));
    }

    @Override
    public void openComments(String postText) {
        ExtendedWebElement element = commentButton.format(postText);
        if (swipe(element, scrollView, Direction.UP, TWENTY_SWIPES, NORMAL_SWIPES)) {
            element.click(ONE_SECOND);
        } else {
            Assert.fail("[Login Page] Can't swipe to element(Comment Button)!");
        }
        if (itemByText.isElementPresent(ONE_SECOND)) {
            floatingButton.click(ONE_SECOND);
            Point point = element.getLocation();
            swipe(point.x, point.y, point.x, point.y - 200, NORMAL_SWIPES);
            element.click(ONE_SECOND);
        }
    }

    @Override
    public void likeAComment(String commentText, boolean check) {
        ExtendedWebElement element = commentLikeCheck.format(commentText);
        if (swipe(element, Direction.UP, FIVE_SWIPES, NORMAL_SWIPES)) {
            if (check) {
                element.check();
            } else {
                element.uncheck();
            }
        } else {
            Assert.fail("[Login Page] Can't swipe to element(Comment Like Check)!");
        }
    }

    @Override
    public boolean isCommentLiked(String commentText) {
        return commentLikeCheck.format(commentText).isChecked();
    }

    @Override
    public void closeComments() {
        backButton.click(ONE_SECOND);
    }

    @Override
    public BottomNavigationBar getBottomNavigationBar() {
        return bottomNavigationBar;
    }
}
