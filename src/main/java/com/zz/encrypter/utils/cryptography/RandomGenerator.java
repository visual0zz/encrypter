package com.zz.encrypter.utils.cryptography;

public final class RandomGenerator{

    byte[] state=null;//储存当前的随机数状态
    int bytecount;//用于输出byte时计数


    public void setSeed(byte[] seed){
        if(seed==null)seed=new byte[]{};//如果输入null视为空串
        state=seed;
        bytecount=-1;//负数迫使其第一次输出时重新计算state
    }
    public void setSeed(String seed){
        if(seed==null)seed="";//如果输入null就当作空串处理
        setSeed(seed.getBytes());
    }
    public void setRandomSeed(){setSeed(HashService.md5.getRandomHash().getByteArray());}
    public RandomGenerator(){setRandomSeed();}
    public RandomGenerator(String seed){setSeed(seed);}
    public RandomGenerator(byte[] seed){setSeed(seed);}


    private void generateNextState(){

        //state=md5.getHash(new String(state)+Integer.toBinaryString(bytecount)).getByteArray();
        state= HashService.md5.getHash(concat(state,int2byte(bytecount))).getByteArray();
    }

    public byte[] getNextByteArray(){
        generateNextState();
        bytecount=-1;//标志这个state已经输出过了，如果要get其他的，要出现生成
        return state;
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


    private char getNextLetterOrDigit() throws Exception {
        char character;
        character=getNextChar();
        if(Character.isLetterOrDigit(character))return character;
        throw new Exception("得到错误的字符");
    }
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
    private static byte[] int2byte(int res) {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }
    private static int byte2int(byte[] res) {
        int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // '|' 表示按位或
                | (res[2] <<16) | (res[3] << 24);
        return targets;
    }
    private static byte[] concat(byte[] a, byte[] b) {//数组合并
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

