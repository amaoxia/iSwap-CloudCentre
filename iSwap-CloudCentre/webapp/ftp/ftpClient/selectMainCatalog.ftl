<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/datepicker/WdatePicker.js"></script>

<link rel="stylesheet" href="${path}/css/manager/xtree.css" type="text/css" />
<script type='text/javascript' src='${path}/js/xtree.js'></script>
<script type='text/javascript' src='${path}/js/webfxRediotreeitem.js'></script>

<script type="text/javascript">
var DG = frameElement.lhgDG; 

//关闭窗口 不做任何操作
function singleCloseWin(){
DG.cancel();
}
DG.addBtn( 'save', '保存', saveWin); 
function saveWin() {
	//实现逻辑
	setHomedirectory();
}
var dg = frameElement.lhgDG;
//时间控件
function setDay(o){
  WdatePicker({skin:'whyGreen'})
}

function setHomedirectory() { 
  	var ids = document.getElementsByName("id");  
	        var homedirectory = "";
			for(var i=0;i<ids.length;i++){
			    if(ids[i].checked==true){
			         homedirectory = ids[i].value;
			     }
			}
    dg.cancel();
	dg.curDoc.getElementById('homedirectory').value = homedirectory;
}
</script>

</head>
<body>	
<form action="${path}/ftp/ftpClient/ftpClient!saveCatalog.action" method="post" name="saveForm" id="saveForm">
	<div style="padding:20px;overflow:auto;">
			 ${catalogTree?default('')}
	</div>
	  <!-- <p class="btn_s_m2">
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input id="sub" type="button" value="确定" onclick="setHomedirectory()" class=" btn2_s"/>
               &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="link_btn" onclick="dg.cancel();">关闭窗口</a></p>-->
<form>
</body>
</html>
