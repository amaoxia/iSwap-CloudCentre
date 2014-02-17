<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/js/jqueryValidate/css/validator.css" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jquery.js"></script>	
<!--弹出窗口-->
<script type="text/javascript" src="${request.getContextPath()}/js/lhgdialog/lhgcore.min.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/lhgdialog/lhgdialog.min.js"></script>

<script type='text/javascript' src='${path}//js/validator/performancemanage/model/modelValidator.js'></script> 
<script type='text/javascript' src='${path}/js/jqueryValidate/formValidatorRegex.js'></script> 
<script type='text/javascript' src='${path}/js/jqueryValidate/formValidator.js'></script>
<!--浮动提示-->
<script type='text/javascript' src='${path}/js/tishi/floatPop.js'></script>
<script>

function update(){
var fa = jQuery.formValidator.pageIsValid('1');
	if (fa) {
	$.ajax({
	 type: "POST",
	 url: "modelmg!coreSet.action",
	 data:$("#checkcoreset").serialize(),
	 
	 success: function(msg){
	 		if(msg){
	 		invokePop('240px','30px','${path}/images/tsp_right.png','恭喜，保存成功！');
	 		}else{
	 		invokePop('240px','30px','${path}/images/tsp_wrong.png','保存失败！');
	 		}
	 	} 
	}); 
	}

}
	function preStep(){
	var dg = parent.frameElement.lhgDG;
		var win = dg.dgWin;
		win.location.href='${request.getContextPath()}/performancemanage/model/modelmg!preStep.action?id=${modelId?default(0)}';
		dg.reDialogSize(290,470);
		dg.SetPosition('center','center'); 
	}
</script>

</head>
<body>	
<form name="checkcoreset" id="checkcoreset">
<div class="loayt_01 rlist100" style="margin-top:10px;">
          <div class="loayt_top"><span class="loayt_tilte"><b>考核指标基本信息 </b></span><span class="loayt_right"><img src="${path}/images/kuang_right.png" width="10" height="31"/></span></div>
          <div class="loayt_mian" >
          
	            <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td align="center" valign="middle" class="loayt_cm"  height="100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
	                    <tr>
	                      <td  height="100%" valign="top" ><div class="item2 item2_scroll">
	                          <ul class="item2_c">
	                            <li>
	                              <p>考核项标准分数：</p>
	                              <span>
	                              <input id="fraction"  name="cts.fraction" type="text" size="30" value="${cts.fraction}" />
	                              <input type="hidden" name="cts.model.id" value="${modelId?default(0)}" id="modelId"/>
	                              <input type="hidden" name="cts.checkTarget.id" value="${targetId?default(0)}" id="targetId"/>
	                               <input type="hidden" name="SumSiblingsCore" value="${SumSiblingsCore?default(0)}" id="SumSiblingsCore"/>
	                              </span> <span><div id="fractionTip" style="width:100%" ></div></span></li>
	                            <li class="item2_bg">
	                              <p>考核项记分方法：</p>
	                              <span>
	                              <textarea name="cts.recodeMethod" id="recodeMethod" cols="55" rows="9" onKeyDown="checkMaxInput(this,122,recodeMethodTip)" onKeyUp="checkMaxInput(this,122,recodeMethodTip)">${cts.recodeMethod?default('')}</textarea>
	                              </span> <span><div id="recodeMethodTip" style="width:100%" ></div></span> </li>
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
          <input name="back" type="button" value="上一步"  class=" btn2_s" onclick="preStep();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="保存"  class=" btn2_s" onclick="update();"/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="link_btn" onclick="reset();">重填</a></div>
      </div>
      </form>
</body>
</html>
