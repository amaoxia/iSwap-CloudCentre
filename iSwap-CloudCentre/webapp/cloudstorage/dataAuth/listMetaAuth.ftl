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
<form method="post" name="pageForm" id="saveForm" action="${path}/cloudstorage/dataAuth/dataAuth!listMetaAuth.action?type=${type?default('')}">
<div class="loayt_01 rlist100">
          <div class="loayt_mian" >
           <table class="main_drop">
       				 <tr>
          <td align="right">
            	指标名称：	<input name="conditions[e.metaData.targetName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="<#if serchMap.e_metaData_targetName?exists>${serchMap.e_metaData_targetName?default("")}</#if>" type="text"  />
	        	<input name="" type="submit"  value="查询"  class="btn_s"/>	
          </td>
        </tr>
      </table>
      <table class="tabs1">
        <tr>
          <th width="28" class="tright">序号</th>
          <th>指标名称</th>
          <th>申请部门</th>
          <th>创建时间&nbsp;<a href="javascript:void(0)" onclick="setOrder(this)" name="e.dataApplyDate"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
          <th>状态</th>
          <th>操作</th>
        </tr>
        <#list listDatas as entity>
          <tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
          <td>${entity_index+1}</td>
          <td>${entity.metaData.targetName?default('')}</td>
          <td>${entity.sysDept.deptName?default('')}</td>
          <td>${entity.dataApplyDate?default('')}</td>
          <td><#if entity.dataAuthState?exists><#if entity.dataAuthState=='0'>未授权<#elseif entity.dataAuthState=='2'>修改授权<#else>已授权</#if><#else>未授权</#if></td>
          <td>
          <a href="javascript:void(0)" class="tabs1_cz" onclick="opdg('${path}/cloudstorage/dataAuth/dataAuth!authView.action?id=${entity.id}','授权信息','450','430')"> 
          <img src="${path}/images/small9/s_shenqinghan.gif"/>查看申请函</a>
          <a href="javascript:void(0)" class="tabs1_cz" onclick="opdg('${path}/cloudstorage/dataAuth/dataAuth!addAuthView.action?id=${entity.id}','授权','730','200')">
          <img src="${path}/images/small9/s_shouquan.gif"/><#if entity.dataAuthState?exists><#if entity.dataAuthState=='0'>授&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权<#else>修改授权</#if><#else>授&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权</#if></a>
          <a href="javascript:void(0)" class="tabs1_cz" onclick="opdg('${path}/cloudstorage/dataAuth/dataAuth!view.action?id=${entity.id}','授权信息','730','200')" >
          <img src="${path}/images/small9/sctb.gif"/>授权信息</a>
          <a href="javascript:void(0)"  <#if entity.dataApplyType=="1">onclick="downLoad('${entity.id}')"<#else> style="color:#ccc;" </#if> class="tabs1_cz"><img src="${path}/images/small9/down.gif" />下载申请文件</a>
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
					</form>
					<script type="text/javascript">
					//下载文件
						function downLoad(id){
							window.location="${path}/cloudstorage/dataAuth/dataAuth!downFile.action?id="+id;
						}
				</script>
				<#include "/common/commonList.ftl">
				<#include "/common/commonLhg.ftl">
</body>
</html>

