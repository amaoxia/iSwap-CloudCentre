 <#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
				<html xmlns="http://www.w3.org/1999/xhtml">
				<head>
					<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
					<title></title>
					</head>
					<body>
				 		<div class="zTreeDemoBackground">
							<ul id="tree" class="tree" style="width:230px;height:300px;"></ul>
						</div>	
						
						<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
						<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
						<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
						<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
						<script type="text/javascript">
							var zTree;//树
							var setting;//参数设置
							var zTreeNodes = [] ;//数据
					    setting={
					    async : true,//异步加载 
					    asyncUrl: "${path}/cloudcenter/appMsg/appMsg!tree.action?type=${type}",//数据文件 
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
				</script>	
				</body>
				</html>	 
			 