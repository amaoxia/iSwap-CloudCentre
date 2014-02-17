$(document)
		.ready(
				function() {
					$.formValidator.initConfig({
						onError : function(msg) {
						},
						inIframe : true
					});
					$("#targetName").formValidator({
						onShow : "请输入指标名称,必填选项!",
						onFocus : "请输入指标名称！"
					}).inputValidator({
						empty : {
							leftEmpty : false,
							rightEmpty : false,
							emptyError : "不能输入空格!"
						},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#itemName").formValidator({
						onShow : "请输入指标名称,必填选项!",
						onFocus : "请输入指标名称！"
					}).inputValidator({
						empty : {
							leftEmpty : false,
							rightEmpty : false,
							emptyError : "不能输入空格!"
						},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#dataType").formValidator({
						onShow : "请选择数据类型!",
						onFocus : "请选择数据类型!"
					}).inputValidator({
						empty : {
							leftEmpty : false,
							rightEmpty : false,
							emptyError : "不能输入空格!"
						},
						min : 1,
						max : 30,
						onError : "输入为空!"
					});
					$("#metaDataBasicTypeId").formValidator({
						onShow : "请选择基础库类型!",
						onFocus : "请选择基础库类型!"
					}).inputValidator({
						empty : {
							leftEmpty : false,
							rightEmpty : false,
							emptyError : "不能输入空格!"
						},
						min : 1,
						onError : "请选择库类型!"
					});
					
					$("#itemCode").formValidator({
						onShow : "请输入指标编码,必填选项!",
						onFocus : "请输入指标编码！"
					}).inputValidator({
						empty : {
							leftEmpty : false,
							rightEmpty : false,
							emptyError : "不能输入空格!"
						},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					}).ajaxValidator({
						type : 'post',
						data : "id=" + escape($("#id").val()),
						url : "item!checkTemplateCode.action",
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
						onError : "指标编码已存在",
						onWait : "正在对指标编码进行校验......"
					});
					$("#deptName").formValidator({
						onShow : "请选择部门,必填选项!",
						onFocus : "请选择部门！"
					}).inputValidator({
						empty : {
							leftEmpty : false,
							rightEmpty : false,
							emptyError : "不能输入空格!"
						},
						min : 1,
						max : 20,
						onError :"输入为空或者已经超过30个字符！"
					});
					$("#dataSourceName").formValidator({
						onShow : "请选择数据源!",
						onFocus : "请选择数据源!"
					}).inputValidator({
						empty : {
							leftEmpty : false,
							rightEmpty : false,
							emptyError : "不能输入空格!"
						},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#appMgName").formValidator({
						onShow : "请输入应用服务!",
						onFocus : "请输入应用服务!"
					}).inputValidator({
						empty : {
							leftEmpty : false,
							rightEmpty : false,
							emptyError : "不能输入空格!"
						},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#tableName").formValidator({
						onShow : "请输入表名!",
						onFocus : "请输入表名!"
					}).inputValidator({
						empty : {
							leftEmpty : false,
							rightEmpty : false,
							emptyError : "不能输入空格!"
						},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#overPeople").formValidator({
						onShow : "请输入指标接收人姓名!",
						onFocus : "请输入指标接收人姓名!"
					}).inputValidator({
						empty : {
							leftEmpty : false,
							rightEmpty : false,
							emptyError : "不能输入空格!"
						},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#overPeopleEmail")
							.formValidator({
								empty : {
									leftEmpty : false,
									rightEmpty : false,
									emptyError : "不能输入空格!"
								},
								onShow : "请输入接收人邮箱地址",
								onFocus : "最多100个字符",
								onCorrect : "通过 ",
								defaultValue : ""
							})
							.inputValidator({
								empty : false,
								max : 100,
								onError : "输入为空或者已经超过100个字符"
							})
							.regexValidator(
									{
										regExp : "^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",
										onError : "您输入的邮箱格式不正确"
									}).ajaxValidator({
								type : 'post',
								url : "metadata!checkEmail.action",
								datatype : "html",
								data : "id=" + escape($("#id").val()),
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
								onError : "邮箱已存在 请更换邮箱!",
								onWait : "正在对邮箱进行校验......"
							}).defaultPassed();
				});

function isSub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	var rule = $("input[type=radio][name=cycle.useDefaultRule]").val();
	if (fa && validator()) {
		if (rule == '0') {// 非系统规则
			if (greenValidator() && yellowValidator() && redValidator()) {
				document.forms[0].submit();
			} else {
				return;
			}
		}
		document.forms[0].submit();//提交
	}
}

function validator() {
	var dataStructure = $("#dataStructure").val();
	var dataValueId = $("#dataValueId").val();
	var dataSourceId = $("#dataSourceId").val();
	var dataType = $("#dataType").val();
	var tableNameId = $("#tableNameId").val();
	if (dataType != '') {
		if (dataType == '0') {
			if (dataStructure == '') {
				alert("请选择文档结构类型!");
				return false;
			}
			if (dataValueId == '') {
				alert("请选择具体文件类型!");
				return false;
			}
		}
		if (dataType == '1') {
			if (dataSourceId == '') {
				alert("请选择数据源!");
				return false;
			}
			if (tableNameId == '') {
				alert("请选择表名称!");
				return false;
			}
		}
	}
	return true;
}
