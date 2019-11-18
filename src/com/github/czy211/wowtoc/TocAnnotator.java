package com.github.czy211.wowtoc;

import com.github.czy211.wowtoc.psi.TocTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class TocAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        ASTNode tagNameNode = psiElement.getNode().findChildByType(TocTypes.TAG_NAME);
        if (tagNameNode != null) {
            String tagName = tagNameNode.getText().replaceAll("\\\\ ", " ");
            if (!tagName.matches(TocUtil.REGEX_TAG_NAME)) {
                annotationHolder.createErrorAnnotation(tagNameNode.getTextRange(), "Unsolved tag name");
            }
        }
        ASTNode fileNameNode = psiElement.getNode().findChildByType(TocTypes.FILE_NAME);
        if (fileNameNode != null) {
            String fileName = fileNameNode.getText().replaceAll("\\\\ ", " ");
            Set<String> fileNames = TocUtil.getFileNames(psiElement);
            if (!fileName.matches(TocUtil.REGEX_FILE_NAME)) {
                annotationHolder.createErrorAnnotation(fileNameNode.getTextRange(), "Unsolved file type");
            } else if (!fileNames.contains(fileName)) {
                Annotation annotation = annotationHolder.createInfoAnnotation(fileNameNode.getTextRange(),
                        "Unsolved file");
                annotation.setTextAttributes(DefaultLanguageHighlighterColors.LABEL);
            }
        }
    }
}
