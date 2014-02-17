$(document).ready(function() {
	$.formValidator.initConfig({
		onError : function(msg) {
		}
	});
	$("#queueName").formValidator({
		onShow : "请输入服务名称，必填选项",
		onFocus : "输入服务名称",
		onCorrect : "该服务名称可以注册"
	}).inputValidator({
		min : 1,
		max : 255,
		onError : "服务名称输入有误,请重新输入"
	}).ajaxValidator({
		type : 'post',
		data : "id=" + escape($("#id").val()),
		url : "queueInfo!checkQueueName.action",
		dataType : "html",
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
		onError : "服务名称已存在",
		onWait : "正在对服务名称进行校验......"
	}).defaultPassed();
	$("#description").formValidator({
		onEmpty : true,
		onShow : "请输入服务描述",
		onFocus : "描述不超过200个字符",
		onCorrect : "通过"
	}).inputValidator({
		max : 200,
		onError : "服务描述输入已经超过200个字符，请重新输入"
	});
	$("#queueDepth").formValidator({
		onShow : "请输入队列深度",
		onFocus : "格式例如：1000",
		onCorrect : "通过"
	}).regexValidator({
		regExp : "^[0-9]+$",
		onError : "队列深度输入有误，请输入正整数"
	});
	$("#queueSize").formValidator({
		onShow : "请输入队列大小",
		onFocus : "格式例如：2000",
		onCorrect : "通过"
	}).regexValidator({
		regExp : "^[0-9]+$",
		onError : "队列大小输入有误 ，请输入正整数"
	});
	$("#id").formValidator({
		onShow : "请选择所属部门，必填选项",
		onFocus : "此项为选择 必填!",
		onCorrect : "通过"
	}).inputValidator({
		min : 1,
		onError : "请选择队列所属部门"
	});
	$("#serType").formValidator({
		onShow : "请选择队列类型，必填选项",
		onFocus : "此项为选择 必填!",
		onCorrect : "通过"
	}).inputValidator({
		min : 1,
		onError : "请选择队列类型"
	});
});

// 验证用户是否通过
function isSub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
}