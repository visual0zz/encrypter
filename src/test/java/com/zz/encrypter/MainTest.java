package com.zz.encrypter;

import com.zz.encrypter.utils.RandomGenerator;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Scanner;

/**
* Main Tester.
*
* @author <Authors name>
* @since <pre>Aug 16, 2018</pre>
* @version 1.0
*/
public class MainTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: main(String[]args)
    *
    */
    @Test
    public void testMain() throws Exception {
    //TODO: Test goes here...
    }

    public static void main(String []args){
        outputRandomStringConstantly();
    }
    static void outputRandomStringConstantly(){
        Scanner in=new Scanner(System.in);
        while(in.hasNextLine()){
            String str=in.nextLine();
            RandomGenerator random=new RandomGenerator(str);
            for(byte ch:str.getBytes()){
                System.out.println(random.getNextString(str.length()));
            }
        }
    }
}
