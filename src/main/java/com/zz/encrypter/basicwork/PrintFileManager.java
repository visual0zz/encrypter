package com.zz.encrypter.basicwork;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

import static java.lang.System.exit;

//      zz依赖:无
//              PrintFileManager 用例
//
// new PrintFileManager("filename","group").print("hello World");//向文件打印字符串
// .close();//关闭这个文件
// .cleanFile();//清空对应文件
//
// .getGroup();
// 获得这个文件的实际分组(由于有可能别的代码先用其他分组名打开了这个文件，
// 而分组只会记录第一次打开时的分组，导致分组名和自己写的分组名不一致)
//
// new PrintFileManager("filename") 等效为 new PrintFileManager("filename","group")
// 即默认组为"main"
//
// PrintFileManager.countFiles();//计数一共打开了多少文件
// PrintFileManager.closeGroup("group");//关闭某一组文件
// .countInGroup(String grp)//计算这一组有多少个打开的文件流
// .getGroups()//列出所有的组 返回一个arrayList 里面是所有的组名
//
// 这个类用于管理打开的一堆文件 每个文件只会打开一次，
// 不用主动打开，第一次调用new ("filename")就会打开文件
// 如果文件不存在就新建，如果文件夹不存在就新建
// 每次调用都是从之前的已经打开的列表里面查找
//
// 分组是为了一次性关闭一组文件，每个文件打开时自带一个分组标识字符串，如果不写默认为main分组
//


public class PrintFileManager {

    private static HashMap<String,Pair<PrintWriter,String>> data;//统一记录所有的文件流

    private String group;//用于储存自己的分组
    private PrintWriter stream;//用于暂存文件流
    private String path;//暂存文件路径

    public PrintFileManager(String file) { this(file,"main"); }//如果不指定组名 默认为main
    public PrintFileManager(String file,String group_){ this(file,group_,false);}//以不清空方式打开文件
    public PrintFileManager(String file_,String group_,boolean clean_before_open){
        if(data==null){data=new HashMap<>();}//建立data
        File file=new File(file_);
        Pair<PrintWriter,String> tmp;//暂存搜索结果
        try {
            path=file.getCanonicalPath();//得到文件的绝对路径
            if(data.containsKey(path)){//如果data里保存有这个文件,就读取已经打开的文件
                tmp=data.get(path);//搜索data得到文件流和group
                stream=tmp.getKey();
                group=tmp.getValue();
            }
            else{
                stream=OpenFile(file,!clean_before_open);//如果data里没有这个文件，就新建流打开文件
                group=group_;
                tmp=new Pair<>(stream,group);
                data.put(path,tmp);//将文件流和group储存进data
            }
        } catch (Exception e) { e.printStackTrace();exit(1); }//出错就退出
    }


    public static int countFiles(){//返回logFile打开的文件数量
        return data.size();
    }
    public static ArrayList<String> getGroups(){//列出所有的组
        Iterator<HashMap.Entry<String, Pair<PrintWriter,String>>> it = data.entrySet().iterator();//迭代data
        ArrayList<String> list=new ArrayList<>();//储存组名
        while(it.hasNext()){
            HashMap.Entry<String,Pair<PrintWriter,String>> entry = it.next();
            String group=entry.getValue().getValue();
            if(!list.contains(group)){
                list.add(group);
            }
        }
        return list;
    }
    public static int countInGroup(String grp){//计算一组有多少个打开的文件流
        Iterator<HashMap.Entry<String, Pair<PrintWriter,String>>> it = data.entrySet().iterator();//迭代data
        int counter=0;
        while(it.hasNext()){
            HashMap.Entry<String,Pair<PrintWriter,String>> entry = it.next();
            String group=entry.getValue().getValue();
            if(grp.equals(group)){counter++;}//组一致就计数
        }
        return counter;
    }
    public static void closeGroup(String grp){//关闭某一组的所有文件
        Iterator<HashMap.Entry<String, Pair<PrintWriter,String>>> it = data.entrySet().iterator();
        ArrayList<String> list=new ArrayList<>();//储存这一组的所有文件在data中的key_value
        //得到data内部数组的迭代器
        while(it.hasNext()){//遍历data
            HashMap.Entry<String,Pair<PrintWriter,String>> entry = it.next();
            if(entry.getValue().getValue().equals(grp)) {//如果分组符合
                entry.getValue().getKey().close();//关闭文件流
                list.add(entry.getKey());//添加进要删除的列表
            }
        }
        for(String e:list){//遍历list 删除所有对应的文件流
            data.remove(e);
        }
    }

    public  String getGroup(){//获得分组
        return group;
    }
    public void cleanFile(){//清除文件内容
        stream.close();
        stream=OpenFile(new File(path),false);
        Pair<PrintWriter,String> tmp=new Pair<>(stream,group);
        data.put(path,tmp);

    }

    public void print(String in){
        stream.print(in);
        stream.flush();
    }
    public void close()
    {
        stream.close();
        data.remove(path);
    }

    //region //内部函数区
    private PrintWriter OpenFile(File file,boolean autoflush){//autoflush是否不覆盖
        MakeSureTargetFileExist(file);
        PrintWriter wt=null;
        try {
            wt= new PrintWriter(new FileWriter(file ,autoflush),autoflush);//
        } catch (Exception e) {
            e.printStackTrace();
            exit(1);
        }
        return wt;
    }

    private void MakeSureTargetFileExist(File in){//判断目标文件存在性，不存在就新建 包括各级目录
        File floder=new File(pathToFloderPath(in.getAbsolutePath()));
        if(!floder.exists())floder.mkdirs();//如果文件夹爱不存在就新建
        if(!in.exists()) {
            try {
                in.createNewFile();
            } catch (IOException e) {
                System.out.println("\033[1m\033[31mErro when creating file<"+in.getAbsolutePath()+">");
                e.printStackTrace();
                exit(1);
            }
        }
    }
    private static  String pathToFloderPath(String path){//从路径得到文件名
        int index;
        index=path.lastIndexOf('/');
        if(index==-1){//-1表示没有找到，说明出问题了
            System.out.println("\033[1m\033[31mfailed extracting floder Path from path <"+path+">");
            exit(1);
        }
        return path.substring(0,index);
    }
    //endregion
}
