package com.ocs.interfaces.daos;

import com.ocs.daos.DAOException;
import com.ocs.entities.Account;

/**
 * 
 * @author Leslie Leung
 *
 */
public interface IAccountDAO {
	/**
	 * 根据id查询account
	 * @param id
	 * @return account
	 */
	Account findById(int id) throws DAOException;
	
	/**
	 * 查找身份证号是否存在
	 * @param idCardNo 身份证号
	 * @return 
	 */
	boolean findIdCardNo(String idCardNo) throws DAOException;
	
	/**
	 * 根据身份证号查找id
	 * @param idCardNo
	 * @return
	 */
	Integer findIdByIdCardNo(String idCardNo) throws DAOException;
	
	/**
	 * 检查该业务账号对应的账户账号是否暂停或已删除
	 * @param id
	 */
	boolean checkAccountPauseOrDelete(int id) throws DAOException;
	
	/**
	 * 查找姓名是否存在
	 * @param realName 姓名
	 * @return 
	 */
	boolean findRealName(String realName) throws DAOException;
	
	/**
	 * 查找登录名是否存在
	 * @param loginName 登录名
	 * @return 
	 */
	boolean findLoginName(String loginName) throws DAOException;
	
	/**
	 * 查找电话是否存在
	 * @param loginName 登录名
	 * @return 
	 */
	boolean findTelephone(String telephone) throws DAOException;
}
