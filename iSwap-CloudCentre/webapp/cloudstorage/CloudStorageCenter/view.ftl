<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>云中心信息</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<!--  关闭当前窗口-->
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
<div class="pop_div6 pm6_c" style="width:530px">
  <div class="item4">
     <ul class="item4_c">
                    <li>
                      <h1 style="width:150px">中心服务的名称：</h1>
                      <p>  ${serverName?default('')}</p>
                    </li>
                    <li class="item_bg">
                      <h1 style="width:150px">存储中心的地址：</h1>
                      <p>
                      ${address?default('')}
                      </p>
				   </li>
				   <li>
                      <h1 style="width:150px">端口：</h1>
                     ${port?default('')}
                    </li>
                   <li class="item_bg">
                      <h1 style="width:150px"> 数据存储地址：</h1>
                      <p>
                      ${dataPath?default('')}
                      </p>
                       </li>
                           <li >
                      <h1 style="width:150px">日志存储地址：</h1>
						<p>
                      ${logPath?default('')}
                      </p>
                       </li>
                           <li class="item_bg">
                      <h1 style="width:150px">索引存储地址：</h1>
                      <p>
                 	 ${indexPath?default('')}
                      </p>
                       </li>
                       <li>
                      <h1 style="width:150px"> 存储检测文件地址：</h1>
                      <p>
                        ${storagePath?default('')}
                      </p>
                       </li>
                        <li class="item_bg">
                      <h1 style="width:150px">节点连接超时时间：</h1>
                      <p>
                     	 ${nodeConnTime?default('')}
                       </p>
                       </li>
                  </ul>
  </div>
</div>
</body>
</html>