<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>FTP数据源接与入定义</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">

</head>
<body>
<form name="save" id="saveForm" method="post" action="${path}/cloudnode/ftplisten/ftplisten!add.action?deptId=${deptId?default('')}" id="saveForm"/>
<@s.token name="token"/>
<input type="hidden" id="deptId" name="sysDept.id" value="${deptId?default('')}"/>
<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
  <div class="main">
    <#-- <div class="main_c2" >-->
    <#-- 
    	<br/>指定业务流程：<br />
      <div class="left_tree_com" style="width:250px;">
        <span>
        <p>
          <div scrolling="Auto" frameborder="0" style="border:0px;height:423px;width:250px;background:url(${path}/images/common_menu_bg.jpg) #CFE1ED bottom repeat-x" class="wpage" >
          	<ul id="tree" class="tree" style="height:410px;width:240px;overflow:auto;"></ul>
          </div>
        </p>
        </span> </div>
        -->
     <#-- <div class="right_main">-->
        <#--<div class="loayt_01 rlist100">
          <div class="loayt_top"> 
          <span class="loayt_tilte"><b>新建目录监听信息 </b></span><span class="loayt_right"><img src="${path}/images/kuang_right.png" width="10" height="31" /></span> </div>
          <div class="loayt_mian">
          -->
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center" valign="middle" class="loayt_cm"  height="100%" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
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
                              <p><b>*</b>FTPServer：</p>
                              <span>
                              <select name="ftpServerInfo.id" id="ftpServerInfo"><option value="">---请选择---</option><#list ftpServerList as ftpServer><option value="${ftpServer.id}">${ftpServer.ftpServerName}</option></#list></select>
                              </span>
                               <span> <div id="ftpServerInfoTip"></div></span>
							</li>
							<li class="">
                              <p><b>*</b>文件路径：</p>
                              <span>
                              <input type="text" size="30" name="filePath" id="filePath"/>
                              </span>
                               <span> <div id="filePathTip"></div></span>
							</li>
							<li class="item2_bg">
                              <p><b>*</b>文件名称：</p>
                              <span>
                              <input type="text" size="30" name="fileName" id="fileName"/>
                              </span>
                               <span> <div id="fileNameTip"></div></span>
							</li>
							<#-- 
							<li class="">
                              <p><b>*</b>调度频率：</p>
                              <span>
                              <select name="times" id="times">
                              <option value="">--请选择--</option>
                              <#list 1..15 as i>
                              <option value="${i}">${i}</option>
                              </#list>
                              </select>单位(分)
                              </span>
                              <span> <div id="timesTip"></div></span>
							</li>
							-->
							<li class="">
                              <p>描述：</p>
                              <span>
                              <textarea  name="notes"  id="notes" cols="42"  rows="3"></textarea>
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
    <#--</div>-->
  <#--</div>
  <div class="clear"></div>
</div>
<div class="clear"></div>
</div>
-->
</form>
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
<!--增删查改-->
<#include "/common/commonUd.ftl">
 <!--验证js-->
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/cloudnode/ftplistenValidator.js'></script> 
<!--Ztree-->
<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
<#include "/common/commonLhg.ftl">
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
			//loadTree();//载入树
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
