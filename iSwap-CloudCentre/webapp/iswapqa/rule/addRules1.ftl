<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
<!--弹出窗口-->
<#include "/common/commonLhg.ftl">
<!--验证js-->
<script type='text/javascript' src='${path}/js/validator/iswapqa/ruleValidator.js'></script> 
<#include "/common/commonValidator.ftl">
<!--日期控件-->
<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
<script type="text/javascript">
var DG = frameElement.lhgDG; 
DG.addBtn('close', '关闭窗口', singleCloseWin);
// 关闭窗口 不做任何操作
function singleCloseWin() {
	DG.cancel();
}

DG.addBtn( 'save', '下一步', saveWin); 
function saveWin() {
	//实现逻辑
	next();
}

     function reloadPage(){
		location.href="${path}/iswapqa/rule/ruleFileAction!list.action";
     }

function next(){
	//var win = dg.dgWin;
	//win.location.href="${path}/iswapqa/rule/ruleAction!addRulesShow2.action";
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa&&checkType()) {
	document.forms['saveForm'].submit();
	dg.reDialogSize(800,550);
	}
}
function checkType(){
var flag2 = "true";
var str2 = "";
var ruleObj = document.getElementById("ruleObj").value;
var applyTo = document.getElementById("applyTo").value;
var attributeName = document.getElementsByName("attributeName");
var attributeType = document.getElementsByName("attributeType");
if(ruleObj==null||ruleObj==""){
	flag2 = "false";
	str2 = "请选择对象";
}
if(applyTo==""||applyTo==null){
	flag2 = "false";
	str2 = "请选择所属应用";
}
//验证输入参数
var count = 0;
for(var i=0;i<attributeName.length;i++){
	if(attributeName[i].value!=""){
		count++;
	}
}
if(count==0){
	flag2 = "false";
	str2 = "请输入输入参数名称";
}
//验证参数类型
for(var i=0;i<attributeName.length;i++){
	if(attributeName[i].value!=""){
		if(attributeType[i].value==""){
			flag2 = "false";
			str2 = "请输入输入参数类型";
		}
	}
}
  if(flag2 == "false"){
    	alert(str2);
    	return false;
    }
return true;
}	
function changeAttr(ruleObj){
	jQuery.ajax(
				{
					type: "post",
					dataType: "html",
					url: "${path}/iswapqa/rule/ruleAction!getRuleObjAttr.action",
					data: {
						ruleObj:ruleObj
					},
					async: false,
					success: function(msg){
						var parmTable = document.getElementById("parmTable");
						var tabLength = parmTable.rows.length;
						for(var i=0;i<tabLength-1;i++){
								parmTable.deleteRow(1);
						}
						for(var i=0;i<msg.split(";").length-1;i++){
							tabLength = parmTable.rows.length;
							var enName = msg.split(";")[i].split("|")[0];
							var cnName = msg.split(";")[i].split("|")[1];
							var type = msg.split(";")[i].split("|")[2];
							if(type=="String"){
								enType="string";
								cnType="字符串";
							}
							if(type=="int"){
								enType="number";
								cnType="数字";
							}
							if(type=="boolean"){
								enType="boolean";
								cnType="布尔值";
							}
							if(type=="date"){
								enType="date";
								cnType="日期";
							}
							parmTable.insertRow();
							if(tabLength%2!=0){
								parmTable.rows[parmTable.rows.length-1].setAttribute("className","trbg");
							}
							parmTable.rows[parmTable.rows.length-1].insertCell(0);// 插入列
							parmTable.rows[parmTable.rows.length-1].cells[0].innerText= tabLength;
							parmTable.rows[parmTable.rows.length-1].insertCell(1)// 插入列
							parmTable.rows[parmTable.rows.length-1].cells[1].innerHTML= 
							"<input type=\"hidden\" name=\"attributeName\" id=\"attributeName\" value=\""+enName+"\"/><input type=\"text\"  name=\"cnName\" id=\"cnName\" style=\"width:180px;\" value=\""+cnName+"\"/>";
							parmTable.rows[parmTable.rows.length-1].insertCell(2);// 插入列
							parmTable.rows[parmTable.rows.length-1].cells[2].innerHTML= "<label for=\"select2\"></label>"+
							"<input type=\"hidden\" name=\"attributeType\" id=\"attributeType\"  value=\""+enType+"\"/><input type=\"text\"  style=\"width:120px;\" value=\""+cnType+"\"/>";
							parmTable.rows[parmTable.rows.length-1].insertCell(3);// 插入列
							parmTable.rows[parmTable.rows.length-1].cells[3].innerHTML="<a href=\"#\" class=\"tabs1_cz\" ><img src=\"${path}/images/tag_add3.gif\"  onclick=\"addParm()\" /></a>";
							parmTable.rows[parmTable.rows.length-1].insertCell(4);// 插入列
							parmTable.rows[parmTable.rows.length-1].cells[4].innerHTML="<a href=\"#\"><img src=\"${path}/images/tag_del3.gif\" onclick=\"delParm(this)\"/></a>";
							
						}
					}
				}
			);
}
</script>
</head>

<body style="width:100%;height:100%;overflow-x:hidden;overflow-y:scroll;">
<form action="${path}/iswapqa/rule/ruleAction!addRulesShow2.action" method="post" name="saveForm" id="saveForm">
<div class="pop_div6 pm6_c" > 
  <div class="item1">
    <ul class="item1_c">
     	<li>
        <p>规则文件名称：
        <span>
        <input  name="fileName" id="fileName"  type="text" size="30" />
        </span>
        <div class="item_ts"><span id="fileNameTip"></span> </div>
        </p>
      </li>
      <li class="item_bg">
        <p>规则名称：
        <span>
        <input  name="ruleName" id="ruleName"  type="text" size="30" />
        </span>
        <div class="item_ts"><span id="ruleNameTip"></span></div>
        </p>
      </li>
      <li>
        <p>选择对象：</p>
        <span>
        <select name="ruleObj" id="ruleObj" style="width:200px;" onchange="changeAttr(this.value)">
          <option value="">请选择对象</option>
            <#if objList?exists>
           <#list objList as obj>   
          <option value="${obj.enName}">${obj.cnName}</option>
           </#list>
          </#if>
        </select>
        </span>
      </li>
      <li class="item_bg">
        <p>所属应用：</p>
        <span>
        <select name="applyTo" id="applyTo" style="width:200px;">
          <option value="">请选择应用</option>
           <#if appMsgList?exists>
           <#list appMsgList as appMsg>   
          <option value="${appMsg.appName}">${appMsg.appName}</option>
             </#list>
          </#if>
        </select>
        </span> </li>
      
      <li>
        <p>规则输入参数：</p>
        <span >
        <table class="tabs1" id="parmTable" >
          <tr>
            <th>序号</th>
            <th>输入参数名称</th>
            <th>参数类型</th>
            <th>&nbsp;&nbsp;操作</th>
            <th></th>
          </tr>
          <tr class="trbg">
            <td>1</td>
            <td><input type="text" name="attributeName" id="attributeName" style="width:180px;"/></td>
            <td><label for="select2"></label>
              <select name="attributeType" id="attributeType" style="width:120px;">
                <option>请选择</option>
           	<option value="string">字符串</option>
           	<option value="number">数字</option>
           	<option  value="boolean">布尔值</option>
           	<option value="date">日期</option>
              </select></td>
            <td><a href="#" class="tabs1_cz" ><img src="${path}/images/tag_add3.gif"  onclick="addParm()"  /></a></td>
            <td class="tabs1_addlink"><a href="#"><img src="${path}/images/tag_del3.gif" onclick="delParm(this)"/></a></td>
          </tr>
          <tr>
            <td>2</td>
            <td class="trbg"><input type="text" name="attributeName" id="attributeName" style="width:180px;"/></td>
            <td class="trbg"><label for="select2"></label>
              <select name="attributeType" id="attributeType" style="width:120px;">
        <option>请选择</option>
             	<option value="string">字符串</option>
           	<option value="number">数字</option>
           	<option  value="boolean">布尔值</option>
           	<option value="date">日期</option>
              </select></td>
           <td><a href="#" class="tabs1_cz"><img src="${path}/images/tag_add3.gif" onclick="addParm()"  /></a></td>
            <td class="tabs1_addlink"><a href="#"><img src="${path}/images/tag_del3.gif" onclick="delParm(this)" /></a></td>
          </tr>
          <tr class="trbg">
            <td>3</td>
            <td><input type="text" name="attributeName" id="attributeName" style="width:180px;"/></td>
            <td><label for="select2"></label>
              <select name="attributeType" id="attributeType" style="width:120px;">
              <option>请选择</option>
             	<option value="string">字符串</option>
           	<option value="number">数字</option>
           	<option  value="boolean">布尔值</option>
           	<option value="date">日期</option>
              </select></td>
          <td><a href="#" class="tabs1_cz"><img src="${path}/images/tag_add3.gif" onclick="addParm()"  /></a></td>
            <td class="tabs1_addlink"><a href="#"><img src="${path}/images/tag_del3.gif" onclick="delParm(this)" /></a></td>
          </tr>
            <tr>
            <td>4</td>
            <td class="trbg"><input type="text" name="attributeName" id="attributeName" style="width:180px;"/></td>
            <td class="trbg"><label for="select2"></label>
              <select name="attributeType" id="attributeType" style="width:120px;">
        <option>请选择</option>
             	<option value="string">字符串</option>
           	<option value="number">数字</option>
           	<option  value="boolean">布尔值</option>
           	<option value="date">日期</option>
              </select></td>
           <td><a href="#" class="tabs1_cz"><img src="${path}/images/tag_add3.gif" onclick="addParm()"  /></a></td>
            <td class="tabs1_addlink"><a href="#"><img src="${path}/images/tag_del3.gif" onclick="delParm(this)" /></a></td>
          </tr>
            <tr class="trbg">
            <td>5</td>
            <td><input type="text" name="attributeName" id="attributeName" style="width:180px;"/></td>
            <td><label for="select2"></label>
              <select name="attributeType" id="attributeType" style="width:120px;">
              <option>请选择</option>
             	<option value="string">字符串</option>
           	<option value="number">数字</option>
           	<option  value="boolean">布尔值</option>
           	<option value="date">日期</option>
              </select></td>
          <td><a href="#" class="tabs1_cz"><img src="${path}/images/tag_add3.gif" onclick="addParm()"  /></a></td>
            <td class="tabs1_addlink"><a href="#"><img src="${path}/images/tag_del3.gif" onclick="delParm(this)" /></a></td>
          </tr>
        </table>
        </span> </li>
      <li class="item_bg">
        <p>规则描述：</p>
        <span>
        <textarea cols="90" name="ruledes" id="ruledes" rows="6"></textarea>
        </span></li>
    </ul>
    <!--<div class="btn_certer">
      <input name="" type="button" value="下一步"  class=" btn2_s" onclick="next()"/>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="link_btn" onclick="close12()">关闭窗口</a></div>
  </div>-->
</div>
<div class="float_div" id="popnav2">
  <div class="float_timg"></div>
  <div class="float_main"> <img src="${path}/images/tree.png" /> </div>
  <div><input type="button" value="确定" id="cfm_pop" /></div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#addExtent,#cfm_pop").bind('click', function(event) {
			$("#popnav2").toggle("slow");
		});
		
	});
	
//添加参数
function addParm(){
	var parmTable = document.getElementById("parmTable");
	var tabLength = parmTable.rows.length;
		parmTable.insertRow();
		if(tabLength%2!=0){
			parmTable.rows[parmTable.rows.length-1].setAttribute("className","trbg");
		}
		parmTable.rows[parmTable.rows.length-1].insertCell(0);// 插入列
		parmTable.rows[parmTable.rows.length-1].cells[0].innerText= tabLength;
		parmTable.rows[parmTable.rows.length-1].insertCell(1)// 插入列
		parmTable.rows[parmTable.rows.length-1].cells[1].innerHTML= "<input type=\"text\" name=\"attributeName\" id=\"attributeName\" style=\"width:180px;\"/>";
		parmTable.rows[parmTable.rows.length-1].insertCell(2);// 插入列
		parmTable.rows[parmTable.rows.length-1].cells[2].innerHTML= "<label for=\"select2\"></label>"+
		"<select name=\"attributeType\" id=\"attributeType\" style=\"width:120px;\"><option>请选择</option>"+
"<option value=\"string\">字符串</option><option value=\"number\">数字</option><option  value=\"boolean\">布尔值</option><option value=\"date\">日期</option></select>";
		parmTable.rows[parmTable.rows.length-1].insertCell(3);// 插入列
		parmTable.rows[parmTable.rows.length-1].cells[3].innerHTML="<a href=\"#\" class=\"tabs1_cz\" ><img src=\"${path}/images/tag_add3.gif\"  onclick=\"addParm()\" /></a>";
		parmTable.rows[parmTable.rows.length-1].insertCell(4);// 插入列
		parmTable.rows[parmTable.rows.length-1].cells[4].innerHTML="<a href=\"#\"><img src=\"${path}/images/tag_del3.gif\" onclick=\"delParm(this)\"/></a>";
}
//删除行为
function delParm(obj){
var parmTable = document.getElementById("parmTable");
parmTable.deleteRow(obj.parentElement.parentElement.parentElement.rowIndex);
}
</script>
<form>
</body>
</html>
