<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<!--  关闭当前窗口-->
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
<div class="pop_div6 pm6_c"  style="width:530px">
  <div class="item4">
    <ul class="item4_c">
      <li>
        <h1>指标名称：</h1>
        <p>${targetName?default('')}</p>
      </li>
      <li class="item_bg">
        <h1>所属部门：</h1>
        <p><#if sysDept?exists>${sysDept.deptName?default('')}</#if></p>
      </li>
      <li>
        <h1>数据源： </h1>
        <p><#if couldDataSource?exists>${couldDataSource.sourceName?default('')}</#if></p>
      </li>
      <#if metaApp?size gt 0>
      <li class="item_bg">
        <h1>应用服务：</h1>
        <p>	
        			<#list appMsgs as data>
                       <#assign isTrue="">
                       <#list metaApp as app>
                       <#if app.appMsg.id==data.id><#assign isTrue="selected"><#break></#if>
                       </#list>
                       <#if isTrue=='selected'>${data.appName?default('')},</#if>
						</#list>
						</p>
      </li>
      </#if>
      
      <#if metaDataBasicType?exists> 
       <li class="item_bg">
        <h1>基础库类型：</h1>
        <p>
        <#list basics as data>
						<#if data.id==metaDataBasicType.id></#if> ${metaDataBasicType.basicTypeName?default('')}<#break>
		</#list>
		</p>
      </li>
      </#if>
      
      <li  <#if (!metaDataBasicType?exists)&&(metaApp?size == 0)>class="item_bg"</#if>>
        <h1>表名称：</h1>
        <p>${tableName?default('')}</p>
      </li>
         <li class="item_bg">
        <h1>负责人邮件地址：</h1>
        <p><#if createDate?exists> ${overPeopleEmail?default('')}<#else>无</#if></p>
      </li>
         <li>
        <h1>指标负责人：</h1>
        <p><#if createDate?exists> ${overPeople?default('')}<#else>无</#if></p>
      </li>
         <li class="item_bg">
        <h1>创建时间：</h1>
        <p><#if createDate?exists> ${createDate?default('')}<#else>无</#if></p>
      </li>
    </ul>
  </div>
</div>
</body>
</html>