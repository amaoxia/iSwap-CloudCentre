<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/common/taglibs.ftl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线考核</title>
<link href="../../css/pop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
<script type='text/javascript' src='${path}/js/validator/iswapmq/mqValidator.js'></script>
<#include "/common/commonValidator.ftl">
<#include "/common/commonUd.ftl">


<script type="text/javascript">
function save(){
		
		document.saveMQ.submit();
		dg.cancel();
		dg.curWin.location.reload(); 
	}

</script>
</head>

<body onload="MM_preloadImages('../../images/close_h.png')">
<div class="pop_01" style="width:700px">
  <div class="pop_mian">
  
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%">
        <form name="saveMQ" action="queueInfo!add.action" method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p>队列名称：</p>
                      <span>
                      <input name="queueName" id="queueName" type="text" size="30" />
                      </span>
                      <div class="item_ts"><img src="../../images/right.png" align="absmiddle"/> 不超过25个汉字，或不超过50个字节（字母，数字和下划线）</div>
                    </li>
					<li class="item_bg">
                      <p>服务类型：</p>
                     <span>
                        <select name="serType" id="serType">
			              <option value="" >所有类型</option>
			              <option value="发送队列">发送队列</option>
			              <option value="接收队列">接收队列</option>
			            </select>
                      </span>
                    </li>
                    <li>
                      <p>队列深度：</p>
                     <span>
                      <input name="queueDepth" id="queueDepth" type="text" size="30" />
                      </span>
                    </li>
                    <li class="item_bg">
                      <p>队列大小：</p>
                      <span>
                      <input name="queueSize" id="queueSize"  type="text" size="30" />
                      </span>
                    </li>
                    <li>
                      <p>所属部门：</p>
                     <span>
                      <select name="deptId" id="deptId">
                        <option>请选择</option>
                         <#list listDepts as entity>
                            <option  value="${entity.deptUid}">${entity.deptName}</option>
                          </#list>
                      </select>
                      </span>
                    </li>
                    <li class="item_bg">
                      <p>描述：</p>
                      <span>
                      <textarea name="description" id="description" cols="90" rows="4"></textarea>
                      </span> </li>
                  </ul>
                  <p class="btn_s_m2">
                    <input name="" type="submit" value="保存" onClick="save()"  class=" btn2_s"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" class="link_btn">重填</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" class="link_btn" onclick="dg.cancel();">关闭窗口</a></p>
                </div></td>
            </tr>
          </table>
          </form>
          </td>
        <td rowspan="2" class="pm_right"><img src="../../images/pop_051.png" width="7" height="1" /></td>
      </tr>
     
    </table>
  </div>
</div>
</body>
</html>
