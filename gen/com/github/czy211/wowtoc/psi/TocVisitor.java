// This is a generated file. Not intended for manual editing.
package com.github.czy211.wowtoc.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class TocVisitor extends PsiElementVisitor {

    public void visitRefer(@NotNull TocRefer o) {
    visitPsiElement(o);
  }

  public void visitTag(@NotNull TocTag o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
