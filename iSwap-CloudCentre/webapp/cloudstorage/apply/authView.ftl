<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script  type="text/javascript">
var DG = frameElement.lhgDG; 
DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
//关闭窗口 不做任何操作
function singleCloseWin(){
DG.curWin.closeWindow();
}
</script>
</head>
<body>
<div class="pop_01" style="width:430px">
  <div class="pop_mian">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p>申请指标项名称：</p>
                      <span>
                       ${entityobj.metaData.targetName?default('')}
                      </span>
                    </li>
                    <li class="item_bg">
                      <p>申请部门：</p>
                      <span>
                       ${entityobj.sysDept.deptName?default('')}
                      </span></li>
                       <li >
                      <p>应用名称：</p>
                      <span>
                      <#if entityobj.appMsg?exists>
                         ${entityobj.appMsg.appName?default('')}
                      </#if>
                      </span></li>
                      
                    <li  class="item_bg">
                      <p>申请函：</p>
                      <span>${entityobj.filedApplyContent?default('')}</span></li>
                    <li>
                      <p>申请时间：</p>
                      <span>${entityobj.filedApplyDate?default('')}</span></li>
                    <li>
                  </ul>
                </div></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>

