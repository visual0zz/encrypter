package com.zz;

import com.zz.encrypter.service.CipherFileHead;
import org.junit.Test;

import java.io.PrintStream;


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
    //@Test
    public void 测试两个流汇聚(){
        PrintStream str1=new PrintStream(System.out);
        PrintStream str2=new PrintStream(System.out);
        str1.println("stream1");
        str2.println("stream2");
        str1.println("stream1");
        str2.println("stream2");
    }
    @Test
    public void 测试0x78563412(){

    }
}
