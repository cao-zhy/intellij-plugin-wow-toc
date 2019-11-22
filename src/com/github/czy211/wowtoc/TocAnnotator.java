package com.github.czy211.wowtoc;

import com.github.czy211.wowtoc.psi.TocRefer;
import com.github.czy211.wowtoc.psi.TocTag;
import com.github.czy211.wowtoc.psi.impl.TocPsiImplUtil;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class TocAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        if (psiElement instanceof TocTag) {
            TocTag tag = (TocTag) psiElement;
            TextRange range = TocPsiImplUtil.getKeyRange(tag);
            if (range != null) {
                String tagName = TocPsiImplUtil.getTagName(tag);
                if (tagName == null || !tagName.matches(TocUtil.REGEX_TAG_NAME)) {
                    annotationHolder.createWarningAnnotation(range, "Unresolved tag name");
                }
            }
        } else if (psiElement instanceof TocRefer) {
            TocRefer refer = (TocRefer) psiElement;
            String fileName = TocPsiImplUtil.getFileName(refer);
            Set<String> fileNames = TocUtil.getFileNames(psiElement);
            if (fileName != null) {
                if (!fileName.matches(TocUtil.REGEX_FILE_NAME)) {
                    annotationHolder.createErrorAnnotation(psiElement, "Unresolved file type");
                } else if (!fileNames.contains(fileName)) {
                    annotationHolder.createErrorAnnotation(psiElement, "Unresolved file");
                }
            }
        }
    }
}
