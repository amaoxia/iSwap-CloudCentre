$(document).ready(
	function() {
		$.formValidator.initConfig( {
			onError : function(msg) {
			}
		});
		$("#taskName").formValidator( {
			onShow : "请输入任务名称,必填选项",
			onFocus : "请输入任务名称！"
		}).inputValidator( {
			min : 1,
			max : 30,
			onerror : "输入为空或者已经超过30个字符！"
		});
		$("#workFlowId").formValidator( {
			onShow : "请选择流程,必填选项",
			onFocus : "请选择流程！"
		}).inputValidator( {
			min : 1,
			onError : "请选择流程！"
		});
		$("#type1").formValidator( {
			onShow : "请选择频率类型,必填选项",
			onFocus : "请选择频率类型！"
		}).inputValidator( {
			min : 1,
			max : 30,
			onError : "输入为空或者已经超过30个字符！"
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
		//var flag = check(); 
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
