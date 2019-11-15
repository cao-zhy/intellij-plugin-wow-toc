package com.github.czy211.wowtoc;

import com.intellij.lang.Language;

public class TocLanguage extends Language {
    public static final TocLanguage INSTANCE = new TocLanguage();

    private TocLanguage() {
        super("Toc");
    }
}
