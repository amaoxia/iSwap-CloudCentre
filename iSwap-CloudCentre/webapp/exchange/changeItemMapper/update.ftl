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
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	</head>
	<body style="width:100%;height:100%;">
		 <form name="saveForm" action="${path}/exchange/mapper/mapper!update.action" method="post" id="saveForm">	
			<input type="hidden" name="mapper.id" value="${mapper.id}"/>
			<@s.token name="token"/>
			<table  style="margin-top:2px;">
				<tr>
				  <td >选择来源指标项：</td>
				  <td colSpan="3"><input type="text" value="${mapper.srcChangeItem.itemName}" style="border: 0 none;padding: 0;" size="66" readonly="readonly"/></td>
		        </tr>
		        <tr>
				  <td width="20%">mapper名称：</td>
				  <td width="30%"><input type="text" id="mapName" name="mapper.mapName" value="${mapper.mapName}"/></td>
				  <td width="20%">mapper编码：</td>
				  <td width="30%"><input type="text" id="mapCode" value="${mapper.mapCode}" readonly="readonly"/></td>
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
		        	 <#list mapper.mapperDetailsList as mapperDetails>
		        	<tr <#if mapperDetails_index%2==0>class="trbg"</#if>>
		        	  <td width="15%"><select name="mapper.mapperDetailsList[${mapperDetails_index}].srcTableDesc.id" val="${mapperDetails.srcTableDesc.id}"></select></td>
			          <td width="10%"></td>
					  <td width="10%"><select name="mapper.mapperDetailsList[${mapperDetails_index}].tarTableDesc.id" val="${mapperDetails.tarTableDesc.id}"></select></td>
					  <td width="10%"></td>
					  <td width="25%"><a href="#" onclick="delMapping(this,null);return false;">删除</></td>
					 </tr>
					 </#list>
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
	var srcbool = false;
	var tarbool = false;
	loadTableInfo(0,'${mapper.srcChangeItem.id}');
	loadTableInfo(1,'${mapper.tarChangeItem.id}');
	
	$(document).ready(function(){
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
	
	function loadTableInfo(flag, itemId){
		if(itemId){
			var url = '';
			$.post('${path}/exchange/tabledesc/tabledesc!getTableDesc4Ajax.action?currTime='+new Date(),{itemId:itemId},function(result, status){
				var options = "<option value=''>请选择...</option>";
				if(result.success){
					var data = result.data;
					$.each(data, function(index, obj){
						options += "<option value='"+obj.id+"'>"+obj.name+"</option>";
					});
					if(flag==1){
						tarSelectStr = options;
						$('#mappingTbody select[name$="tarTableDesc.id"]').html(tarSelectStr);
						tarbool = true;
					}else if(flag==0){
						srcSelectStr = options;
						$('#mappingTbody select[name$="srcTableDesc.id"]').html(srcSelectStr);
						srcbool = true;
					}
					if(srcbool&&tarbool){
						$('#mappingTbody select').each(function(index, domEle){
							var val = $(domEle).attr("val");
							if(val){
								$(domEle).children().each(function(i, elt){
									var value = $(elt).attr("value");
									if(value==val){
										$(elt).attr("selected","selected");
									}
								});
								//$(domEle).find('option[value="'+val+'"').attr("selected","selected");
							}
						});
					}	
				}		
			});
		}
	}
	
	function doSubmit(){
		$('#saveForm').submit();
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