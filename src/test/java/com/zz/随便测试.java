package com.zz;

import com.zz.encrypter.service.CipherFileHead;
import org.junit.Test;


public class 随便测试 {
    @Test
    public void test(){
        //CipherFileHead.magic_word[1]=3;
    }
    @Test
    public void outputRandomStringConstantly(){
        String str="zzprivate";
        byte[]res=str.getBytes();
        System.out.println(str+"的编码是:");
        for(byte b:res){
            System.out.print(String.format("%x",b)+" ");
        }
    }
}
