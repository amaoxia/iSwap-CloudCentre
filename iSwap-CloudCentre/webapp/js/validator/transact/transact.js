$(document).ready(function(){
$.formValidator.initConfig({
onError:
function(msg){
	inIframe:true
	alert(msg);
	}});
					$("#title").formValidator( {
						onShow : "请输入标题名称,必填选项",
						onFocus : "请输入标题名称！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 40,
						onError : "输入为空或者已经超过40个字符！"
					});
					
					$("#content").formValidator( {
						onEmpty : true,
						onShow : "请催办内容,200字以内",
						onFocus : "请输入催办内容！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						max : 200,
						onError : "输入为空或者已经超过200个字符！"
					});
					$("#transactType").formValidator( {
						onShow : "请选择催办方式,必填选项",
						onFocus : "请选择催办方式！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						onError : "请选择催办方式！"
					});
				});