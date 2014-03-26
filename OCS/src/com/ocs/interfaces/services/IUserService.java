package com.ocs.interfaces.services;

import java.util.List;

import com.ocs.services.ServiceException;
import com.ocs.vos.UserVO;

/**
 * 
 * @author Leslie Leung
 */
public interface IUserService {
	/**
	 * 查找用户列表
	 * @param privilegeId 权限id
	 * @param roleName 角色名字
	 * @param page 页码
	 * @param pageSize 页面最大数据量
	 * @return
	 * @throws ServiceException
	 */
	List<UserVO> findByCondition(Integer privilegeId, String roleName,
			int page, int pageSize)
					throws ServiceException;
	
	/**
	 * 查找总页数
	 * @param privilegeId 权限id
	 * @param roleName 角色名字
	 * @param pageSize 页面最大数据容量
	 * @return
	 * @throws ServiceException
	 */
	int findTotalPages(Integer privilegeId, String roleName, int pageSize)
			throws ServiceException;
	
	/**
	 * 添加一个用户
	 * @param user
	 */
	void add(UserVO user) throws ServiceException;
	
	/**
	 * 删除一个用户
	 * @param userId
	 */
	void delete(int userId) throws ServiceException;
	
	/**
	 * 修改某个用户的信息
	 * @param user 某个用户
	 */
	void update(UserVO user) throws ServiceException;
}
