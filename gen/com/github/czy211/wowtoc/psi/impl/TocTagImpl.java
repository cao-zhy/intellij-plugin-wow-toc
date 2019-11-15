// This is a generated file. Not intended for manual editing.
package com.github.czy211.wowtoc.psi.impl;

import com.github.czy211.wowtoc.psi.TocTag;
import com.github.czy211.wowtoc.psi.TocVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class TocTagImpl extends ASTWrapperPsiElement implements TocTag {

    public TocTagImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull TocVisitor visitor) {
        visitor.visitTag(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof TocVisitor) accept((TocVisitor) visitor);
        else super.accept(visitor);
    }

}
