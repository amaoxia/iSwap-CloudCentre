$(document)
		.ready(
				function() {
					$.formValidator.initConfig( {
						onError : function(msg) {
							alert(msg);
						}
					});
					$("#fileName").formValidator( {
						onShow : "请输入规则文件名称，必填选项",
						onFocus : "不超过25个汉字，或不超过50个字节（字母，数字和下划线）",
						onCorrect : "通过"
					}).inputValidator( {
						min : 1,
						max : 50,
						onError : "输入非法,请确认"
					});
					$("#ruleName").formValidator( {
						onShow : "请输入规则名称，必填选项",
						onFocus : "不超过25个汉字，或不超过50个字节（字母，数字和下划线）",
						onCorrect : "通过"
					}).inputValidator( {
						min : 1,
						max : 50,
						onError : "输入非法,请确认"
					});
					});
