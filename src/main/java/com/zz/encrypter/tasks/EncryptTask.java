package com.zz.encrypter.tasks;

import com.zz.encrypter.service.FileEncrypter;
import com.zz.encrypter.utils.filesystem.FileTreeTravelManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class EncryptTask {
    public void encrypt(File source, File target, String password) throws Exception {
        if(source.isFile()&&target.isFile())encryptOne2One(source,target,password);
        if(source.isDirectory()&&target.isDirectory())encryptDir2Dir(source,target,password);
        if(source.isDirectory()&&!target.exists())encryptDir2Dir(source,target,password);
        throw new Exception("参数错误，源文件不存在或者目标不正确。");
    }

    void encryptOne2One(File source, File target, String password) throws Exception {//一个文件对一个文件的加密
        FileEncrypter.encrypt(new FileInputStream(source),new FileOutputStream(target),password.getBytes(),source.length());
    }
    void encryptDir2Dir(File source, File target, String password) throws FileNotFoundException {//文件夹到文件夹的加密
        FileTreeTravelManager travelManager=new FileTreeTravelManager(source);
    }
}
