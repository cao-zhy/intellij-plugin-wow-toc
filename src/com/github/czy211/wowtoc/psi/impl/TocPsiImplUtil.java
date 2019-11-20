package com.github.czy211.wowtoc.psi.impl;

import com.github.czy211.wowtoc.TocReference;
import com.github.czy211.wowtoc.psi.TocElementFactory;
import com.github.czy211.wowtoc.psi.TocRefer;
import com.github.czy211.wowtoc.psi.TocTag;
import com.github.czy211.wowtoc.psi.TocTypes;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

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

    public static String getName(TocRefer psiElement) {
        return getFileName(psiElement);
    }

    public static PsiElement setName(TocRefer psiElement, String newName) {
        ASTNode node = psiElement.getNode().findChildByType(TocTypes.FILE_NAME);
        if (node != null) {
            TocRefer refer = TocElementFactory.createRefer(psiElement.getProject(), newName);
            ASTNode newNode = refer.getFirstChild().getNode();
            psiElement.getNode().replaceChild(node, newNode);
        }
        return psiElement;
    }

    public static PsiElement getNameIdentifier(TocRefer psiElement) {
        ASTNode node = psiElement.getNode().findChildByType(TocTypes.FILE_NAME);
        if (node != null) {
            return node.getPsi();
        } else {
            return null;
        }
    }

    public static PsiReference getReference(TocRefer psiElement) {
        return new TocReference(psiElement, new TextRange(0, psiElement.getTextLength()));
    }
}
