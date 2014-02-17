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
                      <h1>交换周期:</h1>
                      <p>
                       <!--<#if changeItemCycle.exchangeCycleValue=="0">周</#if>-->
	                   <#if changeItemCycle.exchangeCycleValue=="1">月</#if>
	                   <#if changeItemCycle.exchangeCycleValue=="2">季</#if>
	                   <#if changeItemCycle.exchangeCycleValue=="3">年</#if>
                      </p>
                      </li>
                      <li class="item_bg">
                       <h1>交换日期规则:</h1>
                      <p>
                      <#assign sel=changeItemCycle.exchangeDateRule?split(",")[0]>
                      <#assign day=changeItemCycle.exchangeDateRule?split(",")[1]>
                       <!--<#if changeItemCycle.exchangeCycleValue =="0">
                   	        本周数据在
	                      <#if changeItemCycle.exchangeCycleValue=="0"><#if sel=='0'>本周</#if></#if>
	                      <#if changeItemCycle.exchangeCycleValue=='0'><#if sel=='1'>下周</#if></#if>
	                     <#list  1..7 as temp>
   						   <#if changeItemCycle.exchangeCycleValue=='0'><#if day?string==temp?string>${temp}</#if></#if>
						 </#list>
                     	 	 执行
                     	 </#if>-->
                      <!--- 月 --->
                      <#if changeItemCycle.exchangeCycleValue =="1">
                      	本月数据在
	                      <#if changeItemCycle.exchangeCycleValue=='1'><#if sel=='0'>本月</#if></#if>
	                       <#if changeItemCycle.exchangeCycleValue=='1'><#if sel=='1'>下月</#if></#if>
	                     <#list   1..31 as temp>
   						 <#if changeItemCycle.exchangeCycleValue=='1'><#if day?string==temp?string>${temp}</#if></#if>
						 </#list>
                     	 执行
                      </#if>
                      <!--- 季 -->
                        <#if changeItemCycle.exchangeCycleValue =="2">
                     	   本季数据在
	                   <#if changeItemCycle.exchangeCycleValue=='2'><#if sel=='0'>本季</#if></#if>
	                   <#if changeItemCycle.exchangeCycleValue=='2'><#if sel=='1'>下季</#if></#if>
   					   <#if changeItemCycle.exchangeCycleValue=='2'><#if day=='1'>第一月</#if></#if>
   						 <#if changeItemCycle.exchangeCycleValue=='2'><#if day=='2'>第二月</#if></#if>
   						<#if changeItemCycle.exchangeCycleValue=='2'><#if day=='3'>第三月</#if></#if>
                     	 的第
	                     <#list 1..31 as temp>
   						  <#if changeItemCycle.exchangeCycleValue=='2'><#if (changeItemCycle.exchangeDateRule?split(",")[2])?string==temp?string>${temp}</#if></#if>
						 </#list>
                     	 日执行
                      </#if>
                      <!--- 年 -->
                           <#if changeItemCycle.exchangeCycleValue =="3">
                       	 	本年数据在
	                    	 <#if changeItemCycle.exchangeCycleValue=='3'><#if sel=='0'> 本年</#if></#if>
	                     	 <#if changeItemCycle.exchangeCycleValue=='3'><#if sel=='1'>下年</#if></#if>
	                     <#list 1..12 as temp>
   						  <#if changeItemCycle.exchangeCycleValue=='3'><#if day?string==temp?string>${temp}</#if></#if>
						 </#list>
                     	 月
	                     <#list 1..31 as temp>
   						 <#if changeItemCycle.exchangeCycleValue=='3'><#if (changeItemCycle.exchangeDateRule?split(",")[2])?string==temp?string>${temp}</#if></#if>
						 </#list>
                     	 日执行
                      </li>
                      </#if>
                       <li>
                      <h1>是否采用系统规则:</h1>
                      <p>
                      ${entityobj.createDate?default('')}
                      </p>
                      </li>
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