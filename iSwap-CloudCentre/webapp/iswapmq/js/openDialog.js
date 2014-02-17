	var dg ;
	function opdg(path,title,width,height){
		dg=new J.dialog({ id:'hz0', page:path,bgcolor:'#000',maxBtn:false,iconTitle:true,opacity:0.4,title:title,cancelBtn:false, cover:true ,rang:true,width:width,height:height,resize:false});//autoSize:true
		dg.ShowDialog();
	}
	function closeWindow(){
		dg.cancel();
	}