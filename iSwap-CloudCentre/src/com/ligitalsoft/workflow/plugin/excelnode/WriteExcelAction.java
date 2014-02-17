package com.ligitalsoft.workflow.plugin.excelnode;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jbpm.api.activity.ActivityExecution;

import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.FiledDataInfo;
import com.ligitalsoft.workflow.plugin.model.RowDataInfo;

/**
 * 生成Excel文件
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-9-21 下午02:10:12
 * @Team 研发中心
 */
public class WriteExcelAction extends PluginActionHandler {

	private static final long serialVersionUID = 2871532737696751434L;
	public String data_inputVar; // 输入数据的变量
	public String head;
	public String writeExcel_outVar;

	@SuppressWarnings("unchecked")
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		long start = System.currentTimeMillis();
		log.info("开始生成Excle文件【Head:" + head + "】......");
		try {
			List<DataPackInfo> dpInfoList = (List<DataPackInfo>)this.getCacheInfo(data_inputVar);
			List<DataPackInfo> dpInfoObjs = new ArrayList<DataPackInfo>();
			int n = 1;
			for (DataPackInfo dpInfo : dpInfoList) {
				DataPackInfo dp = this.createExcel(dpInfo);
				dpInfoObjs.add(dp);
				log.info("第【" + n + "】个Excel文件解析成功！");
				n++;
			}
			this.putCacheInfo(writeExcel_outVar, dpInfoObjs);
			long end = System.currentTimeMillis();
			log.info("完成生成:Excel文件【Head:" + head + "】使用时间：" + (end - start)
					+ " ms");
		} catch (Exception e) {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bo));
			log.error("生成Excel出错!", e);
			throw new ActionException(e);
		}
	}

	/**
	 * 创建Excel
	 * 
	 * @param dpInfo
	 * @return
	 * @throws ActionException
	 */
	public DataPackInfo createExcel(DataPackInfo dpInfo)
			throws ActionException {
		DataPackInfo dpi = new DataPackInfo();
		List<RowDataInfo> dataInfos = dpInfo.getRowDataList();// 得到所有数据
		Workbook workbook = new HSSFWorkbook();// 创建一个工作薄
		Sheet sheet = workbook.createSheet("index");
		Row row = sheet.createRow(0);// 创建标题头 并填充数据
		if (dataInfos != null && dataInfos.size() > 0) {
			List<FiledDataInfo> filedDataInfos = dataInfos.get(0)
					.getFiledDataInfos();
			for (int i = 0; i < filedDataInfos.size(); i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(filedDataInfos.get(i).getFiledName());
			}
			// 添加详细数据
			for (int j = 1; j <= dataInfos.size(); j++) {// 得到每一行数据
				Row rowData = sheet.createRow(j);
				List<FiledDataInfo> fileds = dataInfos.get(j-1)
						.getFiledDataInfos();
				for (int m = 0; m < fileds.size(); m++) {
					Cell cell = rowData.createCell(m);// 单元格数据
					cell.setCellValue(fileds.get(m).getFiledValue());// 单元格数据
				}
			}
		}
		ByteArrayOutputStream os=null;
		try {
//			File file=new File("C:\\system.xls");
//			OutputStream stream=new FileOutputStream(file);
//			workbook.write(stream);
			 os = new ByteArrayOutputStream();
			workbook.write(os);
			byte[] bb=os.toByteArray();//将生成的文件转成二进制byte[]
			dpi.setByteVal(bb);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
					log.error("关闭IO流异常!",e);
				}
			}
		}
		return dpi;
	}
}
