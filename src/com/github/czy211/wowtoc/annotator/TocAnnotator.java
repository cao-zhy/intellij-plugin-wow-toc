package com.github.czy211.wowtoc.annotator;

import com.github.czy211.wowtoc.constant.Constants;
import com.github.czy211.wowtoc.psi.TocRefer;
import com.github.czy211.wowtoc.psi.TocTag;
import com.github.czy211.wowtoc.psi.impl.TocPsiImplUtil;
import com.github.czy211.wowtoc.quickfix.CreateFileQuickFix;
import com.github.czy211.wowtoc.quickfix.RemoveReferQuickFix;
import com.github.czy211.wowtoc.util.TocUtil;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TocAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        if (psiElement instanceof TocTag) {
            // 没有标签名或标签名不是官方标签名，则显示警告等级的附注
            TocTag tag = (TocTag) psiElement;
            TextRange range = TocPsiImplUtil.getKeyRange(tag);
            if (range != null) {
                String tagName = TocPsiImplUtil.getTagName(tag);
                // 忽略大小写匹配标签名的正则表达式
                Pattern pattern = Pattern.compile(Constants.REGEX_TAG_NAME, Pattern.CASE_INSENSITIVE);
                if (tagName == null || !pattern.matcher(tagName).matches()) {
                    annotationHolder.createWarningAnnotation(range, "Unresolved tag name");
                }
            }
        } else if (psiElement instanceof TocRefer) {
            TocRefer refer = (TocRefer) psiElement;
            String fileName = TocPsiImplUtil.getFileName(refer);
            Set<String> fileNames = TocUtil.getFileNames(psiElement);
            if (fileName != null) {
                if (!fileName.matches(Constants.REGEX_FILE_NAME)) {
                    // 文件名不是以 lua 或 xml 结尾，显示错误等级的附注，并添加移除该引用的快速修复选项
                    annotationHolder.createErrorAnnotation(psiElement, "Unresolved file type").registerFix(
                            new RemoveReferQuickFix(psiElement));
                    // fileNames 中的分隔符是 “\”，需要把 fileName 中的所有 “/” 替换为 “\”
                } else if (!fileNames.contains(fileName.replaceAll("/", Matcher.quoteReplacement("\\")))) {
                    // 未找到引用文件，显示错误等级的附注
                    Annotation annotation = annotationHolder.createErrorAnnotation(psiElement, "Unresolved file");
                    // 添加创建该引用文件的快速修复选项
                    annotation.registerFix(new CreateFileQuickFix(fileName));
                    // 添加移除该引用的快速修复选项
                    annotation.registerFix(new RemoveReferQuickFix(psiElement));
                }
            }
        }
    }
}
