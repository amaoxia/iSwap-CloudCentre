<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#global path = request.getContextPath() >
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8" />
<title>无标题文档</title>
<link href="${request.getContextPath()}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${request.getContextPath()}/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${request.getContextPath()}/css/pop.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='${request.getContextPath()}/dwr/util.js'></script>
<script type='text/javascript' src="${request.getContextPath()}/dwr/interface/modelAction.js"></script>
<script type='text/javascript' src="${request.getContextPath()}/dwr/engine.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.cookie.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.hotkeys.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.jstree.js"></script>
<!--弹出窗口-->
<script type="text/javascript" src="${request.getContextPath()}/js/lhgdialog/lhgcore.min.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/lhgdialog/lhgdialog.min.js"></script>
<!--通用方法-->
<script type="text/javascript" src="${request.getContextPath()}/js/iswap_common.js"></script>
<script type="text/javascript">
$(function () {
$.ajaxSetup({cache:false});//ajax调用不使用缓存 
	$("#checkTree").jstree({
			"json_data" : {
			"data" : ${tree?default("")}
			},
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
			"plugins" : [ "themes", "json_data", "ui", "crrm", "cookies", "dnd", "search", "types", "hotkeys", "contextmenu" ]
		})
		.bind('click.jstree', function(event){
			var eventNodeName = event.target.nodeName;
			if (eventNodeName == 'INS') {
				return;
			} else if (eventNodeName == 'A') {
				var $city = $(event.target);
				var targetid = $city.parent().attr('id');
				$("#content").attr("src","${path}/performancemanage/model/modelmg!checkCoreSet.action?targetId="+targetid+"&modelId="+${id});
			}
		})
});

	function close12(){
		parent.window.aa_close();
	}

	<!--异步的保存信息-->
   function saveInfo(){
   		var subject = $("#subject_id").val();
   		var year = $("#year_id").val(); 
   		var scr = $("#scr_id").val();
   		var text = $("#text_id").val();
   	    var selected = $('#checkTree').jstree("get_selected");
   	    var checkId = selected.attr("id");
   	    modelAction.saveCheckInfo(subject,year,scr,text,checkId,function(data) {
   	       // jAlert(data, "保存考核指标项设置") ;
        	alert(dwr.util.toDescriptiveString(data, 2));
        });
   }
</script>
</head>

<body>
<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
  
  <div class="main">
    <div>
      <div height="60px">
      考核模板名称：<br/>
      ${checkYear}年度${name}
      	</div>
    </div>
    <div class="main_c2" >
    	<br/>考核指标设置：<br />
      <div class="left_tree_com" style="width:250px;">
        <span>
        <p>
          <div id="checkTree" scrolling="Auto" frameborder="0" style="border:0px;height:400px;width:250px;"></div>
        </p>
        </span> </div>
      <div class="right_main">
        <iframe id="content" src="${request.getContextPath()}/performancemanage/model/modelmg!checkCoreSet.action?modelId=${modelId?default(0)}" width="100%"  height="920px;" scrolling="Auto" frameborder="0"  style="border:0px" allowtransparency="true"></iframe>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="clear"></div>
</div>
</body>
</html>
