package com.zz.encrypter;

import com.zz.encrypter.tasks.DecryptTask;
import com.zz.encrypter.tasks.EncryptTask;
import com.zz.encrypter.utils.basicwork.StringAndBasicWork;

import java.io.File;

import static java.lang.System.exit;

public class Main {
    public static void main(String[]args) {
        try {
            if (args.length != 4) erro("参数错误。");
            if (args[0].equals("e")) {
                doJob(args[1], args[2], args[3], true, false);//加密
            } else if (args[0].equals("d")) {
                doJob(args[1], args[2], args[3], false, false);//解密
            } else if (args[0].equals("e!")) {
                doJob(args[1], args[2], args[3], true, true);//强制覆盖加密
            } else if (args[0].equals("d!")) {
                doJob(args[1], args[2], args[3], false, true);//强制覆盖解密
            } else erro("没有这个指令: " + args[0]);
        }catch (Exception e){
            erro(e.getMessage());
        }
    }


    public static void erro(String msg){//错误处理
        System.out.println("\033[31m"+msg+"\033[0m");
        System.out.println("用法: java -jar "+StringAndBasicWork.GetProgramName()+" <command> <fileFrom> <fileTo> <password>");
        System.out.println("命令列表:" +
                "\ne 加密一个文件或者一个文件夹" +
                "\nd 解密一个文件或者文件夹" +
                "\ne! 加密，遇到已存在文件就覆盖" +
                "\nd! 解密，遇到已存在文件就覆盖" +
                "\n\n示例 "+StringAndBasicWork.GetProgramName()+" e! a");
        exit(1);
    }

    public static void doJob(String source,String target,String password,boolean encrypt,boolean force) throws Exception {
        File from=new File(source);
        File to=new File(target);
        if( to.exists() && !force)erro("目标文件已存在，如果要覆盖，请使用！强制执行。");
        if (encrypt){
            new EncryptTask().encrypt(from,to,password);
        }
        else{
            new DecryptTask().decrypt(from,to,password);
        }
    }
}
