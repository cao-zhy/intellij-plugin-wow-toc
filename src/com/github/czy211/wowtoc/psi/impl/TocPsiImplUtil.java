package com.github.czy211.wowtoc.psi.impl;

import com.github.czy211.wowtoc.psi.TocElementFactory;
import com.github.czy211.wowtoc.psi.TocRefer;
import com.github.czy211.wowtoc.psi.TocTag;
import com.github.czy211.wowtoc.psi.TocTypes;
import com.github.czy211.wowtoc.reference.TocReference;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public class TocPsiImplUtil {
    /**
     * 获取标签名
     *
     * @param psiElement PSI 元素
     * @return 标签名
     */
    public static String getTagName(TocTag psiElement) {
        ASTNode node = psiElement.getNode().findChildByType(TocTypes.TAG_NAME);
        if (node != null) {
            return node.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    /**
     * 获取标签前缀加标签名的内容范围
     *
     * @param psiElement PSI 元素
     * @return 内容范围
     */
    public static TextRange getKeyRange(TocTag psiElement) {
        ASTNode tagPrefixNode = psiElement.getNode().findChildByType(TocTypes.TAG_PREFIX);
        ASTNode tagNameNode = psiElement.getNode().findChildByType(TocTypes.TAG_NAME);
        if (tagPrefixNode == null) {
            return null;
        }
        int start = tagPrefixNode.getStartOffset();
        ASTNode node = tagNameNode == null ? tagPrefixNode : tagNameNode;
        int end = node.getTextRange().getEndOffset();
        return new TextRange(start, end);
    }

    /**
     * 获取文件名
     *
     * @param psiElement PSI 元素
     * @return 文件名
     */
    public static String getFileName(TocRefer psiElement) {
        ASTNode node = psiElement.getNode().findChildByType(TocTypes.FILE_NAME);
        if (node != null) {
            return node.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getName(TocRefer psiElement) {
        return getFileName(psiElement);
    }

    public static PsiElement setName(TocRefer psiElement, String newName) {
        ASTNode node = psiElement.getNode().findChildByType(TocTypes.FILE_NAME);
        if (node != null) {
            String fileName = node.getText();
            String prefix = "";
            if (fileName.contains("\\") || fileName.contains("/")) {
                int endIndex = Math.max(fileName.lastIndexOf("\\"), fileName.lastIndexOf("/"));
                prefix = fileName.substring(0, endIndex + 1);
            }
            // 创建新文件名的引用时，文件名应该包含之前的文件夹前缀
            TocRefer refer = TocElementFactory.createRefer(psiElement.getProject(), prefix + newName);
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
        // 内容范围是从该 PSI 元素开始算，而不是从整个文件开始算
        return new TocReference(psiElement, new TextRange(0, psiElement.getTextLength()));
    }
}
