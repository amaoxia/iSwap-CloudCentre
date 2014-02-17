<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
</head>
<body>	
<form action="ftpClient!ftpAccessRuleList.action" method="post" name="pageForm" id="pageForm">
<input type="hidden" id="ftpUserId" value="${id}"/>
<input type="hidden" name="id" value="${id}"/>
<div class="frameset_w" style="height:100%">
  <div class="main">
    <table width="100%" height="400"  border="0" cellpadding="0" cellspacing="0" style="overflow:hidden;margin-top:3px;">
      <tr>
        <td width="100%" valign="top"><table width="98%" class="main_drop" style="margin:8px auto;">
          <tr>
            <td align="left">&nbsp;</td>
            <td align="right">路径名称
              <input name="ip" type="text" onkeypress="showKeyPress()" onpaste="return false" id="path"  <#if ip?exists>value="${ip}"</#if>  />
                <input name="input" type="button" onclick="search();"  value="查询"  class="btn_s"/></td>
          </tr>
          <table width="98%" align="center" class="tabs1">
            <tr>
              <th width="20">&nbsp;</th>
              <th width="28">序号</th>
              <th>IP地址规则</th>
              <th>访问情况</th>
              <th>操作</th>
            </tr>
            <#list ftpAccessRules as entity>
            <tr <#if entity_index%2=0>class="trbg"</#if>>
              <td><input name="ids" type="checkbox" value="${entity.id}" /></td>
              <td width="28">${entity_index+1}</td>
              <td>${entity.ip?default("")}</td>
              <td><span class="STYLE1">${entity.enableaccess?string("允许访问","拒绝访问")}</span></td>
              <td class="trbg"><a href="javascript:void(0)" id="hz1" onclick="editFtpAccessRule(${entity.id});" class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />编辑</a>
              <!--<a href="javascript:void(0)" class="tabs1_cz"><img src="${path}/images/czimg_sc.gif" />删除</a>--></td>
            </tr>
            </#list>
          </table>
           <div class="tabs_foot" style="width:98%;"> 
           <span class="tfl_btns"><img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px; margin-right:8px;" />
           <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
           <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
           <a href="javascript:remove();" class="tfl_blink"><b class="hot">删除</b></a></span> 
           <span class="page pageR">
           	<@pager.pageTag/>
           </span>
    	 </div></td>
      </tr>
    </table>
    <p>&nbsp;</p>
  </div>
        <div class="right_main">
          <iframe id="content" name="content" width="100%"  height="520px;" scrolling="Auto" frameborder="0"  style="border:0px" allowtransparency="true"></iframe>
      </div>
  <div class="clear"></div>
</div>
<form>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">
<script type="text/javascript">
	<!--页面上弹出窗口-->
	function showWindow(url,titleValue,widths,heights){
		var dg = new J.dialog({ page:url,title:titleValue, cover:true ,rang:true,width:widths,height:heights,autoSize:true});
	    dg.ShowDialog();
	}
//时间控件
function setDay(o){
  WdatePicker({skin:'whyGreen'})
}
function saveTheForm(){
	document.forms['saveForm'].submit();
}
 function remove(){
	  var ids = document.getElementsByName('ids');
	  document.getElementById('pageForm').action="ftpClient!deleteFtpAccessRule.action";
	  if(checks()) {
		  if(confirm("确定删除该数据吗？")) {
		  document.getElementById('pageForm').submit();
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
	  document.getElementById('pageForm').action="ftpClient!ftpAccessRuleList.action?path="+path;
		document.getElementById('pageForm').submit();
}
     function editFtpAccessRule(accessRuleId){
     	var id=document.getElementById('ftpUserId').value;
     	showWindow('${path}/ftp/ftpClient/ftpClient!editFtpAccessRule.action?accessRuleId='+accessRuleId+'&id='+id,'IP访问规则编辑',290,370);
     }
</script>
</body>
</html>
