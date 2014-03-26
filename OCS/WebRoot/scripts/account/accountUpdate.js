//通过正则表达式检查邮箱格式是否正确
function checkEmail() {
	if(!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test($("#account_email").val() ) ) {
		$("#account_email").val("");
		$("#emailInfo").removeClass("hidden");
		return;
	} else {
		$("#emailInfo").addClass("hidden");
	}
}

//通过正则表达式检查地址格式是否正确
function checkAddress() {
	if(!/^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/.test($("#account_address").val() ) ) {
		$("#account_address").val("");
		$("#addressInfo").removeClass("hidden");
	} else {
		$("#addressInfo").addClass("hidden");
	}
}

//通过正则表达式检查邮编格式是否正确
function checkZipCode() {
	if(!/^\d{6}$/.test($("#account_zipcode").val() ) ) {
		$("#account_zipcode").val("");
		$("#zipcodeInfo").removeClass("hidden");
	} else {
		$("#zipcodeInfo").addClass("hidden");
	}
}

//通过正则表达式检查QQ格式是否正确
function checkQQ() {
	if(!/^\d{5,13}$/.test($("#account_QQ").val() ) ) {
		$("#account_QQ").val("");
		$("#QQInfo").removeClass("hidden");
	} else {
		$("#QQInfo").addClass("hidden");
	}
}

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

//显示修改密码选项
function showPwd() {
	var tempPasswd = $("#account_newPasswd").val();		//保存旧密码
	
	if($("#chkModiPwd")[0].checked) {
		$("#divPwds").css("display", "block");
		$("#account_newPasswd").val("");
	} else {
		$("#divPwds").css("display", "none");
		$("#account_newPasswd").val(tempPasswd);
	}
}

//提交表单
function submitForm() {
	//提交表单前先判断必须填的选项是否填好
	if($("#chkModiPwd")[0].checked) {//如果选择了修改密码，必须检查旧密码、新密码和重复密码输入框是否为空再提交
		if($("#account_oldPasswd").val() === "" || 
				$("#account_newPasswd").val() === "" ||
				$("#passwdRepeat").val() === "") {
			alert("必须填写好旧密码、新密码和重复密码字段");
			return;
		}
	} 
	
	$("#account_form").submit();
}