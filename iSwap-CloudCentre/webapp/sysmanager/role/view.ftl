<#global path = request.getContextPath() >
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>功能菜单权限设置</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
</script>
</head>
<body>
              	  <ul id="tree" class="tree" style="height:300px;width:445px;overflow:auto;"></ul>
					<script type="text/javascript">
							var zTree;//树
							var setting;//参数设置
							var zTreeNodes = [] ;//数据
						    setting={
						    callback : {
						      beforeClick: zTreeBeforeClick
						    },
						    fontCss:setFontCss,
						    async : true,//异步加载 
						    asyncUrl: "${path}/sysmanager/role/role!roleTree.action",//数据文件 
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
						    function setFontCss(treeId,treeNode){
						    	if(treeNode.id==1){
						    		return {color:"gray"};
						    	}
						    	return {};
						    }
						    //点击节点之前的操作
						    function zTreeBeforeClick(treeId, treeNode){
						    		var id=treeNode.id;
							     	//if(id==-1||id==1){
							     		// return;
							     	//}
								window.parent.invoke(id);//调用菜单iframe	    
						    }
				</script>
</body>
</html>

