$(document).ready(function(){
$.formValidator.initConfig({
onError:
function(msg){
	inIframe:true
	}});
		$("#name").formValidator( {
						onShow : "请输入角色名称,必填选项",
						onFocus : "请输入角色名称！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					
					$("#code").formValidator( {
						onShow : "请输入角色代码,必填选项",
						onFocus : "请输入角色代码！",
						onCorrect : "该用登录可以注册"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					}).ajaxValidator({
						type : 'get',
						data : "id=" + escape($("#id").val()),
						url : "role!checkCode.action",
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
						onError : "角色编码已存在",
						onWait : "正在对昵称进行校验......"
					}).defaultPassed();
					
						$("#descript").formValidator( {
						onShow : "请输入角色描述!",
						onFocus : "请输入角色描述!"
					}).inputValidator( {
					  empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						max : 200,
						onError : "输入为空或者已经超过200个字符！"
					});
				});

   function  isSub(){
   var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
   }				