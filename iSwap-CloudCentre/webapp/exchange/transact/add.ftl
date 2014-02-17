<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<div style="width:450px">
<form action="${path}/exchange/transact/transact!add.action" method="post" name="saveForm" id="saveForm">
<@s.token/>
<div class="pop_div6 pm6_c" > 
  <div class="item1">
    <ul class="item1_c">
      <li>
      	<input type="hidden" name="sendTask.id" value="${taskId}"/>
      	 	<p>催办标题:</p>
            <span>
             	<input type="text" size="40"  id="title"/>
            </span>
            <span><div id="titleTip"></div></span>
      </li>
	      <li class="item_bg">
	   			<p>催办方式:</p>
	            <span>
	             	<select  id="transactType" style="width:260px;">
	             	   <option value="">请选择</option>
					   <option value="0">系统消息</option>   
					   <option value="1">邮件通知</option>  
					   <option value="2">短信通知</option>          	
	             	</select>
	            </span>
	            <span><div id="transactTypeTip"></div></span>   
	      </li>
      <li>
      <p>催办内容:</p>
      <span>
      <textarea id="content"  cols="40" rows="5"></textarea>
      </span>
       <span><div id="contentTip"></div></span>
      </li>
    </ul>
</div>
</div>
</form>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/validator/transact/transact.js"/>
<#include "/common/commonValidator.ftl">
<#include "/common/commonUd.ftl">
<script type="text/javascript">
 function  isSub(){
   var fa = jQuery.formValidator.pageIsValid('1');
   var transactType= $("#transactType").attr("value");
   var content=encodeURIComponent($("#content").html());
   var title=encodeURIComponent($("#title").val());
	if (fa) {
			$.post(
	 		"${path}/exchange/transact/transact!addTransact.action",
			{"taskId":${taskId},"transactType":transactType,"content":content,"title":title},
			function(data){
				if(data=='1'){
			   		 alert("保存成功！");
			   		 DG.cancel();
			   }else{
			    	 alert("保存失败！");
					   }
				   });
 		 }
 }
</script>
</body>
</html>
