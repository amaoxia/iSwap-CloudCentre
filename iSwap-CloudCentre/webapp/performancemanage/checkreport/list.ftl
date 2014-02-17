<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#import "/common/pager.ftl" as pager>
<#global path = request.getContextPath() />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${request.getContextPath()}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${request.getContextPath()}/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${request.getContextPath()}/css/pop.css" rel="stylesheet" type="text/css" />
<!--弹出窗口-->
<script type="text/javascript" src="${request.getContextPath()}/js/windowopen/lhgcore.min.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/windowopen/lhgdialog.js"></script>

<!--日期控件-->
<script type='text/javascript' src='${request.getContextPath()}/js/datepicker/WdatePicker.js'></script>

<script>    
	var dg = new J.dialog({ id:'hz0', page:'khtb_info.html',title:'[发改委－2011年第一季度综合治税考核评分] 考核通报', cover:true ,rang:true,width:720,height:370  });
		function opdg(){
			dg.ShowDialog();
		}
		
		function openwindow(url,title,widths,height){
			var dg = new J.dialog({page:url,title:title, cover:true ,rang:true,width:widths,height:height  });
			dg.ShowDialog();
		}
</script>
</head>

<body onclick="parent.hideMenu()">
<#setting number_format="0">
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${request.getContextPath()}/images/title/img_05.png"  align="absmiddle" />考核通报</div>
      
    </div>
    <div class="main_c">
        <table class="main_drop">
        <tr>
          <td align="right">考核通报名称：
            <input name="" type="text" onkeypress="showKeyPress()" onpaste="return false" />
            考核时间：
            <input id="d4311" class="Wdate" name="start" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/>
        至
        <input id="d4312" class="Wdate" name="end" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})"/>
        <input name="" type="button"  value="查询"  class="btn_s"/>
           </td>
        </tr>
      </table>
      <table class="tabs1" style="margin-top:1px;">
        <tr>
        	<th>序号</th>
          <th>考核通报名称</th>
          <th>考核时间</th>
          <th>系统生成的通报下载</th>
          <th>人工修改的通报上传/下载</th>
        </tr>
        <#assign i = 0>  
        <#list listDatas as list> 
        <#assign i = i+1>
            <tr class="trbg">
	           <td>${i+(page.index-1)*10}</td>
	           <td><!--a href="javascript:void(0)" id="hz0" onclick="location.href='tablist4.html'"></a-->${list.name?default('')}</td>
	           <td>2011-04-20</td>
	           <td><a href="javascript:void(0);" onclick="openwindow('${request.getContextPath()}/performancemanage/sysreport/sysreportMgr!list.action?check.id=${list.id?default('')}&check.name=${list.name?default('')}','系统生成的通报下载',500,350)" class="tabs3_cz"><img src="${request.getContextPath()}/images/small9/down.gif" />下载</a></td>
	           <td>
		           <a href="javascript:void(0);" onclick="openwindow('${request.getContextPath()}/performancemanage/finallyreport/finallyReportMgr!addView.action?check.id=${list.id?default('')}&check.name=${list.name?default('')}','人工修改通报上传',500,350)" class="tabs3_cz"><img src="${request.getContextPath()}/images/small9/up.gif" />上传</a>
		           <a href="javascript:void(0);" onclick="openwindow('${request.getContextPath()}/performancemanage/finallyreport/finallyReportMgr!list.action?check.id=${list.id?default('')}&check.name=${list.name?default('')}','人工修改的通报下载',500,350)" class="tabs3_cz"><img src="${request.getContextPath()}/images/small9/down.gif" />下载</a></td>
		       </tr>
        </#list>
      </table>
      <div class="tabs_foot"> <span class="tfl_btns"><a href="javascript:void(0)" class="tfl_blink"></a></span><img src="${request.getContextPath()}/images/tabsf_bg.png" height="23"/> 
      <span class="page pageR"><@pager.pageTag/></span></div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
</body>
</html>
