package com.github.czy211.wowtoc.color;

import com.github.czy211.wowtoc.highlighter.TocSyntaxHighlighter;
import com.github.czy211.wowtoc.icon.TocIcon;
import com.github.czy211.wowtoc.util.TocUtil;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class TocColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Tag name", TocSyntaxHighlighter.TAG_NAME),
            new AttributesDescriptor("Separator", TocSyntaxHighlighter.SEPARATOR),
            new AttributesDescriptor("Tag value", TocSyntaxHighlighter.TAG_VALUE),
            new AttributesDescriptor("File name", TocSyntaxHighlighter.REFER),
            new AttributesDescriptor("Comment", TocSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("Bad character", TocSyntaxHighlighter.BAD_CHARACTER),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return TocIcon.ICON;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new TocSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return TocUtil.DEMO_TEXT;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "TOC";
    }
}
