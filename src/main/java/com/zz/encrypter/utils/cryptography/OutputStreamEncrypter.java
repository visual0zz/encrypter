package com.zz.encrypter.utils.cryptography;

import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidParameterException;

/**
 * @author vis
 * @apiNote 这是一个流包装类，向这个类打印的数据会被异或加密后送往其内部输出流。
 */
public class OutputStreamEncrypter extends OutputStream {
    OutputStream target;
    byte[] passkey;
    RandomGenerator generator;

    /**
     *
     * @param password 密码
     * @param stream 输出流
     */
    public OutputStreamEncrypter(byte[] password, OutputStream stream) {
        super();
        if(password==null||stream==null)throw new InvalidParameterException("输入参数不能为null");
        passkey=password;
        target=stream;
        generator=new RandomGenerator(password);
    }


    @Override
    public void write(int b) throws IOException {
        target.write((b^generator.getNextByte())&0xff);
    }

    @Override
    public void write(byte[] b) throws IOException {
        int length=b.length;
        byte[] tar=new byte[length];
        for( int i=0;i<length;i++){
            tar[i]= (byte) (b[i]^(generator.getNextByte()));
        }
        target.write(tar);
    }

    @Override
    public void flush() throws IOException {
        target.flush();
    }

    @Override
    public void close() throws IOException {
        target.close();
    }
}
