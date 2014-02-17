<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表结构信息</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="pop_01"   style="width:100%;overflow-x:hidden;overflow-y:scroll;">
    <div class="main_c" >
    <form id="pageForm" method="post"  name="pageForm"	action="${path}/exchange/tabledesc/tabledesc!list.action">
           <table class="main_drop">
        				<tr>
					    </tr>
		   </table>                              
			<div class="main_c2" >
      <table width="100%" height="385"  border="0" cellpadding="0" cellspacing="0" style="overflow:hidden;">
        <tr>
          <td width="100%" valign="top"><div class="loayt_01" style="padding-right:17px; float:left; margin-left:18px;margin-right:17px;">
              <div class="loayt_top"> <span class="loayt_tilte"><b>选择类目</b></span><span class="loayt_right"><img src="${path}/images/kuang_right.png" width="10" height="31" /></span> </div>
              <div class="loayt_mian" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td align="center" valign="middle" class="loayt_cm"  height="100%" ><table width="80%" border="0" cellspacing="0" cellpadding="0"  height="100%">
                        <tr>
                          <td  height="80%" valign="top" >
                            <iframe src="${path}/exchange/item/item!catalogTree.action?catalogId=${catalogId}" id="tree"  height="300px;" scrolling="Auto" frameborder="0"  style="border:0px" allowtransparency="true"></iframe>
                          	</td>
                        </tr>
                      </table></td>
                  </tr>
                </table>
              </div>
            </div>
            </td>
        </tr>
      </table>
    </div>
		</div>
			</div>
	</form>
 <script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript" src="${path}/js/iswap_common.js"></script>	
<#include "/common/commonJsTree.ftl">
<script type="text/javascript">    
var dg= frameElement.lhgDG; 
dg.addBtn( 'closewin', '关闭窗口', closeWin); 
dg.addBtn( 'prop', '推送', pushToCatalog); 
dg.addBtn( 'back', '上一步', returns); 
function returns(){
	window.location.href="${path}/exchange/item/item!selectCatalog.action?itemIds=${itemIds}";
}
function closeWin(){
	dg.cancel();
}

function pushToCatalog(){
	var categoryIds = document.getElementById("tree").contentWindow.getCategoryIds();
	if(categoryIds==""){
		return;
	}
	window.location.href="${path}/exchange/item/item!pushToCatalog.action?itemIds=${itemIds}&catalogId=${catalogId}&categoryIds="+categoryIds;
}
</script>
			</body>
</html>