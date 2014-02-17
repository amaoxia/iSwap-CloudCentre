<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>FTP信息</title>
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
<div class="pop_div6 pm6_c" style="width:380px">
  <div class="item4">
    <ul class="item4_c">
      <li>
        <h1>服务名称：</h1>
        <p> ${entityobj.ftpServerName?default("")}</p>
      </li>
      <li class="item_bg">
        <h1>ip地址：</h1>
        <p>${entityobj.address?default("")}</p>
      </li>
      <li>
        <h1>端口号：</h1>
        <p>${entityobj.port?default("")}</p>
      </li>
      <li class="item_bg">
        <h1>所属部门：</h1>
        <p>
        				<#list listDepts as entity>
	                         <#if entityobj.sysDept?exists >
	                            <#if entityobj.sysDept.id == entity.id>${entity.deptName}<#break></#if>
	                         </#if>
                         </#list>
          </p>
      </li>
      <li>
        <h1>目录：</h1>
        <p>${entityobj.filePath?default("")}</p>
      </li><li class="item_bg">
        <h1>超时时长：</h1>
        <p>${entityobj.outTimes?default("")}</p>
      </li>
      <li>
        <h1>用户名：</h1>
        <p>${entityobj.userName?default("")}</p>
      </li>
      <li class="item_bg">
        <h1>密码：</h1>
        <p>${entityobj.password?default("")}</p>
      </li>
    </ul>
  </div>
</div>
</body>
</html>

