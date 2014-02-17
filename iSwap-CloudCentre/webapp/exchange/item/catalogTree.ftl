<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表结构信息</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div>
     <div id="permissionTree" width="100%" frameborder="0" style="border:0px;height:300px;OVERFLOW-y:auto;" ></div>
				<input type="hidden" id="ids" value=""/>
				<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/iswap_common.js"></script>	
<#include "/common/commonJsTree.ftl">
<script type="text/javascript">    

function getCategoryIds(){
	var ids = "";
	var $nodes = $('#permissionTree').jstree("_get_children",-1).find("li");
	var count=0;
	$nodes.each(function(){
		var id = $(this).attr("id");
		if($('#permissionTree').jstree("is_checked","#"+id)){
			ids = ids+","+id;
			count++;
		}
	});
	if(count==0){
		alert("请选择类目!");
		return "";
	}
	return ids;
}
</script>

	<!--  菜单树-->
  <script type="text/javascript" class="source"> 
     var $jstree = null;
     //创建树方法
	$(function () {
     	$.ajaxSetup({cache:false});//ajax调用不使用缓存 
     	$jstree = $("#permissionTree").bind('click.jstree', function(event){
			var eventNodeName = event.target.nodeName;
			if (eventNodeName == 'INS') {
				return;
			} else if (eventNodeName == 'A') {
				var $city = $(event.target);
				var id = $city.parent().attr('id');
					// 点击A展开子节点
				$("#subjectTree").jstree('toggle_node', $city.parent().find('ins').get(0));
			}
		})
		.bind('loaded.jstree', function(event){
			var selected = $('#permissionTree').jstree("get_selected");
				var ids= $("#ids").val();
			   ids=	ids.split(",");
			   $(ids).each(function(i){
			   	var result = new Number(ids[i]);
				if(!isNaN(result)){
				    var state = $('#permissionTree').jstree('is_checked',"#"+result);
					var is_leaf = $('#permissionTree').jstree('is_leaf',"#"+result);
					if(is_leaf && !state){
						$('#permissionTree').jstree('check_node',"#"+result);
					}
				}
			   });
			//如果没选择的节点，默认选择根
			if(selected.size()==0){
				var $root = $('#permissionTree li[rel="root"]');//根节点
			    var rt=	$('#permissionTree').jstree("select_node",$root);
			}
		})
		.jstree({
			"core" : { "animation" : 250 },
			"ui" : {
				"select_limit" : 1,
				 "initially_select" : [ "33333365" ]  
			},
			"types" : {
				"max_depth" : -2,
				"max_children" : -2,
				"valid_children" : [ "root" ],
				"types" : {
					"default" : {
						"icon" : {
							"image" : "${path}/js/jstree/file.png"
						}
					},
					"folder" : {//文件夹
						"icon" : {
							"image" : "${path}/js/jstree/folder.png"
						}
					},
					"root" : {//根节点
						"icon" : {
							"image" : "${path}/js/jstree/root.png"
						}
					},
					"drive" : {
						// can have files and folders inside, but NOT other `drive` nodes
						"icon" : {
							"image" : "${path}/js/jstree/root.png"
						},
						// those options prevent the functions with the same name to be used on the `drive` type nodes
						// internally the `before` event is used
						"start_drag" : false,
						"move_node" : false,
						"delete_node" : false,
						"remove" : false
					}
				}
			},
			"json_data" : {
				"ajax" : {
					"url" : "${path}/exchange/item/item!getSuperSysTree.action?catalogId=${catalogId}",
					"data" : function (n) { 
						return { id : n.attr ? n.attr("id") : 0 }; 
					}
				}
			}, 
			"plugins" : [ "themes", "json_data", "ui","checkbox", "types", "hotkeys"]
		});
});
</script>
</body>
</html>