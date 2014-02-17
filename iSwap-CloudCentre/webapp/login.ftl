<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>iSwap V6.0  政务信息资源共享平台</title>
		<link href="${path}/css/login.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}/js/jquery.js"></script>
		<script type="text/javascript">
			if(window.top!=window)
			{
		     window.top.location='${path}/toLogin.jsp';
			}
			
		//提交表单
		function subForm(){
			var _loginName=$("#loginName").val();
			var _password=$("#password").val();
		 	_loginName  = _loginName.replace(/\s+/g,""); 
		 	_password    = _password.replace(/\s+/g,"");
		 	if(_loginName.length==0){
		 	 alert("用户名不能为空!");
		 	return;
		 	}
		 	if(_password.length==0){
		 	alert("密码不能为空!");
		   	return ;
		 	}
			document.forms[0].submit();
		}
		//回车键提交
		function loginKeyDown(event) {  
		  if (event.keyCode == 13) {   
		  	document.forms["login"].submit();
		  }  
		} 
		</script>
	</head>
	<body>
			<div class="login_bg">
				<div class="login_main">
				<form action="${path}/login.action" name="login" method="post">
					<ul>
						<li class="username inputBG">
							<span>用户名</span>
							<input name="loginName" type="text" id="loginName"
								value="${loginName?default('')}"  maxLength="20"/>
								<#if loginNameMsg?exists>
							 <div class="login_ts">
							<img src="${path}/images/error.png" align="absmiddle" />
								${loginNameMsg?default('系统不存在此用户，请您核实用户名')}
							</div>
							</#if>
						</li>
						<li class="password inputBG">
							<span>密&nbsp;&nbsp;&nbsp;码</span>
							<input name="password" type="password" id="password"
								value="${password?default('')}"  onkeypress="loginKeyDown(event)" maxLength="20"/>
						    <#if passwordMsg?exists>
					     	<div class="login_ts">
							 <img src="${path}/images/error.png" align="absmiddle" />
						  		${passwordMsg?default('')}
							</div>
							</#if>
						</li>
						<li class="remember">
							<span></span>
							<input type="checkbox" value="1" name="checkValue" <#if checkValue?exists><#if checkValue=='1'>checked="true" </#if></#if>/>
							记住密码
						</li>
						<li class="login_btn">
							<a href="#"  onclick="subForm()">
							<img src="${path}/images/login_btn.png" 
							alt="登录" name="Image1" width="64" height="26" border="0" align="absmiddle" id="Image1" /></a>
							<!--
							<b class="login_help"><img
									src="${path}/images/login_help.png" align="absmiddle" /><a
								href="#">忘记密码</a> </b>-->
						</li>
					</ul>
				</div>
				</div>
				</form>
	</body>
</html>