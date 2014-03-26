package com.ocs.interfaces.daos;

import java.sql.ResultSet;
import java.util.List;

import com.ocs.daos.DAOException;
import com.ocs.vos.UserVO;

/**
 * 
 * @author Leslie Leung
 *
 */
public interface IUserDAO {
	/**
	 * 通过id查找UserVO
	 * @param id
	 * @return
	 */
	UserVO findById(int id) throws DAOException;
	
	/**
	 * 通过用户账号来查找用户
	 * @param userCode 用户名
	 * @return
	 */
	UserVO findByUserCode(String userCode) throws DAOException;
	
	/**
	 * 检查用户账号是否重复
	 * @param userCode
	 * @return
	 * @throws DAOException
	 */
	boolean checkUserCodeRepeat(String userCode) throws DAOException;
	
	/**
	 * 检查用户的名字是否重复
	 * @param userName
	 * @return
	 */
	boolean checkUserNameRepeat(String userName) throws DAOException;
	
	/**
	 * 检查手机号码是否重复
	 * @param telephone
	 * @return
	 */
	boolean checkTelephoneRepeat(String telephone) throws DAOException;
	
	/**
	 * 根据用户账号查找当前用户具有的权限id集合
	 * @param userCode
	 */
	public List<Integer> findUserPrivilegeIds(String userCode) throws DAOException;
}
