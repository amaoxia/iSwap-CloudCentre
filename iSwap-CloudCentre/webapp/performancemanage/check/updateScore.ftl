<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>考核评分汇总表</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />

<!--验证-->
<script type="text/javascript" src="${path}/js/validate/jquery.js"></script>
<script type="text/javascript" src="${path}/js/validate/jquery.validate.js"></script>
<script type="text/javascript" src="${path}/js/validate/jquery.metadata.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#pageForm").validate();
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
<div class="frameset_w" style="height:100%">
  <div class="main">
  	 <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />考核评分汇总表</div>
    </div>
    <div class="tabs3_title">${(entityobj.name)?default("")}</div>
    <div class="tabs3_date">汇总日期：${entityobj.checkTime?string("yyyy年MM月dd日")}</div>

    <div class="main_c ">
    <div class="tabs3_scroll tabs3_w">
      <table class="tabs3">
      
        <!-- 表头 -->
        ${table?default("")}

        <tbody>
        <#if cgList?exists && cgList?size != 0>
        	<input type="hidden" name="modelId"  value="${modelId}"> 
			<input type="hidden" name="checkId"  value="${id}">
			<input type="hidden" name="checkYear"  value="${checkYear}"> 
			<input type="hidden" name="scoreSaveView"  value="gatherListSave">
		<#assign y = 0>	
        <#list cgList?sort_by("totalPoints")?reverse as entity>	
          <tr <#if (entity_index+1)%2==1>class="trbg"</#if> onclick="selectedTD(this);">
            <td>${entity_index+1}</td>
            <td><p>${entity.department.name}</p></td>
            <#list entity.csList as cgcs>
            		<#if cgcs.mannulGrade =="">
            			<td>
            		  	<input type="hidden" name="csList[${y}].id" value="${cgcs.id}">         
			          	<input type="hidden" name="csList[${y}].sysGrade" value="${cgcs.sysGrade}">		          			           			
            			<input type="hidden" name="checkTargetSetId" value="${cgcs.checkTargetSet.id}">
            			<input type="hidden" name="deptIds" value="${entity.department.id}">
            			<input name="csList[${y}].mannulGrade" type="text"  value="0" class="narrow_input_text {number:true, min:0, max:${cgcs.checkTargetSet.fraction?default("40")}, messages:{number:'请输入数字', min:'最小值为0', max:'最大值为${cgcs.checkTargetSet.fraction?default("40")}'}}">
            			</td>
            			<#assign y = y + 1>           		
            		<#else>
            			<td>
            		  	<input type="hidden" name="csList[${y}].id" value="${cgcs.id}">         
			          	<input type="hidden" name="csList[${y}].sysGrade" value="${cgcs.sysGrade}">		          			           			
            			<input type="hidden" name="checkTargetSetId" value="${cgcs.checkTargetSet.id}">
            			<input type="hidden" name="deptIds" value="${entity.department.id}">
            			<input name="csList[${y}].mannulGrade" type="text" value="${(cgcs.mannulGrade)?default("0")}" class="narrow_input_text {number:true, min:0, max:${cgcs.checkTargetSet.fraction?default("40")}, messages:{number:'请输入数字', min:'最小值为0', max:'最大值为${cgcs.checkTargetSet.fraction?default("40")}'}}">
            			</td>
            			<#assign y = y + 1>
            		 </#if>
            </#list>
            			<td>${entity.totalPoints}</td><!-- 总分 -->
            </tr>
         </#list>
         </#if>
          </tbody>
      </table>
      </div>

      <div class="tabs3_fbtn">
        <a href="${path}/performancemanage/check/check!gatherList.action?modelId=${modelId}&id=${entityobj.id}&checkYear=${checkYear}&gatherList=tablist"  class="link_btn">返回考核列表</a>
        <input name="submit" type="submit" value="保存"  class=" btn_s" />
      </div>
    </div>
    <div class="clear"></div>

  </div>
  <div class="clear"></div>
</div>
</form>
</body>
</html>
