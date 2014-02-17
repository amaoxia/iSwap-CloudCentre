$(document).ready(function(){
$.formValidator.initConfig({
onError:
function(msg){
	},
	inIframe:true
	});
					$("#filedName").formValidator( {
						onShow : "请输入字段中文名,必填选项!",
						onFocus : "请输入字段中文名！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#filedcode").formValidator( {
						onShow : "请输入字段代码,必填选项!",
						onFocus : "请输入字段代码！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 20,
						onError : "输入为空或者已经超过30个字符！"
					});
						$("#filedLength").formValidator( {
						onShow : "请输入字段长度!",
						onFocus : "请输入字段长度!"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#dataType").formValidator( {
						onShow : "请输入数据类型!",
						onFocus : "请输入数据类型!"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
				});
   function  isSub(){
   var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
   }				