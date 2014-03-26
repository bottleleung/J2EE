package com.ocs.interfaces.daos;

import com.ocs.daos.DAOException;
import com.ocs.entities.Business;

/**
 * 
 * @author Leslie Leung
 *
 */
public interface IBusinessDAO {
	/**
	 * 通过id查找Business
	 * @param id
	 * @return
	 */
	Business findById(int id) throws DAOException;
	
	/**
	 * 检查前端传入的business是否重复
	 * @return
	 */
	boolean checkBusinessRepeat(String unixHost, String osUserName) throws DAOException;
}
