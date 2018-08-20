package com.zz.encrypter.service; 

import com.zz.encrypter.utils.basicwork.ByteArrayUtils;
import com.zz.encrypter.utils.cryptography.HashService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

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
    ByteArrayInputStream buf2=new ByteArrayInputStream(buf.toByteArray());
    ByteArrayUtils.outputByte(System.out,buf.toByteArray(),"file ",8);

    CipherFileHead head2=new CipherFileHead("123456".getBytes());
    head2.readFromStream(buf2);
    System.out.println(head2.length);
    ByteArrayUtils.outputByte(System.out,head2.salt,"salt");
    ByteArrayUtils.outputByte(System.out,head2.fixed_area,"fixed_area",8);
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
