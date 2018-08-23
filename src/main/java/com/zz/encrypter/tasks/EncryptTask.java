package com.zz.encrypter.tasks;

import com.zz.encrypter.service.FileEncrypter;
import com.zz.encrypter.utils.filesystem.Bash;
import com.zz.encrypter.utils.filesystem.FileTreeTravelManager;
import com.zz.encrypter.utils.filesystem.FileTreeTraveler;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public class EncryptTask {
    public void encrypt(File source, File target, String password) throws Exception {
        if(source.isFile()&&target.isFile())encryptOne2One(source,target,password);
        else if(source.isDirectory()&&target.isDirectory())encryptDir2Dir(source,target,password);
        else if(source.isDirectory()&&!target.exists())encryptDir2Dir(source,target,password);
        else throw new Exception("参数错误，源文件不存在或者目标不正确。");
    }

    private void encryptOne2One(File source, File target, String password) throws Exception {//一个文件对一个文件的加密
        FileEncrypter.encrypt(new FileInputStream(source),new FileOutputStream(target),password.getBytes(),source.length());
    }
    private void encryptDir2Dir(File source, File target, String password) throws Exception {//文件夹到文件夹的加密
        FileTreeTravelManager travelManager=new FileTreeTravelManager(source);
        if(!target.exists()) Bash.mkdir(target);
        travelManager.travel(new FileTreeTraveler() {
            @Override
            public boolean shouldTravelIntoFolder(int layer_level, File folder) { return true; }
            @Override
            public void travelFolder(int layer_level, File folder, List<String> result) { }

            @Override
            public void travelFile(int layer_level, File file, List<String> result) throws Exception {
                FileEncrypter.encrypt(
                        new FileInputStream(file),
                        new FileOutputStream(Bash.changeBase(file,source,target)),
                        password.getBytes("utf-8"),file.length());
            }
        });
    }

}
