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
      <li class="item_bg">
        <h1>监听名称：</h1>
        <p>${listenName?default('')}</p>
      </li>
      <li>
        <h1>实例名称：</h1>
        <p>${dbName?default('')}</p>
      </li>
       <#--
      <li class="item_bg">
        <h1>表名称：</h1>
        <p>${collectionName?default('')}</p>
      </li>
      <li>
        <h1>流程名称：</h1>
        <p><#if workFlow?exists>${workFlow.workFlowName?default('')}<#else>(无)</#if></p>
      </li>
      <li class="item_bg">
        <h1>字段名称：</h1>
        <p>${filedName?default('')}</p>
      </li>
      <li>
        <h1>字段状态：</h1>
        <p>${filedStatus?default('')}</p>
      </li>
      -->
      <li class="item_bg">
        <h1>状态：</h1>
        <p><#if status?exists>禁用<#else><#if status=='0'>禁用</#if><#if status=='1'>启用</#if></#if></p>
      </li>
      <li class="">
        <h1>创建时间：</h1>
        <p>${createDate?default('')}</p>
      </li>
      <#--
      <li class="item_bg">
        <h1>描述：</h1>
        <p>${nodes?default('')}</p>
      </li>
      -->
    </ul>
  </div>
</div>
</body>
</html>

