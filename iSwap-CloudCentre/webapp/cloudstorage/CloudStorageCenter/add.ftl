<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>储中心配置</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body >
<form method="post" action="${path}/cloudstorage/cloudStorageCenter/cloudStorageCenter!add.action" id="saveForm">
<@s.token name="token"/>
<div class="pop_01"  style="width:700px;height:500;overflow-x:hidden;overflow-y:scroll;">
  <div class="pop_mian">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p><b>*</b>中心服务的名称：</p>
                      <span>
                      <input type="text" size="30"  name="serverName" id="serverName"/>
                      </span>
                      <span><div id="serverNameTip"></div></span>
                    </li>
                    <li class="item_bg">
                      <p><b>*</b>存储中心的地址：</p>
                      <span>
                      <input type="text" size="30" name="address" id="address"/>
                      </span>
                      <span><div id="addressTip"></div></span>
				   </li>
				   <li>
                      <p><b>*</b>端口：</p>
                      <span >
                      <input type="text" size="30"  name="port" id="port" maxLength="30"/>
                      </span>
                      <span><div id="portTip"></div></span>
                    </li>
                   <li class="item_bg">
                      <p> <b>*</b>数据存储地址：</p>
                      <span>
                      <input size="30" name="dataPath" id="dataPath"></input>
                      </span>
                      <span><div id="dataPathTip"></div></span>
                       </li>
                           <li>
                      <p><b>*</b>日志存储地址：</p>
                      <span>
                      <input size="30" name="logPath" id="logPath"></input>
                      </span>
                        <span><div id="logPathTip"></div></span>
                       </li>
                           <li class="item_bg">
                      <p><b>*</b>索引存储地址：</p>
                      <span>
                      <input size="30" name="indexPath" id="indexPath"></input>
                      </span>
                      <span><div id="indexPathTip"></div></span>
                       </li>
                       <li>
                      <p> <b>*</b>存储检测文件地址：</p>
                      <span>
                      <input size="30" name="storagePath" id="storagePath"></input>
                      </span>
                      <span><div id="storagePathTip"></div></span>
                       </li>
                        <li class="item_bg">
                      <p><b>*</b>节点连接超时时间：</p>
                      <span>
                      <input size="30" name="nodeConnTime" id="nodeConnTime"></input>
                      </span>
                      <span><div id="nodeConnTimeTip"></div></span>
                       </li>
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
<#include "/common/commonUd.ftl">
<#include "/common/commonValidator.ftl">
 <script type='text/javascript' src='${path}/js/validator/cloudnode/cloudnodeCenter.js'></script> 
</body>
</html>