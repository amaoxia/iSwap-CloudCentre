<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>任务信息</title>
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
<div class="pop_div6 pm6_c" style="width:550px">
  <div class="item4">
     <ul class="item4_c">
					  <li>
                      <h1>指标名称:</h1>
                      <p>
                         ${entityobj.item.itemName?default('')}
                      </p>
                    </li>
     				<li class="item_bg">
                      <h1>任务名称:</h1>
                      <p>
                         ${entityobj.taskName?default('')}
                      </p>
                    </li>
                    <li >
                      <h1>执行时间:</h1>
                      <p>
                 	  ${entityobj.execDate?default('')} 
                      </p>
                    </li>
                    <li class="item_bg"> 
                      <h1>完成状态:</h1>
                      <p>
                      <#if entityobj.finishedState?exists>
                      <#if entityobj.finishedState=='0'>未完成<#else>完成</#if>
                      </#if>
                 	  </p>
                 	  </li>
                 	  <li>
                      <h1>催办次数:</h1>
                      <p>
						 ${entityobj.transactCount?default(0)}
                 	  </p>
                 	  </li>
                    <li class="item_bg">
                      <h1>按时次数:</h1>
                      <p>
                      ${entityobj.ontimeCount?default(0)}
						</p>
                      </li>
                      <li>
                      <h1>超时次数:</h1>
                      <p>
                      ${entityobj.overtimeCount?default('0')}
                      </p>
                      </li>
                      <li   class="item_bg">
                      <h1>任务所属应用:</h1>
                      <p>
                  	 ${entityobj.apps?default('')}
                      </p>
                      </li>
                      <li>
                      <h1>任务执行开始时间:</h1>
                      <p>
                      ${entityobj.execStartDate?default('')}
                      </p>
                      </li>
                      <li  class="item_bg">
                      <h1>任务执行结束时间:</h1>
                      <p>
                      ${entityobj.execEndDate?default('')}
                      </p>
                      </li>
                      <li>
                      <h1>任务执行最后日期:</h1>
                      <p>
                      ${entityobj.execLastDate?default('')}
                      </p>
                      </li>
                  </ul>
				  </div>
				</div>
</body>
</html>