package com.zz.encrypter.service;

public class CipherFileHead {//加密文件的文件头格式
    final static private byte[] magic_word={0x7a, 0x7a, 0x70, 0x72, 0x69, 0x76, 0x61, 0x74};
    //用于标识加密文件的魔数，其实质是Asicii码的"zzprivat"，少一个字母e是为了凑8字节，方便对齐。

    long length;//目标文件长度。

    byte[] pass_salt;//这个应该是16字节长,储存使用pass加密的salt。

    byte[] pass_salt_salt;//这个应该是16字节长，储存使用pass+salt作为密码加密的salt。

    byte[] fixed_area;//16字节长，储存pass+salt加密的固定内容，固定内容是两遍magic_word。

    public byte[] outputToByteArray(){
        return null;
    }
    public void buildFromByteArray(byte[] in){

    }
}
