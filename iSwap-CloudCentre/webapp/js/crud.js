//删除本条
function del(url) {
	if (confirm("确定要删除本记录吗？")) {
		document.forms['pageForm'].action = url;
		document.forms['pageForm'].submit();
	}
}

//删除选中
function delMany(url) {
	if($("input[name='ids']:checked ").length==0){
		alert("请选择记录！");
		return;
	}
	if (confirm("确定要删除选中记录吗？")) {
		document.forms['pageForm'].action = url;
		document.forms['pageForm'].submit();
	}
}

//通报本条
function rep(url) {
	if (confirm("确定要通报本记录吗？")) {
		document.forms['pageForm'].action = url;
		document.forms['pageForm'].submit();
	}
}

//通报选中
function repMany(url) {
	if($("input[name='ids']:checked ").length==0){
		alert("请选择记录！");
		return;
	}
	if (confirm("确定要通报选中记录吗？")) {
		document.forms['pageForm'].action = url;
		document.forms['pageForm'].submit();
	}
}

//修改选中状态
function setStatus(url) {
   	document.forms['pageForm'].action = url;
   	document.forms['pageForm'].submit();
}
//提交表单
function subForm(f) {
	document.forms[f].submit();
}
//添加
function add(url) {
	document.forms['pageForm'].action = url;
	document.forms['pageForm'].submit();
}
//修改
function update(url){
	document.forms['pageForm'].action = url;
	document.forms['pageForm'].submit();
}
//提交表单
//重置表单
//function reset(f) {
//  $(':input',"#"+f)
//              .not(':button, :submit, :reset, :hidden')
//              .val('')
//              .removeAttr('selected');
//}
