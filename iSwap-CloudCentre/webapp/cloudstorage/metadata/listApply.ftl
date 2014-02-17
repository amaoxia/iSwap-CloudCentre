<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据查看申请</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery.js'></script>
</head>
<body>
		  <div class="loayt_01 rlist100">
          <div class="loayt_mian" >
          <form name="pageForm"  method="post"  action="${path}/cloudstorage/metadata/metadata!listApply.action">
		  <table class="main_drop" style="margin:6px auto 0px auto;">
          <tr>	
          <td align="right">
         		 部门名称：<input name="conditions[e.sysDept.deptName,string,like]" onkeypress="showKeyPress()" onpaste="return false"   value="<#if serchMap.e_sysDept_deptName?exists>${serchMap.e_sysDept_deptName?default('')}</#if>" type="text"  />
				 指标名称：<input name="conditions[e.targetName,string,like]" type="text"  onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_targetName?default("")}"/>
				<input  type="submit" value="查询"  class="btn_s"/>
          </td>
        </tr>
      </table>
     							 <table class="tabs1">
						        <tr>
						          <th width="28" class="tright">序号</th>
						          <th>指标名称</th>
						          <th>所属部门</th>
						          <th>创建时间&nbsp;<a href="javascript:void(0)" onclick="setOrder(this)" name="e.createDate"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
						          <th>操作</th>
						        </tr>
        						  <#list listDatas as entity>
          						  <tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
						          <td>${entity_index+1}</td>
						          <td>${entity.targetName?default('无')}</td>
						          <td><#if entity.sysDept?exists>${entity.sysDept.deptName?default('无')}</#if></td>
								  <td><#if entity.createDate?exists>${entity.createDate?date?default('无')}</#if></td>
						          <td>
						          <a href="#"  id="hz0" class="tabs1_cz" onclick="opdg('${path}/cloudstorage/apply/apply!addView.action?itemId=${entity.id?default('')}','申请','980','570');">
		      						   <img src="${path}/images/small9/s_chakan.gif" />申请</a> 	
		      						   <a href="#" onclick="opdg('${path}/cloudstorage/tableinfo/tableinfo!addDesc.action?itemId=${entity.id}','表结构','1000','470');" class="tabs1_cz" >
						           <img src="${path}/images/small9/s_biaojiegou.gif"/>表结构</a>
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
						      </form>
						       </div>
								 </div>
								</div>
<div class=" clear"></div>
<#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">
</body>
</html>