package com.zz;

import static com.zz.HashService.*;

public class RandomGenerator{

    byte[] state=null;//储存当前的随机数状态
    public void setSeed(String seed){ state=seed.getBytes();}
    public void setRandomSeed(){state=md5.getRandomHash().getByteArray();}
    RandomGenerator(){setRandomSeed();bytecount=-5;}//负数迫使其第一次输出时重新计算state
    RandomGenerator(String seed){setSeed(seed);bytecount=-5;}
    int bytecount;//用于输出byte时计数
    public static int hash(String source){
        //todo 要替换成自己的哈希函数
        return source.hashCode();
    }
    private void generateNextState(){
        state=md5.getHash(state).getByteArray();
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
    public int getNextInt(){
        return  getNextByte()+
                getNextByte()<<8+
                getNextByte()<<16+
                getNextByte()<<24
                ;
    }

    public char getNextChar(){
        return (char) (getNextByte()+
                        getNextByte()<<8);
    }
    public char getNextPrintableChar(){
        char character;
        int count=0;
        do {
            count++;
            character=getNextChar();
            if(count>=1000) character= 'a';
        }while(!Character.isLetter(character));
        return character;
    }
    public String getNextPrintableStringWithCertainLength(int length){
        if(length<0){
            throw new NegativeArraySizeException("字符串长度不能小于0!");
        }
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(getNextPrintableChar());
        }
        return builder.toString();
    }
}

