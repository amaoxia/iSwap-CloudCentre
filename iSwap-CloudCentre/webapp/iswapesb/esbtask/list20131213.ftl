<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#global path = request.getContextPath() >
		<#include "/common/taglibs.ftl">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>	
	</head>
	<body>
		<form action="${path}/iswapesb/esbtask/esbTaskAction!list.action" method="post" name="pageForm" id="pageForm">
			<div class="common_menu">
				<div class="c_m_title">
					<img src="${path}/images/title/img_05.png"  align="absmiddle" />
					任务定制
				</div>
		      	<div class="c_m_btn"> 
		        	<span class="cm_btn_m">
			          <a href="javascript:void(0)" onclick="opdg('${path}/iswapesb/esbtask/esbTaskAction!addView.action','新建任务',600,550);" id="hz0">
				          <b>
					          <img src="${path}/images/cmb_xj.gif" class="cmb_img" />
					                    新建任务
					          <a href="javascript:void(0)">
					          	<img src="${path}/images/bullet_add.png" class="bullet_add" />
				         	  </a>
			          	  </b>
		          	  </a>
		          	</span>
		          	<span class="cm_btn_m">
		          		&nbsp;
		          	</span>
		        </div>
		    </div>
	    	<div class="main_c">
	           <table class="main_drop">
		        <tr>
		          <td align="right">
				  	任务名称：
				  	<input name="taskName" onkeypress="showKeyPress()" value="${taskName?default("")}" type="text" />
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
		          <th>流程</th>
				  <th>生效时间</th>
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
		          <td class="trbg"><input name="ids" type="checkbox" value="${entity.id}" /></td>
				  <td>${i+(page.index-1)*10}</td>
				  <td>${entity.taskName?default('')}</td>
		          <td>${entity.workFlow.workFlowName?default('')}</td>
				  <td>${entity.startDate?default('')}</td>
				  <td>
				  	 <#if entity.status =='0'><span style="color: red;">禁用</span></#if> 
				     <#if entity.status =='1'> 启用</#if> 
				  </td>
				  <td>
				     <#if entity.type =='1'>秒</#if> 
				     <#if entity.type =='2'>分</#if> 
				     <#if entity.type =='3'>时</#if> 
				     <#if entity.type =='4'>天</#if> 
				     <#if entity.type =='5'>周</#if> 
				     <#if entity.type =='6'>月</#if> 
				  </td>
				  <td>${entity.times?default('')}</td>
				 
		          <td>
				  	<#if entity.status=='1'>
				  		<a id="hz8" href="javascript:void(0)" disabled="disabled"  class="tabs1_cz">
			                  <img src="${path}/images/small9/s_xiuzheng.gif" />  编辑
			            <a id="hz4" href="javascript:undeploy('${entity.id}');" class="tabs1_cz" ><img src="../../images/small9/s_quxiaobushu.gif" />禁用</a>&nbsp;
				    </#if>
				    <#if entity.status=='0'>
				    	<a href="javascript:opdg('${path}/iswapesb/esbtask/esbTaskAction!updateView.action?esbTaskMsg.id=${entity.id}','编辑',820,550)" class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />编辑</a> 
				    	<a id="hz4" href="javascript:deploy('${entity.id}');" class="tabs1_cz" ><img src="../../images/small9/s_quxiaobushu.gif" />启用</a>&nbsp;
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
