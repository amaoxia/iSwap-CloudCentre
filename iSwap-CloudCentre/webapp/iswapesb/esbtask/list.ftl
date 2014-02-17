<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#global path = request.getContextPath() >
		<#include "/common/taglibs.ftl">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中心任务管理</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>	
	</head>
	<body>
		<form action="${path}/iswapesb/esbtask/esbTaskAction!list.action" method="post" name="pageForm" id="pageForm">
	    	<div class="main_c">
	           <table class="main_drop">
		        <tr>
		          <td align="right">
				  	调度周期： 
				    <select name="type">
				   	　<option value=""> --请选择--</option>
		              <option value="6" <#if type?exists><#if type=='6'>selected</#if></#if> >月</option>
					  <option value="5" <#if type?exists><#if type=='5'>selected</#if></#if> >周</option>
					  <option value="4" <#if type?exists><#if type=='4'>selected</#if></#if> >日</option>
					  <option value="3" <#if type?exists><#if type=='3'>selected</#if></#if> >时</option>
					  <option value="2" <#if type?exists><#if type=='2'>selected</#if></#if> >分</option>
					  <option value="1" <#if type?exists><#if type=='1'>selected</#if></#if> >秒</option>
		            </select>
			    	状态： 
			    	<select name="status" > 
				      	<option value=""> --请选择--</option>
		                 <option value="1" <#if status?exists><#if status=='1'>selected</#if> </#if>>启用</option>
		                <option value="0" <#if status?exists><#if status=='0'>selected</#if> </#if> >禁用</option>
	              	</select>
	            	<input name="" type="button" value="查询" onclick="subForm('pageForm')" class="btn_s"/>
	              </td>
	        	</tr>
		      </table>                              
		      <table class="tabs1"  style="margin-top:0px;">
		        <tr onclick="selectedTD(this);">
		          <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a></th>
		          <th width="28">序号</th>
		          <th>任务名称</th>
		          <th>流程名称</th>
				  <th width="8%">生效时间</th>
		          <th>状态</th>
				  <th>频率类型</th>
				  <th>频率</th>
				  <th>操作</th>
		        </tr>
		         <#assign i = 0>  
		        <#list listDatas as entity>
		         <#assign i = i+1>
		            <#if i%2=0>
				         <tr  onclick="selectedTD(this);">
				    <#else> 
				        <tr class="trbg" onclick="selectedTD(this);">  
				     </#if> 
		          <td class="trbg"><input name="ids" type="checkbox" value="${entity.id?default('')}" /></td>
				  <td>${i+(page.index-1)*10}</td>
				  <td width="25%">${entity.taskName?default('')}</td>
			      <td width="10%"><#if entity.workFlow?exists>${entity.workFlow.workFlowName?default('')}<#else>(空)</#if></td>
				 <td>${entity.startDate?default('')}</td>
				  <td>
					  	<#if entity.status?exists>
					  	 <#if entity.status =='0'><span style="color: red;">禁用</span></#if> 
					     <#if entity.status =='1'> 启用</#if> 
					    </#if>
					  </td>
				  <td>
				  	<#if entity.type?exists>
				     <#if entity.type =='1'>秒</#if> 
				     <#if entity.type =='2'>分</#if> 
				     <#if entity.type =='3'>时</#if> 
				     <#if entity.type =='4'>天</#if> 
				     <#if entity.type =='5'>周</#if> 
				     <#if entity.type =='6'>月</#if>
				     </#if> 
				  </td>
				  <td>${entity.times?default('')}</td>
		          <td>
				  	<a href="javascript:void(0);" <#if (entity.status?exists&&entity.status=='1')>style="color:#ccc;"</#if> <#if (entity.id?exists)>onclick="opdg('${path}/iswapesb/esbtask/esbTaskAction!updateView.action?deptId=${deptId?default('')}&id=${entity.id?default('')}','定制流程任务调度','620','560');" <#else>onclick="opdg('${path}/iswapesb/esbtask/esbTaskAction!addView.action?workFlowId=${entity.workFlow.id}','定制流程任务调度','620','560');" </#if>class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />定制任务</a> 
					  	<#if entity.id?exists>
						  	<#if entity.status=='1'><a  href="javascript:deploy('${entity.id}','0');" class="tabs1_cz" ><img src="../../images/small9/s_quxiaobushu.gif" />禁用</a>&nbsp;</#if>
						    <#if entity.status=='0'><a href="javascript:deploy('${entity.id}','1');" class="tabs1_cz" ><img src="../../images/small9/s_quxiaobushu.gif" />启用</a>&nbsp;</#if>
					  	</#if>
				  </td>
		        </tr>
		        </#list>
		      </table>
		      <div class="tabs_foot"> 
		       <span class="tfl_btns">
		           <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
		           <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
		           <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
		          	 <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('esbTaskAction!delete.action')"><b class="hot">删除</b></a>
		          	 <a href="javascript:delMany('esbTaskAction!deploy.action?sign=1');" class="tfl_blink"><b class="">启用</b></a>
		             <a href="javascript:delMany('esbTaskAction!unDeploy.action?sign=0');" class="tfl_blink"><b class="">禁用</b></a>
	           </span>
	           <span class="page pageR">
	           	<@pager.pageTag/>
	           </span>
	          </div>
	    	</div>
	    	<div class="clear"></div>
	  </div>
	</form>
	<#include "/common/commonList.ftl">
	<#include "/common/commonLhg.ftl">
	<script type="text/javascript">    
	 function reloadPage(){
		window.location.href="esbTaskAction!list.action";
	}
	function deploy(id){
		if(confirm("确定要启用吗？")){
		document.forms['pageForm'].action = "esbTaskAction!deploy.action?esbTaskMsg.id="+id;
	    document.forms['pageForm'].submit();
		}
	}
	function undeploy(id){
		if(confirm("确定要禁用吗？")){
		document.forms['pageForm'].action ="esbTaskAction!unDeploy.action?esbTaskMsg.id="+id;
	    document.forms['pageForm'].submit();
		}
	}
	</script>
	</body>
</html>
