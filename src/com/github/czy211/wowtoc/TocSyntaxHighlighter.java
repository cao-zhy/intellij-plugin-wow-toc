package com.github.czy211.wowtoc;

import com.github.czy211.wowtoc.psi.TocTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class TocSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey TAG_NAME = TextAttributesKey.createTextAttributesKey("TOC_TAG_NAME",
            DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey SEPARATOR = TextAttributesKey.createTextAttributesKey("TOC_SEPARATOR",
            DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey TAG_VALUE = TextAttributesKey.createTextAttributesKey("TOC_TAG_VALUE",
            DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey REFER = TextAttributesKey.createTextAttributesKey("TOC_REFER",
            DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey("TOC_COMMENT",
            DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("TOC_BAD_CHAR",
            HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] TAG_NAME_KEYS = new TextAttributesKey[]{TAG_NAME};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] TAG_VALUE_KEYS = new TextAttributesKey[]{TAG_VALUE};
    private static final TextAttributesKey[] REFER_KEYS = new TextAttributesKey[]{REFER};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new TocLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType iElementType) {
        if (iElementType.equals(TocTypes.TAG_PREFIX) || iElementType.equals(TocTypes.TAG_NAME)) {
            return TAG_NAME_KEYS;
        } else if (iElementType.equals(TocTypes.SEPARATOR)) {
            return SEPARATOR_KEYS;
        } else if (iElementType.equals(TocTypes.TAG_VALUE)) {
            return TAG_VALUE_KEYS;
        } else if (iElementType.equals(TocTypes.FILE_NAME)) {
            return REFER_KEYS;
        } else if (iElementType.equals(TocTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (iElementType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
