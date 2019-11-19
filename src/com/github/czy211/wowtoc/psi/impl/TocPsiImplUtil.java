package com.github.czy211.wowtoc.psi.impl;

import com.github.czy211.wowtoc.psi.TocRefer;
import com.github.czy211.wowtoc.psi.TocTag;
import com.github.czy211.wowtoc.psi.TocTypes;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;

public class TocPsiImplUtil {
    public static String getTagName(TocTag psiElement) {
        ASTNode node = psiElement.getNode().findChildByType(TocTypes.TAG_NAME);
        if (node != null) {
            return node.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static TextRange getTagNameRange(TocTag psiElement) {
        ASTNode node = psiElement.getNode().findChildByType(TocTypes.TAG_NAME);
        return node == null ? null : node.getTextRange();
    }

    public static String getFileName(TocRefer psiElement) {
        ASTNode node = psiElement.getNode().findChildByType(TocTypes.FILE_NAME);
        if (node != null) {
            return node.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static TextRange getFileNameRange(TocRefer psiElement) {
        ASTNode node = psiElement.getNode().findChildByType(TocTypes.FILE_NAME);
        return node == null ? null : node.getTextRange();
    }
}
