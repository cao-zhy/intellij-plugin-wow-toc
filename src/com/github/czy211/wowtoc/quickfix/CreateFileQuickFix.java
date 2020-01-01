package com.github.czy211.wowtoc.quickfix;

import com.github.czy211.wowtoc.util.TocUtil;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class CreateFileQuickFix extends BaseIntentionAction {
    private String fileName;

    public CreateFileQuickFix(String fileName) {
        this.fileName = fileName;
    }

    @NotNull
    @Override
    public String getText() {
        return "Create file " + fileName;
    }

    @Nls(capitalization = Nls.Capitalization.Sentence)
    @NotNull
    @Override
    public String getFamilyName() {
        return getText();
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile) throws IncorrectOperationException {
        ApplicationManager.getApplication().invokeLater(() -> {
            WriteCommandAction.writeCommandAction(project).run(() -> {
                PsiDirectory directory = TocUtil.getDirectory(psiFile.getContainingDirectory(), fileName, true);
                VirtualFile file = directory.createFile(TocUtil.getFileName(fileName)).getVirtualFile();
                FileEditorManager.getInstance(project).openFile(file, true);
            });
        });
    }
}
