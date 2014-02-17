<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>流程定制</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
</head>
<body>
<form name="save" id="save" method="post" action="${path}/iswapesb/esbtask/esbTaskAction!add.action" id="saveForm">
<input type="hidden" name="esbTaskMsg.workFlow.id" value="${workFlowId?default('')}">
<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
  <div class="main">
    <@s.token/>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
			  <td  height="100%" valign="top" ><div class="">
				  <ul class="item2_c">
					<li>
					  <p>任务名称：</p>
					  <span>
					  <input type="text" name="esbTaskMsg.taskName" id="taskName"/> 
					  </span>
					  <span><div id='taskNameTip'></div></span>
					  </li>
					<li class="item2_bg">
					  <p>频率类型：</p>
					  <span id="typeRadio">
					     <input type="radio" name="esbTaskMsg.type"  id="type1" value="1" checked="checked"> 秒
					     <input type="radio" name="esbTaskMsg.type"  id="type1" value="2"> 分
					     <input type="radio" name="esbTaskMsg.type"  id="type1" value="3"> 时
					     <input type="radio" name="esbTaskMsg.type"  id="type1" value="4"> 天
					     <input type="radio" name="esbTaskMsg.type"  id="type1" value="5"> 周
					     <input type="radio" name="esbTaskMsg.type"  id="type1" value="6"> 月
					  </span>
					  <span><div id='type1Tip'></div></span>
					</li>
					<li class="">
					  <p>频率：</p>
					  <span id="miao">
					            秒<select name="seconds" id="miaoSelect">
					       <#list 1..59 as x>   
					         <option value="${x}">${x}</option> 
					       </#list>
					     </select> 
					     &nbsp;
					  </span>
					   <span id="fen">
					             分<select name="branch" id="fenSelect">
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
					      <input type="checkbox" name="eweek" value="WED"/> 星期三&nbsp;
					      <input type="checkbox" name="week" value="THU"/> 星期四&nbsp;
					      <input type="checkbox" name="week" value="FRI"/> 星期五&nbsp;
					      <input type="checkbox" name="week" value="SAT"/> 星期六&nbsp;
					      <input type="checkbox" name="eweek" value="SUN"/> 星期日&nbsp;
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
					  <input type="text" id="d245" name="esbTaskMsg.startDate" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate"/> &nbsp;
					  </span>
					  <span><div id='appCodeTip'></div></span>
					</li>	
					<li>
		        <p>参数输入：</p>
		        <span >
		        <table class="tabs1" id="parmTable" >
		          <tr>
		            <td><textarea name="esbTaskMsg.message" id="attributeXML" cols="73" rows="7"></textarea></td>
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
<div class="clear"></div>
</div>
</form>
<!--增删查改-->
<#include "/common/commonUd.ftl">
 <!--验证js-->
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/cloudnode/nodetask.js'></script> 
</body>
</html>
<script type="text/javascript"> 
	$(document).ready(function(){
	 });
	 
	 function onSelectedItem(newvalue){
		
	}
</script>
