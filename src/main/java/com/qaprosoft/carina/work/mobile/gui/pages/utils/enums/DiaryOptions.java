package com.qaprosoft.carina.work.mobile.gui.pages.utils.enums;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.work.mobile.gui.pages.common.CompleteDiaryPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.DiaryAddNotePageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.DiaryAddWaterPageBase;
import com.qaprosoft.carina.work.mobile.gui.pages.common.DiarySettingsPageBase;

public enum DiaryOptions {
    COMPLETE_DIARY("Complete Diary", CompleteDiaryPageBase.class),
    ADD_WATER("Add Water", DiaryAddWaterPageBase.class),
    ADD_NOTE("Add Note", DiaryAddNotePageBase.class),
    DIARY_SETTINGS("Diary Settings", DiarySettingsPageBase.class);

    private String name;
    private Class<? extends AbstractPage> pageClass;

    DiaryOptions(String name, Class<? extends AbstractPage> pageClass) {
        this.name = name;
        this.pageClass = pageClass;
    }

    public String getName() {
        return name;
    }

    public Class<? extends AbstractPage> getPageClass() {
        return pageClass;
    }
}
