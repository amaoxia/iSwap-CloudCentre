<#--分页-->
<#macro pageTag formName="pageForm" showHidden="true"> 

		<#if showHidden=="true">
			<input type="hidden" name="page.total" value="${page.total}"/>
			<input type="hidden" name="page.perPage" id="perPage" value="${page.perPage}"/>
			<input id="pageNo" type="hidden" name="page.index" value="${page.index}"/>
			<#list sortParas as sort>
				<input type="hidden" name="orders[${sort.property},,]" value="${sort.order}"/>
				<input type="hidden" id="orderBy" value="${sort.property}"/>
			</#list>
		</#if>
		
		<div class="page_s">
			共<span class="page_fbr">${page.total}</span>条记录&nbsp;&nbsp;&nbsp;
			<span class="page_fbg">${page.index}</span>/<span class="page_fbr">${page.pageCount}</span>页&nbsp;&nbsp;&nbsp;
			<a href="javascript:chanagePageNO(1)">首页</a>
			<a href="javascript:chanagePageNO(${page.upNo})">上一页</a>
			<a href="javascript:chanagePageNO(${page.nextNo})">下一页</a>
			<a href="javascript:chanagePageNO(${page.pageCount})">末页</a>&nbsp;&nbsp;&nbsp;
			每页显示
            <select name="pageSize" id="pageSize" onchange="chanagePageSize(this.value);">
              <option value="10" <#if page.perPage==10>selected</#if> >10</option>
              <option value="20" <#if page.perPage==20>selected</#if> >20</option>
              <option value="50" <#if page.perPage==50>selected</#if> >50</option>
              <option value="100" <#if page.perPage==100>selected</#if> >100</option>
              <option value="200" <#if page.perPage==200>selected</#if> >200</option>
            </select>
              条
            &nbsp;&nbsp;&nbsp;
              转到
            <select id="selectpageNO" onchange="chanagePageNO(this.value);">
            	<#list page.pageList as pNo> 
              		<option value="${pNo}" <#if pNo==page.index?string>selected</#if> >${pNo}</option>
              	</#list>
            </select>
              页 
       </div>	

<#if showHidden=="true">
<script type="text/javascript">
//换页
function chanagePageNO(pageIndex){
	var pageNo = document.getElementById("pageNo");
	pageNo.value = pageIndex;
	document.forms['${formName}'].submit();
}
//改变页大小 
function chanagePageSize(pageSize){
	var perPage = document.getElementById("perPage");
	perPage.value = pageSize; 
    // 写入cookie
    setCookie("pageSize",pageSize);
	document.forms['${formName}'].submit();
}

function setOrder(e){
	var ename=e.name;
	orderby(ename);
}
//排序
function orderby(title){
	var pageform=document.forms['${formName}'];
	var order = $("input[name*='orders']");
	var orderValue = order.val();
	if(orderValue=="desc"){
		order.val("asc");
	}else{
	 	order.val("desc");
	}
	order.attr("name","orders["+title+",,]");
	pageform.submit();
}

//在document加载中,需要设置排序的图片.
function setOrderImg(){
	var order = $("input[name*='orders']");
	var orderValue = order.val();
	var orderName=jQuery("#orderBy").val();
	if(orderName==""){
		return;
	}
	if(jQuery("a[name='"+orderName+"']")==null){
		return;
	}
	if (orderValue == "desc"){
		jQuery("a[name='"+orderName+"'] span").html("<img src='${path}/images/arrows/arrow1_down.gif'/>");
	}else if(orderValue == "asc"){
		jQuery("a[name='"+orderName+"'] span").html("<img src='${path}/images/arrows/arrow1_up.gif'/>");
	}
}

$(
	function (){
	setOrderImg();
	}
)
</script> 
</#if>
</#macro>
			
			
		
			
			