package com.ocs.actions;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.daos.DAOException;
import com.ocs.interfaces.daos.IUserDAO;
import com.ocs.interfaces.services.ICommonService;
import com.ocs.services.ServiceException;
import com.ocs.utils.CommonUtil;
import com.ocs.vos.UserVO;

/**
 * 处理一些通用功能的action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class CommonAction extends CommonUtil {
	
	private UserVO user;	//当前用户
	private String password;	//用户密码
	private String userRoles;	//用户所有角色组合成的字符串
	
	@Autowired
	private ICommonService commonService;
	@Autowired
	private IUserDAO userDAO;
	
	/**
	 * 跳转到首页
	 * @return
	 */
	public String toIndex() {
		return "toIndex";
	}
	
	/**
	 * 跳转到没有权限页面
	 * @return
	 */
	public String toNoPermission() {
		return "toNoPermission";
	}
	
	/**
	 * 修改用户的个人信息
	 * @return
	 */
	public String updateUserInfo() {
		try {
			commonService.updateUserInfo(user);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "updateUserInfoSuccess";
	}
	
	/**
	 * 重置用户密码
	 * @return
	 */
	public String resetUserPassword() {
		String userCode = (String) session.get("user");
		try {
			commonService.updateUserPassword(password, userCode);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "resetUserPasswordSuccess";
	}
	
	/**
	 * 跳转到修改用户密码页面
	 * @return
	 */
	public String toResetPassword() {
		String userCode = (String) session.get("user");
		try {
			user = userDAO.findByUserCode(userCode);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "toResetPasswordSuccess";
	}
	
	/**
	 * 跳转到修改用户个人信息页面
	 * @return
	 */
	public String toUpdateUserInfo() {
		String userCode = (String) session.get("user");
		try {
			user = userDAO.findByUserCode(userCode);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "toUpdateUserInfoSuccess";
	}
	
	/**
	 * 退出系统，返回到登录页面，需要清除session
	 * @return
	 */
	public String exit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "exitSuccess";
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	public ICommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
}
