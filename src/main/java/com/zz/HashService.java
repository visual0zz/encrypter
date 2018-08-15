package com.zz;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum HashService {

    md5,
    sha1,
    sha256,
    ;

    public static String byteArrayToString(byte[] in){
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        int j = in.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = in[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
    public static boolean isEqual(byte[] a,byte[] b){
        return (new HashResult(a)).equals(new HashResult(b));
    }
    public static boolean isEqual(HashResult a,HashResult b){
        return a.equals(b);
    }

    public HashResult getHash(String in){//从字符串得到哈希值
        byte[] btInput=in.getBytes();
        return getHash(btInput);
    }
    public HashResult getHash(byte[] in){//得到字节数组格式的哈希值
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
        return new HashResult(mdInst.digest());
        // 把密文转换成十六进制的字符串形式
    }
    @Override
    public String toString() {
        if(this== sha256)return "SHA-256";
        else return super.toString();
    }
    public HashResult getRandomHash(){
        return getHash(getSystemTime()+" 0x1234787 "+getEnvironmentInfo());
    }
    private String getSystemTime(){
        Date time=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateString = formatter.format(time);
        return dateString;
    }
    private String getEnvironmentInfo(){
        String [] properties={"java.version\n",
                "java.vendor\n" ,
                "java.vendor.url\n" ,
                "java.home\n" ,
                "java.vm.specification.version\n" ,
                "java.vm.specification.vendor\n" ,
                "java.vm.specification.name\n" ,
                "java.vm.version\n" ,
                "java.vm.vendor\n" ,
                "java.vm.name\n" ,
                "java.specification.version\n" ,
                "java.specification.vendor\n" ,
                "java.specification.name\n" ,
                "java.class.version\n" ,
                "java.class.path\n" ,
                "java.library.path\n" ,
                "java.io.tmpdir\n" ,
                "java.compiler\n" ,
                "java.ext.dirs\n" ,
                "os.name\n" ,
                "os.arch\n" ,
                "os.version\n" ,
                "file.separator\n" ,
                "path.separator\n" ,
                "line.separator\n" ,
                "user.name\n" ,
                "user.home\n" ,
                "user.dir\n"};
        StringBuilder result=new StringBuilder();
        for(String property:properties){
            result.append(System.getProperty(property));
        }
        return result.toString();
    }

    static class HashResult{
        byte [] value;
        HashResult(byte[] in){value=in;}
        public String getUpper(){
            return byteArrayToString(value).toUpperCase();
        }
        public String getLower(){
            return byteArrayToString(value).toLowerCase();
        }
        public byte[] getByteArray(){
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if(!obj.getClass().equals(HashResult.class))return false;//类型不同就不需要比较了
            HashResult b=(HashResult)obj;
            if(value.length!=b.value.length)return false;//大小不同就不用比较了
            int length=value.length;
            for(int i=0;i<length;i++){
                if(value[i]!=b.value[i])return false;//有一位不同就不行
            }
            return true;
        }
    }
}



