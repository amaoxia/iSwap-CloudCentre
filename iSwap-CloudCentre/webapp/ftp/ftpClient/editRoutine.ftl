<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
</head>
<body>	
<form action="ftpClient!saveRoutine.action" method="post" name="saveForm" id="saveForm">
<input type="hidden" name="ftpUser.id" value="${id}">
<input type="hidden" name="ftpUser.username" value="${ftpUser.username?default("")}">
<input type="hidden" name="ftpUser.userid" value="${ftpUser.userid?default("")}">
<input type="hidden" name="ftpUser.userpassword" value="${ftpUser.userpassword?default("")}">
<input type="hidden" name="ftpUser.homedirectory" value="${ftpUser.homedirectory?default("")}">
<input type="hidden" name="ftpUser.enableflag" value="${ftpUser.enableflag?string("true","false")}">
<input type="hidden" name="ftpUser.writepermission" value="${ftpUser.writepermission?string("true","false")}">
<input type="hidden" name="ftpUser.annotation" value="${ftpUser.annotation?default("")}">
<input type="hidden" name="ftpUser.failureDate" value="${ftpUser.failureDate?default("")}">

<input type="hidden" name="ftpUser.enabletransmissionlimit" value="${ftpUser.enabletransmissionlimit?string("true","false")}">
<input type="hidden" name="ftpUser.everytaskfiles" value="${ftpUser.everytaskfiles?default("")}">
<input type="hidden" name="ftpUser.everytasksize" value="${ftpUser.everytasksize?default("")}">
<input type="hidden" name="ftpUser.alltaskfiles" value="${ftpUser.alltaskfiles?default("")}">
<input type="hidden" name="ftpUser.alltasksize" value="${ftpUser.alltasksize?default("")}">
<input type="hidden" name="ftpUser.enablediskquota" value="${ftpUser.enablediskquota?string("true","false")}">
<input type="hidden" name="ftpUser.mindiskquota" value="${ftpUser.mindiskquota?default("")}">
<input type="hidden" name="ftpUser.maxdiskquota" value="${ftpUser.maxdiskquota?default("")}">

<input type="hidden" name="ftpUser.deptid" value="${ftpUser.deptid?default("")}">

<div class="loayt_01 rlist100" style="margin-top:10px;">
          <div class="loayt_top"><span class="loayt_tilte"><b>常规信息（${ftpUser.username?default("")}）</b></span><span class="loayt_right"><img src="${path}/images/kuang_right.png" width="10" height="31"/></span></div>
          <div class="loayt_mian" >
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td align="left" valign="middle" class="pm01_c" height="100%" style="border:1px solid #819EC6;"><table width="80%" border="0" cellspacing="0" cellpadding="0"  height="100%">
                    <tr>
                      <td  height="100%" valign="top" style="height:407px;"><div class="item1">
                          <ul class="item1_lu">
                            <li>
                              <p>
                                <input type="checkbox" name="ftpUser.enableeditpassword" value='true' <#if ftpUser.enableeditpassword>checked</#if> style="vertical-align:middle" />
                                允许用户修改密码</p>
                            </li>
                            <li class="item2_bg">
                              <p><span>同一IP地址只允许:</span><br/>
                                  <input type="text" name="ftpUser.maxloginperip" value="${ftpUser.maxloginperip?default("")}" size="8" />
                                个登录</p>
                            </li>
                            <li>
                              <p><span>最大上传速度：</span><br/>
                                  <input type="text" name="ftpUser.uploadrate" value="${ftpUser.uploadrate?default("")}" size="8" />
                                KB/秒</p>
                            </li>
                            <li class="item2_bg">
                              <p><span>最大下载速度：</span><br/>
                                  <input type="text" name="ftpUser.downloadrate" value="${ftpUser.downloadrate?default("")}" size="8" />
                                KB/秒</p>
                            </li>
                            <li>
                              <p><span>空闲超时：</span><br/>
                                  <input type="text" name="ftpUser.idletime" value="${ftpUser.idletime?default("")}" size="8" />
                                分钟</p>
                            </li>
                            <li class="item2_bg">
                              <p><span>任务超时：</span><br/>
                                  <input type="text"  name="ftpUser.tasktimeout"  value="${ftpUser.tasktimeout?default("")}" size="8" />
                                分钟</p>
                            </li>
                          </ul>
                        <p align="center" class="btn_s_m2">
                            <input name="input" type="button" value="保存" onclick='saveTheForm()'  class=" btn2_s"/>
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="link_btn" onclick="document.form1.reset();">重填</a></p>
                      </div></td>
                    </tr>
                  </table>
          </div>
        </div>
        <div class=" clear"></div>
  <form>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/datepicker/WdatePicker.js"></script>
<script type="text/javascript">
//时间控件
function setDay(o){
  WdatePicker({skin:'whyGreen'})
}
function saveTheForm(){
	document.forms['saveForm'].submit();
	
}
</script>
</body>
</html>
