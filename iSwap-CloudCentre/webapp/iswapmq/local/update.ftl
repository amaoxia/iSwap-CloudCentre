<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/common/taglibs.ftl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="pop_01" style="width:100%;height:100%;overflow-x:hidden;overflow-y:scroll;">  
  <div class="pop_mian">
  
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%">
        <form  name="saveForm" id="saveForm"  action="queueInfo!update.action" method="post">
        <input type="hidden" id="id" value="${entityobj.id}">
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p>队列名称：</p>
                      <span>
                      <input name="queueName" id="queueName" value="${entityobj.queueName?default("")}" type="text" size="30" />
                      </span>
                      <span><div id="queueNameTip"></div></span>
                    </li>
					<li class="item_bg">
                      <p>服务类型：</p>
                     <span>
                        <select name="serType" id="serType" >
			              <option value="" >所有类型</option>
			              <option value="发送队列" <#if entityobj.serType?exists&& entityobj.serType== '发送队列' >selected</#if>>发送队列</option>
			              <option value="接收队列" <#if entityobj.serType?exists&& entityobj.serType== '接收队列' >selected</#if>>接收队列</option>
			            </select>
                      </span>
                       <span><div id="serTypeTip" ></div></span>
                    </li>
                    <li>
                      <p>队列深度：</p>
                     <span>
                      <input name="queueDepth" id="queueDepth" value="${entityobj.queueDepth?default("")}" type="text" size="30" />
                      </span>
                      <span><div id="queueDepthTip" ></div></span>
                    </li>
                    <li class="item_bg">
                      <p>队列大小：</p>
                      <span>
                      <input name="queueSize" id="queueSize" value="${entityobj.queueSize?default("")}" type="text" size="30" />
                      </span>
                      <span><div id="queueSizeTip" ></div></span>
                    </li>
                    <li>
                      <p>所属部门：</p>
                     <span>
                      <select name="sysDept.id" id="deptId">
                        <option value="">请选择</option>
                         <#list listDepts as entity>
	                         <#if entityobj.sysDept?exists >
	                            <option value="${entity.id}" <#if entityobj.sysDept.id == entity.id>selected</#if>>${entity.deptName}</option>
	                         </#if>
                         </#list>
                      </select>
                      </span>
                       <span><div id="deptIdTip" ></div></span>
                    </li>
                    <li class="item_bg">
                      <p>描述：</p>
                      <span>
                      <textarea name="description" id="description" cols="90" rows="4">${entityobj.description?default("")}</textarea>
                       <span><div id="descriptionTip" ></div></span>
                      </span> 
                     </li>
                   </ul>
                     <input type="hidden" name="createTime" id="createTime" value="${entityobj.createTime?default("")}"/>
                 </td>
            </tr>
          </table>
          </form>
          </td>
        <td rowspan="2" class="pm_right"><img src="${path}/images/pop_051.png" width="7" height="1" /></td>
      </tr>
     
    </table>
  </div>
</div>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type='text/javascript' src='${path}/js/validator/iSwapMQ/mqValidator.js'></script> 
<#include "/common/commonValidator.ftl">
<#include "/common/commonUd.ftl">
</body>
</html>
