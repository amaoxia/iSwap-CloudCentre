<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8" />
<title>无标题文档</title>
<link href="${request.getContextPath()}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${request.getContextPath()}/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${request.getContextPath()}/css/pop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.cookie.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.hotkeys.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/jstree/jquery.jstree.js"></script>
<!--通用方法-->
<script type="text/javascript" src="${request.getContextPath()}/js/iswap_common.js"></script>
<script type="text/javascript">
$(function () {
	$("#demo").jstree({ 
			"json_data" : {
			"data" : [
			{
				"attr":{
					"clAss":"jstree-unchecked",
					"id":"4","rel":"root"
				},
				"children":[{
					"attr":{
						"clAss":"jstree-unchecked",
						"id":"5","rel":"folder"
					},
					"children":[{
						"attr":{
							"clAss":"jstree-unchecked","id":"6","rel":""
						},
						"children":[],
						"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},
							"title":"领导重视"
						},
						"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked",
							"id":"7","rel":""
						},"children":[],"data":{
							"attr":{
								"href":"",
								"leaf":true,
								"title":""
							},"title":"数据归集周期"
						},
						"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked",
							"id":"8","rel":""
						},"children":[],"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"数据报送格式"
						},
						"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked","id":"9","rel":""
						},"children":[],
						"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"数据报送质量"
						},"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked","id":"9","rel":""
						},"children":[],
						"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"工作会议及业务培训"
						},"state":""
					}],
					"data":{
						"attr":{
							"href":"","leaf":false,"title":""
						},"title":"基本项"
					},"state":"open"
				},{
					"attr":{
						"clAss":"jstree-unchecked","id":"11","rel":"folder"
					},"children":[{
						"attr":{
							"clAss":"jstree-unchecked","id":"12","rel":""
						},"children":[],
						"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"数据特别贡献"
						},
						"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked","id":"13","rel":""
						},"children":[],
						"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"数据归集工作量"
						},"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked","id":"58","rel":""
						},"children":[],
						"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"完善数据标识"
						},"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked","id":"14","rel":""
						},"children":[],"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"数据应用成效"
						},"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked","id":"10","rel":""
						},"children":[],"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"治税信息报送"
						},"state":""
					}],
					"data":{
						"attr":{
							"href":"","leaf":false,"title":""
						},"title":"加分项"
					},"state":"open"
				},{
					"attr":{
						"clAss":"jstree-unchecked","id":"15","rel":"folder"
					},"children":[{
						"attr":{
							"clAss":"jstree-unchecked","id":"17","rel":""
						},"children":[],
						"data":{
							"attr":{"href":"","leaf":true,"title":""},
							"title":"信息泄漏"
						},"state":""
					}],"data":{
						"attr":{"href":"","leaf":false,"title":""},"title":"减分项"
					},"state":"open"
				}],
				"data":{
					"attr":{"href":"","leaf":false,"title":""},
					"title":"考核指标"
				},"state":"open"
			}]
		},
		//普通树
		//"plugins" : [ "themes", "json_data", "ui", "crrm", "cookies", "dnd", "search", "types", "hotkeys", "contextmenu" ],
		//复选框树
		"plugins" : [ "themes", "json_data", "ui", "crrm", "cookies", "dnd", "search", "types", "hotkeys", "checkbox" ],
		
			// Plugin configuration
			// Configuring the search plugin
			/*"search" : {
				// As this has been a common question - async search
				// Same as above - the `ajax` config option is actually jQuery's object (only `data` can be a function)
				"ajax" : {
					"url" : "./server.php",
					// You get the search string as a parameter
					"data" : function (str) {
						return { 
							"operation" : "search", 
							"search_str" : str 
						}; 
					}
				}
			},*/
			// Using types - most of the time this is an overkill
			// Still meny people use them - here is how
			"types" : {
				// I set both options to -2, as I do not need depth and children count checking
				// Those two checks may slow jstree a lot, so use only when needed
				"max_depth" : -2,
				"max_children" : -2,
				// I want only `drive` nodes to be root nodes 
				// This will prevent moving or creating any other type as a root node
				"valid_children" : [ "drive" ],
				"types" : {
					// The default type
					"default" : {
						// I want this type to have no children (so only leaf nodes)
						// In my case - those are files
						"valid_children" : "none",
						// If we specify an icon for the default type it WILL OVERRIDE the theme icons
						"icon" : {
							"image" : "js/jstree/file.png"
						}
					},
					// The `folder` type
					"folder" : {
						// can have files and other folders inside of it, but NOT `drive` nodes
						"valid_children" : [ "default", "folder" ],
						"icon" : {
							"image" : "js/jstree/folder.png"
						}
					},
					// The `drive` nodes 
					"drive" : {
						// can have files and folders inside, but NOT other `drive` nodes
						"valid_children" : [ "default", "folder" ],
						"icon" : {
							"image" : "js/jstree/root.png"
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
			// For UI & core - the nodes to initially select and open will be overwritten by the cookie plugin

			// the UI plugin - it handles selecting/deselecting/hovering nodes
			"ui" : {
				// this makes the node with ID node_4 selected onload
				"initially_select" : [ "node_4" ]
			},
			// the core plugin - not many options here
			"core" : { 
				// just open those two nodes up
				// as this is an AJAX enabled tree, both will be downloaded from the server
				"initially_open" : [ "node_2" , "node_3" ] 
			}
		})
		
	$("#new").click(function () { 
		$("#demo").jstree("create"); 
	});
	$("#del").click(function () { 
		$("#demo").jstree("remove"); 
	});
$("#cut").click(function () { 
		$("#demo").jstree("cut"); 
	});
	$("#copy").click(function () { 
		$("#demo").jstree("copy"); 
	});
	$("#paste").click(function () { 
		$("#demo").jstree("paste"); 
	});
});

function close12(){
	parent.window.aa_close();
}
</script>
</head>

<body>
<div class="frameset_w" style="height:100%;background-color:#FFFFFF;">
  <div class="main">
    <div>
      <div height="60px">
      考核模板名称：<br/>
    	<select>
    		<option>2011年度</option>	
    	</select>
    	<select>
    		<option>综合治税绩效考核办法</option>	
    	</select>	
      	</div>
    </div>
    <div class="main_c2" >
    	<br/>考核指标设置：<br />
      <div class="left_tree_com" style="width:250px;">
        <span>
        <p>
          <div id="demo" scrolling="Auto" frameborder="0" style="border:0px;height:400px;width:250px;"></div>
        </p>
        </span> </div>
      <div class="right_main">
        <div class="loayt_01 rlist100">
          <div class="loayt_top"> <span class="loayt_tilte"><b>考核指标基本信息 </b></span><span class="loayt_right"><img src="images/kuang_right.png" width="10" height="31" /></span> </div>
          <div class="loayt_mian">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center" valign="middle" class="loayt_cm"  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
                    <tr>
                      <td  height="100%" valign="top" ><div class="item2 item2_scroll">
                          <ul class="item2_c">
                            <li>
                              <p>考核项标准分数：</p>
                              <span>
                              <input type="text" size="30" />
                              </span> </li>
                            <li class="item2_bg">
                              <p>考核项记分方法：</p>
                              <span>
                              <textarea name="textarea" id="textarea" cols="55" rows="9"></textarea>
                              </span> </li>
                              <li><a href="javascript:void(0)" class="link_btn">重填</a></li>
                          </ul>
                        </div></td>
                    </tr>
                  </table></td>
              </tr>
            </table>
          </div>
        </div>
        <div class=" clear"></div>
        <div class="btn_certer">
          <input name="" type="button" value="保存"  class=" btn2_s" onclick="close12();"/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="link_btn" onclick="dg.cancel();">关闭窗口</a></div>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="clear"></div>
</div>
</body>
</html>
