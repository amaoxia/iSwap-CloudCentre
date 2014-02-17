<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>修改指标</title>
		<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	</head>
	<body>
		<div class="pop_01" id="wpageslide" style="width:620px;height:520;overflow-x:hidden;overflow-y:scroll;">
			<div id="wpages" class="wpages">
		    	<div class="pop_mian wpage">
					<form method="post" action="${path}/exchange/receiveItem/receiveItem!update.action" id="saveForm">
						<@s.token/>
						<input type="hidden"  name="itemType" value="2"/>
					    <table width="100%" border="0" cellspacing="0" cellpadding="0">
					      <tr>
					        <td align="center" valign="middle" class="pm01_c"  height="100%">
					        	<table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
						            <tr>
						              <td  height="100%" valign="top" >
						              <div class="item1">
						                  <ul class="item1_c">
						                    <li class="item_bg">
						                      <p>应用名称:</p>
						                      <span>
						                      <input type="text" size="30" value="${appItemExchangeConf.appMsg.appName}" disabled="true"/>
						                      </span>
						                    </li>
						                    <li>
						                      <p>指标名称:</p>
						                      <span>
						                      <input type="text" size="30" value="${appItemExchangeConf.appItem.appItemName}" disabled="true"/>
						                      </span>
						                    </li>
						                    <li >
						                      <p>所属部门:</p>
												<span>
						                 			  <input type="text" id="deptName" size="30" disabled="disabled" value="${sysDept.deptName}"/> 
						                      	</span>
						                    </li>
						                    <li >
						                      <p>业务类型:</p>
												<span>
						                 			  <input type="text" id="deptName" size="30" disabled="disabled" value="${itemTypeStr}"/> 
						                      	</span>
						                    </li>
						                    <li >
						                      <p>数据提供部门:</p>
												<span>
						                 			  <input type="text" id="deptName" size="30" disabled="disabled" value="${appItemExchangeConf.sendDept.deptName}"/> 
						                      	</span>
						                    </li>
						                 	<li class="item_bg">
						                    	<p>交换类型:</p>
						                        <span id="dataTypeSpan">
							                    	<select name="dataType" id="dataType" onchange="changeData(this)" style="width:200px">
								                    	<option value="">请选择</option>
								                        <option value="0" <#if dataType=='0'>selected="selected"</#if>>文档</option>
									                    <option value="1" <#if dataType=='1'>selected="selected"</#if>>数据库</option>
								                    </select>
							                    </span>
							                    <span><div id="dataTypeTip"></div></span>
							                </li>
							                <li>
							                	<span>
							                                                          文档结构类型
									                <select name="dataStructure"  id="dataStructureSel" onchange="addItem(this)" >
									                      <option value="">请选择</option>
									                      <option value="0">非结构化</option>
									                      <option value="1">结构化</option>
								                    </select>
								                  	    文件类型:
								                    <select name="dataValue" id="dataValueSel">
								                      	<option value="">请选择</option>
								                    </select>
							                 	</span>
							                </li>
							                <li id="dsTypeLi" name="dsType" class="item_bg">
											  <p><b>*</b>数据源类型：</p>
											  <span>
						                     	<input type="radio" name="dsType" value="0"/>
						                      	数据库数据源&nbsp;
						                      	<input type="radio" name="dsType" value="1"/>
						                     	 FTP数据源 &nbsp;
						                     	 <input type="radio" name="dsType" value="2"/>
						                     	 MQ数据源&nbsp;
						                     	 <input type="radio" name="dsType" value="3"/>
						                     	 WS数据源&nbsp;
						                     	 <input type="radio" name="dsType" value="4"/>
						                     	 MONGODB数据源
						                     </span>
											</li>
											<li id="dataSourceLi" name="dataSourceLi"> 
											  <p><b>*</b>数据源：</p>
											  <span>
												  <select name="dataSource.id" id="dataSourceIdSel" style="width:200px" >
												  </select>
											  </span>
											   <span> <div id="dataSourceIdSelTip"></div></span>
											</li>
							                <li id="tableNameLi" class="item_bg">
							                      <p>表名:</p>
							                      <span>
							                      <input type="text" name="tableName" id="tableNameInput"  size="30" value="${tableName?default('')}"/></span>
							                      <span><div id="tableNameTip"></div></span>
							                 </li>
							             </ul>
							          </div>
							        </td>
							     </tr>
							 </table>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<ul id="wpagebtns" class="wpagebtns">
		<li></li>
		<li></li>
	</ul>
	</div>
	<#include "/common/commonSilde.ftl">
	<#include "/common/commonUd.ftl">
	<#include "/common/commonValidator.ftl">
	<script type='text/javascript' src='${path}/js/validator/metaData/item.js'></script> 
 	<!-- 窗体公共方法-->
	<script type="text/javascript">
		//数据类型操作
		function  changeData(obj){
			 var dataStructureSel=$("#dataStructureSel");
		     var dataSourceNameSel=$("#dataSourceNameSel");
			 if($(obj).val()=='0'){//如果是文档数据类型
				$("#dataStructureSel").attr("disabled",false);
				$("#dataValueSel").attr("disabled",false);
			    $("#dataSourceLi").attr("disabled",true);
			    $("#tableNameInput").attr("disabled",true);
			   
			    dataStructureSel.empty();
			    //dataStructure.find("option:first-child~option").remove();
			    dataStructureSel.append('<option value="">请选择</option>'); 
			    dataStructureSel.append('<option value="0">非结构化数据</option>'); 
			    dataStructureSel.append('<option value="1">结构化数据</option>'); 
		 	 }else if($(obj).val()=='1'){//如果是数据库数据类型
				$("#dataStructureSel").attr("disabled",true);
				$("#dataValueSel").attr("disabled",true);
			    $("#dataSourceLi").attr("disabled",false);
			    $("#tableNameInput").attr("disabled",false);
			}
		}
		//给select添加item
		function addItem(obj){
	       var dataValueSel=$("#dataValueSel");
			if($(obj).val()=='0'){//如果是非结构化数据类型
				dataValueSel.empty();
				dataValueSel.append('<option value="">请选择</option>'); 
		    	dataValueSel.append('<option value="doc" selected="true">doc,docx</option>'); 
		 	 }else if($(obj).val()=='1'){//如果是结构化数据类型
				dataValueSel.empty();
				dataValueSel.append('<option value="">请选择</option>'); 
		    	dataValueSel.append('<option value="xml" selected="true">xml</option>'); 
		    	dataValueSel.append('<option value="excel">xls,xlsx</option>'); 
		    	dataValueSel.append('<option value="csv">csv</option>'); 
			}
		}
	</script>
	<script type="text/javascript"> 
	$(document).ready(function(){
		
		//大小写转换
		$('#itemCode').keypress(function(e) {     
		    var keyCode= event.keyCode;  
		    var realkey = String.fromCharCode(keyCode).toUpperCase();  
		    $(this).val($(this).val()+realkey);  
		    event.returnValue =false;  
		});
		
		var dataType = '${dataType?default('')}';
		var dataStructure = '${dataStructure?default('')}';
		var dsType = '${dsType?default('')}';
		var dataSource = {};
		dataSource.id = '<#if datSource?exists>${datSource.id?default('')}</#if>';
		
		if(dataType=='0'){
			$("#dataStructureSel option[value='dataStructure']").attr("selected","selected");
		}
		
		$("input[type='radio'][name='dsType']").click(function(){
			var deptId = $('#deptId').val();
			if(deptId){}else{
				return;
			}
			var value = $(this).attr('value');
			var url = "";
			switch(value){
				case '0':
				  url = "${path}/cloudnode/datasource/datasource!getDataSourceJsonStr.action";
				  break;
				case '1':
				  url = "${path}/cloudnode/ftplisten/ftplisten!getFtpDataSourceJsonStr.action";
				  break;
				case '2':
				  url = "${path}/cloudnode/messagelisten/messagelisten!getMQDataSourceJsonStr.action";
				  break;
				case '3':
				  url = "${path}/iswapmq/external/webservice/webInfoAction!getWSDataSourceJsonStr.action";
				  break;
				case '4':
				  url = "${path}/cloudnode/cloudnodeListen/cloudnodeListen!getMongoDataSourceJsonStr.action";
			}
			if(url){
				$.post(url,{deptId:deptId},function(result, status){
					var options = "<option value=''>请选择...</option>";
					if(result){
						var resobj = eval("("+result+")");
						$.each(resobj, function(index, obj){
							options += "<option value='"+obj.id+"'";
							if(obj.id==dataSource.id){
								options += " selected='selected' ";
							}
							options += ">"+obj.name+"</option>";
						});
					}
					$('#dataSourceIdSel').html(options);
				});
			}
		});
		if(dsType){
	  	  $("input[type='radio'][name='dsType'][value='"+dsType+"']").trigger("click");
	    }
	});
	</script>
	</body>
	</html>