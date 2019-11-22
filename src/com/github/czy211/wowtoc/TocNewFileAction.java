package com.github.czy211.wowtoc;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NotNull;

public class TocNewFileAction extends CreateFileFromTemplateAction implements DumbAware {

    public TocNewFileAction() {
        super("TOC File", "Create a new TOC file", TocIcon.ICON);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory psiDirectory,
                               CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("New TOC File");
        builder.addKind("Source file", TocIcon.ICON, "TOC File.toc");
    }

    @Override
    protected String getActionName(PsiDirectory psiDirectory, @NotNull String s, String s1) {
        return "NewTocFile";
    }
}
