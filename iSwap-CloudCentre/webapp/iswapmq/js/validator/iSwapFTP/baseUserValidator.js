$(document)
		.ready(
				function() {
					$.formValidator.initConfig( {
						onError : function(msg) {
							alert(msg);
						}
					});
					$("#username").formValidator( {
						onShow : "请输入用户名称，必填选项",
						onFocus : "必填选项 最长长度为30个字符",
						onCorrect : "通过"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "输入非法,请确认"
					});
					$("#userid").formValidator( {
						onShow : "请输入登陆名称，必填选项",
						onFocus : "必填选项 最长长度为30个字符",
						onCorrect : "通过"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "输入非法,请确认"
					});
					$("#password").formValidator( {
						onShow : "请输入密码，必填选项",
						onFocus : "密码不能为空",
						onCorrect : "密码合法"
					}).inputValidator( {
						min : 1,
						onError : "密码不能为空,请确认"
					});
					$("#homedirectory").formValidator( {
						onShow : "点击左边图标选择目录，必填选项",
						onFocus : "主目录不能为空",
						onCorrect : "正确"
					}).inputValidator( {
						min : 1,
						onError : "主目录不能为空,请确认"
					});
					});
