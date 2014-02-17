$(document).ready(function() {
	$.formValidator.initConfig({
		onError : function(msg) {
			alert(msg);
		}
	});
	$("#wsName").formValidator({
		onShow : "请输入服务名称，必填",
		onFocus : "输入服务名称",	
		onCorrect : "该服务名称可以注册"
	}).inputValidator({
		min : 1,
		max : 255,
		onError : "服务名称输入有误,请重新输入"
	}).ajaxValidator({
		type : 'post',
		data : "id=" + escape($("#id").val()),
		url : "webInfoAction!checkName.action",
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
	});
	$("#ipAddress").formValidator({
		onEmpty : true,
		onShow : "请输ip地址",
		onFocus : "格式例如：192.168.1.1",
		onCorrect : "通过"
	}).inputValidator({
		min : 1,
		max : 200,
		onError : "ip地址输入错误，请重新输入"
	});
	$("#deptId").formValidator({
		onShow : "请选择所属部门",
		onFocus : "此项为选择 必填!",
		onCorrect : "通过"
	}).inputValidator({
		min : 1,
		onError : "请选择队列所属部门"
	});
	$("#userName").formValidator({
		onEmpty : true,
		onShow : "请输入用户名",
		onFocus : "此项为选择 必填!",
		onCorrect : "通过"
	}).inputValidator({
		min : 1,
		max : 200,
		onError : "用户名输入错误，请重新输入"
	});
	$("#passWord").formValidator({
		onEmpty : true,
		onShow : "请输入密码",
		onFocus : "此项为选择 必填!",
		onCorrect : "通过"
	}).inputValidator({
		min : 1,
		max : 32,
		onError : "密码输入错误，请重新输入"
	});
	$("#notes").formValidator({
		onShow : "请输入服务描述",
		onFocus : "描述不超过200个字符",
		onCorrect : "通过"
	}).inputValidator({
		max : 200,
		onError : "服务描述输入已经超过200个字符，请重新输入"
	});

});

// 验证用户是否通过
function isSub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
}