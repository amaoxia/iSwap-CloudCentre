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
<script type="text/javascript" src="${path}/cloudnode/mapperMsg/swfobject.js"></script>
<script type="text/javascript" src="${path}/cloudnode/mapperMsg/jquery.uploadify.min.js"></script>
<link href="${path}/cloudnode/mapperMsg/uploadify.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#fileQueue {
	width: 500px;
	height: 180px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>
</head>
<body>
 <div class="pop_01" id="wpageslide" style="width:622px;height:492px;overflow-x:hidden;overflow-y:scroll;">
<div id="wpages" class="wpages">
  <div class="pop_mian wpage">
   <form name="save" id="saveForm" action="${path}/cloudnode/mapperMsg/mapper!multupload.action" enctype="multipart/form-data"  method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td align="center" valign="middle" class=""  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
			<tr>
			  <td  height="100%" valign="top" ><div class="">
				<ul class="item1_c">
					  <li class="item2_bg">
                         <p><b>*</b>所属前置机：</p>
					  <span>
					  <select name="cloudeNode.id" id="cloudNodeInfo" style="width:200px">
						<option value="">--请选择--</option>
						<#list cloudNodeInfoList as cloudNodeInfo>
							<option value="${cloudNodeInfo.id}">${cloudNodeInfo.nodesName?default('')}</option>
						</#list>
						</select>
					  </span>
					  <span> <div id="cloudNodeInfoTip"></div></span>
					</li>
					  <li class="">
                         <p><b>*</b>Mapping模式：</p>
					  <span>
					  <select name="mappingType" style="width:200px" id="mappingType">
						<option value="0">发送</option>
						<option value="1">接收</option>
						</select>
					  </span>
					</li>
					<li class="item2_bg">
                              <p><b>*</b>所属应用：</p>
                              <span>
                              <select name="appMsg.id" id="appMsg" style="width:200px"><option value="">---请选择---</option><#list appMsgList as app><option value="${app.id}">${app.appName?default('')}</option></#list></select>
                              </span>
                               <span> <div id="appMsgTip"></div></span>
					</li>
					<li>
					  <p><b>*</b>所属部门：</p>
					  <span>
					  <input type="text" id="sysDept"  size="30"/>
					   <input type="hidden" name="dept.id" id="deptId"/>
					  </span>
					   <span> <div id="sysDeptTip"></div></span>
					</li>
					<!-- 多文件上传-->
					 <li class="item2_bg">
					  <p><b>*</b>Mapper文件</p>
					  <span>
							<div id="fileQueue">
							</div>
							<input type="file"  name="upload" id="upload" />
							&nbsp;&nbsp;&nbsp;&nbsp;单个文件的最大为10M
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
			  <div class="wpage" id="deptTree" style="margin-left:10px;text-align:left;background:url(${path}/images/common_menu_bg.jpg) #CFE1ED bottom repeat-x" >
			  	<ul id="tree" class="tree" style="height:300px;width:445px;overflow:auto;"></ul>
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
						    function singleCloseWin(){
							var DG = frameElement.lhgDG; 
							DG.curWin.location.reload(); //同时刷新父窗口
							DG.curWin.closeWindow();//关闭窗口
						}
					
					$(document).ready(function(){
						$("div#wpageslide").wPageSilde({width: 622,height:505});
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
							
							
								/*mapper多文件上传*/
								$(function() {
										$('#upload')
												.uploadify({
												'uploader' : '${path}/cloudnode/mapperMsg/uploadify.swf',
												'script' : '${path}/cloudnode/mapperMsg/mapper!multUpload.action',
												'cancelImg' : '${path}/cloudnode/mapperMsg/cancel.png',
												'buttonImg': '${path}/cloudnode/mapperMsg/browse.jpg',   
								'folder' : '/uploads',
								'multi' : true,//支持多文件上传
								'auto' : false,
								'fileDesc':'请选择要上传的数据文件（*.xml）',
								'fileExt':'*.xml',
								'method' : 'GET',
								'queueID' : 'fileQueue',
								'queueSizeLimit' : 10,//一次多少文件
								'simUploadLimit' : 10,//同时上传文件个数
								'sizeLimit' : 10485760,//文件大小
								'fileDataName' : 'fileData',
								'onSelect' : function(event, queueID, fileObj) {
									/*$("#upload").uploadifySettings("scriptData", {
										'fileData.name' : encodeURI(encodeURI(fileObj.name))
									});*/
								},
								onError : function(event, queueId, fileObj, errorObj) {
									alert(fileObj.name + "上传失败!");
								},
								'onQueueFull' : function(event, queueSizeLimit) {
									if (queueSizeLimit > 10) {
										alert("单次上传文件不能超过10个!");
										return;
									}
								},
								'removeCompleted' : false,
								'onSelectOnce' : function(event, data) {
								},
								'onAllComplete' : function(event, data) {
									//setInterval("showResult()", 10000);//十秒后删除显示的上传成功结果
								},
								'onComplete' : function(event, queueId, fileObj, response, data) {
					
								}
							});
						});
						function showResult() {//删除显示的上传成功结果 
							$("#fileQueue").html("");
						}
					
						function saveWin(){
							var mappingType=$("#mappingType").val();
							var cloudeNodeId=$("#cloudNodeInfo").val();
							var appMsgId=$("#appMsg").val();
							var deptId=$("#deptId").val();
							$("#upload").uploadifySettings("scriptData", {
							'mappingType':mappingType,'cloudeNodeId':cloudeNodeId,'appMsgId':appMsgId,'deptId':deptId
						});
							$('#upload').uploadifyUpload();
						}
					</script>
					</body>
					</html>
