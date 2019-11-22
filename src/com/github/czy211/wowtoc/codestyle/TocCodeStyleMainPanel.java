package com.github.czy211.wowtoc.codestyle;

import com.github.czy211.wowtoc.lang.TocLanguage;
import com.intellij.application.options.GenerationCodeStylePanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.psi.codeStyle.CodeStyleSettings;

public class TocCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {

    public TocCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
        super(TocLanguage.INSTANCE, currentSettings, settings);
    }

    @Override
    protected void initTabs(CodeStyleSettings settings) {
        addSpacesTab(settings);
        addBlankLinesTab(settings);
        addTab(new GenerationCodeStylePanel(settings, TocLanguage.INSTANCE));
    }
}
