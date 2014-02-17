<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>	
<script type="text/javascript">
var dg = frameElement.lhgDG;
dg.cancel();
alert("${errorInfo?default('测试失败！')}");
dg.curWin.reloadPage();
</script>

</head>
<body>	
</body>
</html>
