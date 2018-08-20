package com.zz.encrypter.utils.basicwork;

import com.zz.encrypter.utils.cryptography.HashService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.InvalidParameterException;

public class ByteArrayUtils {

    public static byte[] int2byte(int res) {//小端序
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }
    public static byte[] long2byte(long res){//小端序
        byte[] targets = new byte[8];
        targets[0] = (byte) (res & 0xff);
        targets[1] = (byte) ((res >> 8) & 0xff);
        targets[2] = (byte) ((res >> 16) & 0xff);
        targets[3] = (byte) ((res >> 24) & 0xff);
        targets[4] = (byte) ((res >> 32) & 0xff);
        targets[5] = (byte) ((res >> 40) & 0xff);
        targets[6] = (byte) ((res >> 48) & 0xff);
        targets[7] = (byte) ((res >> 56) & 0xff);
        return targets;

    }
    public static int byte2int(byte[] res) {//小端序

        if(res==null)throw new InvalidParameterException("参数不能为空。");
        int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // '|' 表示按位或
                | (res[2] <<16) | (res[3] << 24);
        return targets;
    }
    public static byte[] concat(byte[] a, byte[] b) {//数组合并
        if(a==null||b==null)throw new InvalidParameterException("参数不能为空。");

        final int alen = a.length;
        final int blen = b.length;
        if (alen == 0) {
            return b;
        }
        if (blen == 0) {
            return a;
        }
        final byte[] result = new byte[alen + blen];
        System.arraycopy(a, 0, result, 0, alen);
        System.arraycopy(b, 0, result, alen, blen);
        return result;
    }
    public static byte[] xor(byte[]a,byte[]b){
        if(a==null||b==null)throw new InvalidParameterException("参数不能为空。");
        if(a.length==0||b.length==0)throw new InvalidParameterException("两个数组不能是空数组。");
        if(a.length!=b.length) throw new InvalidParameterException("参与计算必须是两个等长数组。");

        byte[] result=new byte[a.length];
        for(int i=0;i<a.length;i++){
            result[i]= (byte) (a[i]^b[i]);
        }
        return result;
    }

    public static void outputByte(OutputStream out,byte[] res) throws IOException {
        PrintStream o=new PrintStream(out);
        o.print("byte[]{ ");
        for(int i=0;i<res.length;i++){
            o.print(String.format("%02x",res[i])+" ");
        }
        o.println("}");
    }

    public static boolean isEqual(byte[] a,byte[] b){
        if(a==null||b==null)throw new InvalidParameterException("参数不能为空。");
        if(a.length!=b.length)return false;//如果长度不等，整体就不相等。
        for(int i=0;i<a.length;i++){
            if(a[i]!=b[i])return false;//只要有一个不相等，整体就不相等。
        }
        return true;
    }
}
