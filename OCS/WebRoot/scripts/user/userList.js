//查找用户,page为要定位到的页码
function findUsers(page) {
	//先设置隐藏域的页码值
	$("#user_page").val(page);
	//再提交表单
	$("#user_form").submit();
}

//显示用户所拥有的角色
function showUserRoles(flag, self) {
    var detailDiv = self.parent().children("div");
    if (flag) {
        detailDiv.css("display", "block");
    } else {
    	detailDiv.css("display", "none");
    }
}