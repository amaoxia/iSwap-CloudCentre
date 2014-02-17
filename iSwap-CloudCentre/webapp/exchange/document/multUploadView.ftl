<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上传文档</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="pop_01" style="width:400px">
  <div class="pop_mian">
  <form name="saveForm" id="saveForm" action="${path}/exchange/document/document!upload.action" method="post"  enctype="multipart/form-data">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                  <li>
                     <p>选择指标项：</p>
                      <span>
                      <select name="itemIds" id="itemId"  onchange="checkFileType(this)" style="width:180px">
                      <option value="">请选择</option>
                       <#list items as data>
						<option value="${data.id}_${data.dataValue}">${data.itemName?default('')}</option>
						</#list>
					  </select>
                      </span>
                    </li>
					<li class="item2_bg">
                      <p>选择要上传的文档：</p>
                      <span>
                      	<input type="file"  size="30"  name="upload" id="upload" onchange="checkFormat(this.value)" />
                      	<input type="hidden"  id="doc"/> 
                      	<p id="lab"></p>
                      </span>
                    </li>
                  </ul>
                </div></td>
            </tr>
          </table></td>
      </tr>
    </table>
    </form>
  </div>
</div>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<!--通用方法-->
<script type="text/javascript">
	function  checkFileType(temp){
	if($(temp).val()!=''){
	 var t=$(temp).attr("value");
	 var ts=t.split("_");
	 $("#doc").val(ts[1]);
	 $("#lab").html("文件格式为:"+ts[1]);
	}else{
	$(temp).val('');
    $("#lab").html('');
  	  return;
	}
}
function checkFormat(filePath) {
			var   i = filePath.lastIndexOf('.');
			var   len = filePath.length;
			var   str = filePath.substring(len,i+1);
			var doc=$("#doc").val();//文件格式
		    var docs=doc.split(",");
		    var uploadName=$("#upload").val();
		    var itemcVal=$("#itemId").val();
				 if(itemcVal==''){
			 		alert("请选择指标后,再上传!");
					 return;
			 }
		   if(uploadName==''||uploadName.length==0){
		    alert("请选择要上传的文件");
		   return;
		  }
		 if(docs.length>=2){
		 if(docs[0]!=str&&docs[1]!=str){
		    alert("请选择正确格式文件上传!");   
		  	return false;   
		 	}
		 }else{
		 	if(doc!=str){
		 	alert("请选择正确格式文件上传!"); 
		 	return false;
		 	}
		 }
		 return true;         
} 
var DG = frameElement.lhgDG; 
DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
//关闭窗口 不做任何操作
function singleCloseWin(){
DG.curWin.closeWindow();
}
DG.addBtn( 'save', '保存', saveWin); 
function saveWin() {
	var itemcVal=$("#itemId").val();
	var workVal=$("#workId").val();
	var workObj=document.getElementById("workId");
	var workName=workObj.options[workObj.selectedIndex].innerText;;
	 if(itemcVal==''){
	 		alert("请选择指标后,在上传!");
		 return;
	 }
	 if(workVal==''){
	 	alert("请选择流程后,在上传文档!");
	 	return;
	 }
	 
	//实现逻辑
	var upload=$("#upload").val();
	if(checkFormat(upload)){
	 $("#workName").attr("value",workName);
	document.forms[0].submit();
	}
}
</script>
</body>
</html>

