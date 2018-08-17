package com.zz.encrypter.utils.basicwork;

import java.io.File;

public class DeleteFiles {
    //删除文件夹
    public static void delFolder(String folderPath) {
        try {
            delAllFilesUnderFolder(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean delAllFilesUnderFolder(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFilesUnderFolder(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }
    public static void delAllEmptyFolderUnder(String rootFolder){
        File folder=new File(rootFolder);
        delEmptyFolder(folder);
    }
    private static void delEmptyFolder(File folder){
        if(folder.isDirectory()){//如果是文件夹才处理
            File[] files=folder.listFiles();//得到子文件列表
            if(files!=null&&files.length>0){//如果列表不为空
                for(File file:files){//遍历所有子文件
                    delEmptyFolder(file);
                    File[] tmp=file.listFiles();//列出下属文件
                    if(tmp==null||tmp.length==0)//如果这个文件夹空了就删掉它
                        if(file.isDirectory())
                            file.delete();
                }
            }
        }
    }
}

