/*!
 * lhgcore Dialog Plugin v3.2.4
 * Date : 2011-03-02 08:21:11
 * Copyright (c) 2009 - 2011 By Li Hui Gang
 */

;(function(J){

var top = window, doc, cover, ZIndex, install = false,
    ie6 = (J.browser.msie && J.browser.version < 7) ? true : false,

getSrc = function()
{
	if( J.browser.msie )
		return ( ie6 ? '' : "javascript:''" );
	else
		return 'javascript:void(0);';
},

iframeTpl = ie6 ? '<iframe hideFocus="true" ' + 
	'frameborder="0" src="' + getSrc() + '" style="position:absolute;' +
	'z-index:-1;width:100%;height:100%;top:0px;left:0px;filter:' +
	'progid:DXImageTransform.Microsoft.Alpha(opacity=0)"><\/iframe>' : '',

compat = function( d )
{
    d = d || document;
	return d.compatMode === 'CSS1Compat' ? d.documentElement : d.body;
},

getScrSize = function()
{
	if( 'pageXOffset' in top )
	{
	    return {
		    x: top.pageXOffset || 0,
			y: top.pageYOffset || 0
		};
	}
	else
	{
	    var d = compat( doc );
		return {
		    x: d.scrollLeft || 0,
			y: d.scrollTop || 0
		};
	}
},

getDocSize = function()
{
	var d = compat( doc );
	
	return {
	    w: d.clientWidth || 0,
		h: d.clientHeight || 0
	}
},

getZIndex = function()
{
    if( !ZIndex ) ZIndex = 999;
	
	return ++ZIndex;
},

reSizeHdl = function()
{
    var rel = compat( doc );
	
	J(cover).css({
	    width: Math.max( rel.scrollWidth, rel.clientWidth || 0 ) - 1 + 'px',
		height: Math.max( rel.scrollHeight, rel.clientHeight || 0 ) - 1 + 'px'
	});
},

getAbsoultePath = function()
{
	var sc = J('script'), bp = '', i = 0, l = sc.length;
	
	for( ; i < l; i++ )
	{
	    if( sc[i].src.indexOf('lhgdialog') >= 0 )
		{
		    bp = !!document.querySelector ?
			    sc[i].src : sc[i].getAttribute('src',4);
			break;
		}
	}
	
	return bp.substr( 0, bp.lastIndexOf('/') + 1 );
};

while( top.parent && top.parent != top )
{
    try{
	    if( top.parent.document.domain != document.domain ) break;
	}catch(e){ break; }
	
	top = top.parent;
}

if( top.document.getElementsByTagName('frameset').length > 0 )
    top = window; 

doc = top.document;

J.fn.fixie6png = function()
{
    var els = J('*',this), iebg, bgIMG;
	
	for( var i = 0, l = els.length; i < l; i++ )
	{
	    bgIMG = J(els[i]).css('backgroundImage');
		
		if( bgIMG.indexOf('.png') !== -1 )
		{
		    iebg = bgIMG.replace(/url\(|"|\)/g,'');
			els[i].style.backgroundImage = 'none';
			els[i].runtimeStyle.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + iebg + "',sizingMethod='scale')";
		}
	}
};

J.fn.dialog = function( opts )
{
    var dialog = false;
	
	if( this[0] )
	    dialog = new J.dialog( opts, this[0] );
	
	return dialog;
};

J.dialog = function( opts, elem )
{
    var S = this,
	
	r = S.opt = J.extend({
		title: 'lhgdialog \u5F39\u51FA\u7A97\u53E3',
		width: 400,
		height: 300,
		id: 'lhgdlgId',
		SetTopWindow: false,
		btns: true,
		link: false,
		page: '',
		event: 'click',
		fixed: false,
		top: 'center',
		left: 'center',
		drag: true,
		resize: true,
		loadingText: '\u7A97\u53E3\u5185\u5BB9\u52A0\u8F7D\u4E2D\uFF0C\u8BF7\u7A0D\u7B49...'
	}, opts || {} );
	
	if( r.SetTopWindow )
	{
	    top = r.SetTopWindow;
		doc = top.document;
	}
	
	if( !install )
	{
		try{
			doc.execCommand( 'BackgroundImageCache', false, true );
		}catch(e){}
		
		J('head',doc).append( '<link href="' + getAbsoultePath() + 'lhgdialog.css" rel="stylesheet" type="text/css"/>' );
		
		install = true;
	}
	
	S.SetIFramePage = function()
	{
	    var innerDoc, dialogTpl;
		
		if( r.html )
		{
		    if( typeof r.html === 'string' )
				innerDoc = '<div id="lhgdig_inbox" class="lhgdig_inbox" style="display:none">' + r.html + '</div>';
			else
				innerDoc = '<div id="lhgdig_inbox" class="lhgdig_inbox" style="display:none"></div>';
		}
		else if( r.page )
		{
			
		    innerDoc = ['<iframe frameborder="0" src="', r.page, '" scrolling="auto" ',
				'id="lhgfrm" style="display:none;width:100%;height:100%;"><\/iframe>'].join('');
		}
		dialogTpl = [
		    '<div id="', r.id, '" class="lhgdig" style="width:', r.width, 'px;height:', r.height, 'px;">',
				'<table border="0" cellspacing="0" cellpadding="0">',
				'<tr>',
					'<td class="lhgdig_leftTop"></td>',
					'<td id="lhgdig_drag" class="lhgdig_top">',
						'<div class="lhgdig_title"><span id="lhgdig_icon" class="lhgdig_icon"></span>', r.title, '</div>',
						'<div id="lhgdig_xbtn" class="lhgdig_xbtn"></div>',
					'</td>',
					'<td class="lhgdig_rightTop"></td>',
				'</tr>',
				'<tr>',
					'<td class="lhgdig_left" id="lhgdigLeft"></td>',
					'<td>',
						'<table border="0" cellspacing="0" cellpadding="0">',
						'<tr>',
							'<td id="lhgdig_content" class="lhgdig_content">',
								innerDoc, '<div id="throbber" class="lhgdig_throbber"><span id="lhgdig_load">', r.loadingText, '</span></div>',
							'</td>',
						'</tr>',
						r.btns ? '<tr><td id="lhgdig_btns" class="lhgdig_btns"><!--div id="lhgdig_bDiv" class="lhgdig_bDiv"></div--></td></tr>' : '',
						'</table>',
					'</td>',
					'<td class="lhgdig_right"></td>',
				'</tr>',
				'<tr>',
					'<td class="lhgdig_leftBottom"></td>',
					'<td class="lhgdig_bottom"></td>',
					'<td id="lhgdig_drop" class="lhgdig_rightBottom"></td>',
				'</tr>',
				'</table>', iframeTpl,
			'</div>'
		].join('');
		
		return dialogTpl;
	};
	
	S.ShowDialog = function()
	{
	    if( J('#'+r.id,doc)[0] )
		    return;
		
		if( r.cover )
		    S.ShowCover();
		
		var fixpos = r.fixed && !ie6 ? 'fixed' : 'absolute',
		    html = S.SetIFramePage();
		
		S.dg = J(html,doc).css({
		    position: fixpos, zIndex: getZIndex()
		}).appendTo(doc.body)[0];
		
		S.iPos( S.dg, r.top, r.left, r.fixed );
		
		S.SetDialog( S.dg );
	
	    if( r.drag )
		    S.initDrag( J('#lhgdig_drag',S.dg)[0] );
		
		if( r.resize )
		    S.initSize( J('#lhgdig_drop',S.dg)[0] );
		
		if( ie6 )
		{
		    var ie6PngRepair = J('html',doc).css('ie6PngRepair') === 'true' ? true : false;
			if( ie6PngRepair ) J(S.dg).fixie6png();
		}
		
		S.lhgDigxW = J('#lhgdigLeft',S.dg)[0].offsetWidth * 2;

		S.reContentSize( S.dg );
		
		if( r.html && r.cusfn ) r.cusfn.call( this );
		
		if( r.html )
		{
		    J('#throbber',S.dg).css('display','none');
			J('#lhgdig_inbox',S.dg)[0].style.display = 'inline-block';
		}
	};
	
	S.ShowCover = function()
	{
	    cover = J('#lhgdgCover',doc)[0];
		
		if( !cover )
		{
			var html = [ '<div id="lhgdgCover" style="position:absolute;top:0px;left:0px;',
					'background-color:#000;">', iframeTpl, '</div>' ].join('');
			
			cover = J(html,doc).css('opacity',0.5).appendTo(doc.body)[0];
		}
		
		J(top).bind( 'resize', reSizeHdl );
		reSizeHdl();
		J(cover).css({ display: '', zIndex: getZIndex() });
	};
	
	S.iPos = function( dg, tp, lt, fix )
	{
	    var cS = getDocSize(top),
		    sS = getScrSize(top),
			dW = dg.offsetWidth,
			dH = dg.offsetHeight, x, y;
		
		if( fix )
		{
			if( ie6 )
			{
				J('html',doc).addClass('lhgdig_ie6_fixed');
				J('<div class="lhgdig_warp"></div>',doc).appendTo(doc.body).append(dg).css('zIndex',getZIndex());
			}
			
			lx = 0;
			rx = cS.w - dW;
			cx = ( rx - 20 ) / 2;
			
			ty = 0;
			by = cS.h - dH;
			cy = ( by - 20 ) / 2;
		}
		else
		{
			lx = sS.x;
			cx = sS.x + ( cS.w - dW - 20 ) / 2;
			rx = sS.x + cS.w - dW;
			
			ty = sS.y;
			cy = sS.y + ( cS.h - dH - 20 ) / 2;
			by = sS.y + cS.h - dH;
		}
		
		switch( lt )
		{
		    case 'center':
				x = cx;
				break;
			case 'left':
				x = lx;
				break;
			case 'right':
				x = rx;
				break;
			default:
			    if(fix) lt = lt - sS.x;
				x = lt; break;
		}
		
		switch( tp )
		{
		    case 'center':
				y = cy;
			    break;
			case 'top':
			    y = ty;
				break;
			case 'bottom':
			    y = by;
				break;
			default:
			    if(fix) tp = tp - sS.y;
				y = tp; break;
		}
		
		J(dg).css({ top: y + 'px', left: x + 'px' });
	};
	
	S.SetDialog = function( dg )
	{
		S.topWin = top;
		S.topDoc = doc;
		
	    S.curWin = window;
		S.curDoc = document;
		
		J(dg).bind('contextmenu',function(ev){
		    ev.preventDefault();
		}).bind( 'mousedown', S.SetIndex );
		
		if( r.html && r.html.nodeType )
		    J('#lhgdig_inbox',dg).append( r.html );
		
		S.regWindow = [ window ];
		
		if( top != window )
		    S.regWindow.push( top );
		
		if( r.page.length > 0 )
		{
		    S.dgFrm = J('#lhgfrm',dg)[0];
			
		    if( !r.link )
			{
			    S.dgWin = S.dgFrm.contentWindow;
				S.dgFrm.lhgDG = S;
			}
			
			J(S.dgFrm).bind('load',function(){
				if( !S.opt.link )
				{
				    var indw = J.browser.msie ?
					    S.dgWin.document : S.dgWin;
					
					J(indw).bind( 'mousedown', S.SetIndex );
					
					S.regWindow.push( S.dgWin );
				    S.dgDoc = S.dgWin.document;
				}
				
				this.style.display = 'block';
			    J('#throbber',S.dg)[0].style.display = 'none';
			});
		}
		
		J('#lhgdig_xbtn',dg).hover(function(){
		    J(this).addClass('lhgdig_xbtnover');
		},function(){
		    J(this).removeClass('lhgdig_xbtnover');
		}).bind( 'click', S.cancel );
	};
	
	S.reContentSize = function( dg )
	{
	    var tH = J('#lhgdig_drag',dg)[0].offsetHeight,
		    bH = J('#lhgdig_drop',dg)[0].offsetHeight,
			xW = S.lhgDigxW,
			nH = r.btns ? J('#lhgdig_btns',dg)[0].offsetHeight : 0,
			iW = parseInt( dg.style.width, 10 ) - xW,
			iH = parseInt( dg.style.height, 10 ) - tH - bH - nH;
		
		J('#lhgdig_content',dg).css({
		    width: iW + 'px', height: iH + 'px'
		});
		
		if( r.html )
		{
		    J('#lhgdig_inbox',dg).css({
			    width: iW + 'px', height: iH + 'px'
			});
		}
		
		S.SetLoadLeft();
	};
	
	S.SetLoadLeft = function()
	{
	    var loadL = ( J('#lhgdig_content',S.dg)[0].offsetWidth -
		    J('#lhgdig_load',S.dg)[0].offsetWidth ) / 2;
			
		J('#lhgdig_load',S.dg)[0].style.left = loadL + 'px';
	};
	
	S.reDialogSize = function( width, height )
	{
		J(S.dg).css({
		    'width': width + 'px', 'height': height + 'px'
		});
		
		S.reContentSize( S.dg );
	};
	
	S.SetIndex = function(ev)
	{
		if( S.opt.fixed && ie6 )
		{
		    J(S.dg).parent()[0].style.zIndex = parseInt(ZIndex,10) + 1;
			ZIndex = parseInt( J(S.dg).parent()[0].style.zIndex, 10 );
		}
		else
		{
		    S.dg.style.zIndex = parseInt(ZIndex,10) + 1;
			ZIndex = parseInt( S.dg.style.zIndex, 10 );
		}
		
		ev.stopPropagation();
	};
	
	S.initDrag = function( elem )
	{
	    var lacoor, maxX, maxY, curpos, regw = S.regWindow, cS, sS;
		
		function moveHandler(ev)
		{
			var curcoor = { x: ev.screenX, y: ev.screenY };
		    curpos =
		    {
		        x: curpos.x + ( curcoor.x - lacoor.x ),
			    y: curpos.y + ( curcoor.y - lacoor.y )
		    };
			lacoor = curcoor;
			
			if( r.rang )
			{
			    if( curpos.x < sS.x ) curpos.x = sS.x;
				if( curpos.y < sS.y ) curpos.y = sS.y;
				if( curpos.x > maxX ) curpos.x = maxX;
				if( curpos.y > maxY ) curpos.y = maxY;
			}
			
			S.dg.style.top = S.opt.fixed ? curpos.y - sS.y + 'px' : curpos.y + 'px';
			S.dg.style.left = S.opt.fixed ? curpos.x - sS.x + 'px' : curpos.x + 'px';
		};
		
		function upHandler(ev)
		{
			for( var i = 0, l = regw.length; i < l; i++ )
			{
				J( regw[i].document ).unbind( 'mousemove', moveHandler );
				J( regw[i].document ).unbind( 'mouseup', upHandler );
			}
			
			lacoor = null; elem = null;
			
		    if( J.browser.msie ) S.dg.releaseCapture();
		};
		
		J(elem).bind( 'mousedown', function(ev){
		    if( ev.target.id === 'lhgdig_xbtn' ) return;

			cS = getDocSize(top);
			sS = getScrSize(top);
			
			var lt = S.dg.offsetLeft,
			    tp = S.dg.offsetTop,
			    dW = S.dg.clientWidth,
			    dH = S.dg.clientHeight;
			
			curpos = S.opt.fixed ?
			    { x: lt + sS.x, y: tp + sS.y } : { x: lt, y: tp };
			
			lacoor = { x: ev.screenX, y: ev.screenY };
			
			maxX = cS.w + sS.x - dW;
			maxY = cS.h + sS.y - dH;
			
			S.dg.style.zIndex = parseInt( ZIndex, 10 ) + 1;
			
			for( var i = 0, l = regw.length; i < l; i++ )
			{
				J( regw[i].document ).bind( 'mousemove', moveHandler );
				J( regw[i].document ).bind( 'mouseup', upHandler );
			}
			
			ev.preventDefault();
			
			if( J.browser.msie ) S.dg.setCapture();
		});
	};
	
	S.initSize = function( elem )
	{
	    var lacoor, dH, dW, curpos, regw = S.regWindow, dialog, cS, sS;
		
		function moveHandler(ev)
		{
		    var curcoor = { x : ev.screenX, y : ev.screenY };
			dialog = {
				w: curcoor.x - lacoor.x,
				h: curcoor.y - lacoor.y
			};
			
			if( dialog.w < 200 ) dialog.w = 200;
			if( dialog.h < 100 ) dialog.h = 100;
			
			S.dg.style.top = S.opt.fixed ? curpos.y - sS.y + 'px' : curpos.y + 'px';
			S.dg.style.left = S.opt.fixed ? curpos.x - sS.x + 'px' : curpos.x + 'px';
			
			S.reDialogSize( dialog.w, dialog.h );
		};
		
		function upHandler(ev)
		{
			for( var i = 0, l = regw.length; i < l; i++ )
			{
			    J( regw[i].document ).unbind( 'mousemove', moveHandler );
				J( regw[i].document ).unbind( 'mouseup', upHandler );
			}
			
			lacoor = null; elem = null;
			
		    if( J.browser.msie ) S.dg.releaseCapture();
		};
	
	    J(elem).bind( 'mousedown', function(ev){
			dW = S.dg.clientWidth;
			dH = S.dg.clientHeight;
			
			dialog = { w: dW, h: dH };
			
			cS = getDocSize(top);
			sS = getScrSize(top);
			
			var lt = S.dg.offsetLeft,
			    tp = S.dg.offsetTop;
			
			curpos = S.opt.fixed ?
			    { x: lt + sS.x, y: tp + sS.y } : { x: lt, y: tp };
				
			lacoor = { x: ev.screenX - dW, y: ev.screenY - dH };
			
			S.dg.style.zIndex = parseInt( ZIndex, 10 ) + 1;
			
			for( var i = 0, l = regw.length; i < l; i++ )
			{
			    J( regw[i].document ).bind( 'mousemove', moveHandler );
				J( regw[i].document ).bind( 'mouseup', upHandler );
			}
			
			ev.preventDefault();
			
			if( J.browser.msie ) S.dg.setCapture();
		});
	};
	
	S.addBtn = function( id, txt, fn )
	{
	    if( J('#'+id,S.dg)[0] )
		    J('#'+id,S.dg).html( '<em>' + txt + '</em>' ).click( fn );
		else
		{
		    var html = '<a id="' + id + '" class="lhgdig_button" href="' +
			    (ie6 ? '###' : 'javascript:void(0)') + '" hidefocus="true"><em>' + txt + '</em></a>',
		
		    btn = J(html,doc).bind( 'click', fn )[0];
		    J('#lhgdig_bDiv',S.dg).append( btn );
		}
	};
	
	S.removeBtn = function( id )
	{
	    if( J('#'+id,S.dg)[0] )
		    J('#'+id,S.dg).remove();
	};
	
	S.cancel = function()
	{
		S.removeDG();
		
		if( cover )
		{
		    if( S.opt.parent && S.opt.parent.opt.cover )
			{
			    var Index = S.opt.parent.dg.style.zIndex;
				cover.style.zIndex = parseInt(Index,10) - 1;
			}
			else
			    cover.style.display = 'none';
		}
	};
	
	S.removeDG = function()
	{
		if( S.dgFrm )
		{
			if( !S.opt.link )
				J(S.dgDoc).unbind( 'load' );
				
			S.dgFrm.src = J.browser.mozilla ? 'about:blank' : getSrc();
			S.dgFrm = null;
		}
		
		S.regWindow = [];
		
		if( S.opt.fixed && ie6 )
		{
		    J('html',doc).removeClass('lhgdig_ie6_fixed');
			J(S.dg).parent().remove();
		}
		else
		    J(S.dg).remove();
		
		S.dg = null;
	};
	
	S.cleanDialog = function()
	{
		if( S.dg )
		    S.removeDG();
		
		if( cover )
		{
		    J(cover).remove();
			cover = null;
		}
	};
	
	J(window).bind( 'unload', S.cleanDialog );
	
	if( elem )
	    J(elem).bind( r.event, S.ShowDialog );
};

J(function(){
	var lhgDY = setTimeout(function(){
	    new J.dialog({ id:'reLoadId', html:'lhgdialog', width:100, title:'reLoad', height:100, left:-9000 }).ShowDialog(); clearTimeout(lhgDY);
	}, 150);
});

})(lhgcore);