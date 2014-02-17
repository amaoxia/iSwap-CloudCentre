<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表结构信息</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="pop_01"   style="width:100%;height:100%;overflow-x:hidden;overflow-y:scroll;">
    <div class="main_c" >
    <form id="pageForm" method="post"  name="pageForm"	action="${path}/exchange/tabledesc/tabledesc!list.action">
           <table class="main_drop">
        				<tr>
					    </tr>
		   </table>                              
						      <table class="tabs1"  style="margin-top:0px;">
						        	 <tr onclick="selectedTD(this);">
						              <th width="28" class="tright">序号</th>
         							  <th>目录名称</th>
							          <th>操作</th>
						        </tr>
						       <#list catalogs as entity>
          						<tr c<#if (entity_index+1)%2==0>lass="trbg"</#if> onclick="selectedTD(this);">
						          <td>${entity_index+1}</td>
						          <td>${entity.catalogName?default('无')}</td>
						          <td>
						           <a href="#" class="tabs1_cz" onclick="selectCatalog('${entity.id}')">
						          <img src="${path}/images/small9/s_chakan.gif" />选择</a> 
						          </td>
						        </tr>
						        </#list>
						      </table>
						    </div>
						    </div>
						     </form>
						     <script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">
<script type="text/javascript">    
var dg = frameElement.lhgDG;
function selectCatalog(id){
	var url = "${path}/exchange/item/item!getCatalogTreePage.action?itemIds=${itemIds}&catalogId="+id;
	var win = dg.dgWin;
	win.location.href=url;
}

dg.removeBtn('prop');
dg.removeBtn('back');

dg.addBtn( 'closewin', '关闭窗口', closeWin); 
function closeWin(){
	dg.cancel();
}
</script>
			</body>
</html>