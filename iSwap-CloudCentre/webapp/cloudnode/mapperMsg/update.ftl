<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
</head>
<body>
 <div class="pop_01" id="wpageslide" style="width:622px;height:510px;overflow-x:hidden;overflow-y:scroll;">
<div id="wpages" class="wpages">
  <div class="pop_mian wpage">
   <form name="save" id="saveForm" action="${request.getContextPath()}/cloudnode/mapperMsg/mapper!update.action" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td align="center" valign="middle" class=""  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
			<tr>
			  <td  height="100%" valign="top" ><div class="">
				<ul class="item1_c">
					<li>
					  <p><b>*</b>Mapper中文名称：</p>
					  <span>
					  ${mapName?default('')},${id}
					  <input type="hidden" name="id" value="${id}">
					  </span> 
					 <span> <div id="mapNameTip"></div></span>
					</li>
					<li class="item2_bg">
					  <p><b>*</b>Mapper英文名称：</p>
					  <span>
					  ${mapCode?default('')}
					  </span> 
					   <span> <div id="mapCodeTip"></div></span>
					  </li>
					  <li class="">
                         <p><b>*</b>所属前置机：</p>
					  <span>
					  <select name="cloudeNode.id" id="cloudNodeInfo" style="width:200px">
					  <option value="">--请选择--</option>
						<#list cloudNodeInfoList as cloudNodeInfo>
							<option value="${cloudNodeInfo.id}" <#if cloudNodeInfo.id==cloudeNode.id>selected</#if> >${cloudNodeInfo.nodesName?default('')}</option>
						</#list>
						</select>
					  </span>
					  <span> <div id="cloudNodeInfoTip"></div></span>
					</li>
					  <li class="">
                         <p><b>*</b>Mapping模式：</p>
					  <span>
					  <select name="mappingType" style="width:200px">
					 	 <option value="">--请选择--</option>
						<option value="0" <#if mappingType=='0'>selected</#if> >发送</option>
						<option value="1" <#if mappingType=='1'>selected</#if> >接收</option>
						</select>
					  </span>
					</li>
					<li class="item2_bg">
                              <p><b>*</b>所属应用：</p>
                              <span>
                              <select name="appMsg.id" id="appMsg" style="width:200px"><option value="">--请选择--</option><#list appMsgList as app><option value="${app.id}" <#if app.id==appMsg.id>selected</#if> >${app.appName?default('')}</option></#list></select>
                              </span>
                               <span> <div id="appMsgTip"></div></span>
					</li>
					<li>
					  <p><b>*</b>所属部门：</p>
					  <span>
					  <input type="text" id="sysDept"  size="30" value="${dept.deptName}"/>
					   <input type="hidden" name="dept.id" id="deptId" value="${dept.id}"/>
					  </span>
					   <span> <div id="sysDeptTip"></div></span>
					</li>
					<li class="">
					  <p><b>*</b>Mapper文件</p>
					  <span>
					  <textarea name="contents" id="contents" cols="60" rows="7">${contents?default('')}</textarea>
					  </span>
					  <span> <div id="contentsTip">{}</div></span>
					</li>
				  </ul>

				</div></td>
			</tr>
		  </table></td>
	  </tr>
	</table>
</form>
</div>
  <div class="wpage" id="deptTree" style="margin-left:10px;text-align:left;background:url(${path}/images/common_menu_bg.jpg) #CFE1ED bottom repeat-x" >
  	<ul id="tree" class="tree" style="height:510px;width:622px;overflow:auto;"></ul>
  </div>
 </div>
	<ul id="wpagebtns" class="wpagebtns">
	<li></li>
	<li></li>
</ul>
</div>
<!--窗体滑动-->
<#include "/common/commonSilde.ftl">
<!--增删查改-->
<#include "/common/commonUd.ftl">
<!--验证js-->
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/cloudnode/mappingValidator.js'></script> 

<script type="text/javascript"> 
$(document).ready(function(){
	$("div#wpageslide").wPageSilde({width: 622,height:510});
});
 
$("#sysDept").bind('click', function(event) {
	$("#wpagebtns li").eq(1).click();
	//取消按钮
	DG.removeBtn('reset');
	DG.removeBtn('save');
	DG.removeBtn('close');
	//添加按钮
	DG.addBtn( 'sel', '确定', sel); 
	function sel() {
	 saveClick();
	 commonBtn();
}
	DG.addBtn( 'back', '返回', back); 
	function back() {
	commonBtn();
}
function commonBtn(){
	 DG.removeBtn('back');
	 DG.removeBtn('sel');
	 $("#wpagebtns li").eq(0).click();//返回
	 DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
	 DG.addBtn( 'reset', '重填', resetWin); 
	 DG.addBtn( 'save', '保存', saveWin); 
}
});
function  saveClick(){
		var selectedNode = zTree.getSelectedNode();
		if(selectedNode==null){
			alert("请选择其它节点");
			return;
		}
		if(selectedNode.id==-1){
			alert("请选择其它节点!");
			zTree.cancelSelectedNode();
			return;
		}
         $("#deptId").attr("value",selectedNode.id);
         $("#sysDept").attr("value",selectedNode.name).defaultPassed();
}
	var zTree;//树
	var setting;//参数设置
	var zTreeNodes = [] ;//数据
    setting={
    callback : {
      beforeClick: zTreeBeforeClick
    },
    async : true,//异步加载 
    asyncUrl: "${path}/sysmanager/dept/dept!getDeptTree.action",//数据文件 
    showLine:true,
    isSimpleData:true,
    treeNodeKey:"id",
    treeNodeParentKey:"pid"
    };		
       function loadTree(){
   		   zTree=$("#tree").zTree(setting,zTreeNodes);
		}
        $(document).ready(function(){
			loadTree();//载入树
    });
    //点击节点之前的操作
    function zTreeBeforeClick(treeId, treeNode){
    	var id=treeNode.id;
	     	if(id==-1){
	     		alert("请选择其他节点!");
	     		zTree.cancelSelectedNode();
	     	 return;
	     	}
    }
</script>
</body>
</html>
