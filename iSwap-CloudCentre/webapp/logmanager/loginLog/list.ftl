			<#include "/common/taglibs.ftl">
			<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
			<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title>登陆/登出日志列表</title>
			<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
			<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
			<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
			</head>
			<body>
			<form name="pageForm" method="post"  action="${path}/sysmanager/email/email!list.action">
			<div class="frameset_w" style="height:100%">
  			<div class="main">
   			<div class="common_menu">
     		<div class="c_m_title"><img src="${path}/images/title/role.png"  align="absmiddle" />登陆/登出日志</div>
    		</div>
    		<div class="main_c">
            <table class="main_drop">
            <tr>
            <td align="right">
		     登录名：
		     <input name="conditions[e.emailAccount,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_emailAccount?default("")}"/>
		       登录类型：
		     <select>
		     	<option>请选择</option>
		     	<option>登陆</option>
		     	<option>登出</option>
		     </select>
		       登录时间：
		     <input readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" name="conditions[e.emailAccount,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_emailAccount?default("")}"/>
             <input  type="submit" value="查询"  class="btn_s"/></td>
           </tr>
           </table>                              
      	  <table class="tabs1"  style="margin-top:0px;">
          <tr onclick="selectedTD(this);">
          <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
          <th width="28">序号</th>
          <th>登录名</th>
          <th>登录类型</th>
          <th>登录ip</th>
		  <th>登录时间<a href="javascript:void(0);" onclick="setOrder(this)" name="e.createDate">&nbsp;&nbsp;&nbsp;<img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
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
	          <td>zhangsan</td>
	          <td>登陆</td>
	          <td>127.0.0.1</td>
			  <td>2013-12-13 00:00:00</td>
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