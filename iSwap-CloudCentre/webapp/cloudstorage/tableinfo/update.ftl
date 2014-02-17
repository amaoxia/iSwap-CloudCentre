<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表结构管理</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="pop_01"   style="width:100%;height:100%;overflow-x:hidden;overflow-y:scroll;">
  <div class="pop_mian">
  <form id="saveForm" action="${path}/cloudstorage/tableinfo/tableinfo!update.action" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li >
                      <p>字段中文名：</p>
                      <span>
                      <input type="text" size="30"  name="name" value="${name?default('')}" id="filedName"/>
                      <input type="hidden"   name="id" value="${id}"/>
                      <input type="hidden"   name="metaData.id" value="<#if metaData?exists>${metaData.id}</#if>"/>
                      </span>
                      <span><div id="filedNameTip"></div></span>
                    </li>
                     <li class="item_bg">
                      <p>字段代码：</p>
                      <span>
                      <input type="text" size="30" name="filedcode" value="${filedcode?default('')}" id="filedcode"  readOnly="true"/>
                      </span>
                        <span><div id="filedcodeTip"></div></span>
                    </li>
                       <li>
                      <p>字段长度：</p>
                      <span>
                      <input type="text" size="30"    readOnly="true" name="filedLength" value="${filedLength?default('')}" id="filedLength"/>
                      </span>
                       <span><div id="filedLengthTip"></div></span>
                    </li>
                    <li class="item_bg">
                      <p>数据类型：</p>
                      <span>
                      <input type="text" size="30" name="dataType" value="${dataType?default('')}" id="dataType" reanOnly="true"/>
                      </span>
                      <span><div id="dataTypeTip"></div></span>
					 </li>
					 <li>
                      <p>是否主键：</p>
                      <span>
                      <input type="radio" name="isPk" value = "1" <#if isPk=='1'>checked</#if>>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="radio" name="isPk" value = "0" <#if isPk=='0'>checked</#if>>否
                      </span>
					 </li>
					 <li class="item_bg">
                      <p>可否为空：</p>
                      <span>
                      <input type="radio" name="isNull" value = "1" <#if isNull=='1'>checked</#if>>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="radio" name="isNull" value = "0" <#if isNull=='0'>checked</#if>>否
                      </span>
					 </li>
                  </ul>
                </div></td>
            </tr>
          </table></td>
      </tr>
    </table>
    </from>
  </div>
</div>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<#include "/common/commonUd.ftl">
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/metaData/tableinfo.js'></script> 
 <script type="text/javascript"> 
DG.addBtn( 'back', '上一步', returns); 
function returns(){
	//取消按钮
	DG.removeBtn('reset');
	DG.removeBtn('save');
	DG.removeBtn('back');
	DG.removeBtn('close');
	//实现逻辑
	DG.dgWin.history.back(-1);
	DG.reDialogSize(1000,470);
	DG.SetPosition('center','center'); 
}
</script>
</body>
</html>

