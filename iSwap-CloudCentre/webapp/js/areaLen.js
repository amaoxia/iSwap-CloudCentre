		function checkLength(address,textCount,leng) {
            var curLength =  $("#"+address).html().length;
            if (curLength > leng) {
                $("#"+address).value =  $("#"+address).html().substr(0, leng);
                curLength = leng;
            }
            if(leng< $("#"+address).html().length){
             $("#"+textCount).html('������0��');
          	  return;
            }else{
            $("#"+textCount).html("(������"+(leng - $("#"+address).html().length)+"��)");
            }
        }