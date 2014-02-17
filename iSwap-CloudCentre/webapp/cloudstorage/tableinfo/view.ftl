<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>字段信息</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<!--  关闭当前窗口-->
<script  type="text/javascript">
var DG = frameElement.lhgDG; 
DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
DG.addBtn( 'back', '上一步', returns); 
function returns(){
	//取消按钮
	DG.removeBtn('reset');
	DG.removeBtn('save');
	DG.removeBtn('back');
	DG.removeBtn('close');
	//实现逻辑
	DG.dgWin.history.back(-1);
	DG.reDialogSize(1000,470);
	DG.SetPosition('center','center'); 
}
//关闭窗口 不做任何操作
function singleCloseWin(){
DG.curWin.closeWindow();
}
</script>
</head>
<body >
<div class="pop_div6 pm6_c"  style="width:530px">
  <div class="item4">
    <ul class="item4_c">
        <li >
                      <h1>字段中文名：</h1>
                      <p>
                      ${name?default('')}
                      </p>
                    </li>
                     <li class="item_bg">
                      <h1>字段代码：</h1>
                      <p>
                    ${filedcode?default('')}
                      </p>
                    </li>
                       <li>
                      <h1>字段长度：</h1>
                      <p>
                      ${filedLength?default('')}
                      </p>
                    </li>
                    <li class="item_bg">
                      <h1>数据类型：</h1>
                      <p>
                      ${dataType?default('')}
                      </p>
					 </li>
					 <li>
                      <h1>是否主键：</h1>
                      <p>
                      <#if isPk=='1'>是<#else>否</#if>
                      </p>
					 </li>
					 <li class="item_bg">
                      <h1>可否为空：</h1>
                      <p>
                     <#if isNull=='1'>是<#else>否</#if>
                      </p>
                      </li>
    </ul>
  </div>
</div>
</body>
</html>