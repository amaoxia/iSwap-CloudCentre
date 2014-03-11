<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
	</head>
	<body>
		
			<div class="pop_01" id="wpageslide" style="width:600px;height:640px;overflow-x:hidden;">
			<div id="wpages" class="wpages">
			  <div class="pop_mian wpage">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td align="center" valign="middle" class="pm01_c"  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
						<tr>
						  <td  height="100%" valign="top" >
						  <div>
						  <form action="${path}/cloudcenter/cloudNodeInfo/cloudNodeInfo!update.action" method="post" name="saveForm" id="saveForm">
							<input name="id" value="${id}" type="hidden" id="id"/>
							  <ul class="item2_c">
								<li>
								  <p>前置机名称：</p>
								  <span>
								  <input type="text" name="nodesName" id="nodesName" value="${nodesName?default('')}" size="30"/>
								  </span>
								  <span><div id='nodesNameTip'></div></span>
								  </li>
								  <li class="item2_bg" style="display:none;">
								  <p>前置机编码：</p>
								  <span><input type="text" name="code" id="code" size="30" value="${code?default('')}"/></span>
								  <span><div id='codeTip'></div></span>
								</li>
								<li class="item2_bg">
								  <p>IP地址：</p>
								  <span><input type="text" name="address" id="address" value="${address?default('')}" size="30"/></span>
								  <span><div id='addressTip'></div></span>
								</li>
								<li >
								  <p>访问端口：</p>
								  <span>
								  <input type="text" name="port" id="port" size="30"  value="${port?default('')}"/>
								  </span>
								  <span><div id='portTip'></div></span>
								</li>
								<li class="item2_bg">
								  <p>所属部门：</p>
								 <span>
								  <textarea  name="deptNames" id="deptNames" cols="42" rows="3" readOnly=true >${sysDeptNames?default('')}</textarea>
								 <input name="deptIds" id="deptIds" value="${sysDeptIds}" type='hidden'/>
								 </span>
								 <span><div id='deptNamesTip'></div></span>
								</li>
								<#-- 
								<li>
								  <p>所属应用：</p>
								  <span>
								   <select name="appMsgIds" id="appMsgIds" multiple style="width:280px;">
								   	<#list appMsgs as entity>  
									 <option value="${entity.id}" <#if contain(appMsgIds?default(""),entity.id)=='true'>selected</#if>>${entity.appName?default('')}</option>
									</#list>
			                       </select>
								  </span>
								  <span><div id='appMsgIdsTip'></div></span>
								</li>	
								-->
								<li class="item2_bg">
								  <p>描述</p>
								  <span>
								  <textarea  name="remark" id="remark" cols="42" rows="3">${remark?default("")}</textarea>
								  </span>
								  <span><div id='remarkTip'></div></span>
								</li>
							  </ul>
							  </form>
							</div></td>
						</tr>
					  </table></td>
				  </tr>
				</table>
			
			  </div>
				  <div class="wpage"  style="margin-left:0px;text-align:left;background:url(${path}/images/common_menu_bg.jpg) #CFE1ED bottom repeat-x" >
				 	 <ul id="deptTree" class="tree" style="width:567px;height:500px;overflow:auto;"></ul>
				  </div>
				 </div>
				<ul id="wpagebtns" class="wpagebtns">
					<li></li>
					<li></li>
				</ul>
			</div>
		<script src="${path}/js/jquery-1.5.1.js"></script>
		<script src="${path}/js/validator/cloudnode/cloudnodeinfo.js"></script>
		<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
		<#include "/common/commonSilde.ftl">
		 <!--验证js-->
		 <#include "/common/commonValidator.ftl">
		<#include "/common/commonUd.ftl">
		
		<#function contain str id>
			<#assign ret='false'>
			<#list str?split(",") as tid>
				<#if tid?number==id>
					 <#assign ret='true'>
				</#if>
			</#list>
			<#return ret>
		</#function>
		
		<script type="text/javascript">
		function  getDeptId(){
		return document.getElementById('deptIds');
		}
		function getDeptName(){
			return document.getElementById('deptNames');
		}
		$(document).ready(function(){
			$("#nodesName").defaultPassed();
			$("#port").defaultPassed();
			$("#address").defaultPassed();
			$("div#wpageslide").wPageSilde({width: 575,height:510});
		});
		$("#deptNames").bind('click', function(event) {
			$("#wpagebtns li").eq(1).click();
			//取消按钮
			DG.removeBtn('reset');
			DG.removeBtn('save');
			DG.removeBtn('close');
				//添加按钮
			DG.addBtn( 'sel', '确定', sel); 
			function sel() {
			 if(saveClick()){
				 commonBtn();
			 }
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
							var zTree;//树
							var setting;//参数设置
							var zTreeNodes = [] ;//数据
						    setting={
						    async : true,//异步加载 
						    asyncUrl: "${path}/cloudcenter/cloudNodeInfo/cloudNodeInfo!getDeptTree.action?id=${id?default('0')}", 
						    showLine:true,
						    open:true,
						    checkable:true,
						    isSimpleData:true,
						    treeNodeKey:"id",
						    checkType :{"Y":'s', "N":'s'},//父子影响
						    treeNodeParentKey:"pid"
						    };		
					       function loadTree(){
			           		   zTree=$("#deptTree").zTree(setting,zTreeNodes);
			    			}
			    			function saveClick(){
			    			var fa=true;
			    			var deptNames="";
						   	var deptIds="";
						   	var len= zTree.getCheckedNodes(true);
						   	 if(len.length==0){
						   		 fa= false;
						   		 alert("请选中节点!");
						   		 return fa;
						   	 }
						   	 if(len.length==1){
						   	 	if($(len[0]).attr("id")==-1){
						   	 	alert("根节点不参与分配!");
						   	 	fa=false;
						   	 	return fa;
						   	 	}
						   	 }
							for(var i=0;i<len.length;i++){
								var id=$(len[i]).attr("id");
								var value=$(len[i]).attr("name");
								 if(id!=-1){
								 deptIds+=id+",";
								 deptNames+=value+",";
								 }
							}
							$("#deptIds").attr("value",deptIds);
							$("#deptNames").attr("value",deptNames);
							return fa;
					    }
					        $(document).ready(function(){
								loadTree();//载入树
					    });
		</script>
	</body>
</html>
