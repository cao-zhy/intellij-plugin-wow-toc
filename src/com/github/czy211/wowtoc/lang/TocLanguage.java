package com.github.czy211.wowtoc.lang;

import com.intellij.lang.Language;

public class TocLanguage extends Language {
    public static final TocLanguage INSTANCE = new TocLanguage();

    private TocLanguage() {
        super("TOC");
    }
}
