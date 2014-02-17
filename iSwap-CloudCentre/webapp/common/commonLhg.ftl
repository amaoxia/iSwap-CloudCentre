<!-- LHG窗体系统js文件文件 -->
<script type="text/javascript" src="${path}/js/lhgdialog/lhgcore.min.js"></script>
<script type="text/javascript" src="${path}/js/lhgdialog/lhgdialog.min.js"></script>
<!-- 打开窗体js文件 -->
<script type="text/javascript" src="${path}/js/openDialog.js"></script>
<!--<script type="text/javascript" src="${path}/js/iswap_table.js"></script>-->
<script type="text/javascript">
//过滤替换转义字符
function showKeyPress(evt) {
 evt = (evt) ? evt : window.event
 return checkSpecificKey(evt);
}
function checkSpecificKey(evt) {
    var specialKey = "#$%\^*\'\"\\+";//Specific Key list
    var realkey = String.fromCharCode(evt.keyCode);
    var flg = false;
 flg = (specialKey.indexOf(realkey) >= 0);
  if (flg) {
        alert('请勿输入特殊字符: ' + realkey);
        evt.returnValue = false;//禁止向页面输出所按下的键值
        return false;
    }
    return true;
}
</script>