//检查角色名称输入是否符合要求
function checkRoleName() {
	//先通过正则表达式作判断
	if(!/^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/.test($("#role_name").val() ) ) {
		$("#role_name").val("");
		$("#roleNameInfo").removeClass("hidden");
		return;
	} else {
		$("#roleNameInfo").addClass("hidden");
	}
	
	//如果修改后的名字等于原来的名字，直接返回
	if($("#role_name").val() === $("#originRoleName").val()) {
		return;
	}
	
	//ajax异步检查数据库中是否存在这个角色名字
	$.post(
		"role_checkRoleNameRepeat",
		{roleName: $("#role_name").val()},
		function(data) {
			if(data) {
				alert("该角色名字已存在，请重新输入");
				$("#role_name").val("");
			}
		}
	);
}

//提交表单
function submitForm() {
	if($("#role_name").val() === "") {
		alert("请填写角色名称");
		return;
	}
	
	var hasChecked = false;
	//如果没有一个选项被选中，不提交
	for(var i = 0; i < $(":checkbox").length; i ++) {
		if($(":checkbox")[i].checked) {
			hasChecked = true;
			break;
		}
	}
	
	if(hasChecked) {
		$("#role_form").submit();
	} else {
		$("#privilegeInfo").removeClass("hidden");
	}
}