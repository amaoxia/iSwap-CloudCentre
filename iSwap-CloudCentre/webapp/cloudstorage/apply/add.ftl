<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"	type="text/css">
		<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css" type="text/css">
		<style>
		form {
				margin: 0;
			}
			textarea {
				display: block;
			}
			</style>
</head>
<body>
  <div class="main"  style="width:950;height:500px;overflow-x:hidden;overflow-y:scroll;">
    <div class="main_c2" >
      <div class="left_tree_com"> 
      <span id="treeOutSpan">
        <p>
        	 <ul id="tree" class="tree" style="height:435px;width:250px;overflow:auto;"></ul>	
              <!--<iframe src="${path}/cloudstorage/tableinfo/tableinfo!treeMain.action?itemId=${metaData.id?default('')}&deptId=${deptId?default('')}&appId=${appId?default('')}" id="tab" width="250px"  height="390px;" scrolling="Auto" frameborder="0"  style="border:0px" allowtransparency="true"></iframe>-->
        </p>
        </span>
      </div>
      <div class="right_main" >
        <div class="loayt_02 rlist100">
          <div class="rlist2">
            <div class="rlist3_overflow" >
              <form  enctype="multipart/form-data" name="saveForm" id="saveForm" action="${path}/cloudstorage/apply/apply!add.action" method="post">
              <@s.token name="token"/>
              <table class="tabs_list">
                <tr>
                  <td>
                  	 <div class="item1">
		                  <ul class="item1_c">
		                     <li>
		                     <input type="hidden"  value="${itemId}" name="itemId"/>
		                     <input type="hidden" value="${appId?default('')}" name="appId"/>
		                      <#if entityobj?exists><input type="hidden"  value="${entityobj.id}" name="id"/></#if>
		                      <p>申请指标项名称：</p>
		                      <span>
		                      ${metaData.targetName?default('')}
		                      </span>
		                    </li>
		                    <li class="item_bg">
		                      <p>所属部门：</p>
		                      <span>
		                      <#if metaData.sysDept?exists>${metaData.sysDept.deptName?default('')}</#if>
		                      </span>
		                      </li>
		                      <#if dataApp?exists>
		                      <li>
		                      <p>所属应用：</p>
		                      <span>
		                       ${dataApp.appMsg.appName?default('')}
		                      </span>
		                      </li>
		                      </#if>
		                      <li id="contentId" <#if dataApp?exists> class="item_bg"</#if>>
		                      <p>申请函类型：</p>
		                      <span>
		                       <input type="radio" value="0" name="filedApplyType" id="filed_type" checked="chekced" onclick="onChecked(this)" <#if entityobj?exists><#if entityobj.filedApplyType=='0'>chekced</#if><#else>chekced</#if>/>申请函 
		                       <input type="radio" value="1" name="filedApplyType" id="filed_file" onclick="onChecked(this)"  <#if entityobj?exists><#if entityobj.filedApplyType=='1'>chekced</#if></#if>/>文件类型
		                      </span>
		                      </li>
		                       <li class="item_bg">
		                      <p>申请文件：</p>
		                      <span><input  name="upload" type="file" id="upload" size="30" <#if entityobj?exists><#if entityobj.filedApplyType=='0'>disabled</#if><#else>disabled</#if>/></span>
		                      </li>
		                      <li id="textareaId">
		                      <p>申请函：(最大字数1000字)   &nbsp;&nbsp;&nbsp;已输入：&nbsp;&nbsp;<b class="word_count"></b>&nbsp; 字</p>
		                      <input type="hidden" name="filedApplyContent" id="filedApplyContent" value=""/>
		                      <span><textarea   name="content"  id="content"  style="width:560px;height:200px;visibility:hidden;" ><#if entityobj?exists>${entityobj.filedApplyContent?default('')}</#if></textarea></span>
		                      </li>
		                  </ul>
		                </div>
                  </td>
                </tr>
                </tr>
              </table>
              </form>
            </div>
          </div>
        </div>
        <div class=" clear"></div>
      </div>
    </div>
  </div>
  <div class="clear"></div>
  		<script charset="utf-8" src="${path}/js/kindeditor/kindeditor.js" type="text/javascript"></script>
		<script charset="utf-8" src="${path}/js/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
		<script type="text/javascript" src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
					 <#include "/common/commonUd.ftl">
					 <#include "/common/commonValidator.ftl">
					 <script type="text/javascript">
					var zTree;//树
					var setting;//参数设置
					var zTreeNodes = [] ;//数据
				    setting={
				    async : true,//异步加载 
				    asyncUrl: "${path}/cloudstorage/tableinfo/tableinfo!tree.action?itemId=${metaData.id?default('')}&deptId=${deptId?default('')}&appId=${appId?default('')}",//数据文件 
				    showLine:true,
				    checkable:true,
				    isSimpleData:true,
				    treeNodeKey:"id",
				    treeNodeParentKey:"pid"
				    };		
			       function loadTree(){
	           		   zTree=$("#tree").zTree(setting,zTreeNodes);
	    			}
			        $(document).ready(function(){
						loadTree();//载入树
			    });
			    	 //得到选中的节点
	   		 function getSelectedNodes(){
				 var sNode=zTree.getCheckedNodes(true);
				  var ids='';
			     for(i=0;i<sNode.length;i++){
			   		 var id=  $(sNode[i]).attr("id")
			   		 if(id!='-1'){
			   		 	 ids+=$(sNode[i]).attr("id")+",";
			   		 }
			}
				return ids;
	    }
							///创建编辑器
							var editor;
							KindEditor.ready(function(K) {
								editor = K.create('textarea[name="content"]', {
									afterChange : function() {
										K('.word_count').html(""+this.count("text"));
									},
									items : [
										'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
										'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
										'insertunorderedlist']
								});
							});
					    ////////////////////////////
						function onChecked(temp){
								var id=	$(temp).attr("value");
								if(id=='0'){
										var obj = document.getElementById('upload') ;   
										obj.select();   
										document.selection.clear(); 
										$("#upload").attr("disabled",true);
										editor.readonly(false);
								}
								if(id=='1'){
									$("#upload").attr("disabled",false);
									 editor.readonly();
									 editor.html('');
								}
						}
						  function isSub(){
						  		var ck=$(":input[name=filedApplyType][checked=true]").val(); 
						  		 if(ck==0){
						  			 var ht=editor.count("text");
						  		 	if(ht>1000){
						  		 		alert("已超过最大字数限制!");
						  		 		return;
						  		 	}
						     		$("#filedApplyContent").val(editor.html());
						  		 }
						     $("#filedApplyContent").val(editor.html());
						     var ids=getSelectedNodes();
							 if(ids==''||ids.length==0){
								alert("请选择元数据项!");
								return;
							}
							 document.forms[0].action="${path}/cloudstorage/apply/apply!add.action?tids="+ids;
							 document.forms[0].submit();
						}
						//事件
						function reset(){
							var ck=$(":input[name=filedApplyType][value=0]");
							 $(ck).attr("checked",true);
							 zTree.checkAllNodes(false);
							//清空File value
							var obj = document.getElementById('upload') ;   
							obj.select();   
							document.selection.clear(); 
							obj.disabled=true;
							//
							editor.html('');
							//
							/* $(':input',"#saveForm")
				              .not(':button, :submit,:radio,:reset, :hidden')
				              .attr('value','')
				              .removeAttr('selected');*/
						}
		</script>
</body>
</html>

