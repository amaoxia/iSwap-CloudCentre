<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>	
</head>

<body onclick="parent.hideMenu()">
<form action="cloudNodeInfo!nodeStatusMonitor.action" method="post" name="pageForm" id="pageForm">
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />前置机状态监控</div>
	  <div class="c_m_btn"> <span class="cm_btn_m">  </a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
    </div>
    <div class="main_c">
    <table class="main_drop">
        <tr>
          <td align="right">
          	所属应用：
            <select name="appId">
            <option value="">--请选择--</option>
            <#list appMsgs as entity>  
                <option value="${entity.id}" <#if appId?exists><#if appId==entity.id>selected</#if></#if>>${entity.appName?default('')}</option>
            </#list> 
            </select>
          	前置机名称：
            <input name="nodesName" value="${nodesName?default("")}" type="text" onkeypress="showKeyPress()" onpaste="return false" />
            <input name="" type="button" value="查询" onclick="subForm('pageForm')" class="btn_s"/></td>
        </tr>
      </table>
      <table class="tabs1" style="margin-top:0px;">
        <tr>
          <th width="28">序号</th>
          <th width="25%">前置机名称</th>
		  <th>状 态</th>
		  <th>所属部门</th>
		  <th>所属应用</th>
          <th>前置机IP</th>
           
        </tr>
        <#list listDatas as entity>
		<tr <#if entity_index%2==0>class="trbg"</#if> onclick="selectedTD(this);" >
          <td width="28">${entity_index+1}</td>
          <td><a href="javascript:opdg('${path}/cloudcenter/cloudNodeInfo/cloudNodeInfo!nodeStatusMonitorView.action?id=${entity.id}','查看前置机',600,300);" >${entity.nodesName?default("")}</a></td>
          <td>${entity.runStatus?default("")}</td>
          <td>${entity.sysDeptNames}</td>
          <td>${entity.appMsgNames}</td>
		  <td>
		  ${entity.address?default("")}
		  </td>
           
        </tr>
        </#list>
      </table>
      <div class="tabs_foot"> <span class="tfl_btns"> 
     
	  <br><br>
       
      </span> 
      <span class="page pageR">
      	<@pager.pageTag/>
      <span>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
<form>
<#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">
<script type="text/javascript">
	<!--页面上弹出窗口-->
	function showWindow(url,titleValue,widths,heights,id){
		var dg = new J.dialog({ page:url,title:titleValue, cover:true ,rang:true,width:widths,height:heights,autoSize:true,id:id});
		
	    dg.ShowDialog();
	}

	function saveTheForm(){
		document.forms['saveForm'].submit();
		
	}

	 function add(){
	 	showWindow('${path}/cloudcenter/cloudNodeInfo/cloudNodeInfo!addView.action','注册前置机信息',600,620,'father');
	 }
	  function edit(id){
	 	showWindow('${path}/cloudcenter/cloudNodeInfo/cloudNodeInfo!updateView.action?id='+id,'编辑前置机信息',600,620,'father');
	 }
	 function changeStatus(id,s){
	 	s=s=='1'?0:1;
	 	window.location.href="cloudNodeInfo!changeStatus.action?id="+id+"&status="+s;
	 }
	 
	 function view(id){
	 	opdg('${path}/cloudcenter/cloudNodeInfo/cloudNodeInfo!nodeStatusMonitorView.action?id='+id,'查看前置机信息',600,620);
	 	//showWindow('${path}/cloudcenter/cloudNodeInfo/cloudNodeInfo!nodeStatusMonitorView.action?id='+id,'查看前置机信息',600,620,'father');
	 }
	function reloadPage(){
		window.location.href="cloudNodeInfo!list.action";
	}
	function deploy(id){
		if(confirm("确定要部署吗？")){
			window.location.href="cloudNodeInfo!deploy.action?id="+id;
		}
	}
	function undeploy(id){
		if(confirm("确定要取消部署吗？")){
			window.location.href="cloudNodeInfo!unDeploy.action?id="+id;
		}
	}
</script>

</body>
</html>
