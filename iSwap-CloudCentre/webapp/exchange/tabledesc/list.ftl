<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表结构信息</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body>
<div class="pop_01"   style="width:100%;height:100%;overflow-x:hidden;overflow-y:scroll;">
    <div class="main_c" >
    <form id="pageForm" method="post"  name="pageForm"	action="${path}/exchange/tabledesc/tabledesc!list.action">
    	<input type="hidden" name="itemId" id="itemId" value="${itemId?default('')}"/>
    	<input type="hidden" name="view" id="view" value="${view?default('')}"/>
           <table class="main_drop">
        <tr>
          <td align="right">
				  				字段代码：	<input name="conditions[e.filedcode,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_filedcode?default("")}" type="text"  />
						          <input  type="submit" value="查询"  class="btn_s"/></td>
						        </tr>
						      </table>                              
						      <table class="tabs1"  style="margin-top:0px;">
						        	 <tr onclick="selectedTD(this);">
						        	 <th width="2%"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
						              <th width="28" class="tright">序号</th>
         							  <th>字段名称</th>
							          <th>字段代码</th>
							          <th>类型</th>
							          <th>长度</th>
							          <th>是否展示</th>
							          <th>操作</th>
						        </tr>
						       <#list listDatas as entity>
          						<tr c<#if (entity_index+1)%2==0>lass="trbg"</#if> onclick="selectedTD(this);">
          						  <td><input name="ids" type="checkbox" value="${entity.id}" /></td>
						          <td>${entity_index+1}</td>
						          <td>${entity.name?default('无')}</td>
						          <td>${entity.filedcode?default('无')}</td>
								  <td>${entity.dataType?default('无')}</td>
								  <td>${entity.filedLength?default('无')}</td>
								  <td><#if entity.isShow=="1">是<#else>否</#if></td>
						          <td>
						           <a href="#" class="tabs1_cz" onclick="next('tabledesc!view.action?id=${entity.id}',500,400)">
						          <img src="${path}/images/small9/s_chakan.gif" />查看</a> 
							          <#if view?exists&&view=='1'>
							          <#else>
								          <a href="javascript:void(0)" id="hz0" onclick="next('tabledesc!updateView.action?id=${entity.id}',655,430)" class="tabs1_cz">
								          <img src="${path}/images/czimg_edit.gif" />编辑</a>
								          <a href="${path}/exchange/tabledesc/tabledesc!updateStatus.action?ids=${entity.id}&status=<#if entity.isShow=='1'>0<#else>1</#if>" id="hz0"  class="tabs1_cz">
								          <img src="${path}/images/small9/jihuo.gif" /><#if entity.isShow=='1'>屏蔽<#else>展示</#if></a>
								          <a href="javascript:void(0)" id="hz0" onclick="del('tabledesc!delete.action?ids=${entity.id}')" class="tabs1_cz">
								          <img src="${path}/images/czimg_sc.gif" />删除</a>
							         </#if>
						          </td>
						        </tr>
						        </#list>
						      </table>
						      <div class="tabs_foot"> 
						      <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
						      <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
						      <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
						      <#if view?exists&&view=='1'>
							   <#else>
							      <a href="javascript:void(0)" onclick="javascript:updateStatus('1')" class="tfl_blink"><b>展示</b></a>
							      <a href="javascript:void(0)" onclick="javascript:updateStatus('0')" class="tfl_blink">
							      <b>屏蔽</b></a><a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/exchange/tabledesc/tabledesc!delete.action')"><b class="hot">删除</b></a></span>
						      </#if>
						      <span class="tfl_btns"><a href="javascript:void(0)" class="tfl_blink"></a></span><img src="${path}/images/tabsf_bg.png" height="23"/> <span class="page pageR">
						      <@pager.pageTag/>
						      </span> </div>
						    </div>
						    </div>
						     </form>
			</body>
			<#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">
<script type="text/javascript">    
	function next(url,width,hegith){
		var dg = frameElement.lhgDG;
		var win = dg.dgWin;
		win.location.href=url;
		dg.reDialogSize(width,hegith);	
		dg.SetPosition('center','center'); 
		
	}
			//删除本条
function del(url) {
	if (confirm("确定要删除本记录吗？")) {
		document.forms['pageForm'].action = url;
		document.forms['pageForm'].submit();
	}
}
//删除选中
function delMany(url) {
	if($("input[name='ids']:checked ").length==0){
		alert("请选择记录！");
		return;
	}
	if (confirm("确定要删除选中记录吗？")) {
		document.forms['pageForm'].action = url;
		document.forms['pageForm'].submit();
	}
}
function updateStatus(type){
		  document.getElementById('pageForm').action="tabledesc!updateStatus.action?status="+type;
		  var state = "";
		  if('0'==type){state="确定屏蔽字段？"}
		  if('1'==type){state="确定显示字段？"}
		  if(checks()) {
			  if(confirm(state)) {
			 	document.getElementById('pageForm').submit();
			  }
		  } else {
		  	alert('至少选择一条数据');
		  }
	}
</script>
</html>