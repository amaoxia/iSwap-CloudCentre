<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<#include "/common/taglibs.ftl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="pop_01" style="width:500px">
  <div class="pop_mian">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%">
        <form name="saveForm" action="webInfoAction!update.action" method="post">
        <input type="hidden" name="deptId" value="${deptId?default('')}"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p><b style="color:red">*</b>服务名称：</p>
                      <span>
                      <input type="hidden" name="id"  id="id" value="${id}"/>
                      <input name="wsName" id="wsName" value="${entityobj.wsName?default("")}" type="text" size="30" />
                      </span>
                      <span><div id="wsNameTip"></div></span
                    </li>
                    <li class="item_bg">
                        <p><b style="color:red">*</b>ip地址：(包括端口号)</p>
                      <span>
                      <input name="ipAddress" id="ipAddress" value="${entityobj.ipAddress?default("")}" type="text" size="30" />
                      </span>
                      <span><div id="ipAddressTip"></div></span>
                    </li>
                    <#-- 
                    <li>
                      <p><b style="color:red">*</b>所属部门：</p>
                     <span>
                      <select name="sysDept.id"  style="width:205px">
                       <option value="${sysDept.id}">${sysDept.deptName?default('')}</option>
                      </select>
                      </span>
                      <span><div id="deptIdTip"></div></span>
                    </li>
                    -->
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
                      <input name="passWord" id="passWord" value="${entityobj.passWord?default("")}"  type="password" size="30" />
                      </span>
                      <span><div id="passWordTip"></div></span>
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
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
<script type='text/javascript' src='${path}/js/validator/iswapmq/wsValidator.js'></script>
<#include "/common/commonValidator.ftl">
<#include "/common/commonUd.ftl">
<script type="text/javascript">
$(function(){
	 $("#wsName").defaultPassed();
});
</script>
</body>
</html>
