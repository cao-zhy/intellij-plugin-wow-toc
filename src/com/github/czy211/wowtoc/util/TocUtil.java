package com.github.czy211.wowtoc.util;

import com.github.czy211.wowtoc.constant.Constants;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import java.util.HashSet;
import java.util.Set;

public class TocUtil {
    /**
     * 获取 PSI 元素所在文件夹及其子文件夹下的所有文件名
     *
     * @param psiElement PSI 元素
     * @return 文件名集合
     */
    public static Set<String> getFileNames(PsiElement psiElement) {
        Set<String> result = new HashSet<>();
        PsiDirectory baseDirectory = psiElement.getContainingFile().getParent();
        if (baseDirectory != null) {
            getFileNames(baseDirectory, "", result);
        }
        return result;
    }

    /**
     * 把文件夹及其子文件夹下的所有文件名添加到结果集合中
     *
     * @param baseDirectory 根文件夹
     * @param dirName 当前遍历的文件夹名
     * @param result 结果集合
     */
    public static void getFileNames(PsiDirectory baseDirectory, String dirName, Set<String> result) {
        PsiFile[] psiFiles = baseDirectory.getFiles();
        for (PsiFile file : psiFiles) {
            String name = file.getName();
            if (name.matches(Constants.REGEX_FILE_NAME)) {
                result.add(dirName + name);
            }
        }
        PsiDirectory[] psiDirectories = baseDirectory.getSubdirectories();
        for (PsiDirectory directory : psiDirectories) {
            String name = directory.getName() + "\\";
            getFileNames(directory, dirName + name, result);
        }
    }

    /**
     * 获取完整文件名中的 PSI 文件夹
     *
     * @param baseDirectory 根文件夹
     * @param fileName 完整文件名
     * @param createDir 使用创建不存在的文件夹
     * @return PSI 文件夹
     */
    public static PsiDirectory getDirectory(PsiDirectory baseDirectory, String fileName, boolean createDir) {
        PsiDirectory result = baseDirectory;
        String[] array = fileName.split("\\\\");
        for (int i = 0; i < array.length - 1; i++) {
            if (result != null) {
                PsiDirectory directory = result.findSubdirectory(array[i]);
                if (directory == null && createDir) {
                    result = result.createSubdirectory(array[i]);
                } else {
                    result = directory;
                }
            }
        }
        return result;
    }

    /**
     * 获取不包含文件夹名前缀的文件名
     *
     * @param fileName 完整文件名
     * @return 不包含文件夹名前缀的文件名
     */
    public static String getFileName(String fileName) {
        String[] array = fileName.split("\\\\");
        return array[array.length - 1];
    }
}
