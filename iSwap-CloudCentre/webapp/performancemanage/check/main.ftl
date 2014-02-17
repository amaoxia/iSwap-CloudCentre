<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#global path = request.getContextPath() />
<html xmlns="http://www.w3.org/1999/xhtml">
<#import "pager.ftl" as pager>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery.js'></script>
<!--日期控件-->
<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
<!--全选-->
<script type='text/javascript' src='${path}/js/checkAll.js'></script>
<!--弹出窗口-->
<script type="text/javascript" src="${path}/js/windowopen/lhgcore.min.js"></script>
<script type="text/javascript" src="${path}/js/windowopen/lhgdialog.js"></script>

<script type="text/javascript" src="${path}/js/selectedTD/selectedTD.js"></script>

<!--通用方法-->
<!--<script type="text/javascript" src="${path}/js/iswap_common.js"></script>-->
<!-- 验证方法-->
<script type="text/javascript" src="${path}/js/crud.js"></script>
<script type="text/javascript">    	
	function opdg(url,title){
	var dg= new J.dialog({ page:url,title:title,rang:true,width:770,height:560,resize:false,autoSize:true, cover:true });
		dg.ShowDialog();
	}
</script>

</head>

<body onclick="parent.hideMenu()">
<form name="pageForm" id="pageForm" action="check!list.action" method="post">
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><span class="c_m_t_img01"></span>考核与信息列表</div>
      <div class="c_m_search">考核年份选择：
		<select name="checkYear"  onchange="javascript:pageForm.submit()">
        	<option value="2010" <#if checkYear?exists&& checkYear== '2010' >selected</#if>>2010年</option>
        	<option value="2011" <#if checkYear?exists&& checkYear== '2011' >selected</#if>>2011年</option>       	
            <option value="2012" <#if checkYear?exists&& checkYear== '2012' >selected</#if>>2012年</option>
            <option value="2013" <#if checkYear?exists&& checkYear== '2013' >selected</#if>>2013年</option>
            <option value="2014" <#if checkYear?exists&& checkYear== '2014' >selected</#if>>2014年</option>
            <option value="2015" <#if checkYear?exists&& checkYear== '2015' >selected</#if>>2015年</option>
            <option value="2016" <#if checkYear?exists&& checkYear== '2016' >selected</#if>>2016年</option>
            <option value="2017" <#if checkYear?exists&& checkYear== '2017' >selected</#if>>2017年</option>
            <option value="2018" <#if checkYear?exists&& checkYear== '2018' >selected</#if>>2018年</option>
            <option value="2019" <#if checkYear?exists&& checkYear== '2019' >selected</#if>>2019年</option>
            <option value="2020" <#if checkYear?exists&& checkYear== '2020' >selected</#if>>2020年</option>
        </select>
      </div>
    </div>
    <div class="main_c">
      <div></div>
      <table>
        <tr>
        
        <td width="20%" align="center">
          <div <#if year==true && yearChStatus == true> class="m_kh_jd spring" <#elseif year==true>class="m_kh_jd summer" <#else> class="m_kh_jd nokh"</#if> >
              <div class="m_kh_date">${checkYear?eval-1}</div>
              <span <#if year==true && yearChStatus == true> class="m_kh_rjd_sr" <#elseif year==true> class="m_kh_rjd_su" <#else>  class="m_kh_rjd_au"</#if> >年度</span>
              <div class="clear"></div>
              <div  class="m_kh_p">
                <div class="m_kh_cont">
                  <#if year==true && yearChStatus == true> <p>已考核</p> <#elseif year==true><p><b class="hot">未考核</b></p><#elseif yearReport==true><p class="m_kh_title">已通报</p> <#else><p class="m_kh_title">未到考核期</p></#if>
                </div>
              </div>
              <div class="m_kh_btn"><#if year==true && yearChStatus == true><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/performancemanage/check/check!checkView.action?checkYear=${checkYear?eval-1}&cycleType=5&flag=2','再次考核');">再次考核</a> <#elseif year==true><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/performancemanage/check/check!checkView.action?checkYear=${checkYear?eval-1}&cycleType=5&flag=1','考核');">考核</a> <#else></#if></div>
            </div></td>
            
          <td width="20%" align="center">
          <div <#if firstQuarter==true && firstChStatus == true> class="m_kh_jd spring" <#elseif firstQuarter==true>class="m_kh_jd summer" <#else> class="m_kh_jd nokh"</#if> >
              <div class="m_kh_date">${checkYear}</div>
              <span <#if firstQuarter==true && firstChStatus == true> class="m_kh_rjd_sr" <#elseif firstQuarter==true> class="m_kh_rjd_su" <#else>  class="m_kh_rjd_au"</#if> >第一季度</span>
              <div class="clear"></div>
              <div  class="m_kh_p">
                <div class="m_kh_cont">
                  <#if firstQuarter==true && firstChStatus == true> <p>已考核</p> <#elseif firstQuarter==true><p><b class="hot">未考核</b></p><#elseif firstReport==true><p class="m_kh_title">已通报</p> <#else><p class="m_kh_title">未到考核期</p></#if>
                </div>
              </div>
              <div class="m_kh_btn"><#if firstQuarter==true && firstChStatus == true><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/performancemanage/check/check!checkView.action?checkYear=${checkYear}&cycleType=1&flag=2','再次考核');">再次考核</a> <#elseif firstQuarter==true><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/performancemanage/check/check!checkView.action?checkYear=${checkYear}&cycleType=1&flag=1','考核');">考核</a> <#else></#if></div>
            </div></td>
         <td width="20%" align="center">
         
          <div <#if secondQuarter==true && secondChStatus == true> class="m_kh_jd spring" <#elseif secondQuarter==true>class="m_kh_jd summer" <#else> class="m_kh_jd nokh"</#if> >
              <div class="m_kh_date">${checkYear}</div>
              <span <#if secondQuarter==true && secondChStatus == true> class="m_kh_rjd_sr" <#elseif secondQuarter==true> class="m_kh_rjd_su" <#else>  class="m_kh_rjd_au"</#if> >第二季度</span>
              <div class="clear"></div>
              <div  class="m_kh_p">
                <div class="m_kh_cont">
                  <#if secondQuarter==true && secondChStatus == true> <p>已考核</p> <#elseif secondQuarter==true><p><b class="hot">未考核</b></p><#elseif secondReport==true><p class="m_kh_title">已通报</p><#else><p class="m_kh_title">未到考核期</p></#if>
                </div>
              </div>
              <div class="m_kh_btn"><#if secondQuarter==true && secondChStatus == true><a href="javascript:void(0)"  id="hz0" onclick="opdg('${path}/performancemanage/check/check!checkView.action?checkYear=${checkYear}&cycleType=2&flag=2','再次考核');">再次考核</a> <#elseif secondQuarter==true>
              <a href="javascript:void(0)"  onclick="opdg('${path}/performancemanage/check/check!checkView.action?checkYear=${checkYear}&cycleType=2&flag=1','考核');">考核</a> <#else></#if></div>
            </div></td>
            
          <td width="20%" align="center">
          <div <#if thirdQuarter==true && thirdChStatus == true> class="m_kh_jd spring" <#elseif thirdQuarter==true>class="m_kh_jd summer" <#else> class="m_kh_jd nokh"</#if> >
              <div class="m_kh_date">${checkYear}</div>
              <span <#if thirdQuarter==true && thirdChStatus == true> class="m_kh_rjd_sr" <#elseif thirdQuarter==true> class="m_kh_rjd_su" <#else>  class="m_kh_rjd_au"</#if> >第三季度</span>
              <div class="clear"></div>
              <div  class="m_kh_p">
                <div class="m_kh_cont">
                  <#if thirdQuarter==true && thirdChStatus == true> <p>已考核</p> <#elseif thirdQuarter==true><p><b class="hot">未考核</b></p><#elseif thirdReport==true><p class="m_kh_title">已通报</p><#else><p class="m_kh_title">未到考核期</p></#if>
                </div>
              </div>
              <div class="m_kh_btn"><#if thirdQuarter==true && thirdChStatus == true><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/performancemanage/check/check!checkView.action?checkYear=${checkYear}&cycleType=3&flag=2','再次考核');">再次考核</a> <#elseif thirdQuarter==true><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/performancemanage/check/check!checkView.action?checkYear=${checkYear}&cycleType=3&flag=1','考核');">考核</a> <#else></#if></div>
            </div></td>
            
          <td width="20%" align="center">
          <div <#if fourthQuarter==true && fourthChStatus == true> class="m_kh_jd spring" <#elseif fourthQuarter==true>class="m_kh_jd summer" <#else> class="m_kh_jd nokh"</#if> >
              <div class="m_kh_date">${checkYear}</div>
              <span <#if fourthQuarter==true && fourthChStatus == true> class="m_kh_rjd_sr" <#elseif fourthQuarter==true> class="m_kh_rjd_su" <#else>  class="m_kh_rjd_au"</#if> >第四季度</span>
              <div class="clear"></div>
              <div  class="m_kh_p">
                <div class="m_kh_cont">
                  <#if fourthQuarter==true && fourthChStatus == true> <p>已考核</p> <#elseif fourthQuarter==true><p><b class="hot">未考核</b></p><#elseif fourthReport==true><p class="m_kh_title">已通报</p><#else><p class="m_kh_title">未到考核期</p></#if>
                </div>
              </div>
              <div class="m_kh_btn"><#if fourthQuarter==true && fourthChStatus == true><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/performancemanage/check/check!checkView.action?checkYear=${checkYear}&cycleType=4&flag=2','再次考核');">再次考核</a> <#elseif fourthQuarter==true><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/performancemanage/check/check!checkView.action?checkYear=${checkYear}&cycleType=4&flag=1','考核');">考核</a> <#else></#if></div>
            </div></td>
            
           
      </table>
      
       <!-- 列表区 -->
      <table class="tabs1">
        <tr onclick="selectedTD(this);">
          <th width="20" class="tright"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
          <th width="28" class="tright">序号</th>
          <th>考核评分汇总表</th>
          <th>考核排名汇总表</th>
          <th>考核日期&nbsp;<a href="javascript:void(0)"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
          <th>操作</th>
        </tr>
        
        <#list listDatas as entity>
        <tr <#if (entity_index+1)%2==1>class="trbg"</#if> onclick="selectedTD(this);">
          <td class="tright"><input name="ids" type="checkbox" value="${entity.id}" <#if entity.status == "2">disabled= "disabled "</#if>/></td>
          <td>${entity_index+1}</td>
          <td><a href="${path}/performancemanage/check/check!gatherList.action?modelId=${entity.model.id}&id=${entity.id}&checkYear=${checkYear}&gatherList=tablist">${entity.name?default("")} </a></td>
          <td><a href="${path}/performancemanage/check/check!checkManualRank.action?id=${entity.id}&checkYear=${checkYear}">${entity.name?default("")}</a></td>
          <td>${entity.checkTime?string("yyyy-MM-dd")}</td>
          <td>
              <#if entity.status != "2">
              <a href="javascript:del('check!deleteCheck.action?ids=${entity.id}')" class="tabs1_cz"><img src="${path}/images/czimg_sc.gif" />删除</a>
              <a href="javascript:rep('check!createReport.action?ids=${entity.id}')" class="tabs1_cz"><img src="${path}/images/small9/sctb.gif" />生成通报</a>
              <#else>
              <a href="javascript:void(0)" class="tabs1_cz"  disabled= "disabled "><img src="${path}/images/czimg_sc.gif" />删除</a>              
              </#if>
          </td>
        </tr>
        </#list>
        
      </table>
      <div class="tabs_foot"> 
          <span class="tfl_btns">
        <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
        <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" /><a href="javascript:delMany('check!deleteCheck.action')" class="tfl_blink"><b class="hot">删除</b>
        </a><!--<a href="javascript:repMany('check!createReport.action')"  class="tfl_blink"><b>生成通报</b></a>--></span> 
        <span class="page pageR"><@pager.pageTag/></span> </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
</form>
</body>
</html>
