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
			var selected = $('#deptTree').jstree("get_selected");
			//如果没选择的节点，默认选择根
			if(selected.size()==0){
				var $root = $('#deptTree li[rel="root"]');//根节点
			    var rt=	$('#deptTree').jstree("select_node",$root);
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
							"image" : path+"/js/jstree/file.png"
						}
					},
					"folder" : {//文件夹
						"icon" : {
							"image" : path+"/js/jstree/folder.png"
						}
					},
					"root" : {//根节点
						"icon" : {
							"image" : path+"/js/jstree/root.png"
						}
					},
					"drive" : {
						// can have files and folders inside, but NOT other `drive` nodes
						"icon" : {
							"image" : path+"/js/jstree/root.png"
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
					"url" : path+"/sysmanager/dept/dept!getDeptTree.action",
					"data" : function (n) { 
						return { id : n.attr ? n.attr("id") : 0 }; 
					}
				}
			}, 
			"plugins" : [ "themes", "json_data", "ui", "types", "hotkeys"]
		});
});