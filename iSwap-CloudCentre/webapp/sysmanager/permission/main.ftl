<#global path = request.getContextPath() >
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>功能菜单权限设置</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
	</head>
    <body onclick="parent.hideMenu()">
	    <div class="common_menu">
	      <div class="c_m_title"><img src="${path}/images/img_02.png" align="absmiddle" />功能菜单权限设置</div>
	    </div>
	    </br>
	    <div class="main_c2" >
	      <table width="100%" height="385"  border="0" cellpadding="0" cellspacing="0" style="overflow:hidden;">
	        <tr>
	          <td width="100%" valign="top"><div class="loayt_01" style="width:38%; padding-right:17px; float:left; margin-left:18px;">
	              <div class="loayt_top"> 
		              <span class="loayt_tilte">
		              	<b>角色模块</b>
		              </span>
	              	  <span class="loayt_right">
	              	  	<img src="${path}/images/kuang_right.png" width="10" height="31" />
	                  </span> 
	              </div>
	              <div class="loayt_mian" >
	                <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                  <tr>
	                    <td align="center" valign="middle" class="loayt_cm"  height="100%" ><table width="80%" border="0" cellspacing="0" cellpadding="0"  height="100%">
	                        <tr>
	                          <td  height="80%" valign="top" >
	                          	<iframe src="${path}/sysmanager/role/role!view.action" height="365px;"  id="roleTree"  scrolling="Auto" frameborder="0"  style="border:0px;width:100%;" allowtransparency="true"></iframe>
	                          	</td>
	                        </tr>
	                      </table></td>
	                  </tr>
	                </table>
	              </div>
	            </div>
	            <div class="loayt_01" style="width:38%;padding-right:17px;height:340px; float:right;">
	              <div class="loayt_top"> 
		              <span class="loayt_tilte">
		              	<b>菜单模块</b>
		              </span>
	             	  <span class="loayt_right">
	                 	 <img src="${path}/images/kuang_right.png" width="10" height="31" />
	                  </span> 
	              </div>
	              <div class="loayt_mian">
	                <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                  <tr>
	                    <td align="center" valign="middle" class="loayt_cm"  height="100%">
	                    <iframe src="${path}/sysmanager/permission/permission!permission.action?roleId=0" id="permissionTree" frameborder="0" style="width:100%;height:365px;" allowtransparency="true"></iframe></td>
	                  </tr>
	                </table>
	              </div>
	            </div>
	            <div class=" clear" ></div>
	            <p class="btn_s_m2">
	              <input name="" type="button" value="保存"  class=" btn2_s"  onclick="setPermission()"/>
	            </p></td>
	        </tr>
	      </table>
	    </div>
	    <div class="clear"></div>
		<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="${path}/js/iswap_common.js"></script>	
		<script type="text/javascript">
				//设置权限菜单跳转路径
				function invoke(id){
				    $("#permissionTree").attr("src","${path}/sysmanager/permission/permission!permission.action?roleId="+id);
				}
					//给角色添加权限
				   function  setPermission(){
					 document.getElementById("permissionTree").contentWindow.setPermission();
				}
		   $(function () { 
	 		$('#roleTree').load( 
			   function() {
				$(this).height($(this).contents().find("body").attr('scrollHeight'));
				}); 
				$('#permissionTree').load( 
			   function() {
				$(this).height($(this).contents().find("body").attr('scrollHeight'));
				}); 
				});
		</script>
	</body>
</html>