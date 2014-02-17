<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>错误页面</title>

<style type="text/css">
body, div, tr, th, td {
	margin:0;
	padding:0;
	font-family:"微软雅黑";
}
img{border:0;}
.pop_bg{
	 background:url(${path}/images/pop_bg.png) no-repeat;
	 width:491px;
	 height:286px;
	 margin:0 auto;
     position:absolute; 
     top:50%; 
     left:50%; 
     margin:-143px 0 0 -245px;
}
.point{
	float:left;
	width:250px;
	font-size:14px;
	color:#fff;
	padding-left:210px;
	padding-top:60px;
	}
.btn{
	float:left;
	width:250px;
	padding-left:210px;
	padding-top:20px;
	}
</style>
</head>
<body>
<div class="pop_bg">
	<script type="text/javascript">
	function  load(){
			var DG = frameElement.lhgDG; 
			if(DG!=null){
			DG.curWin.closeWindow();//关闭窗口
			return;
		}
		history.back();		
	}
	</script>
<div class="point"> 
		${errorInfo?default("出现错误,请联系管理员！")}
      </div>
<div class="btn"><a href="javascript:void(0)" onclick="load()"><img src="${path}/images/btn.png" width="92" height="36" /></a></div>
</div>
</body>
</html>