//通过正则表达式检查名字是否合法并检查数据库中是否存在该真实姓名
function checkName() {
	if(!/^[\u4E00-\u9FA5A-Za-z0-9.]{1,20}$/.test($("#account_name").val() ) ) {
		$("#account_name").val("");
		$("#checkAccountNameInfo").removeClass("hidden");
		return;
	} else {
		$("#checkAccountNameInfo").addClass("hidden");
	}
	
	//检查数据库中是否存在这个姓名
	$.post(
		"account_checkRealName",
		{realName: $("#account_name").val()},
		function(data) {
			if(data) {
				alert("该姓名已经存在，请重新输入");
				$("#account_name").val("");
			}
		}
	);
}

//通过正则表达式检查身份证是否合法并检查数据库中是否存在该身份证，并根据身份证计算生日号码
function checkIdCardNo() {
	if(!/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/.test($("#account_idCardNo").val())) {
		$("#account_idCardNo").val("");
		$("#idCardNoInfo").removeClass("hidden");
		return;
	} else {
		$("#idCardNoInfo").addClass("hidden");
	}
	
	//检查数据库中是否存在这个身份证
	$.post(
		"account_checkIdCardNo",
		{idCardNo: $("#account_idCardNo").val()},
		function(data) {
			if(data) {
				alert("该身份证已经存在，请重新输入");
				$("#account_idCardNo").val("");
			} else {//当且仅当数据库中不存在该身份证时才计算生日
				//根据身份证计算生日
				var idCardNo = $("#account_idCardNo").val();
				var birthday = idCardNo.substring(6, 14);
				$("#accouont_birthday").val(birthday);
			}
		}
	);
		
}

//通过正则表达式检验登录名是否合法并检查数据库中是否存在该登录名
function checkLoginName() {
	if(!/^[\u4E00-\u9FA5A-Za-z0-9_]{1,30}$/.test($("#account_loginName").val())) {
		$("#account_loginName").val("");
		$("#loginNameInfo").removeClass("hidden");
		return;
	} else {
		$("#loginNameInfo").addClass("hidden");
	}
	
	//检查数据库中是否存在这个登录名
	$.post(
		"account_checkLoginName",
		{loginName: $("#account_loginName").val()},
		function(data) {
			if(data) {
				alert("该登录已经存在，请重新输入");
				$("#account_loginName").val("");
			}
		}
	);
}

//通过正则表达式检查密码是否符合规定的格式
function checkPassword() {
	if(!/^[\u4E00-\u9FA5A-Za-z0-9_]{1,30}$/.test($("#account_passwd").val())) {
		$("#account_passwd").val("");
		$("#paswdInfo").removeClass("hidden");
	} else {
		$("#paswdInfo").addClass("hidden");
	}
}

//检查重复输入的密码是否与输入的密码相同
function checkPasswdEqual() {
	//如果没输入密码，直接返回
	if($("#account_passwd").val() === "") {
		return;
	}
	
	if($("#passwdRepeat").val() === "" && $("#account_passwd").val() != "") {
		return;
	}
	
	if($("#passwdRepeat").val() === $("#account_passwd").val()) {
		$("#passwdEqualInfo").addClass("hidden");
	} else {
		$("#passwdEqualInfo").removeClass("hidden");
		$("#passwdRepeat").val("");
		$("#account_passwd").val("");
	}
}

//通过正则表达式检验手机号码是否合法并检查数据库中是否存在该手机号码
function checkTelephone() {
	if(!/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/.test($("#account_telephone").val())) {
		$("#account_telephone").val("");
		$("#telephoneInfo").removeClass("hidden");
		return;
	} else {
		$("#telephoneInfo").addClass("hidden");
	}
	
	//检查数据库中是否存在这个手机号码
	$.post(
		"account_checkTelephone",
		{telephone: $("#account_telephone").val()},
		function(data) {
			if(data) {
				alert("该手机号码已经存在，请重新输入");
				$("#account_telephone").val("");
			}
		}
	);
}

//通过正则表达式检查推荐人身份证号码是否输入正确
function checkRecommenderIdCardNo() {
	if(!/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/.test($("#recommenderIdCardNo").val())) {
		$("#recommenderIdCardNo").val("");
		$("#recommenderInfo").removeClass("hidden");
		return;
	} else {
		$("#recommenderInfo").addClass("hidden");
	}
	
	//检查数据库中是否存在这个身份证号码
	$.post(
		"account_checkRecommenderExist",
		{idCardNo: $("#recommenderIdCardNo").val()},
		function(data) {
			if(!data) {
				alert("该身份证不存在，请重新输入");
				$("#recommenderIdCardNo").val("");
			} else {
				$.post(
					"account_findRecommenderId",
					{idCardNo: $("#recommenderIdCardNo").val()},
					function(data) {
						$("#recommenderId").val(data);
					}
				);
			}
		}
	);
}

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

//提交表单
function submitForm() {
	//先检查必须要填的输入域是否全填上
	if($("#account_name").val() === "" || $("#account_idCardNo").val() === "" ||
			$("#account_loginName").val() === "" || $("#account_passwd").val() === "" ||
			$("#passwdRepeat").val() === "" || $("#account_telephone").val() === "") {
		alert("带*的输入框必须填满");
		return;
	}
	
	//所有条件满足后，提交表单
	$("#account_form").submit();
}

//显示可选项
function showOptionInfo() {
	var optionDiv = $("#optionalInfo");
    if (optionDiv.hasClass("hide")) {
    	optionDiv.removeClass("hide");
    	optionDiv.addClass("show");
    	$("#btn_option").attr("src", "../images/hide.png");
    }
    else {
    	optionDiv.removeClass("show");
    	optionDiv.addClass("hide");
    	$("#btn_option").attr("src", "../images/show.png");
    }
}



