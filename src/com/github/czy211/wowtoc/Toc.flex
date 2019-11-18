package com.github.czy211.wowtoc;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.czy211.wowtoc.psi.TocTypes;
import com.intellij.psi.TokenType;

%%

%class TocLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=[\r|\n|\r\n]
WHITE_SPACE=[ ]
END_OF_LINE_COMMENT=[ ]+#[^\n\f]*|#(##[^\n\f]*|[^#\n\f][^\n\f]*)|#
TAG_PREFIX=##
TAG_NAME=[^: \n\f]+
SEPARATOR=:
TAG_VALUE=[^ \n\f]+|[^ \n\f][^\n\f]*[^ \n\f]
FILE_NAME=[ ]*[^ #\n\f]([^ \n\f]*|[^\n\f]*[^ \n\f])

%state WAITING_TAG_NAME
%state WAITING_SEPARATOR
%state WAITING_TAG_VALUE
%state WAITING_CRLF

%%

<YYINITIAL> {CRLF}+ {yybegin(YYINITIAL); return TokenType.WHITE_SPACE;}
<YYINITIAL> {END_OF_LINE_COMMENT} {yybegin(WAITING_CRLF); return TocTypes.COMMENT;}
<YYINITIAL> {TAG_PREFIX} {yybegin(WAITING_TAG_NAME); return TocTypes.TAG_PREFIX;}
<YYINITIAL> {FILE_NAME} {yybegin(WAITING_CRLF); return TocTypes.FILE_NAME;}
<WAITING_TAG_NAME> {WHITE_SPACE}+ {yybegin(WAITING_TAG_NAME); return TokenType.WHITE_SPACE;}
<WAITING_TAG_NAME> {TAG_NAME} {yybegin(WAITING_SEPARATOR); return TocTypes.TAG_NAME;}
<WAITING_SEPARATOR> {WHITE_SPACE}+ {yybegin(WAITING_SEPARATOR); return TokenType.WHITE_SPACE;}
<WAITING_SEPARATOR> {SEPARATOR} {yybegin(WAITING_TAG_VALUE); return TocTypes.SEPARATOR;}
<WAITING_TAG_VALUE> {WHITE_SPACE}+ {yybegin(WAITING_TAG_VALUE); return TokenType.WHITE_SPACE;}
<WAITING_TAG_VALUE> {TAG_VALUE} {yybegin(WAITING_CRLF); return TocTypes.TAG_VALUE;}
<WAITING_CRLF> {WHITE_SPACE}*{CRLF}+ {yybegin(YYINITIAL); return TokenType.WHITE_SPACE;}
[^] {return TokenType.BAD_CHARACTER;}
