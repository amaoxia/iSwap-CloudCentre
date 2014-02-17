<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body>	
<form action="" method="post" name="saveForm" id="saveForm">
<input type="hidden" name="userId" value="${userId}"/>
	<div class="" style="height:100%">
    <table width="100%" height="400"  border="0" cellpadding="0" cellspacing="0" style="overflow:hidden;margin-top:3px;">
      <tr>
        <td height="100%" valign="top" style=""></td>
        <td width="100%" valign="top"><table width="98%" class="main_drop" style="margin:8px auto;">
          <tr>
            <td align="left">&nbsp;</td>
            <td align="right">路径名称
              <input name="pathName" onkeypress="showKeyPress()" onpaste="return false" type="text" id="path"  <#if pathName?exists>value="${pathName}"</#if> />
                <input name="input" type="button"  value="查询" onclick="search();" class="btn_s"/></td>
          </tr>
        </table>
          <table width="98%" align="center" class="tabs1">
            <tr>
              <th width="20">&nbsp;</th>
              <th width="28">序号</th>
              <th>路径名称</th>
              <th>访问权限</th>
            </tr>
             <#if ftpCatalogAuths?exists>
           <#if ftpCatalogAuths?size!=0>
              <#list ftpCatalogAuths as entity>
            <tr <#if entity_index%2=0>class="trbg"</#if>>
              <td class="trbg"><input  type="checkbox" name="ids" id="ids" value="${entity.id}" /></td>
              <td width="28">${entity_index+1}</td>
              <td class="trbg">${entity.path?default("")}</td>
              <td><p><strong>文件：</strong>
                <input type="checkbox" name="fileread" id="${entity_index+1}|fileread" onclick="editCatalog(this.id,${entity.id});" <#if entity.fileread==true>checked</#if>  />
                读取 
                <input type="checkbox" name="filewrite" id="${entity_index+1}|filewrite" onclick="editCatalog(this.id,${entity.id});" <#if entity.filewrite==true>checked</#if> />
                写入 
                <input type="checkbox" name="fileappend" id="${entity_index+1}|fileappend" onclick="editCatalog(this.id,${entity.id});"  <#if entity.fileappend==true>checked</#if>/>
                追加 
                <input type="checkbox" name="filedelete" id="${entity_index+1}|filedelete" onclick="editCatalog(this.id,${entity.id});" <#if entity.filedelete==true>checked</#if>  />
                删除&nbsp;&nbsp;&nbsp;&nbsp;<strong>目录：</strong>
                <input type="checkbox" name="catalist" id="${entity_index+1}|catalist" onclick="editCatalog(this.id,${entity.id});" <#if entity.catalist==true>checked</#if> />
                列表 
                <input type="checkbox" name="catacreate" id="${entity_index+1}|catacreate" onclick="editCatalog(this.id,${entity.id});" <#if entity.catacreate==true>checked</#if>  />
                创建 
                <input type="checkbox" name="catadelete" id="${entity_index+1}|catadelete" onclick="editCatalog(this.id,${entity.id});" <#if entity.catadelete==true>checked</#if> />
                删除<strong>&nbsp;&nbsp;&nbsp;&nbsp;子目录：</strong>
                <input type="checkbox" name="childcataextend" id="${entity_index+1}|childcataextend" onclick="editCatalog(this.id,${entity.id});" <#if entity.childcataextend==true>checked</#if>  />
              继承</p>              </td>
            </tr>
              </#list>
              </#if>
              </#if>
          </table>
       <div class="tabs_foot" style="width:98%;"> 
           <span class="tfl_btns"><img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px; margin-right:8px;" />
           <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
           <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
           <a href="javascript:remove();" class="tfl_blink"><b class="hot">删除</b></a></span> 
           <span class="page pageR">
           	<@pager.pageTag/>
           </span>
    	 </div>
          
        </td>
      </tr>
    </table>
    <p>&nbsp;</p>
  </div>
  <div class="clear"></div>
</div>
  <form>
  
<script type="text/javascript" src="${path}/js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/checkAll.js"></script>
<script type='text/javascript' src='${path}/js/crud.js'></script>	
<script type="text/javascript">
//时间控件
function setDay(o){
  WdatePicker({skin:'whyGreen'})
}
function saveTheForm(){
	document.forms['saveForm'].submit();
	
}
$(function(){
	window.parent.refreshTree();
})

function editCatalog(id,catalogId){
			var authName = id.split("|")[1];
			var authValue;
			if(document.getElementById(id).checked==true){
				authValue="true";
			}else{
				authValue="false";
			}
			jQuery.ajax(
				{
					type: "post",
					dataType: "html",
					url: "ftpClient!editCatalog.action",
					data: {
						authValue:authValue,
						authName:authName,
						catalogId:catalogId
					},
					async: false,
					success: function(msg){
					}
				}
			);
}
 function remove(){
	  var ids = document.getElementsByName('ids');
	  document.getElementById('saveForm').action="ftpClient!deleteCatalog.action";
	  if(checks()) {
		  if(confirm("确定删除该数据吗？")) {
		  document.getElementById('saveForm').submit();
	 	  }
	  } else {
	  	alert('至少选择一条数据');
	  }
}
function checks() {
  var id = document.getElementsByName('ids');
  for(var i=0;i<id.length;i++)
  {
    if(id[i].checked)
    {
        return true;  
    }
  }
  return false;
}
 function search(){
	  var path = document.getElementById('path').value;
	  document.getElementById('saveForm').action="ftpClient!listCatalog.action?path="+path;
		document.getElementById('saveForm').submit();
}
</script>
</body>
</html>
