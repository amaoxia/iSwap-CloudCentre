<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='../js/jquery.js'></script>
<!--日期控件-->
<script type='text/javascript' src='../js/datepicker/WdatePicker.js'></script>
<!--全选-->
<script type='text/javascript' src='../js/checkAll.js'></script>
<!--弹出窗口-->
<script type="text/javascript" src="../js/windowopen/lhgcore.min.js"></script>
<script type="text/javascript" src="../js/windowopen/lhgdialog.js"></script>

<script type="text/javascript" src="../js/selectedTD/selectedTD.js"></script>

<!--通用方法-->
<script type="text/javascript" src="../js/iswap_common.js"></script>
<script type="text/javascript">    
	var dg = new J.dialog({ id:'hz0', page:'pop_06.html',title:'再次考核', cover:true ,rang:true,width:770,height:540,resize:false});
	function opdg(){
		dg.ShowDialog();
	}
</script>
</head>

<body onclick="parent.hideMenu()">
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><span class="c_m_t_img01"></span>考核与信息列表</div>
      <div class="c_m_search">考核年份选择：
		<select name="selectYear">
        	<option>2011年</option>
            <option>2010年</option>
            <option>2009年</option>
            <option>2008年</option>
            <option>2007年</option>
            <option>2006年</option>
            <option>2005年</option>
            <option>2004年</option>
            <option>2003年</option>
        </select>
      </div>
    </div>
    <div class="main_c">
      <div></div>
      <table>
        <tr>
          <td width="20%" align="center">
          <div class="m_kh_jd spring">
              <div class="m_kh_date">2011</div>
              <span class="m_kh_rjd_sr">第一季度</span>
              <div class="clear"></div>
              <div  class="m_kh_p">
                <div class="m_kh_cont">
                  <p>已考核</p>
                </div>
              </div>
              <div class="m_kh_btn"><a href="javascript:void(0)" id="hz0" onclick="opdg();">再次考核</a></div>
            </div></td>
          <td width="20%" align="center"><div class="m_kh_jd summer">
              <div class="m_kh_date">2011</div>
              <span class="m_kh_rjd_su">第二季度</span>
              <div class="clear"></div>
              <div  class="m_kh_p">
                <div class="m_kh_cont">
                  <p><b class="hot">未考核</b></p>
                </div>
              </div>
              <div class="m_kh_btn"><a href="javascript:void(0)" id="hz0" onclick="opdg();">考核</a></div>
            </div></td>
          <td width="20%" align="center"><div class="m_kh_jd nokh">
              <div class="m_kh_date">2011</div>
              <span class="m_kh_rjd_au">第三季度</span>
              <div class="clear"></div>
              <div  class="m_kh_p">
                <div class="m_kh_cont">
                  <p class="m_kh_title">未到考核期</p>
                </div>
              </div>
              <div class="m_kh_btn"><span></span></div>
            </div></td>
          <td width="20%" align="center"><div class="m_kh_jd nokh">
              <div class="m_kh_date">2011</div>
              <span class="m_kh_rjd_au">第四季度</span>
              <div class="clear"></div>
              <div  class="m_kh_p">
                <div class="m_kh_cont">
                 <p class="m_kh_title">未到考核期</p>
                </div>
              </div>
              <div class="m_kh_btn"><span></span></div>
            </div></td>
          <td width="20%" align="center"><div class="m_kh_jd nokh">
              <div class="m_kh_date">2011</div>
              <span class="m_kh_rjd_au">年度</span>
              <div class="clear"></div>
              <div  class="m_kh_p">
                <div class="m_kh_cont">
                    <p class="m_kh_title">未到考核期</p>
                </div>
              </div>
              <div class="m_kh_btn"><span></span></div>
            </div></td>
        </tr>
      </table>
      <table class="tabs1">
        <tr onclick="selectedTD(this);">
          <th width="20" class="tright"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
          <th width="28" class="tright">序号</th>
          <th>考核评分汇总表</th>
          <th>考核排名汇总表</th>
          <th>考核日期&nbsp;<a href="javascript:void(0)"><img src="images/tabs_arr.png" align="absmiddle" /></a></th>
          <th>操作</th>
        </tr>
        
        <tr class="trbg" onclick="selectedTD(this);">
          <td class="tright"><input name="check" type="checkbox" value="" /></td>
          <td>1</td>
          <td><a href="tablist.html">2011年第一季度综合治税考核评分</a></td>
          <td><a href="tablist2.html">2011年第一季度综合治税考核排名</a></td>
          <td>2011-04-20</td>
          <td><a href="javascript:void(0)" class="tabs1_cz"><img src="images/czimg_sc.gif" />删除</a> <a href="javascript:void(0)" class="tabs1_cz"><img src="images/small9/sctb.gif" />生成通报</a></td>
        </tr>
        <tr onclick="selectedTD(this);">
          <td class="tright"><input name="check" type="checkbox" value="" /></td>
          <td>2</td>
          <td class="trbg"><a href="javascript:void(0)">2011年第一季度行政审批考核评分</a></td>
          <td class="trbg"><a href="javascript:void(0)">2011年第一季度行政审批考核</a><a href="tablist.html">排名</a></td>
          <td class="trbg">2011-04-02</td>
          <td class="trbg"><a href="javascript:void(0)" class="tabs1_cz"><img src="images/czimg_sc.gif" />删除</a> <a href="javascript:void(0)" class="tabs1_cz"><img src="images/small9/sctb.gif" />生成通报</a></td>
        </tr>
        <tr class="trbg" onclick="selectedTD(this);">
          <td class="tright"><input name="check" type="checkbox" value="" /></td>
          <td>3</td>
          <td><a href="javascript:void(0)">2010年年度综合治税考核评分</a></td>
          <td><a href="javascript:void(0)">2010年年度综合治税考核</a><a href="tablist.html">排名</a></td>
          <td>2010-03-12</td>
          <td><a href="javascript:void(0)" class="tabs1_cz"><img src="images/czimg_sc.gif" />删除</a> <a href="javascript:void(0)" class="tabs1_cz"><img src="images/small9/sctb.gif" />生成通报</a></td>
        </tr>
        <tr onclick="selectedTD(this);">
          <td class="tright"><input name="check" type="checkbox" value="" /></td>
          <td>4</td>
          <td class="trbg"><a href="javascript:void(0)">2010年年度行政审批考核评分</a></td>
          <td class="trbg"><a href="javascript:void(0)">2010年年度行政审批考核</a><a href="tablist.html">排名</a></td>
          <td class="trbg">2010-03-22</td>
          <td class="trbg"><a href="javascript:void(0)" class="tabs1_cz"><img src="images/czimg_sc.gif" />删除</a> <a href="javascript:void(0)" class="tabs1_cz"><img src="images/small9/sctb.gif" />生成通报</a></td>
        </tr>
        <tr class="trbg" onclick="selectedTD(this);">
          <td class="tright"><input name="check" type="checkbox" value="" /></td>
          <td>5</td>
          <td><a href="javascript:void(0)">2010年第四季度综合治税考核评分</a></td>
          <td><a href="javascript:void(0)">2010年第四季度综合治税考核</a><a href="tablist.html">排名</a></td>
          <td>2011-01-20</td>
          <td><a href="javascript:void(0)" class="tabs1_cz"><img src="images/czimg_sc.gif" />删除</a> <a href="javascript:void(0)" class="tabs1_cz"><img src="images/small9/sctb.gif" />生成通报</a></td>
        </tr>
        <tr onclick="selectedTD(this);">
          <td class="tright"><input name="check" type="checkbox" value="" /></td>
          <td>6</td>
          <td class="trbg"><a href="javascript:void(0)">2010年第四季度行政审批考核评分</a></td>
          <td class="trbg"><a href="javascript:void(0)">2010年第四季度行政审批考核</a><a href="tablist.html">排名</a></td>
          <td class="trbg">2011-02-07</td>
          <td class="trbg"><a href="javascript:void(0)" class="tabs1_cz"><img src="images/czimg_sc.gif" />删除</a> <a href="javascript:void(0)" class="tabs1_cz"><img src="images/small9/sctb.gif" />生成通报</a></td>
        </tr>
        <tr class="trbg" onclick="selectedTD(this);">
          <td class="tright"><input name="check" type="checkbox" value="" /></td>
          <td>7</td>
          <td><a href="javascript:void(0)">2010年第三季度行政审批考核评分</a></td>
          <td><a href="javascript:void(0)">2010年第三季度行政审批考核</a><a href="tablist.html">排名</a></td>
          <td>2010-11-23</td>
          <td><a href="javascript:void(0)" class="tabs1_cz"><img src="images/czimg_sc.gif" />删除</a> <a href="javascript:void(0)" class="tabs1_cz"><img src="images/small9/sctb.gif" />生成通报</a></td>
        </tr>
        <tr onclick="selectedTD(this);">
          <td class="tright"><input name="check" type="checkbox" value="" /></td>
          <td>8</td>
          <td class="trbg"><a href="javascript:void(0)">2010年第三季度综合治税考核评分</a></td>
          <td class="trbg"><a href="javascript:void(0)">2010年第三季度综合治税考核</a><a href="tablist.html">排名</a></td>
          <td class="trbg">2010-10-10</td>
          <td class="trbg"><a href="javascript:void(0)" class="tabs1_cz"><img src="images/czimg_sc.gif" />删除</a> <a href="javascript:void(0)" class="tabs1_cz"><img src="images/small9/sctb.gif" />生成通报</a></td>
        </tr>
        <tr class="trbg" onclick="selectedTD(this);">
          <td class="tright"><input name="check" type="checkbox" value="" /></td>
          <td>9</td>
          <td><a href="javascript:void(0)">2010年第二季度综合治税考核评分</a></td>
          <td><a href="javascript:void(0)">2010年第二季度综合治税考核</a><a href="tablist.html">排名</a></td>
          <td>2010-07-30</td>
          <td><a href="javascript:void(0)" class="tabs1_cz"><img src="images/czimg_sc.gif" />删除</a> <a href="javascript:void(0)" class="tabs1_cz"><img src="images/small9/sctb.gif" />生成通报</a></td>
        </tr>
        <tr onclick="selectedTD(this);">
          <td class="tright"><input name="check" type="checkbox" value="" /></td>
          <td>10</td>
          <td class="trbg"><a href="javascript:void(0)">2010年第一季度行政审批考核评分</a></td>
          <td class="trbg"><a href="javascript:void(0)">2010年第一季度行政审批考核</a><a href="tablist.html">排名</a></td>
          <td class="trbg">2010-05-23</td>
          <td class="trbg"><a href="javascript:void(0)" class="tabs1_cz"><img src="images/czimg_sc.gif" />删除</a> <a href="javascript:void(0)" class="tabs1_cz"><img src="images/small9/sctb.gif" />生成通报</a></td>
        </tr>
      </table>
      <div class="tabs_foot"> <span class="tfl_btns"><img src="images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;">全选</a><img src="images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" /><a href="javascript:void(0)" class="tfl_blink"><b class="hot">删除</b></a><a href="javascript:void(0)"  class="tfl_blink"><b>生成通报</b></a></span> <span class="page pageR">共 <span class="page_orange">24</span> 页/ <span class="page_green" >240</span> 条记录&nbsp;&nbsp;<a href="javascript:void(0)"><img src="images/p_1.png" align="absbottom" /></a><a href="javascript:void(0)"><img src="images/p_2.png" align="absbottom" /></a>&nbsp;&nbsp;<span><a href="javascript:void(0)" class="page_s">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a><a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">...</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)">10</a></span>&nbsp;&nbsp;<a href="javascript:void(0)"><img src="images/p_4.png" align="absbottom" /></a><a href="javascript:void(0)"><img src="images/p_5.png" align="absbottom" /></a><span style="padding-left:20px;">每页</span> <span>
        <select name="">
          <option>10</option>
          <option>20</option>
          <option>30</option>
        </select>
      </span> <span>条</span></span> </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
</body>
</html>
