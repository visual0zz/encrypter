package com.zz.encrypter.service;

import com.zz.encrypter.utils.cryptography.ByteArrayUtils;
import com.zz.encrypter.utils.cryptography.HashService;
import com.zz.encrypter.utils.cryptography.RandomGenerator;

import java.security.InvalidParameterException;
import java.util.HashSet;

public class CipherFileHead {//加密文件的文件头格式
    final static private byte[] magic_word={0x7a, 0x7a, 0x70, 0x72, 0x69, 0x76, 0x61, 0x74};
    //用于标识加密文件的魔数，其实质是Asicii码的"zzprivat"，少一个字母e是为了凑8字节，方便对齐。

    long length;//原文件长度。

    byte[] salt;//这个应该是16字节长,salt。

    byte[] fixed_area;//16字节长，内容是两遍magic_word^md5(password)，这里的内容被写入流时要加密

    boolean empty;//当前是否储存为空。
    /**
     * @param length 原文件的长度，字节记。
     * @param password 用于加密的密码。
     * @param salt 用于添加随机性的附加值，应该为16位长。
     */
    CipherFileHead(long length , byte[] password , byte[] salt){
        if(salt.length!=16)throw new InvalidParameterException("salt需要16字节长的字节数组。");
        this.length=length;
        this.salt=salt;
        fixed_area=ByteArrayUtils.concat(magic_word,magic_word);//两遍magic_word
        byte[] temp=HashService.md5.getHash(password).getByteArray();//md5(password)
        //fixed_area=
        empty=false;
    }
    CipherFileHead(){empty=true;}

    public byte[] outputToByteArray(){
        return null;
    }
    public void buildFromByteArray(byte[] in){

    }
}
