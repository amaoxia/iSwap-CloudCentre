<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据源注册</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="pop_01"  style="width:622px;height:540px;overflow-x:hidden;overflow-y:scroll;">
<div class="frameset_w" style="height:540px;background-color:#FFFFFF;">
  <div class="main"><div>
   <form name="save" action="${path}/rule/classification/classification!add.action" method="post" id="saveForm">
   <input type="hidden" id="id" value="0"/>
	<@s.token name="token"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td align="center" valign="middle" class=""  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
			<tr>
			  <td  height="100%" valign="top" ><div class="">
				  <ul class="item1_c">
					<li>
					  <p> <b>*</b>名称：</p>
					  <span>
					  <input type="text" size="30" name="name" id="name"/>
					  </span> 
					  <span> <div id="nameTip"></div></span>
					  </li>
					  <li  class="item2_bg">
					  <p> <b>*</b>编码：</p>
					  <span>
					  <input type="text" size="30" name="code" id="code" maxLength="20"/>
					  </span> 
					  <span> <div id="codeTip"></div></span>
					  </li>
					<li class="">
					  <p> <b>*</b>分类规则：</p>
					  <span>
					   <textarea cols="50" rows="10" id="rule" name="rule"></textarea>
					  </span>
					   <span> <div id="ruleTip"></div></span>
					</li>
				  </ul>
				</div></td>
			</tr>
		  </table></td>
	  </tr>
	</table>
</form>
</div>
</div>
<div class="clear"></div>
</div>
</div>
 </div>
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
<!--增删查改-->
<#include "/common/commonUd.ftl">
 <!--验证js-->
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/rule/classificationValidator.js'></script> 
</body>
</html>
