package com.zz.encrypter.tasks;

import com.zz.encrypter.service.FileEncrypter;
import com.zz.encrypter.utils.filesystem.Bash;
import com.zz.encrypter.utils.filesystem.FileTreeTravelManager;
import com.zz.encrypter.utils.filesystem.FileTreeTraveler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class DecryptTask {
    public void decrypt(File source, File target, String password) throws Exception {
    if(source.isFile()&&target.isFile()) decryptOne2One(source,target,password);
    else if(source.isDirectory()&&target.isDirectory()) decryptDir2Dir(source,target,password);
    else if(source.isDirectory()&&!target.exists()) decryptDir2Dir(source,target,password);
    else throw new Exception("参数错误，源文件不存在或者目标不正确。");
}

    private void decryptOne2One(File source, File target, String password) throws Exception {//一个文件对一个文件的加密
        FileEncrypter.decrypt(new FileInputStream(source),new FileOutputStream(target),password.getBytes("utf-8"));
    }
    private void decryptDir2Dir(File source, File target, String password) throws Exception {//文件夹到文件夹的加密
        FileTreeTravelManager travelManager=new FileTreeTravelManager(source);
        if(!target.exists()) Bash.mkdir(target);
        travelManager.travel(new FileTreeTraveler() {
            @Override
            public boolean shouldTravelIntoFolder(int layer_level, File folder) { return true; }
            @Override
            public void travelFolder(int layer_level, File folder, List<String> result) { }

            @Override
            public void travelFile(int layer_level, File file, List<String> result) throws Exception {
                FileEncrypter.decrypt(
                        new FileInputStream(file),
                        new FileOutputStream(Bash.changeBase(file,source,target)),
                        password.getBytes("utf-8"));
            }
        });
    }
}
