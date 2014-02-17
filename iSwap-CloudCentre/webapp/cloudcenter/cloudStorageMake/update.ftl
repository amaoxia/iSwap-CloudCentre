<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/js/themes/base/jquery.ui.all.css">
</head>
<body>
<form action="cloudStorageMake!update.action" method="post" name="saveForm" id="saveForm">
 <input name="id" type="hidden" value="${id?default('')}" id="id"/>
<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
  <div class="main">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td align="center" valign="middle" class=""  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
			<tr>
			  <td  height="100%" valign="top" ><div class="">
				  <ul class="item2_c">
					<li>
					  <p>空间名称：</p>
					  <span>
					  <input type="text" size="44" name="storageName" id="storageName" value="${storageName}"/>
					  </span> 
					  <span><div id='storageNameTip'></div></span>
					  </li>
					<li class="item2_bg">
					  <p>最大存储空间：</p>
					  <span><div class="demo" style="padding-left:2px;" >						
						<div id="slider-range-max" style="width:270px;"></div>			
						设定限额<input type="text"  name="storageSizeCount" id="amount" style="border:0; color:#f6931f; font-weight:bold;" readonly="y" style="width:30px;"/>(MB)	</div>
					  </span>
					  <span><div id='amountTip'></div></span>
					</li>
					<li class="">
					  <p>所属应用：</p>
					  <span>
					  <select name="appMsg.id" id="appMsgId">
					  <option>---请选择应用---</option>
					  <#list appMsgs as appMsg>
					  <option value="${appMsg.id?default('')}">${appMsg.appName?default("")}</option>
					  </#list>
					  </select>
					  </span>
					  <span><div id='appMsgIdTip'></div></span>
					</li>
					<li class="item2_bg">
					  <p>数据同步频率：</p>
					  <span>
					   <select name="times" id="times">
                         <option>---请选择时间---</option>
						 <option value="15">15秒</option>
						 <option value="20">20秒</option>
						 <option value="30">30秒</option>
						 <option value="60">60秒</option>
						 <option value="120">120秒</option>
                       </select>
					  </span>
					</li>					
					<li class="">
					  <p>描述</p>
					  <span>
					  <textarea  name="description" cols="42" rows="5">${description}</textarea>
					  </span>
					</li>
				  </ul>
				</div></td>
			</tr>
		  </table></td>
	  </tr>
	</table>
</div>
</div>
<div class="clear"></div>
</div>
</form>
<script src="${path}/js/jquery-1.5.1.js"></script>
<script src="${path}/js/ui/jquery.ui.core.js"></script>
<script src="${path}/js/ui/jquery.ui.widget.js"></script>
<script src="${path}/js/ui/jquery.ui.mouse.js"></script>
<script src="${path}/js/ui/jquery.ui.slider.js"></script>
<link rel="stylesheet" href="${path}/js/themes/demos.css">
<!--验证js-->
<#include "/common/commonUd.ftl">
<#include "/common/commonValidator.ftl">
<!--通用方法-->
<style>
	#demo-frame > div.demo { padding: 10px !important; };
</style>

<#function contain str id>
	<#assign ret='false'>
	<#list str?split(",") as tid>
		<#if tid?number==id>
			 <#assign ret='true'>
		</#if>
	</#list>
	<#return ret>
</#function>
<script type="text/javascript">
$(function() {
		$( "#slider-range-max" ).slider({
			range: "max",
			min: 1,
			max: 5000,
			value: ${storageSizeCount},
			slide: function( event, ui ) {
				$( "#amount" ).val( ui.value );
			}
		});
		$( "#amount" ).val( $( "#slider-range-max" ).slider( "value" ) );
});

$(document)
.ready(
	function() {
		jQuery("#appMsgId").val("${appMsg.id?default('')}");
		jQuery("#times").val("${times?default('')}");
	
		$.formValidator.initConfig( {
			onError : function(msg) {
			}
		});
		$("#storageName").formValidator( {
			onShow : "请输入空间名称,必填选项",
			onFocus : "请输入空间名称！"
		}).inputValidator( {
			min : 1,
			max : 30,
			onError : "输入为空或者已经超过30个字符！"
		});
		$("#amount").formValidator( {
			onShow : "请输入最大存储空间,必填选项",
			onFocus : "请输入最大存储空间！"
		}).inputValidator( {
			min : 1,
			max : 30,
			onError : "输入为空或者已经超过30个字符！"
		});
		$("#appMsgId").formValidator( {
			onShow : "请选择所属应用,必填选项",
			onFocus : "请选择所属应用！"
		}).inputValidator( {
			min : 1,
			max : 30,
			onError : "请选择！"
		});
		 
	});


function isSub() {
	var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
		document.forms[0].submit();
	}
}
</script>
</body>
</html>
