
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jstree/jquery.js"></script>	

<#include "/common/commonJsTree.ftl">
     <script type="text/javascript" class="source"> 
     var $jstree = null;
     //创建树方法
	$(function () {
     	$.ajaxSetup({cache:false});//ajax调用不使用缓存 
     	$jstree = $("#checkTargetTree")
     .bind('loaded.jstree', function(event){
			var selected = $('#checkTargetTree').jstree("get_selected");
			//如果没选择的节点，默认选择根
			if(selected.size()==0){
				var $root = $('#checkTargetTree li[rel="root"]');//根节点
			    var rt=	$('#checkTargetTree').jstree("select_node",$root);
			    
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
				//$("#subjectTree").jstree('toggle_node', $city.parent().find('ins').get(0));
				
				$("#content").attr("src","${path}/performancemanage/checktarget/checkTarget!view.action?id="+id);
				
				
			}
		})
		.bind("create.jstree", function (e, data) {  
			$.post(
				"${path}/performancemanage/checktarget/checkTarget!add.action", 
				{ 
					"parentId" : data.rslt.parent.attr("id").replace("node_",""), 
					"name" : encodeURIComponent(data.rslt.name)
				}, 
				function (r) {
					if(r!=null) {
						$(data.rslt.obj).attr("id",  r.id);
					}
					else {
						$.jstree.rollback(data.rlbk);
					}
				}
			);
		})
		.bind("rename.jstree", function (e, data) {
			$.post(
				"${path}/performancemanage/checktarget/checkTarget!rename.action", 
				{ 
					"id" : data.rslt.obj.attr("id"),
					"rename" : encodeURIComponent(data.rslt.new_name)
				}, 
				function (r) {
					if(!r) {
						$.jstree.rollback(data.rlbk);
					}
				}
			);
		})
		  
		.bind("remove.jstree", function (e, data) {
			$.post(
				"${path}/performancemanage/checktarget/checkTarget!delete.action", 
				{ 
					"id" : data.rslt.obj.attr("id")
				}, 
				function (r) {
					if(!r) {
						$.jstree.rollback(data.rlbk);
					}
				}
			);
		})
		.bind("move_node.jstree", function (e, data) {
			data.rslt.o.each(function (i) {
				$.ajax({
					async : false,
					type: 'POST',
					url: "${path}/performancemanage/checktarget/checkTarget!copyOrCut.action",
					data : { 
						"ct.parent.id" : data.rslt.np.attr("id"), 
						"ct.id" : $(this).attr("id"),
						"copy" : data.rslt.cy ? 1 : 0 //判断是剪切还是复制
					},
					success : function (r) {
						if(r==null) {
							$.jstree.rollback(data.rlbk);
						}
						else {
							$(data.rslt.oc).attr("id",r.id);
							if(data.rslt.cy && $(data.rslt.oc).children("UL").length) {
								data.inst.refresh(data.inst._get_parent(data.rslt.oc));
							}
						}
						$("#analyze").click();
					}
				});
			});
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
					"url" : "${path}/performancemanage/checktarget/checkTarget!getcheckTargetTree.action",
					"data" : function (n) { 
						return { id : n.attr ? n.attr("id") : 0 }; 
					}
				}
			}, 
			"plugins" : [ "themes", "json_data", "ui", "crrm", "cookies", "dnd", "search", "types", "hotkeys", "contextmenu" ]
		});
		
		
		$("#new").click(function(){
			var selected = $('#checkTargetTree').jstree("get_selected");
			if(selected.attr('id')==undefined){
			alert('请选择节点');
			}else{
			var node= $('#checkTargetTree').jstree("create");   
			}
			
		});
		
		$("#del").click(function () { 
			var $root = $('#checkTargetTree li[rel="root"]');//根节点
			var selected = $('#checkTargetTree').jstree("get_selected");
			if(selected.attr("id")==$root.attr("id")){
				alert("不能删除根元素！");
				return;
			}
			var id = selected.attr("id");
			if(selected.size()==0){
				alert("请选择要删除的节点！");
				return;
			}
			if(confirm("确定要删除吗？")){
			$("#checkTargetTree").jstree("remove"); 
			}			
		});
		
     })
 
 $(function () { 
	$("#menu a").click(function () {
		switch(this.id) {
			default:
				$("#checkTargetTree").jstree(this.id);
				break;
		}
	});
});
 
</script>
</head>

<body>
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />考核指标管理</div>
    </div>
    <div class="main_c2" >
           <div class="left_tree_com">
	        <h1>
	          <div class="left_topimg right"><a href="javascript:void(0)" id="new"><img src="${path}/images/tools/report_add.png" title="新建"/></a><a href="javascript:void(0)" id="del"><img src="${path}/images/tools/cross.png" title="删除"/></a></div>
	          <div class="left_topimg " id="menu"><a href="javascript:void(0)" id="cut"><img src="${path}/images/tools/cut_red.png" title="剪切"/></a><a href="javascript:void(0)" id="copy"><img src="${path}/images/tools/page_white_copy.png" title="复制"/></a><a href="javascript:void(0)" id="paste"><img src="${path}/images/tools/page_white_paste.png" title="粘贴"/></a></div>
	          <div class="left_topimg left"><a href="javascript:void(0)"><img src="${path}/images/tools/lock_open.png" title="激活"/></a><a href="javascript:void(0)"><img src="${path}/images/tools/lock.png" title="冻结" /></a></div>
	        </h1>
	        <span>
	        <p>
	          <div id="checkTargetTree" width="250" scrolling="Auto" frameborder="0" style="border:0px;height:427px;"></div>
	        </p>
	        </span> 
	       </div>
      <div class="right_main">
          <iframe id="content" src="${path}/performancemanage/checktarget/checkTarget!view.action?id=0" width="100%"  height="520px;" scrolling="Auto" frameborder="0"  style="border:0px" allowtransparency="true"></iframe>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="clear"></div>
</body>
</html>
