package com.zz.encrypter.service;

import java.io.InputStream;
import java.io.OutputStream;

public class FileEncrypter {
    InputStream in;
    OutputStream out;
    Mode mode;
    FileEncrypter(){mode=Mode.none;}//新建立的加密器处于不工作模式
    FileEncrypter from(InputStream in){this.in=in;return this;}



    enum Mode {//当前工作模式
        encrypt,
        decrypt,
        none
    }
}
