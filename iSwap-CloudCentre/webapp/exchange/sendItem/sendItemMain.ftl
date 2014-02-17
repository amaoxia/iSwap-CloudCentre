<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#global path = request.getContextPath() >
		<#include "/common/taglibs.ftl">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${path}/js/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>	
		<script src="${path}/js/liger/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    	<script src="${path}/js/liger/lib/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
		<script type="text/javascript" src="${path}/js/liger/lib/ligerUI/js/plugins/ligerTree.js"></script>
		 <script type="text/javascript">
	        $(function () {
	            $("#container").ligerLayout({ leftWidth: 200 }); //这一句可是关键啊
	        	$("#tree1").ligerTree({ checkbox: false});
	        });
	    </script>
	</head>
	<body>
		    <div class="common_menu">
			      <div class="c_m_title">
			      	<img src="${path}/images/title/img_05.png"  align="absmiddle" />
			     	 数据源表配置
			      </div>
		     </div>
		     <div id="container">
		        <div position="left" title="&nbsp;<input></input>">
					<ul id="tree1">
				        <li>
				            <span>应用</span>
				            <ul>
				                <li>
				                    <span>应用1</span>
				                     <ul>
				                        <li>
				                        	<span>指标1</span>
				                        	<ul>
						                        <li><span>发送</span></li>
						                        <li><span>接收</span></li>
						                     </ul>
				                        </li>
				                        <li>
				                        	<span>指标2</span>
				                        	<ul>
						                        <li><span>发送</span></li>
						                     </ul>
				                        </li>
				                     </ul>
				                 </li>
				                 <li><span>应用2</span>
				                 	<ul>
				                        <li><span>指标1</span>
				                        	<ul>
						                        <li><span>接收</span></li>
						                     </ul>
				                        </li>
				                        <li><span>指标2</span>
				                        	<ul>
						                        <li><span>发送</span></li>
						                     </ul>
				                        </li>
				                     </ul>
				                 </li>
				            </ul>
				        </li>   
				    </ul> 
		        </div>
		        <div position="center" title="指标列表">
					<iframe id="content" src="${path}/exchange/sendItem/sendItem!list.action" width="100%"  height="750px";  frameborder="0" style="" allowtransparency="true"></iframe>
		        </div>
		    </div>
	   <div class="clear"></div>
		<#include "/common/commonLhg.ftl">
		<script type="text/javascript" src="${path}/js/iswap_table.js"></script>
		<script type="text/javascript">    
		</script>
	</body>
</html>
