package com.github.czy211.wowtoc.completion;

import com.github.czy211.wowtoc.lang.TocLanguage;
import com.github.czy211.wowtoc.psi.TocTypes;
import com.github.czy211.wowtoc.util.TocUtil;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class TocCompletionContributor extends CompletionContributor {
    public TocCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(TocTypes.TAG_NAME).withLanguage(TocLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters completionParameters,
                                                  @NotNull ProcessingContext processingContext,
                                                  @NotNull CompletionResultSet completionResultSet) {
                        for (String tagName : TocUtil.TAG_NAMES) {
                            completionResultSet.addElement(LookupElementBuilder.create(tagName));
                        }
                        PsiElement psiElement = completionParameters.getOriginalPosition();
                        if (psiElement != null) {
                            String text = psiElement.getText();
                            if (text.startsWith("Title") || text.startsWith("Notes")) {
                                for (String localization : TocUtil.LOCALIZATION) {
                                    completionResultSet.addElement(LookupElementBuilder.create(text.substring(0, 5)
                                            + "-" + localization));
                                }
                            }
                        }
                    }
                });

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(TocTypes.FILE_NAME).withLanguage(TocLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters completionParameters,
                                                  @NotNull ProcessingContext processingContext,
                                                  @NotNull CompletionResultSet completionResultSet) {
                        PsiElement psiElement = completionParameters.getOriginalPosition();
                        if (psiElement != null) {
                            Set<String> fileNames = TocUtil.getFileNames(psiElement);
                            for (String fileName : fileNames) {
                                completionResultSet.addElement(LookupElementBuilder.create(fileName));
                            }
                        }
                    }
                }
        );
    }
}
