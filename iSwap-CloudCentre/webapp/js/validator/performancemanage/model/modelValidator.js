$(document).ready(function() {
	$.formValidator.initConfig( {
		onError : function(msg) {
		//alert(msg);
		}
	});
	function core(){
		var SumSiblingsCore= $("#SumSiblingsCore").val();
		var maxcore=100-parseInt(SumSiblingsCore);
		return parseInt(maxcore);
	}
	
	$("#fraction").formValidator( {
		onShow : "考核项标准分数不能为空！",
		onFocus : "请输入考核项标准分数!"
	}).inputValidator({min:1,max:core(),type:"value",onError:"考核项标准分数必须在1-"+core()+"之间，请确认"});
	
	$("#recodeMethod").formValidator( {
						onEmpty : true,
						onShow : "请输入考核项记分方法",
						onFocus : "不超过122个字符",
						onCorrect : "通过",
						defaultValue : ""
					}).inputValidator( {
						min:1,
						max : 122,
						onError : "输入为空或者已经超过122个字符"
					});
	});

function checkMaxInput(obj,i,obj1){
	var obj1
  		var maxLen=i
        if(obj.value.length>maxLen)
		   {  
		   obj.value=obj.value.substring(0,maxLen);
		   obj1.innerText="你输入的内容超出了字数限制";
		   }
  		 else
   			{ 
  			 obj1.innerText='剩'+(maxLen-obj.value.length)+'字';
  			 }
  } 

function reset(){
	$("#fraction").val("");
	$("#recodeMethod").val("");
}
