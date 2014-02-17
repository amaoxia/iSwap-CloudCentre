<html>
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
        <form name="saveForm" id="saveForm" action="queueInfo!add.action" method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p><b style="color:red">*</b> 队列名称：</p>
                      <span><input name="queueName" id="queueName" type="text" size="30" /></span>
                      <span><div id="queueNameTip"></div></span>
                    </li>
					<li class="item_bg">
                      <p><b style="color:red">*</b>服务类型：</p>
                     <span>
                        <select name="serType" id="serType">
			              <option value="" >所有类型</option>
			              <option value="发送队列">发送队列</option>
			              <option value="接收队列">接收队列</option>
			            </select>
                      </span>
                       <span><div id="serTypeTip" ></div></span>
                    </li>
                    <li>
                      <p><b style="color:red">*</b>队列深度：</p>
                     <span>
                      <input name="queueDepth" id="queueDepth" type="text" size="30" />
                      </span>
                      <span><div id="queueDepthTip"></div></span>
                    </li>
                    <li class="item_bg">
                      <p><b style="color:red">*</b>队列大小：</p>
                      <span>
                      <input name="queueSize" id="queueSize"  type="text" size="30" />
                      </span>
                      <span><div id="queueSizeTip" ></div></span>
                    </li>
                    <li>
                      <p><b style="color:red">*</b>所属部门：</p>
                     <span>
                      <select name="sysDept.id" id="id">
                        <option>请选择</option>
                         <#list listDepts as entity>
                            <option  value="${entity.id}">${entity.deptName}</option>
                          </#list>
                      </select>
                      </span>
                      <span><div id="idTip" ></div></span>
                    </li>
                    <li class="item_bg">
                      <p>描述：</p>
                      <span>
                      <textarea name="description" id="description" cols="50" rows="4"></textarea>
                       </span>
                         <span><div id="descriptionTip" ></div></span>
                     
                    </li>
                  </ul>
                  <input type="hidden" name="id" id="id" value="0"/>
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
<script type='text/javascript' src='${path}/js/validator/iswapmq/mqValidator.js'></script>
<#include "/common/commonValidator.ftl">
<#include "/common/commonUd.ftl">
</body>
</html>
