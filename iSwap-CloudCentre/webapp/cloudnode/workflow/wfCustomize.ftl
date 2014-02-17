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
		 	var treeData = [];
		 	var manager = null; 
		 	var treeConfig = { data:treeData,  
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
								        	$('#content').attr("src","${path}/cloudnode/workflow/workflow!list.action?changeItemId="+node.data.uri+"&deptId=${deptId}&appMsgId="+appId+"&itemId="+itemId+"&itemType="+itemType);
				                }};
			var idArr = [];
	        function appItemGrep(){
	        	var searchText = $('#searchText').val();
	        	manager.clear();
	        	if(searchText){
				}else{
					searchText = "应用";
				}
				var grepData = $.grep(treeData, function(cur,i){
					var bool = (cur['name'].indexOf(searchText))>=0?true:false;
					if(bool)idArr.push(cur['id']);
					return bool;
				});
				treeConfig.data = grepData;
        		$("#tree1").ligerTree(treeConfig);
				manager = $("#tree1").ligerGetTreeManager(); 
	        }
	        
	        $(document).ready(function(){
	        	$.post("${path}/exchange/item/item!getAppItemTreeByDeptId4Node.action",{deptid:'${deptId}'},function(result, status){
					if(result){
						treeData = result;
						treeConfig.data = treeData;
	        			$("#tree1").ligerTree(treeConfig);
	        			manager = $("#tree1").ligerGetTreeManager(); 
	        			//manager.append(treeData);
					}
				});
				//$("#tree1").ligerTree(treeConfig);
	        	//manager = $("#tree1").ligerGetTreeManager();
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
		        <div position="left" title='<input id="searchText" type="text" /><a onclick="appItemGrep();return false;">查询</a>'>
					<ul id="tree1">
				    </ul> 
		        </div>
		        <div position="center" title="流程列表">
					<iframe id="content" src="${path}/cloudnode/workflow/workflow!list.action?deptId=${deptId}" width="100%"  height="750px";  frameborder="0" style="" allowtransparency="true"></iframe>
		        </div>
		    </div>
	   <div class="clear"></div>
		<#include "/common/commonLhg.ftl">
		<script type="text/javascript">    
		</script>
	</body>
</html>
	<script type="text/javascript">
	    $(document).ready(function () {
	    });
	</script>
