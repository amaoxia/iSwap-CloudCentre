$(document).ready(function(){
$.formValidator.initConfig({
onError:
function(msg){
	},
	inIframe:true
	});
					$("#basicTypeName").formValidator( {
						onShow : "请输入基础库类型名称,必填选项!",
						onFocus : "请输入基础库类型名称！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#basicTypeCode").formValidator( {
						onShow : "请输入基础库类型编码,必填选项!",
						onFocus : "请输入基础库类型编码！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 20,
						onError : "输入为空或者已经超过30个字符！"
					}).	ajaxValidator({
						type : 'post',
						data : "id=" + escape($("#id").val()),
						url : "basicType!checkBasicTypeCode.action",
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
						onError : "基础库类型编码已存在",
						onWait : "正在对基础库类型编码进行校验......"
					}).defaultPassed();
					
						$("#descript").formValidator( {
						onShow : "请输入基础库类型描述!",
						onFocus : "请输入基础库类型描述!"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						max : 250,
						onError : "输入为空或者已经超过250个字符！"
					});
				});
   function  isSub(){
   var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
   }				