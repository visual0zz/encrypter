package com.zz.encrypter.utils.filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;

import static java.lang.System.exit;

public class Bash {
    /**
     * @apiNote 函数行为和linux指令touch基本一致，唯一区别是可以自动建立沿途的所有文件夹。
     * @param file 要新建的文件路径，
     * @throws IOException 文件系统操作异常
     */
    public static void touch(File file) throws IOException {//确保一个文件存在，如果不存在就新建
        if(file.exists())return;
        else {
            File parent=file.getParentFile();
            if(parent==null||parent.isDirectory()){
                file.createNewFile();
            }else{
                mkdir(parent);
                file.createNewFile();
            }
        }
    }
    public static void mkdir(File file) throws FileAlreadyExistsException, AccessDeniedException {
        if(file.exists()){
            if(file.isDirectory())
                return;//都已经存在了还干毛。
            else
                throw new FileAlreadyExistsException("文件 "+file.getAbsolutePath()+" 已经存在");
        }

        File parent=file.getParentFile();//得到上级文件夹

        if(parent==null||parent.isDirectory()){//如果上级文件夹情况允许，就建立本文件夹
        }else {
            mkdir(parent);//如果不允许就先建立上级文件夹再建立本文件夹。
        }
        if(!file.mkdir()) throw new AccessDeniedException("无法操作"+file.getAbsolutePath());


    }

    /**
     * @apiNote 用于文件地址变换，将一个文件相对于某个文件夹的路径变换到相对于另一个文件夹。\
     * 比如递归复制文件夹的时候需要把每个源文件夹的文件路径变换为目标文件夹内的对应路径。
     * @param file 要变换路径的文件
     * @param originBase 原始的路径基准
     * @param targetBase 新的路径基准
     * @return 新的路径
     */
    public static File changeBase(File file, File originBase, File targetBase) {//将一个文件路径的rootFrom部分替换为rootTo
        String filepath=null,root1=null,root2=null;
        try {
            filepath = "*+-.,<>?'\";:]}[{|\\=+-_\0/0)9(8*&^%$#@!`~////\\\\\\\\" + file.getCanonicalPath();//用一个不可能在文件路径中出现的字符串确保替换只发生在路径开始。
            root1 = "*+-.,<>?'\";:]}[{|\\=+-_\0/0)9(8*&^%$#@!`~////\\\\\\\\" + originBase.getCanonicalPath();
            root2 = targetBase.getCanonicalPath();
        }catch (IOException e){
            e.printStackTrace();
            exit(1);
        }
        return new File(filepath.replace(root1, root2));
    }
}
