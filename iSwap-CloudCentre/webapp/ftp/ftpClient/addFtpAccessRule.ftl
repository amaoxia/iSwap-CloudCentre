<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>	
<form action="${path}/ftp/ftpClient/ftpClient!saveFtpAccessRule.action" method="post" name="saveForm" id="saveForm">
<input type="hidden" name="ftpAccessRule.ftpUser.id" value="${id}"/>
<div class="pop_01" id="tabLock" style="width:700px">
  <div class="pop_mian">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
       
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p>规则：</p>
                      <span>
                      <input type="text" size="30" id="username" name="ftpAccessRule.ip" />
                       <div id="usernameTip"></div>
                      </span>
                      
                    </li>
					<li class="item_bg">
                      <p>访问情况：</p>
                     <span>
                      <select name="ftpAccessRule.enableaccess" id="select">
                        <option value="true">允许访问</option>
                        <option value="false">拒绝访问</option>
                      </select>
                      </span>
                    </li>
                    <li>
                      <p>规则说明：</p>
                     <span>
                      xxx.xxx.xxx.xxx = 精确匹配
                      <br>
                      xxx.xxx.?.?-xxx.xxx.?.? = IP范围（仅IP号）
                      <br>
                      * = 任何IP
                      <br>
                      ? = 匹配单个字符
                      </span>
                    </li>
                  </ul>
                 <!-- <p class="btn_s_m2">
                    <input id="sub" type="button" value="保存" class=" btn2_s"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="document.forms[0].reset()" class="link_btn">重填</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" class="link_btn" onclick="dg.cancel();">关闭窗口</a></p>-->
                </div></td>
            </tr>
          </table></td>
        <td rowspan="2" class="pm_right"><img src="${path}/images/pop_051.png" width="7" height="1" /></td>
      </tr>
    </table>
  </div>
</div>
<form>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/datepicker/WdatePicker.js"></script>
<script type='text/javascript' src='${path}/js/jqueryblockUI/jquery.blockUI.js'></script> 
<!--验证js-->
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/iSwapFTP/baseUserValidator.js'></script> 
<script type="text/javascript">
var DG = frameElement.lhgDG; 
DG.addBtn( 'reset', '重填', resetWin); 
function resetWin() {
//实现逻辑
document.forms[0].reset();
}
DG.addBtn( 'save', '保存', saveWin); 
function saveWin() {
	//实现逻辑
	 document.forms["saveForm"].submit();
}
var dg = frameElement.lhgDG;
//时间控件
function setDay(o){
  WdatePicker({skin:'whyGreen'})
}
function saveTheForm(){
	document.forms['saveForm'].submit();
	dg.cancel();
	dg.curWin.reloadPage(${id});
}
</script>
</body>
</html>
