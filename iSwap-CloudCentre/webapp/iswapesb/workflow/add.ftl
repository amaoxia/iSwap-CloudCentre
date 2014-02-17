<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>桥接流程定制</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
  <div class="main"><div>
	<form action="${path}/iswapesb/workflow/esbworkflowAction!add.action" method="post" name="saveForm" id="saveForm">
	<@s.token/>
	<input type="hidden" id="changeItemId" name="changeItem.id" value="${changeItemId?default('')}"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td align="center" valign="middle" class=""  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
			<tr>
			  <td  height="100%" valign="top" ><div class="">
				<ul class="item1_c">
					<li>
					  <p><b>*</b>流程名称：</p>
					  <span>
					  <input type="text" size="30" name="workFlowName" id="workFlowName"/>
					  </span> 
					 <span> <div id="workFlowNameTip"></div></span>
					</li>
					<!-- 
					<li class="item2_bg">
					  <p><b>*</b>流程英文名称：</p>
					  <span>
					  <input type="text" size="30" id="workFlowCode" name="workFlowCode"/>
					  </span> 
					   <span> <div id="workFlowCodeTip"></div></span>
					  </li>
					  -->
				  </ul>
				</div></td>
			</tr>
		  </table></td>
	  </tr>
	</table>
	<input type="hidden" id="id" value="0"/>
</form>
</div>
</div>
<script type='text/javascript'  src="${path}/js/jquery-1.5.1.js"></script>
 <!--验证js-->
<#include "/common/commonValidator.ftl">
<#include "/common/commonUd.ftl">
<script type='text/javascript' src='${path}/js/validator/cloudnode/esbworkflowValidator.js'></script>
<script type="text/javascript">
   function clo(){
   var dg = frameElement.lhgDG;
    dg.cancel();
    dg.curWin.location.reload(); 
   }
//验证用户是否通过
function isSub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
}
</script>
</body>
</html>
