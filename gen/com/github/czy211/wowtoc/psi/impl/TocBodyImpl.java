// This is a generated file. Not intended for manual editing.
package com.github.czy211.wowtoc.psi.impl;

import com.github.czy211.wowtoc.psi.TocBody;
import com.github.czy211.wowtoc.psi.TocVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class TocBodyImpl extends ASTWrapperPsiElement implements TocBody {

    public TocBodyImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull TocVisitor visitor) {
        visitor.visitBody(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof TocVisitor) accept((TocVisitor) visitor);
        else super.accept(visitor);
    }

}
