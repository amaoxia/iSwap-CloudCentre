<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>指标管理</title>
	<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
	<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	<script  type="text/javascript">
		var DG = frameElement.lhgDG; 
		DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
		//关闭窗口 不做任何操作
		function singleCloseWin(){
			DG.curWin.closeWindow();
		}
	</script>
</head>
<body class="pm01_c">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle"  height="100%">
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                  	<li class="item_bg">
                      <p>所属应用:</p>
                      <span>
                      	${appMsg.appName}
                      </span>
                    </li>
                    <li>
                      <p>所属指标:</p>
                      <span>
                      	${appItem.appItemName}
                      </span>
                    </li>
                    <li class="item_bg">
                      <p>数据提供部门:</p>
                      <span>
                      	${sendDept.deptName}
                      </span>
                    </li>
                    <li>
                      <p>数据接收部门:</p>
                      <span>
                      	<#list appItemExchangeConfDetails as appItemExchangeConfDetail>
			          		${appItemExchangeConfDetail.receiveDept.deptName},
			            </#list>
                      </span>
                    </li>
                    <li class="item_bg">
                      <p>是否共享:</p>
                      <span>
                      	<#if (isShare==0)>
                      		是
                      	<#else>
							否
						</#if> 
                      </span>
                    </li>
                      <li>
						  <p>描述</p>
						  <span>
						  	${remark}
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
    </body>
</html>
<script type="text/javascript"> 
	$(document).ready(function(){
	});
</script>
