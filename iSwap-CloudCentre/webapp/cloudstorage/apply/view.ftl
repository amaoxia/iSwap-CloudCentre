<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script type='text/javascript'>
var DG = frameElement.lhgDG; 
DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
//关闭窗口 不做任何操作
function singleCloseWin(){
DG.cancel();
}
</script>
</head>
<body>
<div class="pop_01"  style="width:730px;height:492px;overflow-x:hidden;overflow-y:scroll;">
  <div class="pop_mian">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                       <table class="tabs_list">
                       <#assign count=6>
                       <#assign pNum=1>
                       <#if metaAuthList?size lt count>
                       <#list metaAuthList as entity>
                       		<#if entity_index==0>
				                <tr>
				                </#if>
				                  <td style="text-align:left">
				                  <a href="#">
				          	${entity.tableInfo.name?default('')}(<#if entity.filedAuthState=="1">是<#else>否</#if>)
				                  </a>
				                  </td>
				                   <#if entity_index==metaAuthList?size-1></tr></#if>
				                </#list>
				                <#else><!-- 多行-->
				                			<#list metaAuthList as entity>
					                       		<#if  (pNum-1)*count==entity_index>
									                <tr>
									                </#if>
									                  <td style="text-align:left">
									                  <a href="#">
									                 ${entity.tableInfo.name?default('')}( <#if entity.filedAuthState=="1">是<#else>否</#if>)
									                  </a>
									                  </td>
									                  <#if (entity_index+1)==metaAuthList?size>
									                  </tr>
									                  <#else>
									                  <#if (entity_index+1)==pNum*count></tr><#assign pNum=pNum+1></#if>
									                  </#if>
									                </#list>
                      			</#if>
				        </table>
                    </li>
                  </ul>
                </div></td>
            </tr>
          </table></td>
      </tr>
      <tr>
      </tr>
    </table>
  </div>
</div>
</body>
</html>