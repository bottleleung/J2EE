package com.ocs.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.daos.DAOException;
import com.ocs.daos.DAOFactory;
import com.ocs.daos.RoleDAO;
import com.ocs.daos.UserDAO;
import com.ocs.interfaces.daos.IRoleDAO;
import com.ocs.interfaces.daos.IUserDAO;
import com.ocs.interfaces.services.IUserService;
import com.ocs.services.ServiceException;
import com.ocs.services.ServiceFactory;
import com.ocs.services.UserService;
import com.ocs.vos.RoleVO;
import com.ocs.vos.UserVO;

/**
 * 用户模块action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class UserAction {
	private int page = 1;	//默认显示第一页
	private int pageSize;	//页面最大数据容量
	private List<UserVO> users;	//一系列User数据
	private int privilegeId;	//权限id
	private String roleName;	//角色名字
	private int userId;		//用户id
	private int totalPages;		//总页数
	private List<RoleVO> roles;		//一些列RoleVO数据
	private UserVO user;	//一个用户
	private String userName;	//用户的姓名
	private boolean isUserNameRepeat;	//用户姓名是否重复
	private boolean isTelephoneRepeat;	//用户手机号码是否重复
	private boolean isUserCodeRepeat;	//检查用户账号是否重复
	private String telephone;	//手机号码
	private String userCode;	//用户账号
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private IRoleDAO roleDAO;
	
	/**
	 * 查找用户数据
	 * @return
	 */
	public String find() {
		try {
			users = userService.findByCondition(privilegeId, roleName, page, pageSize);
			totalPages = userService.findTotalPages(privilegeId, roleName, pageSize);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findSuccess";
	}
	
	/**
	 * 检查用户名字是否重复
	 * @return
	 */
	public String checkNameRepeat() {
		try {
			if(userDAO.checkUserNameRepeat(userName)) {
				isUserNameRepeat = true;
			} else {
				isUserNameRepeat = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "checkNameRepeatSuccess";
	}
	
	/**
	 * 检查手机号码是否重复
	 * @return
	 */
	public String checkTelephoneRepeat() {
		try {
			if(userDAO.checkTelephoneRepeat(telephone)) {
				isTelephoneRepeat = true;
			} else {
				isTelephoneRepeat = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "checkTelephoneRepeatSuccess";
	}
	
	/**
	 * 删除一个用户数据
	 * @return
	 */
	public String delete() {
		try {
			userService.delete(userId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "deleteSuccess";
	}
	
	/**
	 * 跳转到增加页面,需要找到所有角色的名字，以便供新增时做选择
	 * @return
	 */
	public String toAddUser() {
		try {
			roles = roleDAO.findAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "toAddUserSuccess";
	}
	
	/**
	 * 新增一个用户
	 * @return
	 */
	public String add() {
		try {
			userService.add(user);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "addSuccess";
	}
	
	/**
	 * 跳转到修改user页面
	 * @return
	 */
	public String toUpdateUser() {
		try {
			user = userDAO.findById(userId);
			roles = roleDAO.findAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "toUpdateUserSuccess";
	}
	
	/**
	 * 检查用户账号是否重复
	 * @return
	 */
	public String checkUserCodeRepeat() {
		try {
			if(userDAO.checkUserCodeRepeat(userCode)) {
				isUserCodeRepeat = true;
			} else {
				isUserCodeRepeat = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "checkUserCodeRepeatSuccess";
	}
	
	/**
	 * 修改某个用户信息
	 * @return
	 */
	public String update() {
		try {
			userService.update(user);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "updateSuccess";
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public List<UserVO> getUsers() {
		return users;
	}

	public void setUsers(List<UserVO> users) {
		this.users = users;
	}

	public int getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<RoleVO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleVO> roles) {
		this.roles = roles;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean getIsUserNameRepeat() {
		return isUserNameRepeat;
	}

	public void setUserNameRepeat(boolean isUserNameRepeat) {
		this.isUserNameRepeat = isUserNameRepeat;
	}

	public boolean getIsTelephoneRepeat() {
		return isTelephoneRepeat;
	}

	public void setTelephoneRepeat(boolean isTelephoneRepeat) {
		this.isTelephoneRepeat = isTelephoneRepeat;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public boolean getIsUserCodeRepeat() {
		return isUserCodeRepeat;
	}

	public void setUserCodeRepeat(boolean isUserCodeRepeat) {
		this.isUserCodeRepeat = isUserCodeRepeat;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
}
