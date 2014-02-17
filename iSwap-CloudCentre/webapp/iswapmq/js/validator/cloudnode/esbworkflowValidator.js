$(document)
.ready(
	function() {
		$.formValidator.initConfig( {
			onError : function(msg) {
				alert(msg);
			}
		});
		$("#workFlowName").formValidator( {
			onShow : "请输入流程名称,必填选项",
			onFocus : "请输入流程名称！"
		}).inputValidator( {
			min : 1,
			max : 80,
			onerror : "输入为空或者已经超过80个字符！"
		});
		$("#workFlowCode").formValidator( {
			onShow : "请输入流程英文名称,必填选项",
			onFocus : "请输入流程英文名称！"
		}).inputValidator( {
			min : 1,
			max : 60,
			onError : "输入为空或者已经超过60个字符！"
		}).regexValidator( {
			regExp : "username",
			dataType : "enum",
			onError : "您输入非法,请输入英文名称"
		}).ajaxValidator({
			type : 'post',
			data : "id=" + escape($("#id").val()),
			url : "esbworkflowAction!checkCode.action",
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
		$("#appMsgId").formValidator( {
			onShow : "请选择所属应用,必填选项",
			onFocus : "请选择所属应用！"
		}).inputValidator( {
			min : 1,
			onError : "请选择所属应用！"
		});
	});
