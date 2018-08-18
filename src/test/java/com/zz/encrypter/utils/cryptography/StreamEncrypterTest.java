package com.zz.encrypter.utils.cryptography; 

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.PrintStream;

/** 
* StreamEncrypter Tester. 
* 
* @author <Authors name> 
* @since <pre>Aug 18, 2018</pre> 
* @version 1.0 
*/ 
public class StreamEncrypterTest {

    StreamEncrypter encrypter;
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

    ByteOutputStream stream=new ByteOutputStream();
    encrypter=StreamEncrypter.getInstance("123456",System.out);
    PrintStream printStream=new PrintStream(encrypter);
    printStream.println("测试加密效果");

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
