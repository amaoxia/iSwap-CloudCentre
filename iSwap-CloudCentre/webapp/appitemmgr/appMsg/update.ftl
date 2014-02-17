<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<form action="${path}/cloudcenter/appMsg/appMsg!update.action" method="post" name="saveForm" id="saveForm">
	<input type="hidden" name="id" id="id" value="${id}"/>
	<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
	  <div class="main">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td align="center" valign="middle" class=""  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
				<tr>
				  <td  height="100%" valign="top" ><div class="">
					  <ul class="item2_c">
						<li>
						  <p>应用名称：</p>
						  <span>
						  <input type="text" name="appName" value="${appName?default("")}" id="appName"/>
						  </span> 
						   <span><div id='appNameTip'></div></span>
						   </li>
						   <#-- 
						<li class="item2_bg">
						  <p>应用标识：</p>
						  <span><input type="text" name="appCode" value="${appCode?default("")}" id="appCode"/></span>
						  <span><div id='appCodeTip'></div></span>
						</li>	
						-->
						<li class="">
						  <p>描述</p>
						  <span>
						  <textarea  name="remark" cols="42" rows="5">${remark?default("")}</textarea>
						  </span>
						</li>
					  </ul>
					</div></td>
				</tr>
			  </table></td>
		  </tr>
		</table>
	</div>
	</div>
	<div class="clear"></div>
	</div>
	</form>
	<script src="${path}/js/jquery-1.5.1.js" type="text/javascript"></script>
	 <!--验证js-->
	<#include "/common/commonValidator.ftl">
	<#include "/common/commonUd.ftl">
	<script type="text/javascript" src="${path}/js/validator/appMsg/appMsg.js"></script>
	</body>
</html>
