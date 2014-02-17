<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/js/title/tip-yellow/tip-yellow.css" type="text/css" />
<link rel="stylesheet" href="${path}/js/title/tip-violet/tip-violet.css" type="text/css" />
<!--通用方法-->
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jstree/jquery.js"></script>	
<#include "/common/commonJsTree.ftl">
<!--通用方法-->

<script type="text/javascript" class="source"> 
     var $jstree = null;
     //创建树方法
	$(function () {
     	$.ajaxSetup({cache:false});//ajax调用不使用缓存 
     	$jstree = $("#checkDeptTree")
		.bind('click.jstree', function(event){
			var eventNodeName = event.target.nodeName;
			if (eventNodeName == 'INS') {
				return;
			} else if (eventNodeName == 'A') {
				var $city = $(event.target);
				var id = $city.parent().attr('id');
			    if($city.parent().attr('rel')=='root'){
			 		id=-1;
			    }
			}
		})
		.bind('click.jstree', function(event){
			var eventNodeName = event.target.nodeName;
			if (eventNodeName == 'INS') {
				return;
			} else if (eventNodeName == 'A') {
				var $city = $(event.target);
				var id = $city.parent().attr('id');
					// 点击A展开子节点
				$("#subjectTree").jstree('toggle_node', $city.parent().find('ins').get(0));
				$("#content").attr("src","${path}/performancemanage/check/check!scoreView.action?checkYear=${checkYear}&cycleType=${cycleType}&modelId=${modelId}&deptId="+id);
			}
		})
		.jstree({
			"core" : { "animation" : 250 },
			"ui" : {
				"select_limit" : 2,
				"initially_select" : [ "${deptId?default("")}" ]
			},
			"types" : {
				"max_depth" : -2,
				"max_children" : -2,
				"valid_children" : [ "root" ],
				"types" : {
					"default" : {
						"valid_children" : "none",
						"icon" : {
							"image" : "${path}/js/jstree/file.png"
						}
					},
					"folder" : {//文件夹
						"valid_children" : [ "default", "folder" ],
						"icon" : {
							"image" : "${path}/js/jstree/folder.png"
						}
					},
					"root" : {//根节点
						"valid_children" : [ "default", "root" ],
						"icon" : {
							"image" : "${path}/js/jstree/root.png"
						}
					}
				}
			},
			"json_data" : {
				"ajax" : {
					"url" : "${path}/performancemanage/deptModCon/deptModCon!checkDeptTree.action?modelId=${modelId}&cycleType=${cycleType}",
					"data" : function (n) { 
						return { id : n.attr ? n.attr("id") : 0 }; 
					}
				}
			},
			"plugins" : [ "themes", "json_data", "ui", "crrm", "types", "hotkeys" ]
		});
		$("#new").click(function () { 
			$("#checkDeptTree").jstree("create"); 
		});
		$("#del").click(function () { 
			$("#checkDeptTree").jstree("remove"); 
		});
		
		
     })
     function cl(){
   		var dg = frameElement.lhgDG;
		 dg.cancel();
		  dg.curWin.location.reload(); 
     }
</script>
</head>

<body>
<div class="pop_div6 pm6_c">
  <div class="left_tree6">
    <h1>委办局选择</h1>
    <span>
    <p>
    <div id="checkDeptTree" width="240" scrolling="Auto" frameborder="0" style="height:470px;"></div>
    </p>
    </span> 
  </div>
     <div class="div6_mainc">
    	<div class="div6_tabs5">
          <iframe id="content" src="${path}/performancemanage/check/check!scoreView.action?checkYear=${checkYear}&cycleType=${cycleType}&modelId=${modelId}" width="100%"  height="700px;" scrolling="Auto" frameborder="0"  style="border:0px" allowtransparency="true"></iframe>
      </div>
      </div></div>
</body>
</html>
