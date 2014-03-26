package com.ocs.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.daos.DAOException;
import com.ocs.entities.Account;
import com.ocs.entities.Business;
import com.ocs.entities.Cost;
import com.ocs.interfaces.daos.IAccountDAO;
import com.ocs.interfaces.daos.IBusinessDAO;
import com.ocs.interfaces.daos.ICostDAO;
import com.ocs.interfaces.services.IBusinessService;
import com.ocs.services.ServiceException;
import com.ocs.vos.BusinessVO;

/**
 * 处理业务账号模块的action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class BusinessAction {
	private int id;	//业务账号id
	private boolean isStartSuccess;		//是否开通成功
	private boolean isPauseSuccess;		//是否暂停成功
	private boolean isDeleteSuccess;	//是否删除成功
	private boolean isIdCardNoExist;	//某身份证是否存在
	private boolean isBusinessRepeat;	//业务账号是否重复
	private boolean isAccountPauseOrDelete;		//某账户账号是不是处于暂停或开通状态
	private String osUserName;	//用户名
	private String unixHost;	//服务器主机ip
	private String idCardNo;	//对应的account的身份证号
	private String status;		//状态
	private int page = 1;	//默认打开第一页
	private int pageSize;	//页面最大数据容量
	private List<BusinessVO> businessVOs;	//一系列BusinessVO数据
	private int totalPages;		//总页数
	private Business business;		//Business实例对象
	private BusinessVO bvo;		//BusinessVO实例对象
	private Account account;		//账户账号
	private List<Cost> costs;	//所有cost的集合
	
	@Autowired
	private IBusinessService businessService;
	@Autowired
	private IBusinessDAO businessDAO;
	@Autowired
	private IAccountDAO accountDAO;
	@Autowired
	private ICostDAO costDAO;

	/**
	 * 查找业务账号
	 * @return
	 */
	public String find() {
		try {
			businessVOs = businessService.findByCondition(osUserName, unixHost, idCardNo, status, page, pageSize);
			totalPages = businessService.findTotalPages(osUserName, unixHost, idCardNo, status, pageSize);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findSuccess";
	}
	
	/**
	 * 开通业务账号
	 * @return
	 */
	public String start() {
		try {
			businessService.start(id);
			isStartSuccess = true;

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isStartSuccess = false;
		}
		return "isStartSuccess";
	}

	/**
	 * 暂停业务账号
	 * @return
	 */
	public String pause() {
		try {
			businessService.pause(id);
			isPauseSuccess = true;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isPauseSuccess = false;
		}
		
		return "isPauseSuccess";
	}
	
	/**
	 * 检查某业务账号对应的账户账号是否处于暂停或删除状态
	 * @return
	 */
	public String isAccountPauseOrDelete() {
		try {
			if(accountDAO.checkAccountPauseOrDelete(id)) {
				isAccountPauseOrDelete = true;
			} else {
				isAccountPauseOrDelete = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "checkAccountPauseOrDeleteSuccess";
	}
	
	/**
	 * 添加一个业务
	 * @return
	 */
	public String add() {
		try {
			businessService.add(bvo);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "addSuccess";
	}
	
	/**
	 * 检查前端传入的账户账号是否重复
	 * @return
	 */
	public String checkBusinessRepeat() {
		try {
			if(businessDAO.checkBusinessRepeat(unixHost, osUserName)) {
				isBusinessRepeat = true;
			} else {
				isBusinessRepeat = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "checkBusinessRepeatSuccess";
	}
	
	/**
	 * 检查身份证号是否存在
	 * @return
	 */
	public String checkIdCardNoExist() {
		try {
			if(accountDAO.findIdCardNo(idCardNo) == true) {
				isIdCardNoExist = true;
			} else {
				isIdCardNoExist = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "checkIdCardNoExistSuccess";
	}
	
	/**
	 * 根据身份证号寻找账务账号
	 * @return
	 */
	public String findAccount() {
		try {
			account = businessService.findAccountByCardNo(idCardNo);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "findAccountSuccess";
	}
	
	/**
	 * 删除某个业务账号，并不真正删除，只是把它设置为关闭状态
	 * @return
	 */
	public String delete() {
		try {
			businessService.delete(id);
			isDeleteSuccess = true;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isDeleteSuccess = false;
		}
		return "isDeleteSuccess";
	}
	
	/**
	 * 跳转到增加业务账号页面
	 * @return
	 */
	public String toAddBusiness() {
		try {
			costs = costDAO.findAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "toAddBusinessSuccess";
	}
	
	/**
	 * 跳转到修改业务账号页面
	 * @return
	 */
	public String toUpdateBusiness() {
		try {
			business = businessDAO.findById(id);
			costs = costDAO.findAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "toUpdateBusinessSuccess";
	}
	
	/**
	 * 修改业务账号，并不真正操作业务账号表，而是插入到
	 * bak表中，待月结时自动更新
	 * @return
	 */
	public String update() {
		try {
			businessService.update(business);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "updateSuccess";
	}

	public String getOsUserName() {
		return osUserName;
	}
	public void setOsUserName(String osUserName) {
		this.osUserName = osUserName;
	}
	public String getUnixHost() {
		return unixHost;
	}
	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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

	public List<BusinessVO> getBusinessVOs() {
		return businessVOs;
	}

	public void setBusinessVOs(List<BusinessVO> businessVOs) {
		this.businessVOs = businessVOs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getIsStartSuccess() {
		return isStartSuccess;
	}

	public void setStartSuccess(boolean isStartSuccess) {
		this.isStartSuccess = isStartSuccess;
	}

	public boolean getIsPauseSuccess() {
		return isPauseSuccess;
	}

	public void setPauseSuccess(boolean isPauseSuccess) {
		this.isPauseSuccess = isPauseSuccess;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean getIsDeleteSuccess() {
		return isDeleteSuccess;
	}

	public void setDeleteSuccess(boolean isDeleteSuccess) {
		this.isDeleteSuccess = isDeleteSuccess;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public boolean getIsIdCardNoExist() {
		return isIdCardNoExist;
	}

	public void setIdCardNoExist(boolean isIdCardNoExist) {
		this.isIdCardNoExist = isIdCardNoExist;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BusinessVO getBvo() {
		return bvo;
	}

	public void setBvo(BusinessVO bvo) {
		this.bvo = bvo;
	}

	public List<Cost> getCosts() {
		return costs;
	}

	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}

	public boolean getIsBusinessRepeat() {
		return isBusinessRepeat;
	}

	public void setBusinessRepeat(boolean isBusinessRepeat) {
		this.isBusinessRepeat = isBusinessRepeat;
	}

	public void setAccountPauseOrDelete(boolean isAccountPauseOrDelete) {
		this.isAccountPauseOrDelete = isAccountPauseOrDelete;
	}

	public boolean getIsAccountPauseOrDelete() {
		return isAccountPauseOrDelete;
	}

	public IBusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}

	public IBusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	public IAccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public ICostDAO getCostDAO() {
		return costDAO;
	}

	public void setCostDAO(ICostDAO costDAO) {
		this.costDAO = costDAO;
	}
	
	
}
