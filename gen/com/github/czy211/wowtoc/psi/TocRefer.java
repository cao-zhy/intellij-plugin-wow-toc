// This is a generated file. Not intended for manual editing.
package com.github.czy211.wowtoc.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface TocRefer extends TocNamedElement {

    String getFileName();

    TextRange getFileNameRange();

    String getName();

    PsiElement setName(String newName);

    PsiElement getNameIdentifier();

    PsiReference getReference();

}
