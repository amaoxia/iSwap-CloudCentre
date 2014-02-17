<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <#include "/common/taglibs.ftl">
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      <title>
        	流程管理
      </title>
      <link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
      <link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
      <script type='text/javascript' src='${path}/js/jquery-1.5.1.js'>
      </script>
    </head>
    <body>
          <div class="common_menu">
            <div class="c_m_title">
              <img src="${path}/images/big/WorkFlow.png"  align="absmiddle" />
              	流程管理
            </div>
            <div class="c_m_btn">
              <span class="cm_btn_m">
                <a 
                    href="javascript:void(0)"
                    onclick="opdg('${path}/cloudnode/workflow/workflow!addView.action?deptId=${deptId?default('')}','流程注册','650','550');">

                  <b>
                    <img src="${path}/images/cmb_xj.gif" class="cmb_img" />
                    	注册流程
                    <a href="javascript:void(0)">
                      <img src="${path}/images/bullet_add.png" class="bullet_add" />
                    </a>
                  </b>
                </a>
              </span>
              &nbsp;
              <span class="cm_btn_m">
                <a href="javascript:void(0)" id="hz9" onclick="">
                  <b>
                    <img src="${path}/images/middle/m_daoru.png" class="cmb_img" />
                   	 导入流程
                    <a href="javascript:void(0)">
                      <img src="${path}/images/bullet_add.png" class="bullet_add" alt="收藏" title="收藏"/>
                    </a>
                  </b>
                </a>
              </span>
              <span class="cm_btn_m">
                &nbsp;
              </span>
            </div>
          </div>
          <div class="main_c">
            <form name="sel" id="pageForm" action="${path}/cloudnode/workflow/workflow!list.action" method="post">
              <table class="main_drop">
                <tr>
                  <td align="right">
                  <input type="hidden" name="deptId" value="${deptId?default('')}"/>
                    	流程名称：
                    <input 
                        name="workFlowName"
                        type="text"
                        value="${workFlowName?default('')}" onkeypress="showKeyPress()" /><!-- onpaste="return false"--> 
                   	 所属应用：
                    <select name="appMsgId">
                      <option value="">
                        ---全部---
                      </option>
                      <#list appMsgList as app>
                        <option 
                            value="${app.id}"
                             <#if
                            appMsgId?exists><#if
                            app.id==appMsgId?default('')>selected</#if></#if>
                        >
                          ${app.appName?default('')}
                        </option>
                      </#list>
                    </select>
                    <input  type="button" value="查询"  class="btn_s" onclick="selectflow();"/>
                  </td>
                </tr>
              </table>
              <table class="tabs1" style="margin-top:0px;">
                <tr>
                  <th width="3%">
                    &nbsp;
                  </th>
                  <th width="5%">
                   	 序号
                  </th>
                  <th width="40%">
                   	 流程名称
                  </th>
                  <th width="10%">
                   	 所属应用
                  </th>	
                  <th width="8%">
                   	 流程类型
                  </th>
                  <th width="9%">
                   	 状态
                  </th>
                  <th width="25%">
                  	  操 作
                  </th>
                </tr>
                <#list listDatas as entity>
                  <tr <#if entity_index%2==0>class="trbg"</#if>  onclick="selectedTD(this);">
                    <td width="3%">
                      <input name="ids" type="checkbox" value="${entity.id}" />
                    </td>
                    <td width="5%">
                      ${entity_index+1}
                    </td>
                    <td width="40%">
                      <a 
                          href="javascript:void(0)"
                          onclick="opdg('${path}/cloudnode/workflow/workflow!view.action?id=${entity.id}','流程信息查看','600','370');">
                        ${entity.workFlowName?default('')}
                      </a>
                    </td>
                    <td width="10%">
                      ${entity.appMsg.appName?default('')}
                    </td>
                    <td width="8%">
                      <#if entity.wfType=='0'>
                        	接收
                      </#if>
                      <#if entity.wfType=='1'>
                     	   发送
                      </#if>
                    </td>
                    <td width="9%">
                      <#if entity.status=='0'>
                      	 <font class="font_red">未部署</font>
                      </#if>
                      <#if entity.status=='1'>
                       	 已部署
                      </#if>
                    </td>
                    <td width="25%">
                    <#if entity.status=='0'>
                      <a href="javascript:void(0)" class="tabs1_cz" onclick="opdg('${path}/cloudnode/workflow/workflow!updateView.action?deptId=${deptId?default('')}&id='+${entity.id},'编辑流程信息','650','550');">
                        <img src="${path}/images/czimg_edit.gif" />编辑
                      </a>
                     </#if>
                     <#if entity.status=='1'>
                      <a href="javascript:void(0)" class="tabs1_cz" style="color:#ccc;">
                        <img src="${path}/images/czimg_edit.gif" />编辑
                      </a>
                     </#if>
                      &nbsp;
                      <#if entity.status=='0'>
                       <a id="hz8" href="#"  onclick="updateProcess('${entity.id}','${entity.sysDept.id?default('')}','0');" class="tabs1_cz">
                        <img src="${path}/images/small9/s_xiuzheng.gif" />
                        	编辑流程
                      </a>
                      </#if>
                      <#if entity.status=='1'>
                      <a id="hz8" href="javascript:void(0)"   class="tabs1_cz" style="color:#ccc;">
                        <img src="${path}/images/small9/s_xiuzheng.gif" />
                        	编辑流程
                      </a>
                      </#if>
                      &nbsp;
                      <#if entity.status=='0'>
                      <a  href="javascript:void(0);" class="tabs1_cz" style="color:#ccc;">
                        <img src="${path}/images/small9/s_ceshi.gif" />
                      	  测试
                      </a>
                      </#if>
                      <#if entity.status=='1'>
                      <a  href="javascript:void(0);" class="tabs1_cz" onclick="opdg('${path}/cloudnode/workflow/workflow!testView.action?id='+${entity.id},'测试流程','650','510');">
                        <img src="${path}/images/small9/s_ceshi.gif" />
                      	  测试
                      </a>
                      </#if>
                      
                      <br/>
                      <a id="hz5" href="#" class="tabs1_cz" >
                        <img src="${path}/images/small9/down.gif" />
                       	 导出
                      </a>
                      &nbsp;
                      <#if entity.status=='0'>
                        <a  href="javascript:void('0');"
                            onclick="updateStatus('${path}/cloudnode/workflow/workflow!updateStatus.action?status=1&ids=${entity.id}');"
                            class="tabs1_cz"><img src="${path}/images/small9/s_bushu.gif" />
                          	部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;署
                        </a>
                      </#if>
                      <#if entity.status=='1'>
                        <a href="javascript:void('0');"
                            onclick="updateStatus('${path}/cloudnode/workflow/workflow!updateStatus.action?status=0&ids=${entity.id}');"
                            class="tabs1_cz"> <img src="${path}/images/small9/s_quxiaobushu.gif" />
                       	   取消部署
                        </a>
                      </#if>
                      &nbsp;
                      <a id="hz5" href="javascript:void(0);"
                          onclick="opdg('${path}/cloudnode/workflow/workflow!cloneView.action?deptId=${deptId?default('')}&id='+${entity.id},'克隆流程','650','210');"
                          class="tabs1_cz"><img src="${path}/images/small9/page_white_clone.gif" />
                     	   克隆
                      </a>
                    </td>
                  </tr>
                </#list>
              </table>
              <div class="tabs_foot">
                <span class="tfl_btns">
                  <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px; margin-right:8px;" />
                  <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">
                   	 全选
                  </a>
                  <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
                  <a 
                      href="javascript:void(0)"
                      onclick="delMany('${path}/cloudnode/workflow/workflow!delMany.action');"
                      class="tfl_blink">

                    <b class="hot">
                     	 删除
                    </b>
                  </a>
                  <a href="javascript:void(0)" class="tfl_blink">
                    <b>
                     	 导出
                    </b>
                  </a>
                  <a href="javascript:void(0)" class="tfl_blink" onclick="AllupdateStatus(1)">
                    <b>
                     	 部署
                    </b>
                  </a>
                  <a href="javascript:void(0)" class="tfl_blink" onclick="AllupdateStatus(0)">
                    <b>
                     	 取消部署
                    </b>
                  </a>
                   <a href="javascript:void(0)" class="tfl_blink" onclick="synData()">
                    <b>
                     	 手工同步数据
                    </b>
                  </a>
                </span>
                <span class="page pageR">
                  <@pager.pageTag/>
                </span>
              </div>
            <div class="clear"/>
      	</form>
      </div>
      <div class="clear">
      <script type="text/javascript" src="${path}/js/iswap_table.js">
        <#include "/common/commonList.ftl">
         <#include "/common/commonLhg.ftl">
          <script type="text/javascript">
            function selectflow(){
              document.sel.submit();
            }
            //修改状态
            function updateStatus(url){
              document.forms['sel'].action = url;
              document.forms['sel'].submit();
            }
            function AllupdateStatus(status){
              var temp = document.getElementsByName("ids");
              var ids=new Array();
              for(var i=0;i<temp.length;i++)
              {
                if(temp[i].checked){
                  ids.unshift(temp[i].value);
                }
              }
              if(ids.length==0){
                alert("请选择要部署的流程");
              }else{
                updateStatus("${path}/cloudnode/workflow/workflow!updateStatus.action?status="+status+"&ids="+ids);
              }
            }

            function updateProcess(id,deptid,type) {
	          var prem = "wf_id="+id+"&wf_deptid="+deptid+"&wf_type="+type+"";
	          var url = "${path}/workflow/ISwapWorkFlow.html?"+prem;  
	          //alert(url);   
	          window.showModalDialog(url, "工作流程编辑器", "dialogWidth:1000px;dialogHeight:768px;center:yes;help:no;scroll:no;status:no;resizable:yes;");
	        }
            
            function synData(){
            	$.ajax({
				 type: "POST",
				 url: "${path}/cloudnode/workflow/workflow!synData.action",
				 success: function(msg){
				 	
				  } 
				}); 
            }
            
            //调整表格可显示高度，否则出不了滚动条
           function getDivHeight(){
            	baseTbHeight = document.body.baseTbHeight;
            	var divHeight = $(document.body).height()-$("div .common_menu").eq(0).height()-$("table .main_drop").eq(0).height()-$("div .tabs1").eq(0).height()-$("div .tabs_foot").eq(0).height()-85;
				if(baseTbHeight&&baseTbHeight<divHeight){
					return baseTbHeight;
				}else{
					return divHeight;
				}
            }
            
            function setBaseTbHeight(){
            	document.body.baseTbHeight=$("table.tabs1").eq(0).height();
            }
          </script>
        </body>
      </html>
