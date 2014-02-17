<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />

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
<div class="pop_div6 pm6_c">
  <div class="item4">
    <ul class="item4_c">
      <li>
        <h1>流程中文名称：</h1>
        <p>${workFlowName?default('')}</p>
      </li>
      <li class="item_bg">
        <h1>流程英文名称：</h1>
        <p>${workFlowCode?default('')}</p>
      </li>
      <li>
        <h1>所属前置机：</h1>
        <p>${cloudNodeInfo.nodesName?default('')}</p>
      </li>
      <li class="item_bg">
        <h1>所属应用：</h1>
        <p>${appMsg.appName?default('')}</p>
      </li>
      <li>
        <h1>所属部门：</h1>
        <p>${sysDept.deptName?default('')}</p>
      </li>
      <li class="item_bg">
        <h1>流程类型：</h1>
        <p><#if wfType=='0'>接收</#if><#if wfType=='1'>发送</#if></p>
      </li>
      <!--
      <li>
        <h1>数据类型：</h1>
        <p><#if status=='0'>数据库数据</#if><#if status=='1'>文档数据</#if></p>
      </li>
      -->
    </ul>
  </div>
</div>
</body>
</html>

