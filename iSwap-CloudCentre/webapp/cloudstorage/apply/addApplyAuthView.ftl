<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form nam="saveForm" id="saveForm" method="post" action="${path}/cloudstorage/apply/apply!addMetaAuth.action?id=${id}">
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
                       <#assign pNum=1>
                       <#if metaAuthList?size lt 6>
                       <#list metaAuthList as entity>
                       		<#if entity_index==0>
				                <tr>
				                </#if>
				                  <td style="text-align:left">
				                  <a href="#">
				                  <input type="checkbox"   value="${entity.tableInfo.id}" name="ids"/>&nbsp;&nbsp;${entity.tableInfo.name?default('')}
				                  </a>
				                  </td>
				                   <#if entity_index==metaAuthList?size-1></tr></#if>
				                </#list>
				                <#else><!-- 多行-->
				                			<#list metaAuthList as entity>
					                       		<#if  (pNum-1)*6==entity_index>
									                <tr>
									                </#if>
									                  <td style="text-align:left">
									                  <a href="#">
									                  <input type="checkbox"   value="${entity.id}" name="ids" checked/>&nbsp;&nbsp;${entity.tableInfo.name?default('')}
									                  </a>
									                  </td>
									                  <#if (entity_index+1)==metaAuthList?size>
									                  </tr>
									                  <#else>
									                  <#if (entity_index+1)==pNum*6></tr><#assign pNum=pNum+1></#if>
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
<#include "/common/commonUd.ftl">
<script>
function isSub(){
}
</script>
</body>
</html>

