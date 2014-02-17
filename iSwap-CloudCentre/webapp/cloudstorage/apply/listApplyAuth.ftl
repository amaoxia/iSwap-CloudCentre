<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>元数据管理</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body>
<div class="loayt_01 rlist100">
          <div class="loayt_mian" >
           <table class="main_drop">
       				 <tr>
          <td align="right">
       			 <form name="pageForm" method="post"  action="${path}/cloudstorage/apply/apply!listApplyAuth.action">
            	   指标名称：	<input name="targetName" value="${targetName?default('')}" type="text"  />
	       		 			<input  type="submit"  value="查询"  class="btn_s" onkeypress="showKeyPress()" onpaste="return false"/>	
          </td>
        </tr>
      </table>
      <table class="tabs1">
        <tr>
          <th width="28" class="tright">序号</th>
          <th>指标名称</th>
          <th>申请部门</th>
          <th>所属应用</th>
          <th>状态</th>
          <th>创建时间&nbsp;<a href="javascript:void(0)" onclick="setOrder(this)" name="e.filedAuthState"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
          <th>操作</th>
        </tr>
        <#list listDatas as entity>
          <tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
          <td>${entity_index+1}</td>
          <td>${entity.metaData.targetName?default('')}</td>
          <td>${entity.sysDept.deptName?default('')}</td>
          <td><#if entity.appMsg?exists>${entity.appMsg.appName}<#else>无</#if></td>
          <td><#if entity.filedAuthState?exists><#if entity.filedAuthState=='0'>未授权<#elseif entity.filedAuthState=='2'>修改授权<#else>授权</#if><#else>未授权</#if></td>
           <td>${entity.filedApplyDate}</td>
          <td>
          <a href="javascript:void(0)" class="tabs1_cz" onclick="opdg('${path}/cloudstorage/apply/apply!authView.action?id=${entity.id}','申请函信息','450','430')"> 
          <img src="${path}/images/small9/s_shenqinghan.gif"/>查看申请函</a>
          <a href="javascript:void(0)" class="tabs1_cz" onclick="opdg('${path}/cloudstorage/apply/apply!addAuthView.action?id=${entity.id}','授权','730','200')">
          <img src="${path}/images/small9/s_shouquan.gif"/><#if entity.filedAuthState?exists><#if entity.filedAuthState=='0'>授&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权<#else>修改授权</#if><#else>授&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权</#if></a>
          <a href="javascript:void(0)" class="tabs1_cz" onclick="opdg('${path}/cloudstorage/apply/apply!view.action?id=${entity.id}','授权信息','730','200')" >
          <img src="${path}/images/small9/sctb.gif"/>授权信息</a>
          <#if entity.filedApplyType=="1"><a href="javascript:void(0)"  onclick="downLoad('${entity.id}')" class="tabs1_cz">
          <img src="${path}/images/small9/down.gif" />下载申请文件</a></#if>
          </td>
           </tr>
        </#list>
       </table>
    	
     						
     						 <div class="tabs_foot"  style="width:100%"> 
						      <span class="tfl_btns" style="float:left;">
             					</span> 
       							<span class="page pageR" style="margin-top:0px;">
						       <@pager.pageTag/>
						      </span>
						       </div>
						       </div>
					</form>
					<#include "/common/commonList.ftl">
					<#include "/common/commonLhg.ftl">
					<script type="text/javascript">
					//下载文件
						function downLoad(id){
							window.location="${path}/cloudstorage/apply/apply!downFile.action?id="+id;
						}
				</script>
</body>
</html>

