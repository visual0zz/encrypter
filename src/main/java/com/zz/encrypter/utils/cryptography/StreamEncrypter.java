package com.zz.encrypter.utils.cryptography;

import java.io.IOException;
import java.io.OutputStream;

public class StreamEncrypter extends OutputStream {
    OutputStream target;
    byte[] passkey;
    RandomGenerator generator;
    private StreamEncrypter(byte[] password,OutputStream stream) {
        super();
        passkey=password;
        target=stream;
        generator=new RandomGenerator(password);
    }
    public static StreamEncrypter getInstance(byte[] password,OutputStream stream){
        if(password==null||stream==null) return null;
        return new StreamEncrypter(password,stream);
    }
    public static StreamEncrypter getInstance(String password,OutputStream stream){
        return new StreamEncrypter(password.getBytes(),stream);
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
