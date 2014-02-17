<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
		<link href="${path}/js/liger/lib/ligerUI/skins/Aqua/css/ligerui-form.css" rel="stylesheet" type="text/css" />  
		<link href="${path}/js/liger/lib/ligerUI/skins/Aqua/css/ligerui-tree.css" rel="stylesheet" type="text/css" />        
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
		<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
		<script src="${path}/js/liger/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
   		<script type="text/javascript" src="${path}/js/liger/lib/ligerUI/js/plugins/ligerTree.js"></script>
    	<script type="text/javascript" src="${path}/js/liger/lib/ligerUI/js/plugins/ligerComboBox.js"></script> 
	</head>
	<body style="width:100%;height:100%;">
		 <form name="saveForm" action="${path}/exchange/changeItemMapper/changeItemMapper!add.action" method="post" id="saveForm">	
			<@s.token name="token"/>
			<input type="hidden" name="mapper.tarChangeItem.id" value="${itemId}"/>
			<input type="hidden" id="srcChangeItemId" name="mapper.srcChangeItem.id"/>
			<input type="hidden" name="mapper.status" value="0"/>
			<table  style="margin-top:2px;">
				<tr>
				  <td >选择来源指标项：</td>
				  <td colSpan="3"><input type="text" id="txt1" style="border: 0 none;padding: 0;"/></td>
		        </tr>
		        <tr>
				  <td width="20%">mapper名称：</td>
				  <td width="30%"><input type="text" id="mapName" name="mapper.mapName"/></td>
				  <td width="20%">mapper编码：</td>
				  <td width="30%"><input type="text" id="mapCode" name="mapper.mapCode"/></td>
		        </tr>
	        </table>				
			<table class="tabs1"  style="margin-top:2px;">
				<thead>
					<tr>
			          <th width="15%">来源字段名称</th>
			          <th width="10%">来源字段类型</th>
					  <th width="10%">目标字段名称</th>
					  <th width="10%">目标字段类型</th>
					  <th width="25%">操作</th>
			        </tr>
		        </thead>
		        <tbody id="mappingTbody">
		        </tbody>
		        <tfoot>
			        <tr>
			        	<td colSpan="5"><input id="newBtn" type="button" value="新增字段映射"/></td>
			        </tr>
		        </tfoot>
	        </table>
		</form>
	</body>
</html>
<script type="text/javascript">
	var srcSelectStr = '';
	var tarSelectStr = '';
	$.post('${path}/exchange/tabledesc/tabledesc!getTableDesc4Ajax.action?currTime='+new Date(),{itemId:'${itemId}'},function(result, status){
		var options = "<option value=''>请选择...</option>";
		if(result.success){
			var data = result.data;
			$.each(data, function(index, obj){
				options += "<option value='"+obj.id+"'>"+obj.name+"</option>";
			});
		}
		tarSelectStr = options ;
	});
	$(document).ready(function(){
	  $("#txt1").ligerComboBox({
            width: 400,
            selectBoxWidth: 400,
            selectBoxHeight: 300,
            textField:'name', valueField: 'id',treeLeafOnly:true,
            onSelected:onSelectedItem,
            tree: { url: "${path}/exchange/item/item!getItemtree4Ajax.action?deptId=${deptId}&currTime="+new Date(), checkbox: false,
                    textFieldName:"name",
                    idFieldName:"id",
                    parentIDFieldName:"pid"
                  }
        });
        
        $('#newBtn').click(function(){
        	if(!this.num )this.num = 0;
        	var newMapping = '<tr>'+
					          '<td width="15%"><select name="mapper.mapperDetailsList['+this.num+'].srcTableDesc.id">' + srcSelectStr+ '</select>'+'</td>'+
					          '<td width="10%"></td>'+
							  '<td width="10%"><select name="mapper.mapperDetailsList['+this.num+'].tarTableDesc.id">' + tarSelectStr+ '</select>'+'</td>'+
							  '<td width="10%"></td>'+
							  '<td width="25%"><a href="#" onclick="delMapping(this,null);return false;">删除</></td>'+
					        '</tr>';
			this.num++;
        	$('#mappingTbody').append(newMapping);
        });
	});
	
	function delMapping(obj, id){
		if(id){}else{
			$(obj).parent().parent().remove();
		}
	}
	
	function loadTree(){
		zTree=$("#tree").zTree(setting,zTreeNodes);
	}
	
	function onSelectedItem(newvalue){
		$('#srcChangeItemId').val(newvalue);
		loadSrcTableInfo(newvalue);
	}
	
	function loadSrcTableInfo(itemId){
		if(itemId){
			var url = '';
			$.post('${path}/exchange/tabledesc/tabledesc!getTableDesc4Ajax.action?currTime='+new Date(),{itemId:itemId},function(result, status){
				var options = "<option value=''>请选择...</option>";
				if(result.success){
					var data = result.data;
					$.each(data, function(index, obj){
						options += "<option value='"+obj.id+"'>"+obj.name+"</option>";
					});
				}
				srcSelectStr = options ;
			});
		}
	}
	
	function doSubmit(){
		 
	     if($('#txt1').val()){
			$('#saveForm').submit();
		 }else{
		 	alert("请选择指标项!");
		 	return;
		}
	}
	
	//事件
	function reset(){
		var ck=$(":input[id=applyType][value=0]");
		 $(ck).attr("checked",true);
		 zTree.checkAllNodes(false);
		//清空File value
		var obj = document.getElementById('upload') ;   
		obj.select();   
		document.selection.clear(); 
		obj.disabled=true;
		//
		editor.html('');
		//
		/* $(':input',"#saveForm")
	      .not(':button, :submit,:radio,:reset, :hidden')
	      .attr('value','')
	      .removeAttr('selected');*/
	}
	
	var DG = frameElement.lhgDG;
	DG.addBtn('close', '关闭窗口', singleCloseWin);
	// 关闭窗口 不做任何操作
	function singleCloseWin() {
		DG.cancel();
	}
	//DG.addBtn('submit', '提交', isSub);
	function isSub() {
		$('#auditStatus').val("1");
  		doSubmit();
	}
	DG.addBtn('save', '保存', saveWin);
	function saveWin() {
		doSubmit();
	}
</script>