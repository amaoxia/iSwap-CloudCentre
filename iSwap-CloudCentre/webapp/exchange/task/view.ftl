<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body style="display:none"  onclick="parent.hideMenu()" onload="load();">
<form name="pageForm" action="" method="post">      </form>
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/img_03.png" align="absmiddle" />数据发送任务列表</div>
    </div>
    <div class="main_c">
      <div class="top_layout">
        <input name="apply" id="all" type="checkbox" value="" checked="checked"  onchange="selectApp(this.id,this.value)"  />
        全部应用
        <input name="apply" id="qlyg" type="checkbox" style="margin-left:20px;" value="权力阳光" onchange="selectApp(this.id,this.value)"  />
        权力阳光
        <input name="apply" id="zhzs" type="checkbox" value="综合治税" onchange="selectApp(this.id,this.value)" />
        综合治税
        <input name="apply" id="xzbg" type="checkbox" value="行政办公 " onchange="selectApp(this.id,this.value)" />
        行政办公        </div>
      <div style="overflow-x:hidden;overflow-y:auto;height:420px;">
        <div class="tab_item">
          <div class="tab_item_top tab_item_top_1">
            <h1 class="red"><b>红灯</b></h1>
            <span></span></div>
          <div class="tab_item_list">
              <#if sendDeptList?exists>
           <#list sendDeptList as dept>  
           <#if dept[2]?exists>
           <#if dept[2]=='3'>
            <div class="tilist" onclick="info('${dept[0]}','${dept[1]}');">
              <h2>${dept[1]}</h2>
              <span><img src="${path}/images/jc_red.png" /></span>
            </div>
            </#if>
            </#if>
             </#list>
          </#if>
           
          </div>
        </div>
        <div class="clear"></div>
        <div class="tab_item">
          <div class="tab_item_top tab_item_top_2">
            <h1 class="yellow"><b>黄灯</b></h1>
            <span></span></div>
          <div class="tab_item_list">
           <#if sendDeptList?exists>
           <#list sendDeptList as dept>  
            <#if dept[2]?exists>
           <#if dept[2]=='2'>
            <div class="tilist" onclick="info('${dept[0]}','${dept[1]}');">
              <h2>${dept[1]}</h2>
              <span><img src="${path}/images/jc_yellow.png" /></span>
            </div>
             </#if>
              </#if>
             </#list>
          </#if>
   
          </div>
        </div>
        <div class="clear"></div>
        <div class="tab_item">
          <div class="tab_item_top tab_item_top_3">
            <h1 class="green"><b>绿灯</b></h1>
            <span></span></div>
          <div class="tab_item_list">
           <#if sendDeptList?exists>
           <#list sendDeptList as dept>  
             <#if dept[2]?exists>
           <#if dept[2]=='1'>
            <div class="tilist" onclick="info('${dept[0]}','${dept[1]}');">
              <h2>${dept[1]}</h2>
              <span><img src="${path}/images/jc_green.png" /></span>
            </div>
             </#if>
              </#if>
             </#list>
          </#if>
           
          </div>
        </div>
        <div class="clear"></div>
      </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<!--日期控件-->
<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
<#include "/common/commonLhg.ftl">
<#include "/common/commonList.ftl">
<script type="text/javascript">    
		<!--页面上弹出窗口-->
	function info(id,deptname){
		opdg('${path}/exchange/task/taskAction!list.action?deptId='+id+'&conditions[e.item.sysDept.id,long,eq]='+id+'&conditions[e.sendState,string,ne]=0','['+deptname+']数据发送任务情况',1000,430);
	}
	 function addtask(){
     	opdg('${path}/exchange/task/taskAction!addView.action','添加任务',500,320);
     }
     function load() {
  		if('${deptId}'!='1'){
  		document.forms['pageForm'].action='${path}/exchange/task/taskAction!list.action?deptId='+${deptId}+'&conditions[e.item.sysDept.id,long,eq]='+${deptId}+'&conditions[e.sendState,string,ne]=0';
  		document.forms['pageForm'].submit();
  		}else{
  			document.body.style.display="block";
  		}
  	}
  	 function selectApp(){
  		var applys = document.getElementsByName("apply");
  		var apps = ""
  		for(var i=0;i<applys.length;i++){
			if(applys[i].checked==true){
  	 			apps = apps + applys[i].value+",";
  	 		}
		}
		//document.forms['pageForm'].action='${path}/exchange/task/taskAction!view.action';
  		//document.forms['pageForm'].submit();
     }
</script>
</body>
</html>
