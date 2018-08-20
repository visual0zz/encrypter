package com.zz.encrypter.utils.cryptography;

import static com.zz.encrypter.utils.cryptography.ByteArrayUtils.byte2int;
import static com.zz.encrypter.utils.cryptography.ByteArrayUtils.concat;
import static com.zz.encrypter.utils.cryptography.ByteArrayUtils.int2byte;

public final class RandomGenerator{

    byte[] state=null;//储存当前的随机数状态
    int bytecount;//用于输出byte时计数


    public void setSeed(byte[] seed){
        if(seed==null)seed=new byte[]{};//如果输入null视为空串
        state=seed;
        bytecount=-5;//负数迫使其第一次输出时重新计算state
    }
    @Deprecated
    public void setSeed(String seed){
        if(seed==null)seed="";//如果输入null就当作空串处理
        setSeed(seed.getBytes());
    }
    public void setRandomSeed(){setSeed(HashService.md5.getRandomHash().getByteArray());}
    public RandomGenerator(){setRandomSeed();}
    public RandomGenerator(byte[] seed){setSeed(seed);}

    /**
     * 从当前状态得到下一个状态。
     */
    protected void generateNextState(){
        state= HashService.md5.getHash(concat(state,int2byte(0x78563412))).getByteArray();
    }
    public byte getNextByte(){//获得伪随机字节
        bytecount++;
        if(bytecount<state.length && bytecount>=0){
            return state[bytecount];
        }
        else{
            bytecount=0;
            generateNextState();
            return state[bytecount];
        }
    }
    public char getNextChar(){
        return (char) (getNextByte()|
                getNextByte()<<8);
    }
    public int getNextInt(){

        return  byte2int(new byte[]{getNextByte(),getNextByte(),getNextByte(),getNextByte()})
                ;
    }

    @Deprecated
    private char getNextLetterOrDigit() throws Exception {
        char character;
        character=getNextChar();
        if(Character.isLetterOrDigit(character))return character;
        throw new Exception("得到错误的字符");
    }

    @Deprecated
    public String getNextString(int length){
        if(length<0){
            throw new NegativeArraySizeException("字符串长度不能小于0!");
        }
        char[]buf=new char[length*3];//建立缓冲区
        int index=0;
        for(int i=0;i<length*3;i++){//获取所需三倍的字符，概率上确保大可能性得到足够的随机字符
            char tmp;
            try {
                tmp=getNextLetterOrDigit();
                buf[index]=tmp;//如果能执行到这里说明得到了正常的字符。
                index++;
            } catch (Exception e){}

        }
        for(;index<=length;index++){
            buf[index]='烫';//实在没有足够的字符就随便填一个
        }
        return String.copyValueOf(buf,0,length);
    }

    @Override
    public String toString() {
        StringBuilder result=new StringBuilder();
        result.append("RandomGenerator{ " +
                "\n  state:[ ");
        for(byte b:state){

            result.append(String.format("%02x",Byte.toUnsignedInt(b)));
            result.append(' ');
        }
        result.append("]" +
                "\n  bytecount:" +
                Integer.toString(bytecount));
        result.append("\n}");
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass()!=this.getClass())return false;
        RandomGenerator other=(RandomGenerator)obj;
        if(HashService.isEqual(this.state,other.state) && this.bytecount==other.bytecount)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return (new String(state)+Integer.toBinaryString(bytecount)).hashCode();
    }
}

