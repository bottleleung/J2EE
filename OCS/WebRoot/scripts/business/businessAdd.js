//检查身份证是否存在，异步检查
function checkIdCardNoExist() {
	$.post(
		"business_checkIdCardNoExist",
		{idCardNo: $("#idCardNo").val()},
		function(data) {
			if(!data) {
				$("#checkIdCardNoInfo").removeClass("hidden");
			} else {
				$("#checkIdCardNoInfo").addClass("hidden");
			}
		}
	);
}

//根据身份证查找对应账户账号
function findAccount() {
	$.post(
		"business_findAccount",
		{idCardNo: $("#idCardNo").val()},
		function(data) {
			if(data === null) {
				alert("找不到对应的账户账号");
				return;	//直接返回
			}
			var account = data;
			$("#account").val(account.loginName);	//显示账户账号姓名
			$("#accountId").val(account.id);	//设置账户账号id隐藏域
		}
	);
}

//用正则表达式检查ip是否合法
function checkIpLegal() {
	if(/\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}/.test($("#unixHost").val())) {
		$("#checkIp").addClass("hidden");
	} else {
		$("#checkIp").removeClass("hidden");
		$("#unixHost").val("");
	}
}

//用正则表达式检查osUserName是否合法
function checkOSUserName() {
	if(/^\w{1,8}$/.test($("#osUserName").val() ) ) {
		$("#checkOsUserName").addClass("hidden");
	} else {
		$("#checkOsUserName").removeClass("hidden");
		$("#osUserName").val("");
	}
}

//ͨ用正则表达式检查密码是否合法
function checkPasswd() {
	if(/^\w{1,30}$/.test($("#passwd").val() ) ) {
		$("#checkPasswd").addClass("hidden");
	} else {
		$("#checkPasswd").removeClass("hidden");
		$("#passwd").val("");
	}
}

//检查重复输入的密码是否与之前的相同
function checkPasswdEqual() {
	//如果没输入密码，直接返回
	if($("#passwd").val() === "") {
		return;
	}
	
	if($("#passwdRepeat").val() === "" && $("#passwd").val() != "") {
		return;
	}
	
	if($("#passwdRepeat").val() === $("#passwd").val()) {
		$("#passwdRepeatInfo").addClass("hidden");
	} else {
		$("#passwdRepeatInfo").removeClass("hidden");
		$("#passwdRepeat").val("");
		$("#passwd").val("");
	}
}

//提交表单
function submitForm() {
	//先检查必须填的有没有完全填满，没有的话马上返回
	if($("#idCardNo").val() === "" || $("#unixHost").val() === "" ||
			$("#osUserName").val() === "" || $("#passwd").val() === "" ||
			$("#passwdRepeat").val() === "" || $("#account").val() === "") {
		alert("带*的输入框必须填满 ");
		return;
	}
	
	//先检查有没有数据库中有没有重复的业务账号，没有才可以提交表单
	$.post(
		"business_checkBusinessRepeat",
		{unixHost: $("#unixHost").val(), osUserName: $("#osUserName").val()},
		function(data) {
			if(data) {
				alert("数据库中已存在该业务账号，请重新输入");
				$("#unixHost").val('');
				$("#osUserName").val('');
			} else {
				$("#businessAdd").submit();
			}
		}
	);

}
