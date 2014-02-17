<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#global path = request.getContextPath() >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/js/title/tip-yellow/tip-yellow.css" type="text/css" />
<link rel="stylesheet" href="${path}/js/title/tip-violet/tip-violet.css" type="text/css" />
<!--
<script type="text/javascript" src="${path}/js/jstree/jquery.js"></script>
<script type="text/javascript" src="${path}/js/jstree/jquery.cookie.js"></script>
<script type="text/javascript" src="${path}/js/jstree/jquery.hotkeys.js"></script>
<script type="text/javascript" src="${path}/js/jstree/jquery.jstree.js"></script>
<script src="${path}/js/title/jquery.poshytip.js"></script>
<script type="text/javascript" src="${path}/js/crud.js"></script>
<script type="text/javascript" src="${path}/js/windowopen/lhgcore.min.js"></script>
<script type="text/javascript" src="${path}/js/windowopen/lhgdialog.js"></script>

<script type="text/javascript" src="${path}/js/iswap_common.js"></script>
-->
<script type='text/javascript' src='${path}/js/jquery.js'></script>
<#include "/common/commonLhg.ftl">
<#include "/common/commonUd.ftl">

<script type="text/javascript" >
function isSub(name)
{
		var msg = document.getElementById("description").value;
		document.saveMQ.action="queueInfo!msgTest.action?queueName="+name+"&message="+msg;
		document.saveMQ.submit();
		document.getElementById("description").value= "";
		document.getElementById("description").innerText= "";
		alert("消息 ： '" + msg + "' 发送成功！");
		DG.cancel();
}

var DG= frameElement.lhgDG; 
function returns(){
	//取消按钮
	DG.removeBtn('reset');
	DG.removeBtn('save');
}

DG.addBtn( 'test', '测试', saveWin); 
function saveWin() {
	//实现逻辑
	isSub('${entityobj.queueName}')
}
</script>

</head>

<body onload="returns();">
<div class="pop_01" style="width:700px" >
  <div class="pop_mian">
  
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%">
        <form name="saveMQ" action="" method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
                  <ul class="item1_c">
                    <li class="item_bg">
                      <p>请输入测试消息:</p>
                      <span>
                      <textarea name="description" id="description" cols="90" rows="6"></textarea>
                      </span> 
                    </li>
                  </ul>
                 </td>
            </tr>
          </table>
          </form>
          </td>
        <td rowspan="2" class="pm_right"><img src="${path}/images/pop_051.png" width="7" height="1" /></td>
      </tr>
     
    </table>
  </div>
</div>
</body>
</html>
