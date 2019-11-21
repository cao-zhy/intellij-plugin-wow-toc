package com.github.czy211.wowtoc;

import com.github.czy211.wowtoc.psi.TocTypes;
import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.formatting.FormattingModelProvider;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

public class TocFormattingModelBuilder implements FormattingModelBuilder {
    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        TocCodeStyleSettings customSettings = settings.getCustomSettings(TocCodeStyleSettings.class);
        SpacingBuilder spacingBuilder = new SpacingBuilder(settings, TocLanguage.INSTANCE);

        spacingBuilder.between(TocTypes.TAG_PREFIX, TocTypes.TAG_NAME).spaceIf(
                customSettings.spaceBetweenTagPrefixAndTagName);
        spacingBuilder.between(TocTypes.TAG_NAME, TocTypes.SEPARATOR).spaceIf(
                customSettings.spaceBetweenTagNameAndSeparator);
        spacingBuilder.between(TocTypes.SEPARATOR, TocTypes.TAG_VALUE).spaceIf(
                customSettings.spaceBetweenSeparatorAndTagValue);

        spacingBuilder.between(TocTypes.TAG, TocTypes.TAG).spacing(0, 0, 1 + customSettings.blankLinesInTag, true,
                customSettings.keepBlankLinesInTag);
        spacingBuilder.between(TocTypes.REFER, TocTypes.REFER).spacing(0, 0, 1 + customSettings.blankLinesInRefer,
                true, customSettings.keepBlankLinesInRefer);
        spacingBuilder.between(TocTypes.TAG, TocTypes.REFER).spacing(0, 0,
                1 + customSettings.blankLinesBetweenTagAndRefer, true, customSettings.keepBlankLinesBetweenTagAndRefer);
        spacingBuilder.between(TocTypes.REFER, TocTypes.TAG).spacing(0, 0,
                1 + customSettings.blankLinesBetweenTagAndRefer, true, customSettings.keepBlankLinesBetweenTagAndRefer);
        return spacingBuilder;
    }

    @NotNull
    @Override
    public FormattingModel createModel(PsiElement psiElement, CodeStyleSettings codeStyleSettings) {
        TocCodeStyleSettings customSettings = codeStyleSettings.getCustomSettings(TocCodeStyleSettings.class);
        SpacingBuilder spacingBuilder = createSpaceBuilder(codeStyleSettings);
        TocBlock block = new TocBlock(psiElement.getNode(), spacingBuilder, customSettings);
        return FormattingModelProvider.createFormattingModelForPsiFile(psiElement.getContainingFile(), block,
                codeStyleSettings);
    }
}
