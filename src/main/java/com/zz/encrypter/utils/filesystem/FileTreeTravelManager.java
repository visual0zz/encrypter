package com.zz.encrypter.utils.filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public final class FileTreeTravelManager {
    private File rootFolder;//储存迭代树的根目录
    private FileTreeTraveler traveler;
    private ArrayList<String> list;
    /**
     * @param folder 迭代树的根目录，但也可以是一个文件，如果是一个文件，就只会调用travelFile一次。
     * @throws FileNotFoundException  如果根目录根本不存在就抛出异常
     */
    FileTreeTravelManager(String folder) throws FileNotFoundException {
        this(new File(folder));
    }
    FileTreeTravelManager(File folder) throws FileNotFoundException {
        if(!folder.exists())throw new FileNotFoundException("找不到这个文件:"+folder.getPath());
        rootFolder=folder;//保存文件夹
    }

    public List<String> travel(FileTreeTraveler traveler){
        this.traveler=traveler;//保存traveler
        list=new ArrayList<>();
        travelOneLayer(rootFolder,0);
        return list;
    }
    private void travelOneLayer(File file,int level_count){
        if(file.exists()){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                if(files!=null&&files.length>0){
                    for(File f:files){
                        if(traveler.travelFolder(level_count,file,list))
                            travelOneLayer(f,level_count+1);//如果返回true，继续迭代其下内容
                    }
                }
            }else if(file.isFile()){
                traveler.travelFile(level_count,file,list);
            }
        }
    }
}
