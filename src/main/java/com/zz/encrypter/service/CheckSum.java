package com.zz.encrypter.service;

import com.zz.encrypter.utils.cryptography.HashService;

import static com.zz.encrypter.utils.basicwork.ByteArrayUtils.concat;

public class CheckSum {
    byte[] sum;//校验和,应当始终具有16位的大小
    byte[] buff;//储存数据的缓冲区，应当始终具有16位的大小
    int index;//用于计量数据装填了多少，指向即将装填的空白空间。
    CheckSum(){
        sum=new byte[16];
        for(byte i:sum)i=0;//清空缓冲区
        buff=new byte[16];
        index=0;
    }
    public void addByte(byte in){
        assert index>=0&&index<16;
        buff[index]=in;index++;//装填进缓冲区

        if(index>=16){//如果缓冲区填满就计算校验码并且重置缓冲区。
            index=0;
            sum=HashService.md5.getHash(concat(sum,buff)).getByteArray();
        }
    }
    public void addBytes(byte[] in){
        for(byte i:in)addByte(i);
    }
    public byte[] getSum() {
        if(index==0)return sum;//如果没有还没参与计算的数据 就可以直接返回校验和
        for(int i=index;i<16;i++){
            buff[i]=0;//将还没有数据的缓冲区清零
        }
        sum=HashService.md5.getHash(concat(sum,buff)).getByteArray();
        return sum;
    }
}
