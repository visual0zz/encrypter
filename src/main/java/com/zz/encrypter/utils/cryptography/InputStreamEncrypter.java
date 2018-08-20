package com.zz.encrypter.utils.cryptography;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;

/**
 * 输入流的结构优点复杂，以后再写
 */

public class InputStreamEncrypter extends InputStream {
    InputStream in;
    byte[]passkey;
    RandomGenerator generator;
    public InputStreamEncrypter(byte[] password,InputStream inputStream) {
        super();
        if(password==null||inputStream==null)throw new InvalidParameterException("输入参数不能为null");
        in=inputStream;//保存输入源流
        passkey=password;
        generator=new RandomGenerator(password);
    }

    @Override
    public int read() throws IOException {
        return in.read()^generator.getNextByte();//读取内容加密后输出
    }

    @Override
    public int read(byte[] b) throws IOException {
        int result=in.read(b);
        for(byte i:b){
            i= (byte) (i^generator.getNextByte());
        }
        return result;
    }

    @Override
    public int available() throws IOException {
        return in.available();
    }

    @Override
    public void close() throws IOException {
        in.close();
        in=null;
    }



    @Override
    public synchronized void reset() throws IOException {
        in.reset();
    }

    @Override
    public synchronized void mark(int readlimit) {
        in.mark(readlimit);
    }
    @Override
    public boolean markSupported() {
        return in.markSupported();
    }
}
