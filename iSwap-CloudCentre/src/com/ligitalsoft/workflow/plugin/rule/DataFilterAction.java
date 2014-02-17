package com.ligitalsoft.workflow.plugin.rule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;

import org.jbpm.api.activity.ActivityExecution;

import com.common.utils.common.StringUtils;
import com.common.utils.date.DateUtil;
import com.ligitalsoft.workflow.exception.ActionException;
import com.ligitalsoft.workflow.plugin.PluginActionHandler;
import com.ligitalsoft.workflow.plugin.model.DataFilterObject;
import com.ligitalsoft.workflow.plugin.model.DataPackInfo;
import com.ligitalsoft.workflow.plugin.model.FiledDataInfo;
import com.ligitalsoft.workflow.plugin.model.RowDataInfo;

/**
 * 对象数据过滤处理
 * 
 * @Company 中海纪元
 * @author fangbin
 * @version iSwap V6.0 数据交换平台
 * @date 2012-01-10下午03:20:22
 * @Team 研发中心
 */
public class DataFilterAction extends PluginActionHandler {
	private static final long serialVersionUID = -6937898048966411835L;
	public String data_inputVar;// 输入数据
	public String where_inputVar;// 输入条件
	public String data_outVar;// 输出符合条件的数据
	public String errdata_outVar;// 输出不符合条件的数据
	public String errcount_outVar;// 输出不符合条件的日志信息
	public String fileName_inputVar;// 文件的名称

	@Override
	public void doexecute(ActivityExecution context) throws ActionException {
		log.info("开始将数据过滤......");
		List<DataPackInfo> dpInfoList = (List<DataPackInfo>) this
				.getCacheInfo(data_inputVar);

		List<DataFilterObject> dfoList = new ArrayList<DataFilterObject>();
		dfoList = (List<DataFilterObject>) this.getStringtoList(where_inputVar);
		// 符合条件的数据
		List<DataPackInfo> dataPackInfoList = new ArrayList<DataPackInfo>();
	
		// 不符合条件的数据
		List<DataPackInfo> errDataPackInfoList = new ArrayList<DataPackInfo>();
		

		int succCount = 0;
		int total = 0;
		int errCount = 0;
		String errorLog = "";
		if (!StringUtils.isBlank(where_inputVar)) {
			for (DataPackInfo dataPackInfo : dpInfoList) {
				//存放合条件的对象
				List<RowDataInfo> rowDataInfoList = new ArrayList<RowDataInfo>();
				DataPackInfo dpi = new DataPackInfo();
				//存放不合条件的对象
				List<RowDataInfo> errRowDataInfoList = new ArrayList<RowDataInfo>();
				DataPackInfo errdpi = new DataPackInfo();
				List<RowDataInfo> rdiList = dataPackInfo.getRowDataList();
				total = rdiList.size();
				for (RowDataInfo rdi : rdiList) {
					List<FiledDataInfo> fdis = rdi.getFiledDataInfos();
					boolean isSave = true;
					for (FiledDataInfo filedata : fdis) {
						for (DataFilterObject dfo : dfoList) {
							String name = dfo.getName();
							String value = dfo.getValue();
							String relation = dfo.getRelation();
							String datatype = dfo.getDataType();
							String isnull = dfo.getIsNull();
							String logic = dfo.getLogic();
							if (filedata.getFiledName().equals(name)) {// 判断要查询的字段和传入数据的字段名称是否一致
								if (!StringUtils.isBlank(isnull)) {// 判断是否允许为空
									if ("false".equals(isnull.toLowerCase())) {
										if (StringUtils.isBlank(filedata
												.getFiledValue())) {
											if (!StringUtils.isBlank(logic)) {
												if ("and".equals(logic)) {// 判断逻辑条件是and还是or
													isSave = isSave && false;
												}
												if ("or".equals(logic)) {
													isSave = isSave || false;
												}
											} else {
												isSave = false;
											}
											String err=filedata.getFiledName()+ ":字段数据为空;";
											if(!errorLog.contains(err)){
												errorLog +=err ;
											}
										}
									}
								}
								if (null != datatype
										&& !StringUtils.isBlank(datatype)) {
									if ("date".equals(datatype.toLowerCase())) {// 判断是否为日期类型
										if (null != DateUtil.strToDate(filedata
												.getFiledValue())) {
											if (">".equals(relation)) {
												long filedatatime = DateUtil
														.strToDate(
																filedata.getFiledValue())
														.getTime();
												long paratime = DateUtil
														.strToDate(value)
														.getTime();
												if (paratime - filedatatime > 0) {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && true;
													}
													if ("or".equals(logic)) {
														isSave = isSave || true;
													}
												} else {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && false;
													}
													if ("or".equals(logic)) {
														isSave = isSave || false;
													}
												}
											} else if ("<".equals(relation)) {
												long filedatatime = DateUtil
														.strToDate(
																filedata.getFiledValue())
														.getTime();
												long paratime = DateUtil
														.strToDate(value)
														.getTime();
												if (filedatatime - paratime > 0) {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && true;
													}
													if ("or".equals(logic)) {
														isSave = isSave || true;
													}
												} else {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && false;
													}
													if ("or".equals(logic)) {
														isSave = isSave || false;
													}
												}
											} else if ("=".equals(relation)) {
												if (null!=filedata.getFiledValue()&&filedata.getFiledValue()
														.equals(value)) {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && true;
														}
														if ("or".equals(logic)) {
															isSave = isSave || true;
														}
													} else {
														isSave = isSave && true;
													}
												} else {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && false;
														}
														if ("or".equals(logic)) {
															isSave = isSave || false;
														}
													} else {
														isSave = false;
													}
												}
											} else if ("!=".equals(relation)) {
												if (null!=filedata.getFiledValue()&&!filedata.getFiledValue()
														.equals(value)) {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && true;
														}
														if ("or".equals(logic)) {
															isSave = isSave || true;
														}
													} else {
														isSave = isSave && true;
													}
												} else {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && false;
														}
														if ("or".equals(logic)) {
															isSave = isSave || false;
														}
													} else {
														isSave = false;
													}
												}
											} else {
												if (!StringUtils.isBlank(logic)) {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && true;
													}
													if ("or".equals(logic)) {
														isSave = isSave || true;
													}
												} else {
													isSave = isSave && true;
												}

											}

										} else {
											if("true".equals(isnull)&&StringUtils.isBlank(filedata
												.getFiledValue())){
												if (!StringUtils.isBlank(logic)) {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && true;
													}
													if ("or".equals(logic)) {
														isSave = isSave || true;
													}
												} else {
													isSave = true;
												}
											}else{
											if (!StringUtils.isBlank(logic)) {
												if ("and".equals(logic)) {// 判断逻辑条件是and还是or
													isSave = isSave && false;
												}
												if ("or".equals(logic)) {
													isSave = isSave || false;
												}
											} else {
												isSave = false;
											}
											String err=filedata.getFiledName()+":字段日期格式不正确;";
											if(!errorLog.contains(err)){
												errorLog +=err ;
											}
											}
										}
									} else if ("int".equals(datatype
											.toLowerCase())) {
										try {
											Integer.parseInt(filedata
													.getFiledValue());
											if ("!=".equals(relation)) {
												if (null!=filedata.getFiledValue()&&!filedata.getFiledValue()
														.equals(value)) {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && true;
														}
														if ("or".equals(logic)) {
															isSave = isSave || true;
														}
													} else {
														isSave = isSave && true;
													}
												} else {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && false;
														}
														if ("or".equals(logic)) {
															isSave = isSave || false;
														}
													} else {
														isSave = false;
													}
												}
											} else if ("=".equals(relation)) {
												if (null!=filedata.getFiledValue()&&filedata.getFiledValue()
														.equals(value)) {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && true;
														}
														if ("or".equals(logic)) {
															isSave = isSave || true;
														}
													} else {
														isSave = isSave && true;
													}
												} else {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && false;
														}
														if ("or".equals(logic)) {
															isSave = isSave || false;
														}
													} else {
														isSave = false;
													}
												}
											} else if (">".equals(relation)) {
												int fileValue = Integer
														.parseInt(filedata
																.getFiledValue());
												int val = Integer
														.parseInt(value);
												if (val > fileValue) {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && true;
														}
														if ("or".equals(logic)) {
															isSave = isSave || true;
														}
													} else {
														isSave = isSave && true;
													}
												} else {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && true;
														}
														if ("or".equals(logic)) {
															isSave = isSave || true;
														}
													} else {
														isSave = isSave && true;
													}
												}
											} else if (">=".equals(relation)) {
												int fileValue = Integer
														.parseInt(filedata
																.getFiledValue());
												int val = Integer
														.parseInt(value);
												if (val >= fileValue) {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && true;
														}
														if ("or".equals(logic)) {
															isSave = isSave || true;
														}
													} else {
														isSave = isSave && true;
													}
												} else {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && true;
														}
														if ("or".equals(logic)) {
															isSave = isSave || true;
														}
													} else {
														isSave = isSave && true;
													}
												}
											} else if ("<".equals(relation)) {
												int fileValue = Integer
														.parseInt(filedata
																.getFiledValue());
												int val = Integer
														.parseInt(value);
												if (val < fileValue) {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && true;
														}
														if ("or".equals(logic)) {
															isSave = isSave || true;
														}
													} else {
														isSave = isSave && true;
													}
												} else {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && true;
														}
														if ("or".equals(logic)) {
															isSave = isSave || true;
														}
													} else {
														isSave = isSave && true;
													}
												}
											} else if ("<=".equals(relation)) {
												int fileValue = Integer
														.parseInt(filedata
																.getFiledValue());
												int val = Integer
														.parseInt(value);
												if (val <= fileValue) {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && true;
														}
														if ("or".equals(logic)) {
															isSave = isSave || true;
														}
													} else {
														isSave = isSave && true;
													}
												} else {
													if (!StringUtils
															.isBlank(logic)) {
														if ("and".equals(logic)) {// 判断逻辑条件是and还是or
															isSave = isSave && true;
														}
														if ("or".equals(logic)) {
															isSave = isSave || true;
														}
													} else {
														isSave = isSave && true;
													}
												}
											} else {
												if (!StringUtils.isBlank(logic)) {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && true;
													}
													if ("or".equals(logic)) {
														isSave = isSave || true;
													}
												} else {
													isSave = isSave && true;
												}
											}

										} catch (Exception e) {
											if (!StringUtils.isBlank(logic)) {
												if ("and".equals(logic)) {// 判断逻辑条件是and还是or
													isSave = isSave && false;
												}
												if ("or".equals(logic)) {
													isSave = isSave || false;
												}
											} else {
												isSave = false;
											}
											String err=filedata.getFiledName()+":字段类型不正确;";
											if(!errorLog.contains(err)){
												errorLog +=err ;
											}
										}
									} else {// 判断String数据类型
										if ("like".equals(relation)) {
											if (null!=filedata.getFiledValue()&&filedata.getFiledValue()
													.contains(value)) {
												if (!StringUtils.isBlank(logic)) {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && true;
													}
													if ("or".equals(logic)) {
														isSave = isSave || true;
													}
												} else {
													isSave = isSave && true;
												}
											} else {
												if (!StringUtils.isBlank(logic)) {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && false;
													}
													if ("or".equals(logic)) {
														isSave = isSave || false;
													}
												} else {
													isSave = false;
												}
											}
										} else if ("=".equals(relation)) {
											if (null!=filedata.getFiledValue()&&filedata.getFiledValue()
													.equals(value)) {
												if (!StringUtils.isBlank(logic)) {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && true;
													}
													if ("or".equals(logic)) {
														isSave = isSave || true;
													}
												} else {
													isSave = isSave && true;
												}
											} else {
												if (!StringUtils.isBlank(logic)) {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && false;
													}
													if ("or".equals(logic)) {
														isSave = isSave || false;
													}
												} else {
													isSave = false;
												}
											}
										} else if ("!=".equals(relation)) {
											if (null!=filedata.getFiledValue()&&!filedata.getFiledValue()
													.equals(value)) {
												if (!StringUtils.isBlank(logic)) {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && true;
													}
													if ("or".equals(logic)) {
														isSave = isSave || true;
													}
												} else {
													isSave = isSave && true;
												}
											} else {
												if (!StringUtils.isBlank(logic)) {
													if ("and".equals(logic)) {// 判断逻辑条件是and还是or
														isSave = isSave && false;
													}
													if ("or".equals(logic)) {
														isSave = isSave || false;
													}
												} else {
													isSave = false;
												}
											}
										} else {
											if (!StringUtils.isBlank(logic)) {
												if ("and".equals(logic)) {// 判断逻辑条件是and还是or
													isSave = isSave && true;
												}
												if ("or".equals(logic)) {
													isSave = isSave || true;
												}
											} else {
												isSave = isSave && true;
											}
										}

									}
								}
							}
						}
					}
					if (isSave) {
						rowDataInfoList.add(rdi);
						succCount++;
					} else {
						errRowDataInfoList.add(rdi);
						errCount++;
					}

				}
				if(rowDataInfoList.size()>0){
					dpi.setAlias(dataPackInfo.getAlias());
					dpi.setFileName(dataPackInfo.getFileName());
					dpi.setHeadInfo(dataPackInfo.getHeadInfo());
					dpi.setName(dataPackInfo.getName());
					dpi.setSendDate(dataPackInfo.getSendDate());
					dpi.setStatus(dataPackInfo.getStatus());
					dpi.setValue(dataPackInfo.getValue());
					dpi.setErrorInfo(dataPackInfo.getErrorInfo());
					dpi.setType(dataPackInfo.getType());
					dpi.setByteVal(dataPackInfo.getByteVal());
					
					dpi.setRowDataList(rowDataInfoList);
					dataPackInfoList.add(dpi);
				}
				if(errRowDataInfoList.size()>0){
					errdpi.setAlias(dataPackInfo.getAlias());
					errdpi.setFileName(dataPackInfo.getFileName());
					errdpi.setHeadInfo(dataPackInfo.getHeadInfo());
					errdpi.setName(dataPackInfo.getName());
					errdpi.setSendDate(dataPackInfo.getSendDate());
					errdpi.setStatus(dataPackInfo.getStatus());
					errdpi.setValue(dataPackInfo.getValue());
					errdpi.setErrorInfo(dataPackInfo.getErrorInfo());
					errdpi.setType(dataPackInfo.getType());
					errdpi.setByteVal(dataPackInfo.getByteVal());
					errdpi.setRowDataList(errRowDataInfoList);
					errDataPackInfoList.add(errdpi);
				}
			}

			// if(rowDataInfoList.size()>0){
			// putCacheInfo(data_outVar,dataPackInfoList);
			// }
		}
		if(dataPackInfoList.size()>0){
			putCacheInfo(data_outVar, dataPackInfoList);
		}
		if(errDataPackInfoList.size()>0){
			putCacheInfo(errdata_outVar, errDataPackInfoList);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errCount", errCount);
		map.put("succCount", succCount);
		map.put("total", total);
		map.put("errlog", errorLog);
		map.put("filename", (String) context.getVariable("filename"));
		map.put("createDate", new Date());
		context.setVariable(errcount_outVar, map);
		log.info("数据过滤节点共过虑了【" + total + "】条数据,其中符合条件的有【" + succCount + "】条,不符合条件的有【" + errCount + "】条");
	}

	public static List<DataFilterObject> getStringtoList(String jsonString) {
		List<DataFilterObject> dfoList = new ArrayList<DataFilterObject>();
		try {

			JSONArray jsonArray = JSONArray.fromObject(jsonString);
			dfoList = jsonArray.toList(jsonArray, DataFilterObject.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return dfoList;
	}
	
	/**
	 * 判断字符是否存在
	 * @param errlog
	 * @param context
	 * @return
	 */
	public static boolean isExistError(String errlog,String context){
		boolean falg=false; 
		if(!StringUtils.isBlank(errlog)&&!StringUtils.isBlank(context)){
			if(errlog.contains(context.trim())){
				falg=true;
				return falg;
			}
		}
		return falg;
	}

	public static void main(String arg[]) {
		// String aa="{[aaa]>[bb],![cc]=[ddd]}";
		// aa=aa.substring(1, aa.length()-1);
		// String []kv=aa.split(",");
		// for(int i=0;i<kv.length;i++){
		// String key=kv[i].substring(kv[i].indexOf("[")+1,kv[i].indexOf("]"));
		// String
		// sign=kv[i].substring(kv[i].indexOf("]")+1,kv[i].lastIndexOf("["));
		// String
		// val=kv[i].substring(kv[i].lastIndexOf("[")+1,kv[i].lastIndexOf("]"));
		// String luoji="";
		// if(kv[i].indexOf("[")>0){
		// luoji=kv[i].substring(0,kv[i].indexOf("["));
		// }
		// System.out.println("key="+key+" sign="+sign+" val="+val);
		// System.out.println(luoji);
		// }
//		String where = "[{name='fangbin',value='100'},{name='zhangs',value='100'}]";
//		List<DataFilterObject> acc = new ArrayList<DataFilterObject>();
//		acc = getStringtoList(where);
		String abc="123456abc;";
		String ab="abc;";
		System.out.println("1111");

	}
}
