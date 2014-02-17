package com.ligitalsoft.workflow.plugin.flienode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jbpm.api.activity.ActivityExecution;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;

/**
 *  写文件的操作
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-9-20 下午01:26:08
 *@Team 研发中心
 */
public class WriteFileAction extends PluginActionHandler{

	private static final long serialVersionUID = -6597323510469649838L;
	
	public String data_inputVar;
	public String url;
	public String fileName;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		 try{
			 File f = new File(url);
			 if(!f.exists()){
				 f.mkdirs();
			 }
			 Matcher m = Pattern.compile("^([^\\.]+)(.*)$").matcher(fileName);  
			 if (m.find()); 
			 List<DataPackInfo> dpList = (List<DataPackInfo>)this.getCacheInfo(data_inputVar);
			 int n = 0;
			 for(DataPackInfo dpInfo:dpList){
				if(dpInfo.getByteVal().length>0){
				   n++;
				   this.writeFile(m, dpInfo.getByteVal(), n);
				}
			 }
		 }catch(Exception e){
			 ByteArrayOutputStream bo = new ByteArrayOutputStream();
			 e.printStackTrace(new PrintStream(bo));
			 log.error("写文件出错!",e);
			throw new ActionException(e);
		} 
	}

	 /**
	 * 写文件
	 *@author hudaowan
	 * @throws Exception 
	 *@date  Oct 17, 2008 4:37:18 PM
	 */
	private void writeFile(Matcher m,byte[] msg,int n) throws Exception{
		String uuid = System.currentTimeMillis()+"_"+n;
		String fName = m.group(1) + "_" + uuid + m.group(2); 
		File file = new File(url + File.separator + fName);
		FileOutputStream fos = new FileOutputStream(file);
	    fos.write(msg);
	    fos.close();
	    log.info("第【"+n+"】个数据包已写到本地，名称:【"+file.getPath()+"】.");
	}
	
}
