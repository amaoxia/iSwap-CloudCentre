<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
<script type='text/javascript' src='${path}/dwr/util.js'></script>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<!--Ztree-->
<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
</head>
<body>
<form name="save" id="save" method="post" action="${path}/cloudnode/nodetask/task!update.action" id="saveForm">
<input type="hidden" name="deptId" value="${deptId?default('')}"/>
<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
  <div class="main">
    
    <div class="main_c2" >
    	<br/>指定业务流程：<br />
      <div class="left_tree_com" style="width:250px;">
        <span>
        <p>
          <div scrolling="Auto" frameborder="0" style="border:0px;height:423px;width:250px;background:url(${path}/images/common_menu_bg.jpg) #CFE1ED bottom repeat-x" class="wpage" >
          	<ul id="tree" class="tree" style="height:412px;width:240px;overflow:auto;"></ul>
          </div>
        </p>
        </span> </div>
      <div class="right_main">
        <div class="loayt_01 rlist100">
          <div class="loayt_top"> <span class="loayt_tilte"><b>修改流程任务定制 </b></span><span class="loayt_right"><img src="${path}/images/kuang_right.png" width="10" height="31" /></span> </div>
          <div class="loayt_mian">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
			  <td  height="100%" valign="top" ><div class="">
				  <ul class="item2_c">
					<li>
					  <p>任务名称：</p>
					  <span>
					   <input type="text" name="taskName" id="taskName" value="${taskName?default('')}"/> 
					   <input type="hidden" name="id" value="${id}"/> 
					  <input type="hidden" name="times" value="${times}"/> 
					  <input type="hidden" name="workFlow.id" id="workFlowId" value="${workFlow.id?default('')}"/>
					  </span>
					  <span><div id='taskNameTip'></div></span>
					  </li>
					
					<li class="item2_bg">
					  <p>频率类型：</p>
					  <span id="taskId">
					     <input type="radio" name="type"  id="type1" value="1" checked="checked"> 秒
					     <input type="radio" name="type"  id="type1" value="2"> 分
					     <input type="radio" name="type"  id="type1" value="3"> 时
					     <input type="radio" name="type"  id="type1" value="4"> 天
					     <input type="radio" name="type"  id="type1" value="5"> 周
					     <input type="radio" name="type"  id="type1" value="6"> 月
					  </span>
					  <span><div id='type1Tip'></div></span>
					</li>
					<li class="">
					  <p>频率：</p>
					  <span id="miao">
					            秒<select name="seconds" id="appMsgId">
					       <#list 1..59 as x>   
					         <option value="${x}">${x}</option> 
					       </#list>
					     </select> 
					     &nbsp;
					  </span>
					   <span id="fen">
					             分<select name="branch" id="appMsgId">
					       <#list 1..59 as x>   
					         <option value="${x}">${x}</option> 
					       </#list>
					     </select> 
					     &nbsp; 
					  </span>
					   <span id="xiaoshi">
					             小时<select name="time" id="appMsgId">
					       <#list 1..23 as x>   
					         <option value="${x}">${x}</option> 
					       </#list>
					     </select> 
					     &nbsp;
					  </span> 
					  <span id="tian">
					             天<input type="text" id="d242" name="day" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'H:mm'})" class="Wdate"/> &nbsp;
					  </span>
					  </li>
					   <span id="zhou">
					      <input type="checkbox" name="week" value="MON"/> 星期一&nbsp;
					      <input type="checkbox" name="week" value="TUE"/> 星期二&nbsp;
					      <input type="checkbox" name="week" value="WED"/> 星期三&nbsp;
					      <input type="checkbox" name="week" value="THU"/> 星期四&nbsp;
					      <input type="checkbox" name="week" value="FRI"/> 星期五&nbsp;
					      <input type="checkbox" name="week" value="SAT"/> 星期六&nbsp;
					      <input type="checkbox" name="week" value="SUN"/> 星期日&nbsp;
					  </span> 
					   <span id="yue">
					       <#list 1..12 as x>   
					         <#if x==7></br></#if>
					         <input type="checkbox" name="month" value="${x}"/> ${x} 月&nbsp;
					       </#list>
					  </span> 
					<li class="item2_bg" id="time">
					  <p>执行时间：</p>
					  <span>
					  <input type="text" id="d243" name="executeTime" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'H:mm'})" class="Wdate"/> &nbsp;
					  </span>
					  <span><div id='appCodeTip'></div></span>
					</li>	
					<li class="item2_bg" id="startDate">
					  <p>生效时间：</p>
					  <span>
					  <input type="text" id="d245" name="startDate" value="${startDate?default('')}" readonly="readonly"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate"/> &nbsp;
					  </span>
					  <span><div id='appCodeTip'></div></span>
					</li>
					<li>
			        <p>参数输入：</p>
			        <span >
			        <table class="tabs1" id="parmTable" >
			          <tr>
			            <td><textarea name="message" id="attributeXML" cols="73" rows="7">${message?default('')}</textarea></td>
			          </tr>
			        </table>
			        </span> </li>		
				  </ul>
				</div></td>
			</tr>
                  </table></td>
              </tr>
            </table>
          </div>
        </div>
        <div class=" clear"></div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="clear"></div>
</div>
</form>


<!--日期控件-->
 <script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
<!--XTree-->
<link rel="stylesheet" href="${path}/css/xtree.css" type="text/css" />
<!--增删查改-->
<#include "/common/commonUd.ftl">
 <!--验证js-->
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/cloudnode/nodetask.js'></script> 
<script type="text/javascript">
$(document)
.ready(
	function() {
		$.formValidator.initConfig( {
			onError : function(msg) {
			}
		});
		$("#appName").formValidator( {
			onShow : "请输入应用名称,必填选项",
			onFocus : "请输入应用名称！"
		}).inputValidator( {
			min : 1,
			max : 30,
			onerror : "输入为空或者已经超过30个字符！"
		});
		$("#appCode").formValidator( {
			onShow : "请输入应用标识,必填选项",
			onFocus : "请输入应用标识！"
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
function sub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
}
$(document).ready(function(){
	 var type = ${type?default('')};
	 dwr.util.setValue("type", type);
	 //频率
	var times = dwr.util.getValue("times");
	if(type=="1"){
		 $("#miao").show();
	     $("#fen").hide();
	     $("#xiaoshi").hide();
	     $("#tian").hide();
	     $("#zhou").hide();
	     $("#yue").hide();
	     $("#time").hide();
	     dwr.util.setValue("seconds", times);
	}
	if(type=="2"){
		 $("#miao").hide();
	     $("#fen").show();
	     $("#xiaoshi").hide();
	     $("#tian").hide();
	     $("#zhou").hide();
	     $("#yue").hide();
	     $("#time").hide();
	     dwr.util.setValue("branch", times);
	}
	if(type=="3"){
		 $("#miao").hide();
	     $("#fen").hide();
	     $("#xiaoshi").show();
	     $("#tian").hide();
	     $("#zhou").hide();
	     $("#yue").hide();
	     $("#time").hide();
	     dwr.util.setValue("time", times);
	}
	if(type=="4"){
		 $("#miao").hide(); 
	     $("#fen").hide();
	     $("#xiaoshi").hide();
	     $("#tian").show();
	     $("#zhou").hide();
	     $("#yue").hide();
	     $("#time").hide();
	     dwr.util.setValue("day", times);
	}
	if(type=="5"){
		 $("#miao").hide(); 
	     $("#fen").hide();
	     $("#xiaoshi").hide();
	     $("#tian").hide();
	     $("#zhou").show();
	     $("#yue").hide();
	     $("#time").show();
	     var chkid = times.split(",");
	     var executeTime = chkid[(chkid.length)-1]
	     dwr.util.setValue("executeTime", executeTime); 
	     checkBoxChecked(times,type); 
	}
	if(type=="6"){
		 $("#miao").hide(); 
	     $("#fen").hide();
	     $("#xiaoshi").hide();
	     $("#tian").hide();
	     $("#zhou").hide();
	     $("#yue").show();
	     $("#time").show();
	     var chkid = times.split(",");
	     var executeTime = chkid[(chkid.length)-1]
	     dwr.util.setValue("executeTime", executeTime);
	     checkBoxChecked(times,type);
	}
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
         //选中
	function checkBoxChecked(ids,typeId){
	   var chkid = ids.split(",");
	   if(typeId=='5'){
	   		 for(var j=0;j<chkid.length;j++){
				  var tag = $("#zhou input");
				  for(var i=0;i<tag.length;i++){
				   	  if(tag[i].value==chkid[j]){
					  		tag[i].checked=true;
		 			  }
			      }
		   }
	   }
	   if(typeId=='6'){
	   		 for(var j=0;j<chkid.length;j++){
				  var tag = $("#yue input");
				  for(var i=0;i<tag.length;i++){
				   	  if(tag[i].value==chkid[j]){
					  		tag[i].checked=true;
		 			  }
			      }
		   }
	   }
	}
     
});
</script>
<script type="text/javascript">
	var zTree;//树
	var setting;//参数设置
	var zTreeNodes = [] ;//数据
	setting={
	callback : {
		beforeChange: zTreeBeforeChange,
		change: zTreeOnChange,
	 	 beforeClick: zTreeBeforeClick,
	 	 asyncSuccess:zTreeOnAsyncSuccess
	},
	async : true,//异步加载 
	asyncUrl: "${path}/cloudnode/workflow/workflow!getWorkFlowByDeptId.action?deptId=${deptId?default('')}&workFlowId=${workFlow.id}",//数据文件 
	showLine:true,
	isSimpleData:true,
	treeNodeKey:"id",
	treeNodeParentKey:"pid",
	checkable : true,
	checkStyle : "radio",
	checkRadioType : "all"
	};		
	   function loadTree(){
		   zTree=$("#tree").zTree(setting,zTreeNodes);
		}
	    $(document).ready(function(){
			loadTree();//载入树
			
	});
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	     var node = zTree.getNodeByParam("id",${workFlow.id});
	}
	
	//点击节点之前的操作
	function zTreeBeforeClick(treeId, treeNode){
		    	var id=treeNode.id;
			     	if(id==-1){
			     		alert("请选择其他节点!");
			     		zTree.cancelSelectedNode();
			     	 return;
			     	}
		    }
		    
		    
	function zTreeBeforeChange(treeId, treeNode) {
		 if(treeNode.id==-1){
		 	 alert("请选择其他节点!");
			 return false;	
		 }
	}
	function zTreeOnChange(event, treeId, treeNode) {
	
		$("#workFlowId").val(treeNode.id);
	}
</script>
</body>
</html>
