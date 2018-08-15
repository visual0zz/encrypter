package com.zz;

import org.junit.Assert;
import org.junit.Test;

import static com.zz.HashService.*;

public class HashServiceTest {
    @Test
    public void testDoHash(){
        Assert.assertTrue(HashService.MD5.getUpperHash("qqq").equals("B2CA678B4C936F905FB82F2733F5297F"));
        Assert.assertTrue(MD5.getUpperHash("zzz").equals("F3ABB86BD34CF4D52698F14C0DA1DC60"));
        Assert.assertTrue(MD5.getUpperHash("昆仑").equals("5BC4A0B2472C6C1B54AC6C82BF31F90F"));
        Assert.assertTrue(SHA1.getLowerHash("昆仑").equals("48a938af5692cd018ef1d004dc3db1a51ac8d163"));
        Assert.assertTrue(SHA256.getLowerHash("昆仑").equals("34e38c211804ee9fbf0993b5f01f047fbf42955e0a141ab049f483da0b250837"));
    }
}
