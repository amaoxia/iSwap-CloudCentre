<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#global path = request.getContextPath() >
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>需求目录配置</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
</script>
</head>
	 <body>	
	 	 <@s.token name="token"/>
	     <input type="hidden" id="itemIdsValue" value="${itemIds?default('')}"/>
	  	 <ul id="tree" class="tree" style="height:440px;width:430px;overflow:auto;">
	  	 </ul>
	</body>
</html>
<script type="text/javascript">
	$(document).ready(function(){
		loadTree();//载入树
    });

	var zTree;//树
	var setting;//参数设置
	var zTreeNodes = [] ;//数据
    setting={
	    callback : {
	      beforeClick: zTreeBeforeClick,
	      asyncSuccess:zTreeOnAsyncSuccess 
	    },
	    //fontCss:setFontCss,
	    async : true,//异步加载 
	    asyncUrl: "${path}/exchange/sendItemDept/sendItemDept!deptItemTree.action?deptId=${deptId}",//数据文件 
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
    
    function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		 var itemIdsValue=$("#itemIdsValue").val();
   		 var  itemIds=itemIdsValue.split(",");
   		 
   		 for(i=0;i<itemIds.length;i++){
   		 	id=itemIds[i];
   		 	if(id!=""&&id.length>0){
   		 		var node = zTree.getNodeByParam("id", id);
   		 		if(node){
   		 			node.checked=true;
   		 			zTree.updateNode(node, true);
   		 		}
   		 	}
   		  }
		}
		
    //点击节点之前的操作
    function zTreeBeforeClick(treeId, treeNode){
  		
	 }
    var DG = frameElement.lhgDG;
	DG.addBtn('close', '关闭窗口', singleCloseWin);
	// 关闭窗口 不做任何操作
	function singleCloseWin() {
		DG.cancel();
	}
	DG.addBtn('save', '保存', saveWin);
	
	function saveWin() {
		// 实现逻辑
		 var ids=zTree.getCheckedNodes(true);
	     if(ids.length==0){
	     	alert("请选择指标");
	        return;
	     }
	     var itemIds="";
	     for(i=0;i<ids.length;i++){
	     	if(ids[i].level==2){
	     	 itemIds+=ids[i].id+",";	
	     	}
	      }
	      if(itemIds==''){
	        alert("请选择指标");
	      	return;
	      }
	     $.post("${path}/exchange/sendItemDept/sendItemDept!add.action?deptId=${deptId}"+"&itemids="+itemIds+"&struts.token.name="+$('input[name="struts.token.name"]').val()+"&token="+$('input[name="token"]').val(), null, function(data, textStatus, jqXHR){
		 	if(data.success){
		 		alert("保存成功");
		 		var parentWin = DG.curWin;
		 		if(parentWin){
		 			parentWin.location.replace(parentWin.location.href);
		 		}
		 		singleCloseWin();
		 	}else{
		 		alert("保存失败，请联系系统管理员");
		 	}
		 },"json");
		 /**
		 $.ajax({
	    	url: "${path}/exchange/sendItemDept/sendItemDept!add.action?deptId=${deptId}"+"&itemids="+itemIds+"&struts.token.name="+$('input[name="struts.token.name"]').val()+"&token="+$('input[name="token"]').val(),
		    data: {},
		    dataType: "json",
		    success: function(data,textStatus) {
		    	alert("保存成功");
			},
		    error:function(r,d){
		      alert(d);
			}
		});
		**/

		 
	}
</script>
