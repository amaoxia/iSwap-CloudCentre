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
						max : 300,
						onError : "输入为空或者已经超过300个字符！"
					});
					$("#sourceCode").formValidator( {
						onShow : "请输入数据源编码,必填选项",
						onFocus : "请输入数据源名称！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 50,
						onError : "输入为空或者已经超过50个字符！"
					}).functionValidator({fun:isSystem});
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
					$("#port").formValidator({onShow:"请输入访问端口 例如 ：27017",onCorrect:"通过"}).regexValidator({regExp:"intege",dataType:"enum",onError:"端口格式不正确"}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 20,
						onError : "请输入端口号！"
					});
					
					$("#userName").formValidator( {
						onShow : "请输入用户名!",
						onFocus : "请输入用户名!"
					}).inputValidator( {
						//empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 0,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#passWord").formValidator( {
						onShow : "请输入密码!",
						onFocus : "请输入密码!"
					}).inputValidator( {
						//empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min :0,
						max : 30,
						onError : "输入已经超过30个字符！"
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
function isSystem(value){
	if(value=='SYSTEM'){
		return "该编码已被系统占用，请选用其它编码！";
	}
	return true;
}

