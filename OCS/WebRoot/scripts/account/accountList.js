//根据页码查找账户账号
function findAccounts(page) {
	//设置隐藏域的page值
	$("#account_page").val(page);
	//条件满足后提交表单
	$("#form_findAccount").submit();
}

//删除一个账户账号
function deleteAccount(id, self) {
	$.post(
		"account_delete",
		{id: id},
		function(data) {
			if(data) {
				self.parent().children().css("display", "none");
				self.parent().parent().children("#account_status").html("删除");
			}
		}
	);
}