<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#global path = request.getContextPath() >
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<body height="100%" >	
		<div class="loayt_01 rlist100" style="margin-top:4px;">
	          <div class="loayt_top">
	          	<span class="loayt_tilte">
	          		<b>部门基本信息 </b>
	          	</span>
	          	<span class="loayt_right">
	          		<img src="${path}/images/kuang_right.png" width="10" height="31"/>
	          	</span>
	          </div>
	          <div class="loayt_mian">
	            <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td align="center" valign="middle" class="loayt_cm"  height="100%" >
		                <form action="${path}/sysmanager/dept/dept!saveOrUpdate.action"  method="post" id="saveForm" enctype="multipart/form-data">
			                <@s.token name="token"/>
			                <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
			                    <tr>
			                      <td height="100%" valign="top" ><div class="item2 item2_scroll" style="height:100%;">
			                          <ul class="item2_c">
			                          <li>
			                              <p><b>*</b>机构名称：</p>
			                              <span>
			                             	 <input type="text" size="30" name="deptName" value="${entityobj.deptName?default('')}" id="deptName" maxlength="30"/>
			                                 <input type="hidden" name="id" value="${entityobj.id?default('0')}" id="id"/>
			                                 <input type="hidden" value="${entityobj.logoPath?default('')}" name="oldLogoPath"/>
			                              </span> <span><div id="deptNameTip"></div></span></li>
			                            <li class="item2_bg">
			                              <p><b>*</b>机构简称：</p>
			                               <span>
			                              <input type="text" size="30" name="shortName" value="${entityobj.shortName?default('')}" id="shortName" maxlength="20"/>
			                              </span> <span><div id="shortNameTip"></div> </span> </li>
			                            <li>
			                              <p><b>*</b>组织机构代码：</p>
			                              <span>
			                              <input type="text" size="30" name="orgCode" value="${entityobj.orgCode?default('')}" id="orgCode" maxlength="20"/>
			                              </span> <span><div id="orgCodeTip"></div></span> </li>
			                            <li class="item2_bg">
			                              <p><b>*</b>机构标识：</p>
			                              <span>
			                              <input type="text" size="30" name="deptUid" value="${entityobj.deptUid?default('')}" id="deptUid" maxlength="20"/>
			                              </span> <span> <div id="deptUidTip"></div> </span></li>
			                                <li>
			                              <p><!--<#if !entityobj.deptUid?exists><b>*</b></#if>-->机构LOGO：(JPG,GIF,PNG,JPEG,BMP  70*60 大小)</p>
			                              <span>
			                              <input name="upload"   type="file"  <#if !entityobj.deptUid?exists>id="upload"</#if>  size="31"    onChange="javascript:checkFormat(this.value)"></input>
			                               &nbsp;&nbsp;&nbsp;  <!--<img id="uploadimage" height="100" width="100" src=""  onload="javascript:DrawImage(this);" />"-->
			                              </span> <span><div id="uploadTip"></div></span></li>
			                               <li class="item2_bg">
			                              <p>上级机构：</p>
			                              <span>
			                              <#if entityobj.sysDept?exists><input type="hidden" name="sysDept.id" id="fatherId" value="${entityobj.sysDept.id?default('0')}"/>${entityobj.sysDept.deptName?default('')}<#else>无上级部门</#if>
			                              </span> </li>
			                              <li >
			                              <p>机构联系人： </p>
			                              <span>
			                              <input type="text" size="30" name="linkman" value="${entityobj.linkman?default('')}" id="linkman" maxlength="20"/>
			                              </span> <span><div id="linkmanTip"></div> </span> </li>
			                              <li class="item2_bg">
			                              <p>机构联系电话：</p>
			                              <span>
			                              <input type="text" size="30" name="phone" value="${entityobj.phone?default('')}" id="phone" maxlength="20"/>
			                              </span> <span> <div id="phoneTip"></div></span></li>
			                              <li >
			                              <p>机构地址:<label id="textCount">(可输入200字)</label></p>
			                              <span>
			                              <textarea name="address"  cols="90" rows="2"  id="address"  onkeyPress="checkLength('address','textCount','200')" onpaste="checkLength('address','textCount','200')" >${entityobj.address?default('')}</textarea>
			                              </span><span><div id="addressTip"></div></span></li>
			                              <li class="item2_bg">
			                              <p>机构描述:<label id="textCount1">(可输入200字)</label></p>
			                              <span>
			                              <textarea name="descript"  cols="90" rows="5"  id="descript"  onkeyPress="checkLength('descript','textCount1','200')" onpaste="checkLength('descript','textCount1','200')" >${entityobj.descript?default('')}</textarea>
			                              </span><span><div id="descriptTip"></div></span> </li>
			                          </ul>
			                        </div>
			                       </td>
			                    </tr>
			                  </table>
		                  </form>
	                 	</td>
	              	</tr>
	               </table>
	          	</div>
	          </div>
	        <div class="btn_certer">
	          <input  type="button" value="保存"  class=" btn2_s" onclick="sub()"/>
	          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <input  type="reset" value="重填"  onclick="reset('saveForm')" class="btn2_s"/>
	      </div>
	      <script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>	
	 <!--验证js-->
	 <#include "/common/commonValidator.ftl">
	<script type='text/javascript' src='${path}/js/validator/system/deptValidator.js'></script> 
	<!-- 验证文本域长度-->
	<script type='text/javascript' src="${path}/js/areaLen.js"></script>
	<script type='text/javascript' src="${path}/js/reset.js"></script>
	  <style>
	     b {
		color:#F00;
		padding-right:2px;
		font-weight:normal;
	}
	#newPreview   
	{   
	filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);   
	}   
	</style>
	  
	</head>
	<script type='text/javascript'>
					//小写转成大写
					/*$('#shortName').keypress(function(e) {     
				        var keyCode= event.keyCode;  
				        var realkey = String.fromCharCode(keyCode).toUpperCase();  
				        $(this).val($(this).val()+realkey);  
				        event.returnValue =false;  
				    }); */
				    
				    	$('#orgCode').keypress(function(e) {     
				        var keyCode= event.keyCode;  
				        var realkey = String.fromCharCode(keyCode).toUpperCase();  
				        $(this).val($(this).val()+realkey);  
				        event.returnValue =false;  
				    });
				    	$('#deptUid').keypress(function(e) {     
				        var keyCode= event.keyCode;  
				        var realkey = String.fromCharCode(keyCode).toUpperCase();  
				        $(this).val($(this).val()+realkey);  
				        event.returnValue =false;  
				    });
				    
	function PreviewImg(imgFile)   
	{   
	//新的预览代码，支持 IE6、IE7。   
	var newPreview = document.getElementById("newPreview");   
	newPreview.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgFile.value;   
	newPreview.style.width = "80px";   
	newPreview.style.height = "60px";   
	}   
	var falg=true;
	function checkFormat(filePath) {
	 var   i = filePath.lastIndexOf('.');
	 var   len = filePath.length;
	 var   str = filePath.substring(len,i+1);
	 var   extName = "JPG,GIF,PNG,JPEG,BMP";
	 var uploadName=document.getElementsByTagName("upload");
	 if(extName.indexOf(str.toUpperCase()) < 0)  {     
	  alert("请选择正确的图片文件!");   
	  falg=false;
	  return false;   
	 } 
	 return falg;         
	}   
	//验证用户是否通过
	function sub() {
		var fa = jQuery.formValidator.pageIsValid("1");
		if(!falg){
			alert("请选择正确的图片文件和对应尺寸大小!");
			return;
		}
		if (fa) {
			document.forms[0].submit();
		}
	}
	function FileChange(Value){
	 if(checkFormat(Value)){
	  flag=false;
	  document.getElementById("uploadimage").width=10;
	  document.getElementById("uploadimage").height=10;
	  document.getElementById("uploadimage").alt="";
	  document.getElementById("uploadimage").src=Value;
	 }
	 }
		<#if flag==1>
		 $(function(){
		   window.parent.refreshTree();
		})
		</#if>
	</script>
	</body>
</html>

