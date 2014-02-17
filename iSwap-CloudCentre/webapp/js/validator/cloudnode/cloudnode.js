
   $(document).ready(function(){
$.formValidator.initConfig({
onError:
function(msg){
	},
	inIframe:true
	});
					$("#nodesName").formValidator( {
						onShow : "请输入前置机名称,必填选项!",
						onFocus : "请输入前置机名称！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 90,
						onError : "输入为空或者已经超过90个字符！"
					});
					$("#serverAddress").formValidator( {
						onShow:"请输入ip",onfocus:"例如：172.16.201.18",onCorrect:"谢谢你的合作，你的ip正确"}).regexValidator({regExp:"ip4",dataType:"enum",onError:"ip格式不正确"})
					.inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 20,
						onError : "输入为空或者已经超过30个字符！"
					});
					
						$("#storageCount").formValidator( {
						onShow : "请输入总存储量!",
						onFocus : "请输入总存储量!"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "格式不正确!"
					}).regexValidator({regExp:"decmal1",dataType:"enum",onError:"参数输入有错误!"});
					$("#remark").formValidator( {
						onShow : "请输入前置机描述!",
						onFocus : "请输入前置机描述!"
					}).inputValidator( {
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