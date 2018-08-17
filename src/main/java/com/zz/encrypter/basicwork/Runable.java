package com.zz.encrypter.basicwork;

public interface Runable {//所有可以run的类
    void run(String [] args);//所有的Runable类都直接从Main接收参数 所以args[0]是自身功能对应的command 应当忽略
}
