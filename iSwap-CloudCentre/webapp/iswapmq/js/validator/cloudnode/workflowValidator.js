		$(document)
		.ready(
				function() {
					$.formValidator.initConfig( {
						onError : function(msg) {
						}
					});
					$("#workFlowName").formValidator({onShow:"请输入流程中文名称",onCorrect:"通过"}).inputValidator( {
						min : 1,
						max : 60,
						onError : "输入为空或者已经超过60个字符！"
					});
					$("#workFlowCode").formValidator({onShow:"请输入流程英文名称",onCorrect:"通过"}).inputValidator( {
						min : 1,
						max : 60,
						onError : "输入为空或者已经超过60个字符！"
					}).regexValidator( {
						regExp : "username",
						dataType : "enum",
						onError : "您输入非法,请输入登录名称"
					}).
						ajaxValidator({
						type : 'post',
						data : "id=" + escape($("#id").val()),
						url : "workflow!checkCode.action",
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
						onError : "流程英文名称已存在",
						onWait : "正在对流程英文名称进行校验......"
					});
					$("#appMsg").formValidator( {
						onShow : "请选择所属应用",
						onFocus : "请选择所属应用！"
					}).inputValidator( {
						min : 1,
						max : 20,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#cloudNodeInfoId").formValidator( {
						onShow : "请选择所属前置机！",
						onFocus : "请选择所属前置机！"
					}).inputValidator( {
						min : 1,
						max : 20,
						onError : "请选择前置机！"
					});
					$("#deptId").formValidator( {
						onShow : "请选择所属部门!",
						onFocus : "请选择所属部门!"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "请选择前置机！"
					});
					$("#itemId").formValidator( {
						onShow : "请选择指标!",
						onFocus : "请选择指标!"
					}).inputValidator( {
						min : 1,
						max : 30,
						onError : "请选择前置机！！"
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
		close();
	}
}