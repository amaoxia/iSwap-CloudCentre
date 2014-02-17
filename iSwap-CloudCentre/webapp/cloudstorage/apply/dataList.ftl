<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body >
  <div class="main">
    <div class="main_c">
      <div></div>
      <table class="main_drop" style="margin:6px auto 0px auto;">
        <tr>
	   	<form  action="${path}/cloudstorage/apply/apply!dataList.action?id=${id}" method="post" name="pageForm">
        </tr>
      </table>
      <table class="tabs1">
        <tr>
          <th width="28" class="tright">序号</th>
          <#list metaAuthList as entity>
          <th>${entity.tableInfo.name}</th>
          <#if entity_index==5><#break></#if>
          </#list>
        </tr>
        <#assign len=metaAuthList?size>
        <#list obj as data>
           <tr class="trbg">
           <td>${data_index+1}</td>
           <#list   1..len as temp>
		    <td>${data[temp-1]?default('空')}</td>
		    <#if temp==6><#break></#if>
		    </#list>
         </tr>
        </#list>
      </table>
      							<div class="tabs_foot"> 
             					<img src="${path}/images/tabsf_line.jpg" align="absmiddle" />
       							<span class="page pageR">
						        <@pager.pageTag/>
						        </span>
						        </div>
						   </form>     
      <script type="text/javascript">
      			var DG = frameElement.lhgDG; 
					DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
					//关闭窗口 不做任何操作
					function singleCloseWin(){
					DG.cancel();
					}
      </script>
    </div>
</body>
</html>

