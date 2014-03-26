//当修改资费类型时改变输入域的写入性
function changeInputField(i) {
	if(i === 1) {//如果资费类型为包月
		//先移除
		$("#package").removeAttr("checked");
		$("#timeBased").removeAttr("checked");
		
		$("#basicDuration").removeClass("readonly");
		$("#basicCost").removeClass("readonly");
		$("#unitCost").removeClass("readonly");
		
		$("#basicDuration").removeAttr("readonly");
		$("#basicCost").removeAttr("readonly");
		$("#unitCost").removeAttr("readonly");
		
		$("#basicDuration").val("");
		$("#basicCost").val("");
		$("#unitCost").val("");
		
		//再添加
		$("#basicDuration").attr({"readonly": "readonly"});
		$("#basicDuration").addClass("readonly");
		
		$("#unitCost").attr({"readonly": "readonly"});
		$("#unitCost").addClass("readonly");
		
		$("#monthly").attr({"checked": "checked"});
							
	} else if(i === 2) {//如果资费类型为套餐
		
		//先移除
		$("#monthly").removeAttr("checked");
		$("#timeBased").removeAttr("checked");
		
		$("#basicDuration").removeClass("readonly");
		$("#basicCost").removeClass("readonly");
		$("#unitCost").removeClass("readonly");
		
		$("#basicDuration").removeAttr("readonly");
		$("#basicCost").removeAttr("readonly");
		$("#unitCost").removeAttr("readonly");
		
		$("#basicDuration").val("");
		$("#basicCost").val("");
		$("#unitCost").val("");
		
		$("#package").attr({"checked": "checked"});
					
	} else if(i === 3) {//如果资费类型为计时
		
		//先移除
		$("#package").removeAttr("checked");
		$("#monthly").removeAttr("checked");
		
		$("#basicDuration").removeClass("readonly");
		$("#basicCost").removeClass("readonly");
		$("#unitCost").removeClass("readonly");
		
		$("#basicDuration").removeAttr("readonly");
		$("#basicCost").removeAttr("readonly");
		$("#unitCost").removeAttr("readonly");
		
		$("#basicDuration").val("");
		$("#basicCost").val("");
		$("#unitCost").val("");
		
		//再添加
		$("#basicDuration").attr({"readonly": "readonly"});
		$("#basicDuration").addClass("readonly");
		
		$("#basicCost").attr({"readonly": "readonly"});
		$("#basicCost").addClass("readonly");
		
		$("#timeBased").attr({"checked": "checked"});
		
	}
}

//当资费名称失去焦点时激发，正则表达式检验输入是否合法并且通过ajax异步检验名字在数据库中是否存在
function checkCostRepeat() {

	//正则表达式进行前端检验
	if(!/^[\u4E00-\u9FA5A-Za-z0-9_.]{1,50}$/.test($("#costName").val())) {
		$("#info_costName").removeClass("hidden");
		$("#costName").val('');
		return;
	} else {
		$("#info_costName").addClass("hidden");
	}
	
	$.post(
		"cost_checkCostRepeat",
		{id: $("#costId").val(), name: $("#costName").val()},
		function(data) {
			if(data) {//如果重名
				$("#costName").val('');	//清除表单域上资费名字
				alert("资费名字重复，请重新输入");
			} 
		}			
	);
}

//提交表单
function submitForm() {
	//提交前先根据单选设置资费类型和判断必须要填的地方是否已填
	var costType = $("input[type='radio'][checked='checked']");
	if(costType.attr("id") === "monthly") {
		
		if($("#basicCost").val() === "") {
			alert("基本费用不能为空");
			return;
		}

	} else if(costType.attr("id") === "package") {
		
		if($("#basicDuration").val() === "" || $("#basicCost").val() === ""
			|| $("#unitCost").val() === "") {
			alert("基本时长、基本费用和单位费用不能为空");
			return;
		}
			
	} else if(costType.attr("id") === "timeBased") {
		
		if($("#unitCost").val() === "") {
			alert("单位费用不能为空");
			return;
		}
		
	}
	
	if($("#costName").val() === "") {
		alert("资费名称不能为空");
		return;
	}
	
	$("#form_updateCost").submit();		//所有条件满足后，提交表单
}

