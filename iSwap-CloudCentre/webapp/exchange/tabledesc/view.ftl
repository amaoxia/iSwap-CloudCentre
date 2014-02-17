<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表结构信息</title>
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<!--  关闭当前窗口-->
<script  type="text/javascript">
var DG = frameElement.lhgDG; 
DG.addBtn( 'close', '关闭窗口', singleCloseWin); 
DG.addBtn( 'back', '上一步', returns); 


function returns(){
	//取消按钮
	DG.removeBtn('reset');
	DG.removeBtn('save');
	DG.removeBtn('back');
	DG.removeBtn('close');
	//实现逻辑
	DG.dgWin.history.back(-1);
	DG.reDialogSize(1000,470);
	DG.SetPosition('center','center'); 
}

//关闭窗口 不做任何操作
function singleCloseWin(){
DG.curWin.closeWindow();
}
</script>

</script>
</head>
<body >
	<div class="pop_01" style="width:700px">
   <div class="pop_mian">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle" class="pm01_c"  height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
            <tr>
              <td  height="100%" valign="top" >
               <div class="item1">
                <ul class="item1_c">
                    <li >
                      <p>字段中文名：</p>
                      <span>
                     ${name?default('')}
                      </span>
                    </li>
                     <li class="item_bg">
                      <p>字段代码：</p>
                      <span>
                   ${filedcode?default('')}
                      </span>
                    </li>
                       <li>
                      <p>字段长度：</p>
                      <span>
                      ${filedLength?default('')}
                      </span>
                    </li>
                    <li class="item_bg">
                      <p>数据类型：</p>
                      <span>
                      ${dataType?default('')}
                      </span>
					 </li>
					 <li>
                      <p>是否主键：</p>
                      <span>
                      <#if isPk=='1'>是<#else>否</#if>
                      </span>
					 </li>
					 <li class="item_bg">
                      <p>可否为空：</p>
                      <span>
                      <#if isPk=='1'>是<#else>否</#if>
                      </span>
					 </li>
                  </ul>
			</td>
            </tr>
          </table></td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>