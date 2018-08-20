package com.zz.encrypter.utils.cryptography;

public class ByteArrayUtils {

    public static byte[] int2byte(int res) {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }
    public static int byte2int(byte[] res) {
        int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // '|' 表示按位或
                | (res[2] <<16) | (res[3] << 24);
        return targets;
    }
    public static byte[] concat(byte[] a, byte[] b) {//数组合并
        final int alen = a.length;
        final int blen = b.length;
        if (alen == 0) {
            return b;
        }
        if (blen == 0) {
            return a;
        }
        final byte[] result = new byte[alen + blen];
        System.arraycopy(a, 0, result, 0, alen);
        System.arraycopy(b, 0, result, alen, blen);
        return result;
    }
}
