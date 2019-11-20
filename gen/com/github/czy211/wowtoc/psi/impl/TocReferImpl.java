// This is a generated file. Not intended for manual editing.
package com.github.czy211.wowtoc.psi.impl;

import com.github.czy211.wowtoc.psi.TocRefer;
import com.github.czy211.wowtoc.psi.TocVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;

public class TocReferImpl extends TocNamedElementImpl implements TocRefer {

  public TocReferImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TocVisitor visitor) {
    visitor.visitRefer(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
      if (visitor instanceof TocVisitor) accept((TocVisitor) visitor);
    else super.accept(visitor);
  }

  @Override
  public String getFileName() {
    return TocPsiImplUtil.getFileName(this);
  }

    @Override
    public TextRange getFileNameRange() {
        return TocPsiImplUtil.getFileNameRange(this);
    }

    @Override
    public String getName() {
        return TocPsiImplUtil.getName(this);
    }

    @Override
    public PsiElement setName(String newName) {
        return TocPsiImplUtil.setName(this, newName);
    }

    @Override
    public PsiElement getNameIdentifier() {
        return TocPsiImplUtil.getNameIdentifier(this);
    }

    @Override
    public PsiReference getReference() {
        return TocPsiImplUtil.getReference(this);
    }

}
