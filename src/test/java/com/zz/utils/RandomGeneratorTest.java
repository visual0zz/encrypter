package com.zz.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.security.InvalidParameterException;
import java.util.Scanner;

import static com.zz.utils.HashService.isEqual;

/** 
* RandomGenerator Tester. 
* 
* @author <Authors name> 
* @since <pre>Aug 16, 2018</pre> 
* @version 1.0 
*/ 
public class RandomGeneratorTest { 

    RandomGenerator generator;
@Before
public void before() throws Exception {
    generator=new RandomGenerator();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: setSeed(byte[] seed) 
* 
*/ 
@Test
public void testSetSeedSeed() throws Exception { 
//TODO: Test goes here...

        generator.setSeed((byte[]) null);//测试给乱七八糟的种子会不会崩溃
        generator.getNextByte();
        generator.getNextByteArray();
        generator.getNextChar();
        generator.getNextInt();
        generator.getNextString(123);

        RandomGenerator generator=new RandomGenerator();

        generator.setSeed((String) null);
        generator.getNextByte();
        generator.getNextByteArray();
        generator.getNextChar();
        generator.getNextInt();
        generator.getNextString(123);

    Assert.assertEquals("相同的种子得到了不同的序列",this.generator,generator);
    Assert.assertEquals(
            "相同的种子得到了不同的序列",
            this.generator.getNextString(13),
            generator.getNextString(13));
    Assert.assertEquals("相同的种子得到了不同的序列",this.generator.getNextInt(),generator.getNextInt());
    generator.getNextByte();

    //System.out.println(generator.bytecount);
    Assert.assertTrue("不同的状态得到了相同的序列",!isEqual(this.generator.getNextByteArray(),generator.getNextByteArray()));
    //System.out.println(generator.bytecount);
    Assert.assertNotEquals("不同的状态得到了相同的序列",this.generator,generator);
    System.out.println(generator);
} 

/** 
* 
* Method: setRandomSeed() 
* 
*/ 
@Test
public void testSetRandomSeed() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getNextByteArray() 
* 
*/ 
@Test
public void testGetNextByteArray() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getNextByte() 
* 
*/ 
@Test
public void testGetNextByte() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getNextChar() 
* 
*/ 
@Test
public void testGetNextChar() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getNextInt() 
* 
*/ 
@Test
public void testGetNextInt() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getNextLetterOrDigit() 
* 
*/ 
@Test
public void testGetNextLetterOrDigit() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getNextString(int length) 
* 
*/ 
@Test
public void testGetNextString() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: generateNextState() 
* 
*/ 
@Test
public void testGenerateNextState() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = RandomGenerator.getClass().getMethod("generateNextState"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
