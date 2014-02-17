<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body style="width:100%;height:100%;overflow-x:hidden;overflow-y:scroll;">
<form action="${path}/exchange/task/taskAction!build.action" method="post" name="saveForm" id="saveForm">
<@s.token/>
<div class="pop_div6 pm6_c" > 
  <div class="item1">
    <ul class="item1_c">
      <li>
        <p>任务时间段：</p>
        <span>
       <input id="begin" name="begin" class="Wdate" style="width: 100px;" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')||\'2020-10-01\'}'})"/>
	          	至
	   <input id="end" name="end" class="Wdate" style="width: 100px;" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'begin\')}',maxDate:'2020-10-01'})"/>
        </span>
      </li>
     <li >
        <p>部门：</p>
        <span>
        <select name="deptId" id="deptId" style="width:200px;" onchange="obtainItem(this.value);">
          <option value="0" selected="selected">所有部门</option>
           <#list sysDeptList as entity>
          <option value="${entity.id}">${entity.deptName}</option>
          </#list>
        </select>
        </span>
      </li>
      <li class="item_bg">
        <p>指标项：</p>
        <span>
        <select id="itemId" name="itemId" style="width:200px;">
        </select>
        </span> </li>
    </ul>
</div>
</div>
<form>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<!--弹出窗口-->
<#include "/common/commonLhg.ftl">
<!--验证js-->
<script type='text/javascript' src='${path}/js/validator/iswapqa/ruleValidator.js'></script> 
<#include "/common/commonValidator.ftl">
<!--日期控件-->
<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
<script type="text/javascript">
var DG = frameElement.lhgDG; 
DG.addBtn( 'cancle', '关闭', cancle); 
DG.addBtn( 'save', '添加', saveWin); 
function saveWin() {
	//实现逻辑
	addTask();
}
function cancle(){
DG.cancel();
}
function addTask(){
	if (check()) {
	document.forms['saveForm'].submit();
	alert("保存成功！");
	//DG.cancel();
	}
}
function check() {
  	if($('#begin').val() == "") {
  		alert("请选择起始日期");
  		return false;
  	}
  	if($('#end').val() == "") {
  		alert("请选择截止日期");
  		return false;
  	}
  	return true;
}
 function obtainItem(id) {
	  if(parseInt(id) == 0) {
	  	$('#itemId').empty().append("<option value='0'>所有指标项</option>");
	  } else {
	  	$.ajax({
	  		   type: "POST",
			   url: "${path}/exchange/item/item!obtainitem.action",
			   data: "deptId=" + id,
			   success: function(data){
			     $('#itemId').empty().append(data);
			   }
	  	});
	  }
  }
</script>
</body>
</html>
