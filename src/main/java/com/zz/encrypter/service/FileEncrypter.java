package com.zz.encrypter.service;

import com.zz.encrypter.utils.cryptography.HashService;
import com.zz.encrypter.utils.cryptography.InputStreamEncrypter;
import com.zz.encrypter.utils.cryptography.OutputStreamEncrypter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import static com.zz.encrypter.utils.basicwork.ByteArrayUtils.concat;

public class FileEncrypter {

    private FileEncrypter(){}

    /**
     *
     * @param from 要加密的原始明文
     * @param to 密文要储存到的目标流
     * @param password 用于加密的密码
     */
    public static void encrypt(InputStream from,OutputStream to,byte[] password,long file_length) throws Exception {
        byte[] salt=HashService.md5.getRandomHash().getByteArray();//得到随机盐。
        CipherFileHead head=new CipherFileHead(password,salt,file_length);
        OutputStreamEncrypter target=new OutputStreamEncrypter(concat(password,salt),to);
        try {
            head.writeToStream(to);
            while(from.available()>0){
                target.write(from.read());
            }
        } catch (IOException e) {
            throw new Exception("加密过程在输入输出时出现异常。",e);
        }
    }
    public static void decrypt(InputStream from,OutputStream to,byte[] password) throws Exception {

        CipherFileHead head=new CipherFileHead(password);
        head.readFromStream(from);//读取密文的头部
        byte[] salt=head.getSalt();//得到文件头部储存的盐 用于解密
        long length=head.getLength();
        InputStreamEncrypter source=new InputStreamEncrypter(concat(password,salt),from);
        try {
            while(source.available()>0 && length>0){
                length--;//计算按照头文件的预计 应该有多少字节被读出来
                to.write(source.read());
            }
        } catch (IOException e) {
            throw new Exception("解密过程在输入输出时出现异常。",e);
        }

    }
    public static boolean check(InputStream source,byte[] password) throws IOException {
        CipherFileHead head=new CipherFileHead(password);
        try {
            head.readFromStream(source);
        } catch (IOException e) {
            throw new IOException("输入流工作异常",e);
        } catch (InputFormatException e) {
            throw new IOException("输入流字节格式错误",e);
        } catch (PasswordException e) {
            return false;
        }
        return true;
    }
}
