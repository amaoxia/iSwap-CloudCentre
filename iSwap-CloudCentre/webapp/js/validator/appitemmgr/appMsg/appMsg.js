$(document)
.ready(
	function() {
		$.formValidator.initConfig( {
			onError : function(msg) {
			}
		}); 
		$("#appName").formValidator( {
			onShow : "请输入应用名称,必填选项",
			onFocus : "请输入应用名称！"
		}).inputValidator( {
			empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
			min : 1,
			max : 30,
			onError : "输入为空或者已经超过30个字符！"
		});
		$("#appCode").formValidator( {
			onShow : "请输入应用标识,必填选项",
			onFocus : "请输入应用标识！"
		}).inputValidator( {
			empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
			min : 1,
			max : 30,
			onerror :"输入为空或者已经超30个字符！"
		}).ajaxValidator({
			type : 'get',
			data : "id=" + escape($("#id").val()),
			url : "appMsg!checkCode.action",
			datatype : "html",
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
			onError : "应用标识符已存在",
			onWait : "正在对昵称进行校验......"
		}).defaultPassed();;
	});

//验证用户是否通过
function isSub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
}