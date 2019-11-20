package com.github.czy211.wowtoc;

import com.github.czy211.wowtoc.psi.TocRefer;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TocReference extends PsiReferenceBase {
    public TocReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        if (myElement instanceof TocRefer) {
            PsiDirectory directory = myElement.getContainingFile().getParent();
            if (directory != null) {
                String fileName = ((TocRefer) myElement).getFileName();
                if (fileName.matches(TocUtil.REGEX_FILE_NAME)) {
                    return directory.findFile(fileName);
                }
            }
        }
        return null;
    }

    @Override
    public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
        if (myElement instanceof TocRefer) {
            return ((TocRefer) myElement).setName(newElementName);
        }
        return null;
    }
}
