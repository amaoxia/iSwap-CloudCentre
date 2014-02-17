<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="${path}/iswapesb/workflow/esbworkflowAction!add.action" method="post" name="saveForm" id="saveForm">
<@s.token/>
<input type="hidden" value="0" id="id"/>
<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
  <div class="main">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td align="center" valign="middle" class=""  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
			<tr>
			  <td  height="100%" valign="top" ><div class="">
				  <ul class="item2_c">
					<li>
					  <p>流程名称：</p>
					  <span>
					  <input type="text" name="workFlowName" id="workFlowName" size="30"/> 
					  </span>
					  <span><div id='workFlowNameTip'></div></span>
					  </li>
					<li class="item2_bg">
						<table class="tabs1"  style="margin-top:0px;">
							<tr>
								<th width="5%">选择</th>
								<th width="15%">数据提供方</th>
								<th>数据接收方</th>
							</tr>
							<tr>
								<td><input name="ids" type="radio"/></td>
								<td>县1</td>
								<td>县2、县3</td>
							</tr>
							<tr>
								<td><input name="ids" type="radio"/></td>
								<td>县2</td>
								<td>县3.县4</td>
							</tr>
						</table>
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
