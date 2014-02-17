<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>考核评分汇总表</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<!--日期控件-->
<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
<script>
function sub(){
			document.checkselect.submit();
		}
</script>
</head>

<body>
<form name="checkselect" id="checkselect" action="${path}/performancemanage/check/check!checkSelect.action" method="post"/>
<div class="frameset_w" style="height:100%">
  <div class="main">
  	 <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />考核评分查询</div>
    </div>
    <div class="tabs3_title">综合治税考核评分查询</div>
    <div class="main_c">
    <div class="tabs3_w">
    <div class="tabs3_scroll">
    <table class="main_drop" style="margin:6px auto 0px auto;">
    
        <tr>
          <td align="right">
          部门名称：<input type="text" name="deptName" onkeypress="showKeyPress()" onpaste="return false" value="${deptName?default('')}"/>
	      	开始时间：<input id="d4311" class="Wdate" value="${start?default('')}" name="start" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4312\',{M:-3});}',maxDate:'#F{$dp.$D(\'d4312\');}'})"/>
        至
        <input id="d4312" class="Wdate" name="end" value="${end?default('')}" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'#F{$dp.$D(\'d4311\',{M:3});}'||'#Year#-#Month#-#Day#'})"/>
	      	
	        <input name="" type="button"  value="查询"  class="btn_s" onclick="sub();"/>	
          </td>
        </tr>
        
      </table>
    
      <table class="tabs3">
        <thead>
          <tr>
            <th rowspan="2">考核名称</th>
            <th rowspan="2" class="nopadding"><img src="${path}/images/tabs03_th_img.jpg" /></th>
            <#list list as headlist>
            <th colspan="${headlist[1].size()}">${headlist[0]}</th>
            </#list>
            <th rowspan="2">总分</th>
            </tr>
          <tr>
          <#list list as headlist>
          <#list headlist[1] as childlist>
          <th height="50">${childlist.name}</th>
          </#list>
          </#list>
          </tr>
        </thead>
        <tbody>
 		<#assign y=0>
        <#list objList as obj>
        <#if obj[2].size()!=0>
        <#assign y=y+1>
          <tr <#if y%2==0>class="trbg"</#if> onclick="selectedTD(this);">
            <td>${obj[0]?default('')}</td>
            <#assign i=0>
            <td><p>${obj[1]?default('')}</p></td>
            <#list list as headlist>
	          <#list headlist[1] as childlist>
	          <#assign tag=false>
	            <#list obj[2] as target>
	            <#assign key=childlist.id>
	            <#list target?keys as testKey>
		            <#if testKey?string==childlist.id?string>
		            	<td>${target.get(testKey)?default('-')}
			              <#assign n=target.get(testKey)>
			              <#if target.get(testKey)==''>
			              	<#assign n=0>
			              </#if>
			               <#assign i=(n?number)+i>
		               </td>
		               <#assign tag=true>
		             </#if>
	            </#list>
	           </#list>
	            <#if !tag><td>-</td></#if>
	         </#list>
         </#list>
            <td> ${i}</td>
            </tr>
        </#if>
        </#list>
      
          </tbody>
      </table>
      
      </div>
</div>
      
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
</form>
</body>
</html>
