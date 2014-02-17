<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<#global path = request.getContextPath() >
<HTML>
<HEAD>
<TITLE></TITLE>
<META content="texhtml; charset=utf-8" http-equiv=Content-Type>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />


 
<!--弹出窗口-->
<script type="text/javascript" src="${request.getContextPath()}/js/lhgdialog/lhgcore.min.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/lhgdialog/lhgdialog.min.js"></script>
<!--通用方法-->

<script type="text/javascript" src="${path}/js/jstree/jquery.js"></script>	

<#include "/common/commonJsTree.ftl">
   <script type="text/javascript" class="source"> 

$(function () {
 $.ajaxSetup({cache:false});//ajax调用不使用缓存 
	$("#checkTree").jstree({ 
			"plugins" : [ "themes", "json_data", "ui", "types", "hotkeys", "checkbox" ],
			"json_data" : {
				"ajax" : {
					"url" : "${path}/performancemanage/checktarget/checkTarget!getcheckTargetTree.action",
					"data" : function (n) { 
						return { id : n.attr ? n.attr("id") : 0 }; 
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
				$('#checkTree').jstree('check_node',"#"+result);
			});
		})
});


function next(url){
		var dg = frameElement.lhgDG;
		var win = dg.dgWin;
		win.location.href=url;
		dg.reDialogSize(920,570);
		dg.SetPosition('center','center'); 
	}

	<!--异步的保存信息-->
   function saveInfo(url){
   		var subject = $("#subject_id").val();
   		var year = $("#year_id").val(); 
   	    var ids="";   
		 var $nodes = $('#checkTree').jstree("_get_children",-1).find("li");
    		$nodes.each(function(){
    			var id = $(this).attr("id");
    			if($('#checkTree').jstree("is_checked","#"+id)){
	    			if(id!=0){
	    				ids +=id+",";
	    			}
    				
    			}
    		});
    		if(""!=ids){
    			  $.ajax({
					 type: "POST",
					 url: "${path}/performancemanage/model/modelmg!updateCheckTar.action",
					 data:  {
					 "modelId":${modelId?default('')},
					 "targetid":ids,
					 "checkYear":$("#year_id").val(),
					  "name":encodeURIComponent($("#subject_id").val()),
					 "async":false
					 },
					 success: function(msg){ 
						url+="?id="+msg;
						next(url);
					  } 
					}); 
    		}else{
    			alert("请选择考核指标！");
    		}
   }
   
   function getdeptid(){
		var did=$("#deptid").val().split(",");
		return did;
	}
	
</script>
</HEAD>
<BODY>
<input type="hidden" value="${deptid}" id="deptid"/>
<div class="main">
 <div>
      <div height="60px">
      考核模板名称：<br/>
    	<select id="year_id" name="year_time">
    		<option <#if entityobj.checkYear=='2011'> selected</#if>>2011</option>年度	
    		<option <#if entityobj.checkYear=='2012'> selected</#if>>2012</option>年度
    		<option <#if entityobj.checkYear=='2013'> selected</#if>>2013</option>年度
    		<option <#if entityobj.checkYear=='2014'> selected</#if>>2014</option>年度
    		<option <#if entityobj.checkYear=='2015'> selected</#if>>2015</option>年度
    	</select>
    	<select id="subject_id" name="subject">
    		<option name>综合治税绩效考核办法</option>	
    	</select>	
      	</div>
    </div>
<DIV id="checkTree" style="FONT-SIZE: 14px; HEIGHT: 307px; WIDTH: 200px;OVERFLOW-y:auto;"></DIV>
<p class="btn_s_m2" style="align:center">
<input name="" type="button" id="next" value="提交并下一步"  class=" btn2_s"  onclick="saveInfo('${path}/performancemanage/model/modelmg!addView.action');"/><!--&nbsp;&nbsp;&nbsp;<a href="#" class="link_btn"  onclick="clo();" >关闭窗口</a-->
</p>
</div>
</BODY>
</HTML>
