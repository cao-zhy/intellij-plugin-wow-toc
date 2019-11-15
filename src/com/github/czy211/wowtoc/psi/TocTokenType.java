package com.github.czy211.wowtoc.psi;

import com.github.czy211.wowtoc.TocLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class TocTokenType extends IElementType {
    public TocTokenType(@NotNull String debugName) {
        super(debugName, TocLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "TocTokenType." + super.toString();
    }
}
