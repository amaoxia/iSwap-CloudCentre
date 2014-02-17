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
		  <!--<style>
		  /* 修改操作按钮样式 2011-11-2 zhangbin*/
		  	td#functionButtonArea table {
		  		background:none;
		  		margin-top:0
		  	}
		  	td#functionButtonArea table a {
		  		border:#b4b4b4 1px solid
		  	}
		  	td#functionButtonArea table td {
		  		border-top:none
		  	}-->
		  </style>
		<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>	
	</head>
	<body>
		<form action="esbworkflowAction!list.action" method="post" name="pageForm" id="pageForm">
		    <div class="common_menu">
			      <div class="c_m_title">
			      	<img src="${path}/images/title/img_05.png"  align="absmiddle" />
			     	 流程定制
			      </div>
			      <div class="c_m_btn"> 
			        <span class="cm_btn_m">
			          <a href="javascript:void(0)" onclick="opdg('${path}/iswapesb/workflow/esbworkflowAction!addView.action','新建流程',600,320);" id="hz0" >
				          <b>
				          	<img src="${path}/images/cmb_xj.gif" class="cmb_img" />
				          	新建流程
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
					  	流程名称：
						  <input name="workFlowName" onkeypress="showKeyPress()" value="${workFlowName?default("")}" type="text" /><!--onpaste="return false" -->
						   状态： 
						  <select name="status" > 
						      	<option value=""> --请选择--</option>
				                <option value="1" <#if status?exists><#if status=='1'>selected</#if> </#if>>已部署</option>
				                <option value="0" <#if status?exists><#if status=='0'>selected</#if> </#if>><span style="color: red;">未部署</span></option>
				          </select>
				          <input name="" type="button" value="查询" onclick="subForm('pageForm')" class="btn_s"/>
				      </td>
			        </tr>
			      </table>                              
			      <table class="tabs1"  style="margin-top:0px;">
			        <tr onclick="selectedTD(this);">
			          <th width="3%"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
			          <th width="5%">序号</th>
					  <th width="30%">流程名称</th>
					  <th width="6%">所属应用</th>
			          <th width="5%">状态</th>
					  <th width="8%">创建时间<a href="javascript:void(0)"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
					  <th width="43%">操作</th>
			        </tr>
			         <#assign i = 0>  
			         <#list listDatas as entity>
			         <#assign i = i+1>
			            <#if i%2=0>
					         <tr  onclick="selectedTD(this);">
					    <#else> 
					        <tr class="trbg" onclick="selectedTD(this);">  
					     </#if> 
			          <td class="trbg" width="3%"><input name="ids" type="checkbox" value="${entity.id}" /></td>
					  <td width="5%">${i+(page.index-1)*10}</td>
					  <td width="30%">${entity.workFlowName?default('')}</td>
					  <td width="6%">${entity.appMsg.appName?default('(空)')}</td>
					  <td width="5%">
					  	 <#if entity.status =='0'><span style="color: red;">未部署</span></#if> 
					     <#if entity.status =='1'> 已部署 </#if> 
					  </td>
					  <td width="8%">${entity.createDate?string('yyyy-MM-dd')}</td>
	  			 	  <td width="43%">
				        <#if entity.status=='1'><a href="javascript:void(0)" disabled="disabled"  class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />编辑</a></#if>
				        <#if entity.status=='0'><a href="javascript:opdg('${path}/iswapesb/workflow/esbworkflowAction!updateView.action?esbWorkFlow.id=${entity.id}','定制流程',600,320)" class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />编辑</a></#if>
			          	<#if entity.status=='1'>
			          	<a id="hz8" href="javascript:void(0)" disabled="disabled"  class="tabs1_cz"><img src="${path}/images/small9/s_xiuzheng.gif" />定制流程</a>
			          	</#if> 
						<#if entity.status=='0'>
						<a id="hz8" href="#"  onclick="updateProcess('${entity.id}','000','1');" class="tabs1_cz"><img src="${path}/images/small9/s_xiuzheng.gif" />定制流程</a>
						</#if> 
			          	<#if entity.status=='1'>
			          	<a id="hz4" href="javascript:undeploy('${entity.id}');" class="tabs1_cz" ><img src="${path}/images/small9/s_quxiaobushu.gif" />取消部署</a>
			          	</#if> 
						<#if entity.status=='0'>
						<a id="hz4" href="javascript:deploy('${entity.id}');" class="tabs1_cz" ><img src="${path}/images/small9/s_quxiaobushu.gif" />部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;署</a>
						</#if> 
			          	<#if entity.status=='1'>
			          		<a id="hz5" href="#" class="tabs1_cz" ><img src="${path}/images/small9/down.gif" />导出 </a>
			          	</#if> 
						<#if entity.status=='0'>
			          		<a id="hz5" href="#" class="tabs1_cz" ><img src="${path}/images/small9/down.gif" />导出</a>
			          	</#if> 
			          	<#if entity.status=='1'>
			          		<a href="javascript:opdg('${path}/iswapesb/workflow/esbworkflowAction!testView.action?esbWorkFlow.id=${entity.id}','测试流程',650,510)" class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />测&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;试</a>
			          	</#if> 
						<#if entity.status=='0'>
							<a href="javascript:void(0)" disabled="disabled"  class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />测&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;试</a>
						</#if> 
			            <a id="hz5" href="javascript:void(0);"
		                      onclick="opdg('${path}/iswapesb/workflow/esbworkflowAction!cloneView.action?id='+${entity.id},'克隆流程','650','210');"
		                      class="tabs1_cz"><img src="${path}/images/small9/page_white_clone.gif" />克&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;隆</a>
					  </td>
			        </tr>
			        </#list>
			      </table>
			      <div class="tabs_foot"> 
			      	<span class="tfl_btns"><img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px; margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
			          <a href="javascript:delMany('esbworkflowAction!delete.action');" class="tfl_blink"><b class="hot">删除</b></a>
			          <a href="javascript:delMany('esbworkflowAction!deploy.action?sign=1');" class="tfl_blink"><b class="">部署</b></a>
			          <a href="javascript:delMany('esbworkflowAction!unDeploy.action?sign=0');" class="tfl_blink"><b class="">取消部署</b></a>
			      	</span> 
				    <span class="page pageR">
				      	<@pager.pageTag/>
				    <span>
			      </div>
			     <div class="clear"></div>
		 	 </div>
		<form>
		<#include "/common/commonList.ftl">
		<#include "/common/commonLhg.ftl">
		<script type="text/javascript" src="${path}/js/iswap_table.js"></script>
		<script type="text/javascript">    
				function reloadPage(){
					window.location.href="esbworkflowAction!list.action";
				}
				function deploy(id){
					if(confirm("确定要部署吗？")){
					document.forms['pageForm'].action = "esbworkflowAction!deploy.action?ids="+id+"&sign=1";
		              document.forms['pageForm'].submit();
					}
				}
				function undeploy(id){
					if(confirm("确定要取消部署吗？")){
						document.forms['pageForm'].action = "esbworkflowAction!unDeploy.action?ids="+id+"&sign=0";
		              document.forms['pageForm'].submit();
					}
				}
				function updateProcess(id,deptid,type) {
		          var prem = "wf_id="+id+"&wf_deptid="+deptid+"&wf_type="+type+"";
		          var url = "${path}/workflow/ISwapWorkFlow.html?"+prem;  
		          window.showModalDialog(url, "工作流程编辑器", "dialogWidth:1000px;dialogHeight:768px;center:yes;help:no;scroll:no;status:no;resizable:yes;");
		        }
		        
		        //调整表格可显示高度，否则出不了滚动条
	            function getDivHeight(){
	            	baseTbHeight = document.body.baseTbHeight-28;
	            	var divHeight = $(document.body).height()-$("div .common_menu").eq(0).height()-$("table .main_drop").eq(0).height()-$("div .tabs1").eq(0).height()-$("div .tabs_foot").eq(0).height()-85;
					if(baseTbHeight&&baseTbHeight<divHeight){
						return baseTbHeight;
					}else{
						return divHeight;
					}
	            }
	            
	            function setBaseTbHeight(){
	            	document.body.baseTbHeight=$("table.tabs1").eq(0).height();
	            }
		</script>
	</body>
</html>
