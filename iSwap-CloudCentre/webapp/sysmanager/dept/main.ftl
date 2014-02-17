<#global path = request.getContextPath() >
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title>部门基本信息</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
		<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
	</head>
	<body>
	    <div class="common_menu">
	      <div class="c_m_title">
	      	<img src="${path}/images/title/dept.png"  align="absmiddle" />
	     	 部门信息管理
	      </div>
	    </div>
   		<div class="main_c2" >
           <div class="left_tree_com">
		        <h1>
		          <div class="left_topimg right">
		          	<a href="javascript:void(0)" id="new" onclick="addNode();"><img src="${path}/images/tools/report_add.png" title="新建"/></a>
		          </div>
		          <div class="left_topimg" id="menu">
			          <a href="javascript:void(0)" id="cut"><img src="${path}/images/tools/cut_red.png" rtitle="剪切" onclick="copyNode(1)"/></a><a href="javascript:void(0)" id="copy"><img src="${path}/images/tools/page_white_copy.png" title="复制" onclick="copyNode('0')"/></a>
			          <a href="javascript:void(0)" id="paste"><img src="${path}/images/tools/page_white_paste.png" onclick="pasteNode()" title="粘贴"/></a>
		          </div>
		          <div class="left_topimg left">
		          	<a href="javascript:void(0)"  onclick="moveTreeNode('up')"><img src="${path}/images/ztree/up.png"  title="上移"/></a>
			        <a href="javascript:void(0)"  onclick="moveTreeNode('down')"><img src="${path}/images/ztree/down.png"  title="下移"/></a>
		          </div>
		        </h1>
		        <span id="treeOutSpan">
			        <p>
			         <ul id="tree" class="tree" style="height:420px;width:250px;overflow:auto;"></ul>
			        </p>
		        </span> 
	       </div>
	       <div class="right_main" style=" height:100% ;OVERFLOW-y:auto;">
           		<iframe id="content" src="${path}/sysmanager/dept/dept!view.action?id=0" width="100%"  height="750px";  frameborder="0" style="" allowtransparency="true"></iframe>
           </div>
       </div>
	   <div class="clear"></div>
	   <script type="text/javascript">
			var zTree;//树
			var setting;//参数设置
			var zTreeNodes = [] ;//数据
		    setting={
		    callback : {
		      click: zTreeBeforeClick,//click事件
		      beforeRename: zTreeBeforeRename,
		   	  rename: zTreeOnRename,//修改名称
		      beforeRemove: zTreeBeforeDel,
		      remove: zTreeOnRemove, //删除事件
		      beforeDrag: zTreeBeforeDrag,
		      beforeDrop: zTreeBeforeDrop,
			  drop: zTreeOnDrop
		    },
		    async : true,//异步加载 
		    asyncUrl: "${path}/sysmanager/dept/dept!getDeptTree.action",//数据文件 
		    showLine:true,
		    isSimpleData:true,
		    treeNodeKey:"id",
		    treeNodeParentKey:"pid",
		    dragCopy:true,
		    dragMove : true,
		    editable:true,
			edit_renameBtn : true,
			edit_removeBtn : true
		    };		
		       function loadTree(){
           		   zTree=$("#tree").zTree(setting,zTreeNodes);
    			}
		        $(document).ready(function(){
					loadTree();//载入树
		    });
		    //刷新tree
		    function refreshTree(){
		    	zTree=$("#tree").zTree(setting,zTreeNodes);
				zTree.refresh();
		    }
		    //点击节点之前的操作
		    function zTreeBeforeClick(event,treeId, treeNode){
				 $("#content").attr("src","${path}/sysmanager/dept/dept!view.action?id="+treeNode.id);				    
		    }
		    function addNode(){
		   		 var selectedNode = zTree.getSelectedNode();
		   		 var pid=-1;
		   		 var parentNode = zTree.getNodeByParam("id", -1);
				if(selectedNode!=null){
					pid=selectedNode.id;
					parentNode=selectedNode;
		   		}
			    var newNodes = [{"pid":-1,name:'部门名称'}];
				$.post(
					"${path}/sysmanager/dept/dept!addDept.action", 
					{ 
						"parentId": pid, 
						"name" : encodeURIComponent(newNodes[0].name)
					}, 
					function (r) {
						if(!r){
							 zTree.remove(newNodes);
						}
						//添加成功
						if(r!=null&&r!=0) {
							$(newNodes).attr("id",r);
							zTree.addNodes(parentNode, newNodes);
						}else{
							//移除刚才添加的节点
						    zTree.remove(newNodes);
						}
					}
				);
		    }
		     //修改节点名称
		 function zTreeBeforeRename(treeId, treeNode) {
		 		if(treeNode.id==-1){
		 			alert("此节点不能修改");
		 			return false;
		 		}
			}
		    //修改节点名称
		    function zTreeOnRename(event, treeId, treeNode) {
					$.post(
						"${path}/sysmanager/dept/dept!rename.action", 
						{ 
							"id": treeNode.id, 
							"rename" : encodeURIComponent(treeNode.name)
						 }
					);
				}
				//删除之前的操作
				function zTreeBeforeDel(treeId, treeNode) {
				 	var fa=true;
					if(treeNode.id==-1){
						alert("根节点不能删除");
						return false;
					 }
					 		if(treeNode.isParent){
					 			alert("不能级联删除部门!");
					 		    return false;	
						    	}
						    	if(confirm("您确定要删除该部门吗")){
						    	
						    		}else{
						    			return false;
						         }
				}
				//删除操作
				function zTreeOnRemove(event, treeId, treeNode) {
					$.post(
						"${path}/sysmanager/dept/dept!delete.action", 
						{ 
							"id": treeNode.id
						 },function(data){
						 	if(!data){
						 		alert("删除失败!");
						 	 	zTree=$("#tree").zTree(setting,zTreeNodes);
								zTree.refresh();
						 	}else{
						 	  $("#content").attr("src","${path}/sysmanager/dept/dept!view.action?id=0");
						 	}
						 }
					);
				}
				//拖拽操作之前
				function zTreeBeforeDrag(treeId, treeNode) {
				       if(treeNode.id==0){
				        return false;
				       }
				}
					//拖拽操作之前
					function zTreeBeforeDrop(treeId, treeNode, targetNode, moveType) {
						 if(treeNode==null){
							 return false;
						 }
						 if(targetNode==null){
						 	return false;
						 }
					}
				
				// treeId树的实例  treeNode 节点    targetNode 目标节点  moveType
					function zTreeOnDrop(event, treeId, treeNode, targetNode, moveType) {
						     	if(treeNode!=null&&targetNode!=null){
						     	 zTree.moveNode(targetNode,treeNode, "inner");//
						  		$.post(
									"${path}/sysmanager/dept/dept!move.action", 
									{ 
										"id": treeNode.id,
										"parentId": targetNode.id
									 },function(data){
									 	if(!data){
									 		alert("移动失败!");
									 	 	zTree=$("#tree").zTree(setting,zTreeNodes);
											zTree.refresh();
									 	}
									 }
								);
						  	}
					}
					//复制节点
					 var cNode;
					 var editNode;
					function  copyNode(type){
						editNode=type;
						cNode = zTree.getSelectedNode();
					}
					//粘贴事件
					function pasteNode(type){
						if(cNode==null){
							return;
						}
						//复制
						if(editNode=='0'){
							var selectedNode = zTree.getSelectedNode();
							if(selectedNode==null){
							 return;
							}
							var newNodes = [{"pid":-1,name:cNode.name}];
							 $.post(
								"${path}/sysmanager/dept/dept!addDept.action", 
								{ 
									"parentId": selectedNode.id, 
									"name" : encodeURIComponent(newNodes[0].name)
								}, 
								function (r) {
									if(!r){
										 zTree.remove(newNodes);
									}
									//添加成功
									if(r!=null&&r!=0) {
										$(newNodes).attr("id",r);
										 zTree.addNodes(selectedNode, newNodes);
									}
									else {
									   //移除刚才添加的节点
									   zTree.remove(newNodes);
									}
								}
							);
						}else{
						//剪切
							var selectedNode = zTree.getSelectedNode();
							if(selectedNode==null){
							 return;
							}
							if(selectedNode.id==cNode.id){
								return;
							}
							$.post(
									"${path}/sysmanager/dept/dept!move.action", 
									{ 
										"id": cNode.id,
										"parentId": selectedNode.id
									 },function(data){
									 	if(!data){
									 		alert("移动失败!");
									 	 	//zTree=$("#tree").zTree(setting,zTreeNodes);
											//zTree.refresh();
									 	}else{
									 		zTree.moveNode(selectedNode, cNode, "inner");
									 	}
									 }
								);
							}
					}
					
					////////////////////////////
					function getPreTreeNode(treeNode) {
						if (treeNode.isFirstNode) return null;
						var nodes = treeNode.parentNode ? treeNode.parentNode.nodes : zTree.getNodes();
						var preNode;
						for (var i=0; i<nodes.length; i++) {
							if (nodes[i] == treeNode) {
								return preNode;
							}
							preNode = nodes[i];
						}
					}
					function getNextTreeNode(treeNode) {
						if (treeNode.isLastNode) return null;
						var nodes = treeNode.parentNode ? treeNode.parentNode.nodes : zTree.getNodes();
						for (var i=0; i<nodes.length; i++) {
						if (nodes[i] == treeNode) {
							return nodes[i+1];
						 	}
						}
					}
			    ////////////////////////////
					//排序操作
				function moveTreeNode(move) {
					var srcNode = zTree.getSelectedNode();
					if (!srcNode) {
						alert("请先选中一个节点");
						return;
					}
				   var moveType = "inner";
					var targetNode = "";
					if (move == "up") {
						moveType = "before";
						targetNode = getPreTreeNode(srcNode);
					}else if(move == "down") {
						moveType = "after";
						targetNode = getNextTreeNode(srcNode);
					} 
							if (srcNode && targetNode) {
								   var ids="";
								   zTree.moveNode(targetNode, srcNode, moveType);
								   var len=targetNode.parentNode.nodes;
									for(i=0;i<len.length;i++){
										ids+=len[i].id+",";
									}
								$.post(
									"${path}/sysmanager/dept/dept!order.action", 
									{ 
										"sids":ids
									 },function(data){
									 	if(!data){
									 		alert("移动失败!");
									 	 	//zTree=$("#tree").zTree(setting,zTreeNodes);
											//zTree.refresh();
									 	}/*else{
									 		zTree.moveNode(targetNode, srcNode, moveType);
									 	}*/
									 }
								);
						//setOperatePool();
						
					}
			}
		</script>
	</body>
</html>
