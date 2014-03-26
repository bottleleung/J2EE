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
	
	//如果修改后的名字等于原来的名字，直接返回
	if($("#user_name").val() === $("#originUserName").val()) {
		return;
	}
	
	//ajax异步检查数据库中是否存在这个角色名字
	$.post(
		"../user/user_checkNameRepeat",
		{userName: $("#user_name").val()},
		function(data) {
			if(data) {
				alert("该用户名字已存在，请重新输入");
				$("#user_name").val("");
			}
		}
	);
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
	
	//如果修改后的手机号码等于原来的，直接返回
	if($("#user_telephone").val() === $("#originTelephone").val()) {
		return;
	}
	
	//ajax异步检查数据库中是否存在这个手机号码
	$.post(
		"../user/user_checkTelephoneRepeat",
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
	//提交表单前先判断必须填的选项是否填好
	if($("#user_name").val() === "" || 
			$("#user_telephone").val() === "" ||
			$("#user_email").val() === "") {
		alert("必须填写好所有字段");
		return;
	}
	
	$("#infoModi_form").submit();
}