<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>指标管理</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
<head>
<body>
<div class="pop_01" id="wpageslide" style="width:622px;height:492px;overflow-x:hidden;overflow-y:scroll;">
<div id="wpages" class="wpages">
  <div class="pop_mian wpage">
<form method="post" action="${path}/cloudstorage/metadata/metadata!update.action" id="saveForm">
<input type="hidden" name="id" value="${id}"  id="id"/>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p><b>*</b>指标名称:</p>
                      <span>
                      <input type="text" size="30"  name="targetName" id="targetName" value="${targetName?default('')}"/>
                      </span>
                        <span> <div id="targetNameTip"></div></span>
                    </li>
                    <li class="item_bg">
                      <p><b>*</b>所属部门:</p>
                      <span>
                 			  <input type="text" id="deptName" size="30" readOnly="true"  value="<#if sysDept?exists>${sysDept.deptName?default('')}</#if>"/> 
                 			  <input type="hidden" id="deptId"  name="sysDept.id"  value="<#if sysDept?exists>${sysDept.id?default('')}</#if>"/> 
                 			  <input type="hidden"   name="type"  value="${type?default('')}"/> 
                      </span>
                        <span> <div id="deptNameTip"></div></span>
                    </li>
                    <li >
                      <p><b>*</b>数据源:</p>
                      <span>
                      <select name="couldDataSource.id" id="dataSourceName" style="width:200px">
						<option value="">请选择</option>
						<#list dataSources as data>
						<option value="${data.id}" <#if couldDataSource?exists><#if couldDataSource.id==data.id>selected</#if></#if>>${data.sourceName?default('')}</option>
						</#list>
                      </select>
                 	  </span>
                 	    <span> <div id="dataSourceNameTip"></div></span>
                 	  </li>
                 	    <#if type=='3'><!-- 主题库管理-->
                 	   <li   class="item_bg">
                      <p><b>*</b>应用服务:</p>
                      <span>
                      <select name="appMgName" multiple style="width:200px;" id="appMgName">
                       <#list appMsgs as data>
                       <#assign isTrue="">
                       <#list metaApp as app>
                       <#if app.appMsg.id==data.id><#assign isTrue="selected"><#break></#if>
                       </#list>
                       <option value="${data.id}" ${isTrue}>${data.appName?default('')}</option>
						</#list>
                       </select>
                 	  </span>
                 	   <span> <div id="appMgNameTip"></div></span>
                 	  </li>
                 	  </#if>
                 	  	<#if type=='2'><!-- 基础库管理 -->
                 	  <li  class="item_bg">
                      <p><b>*</b>基础库类型名称:</p>
                      <span>
                       <select name="metaDataBasicType.id"  style="width:200px;" id="metaDataBasicTypeId">
                       <option value="">请选择</option>
                       <#list basics as data>
						 <option value="${data.id}" <#if data.id==metaDataBasicType.id>selected</#if>>${data.basicTypeName?default('')}</option>
						</#list>
                       </select>
                 	  </span>
                 	  <span> <div id="metaDataBasicTypeIdTip"></div></span>
                 	  </li>
                 	  </#if>
                    <li <#if type=='1'>class="item_bg"</#if>>
                      <p><b>*</b>表名:</p>
                      <span>
                      <input type="text" name="tableName" id="tableName"  size="30" value="${tableName?default('')}"/></span>
                       <span> <div id="tableNameTip"></div></span>
                      </li>
                      <li   <#if type!='1'>class="item_bg"</#if>>
                      <p><b>*</b>指标负责人:</p>
                      <span>
                      <input type="text" name="overPeople" id="overPeople"  size="30" value="${overPeople?default('')}"/></span>
                       <span> <div id="overPeopleTip"></div></span>
                      </li>
                      <li>
                      <p><b>*</b>负责人邮件地址:</p>
                      <span>
                      <input type="text" name="overPeopleEmail" id="overPeopleEmail"  size="30" value="${overPeopleEmail?default('')}"/></span>
                       <span> <div id="overPeopleEmailTip"></div></span>
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
  	<ul id="tree" class="tree" style="height:300px;width:445px;overflow:auto;"></ul>
  </div>
 </div>
	<ul id="wpagebtns" class="wpagebtns">
	<li></li>
	<li></li>
</ul>
</div>
<script type='text/javascript' src='${path}/js/validator/metaData/metaData.js'></script> 
<#include "/common/commonSilde.ftl">
<#include "/common/commonUd.ftl">
<#include "/common/commonValidator.ftl">
			<script type="text/javascript">
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
<script type="text/javascript"> 
 $(document).ready(function(){
	$("div#wpageslide").wPageSilde({width: 622,height:<#if type=='1'>350<#elseif type=='2'>390<#else>440</#if>});
	$("#overPeopleEmail").defaultPassed();
});
$("#deptName").bind('click', function(event) {
	$("#wpagebtns li").eq(1).click();
	var node =zTree.getNodeByParam("id", $("#deptId").val());
	zTree.selectNode(node);
	//取消按钮
	DG.removeBtn('reset');
	DG.removeBtn('save');
	DG.removeBtn('close');
	//添加按钮
	DG.addBtn( 'sel', '确定', sel); 
	function sel() {
	 saveClick();
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
         $("#deptName").attr("value",selectedNode.name).defaultPassed();
          commonBtn();
}
</script>
</body>
</html>