<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户修改</title>
		<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
		<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
	</head>
	<body>
		<div class="pop_01" id="wpageslide">
		<div id="wpages" class="wpages">
		  <div class="pop_mian wpage" style="overflow-x:hidden;overflow-y:auto;">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td align="center" valign="middle" class="pm01_c"  height="100%">
		        <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
		            <tr>
		              <td  height="100%" valign="top" >
		                 <form action="${path}/sysmanager/user/user!update.action" method="post" name="saveForm" id="saveForm">
		  				  <input type="hidden" value="${id?default('0')}" name="id" id="id"/>
						  <input type="hidden" name="userDept.deptId"  value="<#if userDept?exists>${userDept.deptId?default('')}</#if>" id="deptId" />
						  <input type="hidden" name="userDept.id" 	   value="<#if userDept?exists>${userDept.id?default('')}</#if>"/>
						  <input type="hidden" name="userDept.userId"     value="${id?default('')}"/>
						  <input type="hidden" name="userRole.id"    	  value="<#if userRole?exists>${userRole.id?default('')}</#if>" />
						  <input type="hidden" name="userRole.userId" 	  value="${id?default('')}" />
		                  <div class="item1">
		                  <ul class="item1_c">
		                    <li>
		                      <p><b>*</b>用户名称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
		                      <span><input type="text" size="30" id="userName"  name="userName" value="${userName?default('')}"/></span>
		                      <span><div id="userNameTip"></div></span>
		                    </li>
		                    <li class="item_bg">
							  <p><b>*</b>性别名称： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
							  <span><input type="radio"   <#if sex=='1'>checked="checked"</#if> name="sex" value="1" />男 &nbsp;&nbsp;&nbsp;&nbsp;
		                            <input type="radio" <#if sex=='0'>checked="checked"</#if> name="sex" value="0" />女</span>
		                      <li>
		                      <p><b>*</b>登录名称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
		                      <span><input type="text" size="30" id="loginName"  name="loginName" value="${loginName?default('')}"/></span>
		                      <span><div id="loginNameTip"></div></span>
		                     <li class="item_bg">
		                      <p><b>*</b>用户标识符：        &nbsp;&nbsp;&nbsp;</p>
		                      <span><input type="text" size="30" id="userUid"  name="userUid" value="${userUid?default('')}"/></span>
		                      <span><div id="userUidTip"></div></span>
		                    </li>
		                           <li>
		                      <p><b>*</b>机构名称：&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		                      <span><input type="text" size="30" id="deptName" readOnly=true value="<#if sysDept?exists>${sysDept.deptName?default('')}</#if>" /></span>
		                      <span><div id="deptNameTip"></div></span>
		                    </li>
		                      <li class="item_bg">
		                           <p><b>*</b>所属角色：&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</p>
		                           <span>
		                            <select   id="roleId"  name="userRole.roleId" style="width:200px">
		                      		<option value="" >请选择</option>
		                      		<#list roles as data>
		                      		<option value="${data.id}" <#if userRole?exists><#if userRole.roleId==data.id >selected</#if></#if>>${data.name?default('')}</option>
		                      		</#list>
		                      		</select>
		                      		</span><span>
		                      		<div id="roleIdTip"></div></span>
		                    </li>
		                    <li>
		                      <p><b>*</b>email：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		                      <span><input type="text" size="30" id="email"  name="email" value="${email?default('')}"/></span>
		                      <span><div id="emailTip"/></div></span>
		                    </li>
		                     <li  class="item_bg">
		                      <p><b>*</b>身份证号： &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;</p>
		                      <span><input type="text" size="30" id="idCardCode"  name="idCardCode" value="${idCardCode?default('')}"/></span>
		                      <span><div id="idCardCodeTip"></div></span>
		                    </li>
		                     <li>
		                      <p><b>*</b>手机号码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		                      <span><input type="text" size="30" id="mobile"  name="mobile" value="${mobile?default('')}"/></span>
		                      <span><div id="mobileTip"></div></span>
		                    </li>
		                     <li  class="item_bg">
		                      <p>联系电话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		                      <span><input type="text" size="30" id="phone"  name="phone" value="${phone?default('')}"/></span>
		                      <span><div id="phoneTip"/></div></span>
		                    </li> 
		                      <li>
		                      <p>联系地址：</p>
		                      <span><textarea cols="60" rows="2" name="address" id="address">${address?default('')}</textarea></span>
		                      <span><div id="addressTip"></div></span>
		                    </li>
		                     <li  class="item_bg">
		                      <p>用户描述：</p>
		                      <span><textarea cols="60" rows="4" name="remark" id="remark">${remark?default('')}</textarea></span>
		                      <span><div id="remarkTip"></div></span>
		                     </li>
		                  	</ul>
		                      </div>
		                      </form>
		                  </td>
		            </tr>
		          </table></td>
		      </tr>
		    </table>
		    </div>
		  <div class="wpage" id="deptTree" style="margin-left:0px;text-align:left;background:url(${path}/images/common_menu_bg.jpg) #CFE1ED bottom repeat-x" >
		  	  <ul id="tree" class="tree" style="height:450px;width:627px;overflow:auto;"></ul>
		  </div>
		 </div>
			<ul id="wpagebtns" class="wpagebtns">
			<li></li>
			<li></li>
		</ul>
		</div>
		<#include "/common/commonSilde.ftl">
		<#include "/common/commonValidator.ftl">
		<#include "/common/commonUd.ftl">
		<script type='text/javascript' src='${path}/js/validator/system/userValidator.js'></script> 
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
			$("div#wpageslide").wPageSilde({width: 638,height:460});
			$("#loginName").defaultPassed();
			$("#userUid").defaultPassed();
			$("#email").defaultPassed();
		});
		 function commonBtn(){
			 DG.removeBtn('back');
			 DG.removeBtn('sel');
			 $("#wpagebtns li").eq(0).click();//返回
			 DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
			 DG.addBtn( 'reset', '重填', resetWin); 
			 DG.addBtn( 'save', '保存', saveWin); 
		}
		$("#deptName").bind('click', function(event) {
			var node =zTree.getNodeByParam("id", $("#deptId").val());
			zTree.selectNode(node);
			$("#wpagebtns li").eq(1).click();
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
		});
		function  saveClick(){
				var selectedNode = zTree.getSelectedNode();
				if(selectedNode==null){
					zTree.cancelSelectedNode();
					alert("请选择其它节点");
					return;
				}
				if(selectedNode.id==-1){
					zTree.cancelSelectedNode();
					alert("请选择其它节点!");
					return;
				}
		         $("#deptId").attr("value",selectedNode.id);
		         $("#deptName").attr("value",selectedNode.name).defaultPassed();
		          commonBtn();
		}
			//大写转换
	    	$('#userUid').keypress(function(e) {     
		        var keyCode= event.keyCode;  
		        var realkey = String.fromCharCode(keyCode).toUpperCase();  
		        $(this).val($(this).val()+realkey);  
		        event.returnValue =false;  
	   	    });
		</script>
	</body>
</html>
