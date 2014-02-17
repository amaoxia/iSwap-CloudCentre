<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/js/title/tip-yellow/tip-yellow.css" type="text/css" />
<link rel="stylesheet" href="${path}/js/title/tip-violet/tip-violet.css" type="text/css" />


<script src="${path}/js/title/jquery-1.4.min.js"></script>
<script src="${path}/js/title/jquery.poshytip.js"></script>

<!--验证-->
<script type="text/javascript" src="${path}/js/validate/jquery.validate.js"></script>
<script type="text/javascript" src="${path}/js/validate/jquery.metadata.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$("#pageForm").validate();
});

function cl(){
	var dg = parent.frameElement.lhgDG;			
	dg.curWin.location.reload();
	parent.cl();
}

$(function () {
		<#if ctsList?exists && ctsList?size != 0>
	 		<#list ctsList as cts>
	 			//弹出
				$('#rid${cts.id}').poshytip({
					content: function(updateCallback) {
						window.setTimeout(function() {
							updateCallback('<div class="float_div"><div><dl><dt>内容和标准</dt><dd>${cts.checkTarget.content?default("")}</dd><dt>计分方法</dt><dd>${cts.recodeMethod?default("")}</dd></dl></div></div>');
						}, 1000);
						return 'Loading...';
					}
				});	
			</#list>
		</#if>	
			});
</script>
<style type="text/css"> 
	form.pageForm label.error, label.error {
		/* remove the next line when you have trouble in IE6 with labels in list */
		color: red;
		font-style: italic
	}
	div.error { display: none; }
	input {	border: 1px solid black; }
	input.checkbox { border: none }
	input:focus { border: 1px dotted black; }
	input.error { border: 1px dotted red; }
	form.pageForm .gray * { color: gray; }
</style> 
</head>

<body>
<form name="pageForm" id="pageForm" action="${path}/performancemanage/check/check!checkScoreSave.action" method="post">
<div>
  <div>
    <div>
      <table class="tabs5" id="roleView">
        <tr>
          <th width="5%">类别</th>
          <th width="21%">评分项目</th>
          <th width="13%">评分标准及计分方法</th>
          <th width="9%">标准分</th>
          <th width="11%">系统给分</th>
          <th width="18%">人工评分</th>
          <th width="26%">得分说明</th>
        </tr>
         <#if ctList?exists && ctList?size != 0>
            <input type="hidden" name="modelId"  value="${modelId}"> 
			<input type="hidden" name="checkId"  value="${checkId}"> 
			<input type="hidden" name="deptId"  value="${deptId}"> 
			<input type="hidden" name="cycleType"  value="${cycleType}">
			<input type="hidden" name="checkYear"  value="${checkYear}">
			<input type="hidden" name="scoreSaveView"  value="checkScoreSave"> 
			<#assign num = 0>
         	<#list ctList as ct>
         		<#assign x = 0>
         		<#list ctsList as cts>
         			<#if ct.id == cts.checkTarget.parent.id>
         				<#assign x = x + 1>
         			</#if>        			
         		</#list>
         		<#if x gte 1>
         			<tr>
         				<td rowspan="${x}" class="trbg">${ct.name}</td>
         		</#if>
         		<#assign y = 0>
         		<#list ctsList as cts>
         			<#if ct.id == cts.checkTarget.parent.id>
         				<#if y gt 0>
         					<tr>
         				</#if>
         				<td><input type='hidden' name='checkTargetSetId' value="${cts.id}">${cts.checkTarget.name}</td>
         				<td><a id="rid${cts.id}" href="#">查看</a></td>
         				<td>${cts.fraction}</td>
         				<td>
	         				<input type="hidden" name="csList[${num}].id" value="${csList[num].id}">
	         				<input type="hidden" name="csList[${num}].sysGrade" value="${csList[num].sysGrade}">
	         				${csList[num].sysGrade}
         				</td>
         				<td><input type="text" name="csList[${num}].mannulGrade" value="${csList[num].mannulGrade}" class="{number:true, min:0, max:${csList[num].checkTargetSet.fraction?default("40")}, messages:{number:'请输入数字', min:'最小值为0', max:'最大值为${csList[num].checkTargetSet.fraction?default("40")}'}}">
         				</td>
         				<td class="i_align_left">
         				<textarea name="csList[${num}].gradeExplain" cols="14" rows="1" class="{maxlength:255, messages:{maxlength:' 最大长度为255字节'}}">${csList[num].gradeExplain?default("")}</textarea>
         				<td>
         				</tr>
         				<#assign y = y+1>
         				<#assign num = num+1>				
         			</#if> 
         		</#list>
         	</#list>
      	 </#if>
      </table>
    </div>
    <div class="sb_margin">
        <div class="btn_position"><input name="submit" type="submit" value="保存"  class=" btn2_s" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="link_btn" onclick="cl();">关闭窗口</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <#if message?exists>
        	<#if "success" == message><font size="3" color="#FF0000">保存成功!</font></#if>
        </#if>
        </div>

		</div>
    </div>
  </div>
  </form>
</body>
</html>
