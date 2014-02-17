		function checkLength(address,textCount,leng) {
            var curLength =  $("#"+address).html().length;
            if (curLength > leng) {
                $("#"+address).value =  $("#"+address).html().substr(0, leng);
                curLength = leng;
            }
            if(leng< $("#"+address).html().length){
             $("#"+textCount).html('可输入0字');
          	  return;
            }else{
            $("#"+textCount).html("(可输入"+(leng - $("#"+address).html().length)+"字)");
            }
        }