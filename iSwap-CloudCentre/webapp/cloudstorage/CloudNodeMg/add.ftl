<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>云节点管理</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form method="post" action="${path}/cloudstorage/cloudNodeMg/cloudNodeMg!add.action" id="saveForm">
<@s.token name="token"/>
<div class="pop_01" style="width:600px">
  <div class="pop_mian">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p><b>*</b>云节点名称：</p>
                      <span>
                      <input type="text" size="30"  name="nodesName" id="nodesName" maxLength="20"/>
                      </span>
                      <span><div id="nodesNameTip"></div></span>
                    </li>
                    <li class="item_bg">
                      <p><b>*</b>服务地址：</p>
                      <span>
                      <input type="text" size="30" name="serverAddress" id="serverAddress" maxLength="20"/>
                      </span>
                      <span><div id="serverAddressTip"></div></span>
				   </li>
				   <li>
                      <p><b>*</b>总存储量(M)：</p>
                      <span >
                      <input type="text" size="30"  name="storageCount" id="storageCount" maxLength="15"/>
                      </span>
                      <span><div id="storageCountTip"></div></span>
                    </li>
                   <li class="item_bg">
                      <p>描述：</p>
                      <span>
                      <textarea cols="50" rows="4" name="remark" id="remark"></textarea>
                      </span> </li>
                      <span><div id="remarkTip"></div></span>
                  </ul>
                </div></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </div>
</div>
</from>
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
 <!--  公用js  -->
<#include "/common/commonUd.ftl">
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/cloudnode/cloudnode.js'></script>
</body>
</html>