<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>无标题文档</title>
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
<div class="pop_div6 pm6_c" style="width:440px">
  <div class="item4">
    <ul class="item4_c">
     <li>
					  <h1>应用名称：</h1>
					  <p>
					  ${appName?default("")}
					  </p> </li>
					<li class="item_bg">
					  <h1>应用标识：</h1>
					  <p>${appCode?default("")}</p>
					</li>	
					<li class="">
					  <h1>描述：</h1>
					  <p>
					  	${remark?default("")}
					  </p>
					</li>
    </ul>
  </div>
</div>
</body>
</html>