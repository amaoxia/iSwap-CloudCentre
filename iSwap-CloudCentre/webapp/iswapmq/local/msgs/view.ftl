<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#global path = request.getContextPath() />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
<#include "/common/commonUd.ftl">
<script type="text/javascript" >
var DG= frameElement.lhgDG; 
function returns(){
	//取消按钮
	DG.removeBtn('reset');
	DG.removeBtn('save');
	
}
</script>
</head>
<body onload="returns()">
<div class="pop_01" style="width:700px">
  <div class="pop_mian">
  
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%">
        <form name="saveMQ" action="queueInfo!add.action" method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                    <li class="item_bg">
                      <p>消息内容：</p>
                      <span>
                      <textarea name="msgBody" id="msgBody" cols="90" rows="5" style="">  
                    	${entityobj.msgBody?default("")}
                      </textarea>
                    
                      </span> </li>
                  </ul>
                </div></td>
            </tr>
          </table>
          </form>
          </td>
        <td rowspan="2" class="pm_right"><img src="${path}/images/pop_051.png" width="7" height="1" /></td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>
