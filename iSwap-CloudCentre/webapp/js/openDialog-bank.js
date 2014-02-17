	var dg;
	function opdg(path,title,width,height){
		dg  = $.dialog({ 
			id:'hz0', 
			content: 'url:'+path,
			bgcolor:'#000',
			lock:true,
			max:false,
			min:false,
			iconTitle:true,
			opacity:0.4,
			title:title,
			cancelBtn:false, 
			cover:true ,
			rang:true,
			width:width,
			height:height,
			resize:false});//autoSize:true
		dg.show();
	}
	function opdg2(path,title,width,height){
		dg2  = $.dialog({ 
			id:'hz1', 
			content: 'url:'+path,
			bgcolor:'#000',
			lock:true,
			max:false,
			min:false,
			parent:api,
			iconTitle:true,
			opacity:0.4,
			title:title,
			cancelBtn:false, 
			cover:true ,
			rang:true,
			width:width,
			height:height,
			resize:false});//autoSize:true
		dg.show();
	}
	function closeWindow(){
		dg.cancel();
	}
	function opendg(){		
		$.dialog.get({ id: 'test10', title: '测试窗口', page: './content5.html' });
	}