package com.zz.utils;

import com.zz.utils.HashService;
import org.junit.Assert;
import org.junit.Test;

import static com.zz.utils.HashService.*;

public class HashServiceTest {
    @Test
    public void testGetHash(){
        Assert.assertTrue(HashService.md5.getHash("qqq").getUpper().equals("B2CA678B4C936F905FB82F2733F5297F"));
        Assert.assertTrue(md5.getHash("zzz").getUpper().equals("F3ABB86BD34CF4D52698F14C0DA1DC60"));
        Assert.assertTrue(md5.getHash("昆仑").getUpper().equals("5BC4A0B2472C6C1B54AC6C82BF31F90F"));
        Assert.assertTrue(sha1.getHash("昆仑").getLower().equals("48a938af5692cd018ef1d004dc3db1a51ac8d163"));
        Assert.assertTrue(sha256.getHash("昆仑").getLower().equals("34e38c211804ee9fbf0993b5f01f047fbf42955e0a141ab049f483da0b250837"));
    }
    @Test
    public void testGetRandomHash(){
        Assert.assertTrue(isEqual(md5.getHash("昆仑"),md5.getHash("昆仑")));
        Assert.assertTrue(!isEqual(md5.getHash("昆仑"),md5.getHash("涤纶")));
        Assert.assertTrue(!isEqual(md5.getRandomHash(),md5.getRandomHash()));
    }
}
