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
<form action="ftpClient!saveDiskQuota.action" method="post" name="saveForm" id="saveForm">
<input type="hidden" name="ftpUser.id" value="${id}">
<input type="hidden" name="ftpUser.username" value="${ftpUser.username?default("")}">
<input type="hidden" name="ftpUser.userid" value="${ftpUser.userid?default("")}">
<input type="hidden" name="ftpUser.userpassword" value="${ftpUser.userpassword?default("")}">
<input type="hidden" name="ftpUser.homedirectory" value="${ftpUser.homedirectory?default("")}">
<input type="hidden" name="ftpUser.enableflag" value="${ftpUser.enableflag?string("true","false")}">
<input type="hidden" name="ftpUser.writepermission" value="${ftpUser.writepermission?string("true","false")}">

<input type="hidden" name="ftpUser.idletime" value="${ftpUser.idletime?default("")}">
<input type="hidden" name="ftpUser.uploadrate" value="${ftpUser.uploadrate?default("")}">
<input type="hidden" name="ftpUser.downloadrate" value="${ftpUser.downloadrate?default("")}">
<input type="hidden" name="ftpUser.maxloginnumber" value="${ftpUser.maxloginnumber?default("")}">
<input type="hidden" name="ftpUser.maxloginperip" value="${ftpUser.maxloginperip?default("")}">
<input type="hidden" name="ftpUser.annotation" value="${ftpUser.annotation?default("")}">
<input type="hidden" name="ftpUser.failureDate" value="${ftpUser.failureDate?default("")}">
<input type="hidden" name="ftpUser.enableeditpassword" value="${ftpUser.enableeditpassword?string("true","false")}">
<input type="hidden" name="ftpUser.tasktimeout" value="${ftpUser.tasktimeout?default("")}">

<input type="hidden" name="ftpUser.enabletransmissionlimit" value="${ftpUser.enabletransmissionlimit?string("true","false")}">
<input type="hidden" name="ftpUser.everytaskfiles" value="${ftpUser.everytaskfiles?default("")}">
<input type="hidden" name="ftpUser.everytasksize" value="${ftpUser.everytasksize?default("")}">
<input type="hidden" name="ftpUser.alltaskfiles" value="${ftpUser.alltaskfiles?default("")}">
<input type="hidden" name="ftpUser.alltasksize" value="${ftpUser.alltasksize?default("")}">

<input type="hidden" name="ftpUser.deptid" value="${ftpUser.deptid?default("")}">

<div class="loayt_01 rlist100" style="margin-top:10px;">
          <div class="loayt_top"><span class="loayt_tilte"><b>磁盘配额信息（${ftpUser.username?default("")}）</b></span><span class="loayt_right"><img src="${path}/images/kuang_right.png" width="10" height="31"/></span></div>
          <div class="loayt_mian" >
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td align="left" valign="middle" class="pm01_c" height="100%" style="border:1px solid #819EC6;"><table width="80%" border="0" cellspacing="0" cellpadding="0"  height="100%">
                    <tr>
                      <td  height="100%" valign="top" style="height:407px;"><div class="item1">
                          <ul class="item1_lu">
                            <li>
                              <p>
                                <input type="checkbox" name="ftpUser.enablediskquota" <#if ftpUser.enablediskquota>checked</#if> value="true" style="vertical-align:middle" />
                                启用磁盘配额</p>
                            </li>
                            <li class="item2_bg">
                              <p><span>最小:</span><br/>
                                  <input type="text" name="ftpUser.mindiskquota" value="${ftpUser.mindiskquota?default("")}" size="8" />
                                  MB                              </p>
                            </li>
                            <li>最大<span>：</span><br/>
                              <input type="text" name="ftpUser.maxdiskquota" value="${ftpUser.maxdiskquota?default("")}"  size="8" />
MB </li>
                            </ul>
                        <p align="center" class="btn_s_m2">
                          <input name="input" type="button" value="保存" onclick="saveTheForm();"  class=" btn2_s"/>
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="link_btn" onclick="document.forms[0].reset();">重填</a></p>
                      </div></td>
                    </tr>
                  </table>
          </div>
        </div>
        <div class=" clear"></div>
  </form>
  
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
