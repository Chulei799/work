package com.qaprosoft.carina.work.mobile.gui.pages.common;

import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.constants.IConstants;
import org.openqa.selenium.WebDriver;

public abstract class HomePageBase extends AbstractPage implements IConstants, IMobileUtils {

    public HomePageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void waitPageToLoad();

    public abstract void clickLikeAPost(String postText);

    public abstract boolean isPostLikesCounterPresent(String postText);

    public abstract boolean isPostLiked(String postText);

    public abstract int getPostLikesCount(String postText);

    public abstract void openComments(String postText);

    public abstract void likeAComment(String commentText, boolean check);

    public abstract boolean isCommentLiked(String commentText);

    public abstract void closeComments();
}
