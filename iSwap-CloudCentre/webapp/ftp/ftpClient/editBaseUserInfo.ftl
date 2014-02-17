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
<form action="ftpClient!saveBaseUserInfo.action" method="post" name="saveForm" id="saveForm">
<input type="hidden" name="ftpUser.id" value="${id}">
<input type="hidden" name="ftpUser.writepermission" value="${ftpUser.writepermission?string("true","false")}">

<input type="hidden" name="ftpUser.idletime" value="${ftpUser.idletime?default("")}">
<input type="hidden" name="ftpUser.uploadrate" value="${ftpUser.uploadrate?default("")}">
<input type="hidden" name="ftpUser.downloadrate" value="${ftpUser.downloadrate?default("")}">
<input type="hidden" name="ftpUser.maxloginnumber" value="${ftpUser.maxloginnumber?default("")}">
<input type="hidden" name="ftpUser.maxloginperip" value="${ftpUser.maxloginperip?default("")}">
<input type="hidden" name="ftpUser.failureDate" value="${ftpUser.failureDate?default("")}">
<input type="hidden" name="ftpUser.enableeditpassword" value="${ftpUser.enableeditpassword?string("true","false")}">
<input type="hidden" name="ftpUser.tasktimeout" value="${ftpUser.tasktimeout?default("")}">

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
          <div class="loayt_top"><span class="loayt_tilte"><b>帐号信息</b></span><span class="loayt_right"><img src="${path}/images/kuang_right.png" width="10" height="31"/></span></div>
          <div class="loayt_mian" >
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <td align="left" valign="middle" class="pm01_c" height="100%" style="border:1px solid #819EC6;"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
                     <tr>
                       <td height="100%" valign="top" bgcolor="#FFFFFF" ><div class="item1">
                           <ul class="item1_lu">
                             <li>
                               <p><span>
                                 <input type="checkbox" name="ftpUser.enableflag" <#if !ftpUser.enableflag>checked</#if> size="30" style="vertical-align:middle" />
                                 禁用帐号</span><span style="margin-left:20px;">自动删除帐号于日期：
                                   <input type="text" size="15" name="ftpUser.failureDate" value="${ftpUser.failureDate?default("")}" readonly="readonly" class="Wdate"  onFocus="setDay(this)"/>
                                   </span></p>
                             </li>
                             <li class="item2_bg">
                               用户名：
                               <span>
                               <input type="text" size="30" id="username" name="ftpUser.username" value="${ftpUser.username?default("")}" />
                               <span class="lurt_note"><span   id="usernameTip"></span></span> </span> </li>
                             <li>
                             登陆名：
                               <span>
                               <input type="text" size="30" id="userid" name="ftpUser.userid" value="${ftpUser.userid?default("")}" />
                               <span class="lurt_note"><span  id="useridTip"></span></span> </span> </span> </li>
                             <li class="item2_bg">
                           密码：
                               <span>
                               <input type="text" size="30" id="password" name="ftpUser.userpassword" value="${ftpUser.userpassword?default("")}"/>
                               <span class="lurt_note"><span  id="passwordTip"></span></span> </span></li>
                             <li>
                              主目录：
                               <span>
                               <input type="text" size="30" id="homedirectory" name="ftpUser.homedirectory" value="${ftpUser.homedirectory?default("")}"  />
                               <img src="${path}/images/lurt/ftp_folder.gif" valign="absmiddle" onclick="openCatalog();" /> <span class="lurt_note">
                              <span  id="homedirectoryTip"></span></span>
                                 <span>
                                   <input type="checkbox" style="vertical-align:middle" />
                                   将用户锁定于主目录</span> </span> </li>
                             <li class="item2_bg">
                             权限分配：
                               <span>
                               <select name="select" >
                                 <option value="" selected="selected">系统管理员</option>
                                 <option value="">普通用户</option>
                               </select>
                             </span> </li>
                             <li class="item2_bg">
                               <p>注释：</p>
                               <span>
                               <textarea name="ftpUser.annotation" value="" cols="80" style="height:55px;">${ftpUser.annotation?default("")}</textarea>
                             </span> </li>
                           <li class="item_bg">
                            <p class="btn_s_m2" align="center">
                             <input name="input" type="button" value="保存" onclick="saveTheForm();"  class=" btn2_s"/>
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="link_btn" onclick="document.forms[0].reset();">重填</a></p>
                           </li>
                           </ul>
                       </div></td>
                     </tr>
                 </table></td>
               </tr>
             </table>
          </div>
        </div>
        <div class=" clear"></div>
		
  <form>
 <script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/lhgdialog/lhgcore.min.js"></script>
<script type="text/javascript" src="${path}/js/lhgdialog/lhgdialog.min.js"></script>
<!--验证js-->
 <script type='text/javascript' src='${path}/js/jqueryValidate/formValidatorRegex.js'></script> 
<script type='text/javascript' src='${path}/js/jqueryValidate/formValidator.js'></script>
<script type='text/javascript' src='${path}/js/validator/iSwapFTP/baseUserValidator.js'></script> 
<link rel="stylesheet" href="${path}/js/jqueryValidate/css/validator.css" type="text/css" />
<#include "/common/commonJsTree.ftl">
<!--通用方法-->
<script type="text/javascript" src="${path}/js/iswap_common.js"></script>


<script type="text/javascript">
	<!--页面上弹出窗口-->
	function showWindow(url,titleValue,widths,heights){
		var dg = new J.dialog({ page:url,title:titleValue, cover:true ,rang:true,width:widths,height:heights,autoSize:true});
	    dg.ShowDialog();
	}
//时间控件
function setDay(o){
  WdatePicker({skin:'whyGreen'})
}
function saveTheForm(){
var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
	document.forms['saveForm'].submit();
	}
	
}
$(function(){
	window.parent.refreshTree();
})

function openCatalog(){
     	showWindow('${path}/ftp/ftpClient/ftpClient!findCatalogTree.action','选择主目录',320,590);
}
</script>
</body>
</html>
