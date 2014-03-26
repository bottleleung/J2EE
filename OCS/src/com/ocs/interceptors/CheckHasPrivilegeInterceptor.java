package com.ocs.interceptors;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.ocs.utils.CommonUtil;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 检查是否拥有该权限
 * @author Leslie Leung
 */
public class CheckHasPrivilegeInterceptor extends CommonUtil {

	@Override
	public String intercept(ActionInvocation ai) 
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> session = ai.getInvocationContext().getSession();
		List<Integer> privilegeIds = (List<Integer>) session.get("userPrivileges");

		HttpServletRequest request = ServletActionContext.getRequest();
		//获取浏览器地址栏地址
		StringBuffer url = request.getRequestURL();
		//获取子字符串，用以作判定，如url为http://localhost:8080/OCS/cost/cost_find,截取cost
		String moduleName = url.substring(url.lastIndexOf("/"), url.lastIndexOf("_"));
		//权限id
		int id = moduleNameToInt(moduleName);
		//如果处于common包
		if(id == 0) {
			return ai.invoke();
		}
		//检查该用户的权限id集合里面有没有该从地址栏截取出来的权限id，有的话，可以访问，没有就不可以访问
		//遍历集合
		for(Integer privilegeId: privilegeIds) {
			if(id == privilegeId) {
				return ai.invoke();
			}
		}
		
		return "noPermission";
	}

	/**
	 * 将模块名由字符串转换为数字
	 * @param moduleName 模块名字
	 * @return
	 */
	public int moduleNameToInt(String moduleName) {
		if("/role".equals(moduleName)) {
			return 1;
		} else if("/user".equals(moduleName)) {
			return 2;
		} else if("/cost".equals(moduleName)) {
			return 3;
		} else if("/account".equals(moduleName)) {
			return 4;
		} else if("/business".equals(moduleName)) {
			return 5;
		} else {
			return 0;	//0表示common包
		}
	}
}
