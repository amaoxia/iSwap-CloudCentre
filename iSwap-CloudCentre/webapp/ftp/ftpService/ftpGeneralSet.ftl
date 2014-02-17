<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/js/jqueryValidate/css/validator.css" type="text/css" />
</head>
<body>	
<form action="" method="post" name="saveForm" id="saveForm">
<input type="hidden" name="ftpGeneralSet.id" value="<#if ftpGeneralSet?exists>${ftpGeneralSet.id?default('')}</#if>"/>
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title" style="margin:2px 0px;"><img src="${path}/images/title/ftpGeneralSet.png" align="absmiddle" />FTP系统常规设置</div></div>
    <div><form class="lurt_form" name="form1">
     <table width="100%" height="400"  border="0" cellpadding="0" cellspacing="0" style="overflow:hidden;margin-top:3px;">
       <tr>
         <td width="100%" valign="top"><div style="width:98%;overflow-y:no; overflow-x:hidden; height:441px;margin-left:10px;">
             <div class="loayt_top"> <span class="loayt_tilte"><b>常规信息</b></span><span class="loayt_right"><img src="${path}/images/kuang_right.png" width="10" height="31" /></span> </div>
           <div class="loayt_mian">
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 
                 <td align="left" valign="middle" class="pm01_c" height="100%" style="border:1px solid #819EC6;"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
                     <tr>
                       <td height="100%" valign="top" bgcolor="#FFFFFF" ><div class="item1">
                           <ul class="item1_lu">
                             <li>
                               <p>
                                 IP地址：
                                   <input type="text" size="30" name="ftpGeneralSet.ip"   <#if ftpGeneralSet?exists>  value="${ftpGeneralSet.ip}"  </#if>/>
                                   </p>
                             </li>
                             <li class="item2_bg">
                               <p>FTP端口号：</p>
                               <span>
                               <input type="text" size="30" <#if ftpGeneralSet?exists>  name="ftpGeneralSet.ftpport" value="${ftpGeneralSet.ftpport}"  </#if> />
                               <span class="lurt_note"></span> </span> </li>
                            
                             <li>
                               <p>最大用户数量：</p>
                               <span>
                               <input type="text" size="30" <#if ftpGeneralSet?exists> name="ftpGeneralSet.maxusernum"  value="${ftpGeneralSet.maxusernum}"  </#if> /> 个
                               <span class="lurt_note"></span> </span></li>
                             <li class="item2_bg">
                               <p>帐号缓冲活动时间：</p>
                               <span>
                               <input type="text" size="10" <#if ftpGeneralSet?exists> name="ftpGeneralSet.buffertime"  value="${ftpGeneralSet.buffertime}"  </#if> /> 秒<span class="lurt_note"></span><br/>
                               </span> </li>
                             <li>
                               <p>
                                 <input type="checkbox" name="checkbox" id="passivemode" name="ftpGeneralSet.passivemode"
                                  <#if ftpGeneralSet?exists&&ftpGeneralSet.passivemode==true>  checked  </#if> />
                                 允许被动模式传输</p>
                               </li>
                             <li class="item2_bg">
                               <input type="checkbox" name="checkbox2" id="dns" name="ftpGeneralSet.dns"
                               <#if ftpGeneralSet?exists&&ftpGeneralSet.dns==true>  checked </#if>
                                />
                               启用动态DNS</li>
                           <li class="item_bg">
                            <p class="btn_s_m2" align="center">
                             <input name="input" type="button" value="保存" onclick="saveTheForm();"  class=" btn2_s"/>
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="link_btn" onclick="document.forms[0].reset()">重填</a></p>
                           </li>
                           </ul>
                       </div></td>
                     </tr>
                 </table></td>
               </tr>
             </table>
           </div>
         </div></td>
       </tr>
     </table>
     </form>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
  <form>
  <script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/datepicker/WdatePicker.js"></script>
<!--验证js-->
 <script type='text/javascript' src='${path}/js/jqueryValidate/formValidatorRegex.js'></script> 
<script type='text/javascript' src='${path}/js/jqueryValidate/formValidator.js'></script>
<script type='text/javascript' src='${path}/js/validator/iSwapFTP/baseUserValidator.js'></script> 
<script type="text/javascript">
//时间控件
function setDay(o){
  WdatePicker({skin:'whyGreen'})
}
function saveTheForm(){
var passivemode;
var dns;
	if(document.getElementById('passivemode').checked==true){
		passivemode=true;
	}else{
		passivemode=false;
	}
	if(document.getElementById('dns').checked==true){
		dns=true;
	}else{
		dns=false;
	}
	document.forms['saveForm'].action="ftpService!saveFtpGeneralSet.action?passivemode="+passivemode+"&dns="+dns;
	document.forms['saveForm'].submit();
}
</script>
</body>
</html>
