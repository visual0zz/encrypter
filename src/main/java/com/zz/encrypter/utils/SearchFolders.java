package com.zz.encrypter.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//
//        用例
// new SearchFile().Do("filepath","name_end");
// 查找filepath下面所有的以name_end结尾的文件 返回一个列表
//
//
//
//
//
public class SearchFolders {
    public List<String> Do(String path, String regex){
        //path是要搜索的路径 regex 是搜索依据的正则表达式字符串
        if(path!=null&& regex !=null){
            File file = new File(path);
            List<String> list = new ArrayList<>();
            getFilesList(file, regex,list);
            return list;
        }
        return null;
    }
    protected   void getFilesList(File file, String regex, List<String> list){
        //如果file是一个文件 判断符不符合file_name_end并加入list，如果是文件夹，递归调用自己
        if(file.exists()){
            if(file.isDirectory()){//只对文件夹操作 忽略所有文件
                if(isfit(file.getName(),regex))
                    list.add(file.getPath());//如果文件夹名符合就加入列表
                File[] files = file.listFiles();
                if(files!=null&&files.length>0){//对每一个下级文件都递归获得其列表
                    for(File f:files){
                        getFilesList(f, regex,list);
                    }
                }
            }
        }
    }
    protected boolean isfit(String name,String name_rule)//判断一个文件名是不是符合name_rule的要求
    {
        Pattern p = Pattern.compile(name_rule);
        Matcher m = p.matcher(name);
        return m.matches();
    }
}
