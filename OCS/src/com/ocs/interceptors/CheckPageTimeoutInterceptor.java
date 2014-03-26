package com.ocs.interceptors;

import java.util.Map;

import com.ocs.utils.CommonUtil;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 检测网页是否超时的拦截器
 * @author Leslie Leung
 */
public class CheckPageTimeoutInterceptor extends CommonUtil {
	@Override
	public String intercept(ActionInvocation ai) 
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> session = ai.getInvocationContext().getSession();
		String userCode = (String) session.get("user");
		
		if(userCode == null) {//如果userCode为null，也就是说网页超时了
			return "pageTimeout";
		} else {//如果userCode不为null，证明网页没超时，可以继续在网页上进行交互操作
			return ai.invoke();
		}
	}
}
