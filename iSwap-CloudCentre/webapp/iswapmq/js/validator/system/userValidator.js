	//表单验证
$(document).ready(function () {
	$.formValidator.initConfig({onError:function (msg) {
	}});
	
					$("#loginName").formValidator( {
						onShow : "请输入登录名，必填选项",
						onFocus : "输入登录名",
						onCorrect : "该用登录可以注册"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min:1,
						max : 20,
						onError : "您输入为空或者已超过20字符"
			}).
					regexValidator( {
						regExp : "username",
						dataType : "enum",
						onError : "您输入非法,请输入登录名称"
					}).
			ajaxValidator({
						type : 'post',
						data : "id=" + escape($("#id").val()),
						url : "user!checkName.action",
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
						onError : "登录名称已存在",
						onWait : "正在对昵称进行校验......"
					});
					$("#password").formValidator({onShow:"请输入密码",onFocus:"至少1个长度",onCorrect:"密码合法"}).inputValidator({min:1,max : 20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请确认"});
					$("#password2").formValidator({onShow:"再次输入密码",onFocus:"至少1个长度",onCorrect:"密码一致"}).inputValidator({min:1,max : 20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"password",operateor:"=",onError:"2次密码不一致,请确认"});
 					$("#idCardCode").formValidator( {
				    onShow : "请输入15或18位的身份证",
 					onFocus : "输入15或18位的身份证",
 					onCorrect : "通过"
 					}).functionValidator( {
 					fun : isCardID
 					});
					$("#email")
							.formValidator( {
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
								url : "user!checkEmail.action",
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
							});
					$("#mobile").formValidator({onShow:"请输入你的手机号码",onFocus:"你要是输入了，必须输入正确",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留手机号码啊？"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"你输入的手机号码格式不正确"});
					$("#phone").formValidator({empty:true,onShow:"请输入你的联系电话",onFocus:"格式例如：0577-88888888",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留联系电话了吗？"}).regexValidator({regExp:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",onError:"你输入的联系电话格式不正确"});					
					$("#remark").formValidator( {
						onEmpty : true,
						onShow : "请输入用户描述",
						onFocus : "描述不超过200个字符",
						onCorrect : "通过"
					}).inputValidator( {
					    empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						max : 200,
						onError : "输入为空或者已经超过200个字符"
					});
					$("#deptName").formValidator( {
						onShow : "请选择所属部门，必填选项",
						onFocus : "此项为选择 必填!",
						onCorrect : "通过"
					}).inputValidator( {
						min : 1,
						onError : "请选择用户所属部门"
					});
					$("#address").formValidator( {
						onEmpty : true,
						onShow : "请输入联系地址",
						onFocus : "不超过200个字符",
						onCorrect : "通过",
						defaultValue : ""
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						max : 200,
						onError : "输入为空或者已经超过200个字符"
					});
					$("#userUid").formValidator({
						onShow : "请输入用户标识符，必填选项",
						onFocus : "必填选项 ",
						onCorrect : "该用户标识符可以注册"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min:1,
						max : 20,
						onError : "用户标识非法,请确认"
					}).ajaxValidator({
						type : 'post',
						data : "id=" + escape($("#id").val()),
						url : "user!checkUid.action",
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
						onError : "用户标识符已存在",
						onWait : "正在对昵称进行校验......"
					});
					$("#userName").formValidator( {
						onShow : "请输入用户名称，必填选项",
						onFocus : "必填选项 最长长度为30个字符",
						onCorrect : "可以注册"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入非法,请确认"
					});
					$("#roleId").formValidator( {
						onShow : "请选择用户角色，必填选项",
						onFocus : "角色必须选择",
						onCorrect : "通过"
					}).inputValidator( {
						min : 1,
						onError : "请选择角色 必填项!"
					})
				});
// 验证用户是否通过
function isSub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
}