package com.github.czy211.wowtoc;

import com.github.czy211.wowtoc.psi.TocTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class TocAnnotator implements Annotator {
    public static final String REGEX_LOCALIZATION = "enUS|enGB|frFR|deDE|esES|esMX|itIT|ptBR|ruRU|koKR|zhTW|zhCN";
    public static final String REGEX_TAG_NAME = "Interface|Title(-(" + REGEX_LOCALIZATION + "))?|Notes(-("
            + REGEX_LOCALIZATION + "))?|RequiredDeps|Dependencies|Dep[^: \\n\\f]+|OptionalDeps|LoadOnDemand|LoadWith|"
            + "LoadManagers|SavedVariables|SavedVariablesPerCharacter|DefaultState|Secure|Author|Version|"
            + "[Xx]-[^: \\n\\f]+";
    public static final String REGEX_FILE_NAME = ".*\\.([lL][uU][aA]|[xX][mM][lL])$";

    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        ASTNode tagNameNode = psiElement.getNode().findChildByType(TocTypes.TAG_NAME);
        if (tagNameNode != null) {
            String tagName = tagNameNode.getText().replaceAll("\\\\ ", " ");
            if (!tagName.matches(REGEX_TAG_NAME)) {
                annotationHolder.createErrorAnnotation(tagNameNode.getTextRange(), "Unsolved tag name");
            }
        }
        ASTNode fileNameNode = psiElement.getNode().findChildByType(TocTypes.FILE_NAME);
        if (fileNameNode != null) {
            String fileName = fileNameNode.getText().replaceAll("\\\\ ", " ");
            Set<String> fileNames = getFileNames(psiElement);
            if (!fileName.matches(REGEX_FILE_NAME)) {
                annotationHolder.createErrorAnnotation(fileNameNode.getTextRange(), "Unsolved file type");
            } else if (!fileNames.contains(fileName)) {
                Annotation annotation = annotationHolder.createInfoAnnotation(fileNameNode.getTextRange(),
                        "Unsolved file");
                annotation.setTextAttributes(DefaultLanguageHighlighterColors.LABEL);
            }
        }
    }

    private Set<String> getFileNames(PsiElement psiElement) {
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
