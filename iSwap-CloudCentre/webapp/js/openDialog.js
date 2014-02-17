	var dg ;
	function opdg(path,title,width,height){
		dg=new J.dialog({ id:'hz0', page:path,bgcolor:'#000',maxBtn:false,iconTitle:true,opacity:0.4,title:title,cancelBtn:false, cover:true ,rang:true,width:width,height:height,resize:false});//autoSize:true
		dg.ShowDialog();
	}
	function closeWindow(){
		dg.cancel();
	}
	function opChild(path,title,width,height)  {  
	    //var testDG2 = new DG.curWin.J.dialog({ id:'child', page:path,bgcolor:'#000',maxBtn:false,iconTitle:true,opacity:0.4,title:title, cover:true ,width:width,height:height,parent:DG });  
		 var testDG2 = new DG.curWin.J.dialog({ id:'child', page:path, bgcolor:'#000',maxBtn:false,iconTitle:true,opacity:0.4,title:title, cover:true ,width:width,height:height, parent:DG });  
		testDG2.ShowDialog();  
	}  

	function opdg1(path,title,width,height){
		dg=new J.dialog({ id:'hz0', page:path,bgcolor:'#000',maxBtn:false,iconTitle:true,opacity:0.4,title:title,cancelBtn:false, cover:true ,rang:true,width:width,height:height,resize:false});//autoSize:true
		//dg.ShowDialog();
	}
	function opendg(){		
		$.dialog.get({ id: 'test10', title: '测试窗口', page: './content5.html' });
	}