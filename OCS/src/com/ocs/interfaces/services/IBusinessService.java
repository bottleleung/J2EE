package com.ocs.interfaces.services;

import java.util.List;

import com.ocs.entities.Account;
import com.ocs.entities.Business;
import com.ocs.services.ServiceException;
import com.ocs.vos.BusinessVO;

/**
 * 
 * @author Leslie Leung
 */
public interface IBusinessService {
	/**
	 * 根据条件查找一系列BusinessVO数据
	 * @param osUserName 用户名
	 * @param unixHost 服务器ip
	 * @param idCardNo 身份证号
	 * @param status 状态
	 * @param page 某页
	 * @param pageSize 页面数据容量
	 * @return 一组BusinessVO数据
	 * @throws ServiceException
	 */
	List<BusinessVO> findByCondition(String osUserName, String unixHost, String idCardNo, 
			String status, 
			int page, int pageSize)
					throws ServiceException;
	
	/**
	 * 暂停某账户账号时，将其下的业务账号都暂停
	 * @param id
	 * @throws ServiceException
	 */
	void pauseByAccount(int id) throws ServiceException;
	
	/**
	 * 删除某个账户账号时，将其下的所有业务账号都删除
	 * @param id 账户账号id
	 */
	void deleteByAccount(int id) throws ServiceException;
	
	/**
	 * 找到总页数
	 * @param osUserName 用户名
	 * @param unixHost 服务器ip
	 * @param idCardNo 身份证号
	 * @param status 状态
	 * @param pageSize 页面最大数据容量
	 * @return
	 */
	int findTotalPages(String osUserName, String unixHost, String idCardNo, 
			String status, int pageSize) 
				throws ServiceException;
	
	/**
	 * 删除某个业务账号
	 * @param id 业务账号id
	 */
	void delete(int id) throws ServiceException;
	
	/**
	 * 修改Business
	 * @param b
	 */
	void update(Business b) throws ServiceException;
	
	/**
	 * 开通某业务
	 * @param id 业务账号id
	 */
	void start(int id) throws ServiceException;
	
	/**
	 * 暂停某业务
	 * @param id
	 */
	void pause(int id) throws ServiceException;
	
	/**
	 * 添加一个业务账号
	 * @param business 
	 * @throws ServiceException
	 */
	void add(BusinessVO business) throws ServiceException;
	
	/**
	 * 根据身份证号查找账户账号
	 * @param idCardNo 身份证号
	 * @return
	 */
	Account findAccountByCardNo(String idCardNo) throws ServiceException;
}
