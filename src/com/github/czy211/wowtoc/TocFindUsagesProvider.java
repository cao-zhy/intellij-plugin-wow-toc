package com.github.czy211.wowtoc;

import com.github.czy211.wowtoc.psi.TocRefer;
import com.github.czy211.wowtoc.psi.TocTypes;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TocFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new TocLexerAdapter(), TokenSet.create(TocTypes.FILE_NAME),
                TokenSet.create(TocTypes.COMMENT), TokenSet.EMPTY);
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof TocRefer;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement psiElement) {
        if (psiElement instanceof TocRefer) {
            return "toc refer";
        } else {
            return "";
        }
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement psiElement) {
        if (psiElement instanceof TocRefer) {
            return ((TocRefer) psiElement).getFileName();
        } else {
            return "";
        }
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement psiElement, boolean b) {
        if (psiElement instanceof TocRefer) {
            return getType(psiElement) + ": " + getDescriptiveName(psiElement);
        } else {
            return "";
        }
    }
}
