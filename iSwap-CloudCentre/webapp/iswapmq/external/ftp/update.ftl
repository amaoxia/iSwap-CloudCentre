<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<#include "/common/taglibs.ftl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="pop_01" style="width:665px;height:550px;overflow-x:hidden;overflow-y:scroll;">
  <div class="pop_mian">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%">
        <form name="pageForm" action="ftpInfoAction!update.action" method="post">
        <input type="hidden" name="id"  id="id" value="${id}"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p><b style="color:red">*</b>服务名称：</p>
                      <span>
                      <input name="ftpServerName" id="ftpServerName" value="${entityobj.ftpServerName?default("")}" type="text" size="30" />
                      </span>
                      <span><div id="ftpServerNameTip"></div></span>
                    </li>
                    <li class="item_bg">
                        <p><b style="color:red">*</b>ip地址：</p>
                      <span>
                      <input name="address" id="address" value="${entityobj.address?default("")}" type="text" size="30" />
                      </span>
                      <span><div id="addressTip"></div></span>
                    </li>
                       <li>
                       <p><b style="color:red">*</b>端口号：</p>
                     <span>
                      <input name="port" id="port" type="text" size="30"  value="${entityobj.port?default("")}"/>
                      </span>
                      <span><div id="portTip"></div></span>
                    </li>
                    <li  class="item_bg">
                      <p><b style="color:red">*</b>目录：</p>
                     <span>
                      <input name="filePath" id="filePath" type="text" size="30" value="${entityobj.filePath?default("")}" />
                      </span>
                      <span><div id="filePathTip"></div></span>
                    </li>
                    
                     <li>
                      <p><b style="color:red">*</b>超时时长：</p>
                     <span>
                       <select name="outTimes">
		                  <#list 1..15 as i>
		                  <option value="${i}" <#if i?string==entityobj.outTimes>selected</#if> >${i}</option>
		                  </#list>
	                  </select>
                      </span>
                      <span><div id="outTimesTip"></div></span>
                    </li>
                    <li class="item_bg">
                      <p><b style="color:red">*</b>用户名：</p>
                     <span>
                      <input name="userName" id="userName" value="${entityobj.userName?default("")}" type="text" size="30" />
                      </span>
                      <span><div id="userNameTip"></div></span>
                    </li>
                    
                    <li>
                      <p><b style="color:red">*</b>密码：</p>
                     <span>
                      <input name="password" id="password" value="${entityobj.password?default("")}" type="password" size="30" />
                      </span>
                      <span><div id="passwordTip"></div></span>
                    </li>
                  </ul>
                  </div></td>
            </tr>
          </table>
          </form>
          </td>
        <td rowspan="2" class="pm_right"><img src="${path}/images/pop_051.png" width="7" height="1" /></td>
      </tr>
     
    </table>
  </div>
</div>
<script src="${path}/js/jquery-1.5.1.js"></script>
<script type='text/javascript' src='${path}/js/validator/iswapmq/ftpValidator.js'></script>
<#include "/common/commonValidator.ftl">
<#include "/common/commonUd.ftl">
<script type="text/javascript">
$(function(){
	 $("#ftpServerName").defaultPassed();
});
</script>
</body>
</html>
