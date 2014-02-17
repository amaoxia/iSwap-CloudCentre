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
        <h1>监听名称：</h1>
        <p>${listenName?default('')}</p>
      </li>
      <li class="item_bg">
        <h1>消息管理名称：</h1>
        <p>${jmsServerInfo.jmsServerName?default('')}</p>
      </li>
      <li>
      	<h1>队列名称：</h1>
        <p>${queueName?default('')}</p>
      </li>
      <#-- 
      <li>
        <h1>流程名称：</h1>
        <p>${workFlow.workFlowName?default('')}</p>
      </li>
      <li class="item_bg">
        <h1>所属应用：</h1>
        <p>${appMsg.appName?default('')}</p>
      </li>
      <li>
        <h1>调度频率：</h1>
        <p>${times?default('')}</p>
      </li>
      -->
      <li class="item_bg">
        <h1>描述：</h1>
        <p>${notes?default('')}</p>
      </li>
    </ul>
  </div>
</div>
</body>
</html>

