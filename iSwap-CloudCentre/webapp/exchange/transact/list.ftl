<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>催办管理</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
</head>
<body  onclick="parent.hideMenu()">	
			<div class="frameset_w" style="height:100%">
  			<div class="main">
   			<div class="common_menu">
     		<div class="c_m_title"><img src="${path}/images/title/role.png"  align="absmiddle" />催办管理</div>
    		</div>
    		<div class="main_c">
            <table class="main_drop">
            <tr>
            <td align="right">
            <form name="pageForm" id="pageForm" method="post" action="${path}/exchange/transact/transact!list.action">
		      	主题名称：
		     <input name="conditions[e.title,string,like]" value="${serchMap.e_title?default("")}" onkeypress="showKeyPress()" onpaste="return false"/>
             <input  type="submit" value="查询"  class="btn_s"/></td>
           </tr>
           </table>                                    
      	  <table class="tabs1"  style="margin-top:0px;">
          <tr onclick="selectedTD(this);">
          <th width="28">序号</th>
          <th>主题名称</th>
          <th>催办方式</th>
          <th>被催方</th>
		  <th>催办时间</th>
           </tr>
          <#list listDatas as entity>
          <tr  <#if ((entity_index+1)%2)==0>class="trbg"</#if>>
          <td>${entity_index+1}</td>
          <td>${entity.title?default('(空)')}</td>
          <td><#if entity.transactType?exists><#if entity.transactType=='0'>系统消息<#elseif entity.transactType=='1'>邮件通知<#else>短信通知</#if><#else>(空)</#if></td>
		  <td>${entity.departmentBySendDeptId.deptName?default('空')}</td>
		  <td>${entity.sendDate?default('(空)')}</td>
		  </tr>
		  </#list>
          </table>	     
          <div class="tabs_foot"> 
         <span class="tfl_btns" style="float:left;">
             </span> 
            <span class="page pageR" style="margin-top:0px;"><@pager.pageTag/></span></div>
    		</div>
    		</div>
    	  <div class="clear"></div>
  		</div>
  		<div class="clear"></div>
	</div>
</form>
</body>
</html>