<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#import "/common/pager.ftl" as pager>
<#global path = request.getContextPath() />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>考核模版</title>

<link href="${request.getContextPath()}/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${request.getContextPath()}/css/pop.css" rel="stylesheet" type="text/css" />
<link href="${request.getContextPath()}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${request.getContextPath()}/js/jquery.js'></script>
<!--日期控件-->
<script type='text/javascript' src='${request.getContextPath()}/js/datepicker/WdatePicker.js'></script>
<!--全选-->
<script type='text/javascript' src='${request.getContextPath()}/js/checkAll.js'></script>
<!--弹出窗口-->
<script type="text/javascript" src="${request.getContextPath()}/js/lhgdialog/lhgcore.min.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/lhgdialog/lhgdialog.min.js"></script>

<script type="text/javascript" src="${request.getContextPath()}/js/selectedTD/selectedTD.js"></script>

<!--通用方法-->
<script type="text/javascript" src="${request.getContextPath()}/js/iswap_common.js"></script>
<script>    
	<!--页面上弹出窗口-->
	function showWindow(url,titleValue,widths,heights){
		var dg = new J.dialog({ page:url,title:titleValue, cover:true ,rang:true,width:widths,height:heights,autoSize:true});
	    dg.ShowDialog();
	}
	
	
	function updateStatus(type){
		  document.getElementById('pageForm').action="modelmg!updateStatus.action?status="+type;
		  var state = "";
		  if('0'==type){state="确定冻结该模板？"}
		  if('1'==type){state="确定激活该模板？"}
		  if(checks()) {
			  if(confirm(state)) {
			 	document.getElementById('pageForm').submit();
			  }
		  } else {
		  	alert('至少选择一条数据');
		  }
	}

	function remove(){
		  document.getElementById('pageForm').action="modelmg!delete.action";
		  if(checks()) {
			  if(confirm("确定删除该数据吗？")) {
			 	document.getElementById('pageForm').submit();
			  }
		  } else {
		  	alert('至少选择一条数据');
		  }
		}
	function sub(){
		document.pageForm.submit(); 
	}
</script>
</head>

<body >
<#setting number_format="0">
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${request.getContextPath()}/images/title/img_05.png"  align="absmiddle" />考核模板</div>
      <div class="c_m_btn"> 
	      <span class="cm_btn_m">
	      <a href="javascript:void(0)"  onclick="showWindow('${request.getContextPath()}/performancemanage/model/modelmg!targettree.action','新建模板',290,470)">
	      	<b>
	     		<img src="${request.getContextPath()}/images/cmb_xj.gif" class="cmb_img" />新建模板
	      		<a href="javascript:void(0)">
	   				<img src="${request.getContextPath()}/images/bullet_add.png" class="bullet_add" alt="收藏" title="收藏"/>
	   			</a>
	   		</b>
	   	  </a>
	   	  </span>
	   	  <span class="cm_btn_m">&nbsp;
	      </span>
      </div>
    </div>
    
    <div class="main_c">
    <form name="pageForm" id="pageForm" action="modelmg!list.action" method="post">
	    <table class="main_drop">
	        <tr>
	          <td align="right">模版名称：
	            <input name="conditions[e.name,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_name?default("")}" type="text" />
	            创建时间：
	            <input id="d4311" class="Wdate"  name="conditions[e.createTime,date,ge]" value="${serchMap.e_createTime_ge?default("")}" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/>
	        至
	           <input id="d4312" class="Wdate" name="conditions[e.createTime_date,date,le]" value="${serchMap.e_createTime_date_le?default("")}"  type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})"/>
	            <input name="" type="button" value="查询"  class="btn_s" onclick="sub();"/></td>
	        </tr>
	      </table>
	      <table class="tabs1" style="margin-top:0px;">
	        <tr>
	          <th width="20">&nbsp;</th>
	          <th width="28">序号</th>
	          <th>模板名称</th>
	          <th>模板状态</th>
	          <th>部门选择(季)</th>
	          <th>部门选择(年度)</th>
	          <th>模板创建时间</th>
	          <th>操作</th>
	        </tr>
	        
	        <#assign i = 0>  
	        <#list listDatas as list>
	        <#assign i = i+1>
	        <#if i%2=0>
		         <tr  onclick="selectedTD(this);">
		    <#else> 
		        <tr class="trbg" onclick="selectedTD(this);">  
		     </#if> 
		          <td class="trbg"><input name="ids" type="checkbox" value="${list.id}" /></td>
		          <td class="trbg" width="28">${i+(page.index-1)*10}</td>
		          <td class="trbg"><a href="javascript:void(0)" onclick="showWindow('${request.getContextPath()}/performancemanage/model/modelmg!view.action?id=${list.id}','模板查看',720,550)" id="hz3">${list.name?default('')}</a></td>
		          <td class="trbg">
		          	<span  class="font_red">
		                  <#if list.status == '0'>
		                      	已冻结
		                  <#else>
		                  		已激活
		                  </#if>
		              </span>
		          </td>
		          <td class="trbg"><a href="javascript:void(0)" onclick="showWindow('${request.getContextPath()}//performancemanage/model/modelmg!deptTree.action?modelId=${list.id}&type=1','选择部门',280,410)"><#if list.deptModeQuarter=='1'><img src="${request.getContextPath()}/images/small9/hongqi.gif" /><#else><img src="${request.getContextPath()}/images/small9/greyqi.gif" /></#if> 选择</a></td>
		          <td class="trbg"><a href="javascript:void(0)"  onclick="showWindow('${request.getContextPath()}//performancemanage/model/modelmg!deptTree.action?modelId=${list.id}&type=2','选择部门',280,410)"><#if list.deptModeYear=='1'><img src="${request.getContextPath()}/images/small9/hongqi.gif" /><#else><img src="${request.getContextPath()}/images/small9/greyqi.gif" /></#if>  选择</a></td>
		          <td class="trbg">${list.createTime?string('yyyy-MM-dd')}</td>
		          <td class="trbg">
		         	 <#if list.status != '0'>
		         		 <a href="javascript:void(0)" class="no_tabs1_cz" ><img src="${request.getContextPath()}/images/no_czimg_edit.gif" />编辑</a>
		              <#else>
		              <a href="javascript:void(0)" onclick="showWindow('${request.getContextPath()}/performancemanage/model/modelmg!preStep.action?id=${list.id}','编辑模板',290,470)" class="tabs1_cz"><img src="${request.getContextPath()}/images/czimg_edit.gif" />编辑</a>
			          </#if>
			          <#if list.status == '0'>
			         	 <a href="${request.getContextPath()}/performancemanage/model/modelmg!updateStatus.action?id=${list.id}&status=1" class="tabs1_cz"><img src="${request.getContextPath()}/images/small9/jihuo.gif" />激活</a>
			          <#else>
			          	<a href="${request.getContextPath()}/performancemanage/model/modelmg!updateStatus.action?id=${list.id}&status=0" class="tabs1_cz"><img src="${request.getContextPath()}/images/small9/dongjie.gif" />冻结</a>
			          </#if>
			           <#if list.status != '0'>
			           <a href="javascript:void(0);" class="no_tabs1_cz"><img src="${request.getContextPath()}/images/no_czimg_sc.gif" />删除</a>
			           <#else>
			            <a href="modelmg!delete.action?id=${list.id}" class="tabs1_cz"><img src="${request.getContextPath()}/images/czimg_sc.gif" />删除</a>
			           </#if>
			       
		          </td>
		        </tr>
	        </#list>
	      </table>
      </form>
      
      
      
      <div class="tabs_foot"> 
      	<span class="tfl_btns">
      		<img src="${request.getContextPath()}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px; margin-right:8px;" />
      			<a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
      			<img src="${request.getContextPath()}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
      			<!--a href="javascript:remove()" class="tfl_blink"><b class="hot">删除</b></a-->
      			<a href="javascript:updateStatus('0')"  class="tfl_blink"><b >冻结</b></a>
      			<a href="javascript:updateStatus('1')"  class="tfl_blink"><b>激活</b></a>
        </span> 
      	<span class="page pageR"><@pager.pageTag/></span></div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
</body>
</html>
