package com.github.czy211.wowtoc.filetype;

import com.github.czy211.wowtoc.icon.TocIcon;
import com.github.czy211.wowtoc.lang.TocLanguage;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TocFileType extends LanguageFileType {
    public static final TocFileType INSTANCE = new TocFileType();

    private TocFileType() {
        super(TocLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "TOC File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "TOC language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "toc";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return TocIcon.ICON;
    }
}
