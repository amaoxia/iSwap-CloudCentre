<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>发送指标项</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	</head>
	<body onclick="parent.hideMenu();">
		<form name="pageForm" method="post"  action="${path}/exchange/sendItem/sendItem!list.action">
			<input type="hidden" name="deptId" id="deptId" value="${deptId}"/>
			<input type="hidden" name="deptName" id="deptName" value="${deptName}"/>
			    <div class="common_menu">
			      <div class="c_m_title">
			      	<img src="${path}/images/title/item.png"  align="absmiddle" />
			      		发送指标项
			      </div>
			    </div>
		    	<div class="main_c">
		           <table class="main_drop">
   		 			  <tr>
	          			<td align="right">
			  				指标项名称：	<input name="conditions[e.itemName,string,like]"  onpaste="return false" value="${serchMap.e_itemName?default("")}" type="text"  />
					        <input name="" type="submit" value="查询"  class="btn_s"/>
					     </td>
					  </tr>
					</table>                              
			      	<table class="tabs1"  style="margin-top:0px;">
			          <th width="2%"><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a></th>
			          <th width="5%">序号</th>
			          <th width="15%">指标项名称</th>
			          <th width="10%">所属部门</th>
					  <th width="10%">创建时间<a href="javascript:void(0)" onclick="setOrder(this)" name="e.createDate"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
					  <!-- <th width="10%">是否已推送至目录</th>-->
					  <th width="25%">操作</th>
			        </tr>
			       <#list listDatas as entity>
					<tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
					 <td width="2%"><input name="ids" type="checkbox" value="${entity.id}" /></td>
			         <td width="5%">${entity_index+1}</td>
			         <td title="${entity.itemName?default('')}">
			          	<#if (entity.itemName?exists)&&(entity.itemName?length lt 13)>
						  	${entity.itemName?default('')}
						<#else>
							${entity.itemName[0..12]}... 
						</#if> 
			          </td>
			          <td width="4%">${entity.sysDept.deptName?default('无')}</td>
					  <td width="10%">${entity.createDate?date?default('无')}</td>
					  <!--<td>${entity.hasProped?string('是','否')}</td>-->
			          <td width="15%">
				          <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/exchange/item/item!view.action?id=${entity.id}','查看','570','460');" class="tabs1_cz">
				          	<img src="${path}/images/small9/s_chakan.gif" />查看
				          </a> 
				          <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/exchange/sendItem/sendItem!updateView.action?id=${entity.id}&deptId=${deptId}&deptName=${deptName}','编辑','630','590');" class="tabs1_cz">
				          	<img src="${path}/images/czimg_edit.gif" />编辑
				          </a> 
				          <#if entity.dataType?exists>
				           <a href="#"  <#if entity.dataType=='1'> onclick="opdg('${path}/exchange/tabledesc/tabledesc!addDesc.action?itemId=${entity.id}','表结构','1000','470');"<#else>style="color:#ccc;"</#if> class="tabs1_cz" >
				          	<img src="${path}/images/small9/s_biaojiegou.gif"/>表结构
				           </a>
				          </#if>
				          <a href="#" id="hz0"  class="tabs1_cz" onclick="del('${path}/exchange/sendItem/sendItem!delete.action?ids=${entity.id}')">
				           <img src="${path}/images/small9/czimg_sc.gif" />删除
				          </a>
			          </td>
			        </tr>
			        </#list>
			      </table>
			      <div class="tabs_foot"> 
				       <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
	    			    <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/exchange/sendItem/sendItem!delete.action')"><b class="hot">删除</b></a>
     					<!-- 推送目录暂时关闭 start 2011.10.22-->
     					<!--img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" /-->
     					
     					 <!--a href="javascript:void(0)" class="tfl_blink" onclick="pushToCatalog()"><b class="hot">推送目录</b></a-->
     					<!--img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" /-->
						<!-- 推送目录暂时关闭 end 2011.10.22-->
						<span class="page pageR">
				      		 <@pager.pageTag/>
				        </span>
			       </div>
			    </div>
			    <div class="clear"></div>
			</form>
			<#include "/common/commonList.ftl">
			<#include "/common/commonLhg.ftl">
			<script type='text/javascript'>
			function pushToCatalog(){
				if($("input[name='ids']:checked ").length==0){
					alert("请选择记录！");
					return;
				}
				var ids="";
				$("input[name='ids']:checked ").each(function(){
					ids=ids+","+$(this).val();
				});
				opdg('${path}/exchange/item/item!selectCatalog.action?itemIds='+ids,'选择目录','430','460');
			}
			</script>
	</body>
</html>