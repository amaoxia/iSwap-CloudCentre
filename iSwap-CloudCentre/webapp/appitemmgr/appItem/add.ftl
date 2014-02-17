<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>新增指标</title>
	<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
	<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body class="pm01_c">
	<form method="post" action="${path}/appitemmgr/appItem/appItem!add.action" id="saveForm">
   <@s.token/>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle"  height="100%">
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p>指标名称:</p>
                      <span>
                      <input type="text" size="30" name="appItemName" id="appItemName"/>
                      </span>
                      <span><div id="appItemNameTip"></div></span>
                    </li>
                    <!-- 
                    <li class="item_bg">
                      <p>指标编码:</p>
                      <span>
                      <input type="text" name="appItemCode" id="appItemCode" size="30"/></span>
                      <span><div id="appItemCodeTip"></div></span>
                      </li>
                     -->
                     <li class="item_bg">
						  <p>描述</p>
						  <span>
						  	<textarea name="remark" cols="42" rows="5"></textarea>
						  </span>
					</li>
                  </ul>
                </div>
                </td>
            </tr>
          </table>
         </td>
      </tr>
    </table>
    </form>
    </body>
</html>
<#include "/common/commonUd.ftl">
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/appitemmgr/appItem/appItem.js'></script> 
<script type="text/javascript"> 
$(document).ready(function(){
	//大小写转换
	$('#itemCode').keypress(function(e) {     
	    var keyCode= event.keyCode;  
	    var realkey = String.fromCharCode(keyCode).toUpperCase();  
	    $(this).val($(this).val()+realkey);  
	    event.returnValue =false;  
	});
});
</script>
