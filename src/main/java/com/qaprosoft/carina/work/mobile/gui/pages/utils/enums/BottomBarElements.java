package com.qaprosoft.carina.work.mobile.gui.pages.utils.enums;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.common.*;

public enum BottomBarElements {
    HOME("Home", HomePageBase.class),
    DIARY("Diary", DiaryPageBase.class),
    RECIPES("Recipes", RecipesPageBase.class),
    PLANS("Plans", PlansPageBase.class),
    ME("Me", MePageBase.class);

    private String name;
    private Class<? extends AbstractPage> pageClass;

    BottomBarElements(String name, Class<? extends AbstractPage> pageClass) {
        this.name = name;
        this.pageClass = pageClass;
    }

    public String getName() {
        return name;
    }

    public Class<? extends AbstractPage> getPageClass() {
        return this.pageClass;
    }
}
