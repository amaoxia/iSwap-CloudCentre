//表单验证
$(document).ready(function () {
	$.formValidator.initConfig({onError:function (msg) {
	}});
	$("input:text[@name='deptName']").formValidator({onShow:"请输入机构名称，必填选项", onFocus:"\u90e8\u95e8\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a", onCorrect:"\u901a\u8fc7"}).regexValidator({regExp:"chinese",dataType:"enum",onError:"请输入部门中文名称"}).inputValidator({min:1, max:30,empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},onError:"输入为空或者已经超过30个字符"});
	$("#shortName").formValidator({onShow:"请输入机构简称，必填选项", onFocus:"\u90e8\u95e8\u7b80\u79f0\u4e0d\u80fd\u4e3a\u7a7a", onCorrect:"\u901a\u8fc7"}).regexValidator({regExp:"chinese",dataType:"enum",onError:"请输入部门中文名称"}).inputValidator({min:1,max:20, empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},onError:"输入为空或者已经超过20个字符"});
	$("#orgCode").formValidator({onShow:"请输入机构代码，必填选项", onFocus:"\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a", onCorrect:"\u901a\u8fc7"}).inputValidator({min:1, max:20, empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},onError:"输入为空或者已经超过20个字符"});
	$("#deptUid").formValidator({onShow:"请输入机构标识，必填选项", onFocus:"\u6807\u8bc6\u7b26\u4e0d\u80fd\u4e3a\u7a7a", onCorrect:"\u901a\u8fc7"})
	.inputValidator({min:1,max:20, empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},onError:"输入为空或者已经超过20个字符"}).ajaxValidator({
						type : 'get',
						async : true,
						data : "id=" + escape($("#id").val()),
						processData : false,
						url : "dept!checkDeptUid.action",
						dataType  : "html",
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
						onError : "机构标识已存在",
						onWait : "正在对昵称进行校验......"
					}).defaultPassed();
	$("#address").formValidator({empty:true, onShow:"\u8bf7\u8f93\u5165\u90e8\u95e8\u5730\u5740", onFocus:" \u4e0d\u8d85\u8fc7200\u5b57", onCorrect:"\u901a\u8fc7", onEmpty:"\u786e\u5b9a\u4e0d\u7559\u4e0b\u5730\u5740\u5417"}).inputValidator({max:200, empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},onError:"\u8bf7\u8f93\u5165\u90e8\u95e8\u63cf\u8ff0\u6700\u5927200\u5b57"});
	$("#linkman").formValidator({onShow:"\u8bf7\u8f93\u5165\u8054\u7cfb\u4eba\u540d\u79f0", onFocus:"\u8054\u7cfb\u4eba\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a", onCorrect:"\u901a\u8fc7"}).inputValidator({max:20, empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},onError:"\u8bf7\u6b63\u786e\u8f93\u5165\u8054\u7cfb\u4eba\u540d\u79f0"});
	$("#phone").formValidator({empty:true,onShow:"请输入你的联系电话",onFocus:"格式例如：0577-88888888",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留联系电话了吗？"}).regexValidator({regExp:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",onError:"你输入的联系电话格式不正确"});						$("#descript").formValidator({empty:true, onShow:"\u8bf7\u8f93\u5165\u90e8\u95e8\u63cf\u8ff0", onFocus:"\u4e0d\u8d85\u8fc7200\u5b57", onCorrect:"\u901a\u8fc7", onEmpty:"\u4e0d\u7559\u90e8\u95e8\u63cf\u8ff0\u5417?"}).inputValidator({max:200,empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"}, onError:"\u8bf7\u8f93\u5165\u90e8\u95e8\u63cf\u8ff0\u6700\u5927200\u5b57"});
    $("#upload").formValidator({empty:true,onShow:"\u8bf7\u4e0a\u4f20\u673a\u6784LOGO", onCorrect:"\u901a\u8fc7", onEmpty:"\u4e0d\u7559\u90e8\u95e8\u63cf\u8ff0\u5417?"}).inputValidator({empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"}, onError:"\u8bf7\u4e0a\u4f20\u90e8\u95e8\u56fe\u7247"});
});


