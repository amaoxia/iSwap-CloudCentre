package com.ligitalsoft.workflow.plugin.flienode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jbpm.api.activity.ActivityExecution;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;

/**
 * 读文件
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-9-20 下午01:11:59
 *@Team 研发中心
 */
public class ReadFileAction  extends PluginActionHandler {
	
	private static final long serialVersionUID = 5931153932033199895L;
	public String pathName;//路径
	public String fileName;
    public String readFile_outVar;
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
	
		long start = System.currentTimeMillis();                
	    log.info("开始读取："+pathName+"/"+fileName);    
	    try{
	    	List<DataPackInfo> dpInfoList = new ArrayList<DataPackInfo>();
	    	List<String> files = this.findFileName();
	    	int n = 1;
	    	for(String filePath:files){
	    		String path_Str = pathName+filePath;
	    		File f = new File(path_Str);
	 			FileInputStream fis = new FileInputStream(f);
	 			byte[] bytes = new byte[(int)f.length()];
	 			int offset = 0;
	 	        int numRead = 0;
	 	        while (offset < bytes.length && (numRead=fis.read(bytes, offset, bytes.length-offset)) >= 0) {
	 	            offset += numRead;
	 	        }
	 	        // Ensure all the bytes have been read in
	 	        if (offset < bytes.length) {
	 	            throw new IOException("Could not completely read file "+f.getName());
	 	        }
	 	        fis.close();
	 	       DataPackInfo dpInfo  = new DataPackInfo();
	 	       dpInfo.setByteVal(bytes);
	 	       dpInfoList.add(dpInfo);
	 	       log.info("第【"+n+"】个 文件读取成功，文件名：【"+filePath+"】");
	 	       n++;
	    	}
	    	this.putCacheInfo(readFile_outVar, dpInfoList);
	    	long end = System.currentTimeMillis();
	        log.info("完成读取:" +pathName+"/"+fileName+ ",使用时间：" + (end-start) + " ms");  
	    }catch(Exception e){
	    	 ByteArrayOutputStream bo = new ByteArrayOutputStream();
			 e.printStackTrace(new PrintStream(bo));
			 log.error("读取文件出错!",e);
			throw new ActionException(e);
		} 
	}

	 /**
     * 得到该目录下符合条件的文件
     *@author hudaowan
     *@date  Sep 23, 2008 4:58:45 PM
     *@param local
     *@return
     */
	 private List<String> findFileName() {
		 List<String> list = new ArrayList<String>();
		 File file = new File(pathName);
		 String[] fileNames = file.list();
		 for(String file_Name:fileNames){
			 if(this.equals(file_Name,fileName)){
				 list.add(file_Name);
			 }
		 }
		 return list;
	 }
	 
	 /**
	  * 过滤文件
	  *@author hudaowan
	  *@date  Sep 23, 2008 5:12:43 PM
	  *@param filenames
	  *@param filename
	  *@return
	  */
	 private synchronized boolean equals(String filenames ,String filename) {
		 boolean flag = false;
		    StringTokenizer sFile = new StringTokenizer(filenames, ".");
		    StringTokenizer tFile = new StringTokenizer(filename, ".");
		    if(sFile.countTokens()>1&&tFile.countTokens()>1){
		    	 if(sFile.nextToken().indexOf(tFile.nextToken())!=-1){
				    	if(sFile.nextToken().equals(tFile.nextToken())){
				    		flag = true;
				    	}
				    }
		    }
	      return flag;
	 }
}
