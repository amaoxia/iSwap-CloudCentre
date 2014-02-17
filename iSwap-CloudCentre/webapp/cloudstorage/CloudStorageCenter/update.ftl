<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>储中心配置</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jstree/jquery.js"></script>
<#include "/common/commonUd.ftl">
<#include "/common/commonValidator.ftl">
 <script type='text/javascript' src='${path}/js/validator/cloudnode/cloudnodeCenter.js'></script>
</head>
<body >
<form method="post" action="${path}/cloudstorage/cloudStorageCenter/cloudStorageCenter!update.action" id="saveForm">
<div class="pop_01" style="width:700px">
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
                      <input type="text" size="30"  name="serverName" id="serverName" value="${serverName?default('')}"/>
                         <input type="hidden"  name="id"  value="${id}"/>
                      </span>
                      <span><div id="serverNameTip"></div></span>
                    </li>
                    <li class="item_bg">
                      <p><b>*</b>存储中心的地址：</p>
                      <span>
                      <input type="text" size="30" name="address" id="address" value="${address?default('')}"/>
                      </span>
                      <span><div id="addressTip"></div></span>
				   </li>
				   <li>
                      <p><b>*</b>端口：</p>
                      <span >
                      <input type="text" size="30"  name="port" id="port" value="${port?default('')}"/>
                      </span>
                      <span><div id="portTip"></div></span>
                    </li>
                   <li class="item_bg">
                      <p> <b>*</b>数据存储地址：</p>
                      <span>
                      <input size="30" name="dataPath" id="dataPath" value="${dataPath?default('')}"></input>
                      </span>
                      <span><div id="dataPathTip"></div></span>
                       </li>
                           <li >
                      <p><b>*</b>日志存储地址：</p>
                      <span>
                      <input size="30" name="logPath" id="logPath" value="${logPath?default('')}"></input>
                      </span>
                      <span><div id="logPathTip"></div></span>
                       </li>
                           <li class="item_bg">
                      <p><b>*</b>索引存储地址：</p>
                      <span>
                      <input size="30" name="indexPath" id="indexPath" value="${indexPath?default('')}"></input>
                      </span>
                        <span><div id="indexPathTip"></div></span>
                       </li>
                       <li>
                      <p><b>*</b>存储检测文件地址：</p>
                      <span>
                      <input size="30" name="storagePath" id="storagePath" value="${storagePath?default('')}"></input>
                      </span>
                      <span><div id="storagePathTip"></div></span>
                       </li>
                        <li class="item_bg">
                      <p><b>*</b>节点连接超时时间：</p>
                      <span>
                      <input size="30" name="nodeConnTime" id="nodeConnTime" value="${nodeConnTime?default('')}"></input>
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
</body>
</html>