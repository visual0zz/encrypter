package com.zz;

import org.junit.Test;


public class 随便测试 {
    @Test
    public void test(){

    }
    @Test
    public void outputRandomStringConstantly(){
        String str="zzprivate";
        byte[]res=str.getBytes();
        for(byte b:res){
            System.out.print(String.format("%x",b)+" ");
        }
    }
}
