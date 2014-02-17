<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<#global path = request.getContextPath() >
<HTML>
<HEAD>
<TITLE></TITLE>
<META content="texhtml; charset=utf-8" http-equiv=Content-Type>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jstree/jquery.js"></script>	
<!--Í¨ÓÃ·½·¨-->
<script type="text/javascript" src="${path}/js/iswap_common.js"></script>

<#include "/common/commonJsTree.ftl">

 <script type="text/javascript"> 
 $(function () {
 $.ajaxSetup({cache:false});//ajaxµ÷ÓÃ²»Ê¹ÓÃ»º´æ 
	$("#deptModelTree").jstree({ 
			"plugins" : [ "themes", "json_data", "ui", "types", "hotkeys", "checkbox" ],
			"json_data" : { 
				"ajax" : {
					"url" : "${path}/performancemanage/sysdept/department!deptJsTree.action",
					"data" : function (n) { 
						return { 
							"operation" : "get_children", 
							"id" : n.attr ? n.attr("id").replace("node_","") : 1 
						}; 
					}
				}
			},
			"types" : {
				"max_depth" : -2,
				"max_children" : -2,
				"valid_children" : [ "drive" ],
				"types" : {
					"default" : {
						"valid_children" : "none",
						"icon" : {
							"image" : "${path}/js/jstree/file.png"
						}
					},
					"folder" : {
						"valid_children" : [ "default", "folder" ],
						"icon" : {
							"image" : "${path}/js/jstree/folder.png"
						}
					},
					"drive" : {
						"valid_children" : [ "default", "folder" ],
						"icon" : {
							"image" : "${path}/js/jstree/root.png"
						},
						"start_drag" : false,
						"move_node" : false,
						"delete_node" : false,
						"remove" : false
					}
				}
			}
		})
		.bind('loaded.jstree', function(event){
			var cc=getdeptid();
			jQuery.each(cc,function(i){
				var result = cc[i];
						$('#deptModelTree').jstree('check_node',"#"+result);
			});
		})
});

  //È¡µÃÑ¡ÖÐµÄ²Ëµ¥id 
      function getMenuIds(){
      var ids="";   
      var nodes=$("#deptModelTree").jstree("get_checked");  //Ê¹ÓÃget_checked·½·¨
		 $.each(nodes, function(i, n) {   
		           ids+=$(n).attr("id")+",";
		 });
		 $.ajax({
			 type: "POST",
			 url: "${path}/performancemanage/deptModCon/deptModCon!updatedeptMod.action",
			 data:  {
			 "type":${type},
			 "modelId":${modelId},
			 "deptid":ids,
			 "async":false
			 },
			 success: function(msg){ 
				dg.cancel();
				  dg.curWin.location.reload(); 
			  } 
			}); 
		 
      } 

	function getdeptid(){
		var did=$("#deptid").val().split(",");
		return did;
	}
</script>
</HEAD>
<BODY>
<div class="main">
<DIV id="deptModelTree" style="FONT-SIZE: 14px; HEIGHT: 307px; WIDTH: 160px;OVERFLOW-y:auto;"></DIV>
<input type="hidden" value="${type}" id="type"/>
<input type="hidden" value="${modelId}" id="modelId"/>
<input type="hidden" value="${deptid}" id="deptid"/>
<p class="btn_s_m2" style="margin-left:35%;">
<input name="" type="button" value="Ìá½»"  class=" btn2_s"  onclick="getMenuIds();"/>&nbsp;&nbsp;&nbsp;<a href="#" class="link_btn"  onclick="dg.cancel();" >¹Ø±Õ´°¿Ú</a>
</p>
</div>
</BODY>
</HTML>
