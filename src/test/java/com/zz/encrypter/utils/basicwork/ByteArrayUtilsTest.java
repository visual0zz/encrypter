package com.zz.encrypter.utils.basicwork; 

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static com.zz.encrypter.utils.basicwork.ByteArrayUtils.*;

/** 
* ByteArrayUtils Tester. 
* 
* @author <Authors name> 
* @since <pre>Aug 20, 2018</pre> 
* @version 1.0 
*/ 
public class ByteArrayUtilsTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: int2byte(int res) 
* 
*/ 
@Test
public void testInt2byte() throws Exception {
    byte[] example=new byte[]{0x12,0x34,0x56,0x78};
    byte[] compare=int2byte(0x78563412);
    //outputByte(System.out,compare);
    Assert.assertTrue(isEqual(compare,example));
//TODO: Test goes here... 
} 

/** 
* 
* Method: long2byte(long res) 
* 
*/ 
@Test
public void testLong2byte() throws Exception {
    byte[] example=new byte[]{0x12,0x34,0x56,0x78,0x11,0x22,0x33,0x44};
    byte[] compare=long2byte(0x4433221178563412L);
    //outputByte(System.out,compare);
    Assert.assertTrue(isEqual(compare,example));
} 

/** 
* 
* Method: byte2int(byte[] res) 
* 
*/ 
@Test
public void testByte2int() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: concat(byte[] a, byte[] b) 
* 
*/ 
@Test
public void testConcat() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: xor(byte[]a, byte[]b) 
* 
*/ 
@Test
public void testXor() throws Exception { 
//TODO: Test goes here... 
} 


} 
