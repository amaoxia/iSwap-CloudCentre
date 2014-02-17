<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>用户信息</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<!--  关闭当前窗口-->
<script  type="text/javascript">
var DG = frameElement.lhgDG; 
DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
//关闭窗口 不做任何操作
function singleCloseWin(){
DG.curWin.closeWindow();
}
</script>
</head>
<body>
<div class="pop_div6 pm6_c" style="width:700px">
  <div class="item4">
    <ul class="item4_c">
      <li>
        <h1>用户名称：</h1>
        <p>${userName?default('')}</p>
      </li>
      <li class="item_bg">
        <h1>性别名称：</h1>
        <p><#if sex=='1'>男<#else>女</#if></p>
      </li>
      <li>
        <h1>登录名称：</h1>
        <p>${loginName?default('')}</p>
      </li>
      <li class="item_bg">
        <h1>用户标识符：</h1>
        <p>${userUid?default('')}</p>
      </li>
      <li>
        <h1>机构名称：</h1>
        <p><#if sysDept?exists>${sysDept.deptName?default('')}</#if></p>
      </li>
      	<li  class="item_bg">
        <h1>所属角色：</h1>
        <p>
        	<#list roles as data>
            <#if userRole.roleId==data.id >${data.name?default('空')}</#if>
            </#list>
            </p>
      </li>
      <li class="item_bg">
        <h1>身份证号：</h1>
        <p>${idCardCode?default('')}</p>
      </li>
      <li >
        <h1>手机号码：</h1>
        <p>${mobile?default('')}</p>
      </li>
      <li class="item_bg">
        <h1>联系电话：</h1>
        <p>${phone?default('')}</p>
      </li>
       <li >
        <h1>email：</h1>
        <p>${email?default('')}</p>
      </li>
       <li class="item_bg">
        <h1>联系地址：</h1>
        <p>${address?default('')}</p>
      </li>
       <li>
        <h1>用户描述：</h1>
        <p>${remark?default('')}</p>
      </li>
    </ul>
  </div>
</div>
</body>
</html>