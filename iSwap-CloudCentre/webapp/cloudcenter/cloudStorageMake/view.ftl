<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<div class="pop_div6 pm6_c" style="width:540px">
  <div class="item4">
    <ul class="item4_c">
		     <li>
					  <h1>空间名称：</h1>
					  <p>
					  	${storageName}
					  </p> 
					  </li>
					<li class="item_bg">
					  <h1>最大存储空间：</h1>
					  <p>
						${storageSizeCount}
					  </p>
					</li>
					<li class="">
					  <h1>所属应用：</h1>
					  <p>
					  <#list appMsgs as appMsg>
					 	${appMsg.appName?default("")},
					  </#list>
					  </p>
					</li>
					<li class="item_bg">
					  <h1>数据同步频率：</h1>
					  <p>
						${times?default('')}
					  </p>
					</li>					
					<li class="">
					  <h1>描述：</h1>
					  <p>
						${description}
					  </p>
					</li>
    </ul>
  </div>
</div>
</body>
</html>

