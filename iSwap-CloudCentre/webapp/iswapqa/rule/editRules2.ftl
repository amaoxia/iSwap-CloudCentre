<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>无标题文档</title>
<link href="${path}/css/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
</head>
<body style="width:100%;height:100%;overflow-x:hidden;overflow-y:scroll;">
<form action="${path}/iswapqa/rule/ruleAction!addRules.action" method="post" name="saveForm" id="saveForm">
<input type="hidden" name="" value=""/>
<div class="pop_div6 pm6_c">
  <div class="div6_tabsr">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
      <tr>
        <td  height="100%" valign="top" ><div id="TabbedPanels1" class="TabbedPanels">
            <ul class="TabbedPanelsTabGroup">
              <li class="TabbedPanelsTab" tabindex="0">设计模式</li>
              <li class="TabbedPanelsTab" tabindex="0">代码模式</li>
            </ul>
            <div class="TabbedPanelsContentGroup">
              <div class="TabbedPanelsContent tag_h" >
                <div class="tag_cont" >
                  <div class="tag_tabs">
                    <ul>
                      <li class="tag_arrl"><a href="#"><img src="${path}/images/tag_arrl.png" align="absbottom" /></a></li>
                      <li class="tag_tabs_select"  id="rule1Class"><a href="javascript:void(0)" onclick="changeTab('rule1');"><b>${ruleName?default("")}</b></a><input type="hidden" name="ruleName" id="ruleName"  value="${ruleName?default("")}"/></li>
                      <li id="rule2Class"><a  href="javascript:void(0)" onclick="changeTab('rule2');"><b>规则2</b></a></li>
                      <li id="rule3Class"><a href="javascript:void(0)" onclick="changeTab('rule3');"><b>规则3</b></a></li>
                      <li id="rule4Class"><a href="javascript:void(0)" onclick="changeTab('rule4');"><b>规则4</b></a></li>
                      <li class="tag_arrr"><a href="#"><img src="${path}/images/tag_arrr.png" /></a></li>
                      <li class="tag_add"><a href="#"><img src="${path}/images/tag_add.png" /></a></li>
                    </ul>
                  </div>
                  <div class="clear"></div>
                    <div class="tag_mainc" id="rule2" style="display:none;" >
                     <iframe id="reportFrame2"  scrolling="no" frameborder="no" border="0" width="100%" height="350px;" 
                      src="${path}/iswapqa/rule/ruleAction!editRuleShow.action"></iframe>
                  </div>
                  
                    <div class="tag_mainc" id="rule3" style="display:none;">
                      <iframe id="reportFrame3"  scrolling="no" frameborder="no" border="0" width="100%" height="350px;" 
                      src=""></iframe>
                   
                  </div>
                   <div class="tag_mainc" id="rule4" style="display:none;">
                      <iframe id="reportFrame4"  scrolling="no" frameborder="no" border="0" width="100%" height="350px;" 
                      src=""></iframe>
                     
                  </div>
                  
                  
                  <div class="tag_mainc" id="rule1"  width="100%">
                    <div class="tab1">
<div class="float_div float_div_float2_pos" id="float2">
  <div class="float_main float_tboder">
    <table width="100%" border="0" class="list_nr4 float_margin">
      <tr>
        <td>条件组
          <label for="select4"></label>
          <select name="select4" id="select4" style="width:150px;">
          </select></td>
      </tr>
    </table>
    <div class="pop_btn_certer ">
      <input name="input" type="button" value="添加"  class=" btn2_s"/> <a href="#" id="casegroupClose" class="link_btn">取消</a>
    </div>
  </div>
</div>
<div class="float_div2 float_div2_pos">
  <div class="float_main2 float_tboder">
    <div class="tag_bg4">
      <div class="tag_L4">自定义条件</div>
      <div class="tag_R"><a href="#"><img src="${path}/images/arrow_down.png" /></a></div>
      <div class="clear"></div>
    </div>
    <div class="tag_nr_c" > 
     <#if attributeMap?exists>
              <#list attributeMap?keys as attrKey>   
    <span>
      <input type="checkbox" name="condition" id="condition" value="${attrKey}|${attributeMap[attrKey]}" onclick="addCondition()" />
      ${attrKey}</span>
           </#list>
              </#if>
       </div>
  
    <div class="tag_bg4">
      <div class="tag_L4">规则公用函数</div>
      <div class="tag_R"><a href="#"><img src="${path}/images/arrow_down.png" /></a></div>
      <div class="clear"></div>
    </div>
    <div class="tag_nr_c" > <span>
      <input type="checkbox" name="checkbox" id="checkbox" />
      fun1<a href="#"><img src="${path}/images/tag_del2.gif" align="absmiddle"  class="tab1_cont_del" /></a></span><span>
    <a href="#" id="functionsAdd"><img src="${path}/images/tag_add3.gif"></a></span>
      </div>
    <div class="taglist">
      <table width="100%" border="0" class="list_nr2">
        <tr>
          <th>函数名</th>
          <td><input type="text" name="funcName" id="funcName" size="70" /></td>
        </tr>
        <tr>
          <th>函数体</th>
          <td><textarea name="funcBody" id="funcBody" cols="69" rows="5"></textarea></td>
        </tr>
      </table>
      <div class="pop_btn_certer">
        <input name="input" type="button" value="创建函数"/> <a href="#" id="functionsAddClose" class="link_btn">取消</a>
      </div>
    </div>
    <input name="input" type="button" value="添加条件" class=" btn2_s" style="padding:0;margin-top:20px;"/> <a href="#" id="caseAddClose" class="link_btn">取消</a>
  </div>
</div>
                      <div class="tab1_title"><b>条件</b><a href="#" id="casegroupAdd"><img src="${path}/images/tag_add.gif" align="absmiddle"  /></a></div>
                      <div class="tab1_subtitle"><div class="subt_name">${ruleObject.cnName?default("")}</div><div class="subt_img"><a href="#"><img src="${path}/images/tag_del3.gif" align="absmiddle" /></a>&nbsp;&nbsp;<a href="#" id="caseAdd"><img src="${path}/images/tag_add3.gif" align="absmiddle" /></a></div></div>
                      <div class="tab1_cont">
                       <input type="hidden" name="fileName" id="fileName"  value="${fileName?default("")}"/>
                      <input type="hidden" name="whenMapVal" id="whenMapVal"  value=""/>
                      <input type="hidden" name="whenMapKey"  value="${ruleObj?default("")}"/>
                       <input type="hidden" name="paramTypeVal" id="paramTypeVal"  value=""/>
                       <input type="hidden" name="ruleFileId"  value="${ruleFileId?default("")}"/>
                       <input type="hidden" name="ruleId"  value="${rule.id?default("")}"/>
                        <input type="hidden" name="applyTo"  value="${applyTo?default("")}"/>
                        <table width="100%" border="0" class="list_nr4" id="conditionTable">
                <#assign i = 0>  
             <#if paramTypeValArr?exists>
              <#list paramTypeValArr as paramTypeVal>   
                <#assign i = i+1>
                          <tr>
                            <th><span value="attrKey">
                             <#if paramTypeVal?split("|")[1]=="number"||paramTypeVal?split("|")[1]=="date">
                             ${paramTypeVal?split("|")[7]}
                              <#else>
                                ${paramTypeVal?split("|")[3]}
           					 </#if>
           					      <#if paramTypeVal?split("|")[1]=="number"||paramTypeVal?split("|")[1]=="date">
                               <input type="hidden" name="objAttr"  value="${paramTypeVal?split("|")[0]}|${paramTypeVal?split("|")[1]}|${paramTypeVal?split("|")[7]}"/>
                              <#else>
                                 <input type="hidden" name="objAttr"  value="${paramTypeVal?split("|")[0]}|${paramTypeVal?split("|")[1]}|${paramTypeVal?split("|")[3]}"/>
           					 </#if>
                            </th>
                            <td>
                             <#if paramTypeVal?split("|")[1]=="boolean">
                            <input type="radio" name="${paramTypeVal?split("|")[0]}Val" id="${paramTypeVal?split("|")[0]}Val" value="true" checked="checked" />
                     		  是
                              <input type="radio" name="${paramTypeVal?split("|")[0]}Val" id="${paramTypeVal?split("|")[0]}Val" value="false" />
                              否<a href="#"><img src="${path}/images/tag_del2.gif" align="absmiddle"  class="tab1_cont_del" onclick="delCondition(this)" /></a>
                               </#if>
                               
                                <#if paramTypeVal?split("|")[1]=="string">
                         <input type="text"  id="${paramTypeVal?split("|")[0]}Val" size="30" value="${paramTypeVal?split("|")[2]}" name="strName" />
                              <a href="#"><img src="${path}/images/tag_del2.gif" align="absmiddle"  class="tab1_cont_del" onclick="delCondition(this)" /></a>
                               </#if>
                               
                                <#if paramTypeVal?split("|")[1]=="number">
                        	<select name="select" id="${paramTypeVal?split("|")[0]}Val1" onchange="checkNum('${paramTypeVal?split("|")[0]}')" name="numDateName">
                        	 		<option value="" <#if paramTypeVal?split("|")[2]=="">selected </#if>>请选择</option>
                        	 	<option value="="  <#if paramTypeVal?split("|")[2]=="==">selected </#if> >=</option>
                        	 	 <option value=">" <#if paramTypeVal?split("|")[2]==">">selected </#if> >&gt;</option>
                        	 	 <option value="<" <#if paramTypeVal?split("|")[2]=="<">selected </#if> >&lt;</option>
                                <option value=">=" <#if paramTypeVal?split("|")[2]==">=">selected </#if> >&gt;=</option>
                                <option value="<=" <#if paramTypeVal?split("|")[2]=="<=">selected </#if> >=&lt;</option>
                              </select>
                              <input type="text" size="13" id="${paramTypeVal?split("|")[0]}Val2" value="${paramTypeVal?split("|")[3]}"/>
                              <select name="select2" id="${paramTypeVal?split("|")[0]}Val3"  onchange="noSelect('${paramTypeVal?split("|")[0]}')">
                              <option value="" <#if paramTypeVal?split("|")[4]=="">selected </#if>>请选择</option>
                                <option value="&&" <#if paramTypeVal?split("|")[4]=="&&">selected </#if>>与(and)</option>
                                 <option value="||" <#if paramTypeVal?split("|")[4]=="||">selected </#if>>或(or)</option>
                                <option value=""  >不选</option>
                              </select>
                              <select name="select3" id="${paramTypeVal?split("|")[0]}Val4">
                               <option value="" <#if paramTypeVal?split("|")[5]=="">selected </#if>>请选择</option>
                               	<option value="=" <#if paramTypeVal?split("|")[5]=="==">selected </#if>>=</option>
                               	 <option value=">" <#if paramTypeVal?split("|")[5]==">">selected </#if>>&gt;</option>
                               	  <option value="<" <#if paramTypeVal?split("|")[5]=="<">selected </#if>>&lt;</option>
                                <option value=">=" <#if paramTypeVal?split("|")[5]==">=">selected </#if>>&gt;=</option>
                                <option value="<=" <#if paramTypeVal?split("|")[5]=="<=">selected </#if>>=&lt;</option>
                              </select>
                              </select>
                              <input type="text" size="13" id="${paramTypeVal?split("|")[0]}Val5" value="${paramTypeVal?split("|")[6]}"/>
                              <a href="#"><img src="${path}/images/tag_add2.gif"  align="absmiddle" /></a><a href="#">
                              <img src="${path}/images/tag_del2.gif" align="absmiddle"  class="tab1_cont_del" onclick="delCondition(this)"  /></a>
                               </#if>
                               
                         <#if paramTypeVal?split("|")[1]=="date">
                        <select name="select" id="${paramTypeVal?split("|")[0]}Val1" onchange="checkNum('${paramTypeVal?split("|")[0]}')" name="numDateName">
                                <option value="" <#if paramTypeVal?split("|")[2]=="">selected </#if>>请选择</option>
                        	 	<option value="="  <#if paramTypeVal?split("|")[2]=="=">selected </#if> >=</option>
                        	 	 <option value=">" <#if paramTypeVal?split("|")[2]==">">selected </#if> >&gt;</option>
                        	 	 <option value="<" <#if paramTypeVal?split("|")[2]=="<">selected </#if> >&lt;</option>
                                <option value=">=" <#if paramTypeVal?split("|")[2]==">=">selected </#if> >&gt;=</option>
                                <option value="<=" <#if paramTypeVal?split("|")[2]=="<=">selected </#if> >=&lt;</option>
                              </select>
                              <input type="text" size="13" id="${paramTypeVal?split("|")[0]}Val2" value="${paramTypeVal?split("|")[3]}"/>
                              <select name="select2"  id="${paramTypeVal?split("|")[0]}Val3" onchange="noSelect('${paramTypeVal?split("|")[0]}')">
                              <option value="" <#if paramTypeVal?split("|")[3]=="">selected </#if>>请选择</option>
                                <option value="&&" <#if paramTypeVal?split("|")[3]=="&&">selected </#if>>与(and)</option>
                                 <option value="||" <#if paramTypeVal?split("|")[3]=="||">selected </#if>>或(or)</option>
                                <option value=""  >不选</option>
                              </select>
                              <select name="select3" id="${paramTypeVal?split("|")[0]}Val4">
                              <option value="" <#if paramTypeVal?split("|")[4]=="">selected </#if>>请选择</option>
                               	<option value="=" <#if paramTypeVal?split("|")[4]=="=">selected </#if>>=</option>
                               	 <option value=">" <#if paramTypeVal?split("|")[4]==">">selected </#if>>&gt;</option>
                               	  <option value="<" <#if paramTypeVal?split("|")[4]=="<">selected </#if>>&lt;</option>
                                <option value=">=" <#if paramTypeVal?split("|")[4]==">=">selected </#if>>&gt;=</option>
                                <option value="<=" <#if paramTypeVal?split("|")[4]=="<=">selected </#if>>=&lt;</option>
                              </select>
                              <input type="text" size="13" id="${paramTypeVal?split("|")[0]}Val5"  value="${paramTypeVal?split("|")[5]}" />
                              <a href="#"><img src="${path}/images/tag_add2.gif"  align="absmiddle" /></a><a href="#">
                              <img src="${path}/images/tag_del2.gif" align="absmiddle"  class="tab1_cont_del" onclick="delCondition(this)"  /></a>
                               </#if>
                             </td>
                          </tr>
                 </#list>
              </#if>
                        </table>
                      </div>
                    </div>
                    <div class="tab1">
                      <div class="tab1_title"><b>行为</b><a href="#" id="behaviorAdd"><img src="${path}/images/tag_add.gif" align="absmiddle"  /></a>
								<div class="float_div float_div_float1_pos" id="float1">
  						<div class="float_main float_tboder">
    					<table width="100%" border="0" class="list_nr4 float_margin">
      						 <tr>
       						 <td>对象
         						 <label for="select4"></label>
						          <select name="ruleObj" id="ruleObj" style="width:150px;">
						        <#if objList?exists>
          				 <#list objList as obj>   
          				<option value="${obj.enName}|${obj.cnName}">${obj.cnName}</option>
           				</#list>
         				 </#if>
          						</select></td>
						 </tr>
						    </table>
						    <div class="pop_btn_certer ">
						      <input name="input" type="button" value="添加"  onclick="addRulesObj()"  class=" btn2_s"/> <a href="#" id="behaviorAddClose" class="link_btn">取消</a>
						    </div>
						  </div>
						</div>
                      </a></div>
                      <div class="tab1_cont">
                        <table width="100%" border="0" id="behavior" class="list_nr4">
                        <input type="hidden" name="thenMapVal" id="thenMapVal"  value=""/>
                         <!-- <tr>
                            <th width="21%">$${ruleName?default("")}&nbsp;&nbsp;</th>
                            <td width="79%">
                              <select name="objBehavior" id="objBehavior" onchange="changeVehaviorValue(this.value)">
                               <option value="" >-请选择-</option>
                                <option value="approve" >approve</option>
                                <option value="update" >update</option>
                              </select>
                              &nbsp;&nbsp;
                              <span id="behaviorSpan"></span>
                              <a href="#"><img src="${path}/images/tag_del2.gif" align="absmiddle"  class="tab1_cont_del" /></a>
                              </td>
                          </tr>-->
                        </table>
                      </div>
                    </div>

                    <div class="tab1">
                      <div class="tab1_title"><b>选项</b><a href="#" id="optionsAdd"><img src="${path}/images/tag_add.gif" align="absmiddle"  /></a></div>
                      <div class="tab1_cont">
                        <table width="100%" border="0" class="list_nr4" id="optionsAddTable">
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
                                  

              <div class="TabbedPanelsContent tag_h">
                <div class="tag_bg3">
                  <div class="tag_L3"></div>
                  <div class="tag_R"></div>
                  <div class="clear"></div>
                </div>
                <div class="tag_nr3" style="height:340px;">
                  <div class="tag_nr_c" >  </div>
                  <textarea name="" cols="" rows="23" style="width:640px; margin-left:4px;">${ruleFileStr}</textarea>
                </div>
              </div>
            </div>
          </div>
        <!--  <div class="btn_certer">
            <input name="input" type="button" value="上一页" onclick="javascript:history.go(-1);" class=" btn2_s"/>
            &nbsp;&nbsp;
            <input name="input" type="button" value="保存" onclick="addRules()"  class=" btn2_s"/>
            &nbsp;              &nbsp; <a href="#" class="link_btn">关闭窗口</a> </div>--></td>
      </tr>
    </table>
  </div>
  <div class="clear"></div>
</div>
 <div class="float_div3 float_div3_pos">
  <div class="float_main3 float_tboder">
    <div class="tag_bg4">
      <div class="tag_L4">选项</div>
      <div class="tag_R"><a href="#"><img src="${path}/images/arrow_down.png" /></a></div>
      <div class="clear"></div>
    </div>
    <div class="tag_nr_c" > <span>
      <input type="checkbox" name="options" id="checkbox" value="salience|优先级" <#if ruleAttribute.indexOf('salience')!= -1>checked</#if>/>
        优先级</span><span>
      <input type="checkbox" name="options" id="checkbox" value="lock_on_active|避免再次执行"  />
   避免再次执行</span><span>
   <input type="checkbox" name="options" id="checkbox" value="no_loop|是否二次执行" />
      是否二次执行</span>
    <input type="checkbox" name="options" id="checkbox" value="date_effective|小于系统时间执行" />
     小于系统时间执行</span>
     <input type="checkbox" name="options" id="checkbox" value="date_expires|大于系统时间执行" />
    大于系统时间执行</span>
     <input type="checkbox" name="options" id="checkbox" value="enabled|规则是否可用" />
   规则是否可用</span>
   <input type="checkbox" name="options" id="checkbox" value="dialect|规则语言类型" />
   规则语言类型</span>duration
   <input type="checkbox" name="options" id="checkbox" value="duration|重置线程" />
  重置线程</span>
  <input type="checkbox" name="options" id="checkbox" value="activation_group|规则分组" />
  规则分组</span>
     </div>
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
    <input name="input" type="button" value="添加选项" onclick="addOptions()" class=" btn2_s" style="padding:0;margin-top:20px;"/> <a href="#" id="optionsAddClose" class="link_btn">取消</a>
  </div>
</div>
<form>
<script type="text/javascript">
showRule();
//初始行为
function showRule(){
	var ruleObjTable = document.getElementById("behavior");
	ruleObjTable.insertRow(); //插入行
	ruleObjTable.rows[ruleObjTable.rows.length-1].insertCell(0)// 插入列
	 ruleObjTable.rows[ruleObjTable.rows.length-1].cells[0].width="21%";
	 ruleObjTable.rows[ruleObjTable.rows.length-1].cells[0].innerText= "    "+"${ruleObject.cnName}";
	 ruleObjTable.rows[ruleObjTable.rows.length-1].insertCell(1);//行为选择Id=对象名，行为对话框id=对象名+Span
	 ruleObjTable.rows[ruleObjTable.rows.length-1].cells[1].width="79%";
	 ruleObjTable.rows[ruleObjTable.rows.length-1].cells[1].innerHTML= " <input type=\"hidden\" name=\"thenObj\" id=\"thenObj\"  value=\""+"${ruleObject.enName}"+"\"/><input type=\"hidden\" name=\"imports\" id=\"imports\"  value=\"${ruleFile.imports}\"/><select name=\"thenObjBehavior\" id=\"then"+"${ruleObject.enName}"+"Behavior\" onchange=\"changeBehaviorValue(this.value,this.id)\">"+
	 "  <option value=\"\" >-请选择-</option>"
	   <#if methodList?exists>
           <#list methodList as meth>   
       + "<option value=\"${meth.enName}\" <#if thenMapVal=="${meth.enName}">selected </#if>>${meth.cnName}</option>"
           </#list>
          </#if>
	 
	 +"</select> &nbsp;&nbsp; <span id=\"then"+"${ruleObject.enName}"+"BehaviorSpan\"></span>"+
	 " <a href=\"#\"><img src=\"${path}/images/tag_del2.gif\" align=\"absmiddle\" onclick=\"delBehavior(this)\"  class=\"tab1_cont_del\" /></a>";

var ui = document.getElementById("then"+"${ruleObject.enName}"+"Behavior"+"Span");
    ui.innerHTML="<input type=\"text\" id=\""+"then"+"${ruleObject.enName}"+"Behavior"+"V\" name=\"thenBehaviorVal\" value=\"${thenVal}\" size=\"20\" />";

var optionsAddTable = document.getElementById("optionsAddTable");
	var tabLength = optionsAddTable.rows.length;
	for(var i=0;i<tabLength;i++){
		optionsAddTable.deleteRow(0);
	}
	var options = document.getElementsByName("options");
	
	for(var i=0;i<options.length;i++){
	if(options[i].checked==true){
		optionsAddTable.insertRow();
		optionsAddTable.rows[optionsAddTable.rows.length-1].insertCell(0);// 插入列
		optionsAddTable.rows[optionsAddTable.rows.length-1].cells[0].width="21%";
		optionsAddTable.rows[optionsAddTable.rows.length-1].cells[0].innerText= "    "+options[i].value.split("|")[1]+"";
		optionsAddTable.rows[optionsAddTable.rows.length-1].insertCell(1)// 插入列
		optionsAddTable.rows[optionsAddTable.rows.length-1].cells[1].width="24%";
		if(options[i].value.split("|")[0]=="lock_on_active"||options[i].value.split("|")[0]=="no_loop"||options[i].value.split("|")[0]=="date_effective"||options[i].value.split("|")[0]=="enabled"){
			optionsAddTable.rows[optionsAddTable.rows.length-1].cells[1].innerHTML= "<input name=\""+options[i].value.split("|")[0]+"Val\" type=\"checkbox\" value=\"\" />";
		}else{
			optionsAddTable.rows[optionsAddTable.rows.length-1].cells[1].innerHTML= "<input type=\"text\"  name=\""+options[i].value.split("|")[0]+"Val\" value=\"1\" id=\"optionVal\" size=\"30\" />";
		}
		optionsAddTable.rows[optionsAddTable.rows.length-1].insertCell(2);// 插入列
		optionsAddTable.rows[optionsAddTable.rows.length-1].cells[2].width="55%";
		optionsAddTable.rows[optionsAddTable.rows.length-1].cells[2].innerHTML= "<a href=\"#\" onclick=\"delOption(this)\"><img src=\"${path}/images/tag_del2.gif\" align=\"left\"  class=\"tab1_cont_del\" /></a>";
		optionsAddTable.rows[optionsAddTable.rows.length-1].insertCell(3);// 插入列
		optionsAddTable.rows[optionsAddTable.rows.length-1].cells[3].innerHTML="<input type=\"hidden\" name=\""+options[i].value.split("|")[0]+"\" id=\"thenObj\" id=\"optionVal\"  value=\""+options[i].value.split("|")[0]+"\"/>";
	}
	}
}
//添加行为
function addRulesObj(){
	var ruleObj = document.getElementById("ruleObj").value.split("|");
	var ruleObjTable = document.getElementById("behavior");
	
	ruleObjTable.insertRow(); //插入行
	 
	 ruleObjTable.rows[ruleObjTable.rows.length-1].insertCell(0)// 插入列
	 ruleObjTable.rows[ruleObjTable.rows.length-1].cells[0].width="21%";
	 ruleObjTable.rows[ruleObjTable.rows.length-1].cells[0].innerText= "    "+ruleObj[1];
	 ruleObjTable.rows[ruleObjTable.rows.length-1].insertCell(1);//行为选择Id=对象名，行为对话框id=对象名+Span
	 ruleObjTable.rows[ruleObjTable.rows.length-1].cells[1].width="79%";
	 ruleObjTable.rows[ruleObjTable.rows.length-1].cells[1].innerHTML= " <input type=\"hidden\" name=\"thenObj\" id=\"thenObj\"  value=\""+ruleObj[0]+"\"/><select name=\"thenObjBehavior\" id=\"then"+ruleObj[0]+"Behavior\" onchange=\"changeBehaviorValue(this.value,this.id)\">"+
	 "  <option value=\"\" >-请选择-</option>"
	   <#if methodList?exists>
           <#list methodList as meth>   
       + "<option value=\"${meth.enName}\">${meth.cnName}</option>"
           </#list>
          </#if>
	 +"</select> &nbsp;&nbsp; <span id=\"then"+ruleObj[0]+"BehaviorSpan\"></span>"+
	 " <a href=\"#\"><img src=\"${path}/images/tag_del2.gif\" align=\"absmiddle\" onclick=\"delBehavior(this)\"  class=\"tab1_cont_del\" /></a>";
}
//改变行为值
function changeBehaviorValue(value,id){
	var ui = document.getElementById(id+"Span");
    ui.innerHTML="<input type=\"text\" id=\""+id+"V\" value=\"\" size=\"20\" name=\"thenBehaviorVal\" display=\"none\"/>";
}
//删除行为
function delBehavior(obj){
var ruleObjTable = document.getElementById("behavior");
ruleObjTable.deleteRow(obj.parentElement.parentElement.rowIndex);
}

//保存规则
function addRules(){
//设置条件
 var whenMapVal = "";
 var paramTypeVal ="";
 var objAttr = document.getElementsByName("objAttr");//页面对象属性数组
 for(var i=0;i<objAttr.length;i++){
 	var obcnName = objAttr[i].value.split("|")[2];
	if(objAttr[i].value.split("|")[1]=="boolean"){
		var obAttr = objAttr[i].value.split("|")[0];
		var obtyp = objAttr[i].value.split("|")[1];
		var bArr = document.getElementsByName(obAttr+"Val");
		for(var i=0;i<bArr.length;i++){
			if(bArr[i].checked==true){
				whenMapVal=whenMapVal+obAttr+"=="+bArr[i].value+",";
				//alert(obAttr+"=="+bArr[i].value+",");
				paramTypeVal=paramTypeVal+obAttr+"|"+obtyp+"|"+bArr[i].value+"|"+obcnName+";";
			}
		}
	}
	if(objAttr[i].value.split("|")[1]=="string"){
		whenMapVal=whenMapVal+objAttr[i].value.split("|")[0]+"=='"+document.getElementById(objAttr[i].value.split("|")[0]+"Val").value+"',";
		paramTypeVal=paramTypeVal+objAttr[i].value.split("|")[0]+"|"+objAttr[i].value.split("|")[1]+"|"+document.getElementById(objAttr[i].value.split("|")[0]+"Val").value+"|"+obcnName+";";
	}
	if(objAttr[i].value.split("|")[1]=="number"){
	 	//alert("number");
		whenMapVal=whenMapVal+objAttr[i].value.split("|")[0]+document.getElementById(objAttr[i].value.split("|")[0]+"Val1").value+
		document.getElementById(objAttr[i].value.split("|")[0]+"Val2").value+document.getElementById(objAttr[i].value.split("|")[0]+"Val3").value+
		document.getElementById(objAttr[i].value.split("|")[0]+"Val4").value+document.getElementById(objAttr[i].value.split("|")[0]+"Val5").value+",";
		paramTypeVal=paramTypeVal+objAttr[i].value.split("|")[0]+"|"+objAttr[i].value.split("|")[1]+"|"+document.getElementById(objAttr[i].value.split("|")[0]+"Val1").value+"|"+
		document.getElementById(objAttr[i].value.split("|")[0]+"Val2").value+"|"+document.getElementById(objAttr[i].value.split("|")[0]+"Val3").value+"|"+
		document.getElementById(objAttr[i].value.split("|")[0]+"Val4").value+"|"+document.getElementById(objAttr[i].value.split("|")[0]+"Val5").value+"|"+obcnName+";";
	}
	if(objAttr[i].value.split("|")[1]=="date"){
	
		whenMapVal=whenMapVal+objAttr[i].value.split("|")[0]+document.getElementById(objAttr[i].value.split("|")[0]+"Val1").value+
		document.getElementById(objAttr[i].value.split("|")[0]+"Val2").value+document.getElementById(objAttr[i].value.split("|")[0]+"Val3").value+
		document.getElementById(objAttr[i].value.split("|")[0]+"Val4").value+document.getElementById(objAttr[i].value.split("|")[0]+"Val5").value+",";
		paramTypeVal=paramTypeVal+objAttr[i].value.split("|")[0]+"|"+objAttr[i].value.split("|")[1]+"|"+document.getElementById(objAttr[i].value.split("|")[0]+"Val1").value+"|"+
		document.getElementById(objAttr[i].value.split("|")[0]+"Val2").value+"|"+document.getElementById(objAttr[i].value.split("|")[0]+"Val3").value+"|"+
		document.getElementById(objAttr[i].value.split("|")[0]+"Val4").value+"|"+document.getElementById(objAttr[i].value.split("|")[0]+"Val5").value+"|"+obcnName+";";
	}
 }
 	whenMapVal=whenMapVal+"";
 	whenMapVal=whenMapVal.substring(0,whenMapVal.length-1)+"";
 	//alert(whenMapVal);
 	//alert(paramTypeVal);
 	document.getElementById("whenMapVal").value=whenMapVal;
 	document.getElementById("paramTypeVal").value=paramTypeVal;
 //设置行为	
 	var thenObjs = document.getElementsByName("thenObj");
 	var thenObj;
 	var thenMapVal="" ;
 	for(var i=0;i<thenObjs.length;i++){
		thenObj=thenObjs[i];
		if(document.getElementById("then"+thenObj.value+"Behavior")!=null){
		thenMapVal=thenMapVal+"$"+thenObj.value+"."+document.getElementById("then"+thenObj.value+"Behavior").value+"('"+document.getElementById("then"+thenObj.value+"BehaviorV").value+"');";
		thenMapVal=thenMapVal+"update("+"$"+thenObj.value+");";
		}
	}
	
 	//alert(thenMapVal);
 	document.getElementById("thenMapVal").value=thenMapVal+"list.add('测试成功');";
	document.forms['saveForm'].submit();
	var dg = frameElement.lhgDG;
	alert("保存成功！");
	dg.cancel();
	dg.curWin.reloadPage();
}
//删除条件
function delCondition(obj){
var attrTable = document.getElementById("conditionTable");
attrTable.deleteRow(obj.parentElement.parentElement.rowIndex);
}
//添加选项
function addOptions(){
	var optionsAddTable = document.getElementById("optionsAddTable");
	var tabLength = optionsAddTable.rows.length;
	for(var i=0;i<tabLength;i++){
		optionsAddTable.deleteRow(0);
	}
	var options = document.getElementsByName("options");
	
	for(var i=0;i<options.length;i++){
	if(options[i].checked==true){
		optionsAddTable.insertRow();
		optionsAddTable.rows[optionsAddTable.rows.length-1].insertCell(0);// 插入列
		optionsAddTable.rows[optionsAddTable.rows.length-1].cells[0].width="21%";
		optionsAddTable.rows[optionsAddTable.rows.length-1].cells[0].innerText= "    "+options[i].value.split("|")[1]+"";
		optionsAddTable.rows[optionsAddTable.rows.length-1].insertCell(1)// 插入列
		optionsAddTable.rows[optionsAddTable.rows.length-1].cells[1].width="24%";
		if(options[i].value.split("|")[0]=="lock_on_active"||options[i].value.split("|")[0]=="no_loop"||options[i].value.split("|")[0]=="date_effective"||options[i].value.split("|")[0]=="enabled"){
			optionsAddTable.rows[optionsAddTable.rows.length-1].cells[1].innerHTML= "<input name=\""+options[i].value.split("|")[0]+"Val\" type=\"checkbox\" value=\"\" />";
		}else{
			optionsAddTable.rows[optionsAddTable.rows.length-1].cells[1].innerHTML= "<input type=\"text\"  name=\""+options[i].value.split("|")[0]+"Val\" value=\"1\" id=\"optionVal\" size=\"30\" />";
		}
		optionsAddTable.rows[optionsAddTable.rows.length-1].insertCell(2);// 插入列
		optionsAddTable.rows[optionsAddTable.rows.length-1].cells[2].width="55%";
		optionsAddTable.rows[optionsAddTable.rows.length-1].cells[2].innerHTML= "<a href=\"#\" onclick=\"delOption(this)\"><img src=\"${path}/images/tag_del2.gif\" align=\"left\"  class=\"tab1_cont_del\" /></a>";
		optionsAddTable.rows[optionsAddTable.rows.length-1].insertCell(3);// 插入列
		optionsAddTable.rows[optionsAddTable.rows.length-1].cells[3].innerHTML="<input type=\"hidden\" name=\""+options[i].value.split("|")[0]+"\" id=\"thenObj\"  value=\""+options[i].value.split("|")[0]+"\"/>";
	}
	}
}
//删除条件
function delOption(obj){
var optionsAddTable = document.getElementById("optionsAddTable");
optionsAddTable.deleteRow(obj.parentElement.parentElement.rowIndex);
}

//添加条件
function addCondition(){
	var conditionTable = document.getElementById("conditionTable");
	var tabLength = conditionTable.rows.length;
	for(var i=0;i<tabLength;i++){
		conditionTable.deleteRow(0);
	}
	var conditions = document.getElementsByName("condition");
	var condition ;
	for(var i=0;i<conditions.length;i++){
	if(conditions[i].checked==true){
		condition = conditions[i].value.split("|");
		conditionTable.insertRow();//插入行
		conditionTable.rows[conditionTable.rows.length-1].insertCell(0);// 插入列
		conditionTable.rows[conditionTable.rows.length-1].cells[0].width="21%";
		conditionTable.rows[conditionTable.rows.length-1].cells[0].innerHTML= "    "+condition[0]+"<input type=\"hidden\" name=\"objAttr\"  value=\""+condition[0]+"|"+condition[1]+"\"/>";
		conditionTable.rows[conditionTable.rows.length-1].insertCell(1)// 插入列
		conditionTable.rows[conditionTable.rows.length-1].cells[1].width="79%";
		if(condition[1]=="boolean"){
			conditionTable.rows[conditionTable.rows.length-1].cells[1].innerHTML= "<input type=\"radio\" name=\""+condition[0]+"Val\" id=\""+condition[0]+"Val\" value=\"true\" checked=\"checked\" />"
                     	+"是<input type=\"radio\" name=\""+condition[0]+"Val\" id=\""+condition[0]+"Val\" value=\"false\" />"
                      +"否<a href=\"#\"><img src=\"${path}/images/tag_del2.gif\" align=\"absmiddle\"  class=\"tab1_cont_del\" onclick=\"delCondition(this)\" /></a>";
		}else if(condition[1]=="string"){
			conditionTable.rows[conditionTable.rows.length-1].cells[1].innerHTML= "<input type=\"text\"  id=\""+condition[0]+"Val\" size=\"30\" />"
                       +"<a href=\"#\"><img src=\"${path}/images/tag_del2.gif\" align=\"absmiddle\"  class=\"tab1_cont_del\" onclick=\"delCondition(this)\" /></a>";
		}else if(condition[1]=="date"){
			conditionTable.rows[conditionTable.rows.length-1].cells[1].innerHTML= "<select name=\"select\" id=\""+condition[0]+"Val1\">"
                        	 	+"<option value=\"\">请选择</option><option value=\"=\">=</option><option value=\">\">&gt;</option> <option value=\"<\">&lt;</option> <option value=\">=\">&gt;=</option> <option value=\"<=\">=&lt;</option>"
                               +"</select><input type=\"text\" size=\"13\" id=\""+condition[0]+"Val2\"/>"
                             +" <select name=\"select2\" id=\""+condition[0]+"Val3\" >"
                              +" <option value=\"\">请选择</option>  <option value=\"&&\">与(and)</option>  <option value=\"||\">或(or)</option> <option value=\"\">不选</option> </select>"
                             +" <select name=\"select3\" id=\""+condition[0]+"Val4\">"
                               	+" <option value=\"\">请选择</option><option value=\"=\">=</option><option value=\">\">&gt;</option> <option value=\"<\">&lt;</option> <option value=\">=\">&gt;=</option> <option value=\"<=\">=&lt;</option>"
                            +"    </select>"
                           +"   <input type=\"text\" size=\"13\" id=\""+condition[0]+"Val5\"/>"
                            +"  <a href=\"#\"><img src=\"${path}/images/tag_add2.gif\"  align=\"absmiddle\" /></a><a href=\"#\">"
                            +"  <img src=\"${path}/images/tag_del2.gif\" align=\"absmiddle\"  class=\"tab1_cont_del\" onclick=\"delCondition(this)\"  /></a>";
		}else if(condition[1]=="number"){
			conditionTable.rows[conditionTable.rows.length-1].cells[1].innerHTML= "<select name=\"select\" id=\""+condition[0]+"Val1\">"
                        	 	+"<option value=\"\">请选择</option><option value=\"=\">=</option><option value=\">\">&gt;</option> <option value=\"<\">&lt;</option> <option value=\">=\">&gt;=</option> <option value=\"<=\">=&lt;</option>"
                               +"</select><input type=\"text\" size=\"13\" id=\""+condition[0]+"Val2\"/>"
                             +" <select name=\"select2\" id=\""+condition[0]+"Val3\">"
                              +" <option value=\"\">请选择</option> <option value=\"&&\">与(and)</option>  <option value=\"||\">或(or)</option> <option value=\"\">不选</option></select>"
                             +" <select name=\"select3\" id=\""+condition[0]+"Val4\">"
                               	+"<option value=\"\">请选择</option><option value=\"=\">=</option><option value=\">\">&gt;</option> <option value=\"<\">&lt;</option> <option value=\">=\">&gt;=</option> <option value=\"<=\">=&lt;</option>"
                            +"   </select>"
                           +"   <input type=\"text\" size=\"13\" id=\""+condition[0]+"Val5\"/>"
                            +"  <a href=\"#\"><img src=\"${path}/images/tag_add2.gif\"  align=\"absmiddle\" /></a><a href=\"#\">"
                            +"  <img src=\"${path}/images/tag_del2.gif\" align=\"absmiddle\"  class=\"tab1_cont_del\" onclick=\"delCondition(this)\"  /></a>";
		}
	}
	}
}


var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
$(document).ready(function(){
	$("#behaviorAdd,#behaviorAddClose").bind('click', function(event) {
		$("#float1").slideToggle("fast");
		return false;
	});
	$("#casegroupAdd,#casegroupClose").bind('click', function(event) {
		$("#float2").slideToggle("fast");
		return false;
	});
	$("#caseAdd,#caseAddClose").bind('click', function(event) {
		$(".float_div2").slideToggle("fast");
		return false;
	});
	$("#functionsAdd,#functionsAddClose").bind('click', function(event) {
		$(".taglist").slideToggle("fast");
		return false;
	});
	$("#optionsAdd,#optionsAddClose").bind('click', function(event) {
		$(".float_div3").slideToggle("fast");
		return false;
	});
	
});
</script>
<script src="${path}/js/SpryTabbedPanels.js" type="text/javascript"></script>
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
<!-- 公用js -->
<script type="text/javascript" src="${path}/js/iswap_common.js"></script>	
<!-- 重置表单-->
<script type="text/javascript" src="${path}/js/reset.js"></script>	
<!-- 验证文本域长度-->
<script type='text/javascript' src="${path}/js/areaLen.js"></script>
<!--日期控件-->
<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
<script type="text/javascript">
var DG1 = frameElement.lhgDG; 
DG1.addBtn( 'reset', '上一步', resetWin); 
function resetWin() {
	javascript:history.go(-1);
}
DG1.addBtn( 'save', '保存', saveWin); 
function saveWin() {
	//实现逻辑
		if (saveChecked()) {
			addRules();
		}
}
function noSelect(id){
var NumVal = document.getElementById(id+"Val3").value;
if(NumVal==""){
	document.getElementById(id+"Val3").style.display="none";
	document.getElementById(id+"Val4").style.display="none";
	document.getElementById(id+"Val5").style.display="none";
}
}
function saveChecked(){
var flag2 = "true";
var str2 = "";

var strVal = document.getElementsByName("strName");

var numDateVal= document.getElementsByName("numDateName");
var thenObjBehaviorVal= document.getElementsByName("thenObjBehavior");
var thenBehaviorVal= document.getElementsByName("thenBehaviorVal");
var optionVal = document.getElementById("optionVal");


//验证字符串
for(var i=0;i<strVal.length;i++){
	if(strVal[i].value==""||strVal[i].value==null){
		flag2 = "false";
		str2 = "条件未填写完整，请输入值!";
		i=strVal.length;
	}
}

//验证num date
for(var i=0;i<numDateVal.length;i++){
	if(numDateVal[i].value==""||numDateVal[i].value==null){
		flag2 = "false";
		str2 = "条件未填写完整，请输入值!";
		i=strVal.length;
	}
	
}
//验证行为
if(thenObjBehaviorVal.length==0){
		flag2 = "false";
		if(str2==""){
			str2 = "请添加行为!";
		}
}

for(var i=0;i<thenObjBehaviorVal.length;i++){
	if(thenObjBehaviorVal[i].value==""||thenObjBehaviorVal[i].value==null){
		flag2 = "false";
		str2 = "行为未填写完整，请输入值!";
		i=strVal.length;
	}
}
for(var i=0;i<thenBehaviorVal.length;i++){
	if(thenBehaviorVal[i].value==""||thenBehaviorVal[i].value==null){
		flag2 = "false";
		str2 = "行为值未填写完整，请输入值!";
		i=strVal.length;
	}
}

if(optionVal==""||optionVal==null){
		flag2 = "false";
		if(str2==""){
			str2 = "选项未填写完整，请输入值!“优先级”必选!";
		}
}
  if(flag2 == "false"){
    	alert(str2);
    	return false;
    }
return true;
}	
function changeTab(ruleNo){
	if(ruleNo=="rule1"){
		document.getElementById(ruleNo).style.display = 'block';
		document.getElementById(ruleNo+"Class").className ="tag_tabs_select";
		document.getElementById("rule2").style.display = 'none';
		document.getElementById("rule2Class").className ="";
		document.getElementById("rule3").style.display = 'none';
		document.getElementById("rule3Class").className ="";
		document.getElementById("rule4").style.display = 'none';
		document.getElementById("rule4Class").className ="";
	}
	if(ruleNo=="rule2"){
		document.getElementById(ruleNo).style.display = 'block';
		document.getElementById(ruleNo+"Class").className ="tag_tabs_select";
		document.getElementById("rule1").style.display = 'none';
		document.getElementById("rule1Class").className ="";
		document.getElementById("rule3").style.display = 'none';
		document.getElementById("rule3Class").className ="";
		document.getElementById("rule4").style.display = 'none';
		document.getElementById("rule4Class").className ="";
	}
	if(ruleNo=="rule3"){
		document.getElementById(ruleNo).style.display = 'block';
		document.getElementById(ruleNo+"Class").className ="tag_tabs_select";
		document.getElementById("rule1").style.display = 'none';
		document.getElementById("rule1Class").className ="";
		document.getElementById("rule2").style.display = 'none';
		document.getElementById("rule2Class").className ="";
		document.getElementById("rule4").style.display = 'none';
		document.getElementById("rule4Class").className ="";
	}
	if(ruleNo=="rule4"){
		document.getElementById(ruleNo).style.display = 'block';
		document.getElementById(ruleNo+"Class").className ="tag_tabs_select";
		document.getElementById("rule1").style.display = 'none';
		document.getElementById("rule1Class").className ="";
		document.getElementById("rule2").style.display = 'none';
		document.getElementById("rule2Class").className ="";
		document.getElementById("rule3").style.display = 'none';
		document.getElementById("rule3Class").className ="";
	}
}	
</script>
</body>
</html>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               