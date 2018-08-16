package com.zz.utils;

import static com.zz.utils.HashService.md5;

public class RandomGenerator{

    byte[] state=null;//储存当前的随机数状态
    public void setSeed(byte[] seed){ state=seed;bytecount=-5;}//负数迫使其第一次输出时重新计算state
    public void setSeed(String seed){ setSeed(seed.getBytes());}
    public void setRandomSeed(){setSeed(md5.getRandomHash().getByteArray());}
    public RandomGenerator(){setRandomSeed();}
    public RandomGenerator(String seed){setSeed(seed);}
    public RandomGenerator(byte[] seed){setSeed(seed);}

    int bytecount;//用于输出byte时计数

    private void generateNextState(){
        state=md5.getHash(state).getByteArray();
    }

    public byte[] getNextByteArray(){
        generateNextState();
        bytecount=-5;
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
        return (char) (getNextByte()+
                getNextByte()<<8);
    }
    public int getNextInt(){
        return  getNextByte()+
                getNextByte()<<8+
                getNextByte()<<16+
                getNextByte()<<24
                ;
    }


    public char getNextLetterOrDigit(){
        char character;
        int count=0;
        do {
            count++;
            character=getNextChar();
            //System.out.println(Integer.toString((int)character));//todo delete
            if(count>20) character= '1';// todo reuse
        }while(!Character.isLetterOrDigit(character));
        //if(character!='1')System.out.println(Integer.toString((int)character));//todo delete
        return character;
    }
    public String getNextString(int length){
        if(length<0){
            throw new NegativeArraySizeException("字符串长度不能小于0!");
        }
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(getNextLetterOrDigit());
        }
        return builder.toString();
    }
}

