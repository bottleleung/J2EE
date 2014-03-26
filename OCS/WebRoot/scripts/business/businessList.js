//根据指定的页码查找business
function findBusiness(page) {
	//设置隐藏域中要传递的页码值
	$("#business_page").val(page);
	//提交
	$("#form_findBusiness").submit();
}

//删除业务账号，异步操作
function deleteBusiness(id, self) {
	$.post(
		"business_delete",
		{id: id},
		function(data) {
			if(data) {
				self.parent().children().css("display", "none");
				self.parent().parent().children("#business_status").html("删除");
			}
		}
	);
}

//开通业务账号，异步操作
function startBusiness(id) {
	//先检查要开通的业务账号对应的账务账号是否处于暂停或删除状态，是的话，不能开通该业务账号
	$.post(
		"business_isAccountPauseOrDelete",	
		{id: id},
		function(data) {
			if(data) {
				alert("该业务账号对应的账务账号处于删除或暂停状态，不能开通");
				return;
			} else {
				//只有对应的账务账号处于开通状态才可以开通业务账号
				location.href="business_start?id=" + id;
			}
		}
	);
}

//显示资费的详细信息
function showDetail(flag, self) {
    var detailDiv = self.parent().children("div");
    if (flag) {
        detailDiv.css("display", "block");
    } else {
    	detailDiv.css("display", "none");
    }
}