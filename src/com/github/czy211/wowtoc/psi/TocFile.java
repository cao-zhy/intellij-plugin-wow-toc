package com.github.czy211.wowtoc.psi;

import com.github.czy211.wowtoc.TocFileType;
import com.github.czy211.wowtoc.TocLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TocFile extends PsiFileBase {
    public TocFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, TocLanguage.INSTANCE);
    }

    @Nullable
    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }

    @Override
    public String toString() {
        return "Toc File";
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return TocFileType.INSTANCE;
    }
}
