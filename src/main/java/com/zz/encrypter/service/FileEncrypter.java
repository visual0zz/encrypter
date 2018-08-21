package com.zz.encrypter.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

public class FileEncrypter {
    InputStream in;
    OutputStream out;
    byte[] password;
    byte[] salt;

    public void encrypt(InputStream from,OutputStream to,byte[] password){

    }
    public void decrypt(InputStream from,OutputStream to,byte[] password){

    }
    public boolean check(InputStream source,byte[] password){

    }
}
