package com.github.czy211.wowtoc;

import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import java.util.HashSet;
import java.util.Set;

public class TocUtil {
    public static final String[] TAG_NAMES = {"Interface", "Title", "Notes", "Description", "RequiredDeps",
            "Dep", "OptionalDeps", "LoadOnDemand", "LoadWith", "LoadManagers", "SavedVariablesPerCharacter",
            "SavedVariables", "DefaultState", "Secure", "Author", "Version"};
    public static final String[] LOCALIZATION = {"enUS", "enGB", "frFR", "deDE", "esES", "esMX", "itIT", "ptBR", "ruRU",
            "koKR", "zhTW", "zhCN"};

    public static final String REGEX_LOCALIZATION = "enUS|enGB|frFR|deDE|esES|esMX|itIT|ptBR|ruRU|koKR|zhTW|zhCN";
    public static final String REGEX_TAG_NAME = "Interface|Title(-(" + REGEX_LOCALIZATION + "))?|Notes(-("
            + REGEX_LOCALIZATION + "))?|RequiredDeps|Dependencies|Dep[^: \\n\\f]+|OptionalDeps|LoadOnDemand|LoadWith|"
            + "LoadManagers|SavedVariables|SavedVariablesPerCharacter|DefaultState|Secure|Author|Version|"
            + "[Xx]-[^: \\n\\f]+";
    public static final String REGEX_FILE_NAME = ".*\\.([lL][uU][aA]|[xX][mM][lL])$";

    public static Set<String> getFileNames(PsiElement psiElement) {
        Set<String> result = new HashSet<>();
        PsiDirectory directory = psiElement.getContainingFile().getParent();
        if (directory != null) {
            PsiFile[] psiFiles = psiElement.getContainingFile().getParent().getFiles();
            for (PsiFile file : psiFiles) {
                String name = file.getName();
                if (name.matches(REGEX_FILE_NAME)) {
                    result.add(name);
                }
            }
        }
        return result;
    }
}
