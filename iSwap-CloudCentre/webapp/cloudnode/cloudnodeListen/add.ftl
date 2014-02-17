<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MONGODB数据源接与入定义</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">

</head>
<body>
<form name="save" id="saveForm" method="post" action="${path}/cloudnode/cloudnodeListen/cloudnodeListen!add.action"/>
<@s.token name="token"/>
<input type="hidden" id="deptId" name="sysDept.id" value="${deptId?default('')}"/>
<div  class="frameset_w" style="width:900px;height:580px;overflow-x:hidden;overflow-y:scroll;background-color:#FFFFFF;" >
  <div class="main">
    <#-- 
    <div class="main_c2" >
    	<br/>指定交换流程：<br />
      <div class="left_tree_com" style="width:250px;">
        <span>
        <p>
           <div scrolling="Auto" frameborder="0" style="border:0px;height:500px;width:250px;background:url(${path}/images/common_menu_bg.jpg) #CFE1ED bottom repeat-x" class="wpage" >
          	<ul id="tree" class="tree" style="height:488px;width:240px;overflow:auto;"></ul>
          </div>
        </p>
        </span> </div>
      <div class="right_main">
        <div class="loayt_01 rlist100">
          <div class="loayt_top"> <span class="loayt_tilte"><b>新建存储监听信息 </b></span><span class="loayt_right"><img src="${path}/images/kuang_right.png" width="10" height="31" /></span> </div>
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
                              <p><b>*</b>实例名称：</p>
                              <span>
                              <input type="text" size="30"  name="dbName" id="dbName"/>
                              </span>
                              <span> <div id="dbNameTip"></div></span>
							</li>
							<#--
							<li class="">
                              <p><b>*</b>表名称：</p>
                              <span>
                              <input type="text" size="30" name="collectionName" id="collectionName"/>
                              </span>
                               <span> <div id="collectionNameTip"></div></span>
							</li>
							<li class="item2_bg">
                              <p><b>*</b>字段名称：</p>
                              <span>
                              <input type="text" size="30" name="filedName"  id="filedName" value="status"/>
                              </span>
                               <span> <div id="filedNameTip"></div></span>
							</li>
							<li >
                              <p><b>*</b>字段状态：</p>
                              <span>
                              	<input type="text" size="30" name="filedStatus" id="filedStatus"/>
                              </span>
                              <span> <div id="filedStatusTip"></div></span>
							</li>
					<li class="item2_bg">
					  <p>频率类型：</p>
					  <span id="taskId">
					     <input type="radio" name="type"  id="type1" value="1" checked="checked"> 秒
					     <input type="radio" name="type"  id="type1" value="2"> 分
					     <input type="radio" name="type"  id="type1" value="3"> 时
					     <input type="radio" name="type"  id="type1" value="4"> 天
					     <input type="radio" name="type"  id="type1" value="5"> 周
					     <input type="radio" name="type"  id="type1" value="6"> 月
					  </span>
					  <span><div id='type1Tip'></div></span>
					</li>
					<li class="">
					  <p>频率：</p>
					  <span id="miao">
					            秒<select name="seconds" id="appMsgId">
					       <#list 1..59 as x>   
					         <option value="${x}">${x}</option> 
					       </#list>
					     </select> 
					     &nbsp;
					  </span>
					   <span id="fen">
					             分<select name="branch" id="appMsgId">
					       <#list 1..59 as x>   
					         <option value="${x}">${x}</option> 
					       </#list>
					     </select> 
					     &nbsp; 
					  </span>
					   <span id="xiaoshi">
					             小时<select name="time" id="appMsgId">
					       <#list 1..23 as x>   
					         <option value="${x}">${x}</option> 
					       </#list>
					     </select> 
					     &nbsp;
					  </span> 
					  <span id="tian">
					             天<input type="text" id="d242" name="day" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'H:mm'})" class="Wdate"/> &nbsp;
					  </span>
					  </li>
					   <span id="zhou">
					      <input type="checkbox" name="week" value="MON"/> 星期一&nbsp;
					      <input type="checkbox" name="week" value="TUE"/> 星期二&nbsp;
					      <input type="checkbox" name="week" value="WED"/> 星期三&nbsp;
					      <input type="checkbox" name="week" value="THU"/> 星期四&nbsp;
					      <input type="checkbox" name="week" value="FRI"/> 星期五&nbsp;
					      <input type="checkbox" name="week" value="SAT"/> 星期六&nbsp;
					      <input type="checkbox" name="week" value="SUN"/> 星期日&nbsp;
					  </span> 
					   <span id="yue">
					       <#list 1..12 as x>   
					         <#if x==7></br></#if>
					         <input type="checkbox" name="month" value="${x}"/> ${x} 月&nbsp;
					       </#list>
					  </span> 
					<li class="item2_bg" id="time">
					  <p>执行时间：</p>
					  <span>
					  <input type="text" id="d243" name="executeTime" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'H:mm'})" class="Wdate"/> &nbsp;
					  </span>
					  <span><div id='appCodeTip'></div></span>
					</li>	
					<li class="item2_bg" id="startDate">
					  <p>生效时间：</p>
					  <span>
					  <input type="text" id="d245" name="startDate" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate"/> &nbsp;
					  </span>
					  <span><div id='appCodeTip'></div></span>
					</li>	
					<li>
			        <p>参数输入：</p>
			        <span >
			        <table class="tabs1" id="parmTable" >
			          <tr>
			            <td><textarea name="message" id="attributeXML" cols="73" rows="7"></textarea></td>
			          </tr>
			        </table>
			        </span> 
			        </li>
			        -->	
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
<script type='text/javascript' src='${path}/dwr/util.js'></script>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<!--增删查改-->
<#include "/common/commonUd.ftl">
 <!--验证js-->
<#include "/common/commonValidator.ftl">
<!--Ztree-->
<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
<script type='text/javascript' src='${path}/js/validator/cloudnode/cloudnodeListenValidator.js'></script> 
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
