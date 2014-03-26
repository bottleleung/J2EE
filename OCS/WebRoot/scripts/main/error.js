//function setErrorTimeout() {
//	
//}

(function() {
	var i = 5;
	setInterval(showSecond, 1000);

	//该函数实现改变显示的秒数并在最后实现自动跳转
	function showSecond() {
		if(i > 0) {
			i --;
			$("#seconds")[0].innerHTML = i;
		} else {
			location.href = "../common/common_toIndex";
		}	
	}
})();


