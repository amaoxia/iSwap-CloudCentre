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
<!--验证-->
<script type='text/javascript' src='${path}/js/validator/performancemanage/checktarget/checktargetValidator.js'></script> 
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
	 url: "checkTarget!update.action",
	 data:{
	 	id:${id},
	 	name:encodeURI($("#name").val()),
	 	checkCode:encodeURI($("#checkCode").val()),
	 	content:encodeURI($("#content").val()),
	 	remark:encodeURI($("#remark").val())
	 },
	 
	 success: function(msg){
	 		if(msg=='false'){
	 		invokePop('240px','30px','${path}/images/tsp_wrong.png','保存失败！');
	 		}
	 		if(msg=='true'){
	 		invokePop('240px','30px','${path}/images/tsp_right.png','恭喜，保存成功！');
	 		}
	 	} 
	}); 
	}
}
</script>

</head>
<body>	
<div class="loayt_01 rlist100" style="margin-top:10px;">
          <div class="loayt_top"><span class="loayt_tilte"><b>考核指标基本信息 </b></span><span class="loayt_right"><img src="${path}/images/kuang_right.png" width="10" height="31"/></span></div>
          <div class="loayt_mian" >
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center" valign="middle" class="loayt_cm"  height="100%" >
                <form name="checkTarget" id="checkTarget">
                <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
                    <tr>
                      <td height="100%" valign="top" ><div class="item2 item2_scroll" style="height:100%;">
                          <ul class="item2_c">
                          <li>
                              <p>考核指标名：</p>
                              <span>
                              ${entityobj.name?default('无')}
                                 <input type="hidden" size="30" name="name" value="${entityobj.name?default('')}" id="name" readonly="true"/>
                              <input type="hidden" name="id" value="${entityobj.id?default('')}"/>
                              </span> 
                            </li>
                            <li class="item2_bg">
                              <p>考核项编码：</p>
                              <span>
                              <input type="text" size="30" name="checkCode" value="${entityobj.checkCode?default('')}" id="checkCode" class="required"/>
                              </span>
                              <span><div id="checkCodeTip" style="width:100%" ></div></span>
                            </li>
                            <li>
                              <p>考核项内容和标准：</p>
                              <span>
                              <textarea  cols="90" rows="5" name="content" id="content" onKeyDown="checkMaxInput(this,255,contentTip)" onKeyUp="checkMaxInput(this,255,contentTip)">${entityobj.content?default('')}</textarea>
                              </span> 
                              <span><div id="contentTip" style="width:200px" ></div></span>
                              </li>
                            <li class="item2_bg">
                              <p>备注：</p>
                              <span>
                              <textarea cols="90" rows="5" name="remark" id="remark"  onKeyDown="checkMaxInput(this,255,remarkTip)" onKeyUp="checkMaxInput(this,255,remarkTip)">${entityobj.remark?default('')}</textarea>
                              </span>
                             <span><div id="remarkTip" style="width:200px" ></div></span>
                             </li>
                          </ul>
                        </div></td>
                    </tr>
                  </table>
                  </form>
                  </td>
              </tr>
            </table>
          </div>
        </div>
        <div class=" clear"></div>
        <div class="btn_certer">
          <input name="" type="button" value="保存"  class=" btn2_s" onclick="update();"/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="link_btn" onclick="reset();">重填</a></div>
      </div>
</body>
</html>
