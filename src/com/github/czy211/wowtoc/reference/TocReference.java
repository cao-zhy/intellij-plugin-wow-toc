package com.github.czy211.wowtoc.reference;

import com.github.czy211.wowtoc.psi.TocRefer;
import com.github.czy211.wowtoc.util.TocUtil;
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

    /**
     * 返回元素的引用
     *
     * @return 元素的引用
     */
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

    /**
     * 重写此方法来重命名元素
     *
     * @param newElementName 新名字
     * @return 有新名字的元素
     * @throws IncorrectOperationException 不正确操作的异常
     */
    @Override
    public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
        if (myElement instanceof TocRefer) {
            return ((TocRefer) myElement).setName(newElementName);
        }
        return null;
    }
}
