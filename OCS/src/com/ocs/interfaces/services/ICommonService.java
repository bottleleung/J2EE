package com.ocs.interfaces.services;
import com.ocs.services.ServiceException;
import com.ocs.vos.UserVO;

/**
 * 
 * @author Leslie Leung
 */
public interface ICommonService {
	/**
	 * 更新用户个人信息
	 * @param user
	 * @throws ServiceException
	 */
	void updateUserInfo(UserVO user) throws ServiceException;
	
	/**
	 * 修改用户密码
	 * @throws ServiceException
	 */
	void updateUserPassword(String password, String userCode) throws ServiceException;
}
