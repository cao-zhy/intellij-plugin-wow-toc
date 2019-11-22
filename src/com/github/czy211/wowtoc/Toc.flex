package com.github.czy211.wowtoc;

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
END_OF_LINE_COMMENT=#([^#\n\f][^\n\f]*)?
TAG_PREFIX=##
TAG_NAME=[^: \n\f]+|[^: \n\f][^:\n\f]*[^: \n\f]
SEPARATOR=:
TAG_VALUE=[^ \n\f]+|[^ \n\f][^\n\f]*[^ \n\f]
FILE_NAME=[ ]+|[^#\n\f]([^ \n\f]*|[^\n\f]*[^ \n\f])

%state WAITING_CRLF
%state WAITING_KEY
%state WAITING_VALUE

%%

<YYINITIAL> {CRLF}+ {yybegin(YYINITIAL); return TokenType.WHITE_SPACE;}
<YYINITIAL> {END_OF_LINE_COMMENT} {yybegin(WAITING_CRLF); return TocTypes.COMMENT;}
<YYINITIAL> {TAG_PREFIX} {yybegin(WAITING_KEY); return TocTypes.TAG_PREFIX;}
<YYINITIAL> {FILE_NAME} {yybegin(WAITING_CRLF); return TocTypes.FILE_NAME;}
<WAITING_KEY> {WHITE_SPACE}+ {yybegin(WAITING_KEY); return TokenType.WHITE_SPACE;}
<WAITING_KEY> {TAG_NAME} {yybegin(WAITING_KEY); return TocTypes.TAG_NAME;}
<WAITING_KEY> {WHITE_SPACE}*{CRLF}+ {yybegin(YYINITIAL); return TokenType.WHITE_SPACE;}
<WAITING_KEY> {SEPARATOR} {yybegin(WAITING_VALUE); return TocTypes.SEPARATOR;}
<WAITING_VALUE> {WHITE_SPACE}+ {yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE;}
<WAITING_VALUE> {TAG_VALUE} {yybegin(WAITING_CRLF); return TocTypes.TAG_VALUE;}
<WAITING_VALUE> {WHITE_SPACE}*{CRLF}+ {yybegin(YYINITIAL); return TokenType.WHITE_SPACE;}
<WAITING_CRLF> {WHITE_SPACE}*{CRLF}+ {yybegin(YYINITIAL); return TokenType.WHITE_SPACE;}
[^] {return TokenType.BAD_CHARACTER;}
