<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="pop_div6 pm6_c" style="width:100%;height:100%;overflow-x:hidden;overflow-y:scroll;"> 
${testStr}
 </div>
 <script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
<!--弹出窗口-->
<#include "/common/commonLhg.ftl">
<!--验证js-->
<script type='text/javascript' src='${path}/js/validator/iswapqa/ruleValidator.js'></script> 
<#include "/common/commonValidator.ftl">
<!--日期控件-->
<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
<script type="text/javascript">
var DG = frameElement.lhgDG; 
</script>
</body>
</html>
