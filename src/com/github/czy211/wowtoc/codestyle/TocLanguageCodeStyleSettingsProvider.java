package com.github.czy211.wowtoc.codestyle;

import com.github.czy211.wowtoc.constant.Constants;
import com.github.czy211.wowtoc.lang.TocLanguage;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TocLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.SPACING_SETTINGS) {
            consumer.showCustomOption(TocCodeStyleSettings.class, "spaceBetweenTagPrefixAndTagName",
                    "Between tag prefix and tag name", CodeStyleSettingsCustomizable.SPACES_OTHER);
            consumer.showCustomOption(TocCodeStyleSettings.class, "spaceBetweenTagNameAndSeparator",
                    "Between tag name and separator", CodeStyleSettingsCustomizable.SPACES_OTHER);
            consumer.showCustomOption(TocCodeStyleSettings.class, "spaceBetweenSeparatorAndTagValue",
                    "Between separator and tag value", CodeStyleSettingsCustomizable.SPACES_OTHER);
        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
            consumer.showCustomOption(TocCodeStyleSettings.class, "keepBlankLinesInTag", "In tag",
                    CodeStyleSettingsCustomizable.BLANK_LINES_KEEP);
            consumer.showCustomOption(TocCodeStyleSettings.class, "keepBlankLinesInRefer", "In refer",
                    CodeStyleSettingsCustomizable.BLANK_LINES_KEEP);
            consumer.showCustomOption(TocCodeStyleSettings.class, "keepBlankLinesBetweenTagAndRefer",
                    "Between tag and refer", CodeStyleSettingsCustomizable.BLANK_LINES_KEEP);
            consumer.showCustomOption(TocCodeStyleSettings.class, "blankLinesInTag", "In tag",
                    CodeStyleSettingsCustomizable.BLANK_LINES);
            consumer.showCustomOption(TocCodeStyleSettings.class, "blankLinesInRefer", "In refer",
                    CodeStyleSettingsCustomizable.BLANK_LINES);
            consumer.showCustomOption(TocCodeStyleSettings.class, "blankLinesBetweenTagAndRefer",
                    "Between tag and refer", CodeStyleSettingsCustomizable.BLANK_LINES);
        } else if (settingsType == SettingsType.COMMENTER_SETTINGS) {
            consumer.showStandardOptions("LINE_COMMENT_AT_FIRST_COLUMN", "LINE_COMMENT_ADD_SPACE");
        }
    }

    @Nullable
    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return Constants.DEMO_TEXT;
    }

    @NotNull
    @Override
    public Language getLanguage() {
        return TocLanguage.INSTANCE;
    }

    @Override
    protected void customizeDefaults(@NotNull CommonCodeStyleSettings commonSettings,
                                     @NotNull CommonCodeStyleSettings.IndentOptions indentOptions) {
        commonSettings.LINE_COMMENT_AT_FIRST_COLUMN = false;
        commonSettings.LINE_COMMENT_ADD_SPACE = true;
    }
}
