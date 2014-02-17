var DG = frameElement.lhgDG;
DG.addBtn('close', '关闭窗口', singleCloseWin);
// 关闭窗口 不做任何操作
function singleCloseWin() {
	DG.cancel();
}
DG.addBtn('reset', '重置', resetWin);
function resetWin() {
	// 实现逻辑
	reset("saveForm");
	
}
DG.addBtn('save', '保存', saveWin);
function saveWin() {
	// 实现逻辑
	isSub();
}
