			<#include "/common/taglibs.ftl">
			<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
			<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title>数据包比对分析</title>
			<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
			<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
			<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
			</head>
			<body>
			<form name="pageForm" method="post"  action="${path}/sysmanager/email/email!list.action">
			<div class="frameset_w" style="height:100%">
  			<div class="main">
   			<div class="common_menu">
     		<div class="c_m_title"><img src="${path}/images/title/role.png"  align="absmiddle" />数据包比对分析</div>
    		</div>
    		<div class="main_c">
            <table class="main_drop">
            <tr>
            <td align="right">
		     应用：
		     <input name="conditions[e.emailAccount,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_emailAccount?default("")}"/>
		   指标：
		     <input name="conditions[e.emailAccount,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_emailAccount?default("")}"/>    
             <input  type="submit" value="查询"  class="btn_s"/></td>
           </tr>
           </table>                              
      	  <table class="tabs1"  style="margin-top:0px;">
          <tr onclick="selectedTD(this);">
          <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
          <th width="28">序号</th>
          <th>应用</th>
          <th>指标</th>
          <th>数据提供部门</th>
          <th>发送数据总量</th>
          <th>数据接收情况</th>
		  <th width="20%">操作</th>
           </tr>
          <#list listDatas as entity>
           <tr  <#if ((entity_index+1)%2)==0>class="trbg"</#if> onclick="selectedTD(this);">
          <td><input  type="checkbox" name="ids" value="${entity.id}" /></td>
          <td></td>
          <td></td>
          <td></td>
		  <td></td>
		  <td></td>
          <td>
            <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/sysmanager/email/email!view.action?id=${entity.id}','邮件信息','400','250');" class="tabs1_cz">
			<img src="${path}/images/small9/s_chakan.gif" />查看</a>
	       	<a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/sysmanager/email/email!updateView.action?id=${entity.id}','编辑邮件信息','600','380');" class="tabs1_cz">
	       	<img src="${path}/images/czimg_edit.gif"/>编辑</a>
		  	<a href="#"class="tabs1_cz" onclick="del('${path}/sysmanager/email/email!delete.action?ids=${entity.id}')">
		  	<img src="${path}/images/czimg_sc.gif" />删除</a>
		  </td>
		    </tr>
		   </#list>
		    <tr>
	          <td><input type="checkbox" name="ids" value="" /></td>
	          <td>1</td>
	          <td>应用1</td>
	          <td>指标1</td>
	          <td>部门1</td>
	          <td>1000</td>
	          <td>
	          	<table>
	          		<tr>
	          			<td>部门2</td>
	          			<td>1000</td>
	          		</tr>
	          		<tr>
	          			<td>部门3</td>
	          			<td>1000</td>
	          		</tr>
	          	  </table>
	          </td>
			  <td></td>
		  </tr>
          </table>
          <div class="tabs_foot"> 
          <span class="tfl_btns">
          <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
           <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
           <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
           <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/sysmanager/email/email!delete.action')"><b class="hot">删除</b></a></span>
            <span class="page pageR"><@pager.pageTag/></span></div>
    		</div>
    	  <div class="clear"></div>
  		</div>
  		<div class="clear"></div>
	</div>
</from>
			<#include "/common/commonList.ftl">
			<#include "/common/commonLhg.ftl">
</body>
</html>