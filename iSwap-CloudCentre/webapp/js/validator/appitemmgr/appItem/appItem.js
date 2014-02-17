var pathName = window.document.location.pathname;
var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
$(document)
		.ready(
				function() {
					$.formValidator.initConfig({
						onError : function(msg) {
						},
						inIframe : true
					});
					$("#appItemName").formValidator({
						onShow : "请输入指标名称,必填选项!",
						onFocus : "请输入指标名称！"
					}).inputValidator({
						empty : {
							leftEmpty : false,
							rightEmpty : false,
							emptyError : "不能输入空格!"
						},
						min : 1,
						max : 60,
						onError : "输入为空或者已经超过60个字符！"
					}).ajaxValidator({
						type : 'post',
						url : projectName+"/appitemmgr/appItem/appItem!checkAppItemName.action",
						dataType : "html",
						beforesend : function(){
						},
						success : function(data) {
							if (data == "succ") {
								return true;
							} else {
								return false;
							}
						},
						buttons : $("#button"),
						error : function() {
							alert("服务器忙，请重试");
						},
						onError : "指标名称已存在",
						onWait : "正在对指标名称进行校验......"
					})
				});

function isSub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();//提交
	}
}