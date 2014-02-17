<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<#include "/common/taglibs.ftl">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		        <form name="saveForm" id="saveForm" action="jmsInfoAction!update.action" method="post">
		       <table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td align="center" valign="middle" class=""  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
						<tr>
						  <td  height="100%" valign="top" ><div class="">
		                  <ul class="item2_c">
		                    <li>
		                     <p><b style="color:red">*</b>服务名称：</p>
		                      <span>
		                      <input name="jmsServerName" id="jmsServerName" value="${entityobj.jmsServerName?default("")}" type="text" size="30"  />
		                     <input type="hidden"  name="id" id="id"  value="${id}"/>
		                      </span>
		                      <span><div id="jmsServerNameTip"></div></span>
		                    </li>
		                    <li class="item2_bg">
		                   	 <p><b style="color:red">*</b>上下文工厂：</p>
		                      <span>
		                      <input name="jmsFactory" id="jmsFactory" value="${entityobj.jmsFactory?default("")}" type="text" size="30" />
		                      </span>
		                       <span><div id="jmsFactoryTip"></div></span>
		                    </li>
		                    <li>
		                   	 <p><b style="color:red">*</b>连接工厂：</p>
		                      <span>
		                      <input name="conntFactory" id="conntFactory" value="${entityobj.conntFactory?default("")}" type="text" size="30" />
		                      </span>
		                       <span><div id="conntFactoryTip"></div></span>
		                    </li>
		                     <li class="item2_bg">
		                      <p><b style="color:red">*</b>连接地址：</p>
		                     <span>
		                      <input name="url" id="url" type="text" size="30"  value="${entityobj.url?default("")}"/>
		                      </span>
		                      <span><div id="urlTip"></div></span>
		                    </li> 
		                    <li>
		                      <p>用户名： </p>
		                     <span>
		                      <input name="userName"  value="${entityobj.userName?default("")}" type="text" size="30" />
		                      </span>
		                      <span></span>
		                    </li>
		                	  <li class="item2_bg">
		                      <p>密码：</p>
		                     <span>
		                      <input name="passWord" type="password" size="30" value="${entityobj.passWord?default('')}"/>
		                      </span>
		                      <span></span>
		                    </li>
		                  </ul>
		                </div></td>
						</tr>
					  </table></td>
				  </tr>
				</table>
			</div>
			<div class="clear"></div>
		</form>
		<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
		<script type='text/javascript' src='${path}/js/validator/iswapmq/jmsValidator.js'></script>
		<#include "/common/commonValidator.ftl">
		<#include "/common/commonUd.ftl">
		<script type="text/javascript">
		$(function(){
			 $("#jmsServerName").defaultPassed();
		});
		</script>
	</body>
</html>
