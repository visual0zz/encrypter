package com.zz.encrypter.utils.cryptography;

import com.zz.encrypter.utils.cryptography.HashService;
import org.junit.Assert;
import org.junit.Test;

public class HashServiceTest {
    @Test
    public void testGetHash(){
        Assert.assertTrue(HashService.md5.getHash("qqq").getUpper().equals("B2CA678B4C936F905FB82F2733F5297F"));
        Assert.assertTrue(HashService.md5.getHash("zzz").getUpper().equals("F3ABB86BD34CF4D52698F14C0DA1DC60"));
        Assert.assertTrue(HashService.md5.getHash("昆仑").getUpper().equals("5BC4A0B2472C6C1B54AC6C82BF31F90F"));
        Assert.assertTrue(HashService.sha1.getHash("昆仑").getLower().equals("48a938af5692cd018ef1d004dc3db1a51ac8d163"));
        Assert.assertTrue(HashService.sha256.getHash("昆仑").getLower().equals("34e38c211804ee9fbf0993b5f01f047fbf42955e0a141ab049f483da0b250837"));
    }
    @Test
    public void testGetRandomHash(){
        Assert.assertTrue(HashService.isEqual(HashService.md5.getHash("昆仑"), HashService.md5.getHash("昆仑")));
        Assert.assertTrue(!HashService.isEqual(HashService.md5.getHash("昆仑"), HashService.md5.getHash("涤纶")));
        HashService.HashResult hash1=HashService.md5.getRandomHash();
        try {
            Thread.currentThread().sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashService.HashResult hash2=HashService.md5.getRandomHash();
        Assert.assertTrue(!HashService.isEqual(hash1,hash2));
    }
}
