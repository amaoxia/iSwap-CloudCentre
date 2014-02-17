<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人工修改通报上传</title>
<link href="${request.getContextPath()}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${request.getContextPath()}/css/lurt.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.cookie.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.hotkeys.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.jstree.js"></script>
<script src="${request.getContextPath()}/js/title/jquery.poshytip.js"></script>
<!--通用方法-->
<script type="text/javascript" src="${request.getContextPath()}/js/iswap_common.js"></script>
<script>
      function sub(){
	   //     var filename = dwr.util.getValue("fileName");
	   //     c=filename.substr((filename.length-3),3); 
	   //		if(c!="jar"){
	   //		   alert("请上传扩展名为jar的文件！");
	   //				return false;
	   //		}
	     	document.saveForm.submit();
	   }
	   
	   //评分汇总表
	   function change_hz_file(){
		 var filename=document.getElementById("hz_upfile").value;
		 var filevalue=filename.substr(filename.lastIndexOf("\\")+1).toLowerCase()
	     document.getElementById("hz_upfilename").value=filevalue
	 }
	 
	  //部门表
	   function change_bm_file(){
		 var filename=document.getElementById("bm_upfile").value;
		 var filevalue=filename.substr(filename.lastIndexOf("\\")+1).toLowerCase()
	     document.getElementById("bm_upfilename").value=filevalue
	 }
	   
</script>	   
</head>
<body onclick="parent.hideMenu()">
<div style="height:100%;margin:5px;">
  <div class="clear"></div>
  <form name="saveForm"  action="${request.getContextPath()}/performancemanage/finallyreport/finallyReportMgr!addFinallyReport.action" enctype="multipart/form-data" method="post">
  <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
    <tr>
      <td height="100%" valign="top" bgcolor="#FFFFFF" ><div class="item1">
          <ul class="item1_lu">
            <li>
              <p><span><strong>考核通报名称：</strong></span>2011年第一季度综合治税考核通报</p>
              <input type="hidden" name="check.id" value="${check.id}" id="fileField" style="width:400px;" />
            </li>
            <li class="item2_bg">
              <p><strong>评分汇总表：</strong></p>
              <span>
              <input type="file" name="hzFile" id="hz_upfile" onchange="change_hz_file();"  style="width:400px;" />
              <input type="hidden" name="hz_upfilename" id="hz_upfilename"/>
              </span> </li>
            <li>
              <p><strong>考核评分表：</strong></p>
              <span>
              <label>
              <input type="file" name="bmFile" id="bm_upfile" onchange="change_bm_file();" style="width:400px;" />
              <input type="hidden" name="bm_upfilename" id="bm_upfilename"/>
              </label>
              </span></li>
              <li class="item2_bg">
              <p><strong>说明：</strong></p>
              <span>
              <label>
              1、新上传的文档将覆盖已上传的文档。<br/>
              2、文档自动命名，如：2010年度扬州市社会综合治税工作目标考核评分汇总表.xls、2010年度扬州市社会综合治税工作目标考核评分表.xls
              </label>
              </span></li>
            <li class="item_bg">
              <p class="btn_s_m2" align="center">
                <input name="input" type="button" value="上传" onclick="sub();" class=" btn2_s"/>
                &nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="link_btn" onclick="dg.cancel();">关闭窗口</a></p>
            </li>
          </ul>
      </div></td>
    </tr>
  </table>
 </form>
</div>
<script type="text/javascript" src="${request.getContextPath()}/js/lurt.js"></script>
</body>
</html>
