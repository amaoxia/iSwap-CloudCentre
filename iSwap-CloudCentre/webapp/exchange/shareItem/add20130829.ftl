<#global path = request.getContextPath() >
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
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
</head>
<body>
	<form action="${path}/exchange/receiveDeptItem/receiveDeptItem!add.action" method="post" name="saveForm" id="saveForm">
                <@s.token name="token"/>
                <input type="hidden" id="ids" name="tids"  vlaue=""/>
	</form>

              	  <ul id="tree" class="tree" style="height:300px;width:445px;overflow:auto;"></ul>
					<script type="text/javascript">
							var zTree;//树
							var setting;//参数设置
							var zTreeNodes = [] ;//数据
						    setting={
						    callback : {
						     // beforeClick: zTreeBeforeClick
						    },
						    fontCss:setFontCss,
						    async : true,//异步加载 
						    asyncUrl: "${path}/exchange/receiveDeptItem/receiveDeptItem!deptExcludeTree.action",//数据文件 
						    checkable:true,
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
				</script>
				<script  type="text/javascript">
				var DG = frameElement.lhgDG; 
				DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
				//关闭窗口 不做任何操作
				function singleCloseWin(){
				DG.curWin.closeWindow();
				}
				DG.addBtn( 'save', '保存', save); 
				//关闭窗口 不做任何操作
				function save(){
				 var checks=zTree.getCheckedNodes();
				  if(checks==null||checks.length==0){
				  	alert("请选择部门");
				  	return;
				  }
				  if(checks.length==1){
				  	if(checks[0].id==-1){
				  		alert("请选择其它选项");
				  		return;
				  	}
				  }
				  var tids="";
				  for(i=0;i<checks.length;i++){
				  	if(	checks[i].id!=-1){
				  		tids+=checks[i].id+",";
				    	}
				  	}
				  		$("#ids").attr("value",tids);
				  	if($("#ids").val()==''){
				  		alert("请选择部门");
				  	 }
				    document.forms[0].submit();
				}
				</script>
</body>
</html>