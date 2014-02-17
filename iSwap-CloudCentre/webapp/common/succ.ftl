<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>操作成功页面</title>
<script type="text/javascript" src="${path}/js/jstree/jquery.js"></script>
<script>
$(document).ready(function(){
	var DG = frameElement.lhgDG; 
	DG.curWin.location.reload(); //同时刷新父窗口
	DG.curWin.closeWindow();//关闭窗口
});
</script>
<body>
</body>
</html>
