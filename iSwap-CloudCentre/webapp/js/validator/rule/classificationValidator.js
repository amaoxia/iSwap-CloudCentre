$(document)
		.ready(
				function() {
					$.formValidator.initConfig( {
						onError : function(msg) {
						}
					});
					$("#name").formValidator( {
						onShow : "请输入中文名称",
						onFocus : "请输入中文名称！"
					}).inputValidator( {
						min : 1,
						max : 90,
						onError : "输入为空或者已经超过90个字符！"
					});
					$("#code").formValidator( {
						onShow : "请输入英文名称",
						onFocus : "请输入英文名称"
					}).inputValidator( {
						min : 1,
						max : 90,
						onError : "输入为空或者已经超过90个字符！"
					});
					$("#rule").formValidator( {
						onShow : "请输入规则表达式",
						onFocus : "请输入规则表达式"
					}).inputValidator( {
						min : 1,
						max : 700,
						onError : "请输入规则表达式！"
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


