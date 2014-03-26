//检查用户名称输入是否符合要求
function checkUserName() {
	//先通过正则表达式作判断
	if(!/^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/.test($("#user_name").val() ) ) {
		$("#user_name").val("");
		$("#userNameInfo").removeClass("hidden");
		return;
	} else {
		$("#userNameInfo").addClass("hidden");
	}
	
	//ajax异步检查数据库中是否存在这个角色名字
	$.post(
		"user_checkNameRepeat",
		{userName: $("#user_name").val()},
		function(data) {
			if(data) {
				alert("该用户名字已存在，请重新输入");
				$("#user_name").val("");
			}
		}
	);
}

//检查用户账号输入是否符合要求
function checkUserCode() {
	//先通过正则表达式作检查
	if(!/^[A-Za-z0-9_]{1,30}$/.test($("#user_userCode").val() ) ) {
		$("#user_userCode").val("");
		$("#userCodeInfo").removeClass("hidden");
		return;
	} else {
		$("#userCodeInfo").addClass("hidden");
	}
	
	//通过ajax异步检查数据库中是否存在该用户账号
	$.post(
		"user_checkUserCodeRepeat",
		{userCode: $("#user_userCode").val()},
		function(data) {
			if(data) {
				alert("该用户账号已存在，请重新输入");
				$("#user_userCode").val("");
			}
		}
	);
}

//通过正则表达式检查密码是否符合规定的格式
function checkPassword() {
	if(!/^[A-Za-z0-9_]{1,30}$/.test($("#user_passwd").val())) {
		$("#user_passwd").val("");
		$("#paswdInfo").removeClass("hidden");
	} else {
		$("#paswdInfo").addClass("hidden");
	}
}

//检查重复输入的密码是否与输入的密码相同
function checkPasswdEqual() {
	//如果没输入密码，直接返回
	if($("#user_passwd").val() === "") {
		return;
	}
	
	if($("#passwdRepeat").val() === "" && $("#user_passwd").val() != "") {
		return;
	}
	
	if($("#passwdRepeat").val() === $("#user_passwd").val()) {
		$("#passwdEqualInfo").addClass("hidden");
	} else {
		$("#passwdEqualInfo").removeClass("hidden");
		$("#passwdRepeat").val("");
		$("#user_passwd").val("");
	}
}

//检查电话是否符合要求
function checkTelephone() {
	//先通过正则表达式作判断
	if(!/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/.test($("#user_telephone").val() ) ) {
		$("#user_telephone").val("");
		$("#userTelephoneInfo").removeClass("hidden");
		return;
	} else {
		$("#userTelephoneInfo").addClass("hidden");
	}
		
	//ajax异步检查数据库中是否存在这个手机号码
	$.post(
		"user_checkTelephoneRepeat",
		{telephone: $("#user_telephone").val()},
		function(data) {
			if(data) {
				alert("该手机号码已存在，请重新输入");
				$("#user_telephone").val("");
			}
		}
	);
}

//检查email输入是否合法
function checkEmail() {
	if(!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test($("#user_email").val() ) ) {
		$("#user_email").val("");
		$("#userEmailInfo").removeClass("hidden");
		return;
	} else {
		$("#userEmailInfo").addClass("hidden");
	}
}

//提交表单
function submitForm() {
	if($("#user_name").val() === "" || $("#user_telephone").val() === "" ||
			$("#user_email").val() === "" || $("#user_userCode").val() === "" ||
			$("#user_passwd").val() === "" || $("#passwdRepeat").val() === "") {
		alert("请填写所有必填项");
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
		$("#user_form").submit();
	} else {
		$("#RoleInfo").removeClass("hidden");
	}
}