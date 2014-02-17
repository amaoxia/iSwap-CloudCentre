<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色添加</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
 <!--验证js-->
<#include "/common/commonUd.ftl">
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/system/roleValidator.js'></script> 
</head>
<body>
<div class="pop_01" style="width:700px">
<form action="${path}/sysmanager/role/role!add.action" method="post" name="saveForm" id="saveForm" >
<@s.token name="token"/>
<input type="hidden"  id="id" value="0"/>
  <div class="pop_mian">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p><b>*</b>角色名称：</p>
                      <span>
                      <input type="text" size="30" id="name"  name="name" maxlength="20"/>
                      </span>
                     <span> <div id="nameTip"></div></span>
                    </li>
                    <li class="item_bg">
					  <p><b>*</b>角色代码：</p>
                      <span>
                      <input type="text" size="30" id="code" name="code" />
                      </span>
                      <span><div id="codeTip"></div></span>
                    </li>
                     <li>
                      <p>角色描述：</p>
                      <span>
                      <textarea cols="50" rows="4" name="descript" id="descript"></textarea>
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
				<script type="text/javascript">
					//大写转换
			    	$('#code').keypress(function(e) {     
			        var keyCode= event.keyCode;  
			        var realkey = String.fromCharCode(keyCode).toUpperCase();  
			        $(this).val($(this).val()+realkey);  
			        event.returnValue =false;  
			   	    });
			   	    </script>
</body>
</html>

