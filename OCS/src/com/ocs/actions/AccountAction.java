package com.ocs.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.daos.DAOException;
import com.ocs.entities.Account;
import com.ocs.interfaces.daos.IAccountDAO;
import com.ocs.interfaces.services.IAccountService;
import com.ocs.interfaces.services.IBusinessService;
import com.ocs.services.ServiceException;

/**
 * 处理账务账号的action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class AccountAction {
	private boolean isDeleteSuccess;	//是否删除成功
	private boolean isOldPasswordInputRight;	//旧密码是否输入正确
	private boolean isRecommenderExist;	//推荐人是否存在
	private boolean isRealNameExist;	//真实姓名是否存在
	private boolean isLoginNameExist;	//登录名是否存在
	private boolean isIdCardNoExist;	//身份证号是否存在
	private boolean isTelephoneExist;	//检查电话号码是否存在
	private int id;	//id
	private String telephone;	//手机号码
	private String idCardNo;	//身份证
	private String realName;	//姓名
	private String loginName;	//登录名
	private String status;		//状态
	private int page = 1;	//默认打开第一页
	private int pageSize;	//一个页面的最大数据量
	private int totalPages;		//总页数
	private Account account;	//一个账户账号数据
	private List<Account> accounts;		//查出来的一系列account
	
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IBusinessService businessService;
	@Autowired
	private IAccountDAO accountDAO;
	
	/**
	 * 根据条件查找account
	 * @return
	 */
	public String find() {
		try {
			accounts = accountService.findByCondition(idCardNo, realName, loginName, status, page, pageSize);
			totalPages = accountService.findTotalPages(idCardNo, realName, loginName, status, pageSize);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findSuccess";
	}
	
	/**
	 * 检查数据库中是否存在该姓名
	 * @return
	 */
	public String checkRealName() {
		try {
			if(accountDAO.findRealName(realName)) {
				isRealNameExist = true;
			} else {
				isRealNameExist = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findRealNameSuccess";
	}
	
	/**
	 * 检查身份证号是否存在
	 * @return
	 */
	public String checkIdCardNo() {
		try {
			if(accountDAO.findIdCardNo(idCardNo)) {
				isIdCardNoExist = true;
			} else {
				isIdCardNoExist = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findIdCardNoSuccess";
	}
	
	/**
	 * 检查登录名是否存在
	 * @return
	 */
	public String checkLoginName() {
		try {
			if(accountDAO.findLoginName(loginName)) {
				isLoginNameExist = true;
			} else {
				isLoginNameExist = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findLoginNameSuccess";
	}
	
	/**
	 * 检查手机号码是否存在
	 * @return
	 */
	public String checkTelephone() {
		try {
			if(accountDAO.findTelephone(telephone)) {
				isTelephoneExist = true;
			} else {
				isTelephoneExist = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findTelephoneSuccess";
	}
	
	/**
	 * 检查推荐人输入是否存在
	 * @return
	 */
	public String checkRecommenderExist() {
		try {
			if(accountDAO.findIdCardNo(idCardNo) == true) {
				isRecommenderExist = true;
			} else {
				isRecommenderExist = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "isRecommenderExist";
	}
	
	/**
	 * 开通账号
	 * @return
	 */
	public String start() {
		try {
			accountService.start(id);
		} catch(ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "startSuccess";
	}
		
	/**
	 * 暂停某账户账号，同时暂停其下的所有业务账号
	 * @return
	 */
	public String pause() {
		try {
			accountService.pause(id);
			businessService.pauseByAccount(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "pauseSuccess";
	}
	
	/**
	 * 删除某个账户账号，同时删除其下的所有业务账号
	 * @return
	 */
	public String delete() {
		try {
			accountService.delete(id);
			businessService.deleteByAccount(id);
			isDeleteSuccess = true;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isDeleteSuccess = false;
		}
		
		return "isDeleteSuccess";
		
	}
	
	/**
	 * 跳转到增加account界面
	 * @return
	 */
	public String toAddAccount() {
		return "toAddAccount";
	}
	
	/**
	 * 跳转到修改account界面
	 * @return
	 */
	public String toUpdateAccount() {
		try {
			account = accountDAO.findById(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "toUpdateAccountSuccess";
	}
	
	/**
	 * 查看一个账户账号的详细信息
	 * @return
	 */
	public String detail() {
		try {
			account = accountService.detail(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "findDetailSuccess";
	}
	
	/**
	 * 增加一个account
	 * @return
	 */
	public String add() {
		try {
			accountService.add(account);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "addSuccess";
	}
	
	/**
	 * 通过身份证查找推荐人id
	 * @return
	 */
	public String findRecommenderId() {
		try {
			id = accountDAO.findIdByIdCardNo(idCardNo);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findRecommenderIdSuccess";
	}
	
	/**
	 * 修改一条account数据
	 * @return
	 */
	public String update() {
		try {
			accountService.update(account);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "updateSuccess";
	}
 	
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
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

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public boolean getIsStartSuccess() {
//		return isStartSuccess;
//	}
//
//	public void setStartSuccess(boolean isStartSuccess) {
//		this.isStartSuccess = isStartSuccess;
//	}

	public boolean getIsRecommenderExist() {
		return isRecommenderExist;
	}

	public void setRecommenderExist(boolean isRecommenderExist) {
		this.isRecommenderExist = isRecommenderExist;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean getIsDeleteSuccess() {
		return isDeleteSuccess;
	}

	public void setDeleteSuccess(boolean isDeleteSuccess) {
		this.isDeleteSuccess = isDeleteSuccess;
	}

	public boolean getIsOldPasswordInputRight() {
		return isOldPasswordInputRight;
	}

	public void setOldPasswordInputRight(boolean isOldPasswordInputRight) {
		this.isOldPasswordInputRight = isOldPasswordInputRight;
	}

	public boolean getIsRealNameExist() {
		return isRealNameExist;
	}

	public void setRealNameExist(boolean isRealNameExist) {
		this.isRealNameExist = isRealNameExist;
	}

	public boolean getIsLoginNameExist() {
		return isLoginNameExist;
	}

	public void setLoginNameExist(boolean isLoginNameExist) {
		this.isLoginNameExist = isLoginNameExist;
	}

	public boolean getIsIdCardNoExist() {
		return isIdCardNoExist;
	}

	public void setIdCardNoExist(boolean isIdCardNoExist) {
		this.isIdCardNoExist = isIdCardNoExist;
	}

	public boolean getIsTelephoneExist() {
		return isTelephoneExist;
	}

	public void setTelephoneExist(boolean isTelephoneExist) {
		this.isTelephoneExist = isTelephoneExist;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public IAccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public IBusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}
	
	
}
