<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>指标管理</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
<head>
<body>
<div class="pop_01" id="wpageslide" style="width:622px;height:500px;overflow-x:hidden;overflow-y:scroll;">
<div id="wpages" class="wpages">
  <div class="pop_mian wpage">
<form method="post" action="${path}/exchange/item/item!update.action" id="saveForm">
	<input type="hidden"  name="cycle.useDefaultRule"  value="1" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
              <div class="item1">
                  <ul class="item1_c">
                    <li>
                      <p>指标名称:</p>
                      <span>
                      <input type="text" size="30"  name="itemName" id="itemName" value="${itemName?default('')}"/>
                      <input type="hidden"   name="id" value="${id}" id="id"/>
                      </span>
                      <span><div id="itemNameTip"></div></span>
                    </li>
                    
                    <li class="item_bg">
                      <p>指标编码:</p>
                      <span>
                      <input type="text" name="itemCode" id="itemCode"  size="30" value="${itemCode?default('')}" maxlength="30"/></span>
                      <span><div id="itemCodeTip"></div></span>
                      </li>
                      
                    <li>
                      <p>所属部门:</p>
                      <#if depts?exists>
	                      <#list depts as entity>
	                      	<span>
	                 			  <input type="text" id="deptnames" size="30" disabled="true" value="${entity.shortName}"/> 
	                 			  <input type="hidden" id="deptId"  name="sysDept.id" value="${entity.id}" />
	                 			  <input type="hidden" id="deptId"  name="deptName" value="${entity.shortName}" /> 
	                      	</span>
	                      </#list>
                      <#else>
						<span>
                 			  <input type="text" id="deptName" size="30"  value="<#if entityobj.sysDept?exists>${entityobj.sysDept.deptName?default('')}</#if>" readOnly="true"/> 
                 			  <input type="hidden" id="deptId"  name="sysDept.id" display  value="<#if entityobj.sysDept?exists>${entityobj.sysDept.id?default('')}</#if>" /> 
                      </span>
                      <span><div id="deptNameTip"></div></span>
                      </#if>
                      
                    </li>
                   <!-- <li class="item_bg">
                      <p>应用服务:</p>
                      <span>
                       <select name="appMgName" multiple style="width: 150px;" id="appMgName">
                       <#list appMsgs as data>
                           <#assign isTrue="">
                       <#list itemApp as item>
					  <#if item.appMsg.id==data.id><#assign isTrue="selected"><#break></#if>                      
                       </#list>
                       	<option value="${data.id}" ${isTrue}>${data.appName?default('')}</option> 
						</#list>
                       </select>
                 	  </span>
                 	  <span> <div id="appMgNameTip"></div></span>
                 	  </li>
                    -->
                    <li class="item_bg">
                      <p>交换类型</p>
                      <span id="dataTypeSpan">
                      <select name="dataType" id="dataType" onchange="changeData(this)" style="width:200px">
                      <option value="">请选择</option>
                      <option value="0" <#if dataType=='0'>selected</#if>>文档</option>
                      <option value="1" <#if dataType=='1'>selected</#if>>数据库</option>
                      </select>
                      </span>
                      <span><div id="dataTypeTip"></div></span>
                 	  </li>
                     <li>
           			  <span>
           			   文档结构类型
                      <select name="dataStructure"  id="dataStructure" onchange="addItem(this)"  <#if dataType=='1'>disabled</#if>>
                      <option value="">请选择</option>
                      <#if dataType=='0'>
                      <#if dataStructure?exists>
                      <option value="0"  <#if  dataStructure=='0'>selected</#if>>非结构化</option>
                      <option value="1"  <#if  dataStructure=='1'>selected</#if>>结构化</option>
                      <#else>
                      <option value="0"  >非结构化</option>
                      <option value="1"  >结构化</option>
                      </#if>
                      </#if>
                      </select>
                      	  文件类型:
                      <select name="dataValue" id="dataValueId"  <#if dataType=='1'>disabled</#if>>
                      <option value="">请选择</option>
                      <<#if dataType=='0'>
                      <#if dataStructure?exists>
                      	<#if dataStructure=='0'>
                      		<option value="doc,docx" <#if dataValue=="doc,docx">selected</#if>>doc</option>
                      	</#if>
                      	<#if dataStructure=='1'>
                      	<option value="xml" <#if dataValue=="xml">selected</#if>>xml</option>
                      	<option value="xsl,xslx" <#if dataValue=="xls,xlsx">selected</#if>>excel</option>
                      	<option value="csv" <#if dataValue=="csv">selected</#if>>csv</option>
                      	</#if>
                      </#if>
                      </#if>
                      </select>
                 	  </span>
                 	  </li>
                 	  
                      <li>
                      <p>数据源:</p>
                      <span>
                      <select name="dataSource.id" id="dataSourceId" style="width:200px" <#if dataType=='0'>disabled</#if>>
						<option value="">请选择</option>
						<#list dataSources as data>
						<option value="${data.id}" <#if entityobj.dataSource?exists><#if entityobj.dataSource.id==data.id>selected</#if></#if>>${data.sourceName?default('')}</option>
						</#list>
                      </select>
                 	  </span>
                 	  </li>
                    
                    <li class="item_bg">
                      <p>表名:</p>
                      <span>
                      <input type="text" name="tableName" id="tableNameId"  size="30" value="${entityobj.tableName?default('')}"/></span>
                      <span><div id="tableNameTip"></div></span>
                      </li>
                      <input type="hidden" name="cycle.id" id="cycleId" value="${cycle.id}"/>
                      <li  class="item_bg">
                      <p>交换周期:</p>
                      <span>
                        <select name="cycle.exchangeCycleValue" id="exchangeCycleValue"  style="width:190px"  onchange="linkageCycle(parseInt(this.value))">
	                    <!--option value="0"  <#if cycle.exchangeCycleValue=="0">selected</#if>>周</option-->
	                    <option value="1"  <#if cycle.exchangeCycleValue=="1">selected</#if>>月</option>
	                    <option value="2"  <#if cycle.exchangeCycleValue=="2">selected</#if>>季</option>
	                    <option value="3"  <#if cycle.exchangeCycleValue=="3">selected</#if>>年</option>
                   	    </select> 
					</span>
                      </li>
                      <li>
                      <p>交换日期规则:</p>
                      <!--
                      <span  id="w_span"  <#if cycle.exchangeCycleValue!="0">style="display: none;"</#if>>
                      <#assign sel=cycle.exchangeDateRule?split(",")[0]>
                      <#assign day=cycle.exchangeDateRule?split(",")[1]>
                   	        本周数据在:
                       <select id="w_sel" name="w_sel" style="width:80px">
	                      <option value="0"  <#if cycle.exchangeCycleValue=="0"><#if sel=='0'>selected</#if></#if>>
	                     	 本周
	                      </option>
	                      <option value="1" <#if cycle.exchangeCycleValue=='0'><#if sel=='1'>selected</#if></#if>>
	                  	    下周
	                      </option>
	                      </select>
	                      <select id="w_day" name="w_day" style="width:60px">
	                     <#list   1..7 as temp>
   						  <option value="${temp}"  <#if cycle.exchangeCycleValue=='0'><#if day?string==temp?string>selected</#if></#if>>${temp}</option>
						 </#list>
                     	 </select>
                     	 	 执行
                      </span>-->
                      <!--- 月 --->
                        <span  <#if cycle.exchangeCycleValue!="1">style="display: none;"</#if>id="m_span">
                      	     本月数据在:
                          <select id="m_sel" name="m_sel" style="width:80px">
			                     <option value="0"  <#if cycle.exchangeCycleValue=='1'><#if sel=='0'>selected</#if></#if>>
			                     	 本月
			                      </option>
			                      <option value="1" <#if cycle.exchangeCycleValue=='1'><#if sel=='1'>selected</#if></#if>>
			                  	    下月
			                      </option>
	                   		 <!--  <option value="1" selected>下月</option>-->
	                      </select>
	                      <select id="m_day" name="m_day" style="width:60px">
	                     <#list   1..31 as temp>
   						  <option value="${temp}" <#if cycle.exchangeCycleValue=='1'><#if day?string==temp?string>selected</#if></#if>>${temp}</option>
						 </#list>
						<!--  <option value="20">20</option>-->
                     	 </select>
                     	 执行
                      </span>
                      <!--- 季 -->
                        <span <#if cycle.exchangeCycleValue!="2">style="display: none;"</#if> id="j_span">
                     	   本季数据在:
                          <select id="j_sel" name="j_sel" style="width:80px">
                         <!-- <option value="1">下季  </option>-->
	                      <option value="0"  <#if cycle.exchangeCycleValue=='2'><#if sel=='0'>selected</#if></#if>>本季</option>
	                      <option value="1" <#if cycle.exchangeCycleValue=='2'><#if  sel=='1'>selected</#if></#if>>下季  </option>
	                      </select>
	                      <select id="j_month" name="j_month" style="width:60px">
	                     <!-- <option value="1" selected>第一月</option>-->
   						 <option value="1" <#if cycle.exchangeCycleValue=='2'><#if day=='1'>selected</#if></#if>>第一月</option>
   						  <option value="2" <#if cycle.exchangeCycleValue=='2'><#if day=='2'>selected</#if></#if>>第二月</option>
   						  <option value="3" <#if cycle.exchangeCycleValue=='2'><#if day=='3'>selected</#if></#if>>第三月</option>
                     	 </select>
                     	 的第
                     	 <select id="j_day" name="j_day" style="width:60px">
                     	<!-- <option value="20">20</option>-->
	                    <#list   1..31 as temp>
   						  <option value="${temp}"  <#if cycle.exchangeCycleValue=='2'><#if (cycle.exchangeDateRule?split(",")[2])?string==temp?string>selected</#if></#if>>${temp}</option>
						 </#list>
                     	 </select>
                     	 日执行
                      </span>
                      <!--- 年 -->
                           <span  <#if cycle.exchangeCycleValue!="3">style="display: none;"</#if> id="y_span">
                       	 	本年数据在:
                          <select id="y_sel" name="y_sel" style="width:80px">
	                      <option value="0" <#if cycle.exchangeCycleValue=='3'><#if sel=='0'>selected</#if></#if>>
	                     	 本年
	                      </option>
	                      <option value="1" <#if cycle.exchangeCycleValue=='3'><#if sel=='1'>selected</#if></#if>>
	                  	    下年
	                      </option>
	                      <!--<option value="1" selected>
	                  	      下年
	                      </option>-->
	                      </select>
	                      <select id="y_month" name="y_month" style="width:60px">
	                  	  <#list   1..12 as temp>
   						 	 <option value="${temp}" <#if cycle.exchangeCycleValue=='3'><#if day?string==temp?string>selected</#if></#if>>${temp}</option>
						 </#list>
						 <!-- <#list 1..12 as temp>
							 <option value="${temp}" selected>${temp}</option>
						 </#list>-->
                     	 </select>
                     	  月
                     	 <select id="y_day" name="y_day" style="width:60px">
	                     <#list   1..31 as temp>
   						  <option value="${temp}" <#if cycle.exchangeCycleValue=='3'><#if (cycle.exchangeDateRule?split(",")[2])?string==temp?string>selected</#if></#if>>${temp}</option>
						 </#list>
						 <!--<option value="30">30</option>-->
                     	 </select>
                     	 日执行
                      </span>
                      </li>
                   <!-- <li>
                     <p> 是否采用系统默认规则:
                     <input type="radio" name="cycle.useDefaultRule" value="1"  onclick="sysRule(1)"  <#if cycle.useDefaultRule=='1'>checked</#if>/>是
                     <input  type="radio" name="cycle.useDefaultRule" value="0"  onclick="sysRule(0)"  <#if cycle.useDefaultRule=='0'>checked</#if>/>否
                    </p>
                     </li>-->
                     <li <#if cycle.useDefaultRule=='1'>style="display: none;"</#if>  id="rule_green" class="item_bg">
                     	<p>绿灯显示规则:
                     		交换日期
	                     	<select name="greenNotify" id="_greenNotify"  <#if cycle.useDefaultRule=='1'>disabled</#if> onchange="greenValidator()">
	                     	 <option value="0">前</option>
	                     	</select>
	                     	<select name="greenNotifyDay" id="_greenNotifyDay" <#if cycle.useDefaultRule=='1'>disabled </#if> onchange="greenValidator()">
	                     	<#if cycle.exchangeCycleValue=='0'>
	                     	<#list 1..7 as temp>
	                     		<option value="${temp}" <#if cycle.useDefaultRule=='0'> <#if temp?string==(cycle.ruleGreenNotify?split(",")[1])?string>selected</#if> </#if>>${temp}</option>
	                     	</#list>
	                     	 <#else>
	                     	 <#list 1..10 as temp>
	                     		<option value="${temp}"  <#if cycle.useDefaultRule=='0'> <#if temp?string==(cycle.ruleGreenNotify?split(",")[1])?string>selected</#if></#if>>${temp}</option>
	                     	</#list>
	                     	</#if>
	                     	</select>
                     	</p>
                     </li>
                     <li <#if cycle.useDefaultRule=='1'>style="display: none;"</#if> id="rule_yellow">
                     	<p>黄灯显示规则:
                     	交换日期
	                     	<select name="yellowNotify" id="_yellowNotify" <#if cycle.useDefaultRule=='1'>disabled </#if> onchange="yellowValidator()">
	                     	 <option value="0" 		<#if cycle.useDefaultRule=='0'> <#if (cycle.ruleYellowNotify?split(",")[0])?string=='0'>selected</#if></#if>>前</option>
	                     	 <option value="1"  		<#if cycle.useDefaultRule=='0'><#if (cycle.ruleYellowNotify?split(",")[0])?string=='1'>selected</#if></#if>>后</option>
	                     	</select>
	                     	<select name="yellowNotifyDay" id="_yellowNotifyDay" <#if cycle.useDefaultRule=='1'>disabled </#if> onchange="yellowValidator()">
	                     	<#if cycle.exchangeCycleValue=='0'>
	                     	<#list 1..7 as temp>
	                     		<option value="${temp}" <#if cycle.useDefaultRule=='0'> <#if temp?string==cycle.ruleYellowNotify?split(",")[1]?string>selected</#if></#if>>${temp}</option>
	                     	</#list>
	                     	 <#else>
	                     	 <#list 1..10 as temp>
	                     		<option value="${temp}"  <#if cycle.useDefaultRule=='0'><#if temp?string==(cycle.ruleYellowNotify?split(",")[1])?string>selected</#if></#if>>${temp}</option>
	                     	</#list>
	                     	</#if>
	                     	</select>
                     	</p>
                     </li>
                     <li  <#if cycle.useDefaultRule=='1'>style="display: none;"</#if> id="rule_red" class="item_bg">
                     	<p>
                     	红灯显示规则:
                     	  交换日期
	                     	<select name="redNotify" id="_redNotify" <#if cycle.useDefaultRule=='1'>disabled </#if> onchange="redValidator()">
	                     	 <option value="1">后</option>
	                     	</select>
	                     	<select name="redNotifyDay" id="_redNotifyDay" <#if cycle.useDefaultRule=='1'>disabled </#if> onchange="redValidator()">
	                     	<#if cycle.exchangeCycleValue=='0'>
	                     	<#list 1..7 as temp>
	                     		<option value="${temp}"  <#if cycle.useDefaultRule=='0'><#if temp?string==(cycle.ruleRedNotify?split(",")[1])?string>selected</#if></#if>>${temp}</option>
	                     	</#list>
	                     	 <#else>
	                     	 <#list 1..10 as temp>
	                     		<option value="${temp}" <#if cycle.useDefaultRule=='0'> <#if temp?string==(cycle.ruleRedNotify?split(",")[1])?string>selected</#if></#if>>${temp}</option>
	                     	</#list>
	                     	</#if>
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
  <div class="wpage" id="deptTree" style="margin-left:10px;text-align:left;background:url(${path}/images/common_menu_bg.jpg) #CFE1ED bottom repeat-x" >
  	<ul id="tree" class="tree" style="height:300px;width:445px;overflow:auto;"></ul>
  </div>
 </div>
	<ul id="wpagebtns" class="wpagebtns">
	<li></li>
	<li></li>
</ul>
</div>
<script type='text/javascript' src='${path}/js/validator/metaData/item.js'></script> 
<#include "/common/commonSilde.ftl">
<#include "/common/commonUd.ftl">
<#include "/common/commonValidator.ftl">
			<script type="text/javascript">
							var zTree;//树
							var setting;//参数设置
							var zTreeNodes = [] ;//数据
						    setting={
						    callback : {
						      beforeClick: zTreeBeforeClick
						    },
						    async : true,//异步加载 
						    asyncUrl: "${path}/sysmanager/dept/dept!getDeptTree.action",//数据文件 
						    showLine:true,
						    isSimpleData:true,
						    treeNodeKey:"id",
						    treeNodeParentKey:"pid"
						    };		
						       function loadTree(){
				           		   zTree=$("#tree").zTree(setting,zTreeNodes);
				    			}
						        $(document).ready(function(){
									loadTree();//载入树
						    });
						    //点击节点之前的操作
						    function zTreeBeforeClick(treeId, treeNode){
						    	var id=treeNode.id;
							     	if(id==-1){
							     		alert("请选择其他节点!");
							     		zTree.cancelSelectedNode();
							     	 return;
							     	}
						    }
				</script>
<script type="text/javascript"> 
 $(document).ready(function(){
	$("div#wpageslide").wPageSilde({width: 620,height:550});
	$("#itemCode").defaultPassed();
	/**
	$("#deptName").bind('click', function(event) {
		$("#wpagebtns li").eq(1).click();
		var node =zTree.getNodeByParam("id", $("#deptId").val());
		zTree.selectNode(node);
		//取消按钮
		DG.removeBtn('reset');
		DG.removeBtn('save');
		DG.removeBtn('close');
		//添加按钮
		DG.addBtn( 'sel', '确定', sel); 
		function sel() {
			 saveClick();
			 commonBtn();
		}
		DG.addBtn( 'back', '返回', back); 
			function back() {
			commonBtn();
		}
	
		function commonBtn(){
			 DG.removeBtn('back');
			 DG.removeBtn('sel');
			 $("#wpagebtns li").eq(0).click();//返回
			 DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
			 DG.addBtn( 'reset', '重填', resetWin); 
			 DG.addBtn( 'save', '保存', saveWin); 
		}
	});
	**/
});
function  saveClick(){
		var selectedNode = zTree.getSelectedNode();
		if(selectedNode==null){
			alert("请选择其它节点");
			return;
		}
		if(selectedNode.id==-1){
			alert("请选择其它节点!");
			zTree.cancelSelectedNode();
			return;
		}	
         $("#deptId").attr("value",selectedNode.id);
         $("#deptName").attr("value",selectedNode.name).defaultPassed();
}
</script>

<script type="text/javascript">
	//数据类型操作
		function   changeData(id){
		 var selected=document.getElementById("dataStructure");
	     var dataSourceName=document.getElementById("dataSourceName");
			if($(id).val()=='0'){
			$("#dataStructure").attr("disabled",false);
			$("#dataValueId").attr("disabled",false);
		   $("#dataSourceId").attr("disabled",true);
		    $("#tableNameId").attr("disabled",true);
		   selected.options.length=1;
		   selected.options.add(new Option("非结构化数据","0")); //这个兼容IE与firefox
		   selected.options.add(new Option("结构化数据","1")); //这个兼容IE与firefox
	  }
			if($(id).val()=='1'){
			$("#dataStructure").attr("disabled",true);
			$("#dataValueId").attr("disabled",true);
		    $("#dataSourceId").attr("disabled",false);
		     $("#tableNameId").attr("disabled",false);
		     selected.options.length=1;
			}
}
		//给select添加item
	function addItem(id){
	       var selected=document.getElementById("dataValue");
			if($(id).val()=='0'){
				selected.options.length=1;
				var op=new Option("doc","doc,docx");
				op.selected=true;
				selected.options.add(op); //这个兼容IE与firefox
		 	 }
			if($(id).val()=='1'){
			    selected.options.length=1;
			    var op=new Option("xml","xml");
			    op.selected=true;
				selected.options.add(op); 
				selected.options.add(new Option("excel","xls,xlsx")); 
				selected.options.add(new Option("csv","csv")); 
			}
	}
</script>
<script  type="text/javascript">
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
							 //addItemData(10);
							break;
				     case 2:
						    $('#w_span').hide();
							$('#m_span').hide();
							$('#j_span').show();
							$('#y_span').hide();
							 //addItemData(10);
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
			//绿灯验证
			function greenValidator(){
			var cycleValue=$("#exchangeCycleValue").attr("value");//交换周期值
		    var w_sel=$("#w_sel").attr("value");//0 本周  1 下周
		    var w_day=$("#w_day").attr("value");//周几
		    var _greenNotifyDay=$("#_greenNotifyDay").val(); //绿灯天数
		    var _yellowNotify=$("#_yellowNotify").val();//黄灯规则
		    var _yellowNotifyDay=$("#_yellowNotifyDay").val();//黄灯天数
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
																     alert(_yellowNotifyDay);
																    alert(_redNotifyDay);
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
					
					function redValidator(){//红灯验证
						    var cycleValue=$("#exchangeCycleValue").attr("value");//交换周期值
						    var w_sel=$("#w_sel").attr("value");//0 本周  1 下周
						    var w_day=$("#w_day").attr("value");//周几
						    var _greenNotifyDay=$("#_greenNotifyDay").val(); //绿灯天数
						    var _yellowNotify=$("#_yellowNotify").val();//黄灯规则
						    var _yellowNotifyDay=$("#_yellowNotifyDay").val();//黄灯天数
						    var _redNotify=$("#_redNotify").val();//红灯规则
						    var _redNotifyDay=$("#_redNotifyDay").val();//红灯天数
						    
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
						          if(parseInt(_yellowNotifyDay)>=parseInt(_redNotifyDay)){	//黄灯小于红灯
						          	alert("交换日期为前的红灯规则天数要求要大于黄灯规则天数！");
						          	 return false;
						        	  }
						        	  return true;
										}
			    	  }
		}
		
					//大小写转换
			    	$('#itemCode').keypress(function(e) {     
			        var keyCode= event.keyCode;  
			        var realkey = String.fromCharCode(keyCode).toUpperCase();  
			        $(this).val($(this).val()+realkey);  
			        event.returnValue =false;  
			      });
</script>
</body>
</html>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           