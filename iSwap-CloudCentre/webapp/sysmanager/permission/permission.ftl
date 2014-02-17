<#global path = request.getContextPath() >
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>功能菜单权限设置</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
		<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
	</head>
	<body style="margin: 0px;padding:0px; width: 100%;height: 100%;">
	    <ul id="tree" class="tree" style="height:100%;width:100%;overflow:auto;"></ul>
		<script type="text/javascript">
				var zTree;//树
				var setting;//参数设置
				var zTreeNodes = [] ;//数据
			    setting={
			    async : true,//异步加载 
			    asyncUrl: "${path}/sysmanager/permission/permission!permissionTree.action?roleId="+${roleId},//数据文件 
			    showLine:true,
			    checkable:true,
			    isSimpleData:true,
			    checkType:{"Y":"ps", "N":"ps"},
			    treeNodeKey:"id",
			    treeNodeParentKey:"pid"
			    };		
		       function loadTree(){
	       		   zTree=$("#tree").zTree(setting,zTreeNodes);
				}
		        $(document).ready(function(){
					loadTree();//载入树
		    });
		    
		   //得到选中的节点
		 function getSelectedNodes(){
			 var sNode=zTree.getCheckedNodes(true);
			  var ids='';
		     for(i=0;i<sNode.length;i++){
		   		 var id=  $(sNode[i]).attr("id")
		   		 if(id!='-1'){
		   		 	 ids+=$(sNode[i]).attr("id")+",";
		   		 }
			}
			return  ids;
		}
		
	   //设置权限
		function setPermission(){
				var ids=getSelectedNodes();
				if(ids==''){
					alert("请选择菜单!");
					return;
				}
	    		 $.ajax({
				  type: "POST",
				   url: "permission!addPermissionRole.action?roleId="+${roleId}+"&permissionIds="+ids,
				   success: function(msg){
				   if(msg==1){
				   alert("保存成功!");
				   }else{
				   alert("保存失败!");
					   }
					   }
					});
	    	}
	    	</script>
	</body>
</html>

