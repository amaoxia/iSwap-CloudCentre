<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>需求目录配置</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	</head>
	<body>
		<form name="pageForm" method="post"  action="${path}/exchange/receiveDeptItem/receiveDeptItem!list.action">
			<input type="hidden" name="deptid" id="deptid" value="${deptid}"/>
	      	<div class="common_menu">
		      	<div class="c_m_title">
		      		<img src="${path}/images/title/item.png"  align="absmiddle" />数据需求目录
		      	</div>
			  	<div class="c_m_btn"> 
			  		<span class="cm_btn_m">
				  		<a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/exchange/sendItemDept/sendItemDept!addView.action?deptId=${deptid}','需求目录配置','470','540')">
						  	<b>
							  	<img src="${path}/images/czimg_edit.gif" class="cmb_img" />
						  		需求目录配置
						  		<a href="javascript:void(0)">
						  			<img src="${path}/images/bullet_add.png" class="bullet_add" />
						  		</a>
					  		</b>
				  		</a>
				  	</span>
				  	<span class="cm_btn_m">
				  		&nbsp;
				  	</span>
			  </div>
	        </div>
	    	<div class="main_c">
	           <table class="main_drop">
			   </table>                              
		       <table class="tabs1"  style="margin-top:0px;">
			      	<tr>
			          <th width="5%">
			          	<a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">
			          	</a>
			          </th>
			          <th width="5%">序号</th>
			          <th width="40%">指标项名称</th>
			          <th width="30%">发送部门</th>
					  <th width="20%">操作</th>
			        </tr>
		        	<#list listDatas as entity>
				  	<tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
					  <td>
					  	<input name="ids" type="checkbox" value="${entity.id}" />
					  </td>
			          <td>
			          	${entity_index+1}
			          </td>
			          <td title="${entity.changeItem.itemName?default('')}">
			          	<#if (entity.changeItem.itemName?exists)&&(entity.changeItem.itemName?length lt 13)>
						  	${entity.changeItem.itemName?default('')}
						<#else>
							${entity.changeItem.itemName[0..12]}... 
						</#if> 
			          </td>
			          <td>
			          	${entity.changeItem.sysDept.deptName?default('无')}
			          </td>
			          <td>
			          	 <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/changeItem/changeItem!view.action?id=${entity.changeItem.id}','查看','570','460');" class="tabs1_cz">
				          	<img src="${path}/images/small9/s_chakan.gif" />查看
				          </a> 
				           <a href="#" onclick="opdg('${path}/cloudstorage/tableinfo/tableinfo!list.action?itemId=${entity.changeItem.id}&view=1','表结构','1000','470');" class="tabs1_cz" >
				          	<img src="${path}/images/small9/s_biaojiegou.gif"/>表结构
				           </a>
			             <a href="#" id="hz0"  class="tabs1_cz" onclick="del('${path}/exchange/receiveDeptItem/receiveDeptItem!delete.action?ids=${entity.id}')">
			           		<img src="${path}/images/small9/czimg_sc.gif" />删除
			           	 </a>
			          </td>
			       </tr>
		        </#list>
		      </table>
		      <div class="tabs_foot"> 
			       	<img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
			       	<a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">
			       		全选
			       	</a>
			        <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
				    <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/exchange/receiveDeptItem/receiveDeptItem!delete.action')">
				   	 <b class="hot">删除</b>
				   	</a>
					<span class="page pageR">
			       		<@pager.pageTag/>
			      	</span>
		       </div>
		   </div>
		   <div class="clear"></div>
		</form>
			<#include "/common/commonList.ftl">
			<#include "/common/commonLhg.ftl">
		</body>
	</html>
	<script type="text/javascript">
		function loadAll(){
		}
	</script>