/*
 * @(#)FileUtil.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */
package com.ligitalsoft.sysmanager.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * *文件相关操作工具类
 * @author  zhangx
 * @since   May 25, 2011 1:34:02 PM
 * @name    com.ligitalsoft.sysmanager.util.FileUtil.java
 * @version 1.0
 */
public class FileUtil {
    /*
     * liuw
     * 建立目录
     */
    public static void makeMulu(String path){
        File f=new File(path);
        try{
            if(f.exists()) {}
            else{
                f.mkdirs();//建立目录
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{ f=null; }
    }
    
    /*
     * liuw
     * 删除单个文件
     */
    public static void deleOnefile(String delepath){
        File file = new File(delepath);
        try
        {
            if(file.exists()){
                file.delete();
            }
        }catch(Exception ex){ 
            ex.printStackTrace();
        }
        finally{
            file=null;
        }
    }
    private static final int BUFFER_SIZE=20*1024;
    public static boolean  copy(File src, File dst) {  
        boolean result=false;  
        InputStream in = null;  
        OutputStream out = null;  
        try {  
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);  
            out = new BufferedOutputStream(new FileOutputStream(dst),  
                    BUFFER_SIZE);  
            byte[] buffer = new byte[BUFFER_SIZE];  
            int len = 0;  
            while ((len = in.read(buffer)) > 0) {  
                out.write(buffer, 0, len);  
            }  
            out.flush();//将缓冲区数据全部写入
            result=true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            result=false;  
        } finally {  
        	 if (null != out) {  
                 try {  
                     out.close();  
                 } catch (IOException e) {  
                     e.printStackTrace();  
                 }  
             }  
            if (null != in) {  
                try {  
                    in.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
           
        }  
        return result;  
    }  
    
    /**   
     * 删除目录（文件夹）以及目录下的文件   
     * @param   dir 被删除目录的文件路径   
     * @return  目录删除成功返回true,否则返回false   
     */    
    public static boolean deleteDirectory(String dir){     
        //如果dir不以文件分隔符结尾，自动添加文件分隔符     
        if(!dir.endsWith(File.separator)){     
            dir = dir+File.separator;     
        }     
        File dirFile = new File(dir);     
        //如果dir对应的文件不存在，或者不是一个目录，则退出     
        if(!dirFile.exists() || !dirFile.isDirectory()){     
            System.out.println("删除目录失败"+dir+"目录不存在！");     
            return false;     
        }     
        boolean flag = true;     
        //删除文件夹下的所有文件(包括子目录)     
        File[] files = dirFile.listFiles();     
        for(int i=0;i<files.length;i++){     
            //删除子文件     
            if(files[i].isFile()){     
               deleOnefile(files[i].getAbsolutePath());     
            }     
            //删除子目录     
            else{     
                flag = deleteDirectory(files[i].getAbsolutePath());     
                if(!flag){     
                    break;     
                }     
            }     
        }     
             
        if(!flag){     
            System.out.println("删除目录失败");     
            return false;     
        }     
             
        //删除当前目录     
        if(dirFile.delete()){     
            System.out.println("删除目录"+dir+"成功！");     
            return true;     
        }else{     
            System.out.println("删除目录"+dir+"失败！");     
            return false;     
        }     
    }     
}

