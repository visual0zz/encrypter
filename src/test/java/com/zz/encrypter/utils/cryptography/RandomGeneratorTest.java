package com.zz.encrypter.utils.cryptography;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static com.zz.encrypter.utils.basicwork.ByteArrayUtils.isEqual;

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

        generator.setSeed(new byte[]{});//测试给乱七八糟的种子会不会崩溃
        generator.getNextByte();
        generator.getNextChar();
        generator.getNextInt();

        RandomGenerator generator=new RandomGenerator();

        generator.setSeed(new String(""));
        generator.getNextByte();
        generator.getNextChar();
        generator.getNextInt();

    Assert.assertEquals("相同的种子得到了不同的序列",this.generator,generator);
    Assert.assertEquals(
            "相同的种子得到了不同的序列",
            this.generator.getNextString(4),
            generator.getNextString(4));
    Assert.assertEquals("相同的种子得到了不同的序列",this.generator.getNextInt(),generator.getNextInt());

    generator.getNextByte();

    Assert.assertNotEquals("不同的状态得到了相同的序列",this.generator.getNextInt(),generator.getNextInt());
    //System.out.println(generator);

    ////////////////下面的测试用例用于保持算法逻辑不变性//////////////////////////////////
    byte[] example=new byte[]{
            (byte) 0xdd, (byte)0xaa, (byte) 0xd2, (byte)0x10,
            (byte) 0x24, (byte)0xa3, (byte) 0x5d, (byte)0x3c,
            (byte) 0x33, (byte)0xfb, (byte) 0xf6, (byte)0x22,
            (byte) 0x79, (byte)0x37, (byte) 0x51, (byte)0xab};
    //System.out.println(generator);
    Assert.assertTrue(isEqual(generator.state,example));
    //System.out.println(generator.getNextInt());
    Assert.assertTrue(-591053==generator.getNextInt());
    //System.out.println(generator.getNextString(10));
    Assert.assertTrue("㝹ﾳﾾ븼ﾯﾀﾆﾛ녳㴘".equals(generator.getNextString(10)));

} 

/** 
* 
* Method: setRandomSeed() 
* 
*/ 
@Test
public void testSetRandomSeed() throws Exception {
    generator.setRandomSeed();//测试随机种子
    generator.getNextString(123);

    RandomGenerator generator2=new RandomGenerator();
    generator2.getNextString(123);

    boolean[] result=new boolean[5];
    result[1]=this.generator.getNextChar()==generator2.getNextChar();
    result[2]= this.generator.getNextString(3).equals(generator2.getNextString(3));
    result[3]=this.generator.getNextByte()==generator2.getNextByte();
    result[4]=this.generator.getNextInt()==generator2.getNextInt();
    int count=0;
    for(boolean a:result){
        if(a)count++;
    }
    if(count>=2)throw new Exception("随机函数连续发生"+count+"次碰撞，这个错误如果偶发属于正常，如果多发，随机函数哈希有问题");

}

/**
*
* Method: int2byte(int res)
*
*/
@Test
public void testInt2byte() throws Exception {
//TODO: Test goes here...
/*
    System.out.println("0x78563412:");
   byte[] result=RandomGenerator.int2byte(0x78563412);
   for(byte a:result) System.out.print(a+" ");
   System.out.println();
//*/
}

/**
*
* Method: byte2int(byte[] res)
*
*/
@Test
public void testByte2int() throws Exception {
//TODO: Test goes here...
/*
try {
   Method method = RandomGenerator.getClass().getMethod("byte2int", byte[].class);
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
}

/**
*
* Method: concat(byte[] a, byte[] b)
*
*/
@Test
public void testConcat() throws Exception {
//TODO: Test goes here...
/*
try {
   Method method = RandomGenerator.getClass().getMethod("concat", byte[].class, byte[].class);
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
}

} 
