<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />

</head>

<body>
<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
  <div class="main">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td align="center" valign="middle" class=""  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
			<tr>
			  <td  height="100%" valign="top" ><div class="">
				  <ul class="item2_c">
					<li>
					  <span id="deptTree">
					  </span> </li>
				  </ul>
				</div></td>
			</tr>
		  </table></td>
	  </tr>
	</table>
</div>
</div>
<div class="clear"></div>
</div>
</form>


<script src="${path}/js/jquery-1.5.1.js"></script>

 <!--验证js-->
<!--树-->
<#include "/common/commonJsTree.ftl">
<!--通用方法-->

 <script type="text/javascript" class="source"> 
 var DG = frameElement.lhgDG; 
DG.addBtn( 'save', '保存', saveWin); 
function saveWin() {
	//实现逻辑
	isSub();
}
     var $jstree = null;
     //创建树方法
	$(function () {
     	$.ajaxSetup({cache:false});//ajax调用不使用缓存 
     	$jstree = $("#deptTree").bind('click.jstree', function(event){
			var eventNodeName = event.target.nodeName;
			if (eventNodeName == 'INS') {
				return;
			} else if (eventNodeName == 'A') {
			     	var $city = $(event.target);
			      	var id = $city.parent().attr('id');//当前节点Id
    		        }
		})
		.bind('loaded.jstree', function(event){
			var deptIds = DG.iDoc("hz0").getElementById('deptIds').value.split(",");
			jQuery.each(deptIds,function(i){
				var result = new Number(deptIds[i]);
				if(!isNaN(result)){
					var state = $('#deptTree').jstree('is_checked',"#"+result);
					var is_leaf = $('#deptTree').jstree('is_leaf',"#"+result);
					if(is_leaf && !state){
						$('#deptTree').jstree('check_node',"#"+result);
					}
				}
			});
		})
		.jstree({
			"core" : { "animation" : 250 },
			"ui" : {
				"select_limit" : 1
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
						"icon" : {
							"image" : "${path}/js/jstree/root.png"
						},
						"start_drag" : false,
						"move_node" : false,
						"delete_node" : false,
						"remove" : false
					}
				}
			},
			"json_data" : {
				"ajax" : {
					"url" : "${path}/cloudcenter/cloudNodeInfo/cloudNodeInfo!deptTreeStr.action",
					"data" : function (n) { 
						return { id : n.attr ? n.attr("id") : 0 }; 
					}
				}
			}, 
			"plugins" : [ "themes", "json_data", "checkbox", "ui", "crrm", "types", "hotkeys" ]
		});
});
var DG = frameElement.lhgDG;
function isSub(){
	var deptNames="";
	var deptIds = "";
	var $nodes = $('#deptTree').jstree("_get_children",-1).find("li");
	$nodes.each(function(){
		var id = $(this).attr("id");
		var name = $.trim($(this).find("a").first().text());
		if($('#deptTree').jstree("is_checked","#"+id) && $("#"+id).attr('rel') !='root' ){
			deptIds = deptIds+","+id;
			deptNames=deptNames+","+name
		}
	});
	deptNames = deptNames.substring(1);
	DG.iDoc('hz0').getElementById('deptNames').value=deptNames;
	DG.iDoc('hz0').getElementById('deptIds').value=deptIds;
	DG.cancel();
}
</script>
</body>
</html>
