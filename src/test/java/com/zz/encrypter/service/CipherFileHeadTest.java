package com.zz.encrypter.service; 

import com.zz.encrypter.utils.basicwork.ByteArrayUtils;
import com.zz.encrypter.utils.cryptography.HashService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static com.zz.encrypter.utils.basicwork.ByteArrayUtils.isEqual;
import static com.zz.encrypter.utils.basicwork.ByteArrayUtils.outputByte;

/** 
* CipherFileHead Tester. 
* 
* @author <Authors name> 
* @since <pre>Aug 20, 2018</pre> 
* @version 1.0 
*/ 
public class CipherFileHeadTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: writeToStream(OutputStream out)
* 
*/ 
@Test
public void testWriteToStream() throws Exception {
//TODO: Test goes here...
    ByteArrayOutputStream buf=new ByteArrayOutputStream();
    CipherFileHead head=new CipherFileHead("123456".getBytes(),HashService.md5.getHash("456").getByteArray(),10);
    head.writeToStream(buf);
    //outputByte(System.out,head.passkey,"head1 passkey ",8);
    //outputByte(System.out,head.salt,"head1 salt ",8);
    //outputByte(System.out,head.mark,"head1 fix ",8);
    ByteArrayInputStream buf2=new ByteArrayInputStream(buf.toByteArray());
    //outputByte(System.out,buf.toByteArray(),"file ",8);

    CipherFileHead head2=new CipherFileHead("123456".getBytes());
    head2.readFromStream(buf2);
    //outputByte(System.out,head2.passkey,"head2 passkey ",8);
    //outputByte(System.out,head2.salt,"head2 salt",8);
    //outputByte(System.out,head2.mark,"head2 fix",8);

    Assert.assertTrue(isEqual(head.mark,head2.mark));
} 

/** 
* 
* Method: buildFromStream(InputStream in) 
* 
*/ 
@Test
public void testReadFromStream() throws Exception {
//TODO: Test goes here... 
} 


} 
