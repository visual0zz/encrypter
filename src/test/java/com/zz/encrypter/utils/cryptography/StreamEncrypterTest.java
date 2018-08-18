package com.zz.encrypter.utils.cryptography; 



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/** 
* StreamEncrypter Tester. 
* 
* @author <Authors name> 
* @since <pre>Aug 18, 2018</pre> 
* @version 1.0 
*/ 
public class StreamEncrypterTest {

    StreamEncrypter encrypter;
    String source="1123456789798在个人";//用于测试加密效果的字符串
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
    encrypter=StreamEncrypter.getInstance("123456",stream);
    PrintStream printStream=new PrintStream(encrypter);
    printStream.print(source);

    byte[] result=stream.toByteArray();
    System.out.print(" \""+source+"\" 的加密结果是 [ ");
    for(int i=0;i<result.length;i++){
        System.out.print(result[i]);
        System.out.print(' ');
    }
    System.out.print(']');


////////////////////////////解密
    ByteArrayOutputStream jiemi=new ByteArrayOutputStream();
    encrypter=StreamEncrypter.getInstance("123456",System.out);
    encrypter.write(result);

    ByteArrayInputStream jieguoin= new ByteArrayInputStream(jiemi.toByteArray());
    Scanner in=new Scanner(jieguoin);
    while(in.hasNext())
        System.out.println(in.next());
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
