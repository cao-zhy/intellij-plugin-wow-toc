package com.github.czy211.wowtoc.psi;

import com.github.czy211.wowtoc.TocFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;

public class TocElementFactory {
    public static TocRefer createRefer(Project project, String name) {
        final TocFile file = createFile(project, name);
        return (TocRefer) file.getFirstChild();
    }

    private static TocFile createFile(Project project, String text) {
        String name = "dummy.toc";
        return (TocFile) PsiFileFactory.getInstance(project).createFileFromText(name, TocFileType.INSTANCE, text);
    }
}
