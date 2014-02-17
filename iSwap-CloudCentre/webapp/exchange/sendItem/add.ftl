<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>新增指标项</title>
		<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	</head>
	<body>
		<div class="pop_01" id="wpageslide" style="width:620px;height:520;overflow-x:hidden;overflow-y:scroll;">
		<div id="wpages" class="wpages">
		<div class="pop_mian wpage">
		<form method="post" action="${path}/exchange/sendItem/sendItem!add.action" id="saveForm">
		    <@s.token/>
		    <input type="hidden"  name="itemType" value="1"/>
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td align="center" valign="middle" class="pm01_c"  height="100%">
		          <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
		            <tr>
		              <td  height="100%" valign="top" >
		              <div class="item1">
		                  <ul class="item1_c">
		                    <li>
		                      <p>指标名称:</p>
		                      <span>
		                      <input type="text" size="30"  name="itemName" id="itemName"/>
		                      </span>
		                      <span><div id="itemNameTip"></div></span>
		                    </li>
		                    <li class="item_bg">
		                      <p>指标编码:</p>
		                      <span>
		                      <input type="text" name="itemCode" id="itemCode"  size="30"/></span>
		                      <span><div id="itemCodeTip"></div></span>
		                      </li>
		                    <li >
		                      <p>所属部门:</p>
								<span>
		                 			  <input type="text" id="deptName" size="30" disabled="disabled" value="${deptName}"/> 
		                 			  <input type="hidden" id="deptId"  name="sysDept.id"  value="${deptId}"/> 
		                      	</span>
		                      	<span><div id="deptNameTip"></div></span>
		                        
		                    </li>
		                 	 <li class="item_bg">
		                      <p>交换类型:</p>
		                      <span id="dataTypeSpan">
			                      <select name="dataType" id="dataType" onchange="changeData(this)" style="width:200px">
				                      <option value="">请选择</option>
				                      <option value="0">文档</option>
				                      <option value="1">数据库</option>
			                      </select>
		                      </span>
		                      <span><div id="dataTypeTip"></div></span>
		                 	</li>
		                 	<li>
		                      <span>
		                                                          文档结构类型
			                      <select name="dataStructure"  id="dataStructureSel" onchange="addItem(this)" >
				                      <option value="">请选择</option>
				                      <option value="0">非结构化</option>
				                      <option value="1">结构化</option>
			                      </select>
			                  	    文件类型:
			                      <select name="dataValue" id="dataValueSel">
			                      	<option value="">请选择</option>
			                      </select>
		                 	  </span>
		                  </li>
		                  <li id="dsTypeLi" name="dsType" class="item_bg">
							  <p><b>*</b>数据源类型：</p>
							  <span>
			                 	<input type="radio" name="dsType" value="0"/>
			                  	数据库数据源&nbsp;
			                  	<input type="radio" name="dsType" value="1"/>
			                 	 FTP数据源 &nbsp;
			                 	 <input type="radio" name="dsType" value="2"/>
			                 	 MQ数据源&nbsp;
			                 	 <input type="radio" name="dsType" value="3"/>
			                 	 WS数据源&nbsp;
			                 	 <input type="radio" name="dsType" value="4"/>
			                 	 MONGODB数据源
			                 </span>
						  </li>
						  <li> 
							  <p><b>*</b>数据源：</p>
							  <span>
							  <select name="datSource.id" id="dataSourceSel" style="width:200px" >
							  </select>
							  </span>
							   <span> <div id="dataSourceSelTip"></div></span>
						  </li>
			              <li class="item_bg">
			                      <p>表名:</p>
			                      <span>
			                      <input type="text" name="tableName" id="tableNameInput"  size="30" /></span>
			                      <span><div id="tableNameInputTip"></div></span>
			              </li>
		                  <li >
		                      <p>交换周期:</p>
		                      <span>
		                        <select name="cycle.exchangeCycleValue" id="exchangeCycleValue"  style="width:200px"  onchange="linkageCycle(parseInt(this.value))">
			                    <!--option value="0">周</option-->
			                    <option value="1">月</option>
			                    <option value="2">季</option>
			                    <option value="3">年</option>
		                   	    </select> 
							</span>
		                  </li>
		                  <li  class="item_bg">
		                      <p>交换日期规则:</p>
		                      <!--<span  id="w_span" >
		                     		 本周数据在:
		                       <select id="w_sel" name="w_sel" style="width:80px">
			                      <option value="0">
			                     	 本周
			                      </option>
			                      <option value="1">
			                  	    下周
			                      </option>
			                  </select>
			                      <select id="w_day" name="w_day" style="width:60px">
				                     <#list   1..7 as temp>
			   						  <option value="${temp}">${temp}</option>
									 </#list>
		                     	 </select>
		                     	 	 执行
		                      </span>-->
		                      <!--- 月 --->
		                        <span  style="" id="m_span">
		                  	    本月数据在:
		                         <select id="m_sel" name="m_sel" style="width:80px">
				                     <option value="0" >
				                     	 本月
				                      </option>
				                      <option value="1" selected>
				                  	    下月
				                      </option>
			                      </select>
			                      <select id="m_day" name="m_day" style="width:60px">
			                      	<option value="20" selected>20</option>
			                     	<#list   1..31 as temp>
		   						  	<option value="${temp}" <#if temp==20>selected</#if>>${temp}</option>
									 </#list>
		                     	 </select>
		                     	 执行
		                      </span>
		                      <!--- 季 -->
		                        <span  style="display: none;" id="j_span">
		                     	   本季数据在:
		                          <select id="j_sel" name="j_sel" style="width:80px">
			                      <option value="0">本季</option>
			                      <option value="1" selected>下季    </option>
			                      </select>
			                      <select id="j_month" name="j_month" style="width:60px">
		   						  <option value="1" selected>第一月</option>
		   						  <option value="2">第二月</option>
		   						  <option value="3">第三月</option>
		                     	 </select>
		                     	 的第
		                     	 <select id="j_day" name="j_day" style="width:60px">
		                     	 <option value="20">20</option>
			                     <#list   1..31 as temp>
		   						  <option value="${temp}" <#if temp==20>selected</#if>>${temp}</option>
								 </#list>
		                     	 </select>
		                     	 日执行
		                      </span>
		                      <!--- 年 -->
		                         <span  style="display: none;" id="y_span">
		                       	  本年数据在:
		                          <select id="y_sel" name="y_sel" style="width:80px">
			                    <option value="0">
			                     	 本年
			                      </option>
			                      <option value="1" selected>
			                  	    下年
			                      </option>
			                      </select>
			                      <select id="y_month" name="y_month" style="width:60px">
			                       <#list 1..12 as temp>
								 <option value="${temp}" selected>${temp}</option>
								 </#list>
			                      <#list   1..12 as temp>
		   						  <option value="${temp}" <#if temp==1>selected</#if>>${temp}</option>
								 </#list>
		                     	 </select>
		                     	 月
		                     	 <select id="y_day" name="y_day" style="width:60px">
		                     	  <option value="30">30</option>
			                     <#list   1..31 as temp>
		   						  <option value="${temp}" <#if temp==30>selected</#if>>${temp}</option>
								 </#list>
		                     	 </select>
		                     	 日执行
		                      </span>
		                  </li>
		                  <!--   <li>
		                   <p> 是否采用系统默认规则:
		                    <input type="radio" name="cycle.useDefaultRule" value="1" checked onclick="sysRule(1)" id="isSys"/>是
		                     <input  type="radio" name="cycle.useDefaultRule" value="0"  onclick="sysRule(0)"/>否
		                    </p> 2011-12-14 
		                     </li>-->
		                     <li style="display: none;" id="rule_green" class="item_bg">
		                     	<p>绿灯显示规则:
		                     		交换日期
			                     	<select name="greenNotify" id="_greenNotify" disabled  onchange="greenValidator()">
			                     	 <option value="0">前</option>
			                     	</select>
			                     	<select name="greenNotifyDay" id="_greenNotifyDay" disabled onchange="greenValidator()">
			                     	</select>
		                     	</p>
		                     </li>
		                     <li style="display: none;" id="rule_yellow">
		                     	<p>黄灯显示规则:
		                     	交换日期
			                     	<select name="yellowNotify" id="_yellowNotify" disabled onchange="yellowValidator()">
			                     	 <option value="0">前</option>
			                     	 <option value="1">后</option>
			                     	</select>
			                     	<select name="yellowNotifyDay" id="_yellowNotifyDay" disabled onchange="yellowValidator()">
			                     	
			                     	</select>
		                     	</p>
		                     </li>
		                     <li style="display: none;" id="rule_red" class="item_bg">
		                     	<p>
		                     	红灯显示规则:
		                     	  交换日期
			                     	<select name="redNotify" id="_redNotify" disabled onchange="redValidator()">
			                     	 <option value="1">后</option>
			                     	</select>
			                     	<select name="redNotifyDay" id="_redNotifyDay" disabled onchange="redValidator()">
			                     	</select>
		                     	</p>
		                     </li>
		                  </ul>
		                </div></td>
		            </tr>
		          </table></td>
		      </tr>
		    </table>
		    </form>
		  </div>
		 </div>
			<ul id="wpagebtns" class="wpagebtns">
			<li></li>
			<li></li>
		</ul>
		</div>
	<#include "/common/commonSilde.ftl">
	<#include "/common/commonUd.ftl">
	<#include "/common/commonValidator.ftl">
	<script type='text/javascript' src='${path}/js/validator/metaData/item.js'></script> 
	<script type='text/javascript'>
		function linkageCycle(t) {
			switch (t) {
				case 0:
						$('#w_span').show();
						$('#m_span').hide();
						$('#j_span').hide();
						$('#y_span').hide();
						 //addItemData(7);
						break;
				case 1:
						$('#w_span').hide();
						$('#m_span').show();
						$('#j_span').hide();
						$('#y_span').hide();
						// addItemData(10);
						break;
			     case 2:
					    $('#w_span').hide();
						$('#m_span').hide();
						$('#j_span').show();
						$('#y_span').hide();
						// addItemData(10);
						break;
				 case 3:
				    $('#w_span').hide();
					$('#m_span').hide();
					$('#j_span').hide();
					$('#y_span').show();
					// addItemData(10);
				break;
			}
		}
	
		//
		function sysRule(temp){
		  var cycle=$("#exchangeCycleValue").attr("value");
		  if(cycle=='0'){//
		    addItemData(7);
		  }else{
		    addItemData(10);
		  }
		  if(temp=='0'){//否
		   	$("#rule_green").show();
		   	$("#rule_yellow").show();
		   	$("#rule_red").show();
		   	$("#_redNotifyDay").attr("disabled",false);
		   	$("#_redNotify").attr("disabled",false);
		   	$("#_yellowNotify").attr("disabled",false);
		   	$("#_yellowNotifyDay").attr("disabled",false);
		   	$("#_greenNotify").attr("disabled",false);
		   	$("#_greenNotifyDay").attr("disabled",false);
		  }
		  if(temp=='1'){//是
		  	$("#rule_green").hide();
		   	$("#rule_yellow").hide();
		   	$("#rule_red").hide();
		   	$("#_redNotifyDay").attr("disabled",true);
		   	$("#_redNotify").attr("disabled",true);
		   	$("#_yellowNotify").attr("disabled",true);
		   	$("#_yellowNotifyDay").attr("disabled",true);
		   	$("#_greenNotify").attr("disabled",true);
		   	$("#_greenNotifyDay").attr("disabled",true);
		  }
		}
		
		//添加数据
		function addItemData(length){
			var _greenNotifyDay=document.getElementById("_greenNotifyDay");
			var _yellowNotifyDay=document.getElementById("_yellowNotifyDay");
			var _redNotifyDay=document.getElementById("_redNotifyDay");
			_greenNotifyDay.options.length=0;
			_yellowNotifyDay.options.length=0;
			_redNotifyDay.options.length=0;
			for(i=1;i<=length;i++){
				_greenNotifyDay.options.add(new Option(i,i)); //这个兼容IE与firefox
				_yellowNotifyDay.options.add(new Option(i,i)); //这个兼容IE与firefox
				_redNotifyDay.options.add(new Option(i,i)); //这个兼容IE与firefox
			}
		}
	</script> 
	<script type="text/javascript">
		//绿灯验证
		function greenValidator(){
			var cycleValue=$("#exchangeCycleValue").attr("value");//交换周期值
			var w_sel=$("#w_sel").attr("value");//0 本周  1 下周
			var w_day=$("#w_day").attr("value");//周几
			var _greenNotifyDay=$("#_greenNotifyDay").val(); //绿灯天数
			var _yellowNotify=$("#_yellowNotify").val();//黄灯规则
			var _yellowNotifyDay=$("#_yellowNotifyDay").val();//黄灯天数
			if($("#isSys").attr("checked")==false){
				if(cycleValue=='0'){//周期交换值
					if(w_sel=='0'){//本周
						//if(parseInt(w_day)<parseInt(_greenNotifyDay)){
						//alert("绿灯交换天数要小于执行时间!");
						//	return false;
						//}
						if(_yellowNotify=='0'){//前
							if(parseInt(_greenNotifyDay)<=parseInt(_yellowNotifyDay)){
								alert("交换日期为前的黄灯规则天数要求要小于绿灯规则天数！");//||parseInt(_yellowNotifyDay) >= parseInt(w_day)
								return false;
							}
						}
						return true;
					}else{//下周
						if(_yellowNotify=='0'){//前
							if(parseInt(_greenNotifyDay)<=parseInt(_yellowNotifyDay)){
								alert("黄灯交换时间不能超过绿灯交换天数");
								return false;
							}
						} 
						return true;
					}
				}else{
					if(_yellowNotify=='0'){//前
						if(parseInt(_greenNotifyDay)<=parseInt(_yellowNotifyDay)){//||parseInt(_yellowNotifyDay) >= parseInt(w_day)
							alert("交换日期为前的黄灯规则天数要求要小于绿灯规则天数！");
							return false;
						}
						return true;
					}
					return true;
				}
			}  
			return true;     
		}
		
		//黄灯验证
		function  yellowValidator(){
			var cycleValue=$("#exchangeCycleValue").attr("value");//交换周期值
			var w_sel=$("#w_sel").attr("value");//0 本周  1 下周
			var w_day=$("#w_day").attr("value");//周几
			var _greenNotifyDay=$("#_greenNotifyDay").val(); //绿灯天数
			var _yellowNotify=$("#_yellowNotify").val();//黄灯规则
			var _yellowNotifyDay=$("#_yellowNotifyDay").val();//黄灯天数
			var _redNotify=$("#_redNotify").val();//红灯规则
			var _redNotifyDay=$("#_redNotifyDay").val();//红灯天数
			if($("#isSys").attr("checked")==false){
				if(cycleValue=='0'){//周期交换值
					if(w_sel=='0'){//本周
						if(_yellowNotify=='0'){//前
							if(parseInt(_greenNotifyDay)<=parseInt(_yellowNotifyDay)){//||parseInt(_yellowNotifyDay) >= parseInt(w_day)
								alert("交换日期为前的黄灯规则天数要求要小于绿灯规则天数和当前执行天数！");
								return false;
							}
						}
						if(_yellowNotify=='1'){//后
							if(parseInt(_yellowNotifyDay)>=parseInt(_redNotifyDay)){
								alert("黄灯规则天数不能大于红灯规则天数!");
								return false;
							}
							return true;
						}
					}else{//下周
						if(_yellowNotify=='0'){//前
							if(parseInt(_greenNotifyDay)<=parseInt(_yellowNotifyDay)){
								alert("交换日期为前的黄灯规则天数要求要小于绿灯规则天数！");
								return false;
							}
						} 
						if(_yellowNotify=='1'){//后
							if(parseInt(_yellowNotifyDay)>=parseInt(_redNotifyDay)){
								alert("交换日期为前的红灯规则天数要求要大于黄灯规则天数！");
								return false;
							}
							return true;
						}
					}
				}else{
					if(_yellowNotify=='0'){//前
						if(parseInt(_greenNotifyDay)<=parseInt(_yellowNotifyDay)){
							alert("交换日期为前的黄灯规则天数要求要小于绿灯规则天数");
							return false;
						}
					}
					if(_yellowNotify=='1'){//后
						if(parseInt(_yellowNotifyDay)>=parseInt(_redNotifyDay)){
							alert("交换日期为前的红灯规则天数要求要大于黄灯规则天数！");
							return false;
						}
					}
					return true;
				} 
			}
			return true;
		}

		function redValidator(){//红灯验证
			var cycleValue=$("#exchangeCycleValue").attr("value");//交换周期值
			var w_sel=$("#w_sel").attr("value");//0 本周  1 下周
			var w_day=$("#w_day").attr("value");//周几
			var _greenNotifyDay=$("#_greenNotifyDay").val(); //绿灯天数
			var _yellowNotify=$("#_yellowNotify").val();//黄灯规则
			var _yellowNotifyDay=$("#_yellowNotifyDay").val();//黄灯天数
			var _redNotify=$("#_redNotify").val();//红灯规则
			var _redNotifyDay=$("#_redNotifyDay").val();//红灯天数
			if($("#isSys").attr("checked")==false){
				if(cycleValue=='0'){//周交换
					if(_yellowNotify=='1'){//后
						if(parseInt(_yellowNotifyDay)>=parseInt(_redNotifyDay)){//黄灯小于红灯
							alert("交换日期为前的红灯规则天数要求要大于黄灯规则天数！");
							return false;
						}
						return true;
					}
				}else{
					if(_yellowNotify=='1'){//后
						if(parseInt(_yellowNotifyDay)>=parseInt(_redNotifyDay)){//黄灯小于红灯
							alert("交换日期为前的红灯规则天数要求要大于黄灯规则天数！");
							return false;
						}
						return true;
					}
				}
			}
			return true;
		}
	</script>
	 <!-- 窗体公共方法-->
	<script type="text/javascript">
		//数据类型操作
		function  changeData(obj){
			var dataStructureSel=$("#dataStructureSel");
		     var dataSourceNameSel=$("#dataSourceNameSel");
			 if($(obj).val()=='0'){//如果是文档数据类型
				$("#dataStructureSel").attr("disabled",false);
				$("#dataValueSel").attr("disabled",false);
			    $("#dataSourceSel").attr("disabled",true);
			    $("#tableNameInput").attr("disabled",true);
			   
			    dataStructureSel.empty();
			    //dataStructure.find("option:first-child~option").remove();
			    dataStructureSel.append('<option value="">请选择</option>'); 
			    dataStructureSel.append('<option value="0">非结构化数据</option>'); 
			    dataStructureSel.append('<option value="1">结构化数据</option>'); 
		 	 }else if($(obj).val()=='1'){//如果是数据库数据类型
				$("#dataStructureSel").attr("disabled",true);
				$("#dataValueSel").attr("disabled",true);
			    $("#dataSourceSel").attr("disabled",false);
			    $("#tableNameInput").attr("disabled",false);
			}
		}
		//给select添加item
		function addItem(obj){
	       var dataValueSel=$("#dataValueSel");
			if($(obj).val()=='0'){//如果是非结构化数据类型
				dataValueSel.empty();
				dataValueSel.append('<option value="">请选择</option>'); 
		    	dataValueSel.append('<option value="doc" selected="true">doc,docx</option>'); 
		 	 }else if($(obj).val()=='1'){//如果是结构化数据类型
				dataValueSel.empty();
				dataValueSel.append('<option value="">请选择</option>'); 
		    	dataValueSel.append('<option value="xml" selected="true">xml</option>'); 
		    	dataValueSel.append('<option value="excel">xls,xlsx</option>'); 
		    	dataValueSel.append('<option value="csv">csv</option>'); 
			}
		}
	</script>
	<script type="text/javascript"> 
		$(document).ready(function(){
			//大小写转换
			$('#itemCode').keypress(function(e) {     
			    var keyCode= event.keyCode;  
			    var realkey = String.fromCharCode(keyCode).toUpperCase();  
			    $(this).val($(this).val()+realkey);  
			    event.returnValue =false;  
			});
			
			$("input[type='radio'][name='dsType']").click(function(){
				var deptId = $('#deptId').val();
				if(deptId){}else{
					alert("请先选择部门！");
					return;
				}
				var value = $(this).attr('value');
				var url = "";
				switch(value){
					case '0':
					  url = "${path}/cloudnode/datasource/datasource!getDataSourceJsonStr.action";
					  break;
					case '1':
					  url = "${path}/cloudnode/ftplisten/ftplisten!getFtpDataSourceJsonStr.action";
					  break;
					case '2':
					  url = "${path}/cloudnode/messagelisten/messagelisten!getMQDataSourceJsonStr.action";
					  break;
					case '3':
					  url = "${path}/iswapmq/external/webservice/webInfoAction!getWSDataSourceJsonStr.action";
					  break;
					case '4':
					  url = "${path}/cloudnode/cloudnodeListen/cloudnodeListen!getMongoDataSourceJsonStr.action";
				}
				if(url){
					$.post(url,{deptId:deptId},function(result, status){
						var options = "";
						if(result){
							var resobj = eval("("+result+")");
							$.each(resobj, function(index, obj){
								options += "<option value='"+obj.id+"'>"+obj.name+"</option>";
							});
						}
						$('#dataSourceSel').html(options);
					});
				}
			});
		    $("input[type='radio'][name='dsType']:first").trigger("click");
		});
	</script>
	</body>
</html>