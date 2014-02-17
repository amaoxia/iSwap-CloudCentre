$(document).ready(function() {
	$.formValidator.initConfig({
		onError : function(msg) {
			alert(msg);
		}
	});
	$("#jmsServerName").formValidator({
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
		url : "jmsInfoAction!checkName.action",
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
		onwait : "正在对服务名称进行校验......"
	});
	$("#jmsFactory").formValidator({
		onempty : true,
		onShow : "请输下下文工厂",
		onFocus : "此项为选择 必填!",
		onCorrect : "通过"
	}).inputValidator({
		min : 1,
		max : 200,
		onError : "ip地址输入错误，请重新输入"
	});
	
	$("#url").formValidator({
		onShow : "请输入连接地址",
		onFocus : "此项为选择 必填!",
		onCorrect : "通过"
	}).inputValidator({
		min : 1,
		max : 200,
		onError : "请输入连接地址，请重新输入"
	});
	$("#queueName").formValidator({
		onShow : "请输入队列名称",
		onFocus : "此项为选择 必填!",
		onCorrect : "通过"
	}).inputValidator({
		min : 1,
		max : 30,
		onError : "输入为空或操作30字符"
	});
	
	
	$("#id").formValidator({
		onShow : "请选择所属部门，必填选项",
		onFocus : "此项为选择 必填!",
		onCorrect : "通过"
	}).inputValidator({
		min : 1,
		onError : "请选择队列所属部门"
	});
	$("#conntFactory").formValidator({
		onShow : "请输入连接工厂",
		onFocus : "此项为选择 必填!",
		onCorrect : "通过"
	}).inputValidator({
		min : 1,
		max : 200,
		onError : "连接工厂输入有误，请重新输入"
	});
	$("#outTime").formValidator({
		onShow : "请输入超时时长",
		onFocus : "此项为选择 必填!",
		onCorrect : "通过"
	}).regexValidator({
		regExp : "^[0-9]+$",
		onError : "超时时长输入错误，请输入正整数"
	}).inputValidator({
		min : 1,
		max : 200,
		onError : "超时时长输入错误，请输入正整数"
	});
	$("#userName").formValidator({
		onShow : "请输入用户名",
		onFocus : "此项为选择 必填!",
		onCorrect : "通过"
	}).inputValidator({
		min : 1,
		max : 200,
		onError : "用户名输入错误，请重新输入"
	});
	$("#passWord").formValidator({
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

//验证用户是否通过
function isSub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
}
