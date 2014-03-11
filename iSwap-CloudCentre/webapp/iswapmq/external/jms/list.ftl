<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<#include "/common/taglibs.ftl">
	<#import "pager.ftl" as pager>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
		<#include "/common/commonLhg.ftl">
		<#include "/common/commonList.ftl">
	</head>
	<body >
		    <div class="common_menu">
		      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />MQ服务接入管理</div>
		      <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/iswapmq/external/jms/jmsInfoAction!addView.action','接入服务',675,430);"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新建服务<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
		    </div>
		    <div class="main_c">
		     <form name="pageForm" id="pageForm" action="jmsInfoAction!list.action" method="post">
		           <table class="main_drop">
		        <tr>
		          <td align="right">
					  服务名称：<input name="conditions[e.serviceName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_serviceName?default("")}"  type="text" />
		            <input name="" type="submit" value="查询"  class="btn_s" onClick="javascript:subForm('pageForm')"/></td>
		        </tr>
		      </table>    
		                             
		      <table class="tabs1"  style="margin-top:0px;">
		        <tr onclick="selectedTD(this);">
		          <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
		          <th width="28">序号</th>
		          <th>服务名称</th>
		          <th>队列名称</th>
				  <th>创建时间<a href="javascript:void(0)"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
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
			          <td class="tright"><input name="ids" value="${entity.id}" type="checkbox"/></td>
			          <td>${entity_index+1}</td>
			          <td><a href="javascript:opdg('${path}/iswapmq/external/jms/jmsInfoAction!view.action?id=${entity.id}','详细信息',400,380)"  id="hz1">${entity.jmsServerName?default("")}</td>
					  <td>${entity.queueName?default("")}</td>
					  <td>${entity.dateCreate?default("")}</td>
			            <td>
					      <a href="javascript:void(0)" onclick="opdg('${path}/iswapmq/external/jms/jmsInfoAction!updateView.action?id=${entity.id}','编辑队列',675,430)" class="tabs1_cz" id="hz1"><img src="${path}/images/czimg_edit.gif" />编辑</a>
					 	  <a href="javascript:void(0)" onclick="del('${path}/iswapmq/external/jms/jmsInfoAction!delete.action?ids=${entity.id}');" class="tabs1_cz" /><img src="${path}/images/czimg_sc.gif" />删除</a>
					 	  <a href="javascript:void(0)" onclick="testJMS('${entity.id}')"  class="tabs1_cz" ><img src="/iswap/images/small9/s_ceshi.gif" />测试</a>
					  </td>
		         </tr>
		         </#list>
		      </table>
		      <div class="tabs_foot"> 
			      <span class="tfl_btns">
				      <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
				      <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
				      <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
				       <a href="javascript:delMany('jmsInfoAction!delete.action')" class="tfl_blink">
				        <b class="hot">删除</b>
				       </a>
			      </span> 
			      <span class="page pageR">
			      	<@pager.pageTag/>
			      </span> 
		      </div>
		    </div>
		    <div class="clear"></div>
		 </form>
		 <#include "/common/commonLhg.ftl">
		<#include "/common/commonList.ftl">  
		<script>
			function testJMS(id){
				$.ajax({
				 type: "GET",
				 url: "jmsInfoAction!testJMS.action",
				 data:"id="+id,
				 success: function(msg){
				 	if(msg=='true'){
				 		alert("JMS服务测试通过!");
				 	}
				 	if(msg=='false'){
				 		alert("JMS服务测试失败!");
				 	}
				 } 
				});
			}
		</script>
	</body>
</html>
