<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>指标管理</title>
	<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
	<link href="${path}/js/liger/lib/ligerUI/skins/Aqua/css/ligerui-form.css" rel="stylesheet" type="text/css" />  
	<link href="${path}/js/liger/lib/ligerUI/skins/Aqua/css/ligerui-tree.css" rel="stylesheet" type="text/css" />        
	<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	<script type="text/javascript" src="${path}/js/liger/lib/ligerUI/js/core/base.js"></script>
	<script type="text/javascript" src="${path}/js/liger/lib/ligerUI/js/plugins/ligerTree.js"></script>
	<script type="text/javascript" src="${path}/js/liger/lib/ligerUI/js/plugins/ligerComboBox.js"></script> 
</head>
<body class="pm01_c">
	<form method="post" action="${path}/appitemmgr/appItemExchangeConf/appItemExchangeConf!update.action" id="saveForm">
   	<input type="hidden" name="appItemExchangeConf.id" value="${id?default('')}"/>
   	<input type="hidden" name="appItemExchangeConf.appMsg.id" value="${appMsg.id}"/>
   	<input type="hidden" name="appItemExchangeConf.appItem.id" value="${appItem.id}"/>
   	<input type="hidden" name="appItemExchangeConf.appMsg.id" value="${appMsg.id}"/>
   <@s.token/>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle"  height="100%">
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                  	<li class="item_bg">
                      <p>所属应用:</p>
                      <span>
                      	<select style="width:300px;" name="appItemExchangeConf.appMsg.id" id="appMsgId" defaultValue="${appMsg.id}" disabled="disabled">
                      		<option>请选择...</option>
                      	</select>
                      <span><div id="appMsgIdTip"></div></span>
                      </li>
                    <li>
                      <p>所属指标:</p>
                      <span>
                      	<select style="width:300px;" name="appItemExchangeConf.appItem.id" id="appItemId" defaultValue="${appItem.id}" disabled="disabled">
                      		<option>请选择...</option>
                      	</select>
                      </span>
                      <span><div id="appItemId"></div></span>
                    </li>
                    <li class="item_bg">
                      <p>数据提供部门:</p>
                      <span>
                      	<input type="hidden" id="sendDeptId" name="appItemExchangeConf.sendDept.id" value="${sendDept.id}"/>
                     	<input type="text" style="width:290px;" id="txt1" value="${sendDept.deptName}" disabled="disabled"/><!-- style="border: 0 none;padding: 0;"-->
                      </span>
                      <span><div id="sendDeptIdTip"></div></span>
                    </li>
                    <li>
                      <p>数据接收部门:</p>
                      	<#assign receiveDeptIds="">
                      	<#assign receiveDeptNames="">
                      	<#list appItemExchangeConfDetails as appItemExchangeConfDetail>
			          		<#assign receiveDeptIds=receiveDeptIds+appItemExchangeConfDetail.receiveDept.id+",">
			          		<#assign receiveDeptNames=receiveDeptNames+appItemExchangeConfDetail.receiveDept.deptName+",">
			            </#list>
                      <span>
                      <span style="display:none;" id="appItemExchangeConfDetailsSpan" defaultValues="${receiveDeptIds}"></span>
                      <input type="text" id="txt2" value="${receiveDeptNames}" style="border: 0 none;padding: 0;"/>
                      </span>
                    </li>
                    <li class="item_bg">
                      <p>是否共享:</p>
                      <span>
                     	<input type="radio" name="appItemExchangeConf.isShare" value="0" <#if (isShare==0)>checked="true"</#if>>是</input>
                     	<input type="radio" name="appItemExchangeConf.isShare" value="1" <#if (isShare==1)>checked="true"</#if>>否</input>
                      </span>
                    </li>
                      <li>
						  <p>描述</p>
						  <span>
						  <textarea  name="appItemExchangeConf.remark" id="remark" cols="42" rows="5">${remark}</textarea>
						  </span>
						</li>
                  </ul>
                </div>
                </td>
            </tr>
          </table>
         </td>
      </tr>
    </table>
    </form>
    </body>
</html>
<#include "/common/commonUd.ftl">
<#include "/common/commonValidator.ftl">
<script type='text/javascript' src='${path}/js/validator/metaData/item.js'></script> 
<script type="text/javascript"> 
	$(document).ready(function(){
		//应用
		$.post('${path}/appitemmgr/appMsg/appMsg!getAppMsgList4Ajax.action',{},function(result, status){
			var options = "";
			if(result){
				//var resobj = eval("("+result+")");
				$.each(result, function(index, obj){
					options += "<option value='"+obj.id+"'>"+obj.appName+"</option>";
				});
			}
			$('#appMsgId').html($('#appMsgId').html()+options);
			$('#appMsgId option[value="'+$('#appMsgId').attr("defaultValue")+'"]').attr("selected",true);
		});
		
		//指标
		$.post('${path}/appitemmgr/appItem/appItem!getAppItemList4Ajax.action',{},function(result, status){
			var options = "";
			if(result){
				//var resobj = eval("("+result+")");
				$.each(result, function(index, obj){
					options += "<option value='"+obj.id+"'>"+obj.appItemName+"</option>";
				});
			}
			$('#appItemId').html($('#appItemId').html()+options);
			$('#appItemId option[value="'+$('#appItemId').attr("defaultValue")+'"]').attr("selected",true);
		});
	
		/**
		 $("#txt1").ligerComboBox({
	        width: 300,
	        selectBoxWidth: 300,
	        selectBoxHeight: 300,
	        textField:'name', valueField: 'id',treeLeafOnly:true,
	        onSelected:onSelectedSendDept,
	        tree: { url: "${path}/sysmanager/dept/dept!getDeptTree.action", checkbox: false,
	                textFieldName:"name",
	                idFieldName:"id",
	                parentIDFieldName:"pid",
	                onSuccess:function(data){
                       var node = $.grep(data, function(cur,i){
				          return cur['id']=='${sendDept.id}';
				       });
                       this.getNodeDom(node[0].treedataindex).click();
                    }
	         }
	    });
	     **/
	     
	    
	    var txt2Manager = $("#txt2").ligerComboBox({
	        width: 300,
	        selectBoxWidth: 300,
	        selectBoxHeight: 300,
	        textField:'name', valueField: 'id',treeLeafOnly:true,
	        onSelected:onSelectedReceiveDept,
	        tree: { url: "${path}/ajax/ajax!getDeptTree4AppItemExchangeConf.action?appMsgId=${appMsg.id}&appItemId=${appItem.id}", checkbox: true,
	                textFieldName:"name",
	                idFieldName:"id",
	                parentIDFieldName:"pid",
	                onSuccess:function(data){
	                	var receiveDeptIds = '${receiveDeptIds}';
	                	var receiveDeptIdsArray = receiveDeptIds.split(",");
	                	var tree = this;
	                	$.each(receiveDeptIdsArray, function(index, deptId){
	                		if(deptId){
	                			var nodeData = tree.getDataByID(deptId);
	                			if(nodeData){
	                				$(tree.getNodeDom(nodeData.treedataindex)).find('div .l-checkbox-unchecked').removeClass("l-checkbox-unchecked").addClass("l-checkbox-checked");
	                			}
	                		}
	                	});
                    }
	              }
	    });
		
		//大小写转换
		$('#itemCode').keypress(function(e) {     
		    var keyCode= event.keyCode;  
		    var realkey = String.fromCharCode(keyCode).toUpperCase();  
		    $(this).val($(this).val()+realkey);  
		    event.returnValue =false;  
		});
	});

	function onSelectedSendDept(value){
		$('#sendDeptId').val(value);
	}
	
	function onSelectedReceiveDept(values){
		$('#appItemExchangeConfDetailsSpan').html();
		var idsArray = values.split(";");
		var html = '';
		$.each(idsArray, function(index, id){
			html += '<input type="hidden" name="appItemExchangeConf.appItemExchangeConfDetails['+index+'].receiveDept.id" value="'+id+'"/>';
		});
		$('#appItemExchangeConfDetailsSpan').html(html);
	}
</script>
