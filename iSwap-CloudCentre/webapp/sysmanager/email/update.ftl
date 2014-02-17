<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统邮件修改</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="pop_01" style="width:600px">
<form action="${path}/sysmanager/email/email!update.action" method="post" name="saveForm" id="saveForm" >
<input type="hidden"  id="id" value="0"/>
  <div class="pop_mian">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                  <li class="item_bg">
                      <p><b>*</b>邮箱帐号：</p>
                      <span>
                      <input type="text" size="30" id="emailAccount" name="emailAccount"  value="${emailAccount?default('')}"/>
                      <input type="hidden" value="${id}" name="id" id="id"/>
                      </span> 
                      <span> <div id="emailAccountTip"></div></span>
                      </li>
                      <li>
                      <p><b>*</b>邮箱密码：</p>
                      <span>
                      <input type="password" size="30" id="emailPassWord" name="emailPassWord" value="${emailPassWord?default('')}"/>
                      </span> 
                      <span> <div id="emailPassWordTip"></div></span>
                      </li>
                      <li class="item_bg">
                      <p><b>*</b>重复密码：</p>
                      <span>
                      <input type="password" size="30" id="tpass" name="tpass" value="${emailPassWord?default('')}"/>
                      </span> 
                      <span> <div id="tpassTip"></div></span>
                      </li>
                    <li>
                      <p><b>*</b>邮箱服务器地址：</p>
                      <span>
                      <input type="text" size="30" id="emailAddress"  name="emailAddress" maxlength="20" value="${emailAddress?default('')}"/>
                      </span>
                     <span> <div id="emailAddressTip"></div></span>
                    </li>
                    <li class="item_bg">
					  <p><b>*</b>邮箱SMTP地址：</p>
                      <span>
                      <input type="text" size="30" id="emailServerAddress" name="emailServerAddress" value="${emailServerAddress?default('')}"/>
                      </span>
                      <span><div id="emailServerAddressTip"></div></span>
                    </li>
                  </ul>
                </div></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </div>
</div>
</form>
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
<script type='text/javascript' src='${path}/js/validator/system/email.js'></script> 
 <!--验证js-->
<#include "/common/commonUd.ftl">
<#include "/common/commonValidator.ftl">
</body>
</html>