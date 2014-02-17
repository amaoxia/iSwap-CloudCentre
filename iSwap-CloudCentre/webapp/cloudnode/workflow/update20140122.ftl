<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>
 <div class="pop_01">
<div id="wpages" class="wpages">
<div class="pop_mian wpage">
<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
  <div class="main"><div>
   <form name="save" id="saveForm" action="${path}/cloudnode/workflow/workflow!update.action" method="post">
   <input type="hidden" name="deptId" value="${deptId?default('')}"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td align="center" valign="middle" class=""  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
			<tr>
			  <td  height="100%" valign="top" ><div class="">
				<ul class="item1_c">
					<li>
					  <p><b>*</b>流程中文名称：</p>
					  <span>
					  <input type="hidden" value="${id}" name="id" id="id"/>
					  <input type="text" size="30" name="workFlowName" id="workFlowName" value="${workFlowName?default('')}"/>
					  </span> 
					 <span> <div id="workFlowNameTip"></div></span>
					</li>
					<li class="item2_bg">
					  <p><b>*</b>流程英文名称：</p>
					  <span>
					  <input type="text" size="30" id="workFlowCode" name="workFlowCode" value="${workFlowCode?default('')}"/>
					  </span> 
					   <span> <div id="workFlowCodeTip"></div></span>
					  </li>
					<li >
					  <p><b>*</b>流程类型：</p>
					  <span>
					  <input name="wfType" type="radio" value="1" <#if wfType=='1'> checked="checked"</#if>/>
                    	  发送 &nbsp;&nbsp;
                      <input name="wfType" type="radio" value="0" <#if wfType=='0'> checked="checked"</#if>/>
                     	 接收
                      </span>
					</li>
					  <li class="item2_bg">
					  <p><b>*</b>所属应用：</p>
					  <span>
					  <select name="appMsg.id" id="appMsg" style="width:200px" onchange="getNode()">
						<option value="">--请选择--</option>
						<#list appMsgList as app>
						<option value="${app.id}" <#if appMsg.id==app.id>selected</#if> >${app.appName?default('')}</option>
						</#list>
						</select>
					  </span>
					  <span> <div id="appMsgTip"></div></span>
					</li>
					<li>
					  <p><b>*</b>所属前置机：</p>
					  <span>
					  <select name="cloudNodeInfo.id" id="cloudNodeInfoId"  style="width:200px">
						<#if cloudNodeInfo?exists>
						<option value="${cloudNodeInfo.id}">${cloudNodeInfo.nodesName?default('')}</option>
						</#if>
						</select>
					  </span>
					  <span id="cloudNodeInfoIdTip"></span>
					</li>
					<#--
					<li class="item2_bg">
					  <p><b>*</b>所属部门：</p>
					  <span>
					  <select name="sysDept.id" id="deptId" style="width:200px" >
					  <#if entityobj.sysDept?exists>
					  <option value="${sysDept.id}" >${sysDept.deptName}</option>
					  </#if>
					  </select>
					  </span>
					   <span> <div id="deptIdTip"></div></span>
					</li>
					-->
					<li id="target" class="item2_bg"> <#-- <#if wfType=='0'> style="display:none"</#if> -->
					  <p><b>*</b>选择指标：</p>
					  <span>
					  <select name="itemId" id="itemId" style="width:200px" >
					  <option value="">--请选择--</option>
					  <#list changeItems as data>
					  	<option value="${data.id}" <#if itemId?exists><#if (data.id?c)==itemId>selected</#if></#if>>${data.itemName?default('(空)')}</option>
					  </#list>
					  </select>
					  </span>
					   <span> <div id="itemIdTip"></div></span>
					</li>
					<#--
					<li class="item2_bg" id="dataType">
					  <p><b>*</b>初始数据来源类型：</p>
					  <span>
					   <input name="dataType" type="radio" value="0" <#if dataType=='0'> checked="checked"</#if>/>
                     	 数据库数据&nbsp;&nbsp;
                      <input type="radio" name="dataType" value="1" <#if dataType=='1'> checked="checked"</#if>/>
                      	文档数据 </span>
					</li>
					-->
					<!--
					<li id="dsType" name="dsType" class="">
					  <p><b>*</b>数据源类型：</p>
					  <span>
                     	<input type="radio" name="dataType" value="0" <#if entityobj.dataType?exists><#if entityobj.dataType='0'>checked="checked"</#if></#if>/>
                      	数据库数据源&nbsp;
                      	<input type="radio" name="dataType" value="1" <#if entityobj.dataType?exists><#if entityobj.dataType='1'>checked="checked"</#if></#if>/>
                     	 FTP数据源 &nbsp;
                     	 <input type="radio" name="dataType" value="2" <#if entityobj.dataType?exists><#if entityobj.dataType='2'>checked="checked"</#if></#if>/>
                     	 MQ数据源&nbsp;
                     	 <input type="radio" name="dataType" value="3" <#if entityobj.dataType?exists><#if entityobj.dataType='3'>checked="checked"</#if></#if>/>
                     	 WS数据源&nbsp;
                     	 <input type="radio" name="dataType" value="4" <#if entityobj.dataType?exists><#if entityobj.dataType='4'>checked="checked"</#if></#if>/>
                     	 MONGODB数据源
                     	 </span>
					</li>
					<li  id="target" class="item2_bg"> 
					  <p><b>*</b>选择数据源：</p>
					  <span>
					  <select name="dsId" id="dsId" style="width:200px" >
					  </select>
					  </span>
					   <span> <div id="itemIdTip"></div></span>
					</li>
					-->
				  </ul>
				</div></td>
			</tr>
		  </table></td>
	  </tr>
	</table>
</form>
</div>
</div>
<div class="clear"></div>
</div>
</div>
  <div class="wpage" id="deptTree" style="margin-left:10px;text-align:left;background:url(${path}/images/common_menu_bg.jpg) #CFE1ED bottom repeat-x" >
  </div>
 </div>
</div>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<!--窗体滑动-->
<#include "/common/commonSilde.ftl">
<!--增删查改-->
<#include "/common/commonUd.ftl">
 <!--验证js-->
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/cloudnode/workflowValidator.js'></script> 
<script type="text/javascript">
		$(function(){
			$("#workFlowCode").defaultPassed();
			$("#itemId").attr("disabled",true).unFormValidator(true);
			var checkdefault=$("input[name='wfType'][checked]").val();
				if(checkdefault==0){
				//$("#itemId").attr("disabled",true).unFormValidator(true);
					//$("#target").css("display","none"); 
					//$("#dataType").removeClass();
					}else{
					//$("#itemId").attr("disabled",false).unFormValidator(false);
					//$("#target").removeAttr("style");
					//$("#dataType").addClass("item2_bg") 
					}
			$("input[name='wfType']").click( function() { 
					var wfType= $("input[name='wfType'][checked]").val();
					if(wfType==0){
					//$("#itemId").attr("disabled",true).unFormValidator(true);
					//$("#target").css("display","none"); 
					//$("#dataType").removeClass();
					}else{
					//$("#itemId").attr("disabled",false).unFormValidator(false);
					//$("#target").removeAttr("style");
					//$("#dataType").addClass("item2_bg"); 
					}
				}); 
				$("input[type='radio'][name='dataType']:checked").trigger("click");
		})

 		$("input[type='radio'][name='dataType']").click(function(){
			var deptId = '${deptId?default('')}';
			if(deptId){}else{
				alert("请先选择部门！");
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
					var options = "";
					if(result){
						var resobj = eval("("+result+")");
						$.each(resobj, function(index, obj){
							options += "<option value='"+obj.id+"'";
							<#if entityobj.dsId?exists>
								if('${entityobj.dsId}'==obj.id)options += " 'selected' ";
							</#if>
							options +=">"+obj.name+"</option>";
						});
					}
					$('#dsId').html(options);
				});
			}
		});
	 
function close(){
	 dg.cancel();
   	dg.curWin.location.reload(); 
}
</script>
</body>
</html>
