package com.zz.encrypter.service;

import com.zz.encrypter.utils.basicwork.ByteArrayUtils;
import com.zz.encrypter.utils.cryptography.HashService;
import com.zz.encrypter.utils.cryptography.InputStreamEncrypter;
import com.zz.encrypter.utils.cryptography.OutputStreamEncrypter;
import java.io.OutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.InvalidPropertiesFormatException;

import static com.zz.encrypter.utils.basicwork.ByteArrayUtils.*;

public class CipherFileHead {//加密文件的文件头格式
    //region //数据内容
    final static private byte[] magic_word={0x7a, 0x7a, 0x70, 0x72, 0x69, 0x76, 0x61, 0x74};
    //用于标识加密文件的魔数，其实质是Asicii码的"zzprivat"，少一个字母e是为了凑8字节，方便对齐。

    long length;//原文件长度。

    byte[] salt;//这个应该是16字节长,salt。

    byte[] mark;//16字节长，内容是两遍magic_word^md5(password)，这里的内容被写入流时要加密,用于区分密码是否正确
    //endregion

    //region //元数据
    boolean empty;//当前是否储存为空。
    byte[] passkey;//暂存密码，不参与输出
    //endregion

    /**
     * @param length 原文件的长度，字节记。
     * @param password 用于加密的密码。
     * @param salt 用于添加随机性的附加值，应该为16位长。
     */
    CipherFileHead(  byte[] password, byte[] salt,long length){
        if(salt.length!=16)throw new InvalidParameterException("salt需要16字节长的字节数组。");
        this.length=length;
        this.salt=salt;
        mark =ByteArrayUtils.concat(magic_word,magic_word);//两遍magic_word
        byte[] temp=HashService.md5.getHash(password).getByteArray();//md5(password)
        mark =ByteArrayUtils.xor(mark,temp);
        passkey=password;
        empty=false;
    }
    CipherFileHead(byte[] passkey){empty=true;this.passkey=passkey;}

    public void writeToStream(OutputStream out) throws IOException {//输出文件头
        if(empty)throw new SecurityException("对一个没有数据的CipherFileHead对象进行输出是没有意义的。");

        OutputStreamEncrypter encrypter=new OutputStreamEncrypter(concat(passkey,salt),out);

        out.write(magic_word);//8字节
        out.write(long2byte(length));//8字节
        out.write(salt);//16字节
        encrypter.write(mark);//输出加密过的固定区
    }
    public void readFromStream(InputStream in) throws IOException {
        empty=true;
        allocSpace();

        byte[] magic=new byte[8];
        if(in.read(magic)!=8)throw new InvalidPropertiesFormatException("输入流内没有足够数据，无法解析文件头");
        if(!isEqual(magic,magic_word))throw new InvalidPropertiesFormatException("文件格式错误，无法解析");

        byte[] length=new byte[8];
        if(in.read(length)!=8)throw new InvalidPropertiesFormatException("输入流内没有足够数据，无法解析文件头");
        this.length=byte2long(length);

        //byte[] salt=new byte[16];
        if(in.read(salt)!=16)throw new InvalidPropertiesFormatException("输入流内没有足够数据，无法解析文件头");

        InputStreamEncrypter encrypter=new InputStreamEncrypter(concat(passkey,salt),in);

        if(encrypter.read(mark)!=16)throw new InvalidPropertiesFormatException("输入流内没有足够数据，无法解析文件头");

        byte[] temp=HashService.md5.getHash(passkey).getByteArray();//md5(password)
        byte[] fixed=ByteArrayUtils.xor(mark,temp);//解码这个区域，看密码是否正确
        if(!isEqual(fixed,concat(magic_word,magic_word)))throw new SecurityException("密码错误，读取不到正确数据");
        empty=false;
    }
    private void allocSpace(){
        if(salt==null)salt=new byte[16];
        if(mark ==null) mark =new byte[16];
    }
}
