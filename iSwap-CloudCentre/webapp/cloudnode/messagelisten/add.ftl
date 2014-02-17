<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MQ数据源接与入定义新增</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">

</head>
<body>
<form name="save" id="saveForm" method="post" action="${path}/cloudnode/messagelisten/messagelisten!add.action?deptId=${deptId?default('')}"/>
 	 <@s.token name="token"/>
 	 <input type="hidden" id="deptId" name="sysDept.id" value="${deptId?default('')}"/>
<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
  <div class="main">
    <#-- 
    <div class="main_c2" >
    	<br/>指定交换流程：<br />
      <div class="left_tree_com" style="width:250px;">
        <span>
        <p>
          <div scrolling="Auto" frameborder="0" style="border:0px;height:423px;width:250px;background:url(${path}/images/common_menu_bg.jpg) #CFE1ED bottom repeat-x" class="wpage" >
          	<ul id="tree" class="tree" style="height:410px;width:240px;overflow:auto;"></ul>
          </div>
        </p>
        </span> </div>
      <div class="right_main">
        <div class="loayt_01 rlist100">
          <div class="loayt_top"> <span class="loayt_tilte"><b>消息监听信息 </b></span><span class="loayt_right"><img src="${path}/images/kuang_right.png" width="10" height="31" /></span> </div>
          <div class="loayt_mian">
          -->
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center" valign="middle" class="loayt_cm"  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
                    <tr>
                      <td  height="100%" valign="top" ><div>
                          <ul class="item1_c">
                            <li>
                              <p><b>*</b>监听名称：</p>
                              <span>
                              <input type="text" size="30" name="listenName" id="listenName"/>
                              <#-- <input type="hidden" name="workFlow.id" id="workFlowId"/>-->
                              </span> 
                              <span> <div id="listenNameTip"></div></span>
                              </li>
							 <li class="item2_bg">
                              <p><b>*</b>消息管理名称：</p>
                              <span>
                              <select id="jmsServerInfo" name="jmsServerInfo.id" style="width:200px"><option value="">---请选择---</option><#list jmsServerList as jmsServer ><option value="${jmsServer.id}">${jmsServer.jmsServerName}</option></#list></select>
                              </span>
                              <span> <div id="jmsServerInfoTip"></div></span>
							</li>
							<li>
                              <p><b>*</b>队名名称：</p>
                              <span>
                              <input type="text" size="30" name="queueName" id="queueName"/>
                              </span> 
                              <span> <div id="queueNameTip"></div></span>
                              </li>
                              <#-- 
							<li  class="item2_bg">
                              <p><b>*</b>调度频率：</p>
                              <span>
                              <select name="times" id="times">
                              <option value="">--请选择--</option>
                              <#list 1..15 as i>
                              <option value="${i}">${i}</option>
                              </#list>
                              </select>单位(秒)
                              </span>
                               <span> <div id="timesTip"></div></span>
							</li>
							-->
							<li class="item2_bg">
                              <p>描述：</p>
                              <span>
                              <textarea  name="notes" id="notes" cols="42" rows="3"></textarea>
                              </span>
                                <span> <div id="notesTip"></div></span>
							</li>
                          </ul>
                        </div></td>
                    </tr>
                  </table></td>
              </tr>
            </table>
          </div>
        </div>
        <div class=" clear"></div>
        <#-- 
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="clear"></div>
</div>
-->
</form>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<!--增删查改-->
<#include "/common/commonUd.ftl">
 <!--验证js-->
<#include "/common/commonValidator.ftl">
<!--Ztree-->
<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
<script type='text/javascript' src='${path}/js/validator/cloudnode/messagelistenValidator.js'></script> 
<script type="text/javascript">
	var zTree;//树
	var setting;//参数设置
	var zTreeNodes = [] ;//数据
	setting={
	callback : {
		beforeChange: zTreeBeforeChange,
		change: zTreeOnChange,
	  beforeClick: zTreeBeforeClick
	},
	async : true,//异步加载 
	asyncUrl: "${path}/cloudnode/workflow/workflow!getWorkFlowByDeptId.action?deptId=${deptId?default('')}",//数据文件 
	showLine:true,
	isSimpleData:true,
	treeNodeKey:"id",
	treeNodeParentKey:"pid",
	checkable : true,
	checkStyle : "radio",
	checkRadioType : "all"
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
		    
		    
	function zTreeBeforeChange(treeId, treeNode) {
		 if(treeNode.id==-1){
		 	 alert("请选择其他节点!");
			 return false;	
		 }
	}
	function zTreeOnChange(event, treeId, treeNode) {
		$("#workFlowId").val(treeNode.id);
	}
</script>
</body>
</html>
