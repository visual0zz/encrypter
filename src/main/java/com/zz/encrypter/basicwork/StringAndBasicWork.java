package com.zz.encrypter.basicwork;



import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;

// zz依赖 无
//
//
//
//

public class StringAndBasicWork {//负责本程序内所有和字符串相关的操作

    private static final String findClass="(?<=L)[a-zA-z_0-9/]*(?=;)";//用于在smali语句中查找类的正则表达式

    public static  String pathToname(String path){//从路径得到文件名
        String result;
        if(!IsStringAFilePath(path)){System.out.println("path to name Error:its not a path.");exit(1);}
        result=FindStrByRegex(path,"[^/]*/?$");
        if(result==null){//null表示没有找到，说明有问题
            System.out.println("path to name Error");
            exit(1);
        }
        return result;
    }//*///
    /*public static  String pathToFloderPath(String path){//从路径得到文件名
        int index;
        index=path.lastIndexOf('/');
        if(index==-1){//-1表示没有找到，说明出问题了
            new logger("").erro("failed extracting floder Path from path <"+path+">");
            exit(1);
        }
        return path.substring(0,index);
    }//*///
    public static String  FindStrByRegex( String source,String regex) {//正则表达式匹配函数
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(source);
        if( m.find() )
        {

            return m.group(); //group方法返回由以前匹配操作所匹配的输入子序列。
        }
        return null;
    }
    public static boolean StringMatchRegex( String source,String regex) {//正则表达式匹配函数
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(source);
        return m.matches();
    }

    public static boolean IsStringAFilePath(String in){//判断一个字符串是不是一个合法的文件
        if(FindStrByRegex(in,"[:*?|<>]")!=null)return false;
        return true;
    }

    public static String PathToCanonicalPath(String path){
        File tmp=new File(path);
        try {
            return tmp.getCanonicalPath();
        } catch (IOException e) {
            System.out.println("error when transforming path to Canonical path.");
            e.printStackTrace();
            exit(1);
        }
        return null;
    }
    public static boolean IsArgsValid(String[] in,int paranum){//判断参数数组是不是合法的
        if (in.length != paranum) return false;//如果输入参数个数和预设格式不符合就有问题

        for(String a:in){//所有参数都必须是合法的路径
            if(!IsStringAFilePath(a))return false;
        }
        return true;
    }
    public static String GetProgramPath(){
        File a=null;
        try {
            a=new File(StringAndBasicWork.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            exit(1);
        }
        return a.getPath();
    }
    public static String GetProgramName(){
        File a=null;
        try {
            a=new File(StringAndBasicWork.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            exit(1);
        }
        return a.getName();
    }
}
