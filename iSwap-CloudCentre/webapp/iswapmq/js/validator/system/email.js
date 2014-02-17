	//表单验证
$(document).ready(function () {
	$.formValidator.initConfig({onError:function (msg) {
	}});
	
					$("#emailPassWord").formValidator({onShow:"请输入密码",onFocus:"至少1个长度",onCorrect:"密码合法"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请确认"});
					$("#tpass").formValidator({onShow:"再次输入密码",onFocus:"至少1个长度",onCorrect:"密码一致"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"emailPassWord",operateor:"=",onError:"2次密码不一致,请确认"});
					$("#emailAccount")
							.formValidator( {
								empty : true,
								onShow : "请输入邮箱地址",
								onFocus : "最多100个字符",
								onCorrect : "通过 ",
								defaultValue : ""
							})
							.inputValidator( {
								empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
								min:1,
								max : 100,
								onError : "输入为空或者已经超过100个字符"
							})
							.regexValidator(
									{
										regExp : "^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",
										onError : "您输入的邮箱格式不正确"
									}).ajaxValidator( {
								type : 'post',
								url : "email!checkEmail.action",
								datatype : "html",
								data : "id=" + escape($("#id").val()),
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
								onError : "邮箱已存在 请更换邮箱!",
								onwait : "正在对邮箱进行校验......"
							}).defaultPassed();
					$("#emailAddress").formValidator( {
						onShow : "请输入邮箱地址，必填选项",
						onFocus : "此项为必填选项!",
						onCorrect : "通过"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						onError : "请输入邮箱地址!"
					});
					$("#emailServerAddress").formValidator( {
						onShow : "请输入服务器邮件地址(smtp)，必填选项",
						onFocus : "必填选项 最长长度为50个字符",
						onCorrect : "可以注册"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 50,
						onError : "输入非法,请确认"
					});
				});
// 验证用户是否通过
function isSub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
}