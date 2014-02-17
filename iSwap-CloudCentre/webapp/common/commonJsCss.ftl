<script type="text/javascript" src="${path}/js/jquery-1.6.1.min.js"></script>

<!-- jqueryValidate js-->
<script type="text/javascript" src="${path}/js/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${path}/js/jqueryValidate/jquery.metadata.js"></script>
<script type="text/javascript" src="${path}/js/jqueryValidate/messages_cn.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${path}/js/jqueryValidate/css/custom.css" />

<link rel="stylesheet" href="${path}/js/jqueryValidate/css/validator.css" type="text/css" />

<script type="text/javascript" src="${path}/js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/m/crud.js"></script>


<link rel="stylesheet" type="text/css" media="screen" href="${path}/css/main.css" />


<script type="text/javascript">
$.validator.setDefaults({
	//submitHandler: function() { },
	success: function(label) {
		// set &nbsp; as text for IE
		label.html("&nbsp;").addClass("checked");
	}
});
//时间控件
function setDay(o){
  WdatePicker({skin:'whyGreen'})
}
</script>

