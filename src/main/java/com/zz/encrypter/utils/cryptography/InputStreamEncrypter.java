package com.zz.encrypter.utils.cryptography;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;

/**
 * 输入流的结构优点复杂，以后再写
 */
@Deprecated
public class InputStreamEncrypter extends InputStream {
    InputStream in;
    byte[]passkey;
    RandomGenerator generator;
    public InputStreamEncrypter(byte[] password,InputStream inputStream) {
        super();
        if(password==null||inputStream==null)throw new InvalidParameterException("输入参数不能为null");
        in=inputStream;//保存输入源流
        passkey=password;
        generator=new RandomGenerator();
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return super.skip(n);
    }

    @Override
    public int available() throws IOException {
        return super.available();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        super.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        super.reset();
    }

    @Override
    public boolean markSupported() {
        return super.markSupported();
    }
}
