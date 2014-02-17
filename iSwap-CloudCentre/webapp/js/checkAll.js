function selectAll(box)	{
	var id=document.getElementsByName('ids');
	for(var i=0;i<id.length;i++){
		id[i].checked=box.checked;
	}
}
<!--全选、取消全选-->
function checkedAll(obj,checkboxName ){
	if (obj.innerHTML == '全选'){
		$(".tabs1 tr").css({"background-image":"url(images/tabs_tr.jpg)"});
		$(".tabs3 tr").css({"background-image":"url(images/tabs_tr.jpg)"});
		obj.innerHTML='取消全选';
		selAllCheckbox(checkboxName);
	}else{
		$(".tabs1 tr").css({"background-image":""});
		$(".tabs3 tr").css({"background-image":""});
		obj.innerHTML='全选';
		unselAllCheckbox(checkboxName);
	}
}
<!--全选-->
function selAllCheckbox(checkboxName){
	o = document.getElementsByName(checkboxName);
	for(i = 0; i < o.length; i++){
	  if(o[i].disabled==false){
	  	o[i].checked = true;
	  }
	}
}
<!--取消全选-->
function unselAllCheckbox(checkboxName) {
	o = document.getElementsByName(checkboxName);
	for(i = 0; i < o.length; i++){
		o[i].checked = false;
	}
}
 <!--判断是否有选中的-->
function checks() {
   var id = document.getElementsByName('ids');
   for(var i=0;i<id.length;i++){
    if(id[i].checked){
		return true;  
	 }
    }
     return false;
}