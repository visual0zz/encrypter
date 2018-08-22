package com.zz.encrypter.service; 

import com.zz.encrypter.utils.basicwork.ByteArrayUtils;
import org.junit.*;

import java.io.*;

import static com.zz.encrypter.utils.basicwork.ByteArrayUtils.isEqual;

/** 
* FileEncrypter Tester. 
* 
* @author <Authors name> 
* @since <pre>Aug 21, 2018</pre> 
* @version 1.0 
*/ 
public class FileEncrypterTest { 

@BeforeClass
public static void before() throws Exception {
} 

@AfterClass
public static void after() throws Exception {
} 

/** 
* 
* Method: encrypt(InputStream from, OutputStream to, byte[] password, long file_length) 
* 
*/ 
@Test
public void testEncrypt() throws Exception { 
//TODO: Test goes here...
    byte[] example;
    File targetFile=new File("test/cipher.txt.zpi");
    FileEncrypter.encrypt(
            new ByteArrayInputStream(example=new byte[]{0x32,0x31,0x11, (byte) 0x80}),
            new FileOutputStream(targetFile),
            "123".getBytes(),
            4);

    ByteArrayOutputStream outbuf=new ByteArrayOutputStream();
    FileEncrypter.decrypt(new FileInputStream("test/cipher.txt.zpi"),outbuf,"123".getBytes());
    //ByteArrayUtils.outputByte(System.out,outbuf.toByteArray(),"解密内容");
    Assert.assertTrue(isEqual(example,outbuf.toByteArray()));
} 

/** 
* 
* Method: decrypt(InputStream from, OutputStream to, byte[] password) 
* 
*/
@Test
public void testDecrypt() throws Exception {
//TODO: Test goes here...
}

/**
*
* Method: isEncryptedBy(InputStream source, byte[] password)
*
*/
@Test
public void testIsLockedBy() throws Exception {
//TODO: Test goes here...
}


}
