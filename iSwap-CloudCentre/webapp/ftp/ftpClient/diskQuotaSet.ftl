<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/diskQuotaSet.png"  align="absmiddle" />磁盘配额设置</div>
    </div>
    <div class="main_c2" >
           <div class="left_tree_com">
	        <span>
	        <p>
	          <div id="deptTree" width="250" scrolling="Auto" frameborder="0" style="border:0px;height:427px;"></div>
	        </p>
	        </span> 
	       </div>
      <div class="right_main">
          <iframe id="content" width="100%"  height="520px;" scrolling="Auto" frameborder="0"  style="border:0px" allowtransparency="true"></iframe>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="clear"></div>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<#include "/common/commonJsTree.ftl">
<!--通用方法-->
<script type="text/javascript" src="${path}/js/iswap_common.js"></script>
<script type="text/javascript" class="source"> 
	var selectedId;
     var $jstree = null;
     //创建树方法
	$(function () {
     	$.ajaxSetup({cache:false});//ajax调用不使用缓存 
     	$jstree = $("#deptTree")
     .bind('loaded.jstree', function(event){
     	if(selectedId+"t"!="t"){
     		$("#content").attr("src","ftpClient!editDiskQuota.action?id="+selectedId);
     	}
	  })
		.bind('click.jstree', function(event){
			var eventNodeName = event.target.nodeName;
			if (eventNodeName == 'INS') {
				return;
			} else if (eventNodeName == 'A') {
				var $city = $(event.target);
				var id = $city.parent().attr('id');
				if($city.parent().attr('rel')==''){
			 		$("#content").attr("src","ftpClient!editDiskQuota.action?id="+id);
			    }
			}
		})
		.jstree({
			"core" : { "animation" : 250 },
			"ui" : {
				 "select_limit" : 1,
				 "initially_select":${selectedId}
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
					}					
				}
			},
			"json_data" : {
				"ajax" : {
					"url" : "ftpClient!deptTree.action",
					"data" : function (n) { 
						return { id : n.attr ? n.attr("id") : 0 }; 
					},
					success: function(msg){
					   selectedId= msg.selectedId;
					   return eval('(' + msg.treeStr + ')');
					}
				}
			}, 
			"plugins" : [ "themes", "json_data", "ui",  "types", "hotkeys" ]
		});
     })
 
      //刷新树
     function refreshTree(){
     	$('#deptTree').jstree("refresh",-1)
     }
</script>
</body>
</html>
