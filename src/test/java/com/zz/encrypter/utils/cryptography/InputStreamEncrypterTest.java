package com.zz.encrypter.utils.cryptography; 

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static com.zz.encrypter.utils.basicwork.ByteArrayUtils.isEqual;

/** 
* InputStreamEncrypter Tester. 
* 
* @author <Authors name> 
* @since <pre>Aug 20, 2018</pre> 
* @version 1.0 
*/ 
public class InputStreamEncrypterTest {
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: read() 
* 
*/ 
@Test
public void testRead() throws Exception { 
//TODO: Test goes here...
    InputStreamEncrypter source;
    byte[] aaa=("123 456").getBytes();
    ByteArrayOutputStream out=new ByteArrayOutputStream();
    source=new InputStreamEncrypter("123ff".getBytes(),new ByteArrayInputStream(aaa));
    OutputStreamEncrypter target=new OutputStreamEncrypter("123ff".getBytes(),out);
    while(source.available()>0){
        target.write(source.read());
    }
    Assert.assertTrue(isEqual(aaa,out.toByteArray()));
} 

/** 
* 
* Method: available() 
* 
*/ 
@Test
public void testAvailable() throws Exception { 
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

/** 
* 
* Method: reset() 
* 
*/ 
@Test
public void testReset() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: mark(int readlimit) 
* 
*/ 
@Test
public void testMark() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: markSupported() 
* 
*/ 
@Test
public void testMarkSupported() throws Exception { 
//TODO: Test goes here... 
} 


} 
