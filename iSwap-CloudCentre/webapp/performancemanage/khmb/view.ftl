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
<script type="text/javascript" src="/iswap/js//jstree/jquery.jstree.js"></script>
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
      <strong>鑰冩牳妯℃澘鍚嶇О锛�/strong>2011骞寸患鍚堟不绋庣哗鏁堣�鏍稿姙娉�<strong style="margin-left:30px;">妯＄増鍒涘缓鏃堕棿锛�/strong>2011骞�鏈�0鏃�
      	</div>
    </div>
    <div class="main_c2" >
    	<strong>鑰冩牳鎸囨爣锛�/strong><br />
<table class="tabs5" id="roleView">
        <tr>
          <th width="8%">绫诲埆</th>
          <th width="13%">璇勫垎椤圭洰</th>
          <th width="69%">璁″垎鏂规硶</th>
          <th width="10%">鏍囧噯鍒嗗�</th>
        </tr>
        <tr >
          <td rowspan="5" class="trbg">鍩烘湰椤�/td>
          <td>棰嗗閲嶈</td>
          <td style="text-align:left;">鏈槑纭浉鍏宠矗浠讳汉鎴栫浉鍏宠矗浠讳汉鏈夊彉鍔紝浣嗘湭鍙婃椂閫氱煡甯傜患鍚堟不绋庡姙鎴栧競淇℃伅涓績鐨勶紝姣忔鎵�鍒嗭紝鎬绘墸鍒嗕笉瓒呰繃10鍒�/td>
          <td>10</td>
        </tr>
        <tr>
          <td>鏁版嵁褰掗泦鍛ㄦ湡</td>
          <td style="text-align:left;">缂撴姤1澶╋紝鎵�鍒嗭紝鎬绘墸鍒嗕笉瓒呰繃20鍒�/td>
          <td>20</td>
        </tr>
        <tr >
          <td>鏁版嵁鎶ラ�鏍煎紡</td>
          <td style="text-align:left;">鏈寜瑙勫畾瑕佹眰杩涜鎶ラ�鐨勶紝姣忔鎵�鍒嗭紱鐢卞競淇℃伅涓績浠ｄ负鎶ラ�鐨勶紝姣忔鎵�鍒嗭紱鎬绘墸鍒嗕笉瓒呰繃20鍒�/td>
          <td>20</td>
        </tr>
        <tr>
          <td>鏁版嵁鎶ラ�璐ㄩ噺</td>
          <td style="text-align:left;">閿欙紙婕忥級鎶ョ巼鍦�0%浠ュ唴鐨勶紝姣忔寚鏍囬」姣忔鎵�鍒嗭紱閿欙紙婕忥級鎶ョ巼鍦�0%浠ュ唴鐨勶紝姣忔寚鏍囬」姣忔鎵�鍒嗭紱閿欙紙婕忥級鎶ョ巼瓒呰繃40%鐨勶紝姣忔寚鏍囬」姣忔鎵�鍒�/td>
          <td>40</td>
        </tr>
        <tr >
          <td>宸ヤ綔浼氳鍙婁笟鍔″煿璁�/td>
          <td style="text-align:left;">姣忕己甯竴娆℃墸2鍒嗭紝杩熷埌鎴栨棭閫�墸1鍒嗭紝鎬绘墸鍒嗕笉瓒呰繃10鍒�/td>
          <td>10</td>
        </tr>
        <tr>
          <td rowspan="5" class="trbg">鍔犲垎椤�/td>
          <td >鏁版嵁鐗瑰埆璐＄尞</td>
          <td style="text-align:left;">绗竴鍚嶅姞璁�鍒嗭紝绗簩鍚嶅姞璁�.5鍒嗭紝渚濇绫绘帹锛岀鍗佸悕鍔犺0.5鍒�/td>
          <td rowspan="5" class="nobg">25</td>
        </tr>
        <tr >
          <td>鏁版嵁褰掗泦宸ヤ綔閲�/td>
          <td style="text-align:left;">鏈堟姤鎸囨爣椤�涓互涓婏紝鎴栨湰锛堝勾锛夊搴︽暟鎹�閲忚秴杩囷紵锛熸潯锛屽姞璁�鍒嗭紱鏈堟姤鎸囨爣椤�-6涓紝鎴栨湰瀛ｏ紙骞达級搴︽暟鎹�閲忚秴杩囷紵锛熸潯锛屽姞璁�鍒嗭紱鏈堟姤鎸囨爣椤�-3涓紝鎴栨湰瀛ｏ紙骞达級搴︽暟鎹�閲忚秴杩囷紵锛熸潯锛屽姞璁�鍒�/td>
        </tr>
        <tr>
          <td>瀹屾暣鏁版嵁鏍囪瘑</td>
          <td style="text-align:left;">姣忔寚鏍囬」鍔犺1鍒嗭紝鎬诲姞鍒嗕笉瓒呰繃5鍒�/td>
        </tr>
        <tr >
          <td>鏁版嵁搴旂敤鎴愭晥</td>
          <td style="text-align:left;">鏍规嵁搴旂敤鎴愭晥鐨勫ぇ灏忥紝鍔�-5鍒�/td>
        </tr>
        <tr>
          <td>娌荤◣淇℃伅鎶ラ�</td>
          <td style="text-align:left;">姣忓彂甯�鏉★紝鍔�鍒嗭紝鎬诲姞鍒嗕笉瓒呰繃5鍒�/td>
        </tr>
        <tr>
          <td height="30" class="trbg">鍑忓垎椤�/td>
          <td><span >淇℃伅娉勯湶</span></td>
          <td style="text-align:left;">鏍规嵁鎯呰妭涓ラ噸绋嬪害锛屾墸鍑�-10鍒�/td>
          <td>10</td>
        </tr>
      </table>
      <strong>鑰冩牳閮ㄩ棬锛�/strong>
      <table class="tabs5" id="roleView2">
        <tr>
          <th>骞村害鑰冩牳閮ㄩ棬</th>
          <th>瀛ｅ害鑰冩牳閮ㄩ棬</th>
        </tr>
        <tr >
          <td style="text-align:left;">鍙戞敼濮斻�缁忎俊濮斻�鏁欒偛灞��绉戞妧灞��鍏畨灞��姘戞斂灞��鍙告硶灞��璐㈡斂灞��甯傜紪鍔炪�浜轰繚灞��鍥藉湡灞��寤鸿灞��鐜繚灞��鍏矾绠＄悊澶勩�鑸亾绠＄悊澶勩�杩愯緭绠＄悊澶勩�娓姟绠＄悊灞��鍦版柟娴蜂簨灞��姘村埄灞��鍐滃紑灞��鍟嗗姟灞��鏂囧箍鏂板眬銆佸崼鐢熷眬銆佷綋鑲插眬銆佸璁″眬銆佺粺璁″眬銆佸浗璧勫銆佺墿浠峰眬銆佹埧绠″眬銆佽鍒掑眬銆佸伐鍟嗗眬銆佽川鐩戝眬銆佹畫鑱斻�鍥界◣灞��绮灞��渚涚數鍏徃銆佸湴绋庡眬銆佽嚜鏉ユ按鍏徃銆佽嵂鐩戝眬銆佸浗瀹舵捣浜嬪眬</td>
          <td style="text-align:left;">鍙戞敼濮斻�缁忎俊濮斻�鏁欒偛灞��绉戞妧灞��鍏畨灞��姘戞斂灞��鍙告硶灞��璐㈡斂灞��甯傜紪鍔炪�浜轰繚灞��鍥藉湡灞��寤鸿灞��鐜繚灞��鍏矾绠＄悊澶勩�鑸亾绠＄悊澶勩�杩愯緭绠＄悊澶勩�娓姟绠＄悊灞��鍦版柟娴蜂簨灞��姘村埄灞��鍐滃紑灞��鍟嗗姟灞��鏂囧箍鏂板眬銆佸崼鐢熷眬銆佷綋鑲插眬銆佸璁″眬銆佺粺璁″眬銆佸浗璧勫銆佺墿浠峰眬銆佹埧绠″眬銆佽鍒掑眬銆佸伐鍟嗗眬銆佽川鐩戝眬銆佹畫鑱斻�鍥界◣灞��绮灞��渚涚數鍏徃銆佸湴绋庡眬銆佽嚜鏉ユ按鍏徃銆佽嵂鐩戝眬銆佸浗瀹舵捣浜嬪眬</td>
        </tr>
      </table>
      <br />
      <div>
        <div class="btn_certer">
          <input name="" type="button" value="鍏�闂� class=" btn2_s" onclick="dg.cancel();"/></div>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="clear"></div>
</div>
</body>
</html>
