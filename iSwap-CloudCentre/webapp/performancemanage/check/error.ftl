<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>ÎÞ±êÌâÎÄµµ</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	function cl(){
		var dg = frameElement.lhgDG;			
		dg.cancel();
	}
</script>
</head>

<body>
<div class="pop_div7 pm6_c">
  <div class="clear"> <span><img src="${path}/images/error_ico.png" align="absmiddle" /></span> 
  <span class="error_c">${message?default("")}<br/>
  <div class="btn_certer">
    <input name="button" type="button" value="¹Ø±Õ"  class=" btn2_s" onclick="cl();"/>
  </div>
</div>
</body>
</html>