<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body>
 <div class="loayt_01 rlist100">
          <div class="loayt_mian" >
	<table class="main_drop" style="margin:6px auto 0px auto;">
        <tr>
          <td align="right">
          <form name="pageForm" method="post"  action="${path}/cloudstorage/apply/apply!serachList.action?type=${type?default('')}">
     	         指标项名称：<input type="text"  name="conditions[e.metaData.targetName,string,like]" value="<#if serchMap.e_metaData_targetName?exists>${serchMap.e_metaData_targetName}</#if>"  onkeypress="showKeyPress()" onpaste="return false"/>
	        <input type="submit"  value="查询"  class="btn_s"/>	
          </td>
        </tr>
      </table>
      <table class="tabs1">
        <tr>
          <th width="28" class="tright">序号</th>
          <th>指标项名称</th>
          <th>所属部门</th>
          <!--<th>使用部门</th>-->
          <th>创建时间&nbsp;<a href="javascript:void(0)" onclick="setOrder(this)" name="e.metaData.createDate"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
          <th width="300">操作</th>
        </tr>
         <#list listDatas as entity>
          <tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
          <td>${entity_index+1}</td>
          <td>${entity.metaData.targetName?default('')}</td>
          <td><#if entity.metaData.sysDept?exists>${entity.metaData.sysDept.deptName?default('')}</#if></td>
         <!-- <td>${entity.sysDept.deptName?default('')}</td>-->
          <td>${entity.metaData.createDate}</td>
          <td><a href="#" class="tabs1_cz" id="targetinfo" onclick="opdg('${path}/cloudstorage/tableinfo/tableinfo!list.action?itemId=${entity.metaData.id}','查看指标项','920','500');" onclick="#">
          <img src="${path}/images/small9/s_biaojiegou.gif"/>查看指标项</a>
           <a href="javascript:void(0)" class="tabs1_cz"  <#if !entity.filedApplystate?exists||entity.filedAuthState=="0">style="color:#ccc;"<#else>onclick="opdg('${path}/cloudstorage/apply/apply!dataList.action?id=${entity.id}','数据列表(部分字段数据列表)','920','500')"</#if>>
          <img src="${path}/images/small9/s_chakan.gif"/>查看数据</a>
          <a href="javascript:void(0)" class="tabs1_cz"  <#if !entity.dataAuthState?exists||entity.dataAuthState=="0">style="color:#ccc;"<#else>onclick="downLoad('${entity.id}')"</#if>>
          <img src="${path}/images/small9/down.gif"/>下载数据</a></td></td>
          <td>
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
</div>
				<div class=" clear"></div>
				<#include "/common/commonList.ftl">
				<#include "/common/commonLhg.ftl">
				<script type="text/javascript">
					//下载文件
						function downLoad(id){
							window.location="${path}/cloudstorage/apply/apply!sendMail.action?id="+id;
						}
				</script>
</body>
</html>

