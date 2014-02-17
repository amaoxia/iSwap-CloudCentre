<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基础库类型</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jstree/jquery.js"></script>
 <!--验证js-->
<#include "/common/commonUd.ftl">
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/metaData/basicType.js'></script>
</head>
<body>
<div class="pop_01" style="width:700px">
<form action="${path}/cloudstorage/basicType/basicType!update.action" method="post" name="saveForm" id="saveForm" >
  <div class="pop_mian">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p><b>*</b>基础库类型名称：</p>
                      <span>
                      <input type="hidden" value="${id}" name="id" id="id"/>
                      <input type="text" size="30" id="basicTypeName"  name="basicTypeName" maxlength="30" value="${basicTypeName?default('')}"/>
                      </span>
                     <span> <div id="basicTypeNameTip"></div></span>
                    </li>
                    <li class="item_bg">
					  <p><b>*</b>基础库类型编码：</p>
                      <span>
                      <input type="text" size="30" id="basicTypeCode" name="basicTypeCode"  value="${basicTypeCode?default('')}"/>
                      </span>
                      <span><div id="basicTypeCodeTip"></div></span>
                    </li>
                     <li>
                      <p>基础库类型描述：</p>
                      <span>
                      <textarea cols="60" rows="4" name="descript" id="descript">${descript?default('')}</textarea>
                      </span> 
                      <span> <div id="descriptTip"></div></span>
                      </li>
                  </ul>
                </div></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </div>
</div>
</form>
</body>
</html>

