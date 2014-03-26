package com.ocs.interfaces.daos;

import com.ocs.daos.DAOException;
import com.ocs.vos.UserVO;

/**
 * 
 * @author Leslie Leung
 *
 */
public interface ICommonDAO {
	/**
	 * 检查旧密码是否输入正确
	 * @param password 旧密码
	 * @return
	 * @throws DAOException
	 */
	boolean checkPassword(UserVO user) throws DAOException;
}
