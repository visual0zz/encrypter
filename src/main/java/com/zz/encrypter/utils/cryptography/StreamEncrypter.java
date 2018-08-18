package com.zz.encrypter.utils.cryptography;

import java.io.IOException;
import java.io.OutputStream;

public class StreamEncrypter extends OutputStream {
    public StreamEncrypter(String password,OutputStream stream) {
        super();
    }

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        super.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}
