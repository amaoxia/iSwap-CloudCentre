<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>鏃犳爣棰樻枃妗�/title>
<link href="/iswap/css/main.css" rel="stylesheet" type="text/css" />
<link href="/iswap/css/tree.css" rel="stylesheet" type="text/css" />
<link href="/iswap/css/pop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/iswap/js/jstree/jquery.js"></script>
<script type="text/javascript" src="/iswap/js/jstree/jquery.cookie.js"></script>
<script type="text/javascript" src="/iswap/js/jstree/jquery.hotkeys.js"></script>
<script type="text/javascript" src="/iswap/js/jstree/jquery.jstree.js"></script>
<!--閫氱敤鏂规硶-->
<script type="text/javascript" src="/iswap/js/iswap_common.js"></script>
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
							"title":"棰嗗閲嶈"
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
							},"title":"鏁版嵁褰掗泦鍛ㄦ湡"
						},
						"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked",
							"id":"8","rel":""
						},"children":[],"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"鏁版嵁鎶ラ�鏍煎紡"
						},
						"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked","id":"9","rel":""
						},"children":[],
						"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"鏁版嵁鎶ラ�璐ㄩ噺"
						},"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked","id":"9","rel":""
						},"children":[],
						"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"宸ヤ綔浼氳鍙婁笟鍔″煿璁�
						},"state":""
					}],
					"data":{
						"attr":{
							"href":"","leaf":false,"title":""
						},"title":"鍩烘湰椤�
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
							},"title":"鏁版嵁鐗瑰埆璐＄尞"
						},
						"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked","id":"13","rel":""
						},"children":[],
						"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"鏁版嵁褰掗泦宸ヤ綔閲�
						},"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked","id":"58","rel":""
						},"children":[],
						"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"瀹屽杽鏁版嵁鏍囪瘑"
						},"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked","id":"14","rel":""
						},"children":[],"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"鏁版嵁搴旂敤鎴愭晥"
						},"state":""
					},{
						"attr":{
							"clAss":"jstree-unchecked","id":"10","rel":""
						},"children":[],"data":{
							"attr":{
								"href":"","leaf":true,"title":""
							},"title":"娌荤◣淇℃伅鎶ラ�"
						},"state":""
					}],
					"data":{
						"attr":{
							"href":"","leaf":false,"title":""
						},"title":"鍔犲垎椤�
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
							"title":"淇℃伅娉勬紡"
						},"state":""
					}],"data":{
						"attr":{"href":"","leaf":false,"title":""},"title":"鍑忓垎椤�
					},"state":"open"
				}],
				"data":{
					"attr":{"href":"","leaf":false,"title":""},
					"title":"鑰冩牳鎸囨爣"
				},"state":"open"
			}]
		},
		//鏅�鏍�
		//"plugins" : [ "themes", "json_data", "ui", "crrm", "cookies", "dnd", "search", "types", "hotkeys", "contextmenu" ],
		//澶嶉�妗嗘爲
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
      鑰冩牳妯℃澘鍚嶇О锛�br/>
    	<select>
    		<option>2011骞村害</option>	
    	</select>
    	<select>
    		<option>缁煎悎娌荤◣缁╂晥鑰冩牳鍔炴硶</option>	
    	</select>	
      	</div>
    </div>
    <div class="main_c2" >
    	<br/>鑰冩牳鎸囨爣璁剧疆锛�br />
      <div class="left_tree_com" style="width:250px;">
        <span>
        <p>
          <div id="demo" scrolling="Auto" frameborder="0" style="border:0px;height:400px;width:250px;"></div>
        </p>
        </span> </div>
      <div class="right_main">
        <div class="loayt_01 rlist100">
          <div class="loayt_top"> <span class="loayt_tilte"><b>鑰冩牳鎸囨爣鍩烘湰淇℃伅 </b></span><span class="loayt_right"><img src="/iswap/images/kuang_right.png" width="10" height="31" /></span> </div>
          <div class="loayt_mian">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center" valign="middle" class="loayt_cm"  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
                    <tr>
                      <td  height="100%" valign="top" ><div class="item2 item2_scroll">
                          <ul class="item2_c">
                            <li>
                              <p>鑰冩牳椤规爣鍑嗗垎鏁帮細</p>
                              <span>
                              <input type="text" size="30" />
                              </span> </li>
                            <li class="item2_bg">
                              <p>鑰冩牳椤硅鍒嗘柟娉曪細</p>
                              <span>
                              <textarea name="textarea" id="textarea" cols="55" rows="9"></textarea>
                              </span> </li>
                              <li><a href="javascript:void(0)" class="link_btn">閲嶅～</a></li>
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
          <input name="" type="button" value="淇濆瓨"  class=" btn2_s" onclick="close12();"/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="link_btn" onclick="dg.cancel();">鍏抽棴绐楀彛</a></div>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="clear"></div>
</div>
</body>
</html>
