package com.ocs.interfaces.daos;

import java.util.List;
import com.ocs.daos.DAOException;
import com.ocs.entities.Cost;

/**
 * 
 * @author Leslie Leung
 *
 */
public interface ICostDAO {	
	/**
	 * 通过名字查找某个Cost
	 * @param id 当前资费的id
	 * @param name
	 * @return
	 */
	boolean findByName(Integer id, String name) throws DAOException;
	
	/**
	 * 根据id查找cost
	 * @param id
	 * @return 一组资费数据
	 * @throws DAOException
	 */
	Cost findById(int id) throws DAOException;
	
	/**
	 * 找到所有的资费
	 * @return
	 */
	List<Cost> findAll() throws DAOException;
}
