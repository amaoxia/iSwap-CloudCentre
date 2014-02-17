<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>指标信息</title>
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
                         ${itemName?default('')}
                      </p>
                    </li>
                    <li class="item_bg">
                      <h1>所属部门:</h1>
                      <p>
                 			  ${entityobj.sysDept.deptName?default('')} 
                      </p>
                    </li>
                    <#if entityobj.dataSource?exists>
                    <li>
                      <h1>数据源:</h1>
                      <p>
					  ${entityobj.dataSource.sourceName?default('')}
                 	  </p>
                 	  </li>
                 	  <li  class="item_bg">
                      <h1>表名称:</h1>
                      <p>
					  ${entityobj.tableName	?default('')}
                 	  </p>
                 	  </li>
                 	  </#if>	
                      <li >
                      <h1>指标编码:</h1>
                      <p>
                      ${entityobj.itemCode?default('')}
                      </p>
                      </li>
                      <li  class="item_bg">
                      <h1>数据类型:</h1>
                      <p>  <#if entityobj.dataType?exists>
	                    <#if entityobj.dataType=='0'>
	                       	 文档类型
                      <#else>
                      		数据源类型
                      </#if>
					</#if>
                      </p>
                      </li>
                       <#if entityobj.dataStructure?exists>
                      <li >
                      <h1>文档数据结构:</h1>
                      <p>
                      <#if entityobj.dataStructure=='0'>
                      		非结构化数据
                      <#else>
                   	   结构化数据
                      </#if>
                      </p>
                      </li>
                      <li class="item_bg">
                      <h1>文档数据具体类型:</h1>
                      <p>
                      ${entityobj.dataValue?default('')}
                      </p>
                      </li>
                       </#if>
                        <li>
                      
                      <li class="item_bg">
                      <h1>创建时间:</h1>
                      <p>
                      ${entityobj.createDate?default('')}
                      </p>
                      </li>
                  </ul>
				  </div>
				</div>
</body>
</html>