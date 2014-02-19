<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#global path = request.getContextPath() >
		<#include "/common/taglibs.ftl">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${path}/js/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>	
		<script src="${path}/js/liger/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    	<script src="${path}/js/liger/lib/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
		<script type="text/javascript" src="${path}/js/liger/lib/ligerUI/js/plugins/ligerTree.js"></script>
		 <script type="text/javascript">
		 	 $(function () {
	            $("#container").ligerLayout({ leftWidth: 200 }); //这一句可是关键啊
	        });
		 	var treeDataStr = "";
		 	var manager = null; 
		 	var treeConfig = { data:null,  
    							textFieldName:"name",
				                idFieldName:"id",
				                parentIDFieldName:"pid",
				                checkbox: false,
				                onClick: function(node){
				                	var obj = node.data;
						        	//var dom = $(this.getNodeDom(node.data.treedataindex));
						        	//alert($(dom).html());
							        var appId = "";
							        var itemId = "";
							        var itemType = "";
					  
						        	if(node.data.uri){}else{
							        	var paramArray = node.data.id.split("-");
							        	var appId = paramArray[0];
							        	var itemId = paramArray.length>1?paramArray[1]:"";
							        	var itemType = paramArray.length>2?paramArray[2]:"";
						        	}
						        	$('#content').attr("src","${path}/iswapesb/workflow/esbworkflowAction!list.action?changeItemId="+node.data.uri+"&appMsgId="+appId+"&itemId="+itemId+"&itemType="+itemType);
				                }};
			//根据条件过滤数据
	        function appItemGrep(){
	        	var searchText = $('#searchText').val();
	        	var grepData = eval("("+treeDataStr+")");
	        	if(searchText){
	        		var rootData = null;
	        		var tempData = new Array();
	        		var appMsgArray = new Array();
	        		var appItemArray = new Array();
	        		var taskArray = new Array();
	        		$.each(grepData, function(index, nodeData){
	        			if(nodeData.id=="-1"){
							rootData = nodeData;
						}else if(nodeData.name.indexOf(searchText)>=0){
							tempData.push(nodeData);
						}else if(nodeData.id.split("-").length=1){//应用
							appMsgArray[nodeData.id] = nodeData;
						}else if(nodeData.id.split("-").length==2){//指标
							appItemArray[nodeData.id] = nodeData;
						}else if(nodeData.id.split("-").length==3){//发送接收任务
							var taskNodeArray = taskArray[nodeData.id];
							if(taskNodeArray){
								taskNodeArray.push(nodeData);
							}else{
								taskNodeArray = new Array();
								taskNodeArray.push(nodeData);
								taskArray[nodeData.id] = taskNodeArray;
							}
						}
					});
					grepData = new Array();
					$.each(tempData, function(index, nodeData){//补指标和应用
						grepData.push(nodeData);
						if(nodeData.id.split("-").length==1){//应用补指标
							var tempArray = new Array();
							$.each(appItemArray, function(i, itemData){
								if(itemData.id){//指标id是否以应用id为前缀
									grepData.push(itemData);
								}else{
									tempArray[itemData.id] = itemData;
								}
							});
							appItemArray = tempArray;
						}else if(nodeData.id.split("-").length==2){//指标补应用
							var appMsgId = nodeData.id.substring(0,nodeData.id.indexOf("-"));
							var msgData = appMsgArray[appMsgId];
							if(msgData){
								grepData.push(msgData);
								appMsgArray[appMsgId] = null;
							}
						}
					});
					var tempGrepData = {};
   					extend(tempGrepData,grepData);//拷贝数组
					$.each(tempGrepData, function(index, nodeData){//指标补任务
						var taskDataArray = taskArray[nodeData.id];
						if(taskDataArray){
							grepData.concat(taskDataArray);
							taskArray[nodeData.id] = null;
						}
					});
					grepData.push(rootData);
				}
				manager.clear();
				manager.setData(grepData);
	        }
	        function getType(o){
		        var _t;
		        return ((_t = typeof(o)) == "object" ? o==null && "null" || Object.prototype.toString.call(o).slice(8,-1):_t).toLowerCase();
		    }
		    function extend(destination,source){
		        for(var p in source){
		            if(getType(source[p])=="array"||getType(source[p])=="object"){
		                destination[p]=getType(source[p])=="array"?[]:{};
		                arguments.callee(destination[p],source[p]);
		            }else{
		                destination[p]=source[p];
		            }
		        }
		    }
	        
	        $(document).ready(function(){
	        	$("#tree1").ligerTree(treeConfig);
	        	manager = $("#tree1").ligerGetTreeManager(); 
	        	$.post("${path}/exchange/item/item!getAppItemTreeByDeptId4Center.action",{},function(result, status){
					if(result){
						treeDataStr = result;
	        			manager.setData(eval("("+result+")"));
					}
				});
	        });
	    </script>
	</head>
	<body>
		    <div class="common_menu">
			      <div class="c_m_title">
			      	<img src="${path}/images/title/img_05.png"  align="absmiddle" />
			     	 流程定制
			      </div>
		     </div>
		     <div id="container">
		        <div position="left" title='<input id="searchText" type="text" /><a onclick="appItemGrep();return false;">查询</a>'><!-- style='background-image:url(${path}/images/login_help.png);background-position:right bottom;'-->
					<ul id="tree1">
				    </ul> 
		        </div>
		        <div position="center" title="流程列表">
					<iframe id="content" src="${path}/iswapesb/workflow/esbworkflowAction!list.action" width="100%"  height="750px";  frameborder="0" style="" allowtransparency="true"></iframe>
		        </div>
		    </div>
	   <div class="clear"></div>
		<#include "/common/commonLhg.ftl">
		<script type="text/javascript" src="${path}/js/iswap_table.js"></script>
		<script type="text/javascript">    
		</script>
	</body>
</html>
