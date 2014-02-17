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

<script type="text/javascript" src="${path}/js/jstree/jquery.js"></script>
<script type="text/javascript" src="${path}/js/jstree/jquery.cookie.js"></script>
<script type="text/javascript" src="${path}/js/jstree/jquery.hotkeys.js"></script>

<script type="text/javascript" src="${path}/js/jstree/jquery.jstree.js"></script>
<script src="${path}/js/title/jquery.poshytip.js"></script>


<!--弹出窗口-->
<script type="text/javascript" src="${path}/js/windowopen/lhgcore.min.js"></script>
<script type="text/javascript" src="${path}/js/windowopen/lhgdialog.js"></script>
<!-- 验证方法-->
<script type="text/javascript" src="${path}/js/crud.js"></script>
<script type="text/javascript">    
	function opdg(url,title){
		var dg = new J.dialog({ id:'hz0', page:url,title:title, cover:true ,rang:true,width:770,height:569,resize:false});
		dg.ShowDialog();
	}
</script>
</head>
<body onclick="parent.hideMenu()">
<form name="pageForm" id="pageForm" action="check!createReport.action" method="post">
<div class="frameset_w" style="height:100%">
  <div class="main">
  	 <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />考核评分汇总表</div>
    </div>
    <div class="tabs3_title">${(entityobj.name)?default("")}</div>
    <div class="tabs3_date">汇总日期：${entityobj.checkTime?string("yyyy年MM月dd日")}</div>

    <div class="main_c">
    <div class="tabs3_w">
    <div class="tabs3_scroll">
      <table class="tabs3">
      
      	<!-- 表头 -->
        ${table?default("")}
        
        <tbody>
		<#if cgList?exists && cgList?size != 0>
			<#list cgList?sort_by("totalPoints")?reverse as entity>	
	          <tr <#if (entity_index+1)%2==1>class="trbg"</#if> onclick="selectedTD(this);">
	            <td>${entity_index+1}</td>
	            <td><p><#if entityobj.status != "2"><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/performancemanage/check/check!updateScoDeptView.action?modelId=${modelId}&id=${id}&deptId=${entity.department.id}','${entity.department.name}');">${entity.department.name}</a><#else>${entity.department.name}</#if></p></td>
	            <#list entity.csList as cgcs>
	            		<#if cgcs.mannulGrade =="">
	            			<td>0</td>           		
	            		<#else>
	            			<td>${(cgcs.mannulGrade)?default("0")}</td>
	            		 </#if>
	            </#list>
	            			<td>${entity.totalPoints}</td><!-- 总分 -->
	            </tr>
	         </#list>
         </#if>
          </tbody>
      </table>
      </div>
</div>
      <div class="tabs3_fbtn">
        <a href="${path}/performancemanage/check/check!list.action?checkYear=${checkYear}"  class="link_btn">返回考核列表</a>
        <#if entityobj.status != "2">
        	<input name="" type="button"  value="修改分数"  class="btn_s" onclick="location.href='${path}/performancemanage/check/check!gatherList.action?modelId=${modelId}&id=${id}&checkYear=${checkYear}&gatherList=updateScore'"/>
        	<input name="" type="button"  value="生成通报"  class="btn_s" onclick="javascript:rep('check!createReport.action?checkYear=${checkYear}&ids=${entityobj.id}')"/>
        </#if>
      </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
</form>
</body>
</html>
