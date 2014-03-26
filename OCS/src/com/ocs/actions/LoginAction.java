package com.ocs.actions;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.interfaces.services.ILoginService;
import com.ocs.services.ServiceException;
import com.ocs.utils.CommonUtil;

/**
 * 处理登录的action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class LoginAction extends CommonUtil {
	private String userCode;		//登录名
	private String password;	//密码
	private String checkCode;	//验证码
	private InputStream checkCodeImgStream;		//验证码图片流
	private int loginStatus = 1;	//登录状态，1表示登录成功，0表示登录失败，默认跳转到登录页面时为1
	
	@Autowired
	private ILoginService loginService;
	
	/**
	 * 控制其跳转回登录界面
	 * @return
	 */
	public String toLogin() {
		return "toLogin";
	}
	
	/**
	 * 用于控制登录业务逻辑
	 * @return
	 */
	public String login() {
		try {
			loginStatus = loginService.check(userCode, password, checkCode, session);
			if(loginStatus == 0) {
				return "loginFail";
			} else {
				return "loginSuccess";
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * 该方法用于控制生成验证码图片
	 * @return
	 */
	public String createCheckCodeImg() {
		try {
			checkCodeImgStream = loginService.generateCheckCode(session);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "createCheckCodeImgSuccess";
	}


	public InputStream getCheckCodeImgStream() {
		return checkCodeImgStream;
	}


	public void setCheckCodeImgStream(InputStream checkCodeImgStream) {
		this.checkCodeImgStream = checkCodeImgStream;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public int getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
}
