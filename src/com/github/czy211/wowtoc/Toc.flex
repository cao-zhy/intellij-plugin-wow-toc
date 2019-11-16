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
SEPARATOR=:
END_OF_LINE_COMMENT=("#"([^#].*|##.*))|([ ]+#.*)
TAG_PREFIX="##"
LOCALIZED=enUS|enGB|frFR|deDE|esES|esMX|itIT|ptBR|ruRU|koKR|zhTW|zhCN
TAG_NAME=Interface|Title(-{LOCALIZED})?|Notes(-{LOCALIZED})?|Description(-{LOCALIZED})?|RequiredDeps|Dependencies|Dep[^:\r\n]+|OptionalDeps|LoadOnDemand|LoadWith|LoadManagers|SavedVariablesPerCharacter|SavedVariables|DefaultState|Secure|Author|Version|[Xx]-[^:\r\n]+
FIRST_TAG_VALUE_CHARACTER=[^\r\n ]
TAG_VALUE_CHARACTER=[^\r\n]
FILE_NAME=[^#].*\.([lL][uU][aA]|[xX][mM][lL])

%state WAITING_VALUE

%%

<YYINITIAL> {END_OF_LINE_COMMENT} {yybegin(YYINITIAL); return TocTypes.COMMENT;}
<YYINITIAL> {TAG_PREFIX} {yybegin(YYINITIAL); return TocTypes.TAG_PREFIX;}
<YYINITIAL> {WHITE_SPACE}+ {yybegin(YYINITIAL); return TokenType.WHITE_SPACE;}
<YYINITIAL> {TAG_NAME} {yybegin(YYINITIAL); return TocTypes.TAG_NAME;}
<YYINITIAL> {SEPARATOR} {yybegin(WAITING_VALUE); return TocTypes.SEPARATOR;}
<YYINITIAL> {FILE_NAME} {yybegin(WAITING_VALUE); return TocTypes.FILE_NAME;}
<WAITING_VALUE> {WHITE_SPACE}+ {yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE;}
<WAITING_VALUE> {FIRST_TAG_VALUE_CHARACTER}{TAG_VALUE_CHARACTER}* {yybegin(WAITING_VALUE); return TocTypes.TAG_VALUE;}
{CRLF}+ {yybegin(YYINITIAL); return TokenType.WHITE_SPACE;}
[^] {return TokenType.BAD_CHARACTER;}
