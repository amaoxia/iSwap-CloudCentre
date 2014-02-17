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
<div class="pop_01">
  <div class="pop_mian">
  <form name="saveForm" id="saveForm" action="${path}/exchange/template/template!upload.action" method="post"  enctype="multipart/form-data">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                  	<li>
                      <p class="pop_notice">注意：为防止数据通过模板泄密，要求模板内只能有一条示意性数据！</p>
                      	<input type="hidden" name="id" value="${id}"/>
                      	<inpu type="hidden" id="doc" value="${entityobj.changeItem.dataValue?default('')}"/>
                    </li>
                  	<li>
                      <p>指标项名称：
                      <span>
                       ${entityobj.changeItem.itemName?default('')}
                      </span>
                     </p>
                    </li>
                    <li class="item2_bg">
                    <span>
                      <p>指标项编码：${entityobj.changeItem.itemCode?default('')}</span></p>
                    </li>
                    <li>
                      <p>所属部门：<span>${entityobj.changeItem.sysDept.deptName?default('')}</span></p>
                    </li>
					<li class="item2_bg">
                      <p>选择要上传的模板：</p>
                      <span>
                      <input type="file" size="30"  name="upload" id="upload" onchange="checkFormat(this.value)"/>(文件格式:${entityobj.changeItem.dataValue?default('')}) 
                      </span>
                      </p>
                    </li>
                  </ul>
                </div></td>
            </tr>
          </table>
          </td>
      </tr>
    </table>
    </form>
  </div>
</div>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>	
<!--通用方法-->
<script type="text/javascript">
function checkFormat(filePath) {
 var   i = filePath.lastIndexOf('.');
 var   len = filePath.length;
 var   str = filePath.substring(len,i+1);
 var doc=$("#doc").val();//文件格式
 var docs=doc.split(",");
 if(docs.length>=2){
 if(docs[0]!=str&&docs[1]!=str){
   alert("请选择正确格式文件!");   
  	return false;   
 	}
 }else{
 	if(doc!=str){
 	alert("请选择正确格式文件!"); 
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
	//实现逻辑
	var upload=$("#upload").val();
	if(checkFormat(upload)){
	document.forms[0].submit();
	}
}
</script>
</body>
</html>

