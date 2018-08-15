package com.zz;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainTest {
    public static void main(String [] args) throws NoSuchAlgorithmException {
        System.out.println(HashService.SHA256.getSystemTime());
    }
}
