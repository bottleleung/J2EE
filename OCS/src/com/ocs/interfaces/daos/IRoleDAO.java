package com.ocs.interfaces.daos;

import java.util.List;
import com.ocs.daos.DAOException;
import com.ocs.vos.RoleVO;

/**
 * 
 * @author Leslie Leung
 *
 */
public interface IRoleDAO {
	/**
	 * 通过id查找RoleVO
	 * @param id
	 * @return
	 */
	RoleVO findById(int id) throws DAOException;
	
	/**
	 * 查找所有角色
	 * @return
	 * @throws DAOException
	 */
	List<RoleVO> findAll() throws DAOException;
	
	/**
	 * 检查数据库中是否存在这个角色名字
	 * @param roleName
	 * @return
	 */
	boolean checkRoleName(String roleName) throws DAOException;
}
