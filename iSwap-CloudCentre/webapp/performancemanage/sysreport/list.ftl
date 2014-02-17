<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统生成的通报</title>
<link href="${request.getContextPath()}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${request.getContextPath()}/css/lurt.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.cookie.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.hotkeys.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.jstree.js"></script>
<script src="${request.getContextPath()}/js/title/jquery.poshytip.js"></script>
<script>
      function sub(){
      	    document.saveForm.submit();
	   }
</script>
<!--通用方法-->
<script type="text/javascript" src="${request.getContextPath()}/js/iswap_common.js"></script>
</head>
<body onclick="parent.hideMenu()" class="main">
<div style="height:100%;margin:5px;">
  <div class="clear"></div>
  <form name="saveForm"  action="${request.getContextPath()}/performancemanage/sysreport/sysreportMgr!download.action" method="post">
  <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
    <tr>
      <td height="100%" valign="top" bgcolor="#FFFFFF" ><div class="item1">
          <ul class="item1_lu">
            <li>
              <p><span><strong>考核通报名称：</strong></span>${check.name}</p>
              <input name="check.id" type="hidden" value="${check.id}" />
            </li>
            <#list listDatas as list> 
                <#if list.fileType = '1'>
                   <li class="item2_bg">
		              <input name="checkbox" type="checkbox" id="checkbox" checked="checked" />
		              <strong>评分汇总表：</strong>${list.fileName?default('')}
		            </li>
                </#if>
                <#if list.fileType = '2'>
	                 <li class="item2_bg">
			              <input name="checkbox2" type="checkbox" id="checkbox2" checked="checked" />
			               <strong>考核评分表：</strong>${list.fileName?default('')}
			         </li>
                </#if>
            </#list>
            <li class="item_bg">
              <p class="btn_s_m2" align="center">
                <input name="input" type="button" value="下载" onclick="sub();" class=" btn2_s"/>
                &nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="link_btn" onclick="dg.cancel();">关闭窗口</a></p>
            </li>
          </ul>
      </div></td>
    </tr>
  </table>
 </form>
</div>
<script type="text/javascript" src="${request.getContextPath()}/js/lurt.js"></script>
</body>
</html>
