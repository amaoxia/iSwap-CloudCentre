<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户添加</title>
		<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
		<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
	</head>
	<body >
		<div id="wpageslide">
			<div id="wpages" class="wpages">
			  <div class="pop_mian wpage" style="overflow-x:hidden;overflow-y:auto;">
			    <table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td align="center" valign="middle" class="pm01_c"  height="100%">
			        <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
			            <tr>
			              <td  height="100%" valign="top" >
			              <div class="item1">
			                <form action="${path}/sysmanager/user/user!add.action" method="post" name="saveForm" id="saveForm">
			                <@s.token name="token"/>
							<input type="hidden" name="userDept.deptId" id="deptId"/>
							<input type="hidden"  id="id" value="0"/>
			                  <ul class="item1_c">
			                    <li>
			                      <p> <b>*</b>用户名称：</p>
			                      <span><input type="text" size="30" id="userName"  name="userName"  /></span>
			                      <span><div id="userNameTip"></div></span>
			                    </li>
			                    <li class="item_bg">
								  <p><b>*</b>性别名称：</p>
								   <span><input type="radio" checked="checked" name="sex" value="1" />男 &nbsp;&nbsp;&nbsp;&nbsp;
			                         <input type="radio"  name="sex" value="0" />女</span>
			                    </li>
			                      <li>
			                      <p><b>*</b>登录名称：</p>
			                      <span><input type="text" size="30" id="loginName"  name="loginName" maxLength="20"/></span>
			                      <span><div id="loginNameTip" ></div></span>
			                    </li>
			                       <li  class="item_bg">
			                      <p><b>*</b>登录密码：</p>
			                      <span><input type="password" size="31" id="password"  name="password"/></span>
			                      <span><div id="passwordTip" ></div></span>
			                    </li>
			                       <li>
			                      <p><b>*</b>再次输入密码：</p>
			                      <span><input type="password" size="31" id="password2"  /></span>
			                      <span><div id="password2Tip" ></div></span>
			                    </li>
			                     <li class="item_bg">
			                      <p><b>*</b>用户标识符： </p>
			                      <span><input type="text" size="30" id="userUid"  name="userUid"/></span>
			                      <span><div id="userUidTip" ></div></span>
			                    </li>
			                      <li>
			                      <p><b>*</b>机构名称：&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			                      <span><input type="text" size="30" id="deptName" readOnly=true value="${deptName?default('')}" /></span>
			                      <span><div id="deptNameTip" ></div></span> 
			                    </li>
			                      <li class="item_bg">
			                      <p><b>*</b>所属角色：&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</p>
			                     		 <span>
			                            <select   id="roleId"  name="userRole.roleId" style="width:200px">
			                      		<option value="" >请选择</option>
			                      		<#list roles as data>
			                      		<option value="${data.id}">${data.name?default('')}</option>
			                      		</#list>
			                      		</select>
			                      		</span>
			                      		<span><div id="roleIdTip" ></div></span>
			                      
			                    </li>
			                    <li>
			                      <p><b>*</b>email：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			                      <span><input type="text" size="30" id="email"  name="email"/></span>
			                      <span><div id="emailTip" ></div></span>
			                    </li>
			                     <li class="item_bg">
			                      <p><b>*</b>身份证号： &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
			                      <span><input type="text" size="30" id="idCardCode"  name="idCardCode"/></span>
			                      <span><div id="idCardCodeTip" ></div></span>
			                    </li>
			                     <li>
			                      <p><b>*</b>手机号码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			                      <span><input type="text" size="30" id="mobile"  name="mobile"/></span>
			                      <span><div id="mobileTip" ></div></span>
			                    </li>
			                     <li  class="item_bg">
			                      <p>联系电话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			                      <span><input type="text" size="30" id="phone"  name="phone"/></span>
			                      <span><div id="phoneTip" ></div></span>
			                    </li> 
			                      <li>
			                      <p>联系地址:<label id="textCount">(可输入200字)</label>：</p>
			                      <span><textarea cols="50" rows="4" name="address" id="address"  onkeyPress="checkLength('address','textCount','200')" onpaste="checkLength('address','textCount','200')" ></textarea></span>
			                      <span><div id="addressTip" ></div></span>
			                    </li>
			                     <li class="item_bg">
			                      <p>用户描述:<label  id="textCount1">(可输入200字)</label>：</p>
			                      <span> <textarea cols="50" rows="4" name="remark" id="remark" onkeyPress="checkLength('remark','textCount1','200')" onpaste="checkLength('remark','textCount1','200')"></textarea></span>
			                      <span><div id="remarkTip" ></div></span>
			                      </li>
			                  </ul>
			                   </form>
			                  </div>
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
