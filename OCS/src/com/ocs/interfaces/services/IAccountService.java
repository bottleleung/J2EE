package com.ocs.interfaces.services;

import java.util.List;
import com.ocs.entities.Account;
import com.ocs.services.ServiceException;

/**
 * 
 * @author Leslie Leung
 */
public interface IAccountService {
	/**
	 * 根据条件查找account
	 * @param idCardNo 身份证
	 * @param realName 姓名
	 * @param loginName 登录名
	 * @param status 状态
	 * @page 某页
	 * @pageSize 某页最大地数据容量
	 * @return 查找出来的account的集合
	 * @throws ServiceException
	 */
	List<Account> findByCondition(String idCardNo, String realName,
			String loginName, String status, int page, int pageSize) 
					throws ServiceException;
	
	/**
	 * 暂停某账户账号
	 * @param id 该账户账号id
	 * @throws ServiceException
	 */
	void pause(int id) throws ServiceException;
	
	/**
	 * 删除某个账户账号
	 * @param id 该账户账号id
	 * @throws ServiceException
	 */
	void delete(int id) throws ServiceException;
	
	/**
	 * 查找总页数
	 * @param idCardNo 身份证
	 * @param realName 姓名
	 * @param loginName 登录名
	 * @param status 状态
	 * @param pageSize 页面最大数据容量
	 * @return 总页数
	 * @throws ServiceException
	 */
	int findTotalPages(String idCardNo, String realName,
			String loginName, String status, int pageSize) 
			throws ServiceException;
	
	/**
	 * 开通账务账号
	 * @param id 账务账号id
	 * @throws ServiceException
	 */
	void start(int id) throws ServiceException;
	
	/**
	 * 添加一个account
	 * @param account
	 */
	void add(Account account) throws ServiceException;
	
	/**
	 * 修改account
	 * @param account
	 */
	void update(Account account) throws ServiceException;
	
	/**
	 * 找到一个账户账号的详细信息
	 * @param id
	 * @return
	 */
	Account detail(int id) throws ServiceException;
}
