// This is a generated file. Not intended for manual editing.
package com.github.czy211.wowtoc.psi.impl;

import com.github.czy211.wowtoc.psi.TocRef;
import com.github.czy211.wowtoc.psi.TocVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class TocRefImpl extends ASTWrapperPsiElement implements TocRef {

    public TocRefImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull TocVisitor visitor) {
        visitor.visitRef(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof TocVisitor) accept((TocVisitor) visitor);
        else super.accept(visitor);
    }

}
