/*!
* jQuery WPageSilde 1.0.45 页面滑动插件
* http://jquery.com/
* http://www.wobumang.com/FenXiang/jquery/wpageslide
* Copyright 2011, xusion
* Date: 2011-4-5 15:08:48
*/

$.fn.extend({
    wPageSilde: function (options) {
        var defaults = {
            width: $(window).width() + 17, //宽度：默认使用满屏
            height: $(window).height(), 	//高度：默认使用宽屏
            btnsHide: false, 			//按钮隐藏：默认否
            btnsPos: "b"					//按钮定位：10个方位，参考btnPos函数，默认中下
        };
        options = options || defaults;
        options.width = options.width || defaults.width;
        options.height = options.height || defaults.height;
        options.btnsPos = options.btnsPos || defaults.btnsPos;
        options.btnsHide = options.btnsHide || defaults.btnsHide;
        options.fullPage = options.fullPage || defaults.fullPage;

        var $body = $("body");
        var $wslide = $(this);
        var $wpages = $wslide.children("div#wpages");
        var $wpagesli = $wpages.children("div.wpage");
        var $wbtns = $wslide.children("ul#wpagebtns");
        var $wbtnsli = $wbtns.children("li");
        var pagewidth = options.width;
        var pageheight = options.height;
        var windowwidth = $wpagesli.size() * pagewidth;
        var slidewidth = pagewidth;
        var btnwidth = $wbtns.width();
        var btnheight = $wbtns.height();
        var btnpos = btnPos(options.btnsPos);
        var currentindex = 0;
        var currentcls = "current";
        var delay = 200;
        var drag = pagewidth * 0.1;
        if (options.width == defaults.width) { $body.css({ overflow: "hidden", margin: 0, padding: 0 }); }
        $wbtns.css({ position: "absolute", margin: 0, padding: 0, zIndex: 11, top: btnpos.y, left: btnpos.x, display: options.btnsHide ? "none" : "" });
        $wpages.css({ position: "relative", margin: 0, padding: 0, zIndex: 10 });
        $wslide.css({ position: "relative", overflow: "hidden", width: slidewidth, height: pageheight });
        $wbtns.children("li:eq(0)").addClass(currentcls);

        $wpagesli.each(function (index) {
            $(this).css({ position: "absolute", top: 0, left: pagewidth * index, width: pagewidth, height: pageheight });
        });


        $wbtnsli.each(function (index) {
            var left = -(pagewidth * index);
            var oril = currentindex * pagewidth;
            left = left > 0 ? oril : Math.abs(left) >= windowwidth ? oril : left;
            $(this).unbind("click").click(function () {
                pageSilde(left, delay);
            });
        });

        $(this).mousedown(function (event) {
            var orix = event.clientX;
            var tarx = event.clientX;
            var oril = currentindex * pagewidth;
            //拖动时排除例外
            if (targetInExcluded(event)) { return; }
            oril = $wpages.position().left > 0 ? oril : 0 - oril;
            $(this).mousemove(function (event) {
                //防止拖动中选中
                if (document.selection) {//IE ,Opera
                    if (document.selection.empty) {
                        document.selection.empty(); //IE
                    } else { //Opera
                        document.selection = null;
                    }
                } else if (window.getSelection) {//FF,Safari 
                    window.getSelection().removeAllRanges();
                }

                tarx = event.clientX;
                var left = oril - (orix - tarx);
               // pageSilde(left, 0);
            });
            $(this).mouseup(function (event) {
                $(this).unbind("mousemove");
                $(this).unbind("mouseup");
                tarx = event.clientX;
                var left = 0;
                if (orix - drag > tarx) { left = oril - pagewidth; }
                else if (orix < tarx - drag) { left = oril + pagewidth; }
                else { left = oril; }
                left = left > 0 ? oril : Math.abs(left) >= windowwidth ? oril : left;
               // pageSilde(left, delay);
            });
        });

        //页面滑动
        function pageSilde(left, delay) {
            $wpages.animate({ left: left }, delay, function () {
                var index = Math.round(Math.abs(left) / pagewidth);
                if (currentindex != index) {
                    currentindex = index;
                    $wbtnsli.removeClass(currentcls);
                    $wbtns.children("li:eq(" + index + ")").addClass(currentcls);
                }
            });
        }

        //拖动时排除例外
        function targetInExcluded(event) {
            var taro = event.srcElement || event.target;
            var excluded = ["input", "textarea"];  //例外列表

            for (var i = 0; i < excluded.length; i++) {
                if ($(taro).attr("tagName").toLowerCase() == excluded[i].toLowerCase()) {
                    return true;
                }
            }
            return false;
        }

        //按钮位置
        function btnPos(pos) {
            var x = 10;
            var y = 10;
            var xr = pagewidth - btnwidth - x;
            var xc = pagewidth / 2 - btnwidth / 2 + x;
            var yc = pageheight / 2 - btnheight / 2 - y;
            var yb = pageheight - btnheight - y;
            var offset = { x: yc, y: yb };
            switch (pos) {
                case "t": offset = { x: xc, y: y }; break; 	//中上
                case "lt": offset = { x: x, y: y }; break; 	//左上
                case "l": offset = { x: x, y: yc }; break; 	//左		
                case "lb": offset = { x: x, y: yb }; break; 	//左下
                case "b": offset = { x: xc, y: yb }; break; 	//中下
                case "c": offset = { x: xc, y: yc }; break; 	//中间
                case "rt": offset = { x: xr, y: y }; break; 	//右上
                case "r": offset = { x: xr, y: yc }; break; 	//右
                case "rb": offset = { x: xr, y: yb }; break; //右下
                case "b": offset = { x: yc, y: yb }; break; 	//中下
            }
            return offset;
        }
    }
});
