$(document)
		.ready(
				function() {
					$.formValidator.initConfig( {
						onError : function(msg) {
						}
					});
					$("#sourceName").formValidator( {
						onShow : "请输入数据源名称,必填选项",
						onFocus : "请输入数据源名称！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#sourceCode").formValidator( {
						onShow : "请输入数据源编码,必填选项",
						onFocus : "请输入数据源名称！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 20,
						onError : "输入为空或者已经超过20个字符！"
					});
					$("#type").formValidator( {
						onShow : "请选择数据库类型",
						onFocus : "请选择数据库类型"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "数据库类型必选！"
					});
					$("#cloudNodeInfo").formValidator( {
						onShow : "请选择前置机",
						onFocus : "请选择前置机"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "所属前置机必选！"
					});
					$("#ip").formValidator({onShow:"请输入ip",onfocus:"例如：172.16.201.18",onCorrect:"谢谢你的合作，你的ip正确"}).regexValidator({regExp:"ip4",dataType:"enum",onError:"ip格式不正确"}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 20,
						onError : "请输入IP地址！"
					});
					$("#dbName").formValidator({
							onShow:"请输入数据库名称",
							onfocus:"请输入数据库名称",
							onCorrect:"通过"
							}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 20,
						onError : "请输入数据库名称！"
					});
//					
//					.ajaxValidator({
//						type : 'post',
//						data : "id=" + $("#id").val()+"&ip=" + escape($("#ip").val())+"&port=" + escape($("#port").val()),
//						url : "datasource!checkDataSource.action",
//						dataType : "html",
//						success : function(data) {
//							if (data == "succ") {
//								return true;
//							} else {
//								return false;
//							}
//						},
//						buttons : $("#button"),
//						error : function() {
//							alert("服务器忙，请重试");
//						},
//						onError : "数据源已经注册,请更换数据源!",
//						onWait : "正在对数据源进行校验......"
//					});
					$("#port").formValidator({onShow:"请输入访问端口 例如 ：1521",onCorrect:"通过"}).regexValidator({regExp:"intege",dataType:"enum",onError:"端口格式不正确"}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 20,
						onError : "请输入端口号！"
					});
					$("#sysDept").formValidator( {
						onShow : "请选择部门",
						onFocus : "请选择部门！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 20,
						onError : "部门为必选项！"
					});
					$("#userName").formValidator( {
						onShow : "请输入用户名,必填选项!",
						onFocus : "请输入用户名!"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#passWord").formValidator( {
						onShow : "请输入密码!",
						onFocus : "请输入密码!"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min :1,
						max : 30,
						onError : "输入已经超过30个字符！"
					});
					$("#defaultConnt").formValidator( {
						onShow : "请输入初始化连接数!",
						onFocus : "请输入初始化连接数!"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min:1,
						max : 30,
						type:"value",
						onError:"最大连接数为1~30的数字，请确认"
					});
					$("#maxConnt").formValidator( {
						onShow : "请输入最大连接数!",
						onFocus : "请输入最大连接数!"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min:1,
						max : 30,
						type:"value",
						onError:"最大连接数为1~30的数字，请确认"
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


