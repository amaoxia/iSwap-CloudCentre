<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body>	
<form action="" method="post" name="saveForm" id="saveForm">
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title" style="margin:2px;float:left;"><img src="${path}/images/title/ftpClientLogList.png" align="absmiddle" />客户端日志列表</div>
      </div>
    <table width="100%" height="400"  border="0" cellpadding="0" cellspacing="0" style="overflow:hidden;margin-top:3px;">
      <tr>

        <td width="100%" valign="top"><table width="98%" class="main_drop" style="margin:8px auto;">
          <tr>
            <td align="left">&nbsp;</td>
            <td align="right">用户名称
              <input name="input" type="text" onkeypress="showKeyPress()" onpaste="return false" id="username"  <#if username?exists> value="${username}" </#if> />
                <input name="input" type="button"  value="查询"  onclick="search();"  class="btn_s"/></td>
          </tr>
        </table>
          <table width="98%" align="center" class="tabs1">
            <tr>
              <th width="9%">用户名</th>
              <th width="15%">用户IP</th>
              <th width="15%">活动</th>
              <th width="61%">文件/目录</th>
            </tr>
                <#if ftpLogs?exists>
           <#if ftpLogs?size!=0>
              <#list ftpLogs as entity>
            <tr class="trbg">
              <td class="trbg">${entity.username?default("")}</td>
              <td class="trbg">${entity.ip?default("")}</td>
              <td>${entity.action?default("")}</td>
              <td>${entity.homedirectory?default("")}</td>
            </tr>
               </#list>
              </#if>
              </#if>
          </table>
              <div class="tabs_foot" style="width:98%;"> 
           <span class="tfl_btns">
           <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a>
           <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
           <a href="javascript:remove();" class="tfl_blink"><b class="hot"></b></a></span> 
           <span class="page pageR">
           	<@pager.pageTag/>
           </span>
    	 </div></td>
      </tr>
    </table>
    <p>&nbsp;</p>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${path}/js/datepicker/WdatePicker.js"></script>
<script type="text/javascript">
 function search(){
	  var username = document.getElementById('username').value;
	  document.getElementById('saveForm').action="ftpLog!ftpClientLogList.action?username="+username;
		document.getElementById('saveForm').submit();
}
</script>
  <form>
</body>
</html>
