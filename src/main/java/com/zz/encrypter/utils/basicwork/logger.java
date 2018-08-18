package com.zz.encrypter.utils.basicwork;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.System.exit;
//
//    zz依赖 PrintFileManager
//
//          用例
// new logger("name").erro("error message");//以name的身份发出错误消息
//         可用接口
// erro("message");
// warning("message");
// debug("message");
// erroAndExit("message",exception);
//
// 以及他们分别对应的OnScreen 版本 OnScreen发出的消息不记录在log文件中 只显示在屏幕上
//
//
@Deprecated
public class  logger {
    private static final boolean outputScreen=true;//是否输出到屏幕
    private static final boolean outputLogfile=true;//是否输出到日志文件
    private static final boolean outputErrolog=true;//是否输出错误到单独的错误日志

    private static final boolean cleanBeforeOpen=true;//是否输出前清空日志文件

    private static String logfilePath="apkanalyser.output/zz.apkanalyse.log";//用于记录日志
    private static String logerroPath="apkanalyser.output/zz.apkanalyse.erro.log";//用于记录错误
    private static boolean has_been_set=false;
    public static void setOutputFolder(String folder){//可以中途设定日志文件的输出目录
        logfilePath=folder+"/zz.apkanalyse.log";
        logerroPath=folder+"/zz.apkanalyse.erro.log";
        has_been_set=true;
    }
    private String name;//储存调用者传入的名字
    public logger(String name){this.name=name;}

    //region  //公共接口区
    public logger erroAndExit(String str, Exception e){

        outputEvent(new Event(name,str+"\n"+ stringFromException(e),EventType.erro,false));
        exit(1);
        return this;
    }
    public logger erro(String in){outputEvent(new Event(name,in,EventType.erro,false));return this;}
    public logger warning(String in){ outputEvent(new Event(name,in,EventType.warning,false)); return this;}
    public logger debug(String in){ outputEvent(new Event(name,in,EventType.debug,false)); return this;}

    public logger erroOnScreenAndExit(String in,Exception e){
        outputEvent(new Event(name,in+"\n"+ stringFromException(e),EventType.erro,true));
        exit(1);
        return this;
    }
    public logger erroOnScreen(String in){outputEvent(new Event(name,in,EventType.erro,true));return this;}
    public logger warningOnScreen(String in){outputEvent(new Event(name,in,EventType.warning,true));return this;}
    public logger debugOnScreen(String in){outputEvent(new Event(name,in,EventType.debug,true));return this;}
    //endregion

    //region //私有函数定义区
    private void outputEvent(Event event){//负责输出一个事件
        PrintFileManager file=new PrintFileManager(whichFileToLog(event),"logger",cleanBeforeOpen);//打开日志文件
        if(outputScreen)System.out.print(messageFormat(event,true));//打印到屏幕
        if(!event.screenOnly) {//如果该消息不是只打印到屏幕
            if(outputLogfile)file.print(messageFormat(event, false));//打印到文件
            if(outputErrolog)outputToErroLog(event);//发送消息到erro记录函数，经过筛选后就记录进入erro-log文件
        }
    }

    private String stringFromException(Exception e){
        if(e==null)return "<null exception>";//如果输入空异常
        StringBuilder buf=new StringBuilder();
        buf.append(e.toString());
        buf.append('\n');
        int layer=0;
        for(StackTraceElement elem : e.getStackTrace()) {
            for(int i=layer;i>0;i--){
                if(i==1)
                    buf.append("└─");
                else
                    buf.append("   ");
            }
            buf.append(elem);
            buf.append('\n');
            layer++;
        }
        return  buf.toString();
    }

    private static int normalMessagecount=numNearbyErro();//计数 从上一次输出错误到现在已经输出了几条普通消息了
    private static ArrayList<String> normalMessages;//暂存普通消息
    private void outputToErroLog(Event event){//负责筛选消息并缓存普通消息 打印错误消息

        boolean interst=isThisErro(event);//只关注一个消息是不是erro
        String msg=messageFormat(event,false);//格式化消息
        PrintFileManager file=new PrintFileManager(whichFileToErro(),"logger",cleanBeforeOpen);//打开日志文件
        if(normalMessages==null)normalMessages=new ArrayList<>();//建立普通消息缓冲器
        if(interst){//如果是erro消息
            normalMessagecount=0;//计数清空
            if(normalMessages.size()>numNearbyErro()){//如果缓存满了 说明从上一次输出错误已经过了很久
                file.print("\n... ... ...\n");//输出省略号
                normalMessages.remove(0);
            }

            for(String str:normalMessages){//遍历缓存输出所有普通消息
                file.print(str);
            }
            file.print(msg);//输出错误消息
            normalMessages.clear();//清空缓冲器
        }else{//如果是普通消息
            if(normalMessagecount< numNearbyErro())//如果上一次错误之后还没有输出足够多的普通消息
            {
                file.print(msg);//输出错误消息
                normalMessagecount++;//计数+1表示输出了一条普通消息
            }else{//如果已经输出了足够多的普通消息 就要对普通消息进行缓存
                if(normalMessages.size()<=numNearbyErro()){//如果缓存的普通消息数量少于限制
                    normalMessages.add(msg);//缓存此消息
                }else{//如果已经存满
                    normalMessages.add(msg);
                    normalMessages.remove(0);//存一个删一个
                }
            }
        }
    }

    private class Event{//代表一个事件
        boolean screenOnly;//是否只打印在屏幕上，不记录进日志
        EventType type;//事件类型
        Date time;//事件发生的时间
        String message;//消息字符串
        String name;//发送者名字
        Event(String name,String message,EventType type,boolean screenOnly)//
        {
            time=new Date();//获得系统时间
            this.name=name;
            this.type=type;
            this.screenOnly=screenOnly;
            this.message=message;
        }
    }
    private enum EventType{
        erro("[ERROR:]",red),
        warning("[WRN:]  ",yellow),
        debug("[:]     ",blue);

        String titile;//这一类事件记录在文件中的时候的前缀
        String color;//这一类型事件屏幕显示的颜色
        EventType(String titile,String color){this.titile=titile;this.color=color;}
    }
    //endregion

    //region //protected函数区
    protected String whichFileToLog(Event event){
        //logger通过调用这个函数来获取log文件路径 这个文件负责储存所有log
        return logfilePath;
    }
    protected String whichFileToErro(){
        //logger通过调用这个函数来获取erro-log文件路径 这个文件负责单独储存erro 方便查看
        return logerroPath;
    }
    protected String messageFormat(Event event,boolean to_screen){
        //这个函数用于定义消息格式
        //to_screen=true 表示当前要产生的字符串是要发送到屏幕的
        //to_screen=false 表示要打印到文件
        String result;
        if(to_screen){//如果是向屏幕显示的消息
            result=event.type.color+(event.name!=null?("<"+event.name+">:"):"")+event.message+"\n"+clear;
        }else{//如果是向文件打印的消息
            result=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(event.time)+" "+event.type.titile+"<"+event.name+">:"+event.message+"\n";
            //  时间  +类型标识  +名字   +消息
        }
        return result;
    }
    protected boolean isThisErro(Event event){return event.type!=EventType.debug;}//这个函数如果返回true决定消息会被写入erro-log文件
    protected  static int numNearbyErro(){return 2;}//这个函数决定erro附近保留的普通消息的个数
    //endregion


    //region //颜色代码定义区
    private static String clear = "\033[0m";
    private static String underline = "\033[4m";
    private static String blink = "\033[5m";

    private static String black = "\033[1m\033[30m";
    private static String red = "\033[1m\033[31m";
    private static String green = "\033[1m\033[32m";
    private static String yellow = "\033[1m\033[33m";
    private static String blue = "\033[1m\033[34m";
    private static String purple = "\033[1m\033[35m";
    private static String sky = "\033[1m\033[36m";
    private static String white = "\033[1m\033[37m";

    private static String black_bg = "\033[40m";
    private static String red_bg = "\033[41m";
    private static String green_bg = "\033[42m";
    private static String yellow_bg = "\033[43m";
    private static String blue_bg = "\033[44m";
    private static String purple_bg = "\033[45m";
    private static String sky_bg = "\033[46m";
    private static String white_bg = "\033[47m";
    //endregion

}


