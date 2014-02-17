<#include "/common/taglibs.ftl">
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<body onclick="parent.hideMenu()">
<form id="pageForm" name="pageForm" action="cloudNodeMake!add.action" method="post">
<input type="hidden" id="appId" name="appId" value="${appId?default("")}" />
<@s.token name="token"/>
<div class="frameset_w" style="height:100%;">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/cloudnode.png" align="absmiddle" />前置机定制</div>
    </div>
    <div class="main_c2" style="margin:3px 0px 0px 3px;">
      <div class="left_tree_com">
        <h1> 业务应用 </h1>
        <span>
        <p>
        <div class="left_top"><a href="#" id="left_tops"></a></div>
        <div class="border_lr">
          <ul class="Menu">
           <#list appMsgs as appMsg>
	          <li ><a href="javascript:selectApp('${appMsg.id?default('')}','${appMsg.appName?default('')}',this)" <#if appMsg.id==appId>id="Menuselect"</#if> title="${appMsg.appName?default('')}" >${appMsg.appName?default('')}</a></li>
           </#list>
          </ul>
        </div>
        <div class="left_bottom"><a href="#" ></a></div>
        </p>
        </span> </div>
      <div class="right_main">
        <div class="loayt_01 rlist100">
          <div class="loayt_top"> <span class="loayt_tilte" id="nodeNameTip"><b>前置机选择 </b><strong><#if appMsg?exists>【${appMsg.appName?default("")}】</#if></strong></span><span class="loayt_right"><img src="${path}/images/kuang_right.png" /></span>
            <div class="from">
              <input type="text" size="30" name="nodeName" value="${nodeName?default("")}" onkeypress="showKeyPress()" /><!-- onpaste="return false"-->
              <input onclick="sub()"  type="button"  value="查询" class="btn_s"/>
            </div>
          </div>
          <div class="loayt_mian" >
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center" valign="middle" class="loayt_cm"  height="100%" ><div class="list_glzx_bg list_glzx_scroll">
                	
                
                        <#assign count=7>
                       <#assign pNum=1>
                       <#if cloudNodeInfos?size lt count>
                       <#list cloudNodeInfos as entity>
                       		<#if entity_index==0>
				                <tr>
				                </#if>
				                  <td style="text-align:left;width:14.3%">
					                  <div class="list_box">
				                        <p><img src="${path}/images/icon.png" /></p>
				                        <p class="list_word">${entity.nodesName?default("(空)")}</p>
				                        <p>
				                          <input type="checkbox" name="nodeIds" id="checkbox" value="${entity.id?default("")}"
				                          	<#list hasCloudNodeInfos as hasCloudNodeInfo>
				                          		<#if hasCloudNodeInfo.id==entity.id> checked </#if>
				                          	</#list>
				                          />
				                        </p>
				                      </div>
				                  </td>
				                   <#if entity_index==cloudNodeInfos?size-1></tr></#if>
				                </#list>
				                <#else>
				                			<#list cloudNodeInfos as entity>
					                       		<#if  (pNum-1)*count==entity_index>
									                <tr>
									                </#if>
									                  <td style="text-align:left;width:14.3%">
									                  <div class="list_box">
									                        <p><img src="${path}/images/icon.png" /></p>
									                        <p class="list_word">${entity.nodesName?default("(空)")}</p>
									                        <p>
									                          <input type="checkbox" name="nodeIds" id="checkbox" value="${entity.id?default("")}"
									                          	<#list hasCloudNodeInfos as hasCloudNodeInfo>
									                          		<#if hasCloudNodeInfo.id==entity.id> checked </#if>
									                          	</#list>
									                          />
									                        </p>
									                      </div>
									                  </td>
									                  <#if (entity_index+1)==cloudNodeInfos?size>
									                  </tr>
									                  <#else>
									                  <#if (entity_index+1)==pNum*count></tr><#assign pNum=pNum+1></#if>
									                  </#if>
									                </#list>
                      			</#if>
                  </div></td>
              </tr>
            </table>
          </div>
        </div>
        <div class=" clear"></div>
        <div class="btn_certer" style="margin-top:10px;">
          <input type="button" value="保存"  onclick="sub()"  class=" btn2_s"/>
          <input type="button" value="重选"  onclick="resetChecked()" class=" btn2_s" />
        </div>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="clear"></div>
</form>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<script>
function selectApp(appId,appName){
	//jQuery("#nodeNameTip").html("<b>前置机选择 </b><strong>【"+nodesName+"】</strong>");
	//jQuery("#appId").val(nodeId);
	window.location.href="cloudNodeMake!getCloudNodeInfoListByAppMsg.action?appId="+appId;
}
	//重置按钮
	function resetChecked(){
     var len=  $(":input[name='nodeIds'][checked=true]");
  		for(i=0;i<len.length;i++){
  		  len[i].checked=false;
  		}
	}
	//提交事件
	function sub() {
		/*var len=  $(":input[name='nodeIds'][checked=true]");
		 if(len.length==0){	
		 	alert("请选择前置机!");
		 	return;
		 }*/
		//document.forms[0].action="cloudNodeMake!search.action";
		document.forms[0].submit();
	}
</script>
</body>
</html>
