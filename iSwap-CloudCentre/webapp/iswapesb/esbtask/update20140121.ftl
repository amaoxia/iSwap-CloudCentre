<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/dwr/util.js'></script>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<!--日期控件-->
 <script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
 <script type='text/javascript' src='${path}/js/xtree/xtree.js'></script>
<script type='text/javascript' src='${path}/js/xtree/webfxRediotreeitem.js'></script>
<!--XTree-->
<link rel="stylesheet" href="${path}/css/xtree.css" type="text/css" />
</head>
<body>
<form name="save" id="save" method="post" action="${path}/iswapesb/esbtask/esbTaskAction!update.action" id="saveForm">
<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
  <div class="main">
        <div class="loayt_01 rlist100">
          <div class="loayt_mian">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
			  <td  height="100%" valign="top" ><div class="">
				  <ul class="item2_c">
				  	<li class="item2_bg">
					  <p>所属流程：</p>
					  <span>
					  <input type="text" name="esbTaskMsg.taskName" value="${esbTaskMsg.taskName?default('')}" id="appName"/> 
					  <input type="hidden" name="esbTaskMsg.id" value="${esbTaskMsg.id}"/> 
					  <input type="hidden" name="times" value="${esbTaskMsg.times}"/> 
					  </span>
					  <span><div id='appNameTip'></div></span> 
					</li>
					<li>
					  <p>任务名称：</p>
					  <span>
					  <input type="text" name="esbTaskMsg.taskName" value="${esbTaskMsg.taskName?default('')}" id="appName"/> 
					  <input type="hidden" name="esbTaskMsg.id" value="${esbTaskMsg.id}"/> 
					  <input type="hidden" name="times" value="${esbTaskMsg.times}"/> 
					  </span>
					  <span><div id='appNameTip'></div></span> 
					</li>
					<li class="item2_bg">
					  <p>频率类型：</p>
					  <span id="taskId">
					     <input type="radio" name="type"  id="appMsgId" value="1" <#if esbTaskMsg.type=='1'>checked="checked"</#if> >秒
					     <input type="radio" name="type"  id="appMsgId" value="2" <#if esbTaskMsg.type=='2'>checked="checked"</#if>> 分
					     <input type="radio" name="type"  id="appMsgId" value="3" <#if esbTaskMsg.type=='3'>checked="checked"</#if>> 时
					     <input type="radio" name="type"  id="appMsgId" value="4" <#if esbTaskMsg.type=='4'>checked="checked"</#if>> 天
					     <input type="radio" name="type"  id="appMsgId" value="5" <#if esbTaskMsg.type=='5'>checked="checked"</#if>> 周
					     <input type="radio" name="type"  id="appMsgId" value="6" <#if esbTaskMsg.type=='6'>checked="checked"</#if>> 月
					  </span>
					  <span><div id=''></div></span>
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
					<li class="item2_bg" id="">
					  <p>生效时间：</p>
					  <span>
					  <input type="text" id="d245" name="esbTaskMsg.startDate" value="${esbTaskMsg.startDate?default('')}" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate"/> &nbsp;
					  </span>
					  <span><div id='appCodeTip'></div></span>
					</li>
					<li>			
		        <p>参数输入：</p>
		        <span >
		        <table class="tabs1" id="parmTable" >
		          <tr>
		            <td><textarea name="esbTaskMsg.message" id="attributeXML" cols="73" rows="7">${esbTaskMsg.message?default('')}</textarea></td>
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
</form>
<!--增删查改-->
<#include "/common/commonUd.ftl">
 <!--验证js-->
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/cloudnode/nodetask.js'></script> 
<script> 
$(document).ready(function(){
	 var type = ${esbTaskMsg.type?default('')};
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
     		}
     		if(type=="2"){
     			 $("#miao").hide();
			     $("#fen").show();
			     $("#xiaoshi").hide();
			     $("#tian").hide();
			     $("#zhou").hide();
			     $("#yue").hide();
			     $("#time").hide();
     		}
     		if(type=="3"){
     			 $("#miao").hide();
			     $("#fen").hide();
			     $("#xiaoshi").show();
			     $("#tian").hide();
			     $("#zhou").hide();
			     $("#yue").hide();
			     $("#time").hide();
     		}
     		if(type=="4"){
     			 $("#miao").hide(); 
			     $("#fen").hide();
			     $("#xiaoshi").hide();
			     $("#tian").show();
			     $("#zhou").hide();
			     $("#yue").hide();
			     $("#time").hide();
     		}
     		if(type=="5"){
     			 $("#miao").hide(); 
			     $("#fen").hide();
			     $("#xiaoshi").hide();
			     $("#tian").hide();
			     $("#zhou").show();
			     $("#yue").hide();
			     $("#time").show();
     		}
     		if(type=="6"){
     			 $("#miao").hide(); 
			     $("#fen").hide();
			     $("#xiaoshi").hide();
			     $("#tian").hide();
			     $("#zhou").hide();
			     $("#yue").show();
			     $("#time").show();
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
</body>
</html>
