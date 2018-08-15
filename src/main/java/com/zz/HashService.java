package com.zz;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum HashService {

    MD5,
    SHA1,
    SHA256,
    ;

    public final String getUpperHash(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        try {
            byte[]md=getByteArrayHash(s);
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getLowerHash(String in){
        return getUpperHash(in).toLowerCase();
    }
    public byte[] getByteArrayHash(String in){//得到字节数组格式的哈希值
        byte[] btInput=in.getBytes();
        return getByteArrayHash(btInput);
    }
    public byte[] getByteArrayHash(byte[] in){
        // 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest mdInst = null;
        try {
            mdInst = MessageDigest.getInstance(this.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        // 使用指定的字节更新摘要
        mdInst.update(in);
        // 获得密文
        return mdInst.digest();
        // 把密文转换成十六进制的字符串形式
    }
    @Override
    public String toString() {
        if(this==SHA256)return "SHA-256";
        else return super.toString();
    }
}



