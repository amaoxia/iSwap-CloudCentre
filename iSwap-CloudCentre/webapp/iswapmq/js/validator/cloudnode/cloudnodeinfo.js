$(document)
.ready(
	function() {
		$.formValidator.initConfig( {
			onError : function(msg) {
			}
		});
		$("#nodesName").formValidator( {
			onShow : "请输入前置机名称,必填选项",
			onFocus : "请输入前置机名称！"
		}).inputValidator( {
			empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
			min : 1,
			max : 30,
			onError : "输入为空或者已经超过30个字符！"
		}).ajaxValidator({
			type : 'post',
			data : "id=" + escape($("#id").val()),
			url : "cloudNodeInfo!checkNodesName.action",
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
			onError : "前置机名称已存在",
			onWait : "正在对前置机名称进行校验......"
		});
		$("#address").formValidator({empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},onShow:"请输入ip",onfocus:"例如：172.16.201.18",onCorrect:"谢谢你的合作，你的ip正确"}).regexValidator({regExp:"ip4",dataType:"enum",onError:"ip格式不正确"}).ajaxValidator({
			type : 'post',
			data : "id=" + escape($("#id").val()),
			url : "cloudNodeInfo!checkAddress.action",
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
			onError : "ip地址已存在",
			onWait : "正在对ip地址进行校验......"
		});
		
		$("#port").formValidator({empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},onShow:"请输入访问端口 例如 ：8080",onCorrect:"通过"}).regexValidator({regExp:"intege",dataType:"enum",onError:"端口格式不正确"});
		
		$("#deptNames").formValidator( {
			onShow : "请输入所属部门,必填选项",
			onFocus : "请输入所属部门！"
		}).inputValidator( {
			empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
			min : 1,
			onError : "请输入所属部门！"
		});
		$("#appMsgIds").formValidator( {
			onShow : "请选择所属应用,必填选项",
			onFocus : "请选择所属应用！"
		}).inputValidator( {
			empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
			min : 1,
			onError : "请选择所属应用！"
		});
		$("#remark").formValidator( {
			onShow : "请输入前置机描述信息",
			onFocus : "请输入前置机描述信息！"
		}).inputValidator( {
			empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
			max:200,
			onError : "已超过200字！"
		});
	});
//验证用户是否通过
function isSub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
}