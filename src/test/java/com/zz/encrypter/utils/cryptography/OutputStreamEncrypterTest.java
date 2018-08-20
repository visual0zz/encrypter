package com.zz.encrypter.utils.cryptography; 



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/** 
* OutputStreamEncrypter Tester.
* 
* @author <Authors name> 
* @since <pre>Aug 18, 2018</pre> 
* @version 1.0 
*/ 
public class OutputStreamEncrypterTest {

    OutputStreamEncrypter encrypter;
    String source="天王盖地虎 宝塔镇河妖";//用于测试加密效果的字符串
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}
/**
*
* Method: getInstance(byte[] password, OutputStream stream)
*
*/
@Test
public void testGetInstanceForPasswordStream() throws Exception {
//TODO: Test goes here...

/////////////////////////////加密
    ByteArrayOutputStream stream=new ByteArrayOutputStream();
    encrypter=new OutputStreamEncrypter("123456".getBytes(),stream);
    PrintStream printStream=new PrintStream(encrypter);
    printStream.print(source);

    byte[] result=stream.toByteArray();
    //System.out.print(" \""+source+"\" 的加密结果是 [ ");
    /*for(int i=0;i<result.length;i++){
        System.out.print(result[i]);
        System.out.print(' ');
    }//*///
    //System.out.println("]");


////////////////////////////解密
    ByteArrayOutputStream jiemi=new ByteArrayOutputStream();
    encrypter=new OutputStreamEncrypter("123456".getBytes(),jiemi);
    encrypter.write(result);

    String it=new String(jiemi.toByteArray(),"utf-8");
    //System.out.println("解密结果是: \""+it+"\" ");
    Assert.assertEquals(source,it);
}

/** 
* 
* Method: write(int b) 
* 
*/ 
@Test
public void testWriteB() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: flush() 
* 
*/ 
@Test
public void testFlush() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: close() 
* 
*/ 
@Test
public void testClose() throws Exception { 
//TODO: Test goes here... 
} 


} 
