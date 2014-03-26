package com.ocs.interfaces.services;

import java.util.List;

import com.ocs.services.ServiceException;
import com.ocs.vos.RoleVO;

/**
 * 
 * @author Leslie Leung
 */
public interface IRoleService {
	/**
	 * 根据分页找到某页的所有数据
	 * @param page 某页
	 * @param pageSize 页面最大数据容量
	 * @return
	 * @throws ServiceException
	 */
	List<RoleVO> find(int page, int pageSize) throws ServiceException;
	
	/**
	 * 找到总页数
	 * @param pageSize 页面最大数据容量
	 * @return
	 */
	int findTotalPages(int pageSize) throws ServiceException;
	
	/**
	 * 新增role
	 * @param role
	 * @throws ServiceException
	 */
	void add(RoleVO role) throws ServiceException;
	
	/**
	 * 删除一个角色
	 * @param id
	 * @throws ServiceException
	 */
	void delete(int id) throws ServiceException;
	
	/**
	 * 修改role
	 * @param r
	 * @throws ServiceException
	 */
	void update(RoleVO role) throws ServiceException;
}
