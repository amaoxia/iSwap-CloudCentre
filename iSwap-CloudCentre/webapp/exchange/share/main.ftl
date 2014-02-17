<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="frameset_w" style="height:100%" >
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title" id="showTile">
      <#if type=="1">
      <img src="${path}/images/title/fa.png"  align="absmiddle" />数据共享发布
      <#elseif type=="2">
      <img src="${path}/images/title/dataserach.png"  align="absmiddle" />数据查看申请
      <#elseif type=="3">
      <img src="${path}/images/title/dataAuth.png"  align="absmiddle" />数据查看授权
      <#elseif type=="4">
      <img src="${path}/images/title/useApply.png"  align="absmiddle" />数据使用申请
      <#elseif type=="5">
      <img src="${path}/images/title/useAuth.png"  align="absmiddle" />数据使用授权
      <#elseif type=="6">
      <img src="${path}/images/title/datac.png"  align="absmiddle" />数据查查询
      </#if>
      </div>
    </div>
    <div class="main_c2" >
      <div class="left_tree_com" >
        <h1>
          <ul class="ltab">
            <li ifrmUrl='#'><a href="javascript:void(0)"  onclick="setUrl('0')" id="meta" class="ltab_s">元数据</a></li>
            <li ifrmUrl='#'><a href="javascript:void(0)"  onclick="setUrl('1')" id="app">应用 </a></li>
          </ul>
        </h1>
        <span>
        <p>
          <iframe id="tree" src="${path}/cloudstorage/metadata/metadata!treeMain.action?type=${type}" width="250px"  height="390px;" scrolling="Auto" frameborder="0"  style="border:0px" allowtransparency="true"></iframe>
        </p>
        </span> </div>
         <div class="right_main">
          <iframe id="right_content" name="right_content" src="<#if type=='1'>${path}/cloudstorage/metadata/metadata!listMeta.action<#elseif type=='2'>${path}/cloudstorage/metadata/metadata!listApply.action<#elseif type=='3'>${path}/cloudstorage/apply/apply!listMetaAuth.action<#elseif type=='4'>${path}/cloudstorage/dataAuth/dataAuth!listMeta.action<#elseif type=='5'>${path}/cloudstorage/dataAuth/dataAuth!listMetaAuth.action<#else>${path}/cloudstorage/apply/apply!serachList.action</#if>" width="100%"  height="400px;" scrolling="Auto" frameborder="0"  style="border:0px" allowtransparency="true"></iframe>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="clear"></div>
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" >
	 function  setUrl(temp){
  		if(temp=='0'){
  			$("#app").removeClass("ltab_s");
  			$("#meta").addClass("ltab_s");
  		   $("#tree").attr("src","${path}/cloudstorage/metadata/metadata!treeMain.action?type=${type}");
  			 if(${type}=='1'){//共享
  			 	$("#right_content").attr("src","${path}/cloudstorage/metadata/metadata!listMeta.action");
  			 }
  			 if(${type}=='2'){//申请
  			 	$("#right_content").attr("src","${path}/cloudstorage/metadata/metadata!listApply.action");
  			 }
  			  if(${type}=='3'){//授权
  			 	$("#right_content").attr("src","${path}/cloudstorage/apply/apply!listMetaAuth.action");
  			 }
  			  if(${type}=='4'){//数据使用申请
  			 	$("#right_content").attr("src","${path}/cloudstorage/dataAuth/dataAuth!listMeta.action");
  			 }
  			 if(${type}=='5'){//数据使用授权
  			 	$("#right_content").attr("src","${path}/cloudstorage/dataAuth/dataAuth!listMetaAuth.action");
  			 }
  			  if(${type}=='6'){//数据查询
  			 	$("#right_content").attr("src","${path}/cloudstorage/apply/apply!serachList.action");
  			 }
  		}
  		if(temp=='1'){
  		$("#meta").removeClass("ltab_s");
  		$("#app").addClass("ltab_s");
  		$("#tree").attr("src","${path}/cloudcenter/appMsg/appMsg!treeMain.action?type=${type}");
  		if(${type}=='1'){//共享
  		 	$("#right_content").attr("src","${path}/exchange/share/share!listApp.action");
  		 }
  		  if(${type}=='2'){//申请
  			 $("#right_content").attr("src","${path}/exchange/share/share!listApply.action");
  			 }
  			 if(${type}=='3'){//授权
  			 	$("#right_content").attr("src","${path}/cloudstorage/apply/apply!listApplyAuth.action");
  			 }
  			  if(${type}=='4'){//数据使用申请
  			 	$("#right_content").attr("src","${path}/cloudstorage/dataAuth/dataAuth!listApply.action");
  			 }
  			 if(${type}=='5'){//数据使用申请
  			 	$("#right_content").attr("src","${path}/cloudstorage/dataAuth/dataAuth!listApplyAuth.action");
  			 }
  			   if(${type}=='6'){//数据查询
  			 	$("#right_content").attr("src","${path}/cloudstorage/apply/apply!serachAppList.action");
  			 }
  		}
  }
</script>
</body>
</html>

