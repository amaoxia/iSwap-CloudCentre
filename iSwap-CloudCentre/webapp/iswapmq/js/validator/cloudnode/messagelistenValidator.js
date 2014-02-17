$(document)
		.ready(
				function() {
					$.formValidator.initConfig( {
						onError : function(msg) {
						}
					});
					$("#listenName").formValidator( {
						onShow : "请输入监听名称",
						onFocus : "请输入监听名称！"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#jmsServerInfo").formValidator( {
						onShow : "请选择服务名称",
						onFocus : "请选择服务名称"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "请选择服务名称！"
					});
					$("#queueName").formValidator( {
						onShow : "请输入队列名称",
						onFocus : "请输入队列名称"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "请输入队列名称!"
					});
					$("#times").formValidator( {
						onShow : "请输入调度频率!",
						onFocus : "请输入调度频率!"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#notes").formValidator( {
						onEmpty : true,
						onShow : "请输入描述",
						onFocus : "描述不超过200个字符",
						onCorrect : "通过"
					}).inputValidator( {
					    empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						max : 200,
						onError : "输入为空或者已经超过200个字符"
					});
				});

   function clo(){
   var dg = frameElement.lhgDG;
    dg.cancel();
    dg.curWin.location.reload(); 
   }
//验证用户是否通过
function isSub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
}


