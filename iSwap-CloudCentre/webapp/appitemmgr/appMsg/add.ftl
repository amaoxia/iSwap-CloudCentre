<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<script src="${path}/js/jquery-1.5.1.js" type="text/javascript"></script>
	</head>
	<body class="pm01_c">
		<form action="${path}/appitemmgr/appMsg/appMsg!add.action" method="post" name="saveForm" id="saveForm">
			<@s.token name="token"/>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td align="center" valign="middle" height="100%" >
						<table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
						<tr>
						  <td  height="100%" valign="top" ><div class="">
							  <ul class="item2_c">
								<li>
								  <p>应用名称：</p>
								  <span>
									  <input type="text" name="appName" id="appName" maxLength="20" size="30"/> 
									  <input type="hidden"  id="id" value="0"/>
								  </span>
								  <span><div id='appNameTip'></div></span>
								  </li>
								  <#-- 
								<li class="item2_bg">
								  <p>应用标识：</p>
								  <span><input type="text" name="appCode" id="appCode" maxLength="10" size="30"/></span>
								  <span><div id='appCodeTip'></div></span>
								</li>	
								-->
								<li class="">
								  <p>描述</p>
								  <span>
								  <textarea name="remark" cols="42" rows="5"></textarea>
								  </span>
								</li>
							  </ul>
							</div></td>
						</tr>
					  </table>
					 </td>
				  </tr>
				</table>
		</form>
	</body>
</html>
		<#include "/common/commonValidator.ftl">
		<#include "/common/commonUd.ftl">
		<script type="text/javascript" src="${path}/js/validator/appitemmgr/appMsg/appMsg.js"></script>
