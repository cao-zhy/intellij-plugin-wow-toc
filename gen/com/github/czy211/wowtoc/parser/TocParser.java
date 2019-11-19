// This is a generated file. Not intended for manual editing.
package com.github.czy211.wowtoc.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;

import static com.github.czy211.wowtoc.psi.TocTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TocParser implements PsiParser, LightPsiParser {

    static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
        return tocFile(b, l + 1);
    }

    /* ********************************************************** */
    // tag|refer|COMMENT|CRLF
    static boolean item_(PsiBuilder b, int l) {
        if (!recursion_guard_(b, l, "item_")) return false;
        boolean r;
        r = tag(b, l + 1);
        if (!r) r = refer(b, l + 1);
        if (!r) r = consumeToken(b, COMMENT);
        if (!r) r = consumeToken(b, CRLF);
        return r;
    }

    /* ********************************************************** */
    // FILE_NAME
    public static boolean refer(PsiBuilder b, int l) {
        if (!recursion_guard_(b, l, "refer")) return false;
        if (!nextTokenIs(b, FILE_NAME)) return false;
        boolean r;
        Marker m = enter_section_(b);
        r = consumeToken(b, FILE_NAME);
        exit_section_(b, m, REFER, r);
        return r;
    }

    /* ********************************************************** */
    // TAG_PREFIX TAG_NAME SEPARATOR TAG_VALUE
    public static boolean tag(PsiBuilder b, int l) {
        if (!recursion_guard_(b, l, "tag")) return false;
        if (!nextTokenIs(b, TAG_PREFIX)) return false;
        boolean r;
        Marker m = enter_section_(b);
        r = consumeTokens(b, 0, TAG_PREFIX, TAG_NAME, SEPARATOR, TAG_VALUE);
        exit_section_(b, m, TAG, r);
        return r;
    }

    /* ********************************************************** */
    // item_*
    static boolean tocFile(PsiBuilder b, int l) {
        if (!recursion_guard_(b, l, "tocFile")) return false;
        while (true) {
            int c = current_position_(b);
            if (!item_(b, l + 1)) break;
            if (!empty_element_parsed_guard_(b, "tocFile", c)) break;
        }
        return true;
    }

    public ASTNode parse(IElementType t, PsiBuilder b) {
        parseLight(t, b);
        return b.getTreeBuilt();
    }

    public void parseLight(IElementType t, PsiBuilder b) {
        boolean r;
        b = adapt_builder_(t, b, this, null);
        Marker m = enter_section_(b, 0, _COLLAPSE_, null);
        r = parse_root_(t, b);
        exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
    }

    protected boolean parse_root_(IElementType t, PsiBuilder b) {
        return parse_root_(t, b, 0);
    }

}
