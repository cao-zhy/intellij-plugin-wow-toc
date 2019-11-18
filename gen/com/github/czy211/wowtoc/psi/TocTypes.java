// This is a generated file. Not intended for manual editing.
package com.github.czy211.wowtoc.psi;

import com.github.czy211.wowtoc.psi.impl.TocRefImpl;
import com.github.czy211.wowtoc.psi.impl.TocTagImpl;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public interface TocTypes {

    IElementType REF = new TocElementType("REF");
    IElementType TAG = new TocElementType("TAG");

    IElementType COMMENT = new TocTokenType("COMMENT");
    IElementType CRLF = new TocTokenType("CRLF");
    IElementType FILE_NAME = new TocTokenType("FILE_NAME");
    IElementType SEPARATOR = new TocTokenType("SEPARATOR");
    IElementType TAG_NAME = new TocTokenType("TAG_NAME");
    IElementType TAG_PREFIX = new TocTokenType("TAG_PREFIX");
    IElementType TAG_VALUE = new TocTokenType("TAG_VALUE");

    class Factory {
        public static PsiElement createElement(ASTNode node) {
            IElementType type = node.getElementType();
            if (type == REF) {
                return new TocRefImpl(node);
            } else if (type == TAG) {
                return new TocTagImpl(node);
            }
            throw new AssertionError("Unknown element type: " + type);
        }
    }
}
