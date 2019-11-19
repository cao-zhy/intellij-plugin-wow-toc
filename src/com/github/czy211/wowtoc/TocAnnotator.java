package com.github.czy211.wowtoc;

import com.github.czy211.wowtoc.psi.TocRefer;
import com.github.czy211.wowtoc.psi.TocTag;
import com.github.czy211.wowtoc.psi.impl.TocPsiImplUtil;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class TocAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        if (psiElement instanceof TocTag) {
            TocTag tag = (TocTag) psiElement;
            String tagName = TocPsiImplUtil.getTagName(tag);
            TextRange textRange = TocPsiImplUtil.getTagNameRange(tag);
            if (tagName != null && textRange != null && !tagName.matches(TocUtil.REGEX_TAG_NAME)) {
                annotationHolder.createErrorAnnotation(textRange, "Unsolved tag name");
            }
        } else if (psiElement instanceof TocRefer) {
            TocRefer refer = (TocRefer) psiElement;
            String fileName = TocPsiImplUtil.getFileName(refer);
            TextRange textRange = TocPsiImplUtil.getFileNameRange(refer);
            Set<String> fileNames = TocUtil.getFileNames(psiElement);
            if (fileName != null && textRange != null) {
                if (!fileName.matches(TocUtil.REGEX_FILE_NAME)) {
                    annotationHolder.createErrorAnnotation(textRange, "Unsolved file type");
                } else if (!fileNames.contains(fileName)) {
                    Annotation annotation = annotationHolder.createInfoAnnotation(textRange, "Unsolved file");
                    annotation.setTextAttributes(DefaultLanguageHighlighterColors.LABEL);
                }
            }
        }
    }
}
