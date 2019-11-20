package com.github.czy211.wowtoc.psi.impl;

import com.github.czy211.wowtoc.psi.TocNamedElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class TocNamedElementImpl extends ASTWrapperPsiElement implements TocNamedElement {
    public TocNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
