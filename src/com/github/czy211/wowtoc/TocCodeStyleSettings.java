package com.github.czy211.wowtoc;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class TocCodeStyleSettings extends CustomCodeStyleSettings {
    public boolean spaceBetweenTagPrefixAndTagName = true;
    public boolean spaceBetweenTagNameAndSeparator = false;
    public boolean spaceBetweenSeparatorAndTagValue = true;
    public int keepBlankLinesInTag = 0;
    public int keepBlankLinesInRefer = 0;
    public int keepBlankLinesBetweenTagAndRefer = 1;
    public int blankLinesInTag = 0;
    public int blankLinesInRefer = 0;
    public int blankLinesBetweenTagAndRefer = 1;

    protected TocCodeStyleSettings(CodeStyleSettings container) {
        super("TocCodeStyleSettings", container);
    }
}
