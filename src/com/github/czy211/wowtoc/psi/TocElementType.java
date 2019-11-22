package com.github.czy211.wowtoc.psi;

import com.github.czy211.wowtoc.lang.TocLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class TocElementType extends IElementType {
    public TocElementType(@NotNull String debugName) {
        super(debugName, TocLanguage.INSTANCE);
    }
}
