$(document)
		.ready(
				function() {
					$.formValidator.initConfig( {
						onError : function(msg) {
						}
					});
					$("#mapName").formValidator( {
						onShow : "请输入Mapper中文名称",
						onFocus : "请输入Mapper中文名称！"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#mapCode").formValidator( {
						onShow : "请输入Mapper英文名称",
						onFocus : "请输入Mapper英文名称"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#cloudNodeInfo").formValidator( {
						onShow : "请选择所属前置机",
						onFocus : "请选择所属前置机"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "请选择所属前置机！"
					});
					$("#appMsg").formValidator( {
						onShow : "请选择所属应用",
						onFocus : "请选择所属应用"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "请选择所属应用！"
					});
					$("#sysDept").formValidator( {
						onShow : "请选择所属部门",
						onFocus : "请选择所属部门"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "请选择所属部门！"
					});
					$("#fileupload").formValidator( {
						onShow : "请选择Mapper文件",
						onFocus : "请选择Mapper文件"
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


