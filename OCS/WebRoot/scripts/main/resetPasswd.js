//检查旧密码输入是否正确
function checkOldPasswd() {
	//先通过正则表达式检验密码输入是否符合规范
	if(!/^[\u4E00-\u9FA5A-Za-z0-9_]{1,30}$/.test($("#account_oldPasswd").val())) {
		$("#account_oldPasswd").val("");
		$("#oldPasswdInfo").removeClass("hidden");
		return;
	} else {
		$("#oldPasswdInfo").addClass("hidden");
	}
	
	//如果符合规范，再检查旧密码输入是否正确
	if($("#account_oldPasswd").val() != $("#oldPasswdHidden").val()) {//如果不等
		$("#account_oldPasswd").val("");
		alert("原密码输入错误，请重新输入");
	}
}

//检查新密码输入是否正确
function checkNewPasswd() {
	//先通过正则表达式检验密码输入是否符合规范
	if(!/^[\u4E00-\u9FA5A-Za-z0-9_]{1,30}$/.test($("#account_newPasswd").val())) {
		$("#account_newPasswd").val("");
		$("#newPasswdInfo").removeClass("hidden");
		return;
	} else if($("#oldPasswdHidden").val() === $("#account_newPasswd").val()) {
		//如果新密码与原密码相同
		$("#account_newPasswd").val("");
		alert("新密码与原密码相同，请重新输入");
	} else {
		$("#newPasswdInfo").addClass("hidden");
	}
}

//检查重复输入的密码是否与输入的密码相同
function checkPasswdEqual() {
	//如果没输入密码，直接返回
	if($("#account_newPasswd").val() === "") {
		return;
	}
	
	if($("#passwdRepeat").val() === "" && $("#account_newPasswd").val() != "") {
		return;
	}
	
	if($("#passwdRepeat").val() === $("#account_newPasswd").val()) {
		$("#passwdEqualInfo").addClass("hidden");
	} else {
		$("#passwdEqualInfo").removeClass("hidden");
		$("#passwdRepeat").val("");
		$("#account_newPasswd").val("");
	}
}

//提交表单
function submitForm() {
	//提交表单前先判断必须填的选项是否填好
	if($("#account_oldPasswd").val() === "" || 
			$("#account_newPasswd").val() === "" ||
			$("#passwdRepeat").val() === "") {
		alert("必须填写好旧密码、新密码和重复密码字段");
		return;
	}
	
	$("#account_form").submit();
}