var x=window.x||{};
x.creat=function(height,width,img,text){
	this.t=1;
	this.b=50;
	this.c=25;
	this.d=30;
	this.op=1;
	this.div=document.createElement("div");
	this.div.style.height= width;
	this.div.style.width= height;
	//this.div.style.background="red";
	this.div.style.position="absolute";
	this.div.style.left="50%";
	//this.div.style.marginLeft="-50px";
	//this.div.style.marginTop="-20px";
	this.div.innerHTML = "<div class='tspage_w tspage bcor_r'> <span class='bcor_l'><img src='"+img+"' align='absmiddle' /><b>"+text+"</b></span></div>";
	this.div.style.fontSize="12";
	this.div.style.lineHeight=this.div.style.height;
	this.div.style.textAlign="center";
	this.div.style.fontWeight="bold";
	//this.div.style.border="solid red 1px";
	this.div.style.color="#fff"
	this.div.style.top=(this.b+"%");
	document.body.appendChild(this.div);
	this.run();
} 
x.creat.prototype={
	run:function(){
		var me=this;
		this.div.style.top=-this.c*(this.t/this.d)*(this.t/this.d)+this.b+"%";
		this.t++;
		this.q=setTimeout(function(){me.run()},25);
		if(this.t==this.d){
		clearTimeout(me.q);
		setTimeout(function(){me.alpha();},1000);
	}
	},
	alpha:function(){ 
		var me=this; 
		if("\v"=="v"){ 
			this.div.style.filter="progid:DXImageTransform.Microsoft.Alpha(opacity="+this.op*100+")"; 
			this.div.style.filter="alpha(opacity="+this.op*100+")"; 
		;}
		else{this.div.style.opacity=this.op}
			this.op-=0.02; 
			this.w=setTimeout(function(){me.alpha()},25) 
			if(this.op<=0){ 
				clearTimeout(this.w); 
				document.body.removeChild(me.div); 
			}
	}
}


function invokePop(height,width,img,text) {
	new x.creat(height,width,img,text);
}