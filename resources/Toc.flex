package com.github.czy211.wowtoc.lexer;

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
// 注释以 “#” 开头，如果大于或等于 2 个字符，则第二个字符不能是 “#”
END_OF_LINE_COMMENT=#([^#\n\f][^\n\f]*)?
TAG_PREFIX=##
// 标签名不能有 “:”，中间可以有空格，不匹配开头和结尾的空格
TAG_NAME=[^: \n\f]+|[^: \n\f][^:\n\f]*[^: \n\f]
SEPARATOR=:
// 标签值中间可以有空格，不匹配开头和结尾的空格
TAG_VALUE=[^ \n\f]+|[^ \n\f][^\n\f]*[^ \n\f]
// 文件名不能以 “#” 开头，不匹配结尾的空格
FILE_NAME=[ ]+|[^#\n\f]([^ \n\f]*|[^\n\f]*[^ \n\f])

%state WAITING_CRLF
%state WAITING_KEY
%state WAITING_VALUE

%%

// 初始状态时，可以匹配到换行符、注释、标签前缀或文件名
<YYINITIAL> {CRLF}+ {yybegin(YYINITIAL); return TokenType.WHITE_SPACE;}
<YYINITIAL> {END_OF_LINE_COMMENT} {yybegin(WAITING_CRLF); return TocTypes.COMMENT;}
<YYINITIAL> {TAG_PREFIX} {yybegin(WAITING_KEY); return TocTypes.TAG_PREFIX;}
<YYINITIAL> {FILE_NAME} {yybegin(WAITING_CRLF); return TocTypes.FILE_NAME;}
// 等待标签名或分隔符状态时，可以匹配到空白符、标签名、分隔符或空白换行符
<WAITING_KEY> {WHITE_SPACE}+ {yybegin(WAITING_KEY); return TokenType.WHITE_SPACE;}
<WAITING_KEY> {TAG_NAME} {yybegin(WAITING_KEY); return TocTypes.TAG_NAME;}
<WAITING_KEY> {WHITE_SPACE}*{CRLF}+ {yybegin(YYINITIAL); return TokenType.WHITE_SPACE;}
<WAITING_KEY> {SEPARATOR} {yybegin(WAITING_VALUE); return TocTypes.SEPARATOR;}
// 等待标签值状态时，可以匹配到空白符、标签值或空白换行符
<WAITING_VALUE> {WHITE_SPACE}+ {yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE;}
<WAITING_VALUE> {TAG_VALUE} {yybegin(WAITING_CRLF); return TocTypes.TAG_VALUE;}
<WAITING_VALUE> {WHITE_SPACE}*{CRLF}+ {yybegin(YYINITIAL); return TokenType.WHITE_SPACE;}
// 等待换行符时，可以匹配空白换行符
<WAITING_CRLF> {WHITE_SPACE}*{CRLF}+ {yybegin(YYINITIAL); return TokenType.WHITE_SPACE;}
[^] {return TokenType.BAD_CHARACTER;}
