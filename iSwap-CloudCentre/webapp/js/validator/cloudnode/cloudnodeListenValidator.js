$(document)
		.ready(
				function() {
					$.formValidator.initConfig( {
						onError : function(msg) {
						}
					});
					$("#listenName").formValidator( {
						onShow : "请输入监听名称",
						onFocus : "请输入监听名称！"
					}).inputValidator( {
						min : 1,
						max : 90,
						onError : "输入为空或者已经超过90个字符！"
					});
					$("#dbName").formValidator( {
						onShow : "请输入实例名称",
						onFocus : "请输入实例名称！"
					}).inputValidator( {
						min : 1,
						max : 60,
						onError : "请输入实例名称！"
					});
					$("#filedName").formValidator( {
						onShow : "请输入字段名称",
						onFocus : "请输入字段名称！"
					}).inputValidator( {
						min : 1,
						max : 60,
						onError : "请输入实例名称！"
					});
					$("#collectionName").formValidator( {
						onShow : "请输入 表名称",
						onFocus : "请输入表名称！"
					}).inputValidator( {
						min : 1,
						max : 60,
						onError : "输入为空或者已经超过60个字符！"
					});
					$("#filedStatus").formValidator( {
						onShow : "请输入字段状态",
						onFocus : "请输入字段状态！"
					}).inputValidator( {
						min : 1,
						max : 20,
						onError : "输入为空或者已经超过30个字符！"
					});
					$("#notes").formValidator( {
						onEmpty : true,
						onShow : "请输入描述",
						onFocus : "描述不超过200个字符",
						onCorrect : "通过"
					}).inputValidator( {
					    empty:{leftEmpty:false,rightEmpty:false,emptyError:"不能输入空格!"},
						max : 200,
						onError : "输入为空或者已经超过200个字符"
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
$(document).ready(function(){
	 $("#miao").show();
    $("#fen").hide();
    $("#xiaoshi").hide();
    $("#tian").hide();
    $("#zhou").hide();
    $("#yue").hide();
    $("#time").hide();
    $("#taskId").click(function(){
    		var type = dwr.util.getValue("type");
    		if(type=="1"){
    			 $("#miao").show();
			     $("#fen").hide();
			     $("#xiaoshi").hide();
			     $("#tian").hide();
			     $("#zhou").hide();
			     $("#yue").hide();
			     $("#time").hide();
			     $("#startDate").addClass("item2_bg");
    		}
    		if(type=="2"){
    			 $("#miao").hide();
			     $("#fen").show();
			     $("#xiaoshi").hide();
			     $("#tian").hide();
			     $("#zhou").hide();
			     $("#yue").hide();
			     $("#time").hide();
			     $("#startDate").addClass("item2_bg");
    		}
    		if(type=="3"){
    			 $("#miao").hide();
			     $("#fen").hide();
			     $("#xiaoshi").show();
			     $("#tian").hide();
			     $("#zhou").hide();
			     $("#yue").hide();
			     $("#time").hide();
			     $("#startDate").addClass("item2_bg");
    		}
    		if(type=="4"){
    			 $("#miao").hide(); 
			     $("#fen").hide();
			     $("#xiaoshi").hide();
			     $("#tian").show();
			     $("#zhou").hide();
			     $("#yue").hide();
			     $("#time").hide();
			     $("#startDate").addClass("item2_bg");
    		}
    		if(type=="5"){
    			 $("#miao").hide(); 
			     $("#fen").hide();
			     $("#xiaoshi").hide();
			     $("#tian").hide();
			     $("#zhou").show();
			     $("#yue").hide();
			     $("#time").show();
			     $("#startDate").removeClass();
    		}
    		if(type=="6"){
    			 $("#miao").hide(); 
			     $("#fen").hide();
			     $("#xiaoshi").hide();
			     $("#tian").hide();
			     $("#zhou").hide();
			     $("#yue").show();
			     $("#time").show();
			     $("#startDate").removeClass();
    		}
    });
    
});

  function check(){
       var type = dwr.util.getValue("type");
      // alert(type);
       if(type=="1"){
           var seconds = dwr.util.getValue("seconds");
           if(seconds==""){
              alert("频率不能为空！");
           }
       }
       if(type=="2"){
           var seconds = dwr.util.getValue("branch");
           if(branch==""){
              alert("频率不能为空！");
              return;
           }
       }
       if(type=="3"){
           var time = dwr.util.getValue("time");
           if(time==""){
              alert("频率不能为空！");
              return;
           }
       }
       if(type=="4"){
           var day = dwr.util.getValue("day");
           if(day==""){
              alert("频率不能为空！");
              return false;
           }
       }
       if(type=="5"){
           var week = dwr.util.getValue("week");
           alert(week);
           if(week==""){
              alert("频率不能为空！");
              return;
           }
       }
       if(type=="6"){
           var month = dwr.util.getValue("month");
           alert(month);
           if(seconds==""){
              alert("频率不能为空！");
              return;
           }
       }
  }

