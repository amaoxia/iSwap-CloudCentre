$(document).ready(function(){
$.formValidator.initConfig({
onError:
function(msg){
	},
	inIframe:true
	});
					$("#serverName").formValidator( {
						onShow : "请输入中心服务的名称,必填选项!",
						onFocus : "请输入中心服务的名称！"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
						$("#address").formValidator({onShow:"请输入ip",onFocus:"例如：172.16.201.18",onCorrect:"谢谢你的合作，你的ip正确"}).regexValidator({regExp:"ip4",dataType:"enum",onError:"ip格式不正确"}).inputValidator( {
							empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
							min : 1,
							max : 30,
							onError : "输入为空或者已经超过30个字符！"
						});
						$("#port").formValidator({onShow:"请输入访问端口 例如 ：8080",onCorrect:"通过"}).regexValidator({regExp:"intege",dataType:"enum",onError:"端口格式不正确"}).inputValidator( {
							empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
							min : 1,
							max : 30,
							onError : "输入为空或者已经超过30个字符！"
						});
						$("#dataPath").formValidator( {
						onShow : "请输入数据存储地址!",
						onFocus : "请输入数据存储地址!"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
						$("#logPath").formValidator( {
						onShow : "请输入日志存储地址!",
						onFocus : "请输入日志存储地址!"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
						$("#indexPath").formValidator( {
						onShow : "请输入索引存储地址!",
						onFocus : "请输入索引存储地址!"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
						$("#storagePath").formValidator( {
						onShow : "请输入存储检测文件地址!",
						onFocus : "请输入存储检测文件地址!"
					}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min : 1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#nodeConnTime").formValidator( {
						onShow : "请输入节点连接超时时间(ms)!",
						onFocus : "请输入节点连接超时时间(ms)!"
					}).regexValidator({regExp:"intege",dataType:"enum",onError:"格式不正确"}).inputValidator( {
						empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						min:1,
						max : 30,
						onError : "输入为空或者已经超过30个字符！"
					});
				});
   function  isSub(){
   var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
   }
   