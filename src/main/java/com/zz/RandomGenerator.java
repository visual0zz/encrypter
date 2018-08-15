package com.zz;

public class RandomGenerator{

    State state;//储存当前的随机数状态
    public void setSeed(String seed){ state=new State(seed);}
    public void setSeed(int seed){state=new State(seed);}

    public static int hash(String source){
        //todo 要替换成自己的哈希函数
        return source.hashCode();
    }

    public int getNextInt(){
        state.generateNextState();
        return state.getInt();
    }

    public char getNextChar(){
        state.generateNextState();
        return state.getChar();
    }
    public char getNextPrintableChar(){
        char c;
        int count=0;
        do {
            count++;
            c=getNextChar();
            if(count>=1000) c= (char) (c%26+'a');
        }while(Character.isLetter(c));
        return c;
    }

    public String getNextStringWithCertainLength(int length){
        if(length<0){
            throw new NegativeArraySizeException("字符串长度不能小于0!");
        }
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(getNextChar());
        }
        return builder.toString();
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

    static class State{//表示一个随机数生成器的状态
        int value;
        State(State state){
            this.value =state.value;
        }
        State(int state){
            value=state;
        }
        State(String state){
            value=state.hashCode();//todo 要仔细设计这里
        }
        int getInt(){
            return value;
        }
        char getChar(){
            return (char)value;
        }
        private void generateNextState(){//得到下一个状态
            value =hash(String.valueOf(value));
        }
    }
}

