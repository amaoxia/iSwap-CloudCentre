<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>云存储管理</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body >
<div class="frameset_w" style="height:100%" onclick="parent.hideMenu()">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/CloudStorageCenter.png"  align="absmiddle" />云存储中心配置</div>
      <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/cloudStorageCenter/cloudStorageCenter!addView.action','编辑','630','550');">
      <b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新建中心配置<a href="javascript:void(0)">
      <img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
    </div>
    <div class="main_c">
           <table class="main_drop">
        <tr>
          <td align="right">
          <form name="pageForm" method="post"  action="${path}/cloudstorage/cloudStorageCenter/cloudStorageCenter!list.action">
		  服务的名称：<input name="conditions[e.serverName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_serverName?default("")}" type="text" />
		  端口号：<input name="conditions[e.port,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_port?default("")}" type="text" />
            <input name="" type="submit" value="查询"  class="btn_s"/></td>
        </tr>
      </table>                              
      <table class="tabs1"  style="margin-top:0px;">
        <tr onclick="selectedTD(this);">
          <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a></th>
          <th width="28">序号</th>
            <th>服务的名称</th>
          <th>存储中心IP地址</th>
          <th>端口号</th>
          <th>节点连接超时时间</th>
		  <th>创建时间<a href="javascript:void(0)"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
		  <th>操作</th>
        </tr>
          <#list listDatas as entity>
           <tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
           <td class="tright"><input name="ids" type="checkbox" value="${entity.id}" /></td>
          <td>${entity_index+1}</td>
          <td>${entity.serverName?default('无')}</td>
          <td>${entity.address?default('无')}</td>
		  <td>${entity.port?default('无')}</td>
		  <td>${entity.nodeConnTime?default('无')}</td>
		  <td>${entity.createDate?default('无')}</td>
           <td>			
           <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/cloudStorageCenter/cloudStorageCenter!view.action?id=${entity.id}','查看','530','350');" class="tabs1_cz">
	       <img src="${path}/images/small9/s_chakan.gif" />查看</a> 																		  	
          <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/cloudStorageCenter/cloudStorageCenter!updateView.action?id=${entity.id}','编辑','630','550');" class="tabs1_cz">
           <img src="${path}/images/czimg_edit.gif" />编辑</a>
          <a href="#" id="hz0"  class="tabs1_cz" onclick="del('${path}/cloudstorage/cloudStorageCenter/cloudStorageCenter!delete.action?ids=${entity.id}')"><img src="${path}/images/small9/czimg_sc.gif" />删除</a>
          </td>
        </tr>
      </#list>
      </table>
  					<div class="tabs_foot"> 
						 <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
			    		 <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/cloudstorage/cloudStorageCenter/cloudStorageCenter!delete.action')"><b class="hot">删除</b></a>
             			<img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
       					<span class="page pageR">
						   <@pager.pageTag/>
						       </from>
						      </span>
						       </div>
						    </div>    	
						    <#include "/common/commonLhg.ftl">
							<#include "/common/commonList.ftl">												
</body>
</html>