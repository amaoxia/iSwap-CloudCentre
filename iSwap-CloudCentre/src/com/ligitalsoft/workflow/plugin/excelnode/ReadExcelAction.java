package com.ligitalsoft.workflow.plugin.excelnode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jbpm.api.activity.ActivityExecution;

import com.common.utils.common.StringUtils;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.FiledDataInfo;
import com.ligitalsoft.workflow.plugin.model.RowDataInfo;

/**
 * 读取Excel文件
 * 
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-9-21 下午02:11:35
 * @Team 研发中心
 */
public class ReadExcelAction extends PluginActionHandler {
	private static final long serialVersionUID = -287909076176032777L;
	public String data_inputVar;
	public String readRow;
	public String head;
	public String readExcel_outVar;
	public String excelTotal_outVar;// 输出excel总数

	@SuppressWarnings("unchecked")
	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		long start = System.currentTimeMillis();
		log.info("开始解析Excle文件【Head:" + head + "】......");
		try {
			List<DataPackInfo> dpInfoList = (List<DataPackInfo>) this
					.getCacheInfo(data_inputVar);
			List<DataPackInfo> dpInfoObjs = new ArrayList<DataPackInfo>();
			int n = 1;
			for (DataPackInfo dpInfo : dpInfoList) {
				byte[] bytes = dpInfo.getByteVal();
				DataPackInfo dp = this.ExcelToDataPackInfo(bytes, context);
				dpInfoObjs.add(dp);
				log.info("第【" + n + "】个Excel文件解析成功,数据总条数为：【" + dp.getRowDataList().size() + "】条！");
				n++;
			}
			this.putCacheInfo(readExcel_outVar, dpInfoObjs);
			long end = System.currentTimeMillis();
			log.info("完成解析:Excel文件【Head:" + head + "】使用时间：" + (end - start)
					+ " ms");
		} catch (Exception e) {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bo));
			log.error("读取文件出错!", e);
			throw new ActionException(e);
		}
	}

	public DataPackInfo ExcelToDataPackInfo(byte[] bytes,
			ActivityExecution context) throws ActionException {
		DataPackInfo dataPackInfo = new DataPackInfo();
		List<RowDataInfo> dataInfos = new ArrayList<RowDataInfo>();
		InputStream stream = new ByteArrayInputStream(bytes);
		DecimalFormat df = new DecimalFormat("#");
		try {
			Workbook workbook = WorkbookFactory.create(stream);
			// int leng = workbook.getNumberOfSheets();// 得到sheet总数
			for (int i = 0; i < 1; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				if (sheet != null) {// 工作簿对象不为空
					// 读取头
					int total = sheet.getLastRowNum();// 得到所有行数
					// context.setVariable(excelTotal_outVar,
					// total);//向变量里添加excel总数
					int startRow = 1;
					if (!StringUtils.isBlank(readRow)) {
						startRow = Integer.parseInt(readRow);
						if (startRow <= 1) {
							startRow = 1;
						} else {
							startRow -= 1;
						}
					}
					Row row = sheet.getRow(startRow);// 读取头部即第N行
					if (row != null) {
						if (!StringUtils.isBlank(head)) {
							String args[] = head.split(",");
							// 读取内容 排除一行
							for (int j = startRow; j <= total; j++) {
								RowDataInfo data = new RowDataInfo();// 一条数据
								List<FiledDataInfo> filedDataInfos = new ArrayList<FiledDataInfo>();// 单个字段信息
								Row content = sheet.getRow(j);//

								if (RowisNull(content, args.length)) {
									for (int m = 0; m < args.length; m++) {// 读取单元格
										if (!StringUtils.isBlank(args[m])) {
											FiledDataInfo dataInfo = new FiledDataInfo();
											dataInfo.setFiledName(args[m]);
											int rowcount = j + 1;
											int celcount = m + 1;
											log.info("解析Excel第[" + rowcount
													+ "]行第[" + celcount
													+ "]列,字段名称为：" + args[m]
													+ " ");
											Cell cell = content.getCell(m);
											if (cell != null) {
												int cellType = cell
														.getCellType();
												switch (cellType) {
												case HSSFCell.CELL_TYPE_NUMERIC:
													if (HSSFDateUtil
															.isCellDateFormatted(cell)) {// 判断是不是日期类型
														String date = DateUtil
																.formatDate(
																		cell.getDateCellValue(),
																		"yyyy-MM-dd");
														dataInfo.setFiledValue(date);
													} else {
														String strCell = df
																.format(cell
																		.getNumericCellValue());// 将所有科学计算法的数据进行格式转换
														dataInfo.setFiledValue(strCell);
													}
													break;
												case HSSFCell.CELL_TYPE_STRING:
													dataInfo.setFiledValue(cell
															.getStringCellValue());
													break;
												case HSSFCell.CELL_TYPE_FORMULA:
													dataInfo.setFiledValue(cell
															.getNumericCellValue()
															+ "");
													break;
												case HSSFCell.CELL_TYPE_BOOLEAN:
													dataInfo.setFiledValue(cell
															.getBooleanCellValue()
															+ "");
													break;
												case HSSFCell.CELL_TYPE_ERROR:
													dataInfo.setFiledValue(cell
															.getErrorCellValue()
															+ "");
													break;
												case HSSFCell.CELL_TYPE_BLANK:
													dataInfo.setFiledValue("");
												default:
													dataInfo.setFiledValue(cell
															.getStringCellValue());
												}
											} else {
												dataInfo.setFiledValue("");
											}
											filedDataInfos.add(dataInfo);// 添加数据
										}
									}
									data.setFiledDataInfos(filedDataInfos);// 读取一行之后
									dataInfos.add(data);// 放入到数据包里面
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dataPackInfo.setRowDataList(dataInfos);// list
		return dataPackInfo;
	}

	/**
	 * is null 添加说明
	 * 
	 * @return
	 * @author hudaowan
	 * @date 2011-11-28 下午09:10:38
	 */
	public static boolean RowisNull(Row content, int len) {
		boolean fa = false;
		for (int i = 0; i < len; i++) {
			Object obj;
			Cell cell = content.getCell(i);
			if (cell == null) {
				continue;
			}
			switch (cell.getCellType()) {
			case 0:// Numeric
				obj = cell.getNumericCellValue();
				break;
			case 1:// String
				obj = cell.getStringCellValue();
				break;
			default:
				obj = cell.getStringCellValue();
			}
			if (obj != null && !StringUtils.isBlank(obj.toString().trim())) {
				fa = true;
				break;
			}
		}
		return fa;
	}

}
