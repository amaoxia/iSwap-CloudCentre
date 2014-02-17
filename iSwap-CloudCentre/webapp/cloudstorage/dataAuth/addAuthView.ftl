<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<!-- 重置表单-->
<script type="text/javascript" src="${path}/js/jquery.js"></script>	
<!-- 重置表单-->
<script type="text/javascript" src="${path}/js/reset.js"></script>	
 <!-- 窗体公共方法-->
<!-- 验证文本域长度-->
<script type='text/javascript' src="${path}/js/areaLen.js"></script>
<!--日期控件-->
<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
<script type='text/javascript'>
var DG = frameElement.lhgDG; 
DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
//关闭窗口 不做任何操作
function singleCloseWin(){
DG.cancel();
}
DG.addBtn( 'cancleCheckAll', '全不选', cancleCheckAll); 
function cancleCheckAll() {
	var ids=$("input[type='checkbox']");
	for(i=0;i<ids.length;i++){
	   $(ids[i]).attr("checked",false)
	}
}

DG.addBtn( 'checkAll', '全选', checkAll); 
function checkAll() {
	var ids=$("input[type='checkbox']");
	for(i=0;i<ids.length;i++){
	   $(ids[i]).attr("checked",true)
	}
}
DG.addBtn( 'save', '保存', saveWin); 
function saveWin() {
	//实现逻辑
	isSub();
}

function isSub(){
	/*if($("input[type='checkbox']").attr("checked")==false){
		alert("请选择要授权的字段!");
		return;
	}*/
	var str="";
	var ids=$("input[type='checkbox']");
	for(i=0;i<ids.length;i++){
	   if($(ids[i]).attr("checked")==false){
	   	str+=$(ids[i]).attr("value")+",";
	   }
	}
   $("#noIds").attr("value",str);
   document.forms[0].submit();
}
</script>
</head>
<body>
<form nam="saveForm" id="saveForm" method="post" action="${path}/cloudstorage/dataAuth/dataAuth!addAuth.action?id=${id}">
<div class="pop_01"  style="width:730px;height:492px;overflow-x:hidden;overflow-y:scroll;">
  <div class="pop_mian">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                       <table class="tabs_list">
                       <#assign count=6>
                       <#assign pNum=1>
                       <#if metaAuthList?size lt count>
                       <#list metaAuthList as entity>
                       		<#if entity_index==0>
				                <tr>
				                </#if>
				                  <td style="text-align:left">
				                  <a href="#">
				                  <input type="checkbox"   value="${entity.id}" name="ids" <#if entity.dataAuthState?exists><#if entity.dataAuthState=="1">checked</#if></#if>/>&nbsp;&nbsp;${entity.tableInfo.name?default('')}
				                  </a>
				                  </td>
				                   <#if entity_index==metaAuthList?size-1></tr></#if>
				                </#list>
				                <#else><!-- 多行-->
				                			<#list metaAuthList as entity>
					                       		<#if  (pNum-1)*count==entity_index>
									                <tr>
									                </#if>
									                  <td style="text-align:left">
									                  <a href="#">
									                  <input type="checkbox"   value="${entity.id}" name="ids" <#if entity.dataAuthState?exists><#if entity.dataAuthState=="1">checked</#if></#if>/>&nbsp;&nbsp;${entity.tableInfo.name?default('')}
									                  </a>
									                  </td>
									                  <#if (entity_index+1)==metaAuthList?size>
									                  </tr>
									                  <#else>
									                  <#if (entity_index+1)==pNum*count></tr><#assign pNum=pNum+1></#if>
									                  </#if>
									                </#list>
                      			</#if>
                      			<input type="hidden" value=""  name="noIds" id="noIds"/>
				        </table>
                    </li>
                  </ul>
                </div></td>
            </tr>
          </table>
          </form>
          </td>
      </tr>
      <tr>
      </tr>
    </table>
  </div>
</div>
</body>
</html>

