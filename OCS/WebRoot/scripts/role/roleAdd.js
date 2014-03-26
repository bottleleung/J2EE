//检查角色名称
function checkRoleName() {
	//先用正则表达式检查是否符合规范
	if(!/^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/.test($("#role_name").val() ) ) {
		$("#role_name").val("");
		$("#roleNameInfo").removeClass("hidden");
		return;
	} else {
		$("#roleNameInfo").addClass("hidden");
	}
	
	//异步检查数据库中有没有这个角色名称
	$.post(
		"role_checkRoleNameRepeat",
		{roleName: $("#role_name").val()},
		function(data) {
			if(data) {
				alert("该角色名称已存在");
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