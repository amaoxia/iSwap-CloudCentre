<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ÎÞ±êÌâÎÄµµ</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${path}/js/jquery.js"></script>	
 <!--ÑéÖ¤js-->
<#include "/common/commonValidator.ftl">
<!--ÈÕÆÚ¿Ø¼þ-->
<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
<style type="text/css">
body{color:#333;font-size:12px;margin:0 auto;background-color:#ffffff;}
div,ul,li{overflow:hidden;}
ul{list-style:none;}
html,body,div,h1,h2,h3,h4,h5,h6,ul,ol,dl,li,dt,dd,p,blockquote,pre,form,fieldset,table,th,td{margin:0;padding:0;} 
.gengduo{float:right;font-size:12px;font-weight:normal;}
.bt{font-size:14px;font-weight:bold;padding:0 10px 0 0;}
.hid{display:none;}
.unhid{display: block;}
.main{width:99%;height:100%;border:1px solid #ccc;}
.main .bt{height:25px;line-height:25px;background-color:#ccc;border-bottom:2px solid #f00;}
.main .list{font-size:30px;font-weight:bold;padding:20px;}
#tab1_1,#tab1_2{width:100px;text-align:center;float:left;cursor:hand;}
#tab .up{width:100px;background-color:#f00;color:#fff;}
</style>
<script type="text/javascript" >
function showtab(m,n,count)
{
 for(var i=1;i<count+1;i++)
 {
  if(i==n)
  {
   getObject("list"+m+"_"+i).className="unhid";
   getObject("tab"+m+"_gd_"+i).className="gengduo unhid";
   getObject("tab"+m+"_"+i).className="up";
  }
  else
  {
   getObject("list"+m+"_"+i).className="hid";
   getObject("tab"+m+"_gd_"+i).className="gengduo hid";
   getObject("tab"+m+"_"+i).className="";
  }
 }
} 
function getObject(objectId) 
{
 if(document.getElementByIdx && document.getElementByIdx(objectId)) 
 {
  // W3C DOM
  return document.getElementByIdx(objectId);
 } 
 else if (document.all && document.all(objectId)) 
  {
   // MSIE 4 DOM
   return document.all(objectId);
  } 
 else if (document.layers && document.layers[objectId]) 
  {
   // NN 4 DOM.. note: this won't find nested layers
   return document.layers[objectId];
  } 
 else 
  {
   return false;
  }
}
</script>
</head>

<body style="width:100%;height:100%;overflow-x:hidden;overflow-y:hidden;">
<form action="${path}/iswapesb/workflow/esbworkflowAction!workFlowTest.action" method="post" name="saveForm" id="saveForm">
<div class="main" > 
		<div id="tab" class="bt">
		 <ul id="tab1_1" class="up" onmousemove="javascript:showtab(1,1,2);">Map</ul>
		 <ul id="tab1_2" onmousemove="javascript:showtab(1,2,2);">XML</ul>
		</div>
		<div class="list">
		  <div id="list1_1" class="unhid">
		    <ul class="item1_c">
		     	<li>
		        <p>Á÷³ÌÃû³Æ£º</p>
		        <span>
		        <input type="text" disabled=true¡¡ value="${esbWorkFlow.workFlowName?default('')}"/>
		        <input type="hidden" name="id" value="${esbWorkFlow.id}"/>
		        </span>
		      </li>
		      <li>
		        <p>²ÎÊýÊäÈë£º</p>
		        <span >
		        <table class="tabs1" id="parmTable" >
		          <tr>
		            <th style="width:100px;">ÐòºÅ</th>
		            <th>²ÎÊýÃû³Æ</th>
		            <th>²ÎÊýÖµ</th>
		            <th style="width:80px;">²Ù×÷</th>
		            <th></th>
		          </tr>
		          <tr class="trbg">
		            <td style="width:100px;">1</td>
		            <td><input type="text" name="attributeKey" id="attributeKey" style="width:170px;"/></td>
		            <td><label for="select2"></label>
		            	<input type="text" name="attributeValue" id="attributeValue" style="width:170px;"/>
		            </td>
		            <td><a href="#" class="tabs1_cz" ><img src="${path}/images/tag_add3.gif"  onclick="addParm()"  /></a></td>
		            <td class="tabs1_addlink"><a href="#"><img src="${path}/images/tag_del3.gif"/></a></td>
		          </tr>
		        </table>
		        </span> </li>
		    </ul>
		</div>
		<div id="list1_2" class="hid">
		 <ul class="item1_c">
		     	<li>
		        <p>Á÷³ÌÃû³Æ£º</p>
		        <span>
		        <input type="text" disabled=true¡¡ value="${esbWorkFlow.workFlowName?default('')}"/>
		        </span>
		      </li>
		      <li>
		        <p>²ÎÊýÊäÈë£º</p>
		        <span >
		        <table class="tabs1" id="parmTable" >
		          <tr>
		            <td><textarea name="attributeXML" id="attributeXML" cols="80" rows="8"><?xml version="1.0" encoding="UTF-8"?>
<root>
	<parameter key="hudws" >111111111111, 22222222222, 3333333333</parameter>
	<parameter key="num" >3</parameter>
</root></textarea></td>
		          </tr>
		        </table>
		        </span> </li>
		    </ul>
		</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		but();
		$("#addExtent,#cfm_pop").bind('click', function(event) {
			$("#popnav2").toggle("slow");
		});
	});
	
//Ìí¼Ó²ÎÊý
function addParm(){
	var parmTable = document.getElementById("parmTable");
	var tabLength = parmTable.rows.length;
		parmTable.insertRow();
		if(tabLength%2!=0){
			parmTable.rows[parmTable.rows.length-1].setAttribute("className","trbg");
		}
		parmTable.rows[parmTable.rows.length-1].insertCell(0);// ²åÈëÁÐ
		parmTable.rows[parmTable.rows.length-1].cells[0].innerText= tabLength;
		parmTable.rows[parmTable.rows.length-1].insertCell(1)// ²åÈëÁÐ
		parmTable.rows[parmTable.rows.length-1].cells[1].innerHTML= "<input type=\"text\" name=\"attributeKey\" id=\"attributeKey\" style=\"width:180px;\"/>";
		parmTable.rows[parmTable.rows.length-1].insertCell(2);// ²åÈëÁÐ
		parmTable.rows[parmTable.rows.length-1].cells[2].innerHTML= "<label for=\"select2\"></label>"+"<input type=\"text\" name=\"attributeValue\" id=\"attributeValue\" style=\"width:180px;\"/>";
		parmTable.rows[parmTable.rows.length-1].insertCell(3);// ²åÈëÁÐ
		parmTable.rows[parmTable.rows.length-1].cells[3].innerHTML="<a href=\"#\" class=\"tabs1_cz\" ><img src=\"${path}/images/tag_add3.gif\"  onclick=\"addParm()\" /></a>";
		parmTable.rows[parmTable.rows.length-1].insertCell(4);// ²åÈëÁÐ
		parmTable.rows[parmTable.rows.length-1].cells[4].innerHTML="<a href=\"#\"><img src=\"${path}/images/tag_del3.gif\" onclick=\"delParm(this)\"/></a>";
}
//É¾³ýÐÐÎª
function delParm(obj){
var parmTable = document.getElementById("parmTable");
parmTable.deleteRow(obj.parentElement.parentElement.parentElement.rowIndex);
}


function but(){
var DG = frameElement.lhgDG; 
//¹Ø±Õ´°¿Ú ²»×öÈÎºÎ²Ù×÷

 DG.addBtn( 'close', '¹Ø±Õ´°¿Ú', singleCloseWin); 
 DG.addBtn( 'save', '²âÊÔ', saveWin); 
 function singleCloseWin(){
	 DG.cancel();
 }
	function saveWin() {
		//ÊµÏÖÂß¼­
		isSub();
	}
}
//ÑéÖ¤ÓÃ»§ÊÇ·ñÍ¨¹ý
function isSub() {
   $("#attributeValue")
	//var fa = jQuery.formValidator.pageIsValid('1');
	//if (fa) {
		document.forms[0].submit();
		//close();
	//}
}
</script>
<form>
</body>
</html>
