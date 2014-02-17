<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${request.getContextPath()}/css/main.css" rel="stylesheet" type="text/css" />

<script type="text/javascript"  src="${request.getContextPath()}/js/sortable/jquery-1.5.1.js"></script>
<script>

function ba(){
	
	window.location.href="main.html";
	}
	
	 	//<![CDATA[
    	/*
			ORain: 2009-3-10
			http://blog.csdn.net/orain 
		*/		
		$(function(){
			//交替显示行
			$('#alternation').click(function(){				
				$('tbody > tr:odd', $('#example')).toggleClass('alternation');
			});
			
			//三色交替显示行
			$('#alternationThree').click(function(){				
				$('tbody > tr:nth-child(3n)', $('#example')).toggleClass('alternation');
				$('tbody > tr:nth-child(3n+2)', $('#example')).toggleClass('alternation3');
			});
			
			//选择行
			$('#selectTr').click(function(){
				//为表格行添加选择事件处理
				$('tbody > tr', $('#example')).click(function(){
					$('.selected').removeClass('selected');					
					$(this).addClass('selected'); //this 表示引发事件的对象，但它不是 jquery 对象
				}).hover(		//注意这里的链式调用
					function(){
						$(this).addClass('mouseOver');
					},
					function(){
						$(this).removeClass('mouseOver');
					}
				);				
			});
			
			//增加排序功能
			$('#sort').click(tableSort);
			
			//获取排好序后的主键值
			$('#getSequence').click(function(){
				var sequence = [];
				$('#content input[name=noticeSelect]').each(function(){
					sequence.push(this.value);
				});
				alert(sequence.join(','));
			});
			
			//获取表格中已选择的复选框的值集合
			$('#getSelected').click(function(){
				var sequence = [];
				$('#content input[name=noticeSelect]:checked').each(function(){
					sequence.push(this.value);
				});
				alert(sequence.join(','));
			});
			
			//按日期降序排列
			$('#dateDesc').click(descByDate);
		});
		
		//表格排序
		function tableSort()
		{
			var tbody = $('#example > tbody');
			var rows = tbody.children();
			var selectedRow;
			//压下鼠标时选取行
			rows.mousedown(function(){
				selectedRow = this;
				tbody.css('cursor', 'move');
				return false;	//防止拖动时选取文本内容，必须和 mousemove 一起使用
			});
			rows.mousemove(function(){
				return false;	//防止拖动时选取文本内容，必须和 mousedown 一起使用
			});
			//释放鼠标键时进行插入
			rows.mouseup(function(){				
				if(selectedRow)
				{
					if(selectedRow != this)
					{
						$(this).before($(selectedRow)).removeClass('mouseOver'); //插入														
					}
					tbody.css('cursor', 'default');
					selectedRow = null;						
				}								
			});
			//标示当前鼠标所在位置
			rows.hover(
				function(){					
					if(selectedRow && selectedRow != this)
					{
						$(this).addClass('mouseOver');	//区分大小写的，写成 'mouseover' 就不行						
					}					
				},
				function(){
					if(selectedRow && selectedRow != this)
					{
						$(this).removeClass('mouseOver');
					}
				}
			);
			
			//当用户压着鼠标键移出 tbody 时，清除 cursor 的拖动形状，以前当前选取的 selectedRow			
			tbody.mouseover(function(event){
				event.stopPropagation(); //禁止 tbody 的事件传播到外层的 div 中
			});	
			$('#contain').mouseover(function(event){
				if($(event.relatedTarget).parents('#content')) //event.relatedTarget: 获取该事件发生前鼠标所在位置处的元素
				{
					tbody.css('cursor', 'default');
					selectedRow = null;
				}
			});
		}
		
		//按日期降序排列
		function descByDate()
		{
			var descElements = $('#content > tr').get().sort(function(first, second){				
				var f = $('td:eq(4)', first).html();	//first = $('td:eq(4)', first).html();IE 下会有问题，FF 正常，下同
				var s = $('td:eq(4)', second).html();
				if(f < s)
					return 1;				
				if(f == s)
					return 0;
				return -1;				
			});			
			$(descElements).appendTo('#content');
		}
	//]]>
	
	function sub(){
      	    document.saveForm.submit();
	   }
</script>
</head>

<body onclick="parent.hideMenu()">
<form name="saveForm"  action="${request.getContextPath()}/performancemanage/checkreport/checkRankMgr!downloadExcel.action" method="post">
<div class="frameset_w" style="height:100%">
  <div class="main">
  	<div class="common_menu">
      <div class="c_m_title"><img src="${request.getContextPath()}/images/title/img_05.png"  align="absmiddle" />考核排名表</div>
    </div>
    <div class="tabs3_title">
    	${check.name?default('')}
    	<input name="check.id" type="hidden" value="${check.id?default('')}" />
    	<input name="check.name" type="hidden" value="${check.name?default('')}" />
    </div>
    <div class="tabs4_date">汇总日期：2011年4月20日</div>
    <!--
    <div class="common_menu">
      <div class="c_m_title"></div>
  </div>-->
    <div class="main_c ">
      <div class="tabs4_scroll tabs4_w">
        <table class="tabs1" id='example'>
          <tr>
            <th>排名</th>
            <th>部门</th>
          </tr>
          	<tbody id='content'>
		        <#assign i = 0>  
		        <#list listDatas as list> 
		        <#assign i = i+1>
		        	<tr class="trbg">
			            <td>${i}</td>
			            <td ><a href="#">${list.deptModelContact.department.name?default('')}</a></td>
		           </tr>
		        </#list>
          </tbody>
        </table>
      </div>

      <div class="tabs3_fbtn">
        <a href="${request.getContextPath()}/performancemanage/checkreport/checkmgr!pmlist.action"  class="link_btn">返回考核列表</a> 
        <input name="" type="button" onclick="sub();"  value="生成通报"  class="btn_s"/>
        	<!--input type='button' id='alternation' value='交替显示' /> <input type='button' id='alternationThree' value='三色替换' /><br />
	<input type="button" id='selectTr' value='选择行' /><br />
	<input type='button' id='sort' value='排序' /> <input type='button' id='getSequence' value='获取值序列' /><br />
	<input type='button' id='getSelected' value='获取表格中选取的值' /> <input type='button' id='dateDesc' value='按日期降序' /-->
      </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
<br />
</form>
</body>
</html>
