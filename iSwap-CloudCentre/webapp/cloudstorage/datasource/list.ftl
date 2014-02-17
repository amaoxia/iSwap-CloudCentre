<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<#include "/common/taglibs.ftl">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>数据源注册</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	</head>
	<body>
	    <div class="common_menu">
	      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />数据源注册</div>
		  <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz1" onclick="opdg('${path}/cloudstorage/datasource/datasource!addView.action','注册数据源','650','550');"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />注册数据源<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
	    </div>
	    <div class="main_c">
		    <form name="pageForm" action="${path}/cloudstorage/datasource/datasource!list.action" method="post">
			    <table class="main_drop">
			        <tr>
			          <td align="right">数据源名称：
			            <input name="conditions[e.sourceName,string,like]" type="text" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_sourceName?default("")}"/>
			                            数据库类型：
			            <select name="conditions[e.type,string,eq]"><option value="">---请选择---</option><option value="ORACLE" <#if serchMap.e_type?exists><#if serchMap.e_type=='ORACLE'>selected</#if></#if> >Oracle</option><option value="DB2" <#if serchMap.e_type?exists><#if serchMap.e_type=='DB2'>selected</#if></#if>>IBM DB2</option><option value="SQLSERVER" <#if serchMap.e_type?exists><#if serchMap.e_type=='SQLSERVER'>selected</#if></#if>>MS SQLServer</option><option value="MYSQL" <#if serchMap.e_type?exists><#if serchMap.e_type=='MYSQL'>selected</#if></#if>>MySQL</option></select>
			            <input name="" type="button" value="查询"  class="btn_s" onclick="selectdatasource();"/></td>
			        </tr>
			      </table>
			     
			      <table class="tabs1" style="margin-top:0px;">
			        <tr>
			         <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a></th>
			          <th width="28">序号</th>
			          <th>数据源名称</th>
			          <th>数据库类型</th>
			          <th>创建时间</th>
			          <th>操  作</th>
			        </tr>
			      	 <#list listDatas as entity>
			      	  <tr <#if entity_index%2==0>class="trbg"</#if> onclick="selectedTD(this);">
			          <td><input name="ids" type="checkbox" value="${entity.id}" /></td>
			          <td width="28">${entity_index+1}</td>
			          <td><a href="javascript:void(0)" onclick="opdg('${path}/cloudstorage/datasource/datasource!view.action?id=${entity.id}','数据源信息查看','550','350');">${entity.sourceName?default('')}</a></td>
			          <td>${entity.type?default('')}</td>
			          <td>${entity.createDate?default('')}</td>
			          <td width="250">
			          <a id="hz2" href="javascript:void(0);" onclick="opdg('${path}/cloudstorage/datasource/datasource!updateView.action?id='+${entity.id},'数据源编辑','650','550');" class="tabs1_cz">
			          <img src="${path}/images/czimg_edit.gif" />编辑</a>&nbsp;
			          <a id="hz4" href="javascript:void(0);" onclick="del(${entity.id});" class="tabs1_cz" >
			          <img src="${path}/images/small9/czimg_sc.gif" />删除</a>&nbsp;
				               <a id="hz4" href="javascript:testConn('${entity.id}');" class="tabs1_cz" >
			          			<img src="${path}/images/small9/s_ceshi.gif" />测试</a>&nbsp;
			          </td>
			        </tr>
					</#list>
			      </table>
			       <div class="tabs_foot"> 
				       <span class="tfl_btns">
					      	<img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px; margin-right:8px;" />
					      	<a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
					      	<img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
					      	<a href="javascript:void(0)" onclick="delMany('${path}/cloudstorage/datasource/datasource!delete.action');" class="tfl_blink">
					      		<b class="hot">删除</b>
					      	</a>
				      </span> 
	     			  <span class="page pageR">
	     			  	<@pager.pageTag/>
	     			  </span>
	     		 </div>
	      </form>
	    </div>
	    <div class="clear"></div>
	<#include "/common/commonList.ftl">
	<#include "/common/commonLhg.ftl">
	<script> 
		function selectflow(){
			document.pageForm.submit();
		}
		//删除数据
		function del(id){
			if (confirm("确定要删除选中记录吗？")) {
				document.forms['pageForm'].action ="${path}/cloudstorage/datasource/datasource!delete.action?ids="+id ;
				document.forms['pageForm'].submit();
			}
		}
		
		function selectdatasource(){
			document.pageForm.submit();
		}
		//修改状态
		function updateStatus(url,type){
		 var state="";
		  if('0'==type){state="确定要取消发布数据源？"}
			  if('1'==type){state="确定发布数据源？"}
			  if(checks()) {
				  if(confirm(state)) {
				 	document.forms['pageForm'].action = url;
					document.forms['pageForm'].submit();
				  }
			  } else {
			  	alert('至少选择一条数据');
			  }
			
		}
		//测试数据库连接
		function  testConn(connId){
			$.ajax({
		  		   type: "POST",
				   url: "${path}/cloudstorage/datasource/datasource!testConnDataSource.action",
				   data: "id=" + connId,
				   success: function(data){
				   		if(data=='true'){
				   			alert("连接成功!");
				   		}else{
				   			alert("连接失败,请检查数据连接信息及网络情况!");
				   		}
				   }
		  	});
		}
	</script>
	</body>
</html>
