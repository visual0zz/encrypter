package com.zz;

import java.util.Scanner;

public class MainTest {
    public static void main(String [] args){
        outputRandomStringConstantly();
    }


    static void outputRandomStringConstantly(){
        Scanner in=new Scanner(System.in);
        while(in.hasNextLine()){
            String str=in.nextLine();
            RandomGenerator random=new RandomGenerator();
            for(byte ch:str.getBytes()){
                System.out.println(random.getNextPrintableStringWithCertainLength(str.length()));
            }
        }
    }
}
