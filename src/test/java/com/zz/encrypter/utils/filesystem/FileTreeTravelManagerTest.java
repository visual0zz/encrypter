package com.zz.encrypter.utils.filesystem; 


import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
* FileTreeTravelManager Tester. 
* 
* @author <Authors name> 
* @since <pre>Aug 18, 2018</pre> 
* @version 1.0 
*/ 
public class FileTreeTravelManagerTest { 
FileTreeTravelManager manager;
@Before
public void before() throws Exception {
    manager=new FileTreeTravelManager(".");
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: travel(FileTreeTraveler traveler) 
* 
*/ 
@Test
public void testTravel() throws Exception { 
//TODO: Test goes here...
    List<String> gradle_files = manager.travel(new FileTreeTraveler() {
        @Override
        public boolean shouldTravelIntoFolder(int layer_level, File folder) {
            if(layer_level!=0)
                return false;
            else
                return true;
        }

        @Override
        public void travelFolder(int layer_level, File folder, List<String> result) {
            //result.add(folder.getName());
        }

        @Override
        public void travelFile(int layer_level, File file, List<String> result) {
            Pattern pattern = Pattern.compile(".*\\.gradle");
            Matcher matcher = pattern.matcher(file.getName());
            if (matcher.matches())
                result.add(file.getName());
        }
    });
    ArrayList<String> compare=new ArrayList<>();
    compare.add("settings.gradle");
    compare.add("build.gradle");
    //System.out.println(gradle_files);
    Assert.assertEquals("遍历当前目录找不到build.gradle和settings.gradle",gradle_files,compare);
} 


/** 
* 
* Method: travelOneLayer(File file, int level_count) 
* 
*/ 
@Test
public void testTravelOneLayer() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = FileTreeTravelManager.getClass().getMethod("travelOneLayer", File.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
