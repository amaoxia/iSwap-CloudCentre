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
<script>
var dg = new J.dialog({ id:'hz0', page:'pmtb_info.html',title:'2011年第一季度综合治税考核排名', cover:true ,rang:true,width:1000,height:510  });
		function opdg(  ){
			dg.ShowDialog();
		}
</script>
</head>

<body onclick="parent.hideMenu()">
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${request.getContextPath()}/images/title/img_05.png"  align="absmiddle" />排名通报信息列表</div>
      
    </div>
    <div class="main_c">
      <table class="tabs1">
        <tr>
        	<th>序号</th>
          <th>考核排名表</th>
          <th>考核时间</th>
          <th>下载排名表</th>
        </tr>
         <#assign i = 0>  
        <#list listDatas as list> 
        <#assign i = i+1>
            <tr class="trbg">
	           <td>${i+(page.index-1)*10}</td>
	           <td><a href="${request.getContextPath()}/performancemanage/checkreport/checkRankMgr!deptlist.action?check.id=${list.id?default('')}&check.name=${list.name?default('')}" id="hz0">${list.name?default('')}</a></td>
	           <td>2011-04-20</td>
	           <td><a href="${request.getContextPath()}/performancemanage/checkreport/checkRankMgr!downloadExcel.action?check.id=${list.id?default('')}&check.name=${list.name?default('')}" class="tabs3_cz"><img src="${request.getContextPath()}/images/small9/down.gif" />下载</a></td>
        </#list>
      </table>
 <div class="tabs_foot"> <span class="tfl_btns"><a href="javascript:void(0)" class="tfl_blink"></a></span><img src="${request.getContextPath()}/images/tabsf_bg.png" height="23"/> 
 
 <span class="page pageR"><@pager.pageTag/></span></div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</body>
</html>
