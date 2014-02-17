<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		</head>
		<body>		
							<ul id="tree" class="tree" style="width:500px;overflow:auto;"></ul>
								<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
								<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
								<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
								<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
									<script type="text/javascript">
											var zTree;//树
											var setting;//参数设置
											var zTreeNodes = [] ;//数据
									    setting={
									    async : true,//异步加载 
									    asyncUrl: "${path}/cloudstorage/tableinfo/tableinfo!dataTree.action?itemId=${itemId?default('')}&deptId=${deptId?default('')}&appId=${appId?default('')}&applyId=${applyId?default('')}",//数据文件 
									    showLine:true,
									    checkable:true,
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
										return ids;
									}
										    	 //得到选中的节点
							   		 function getNoSelectedNodes(){
										 var sNode=zTree.getCheckedNodes(false);
										  var ids='';
									     for(i=0;i<sNode.length;i++){
									   		 var id=  $(sNode[i]).attr("id")
											   		 if(id!='-1'){
											   		 	 ids+=$(sNode[i]).attr("id")+",";
											   		 }
											}
										return ids;
							    }
								</script>
		</body>
		</html>	 
	 