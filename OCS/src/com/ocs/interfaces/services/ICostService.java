package com.ocs.interfaces.services;
import java.util.List;

import com.ocs.entities.Cost;
import com.ocs.services.ServiceException;

/**
 * 
 * @author Leslie Leung
 */
public interface ICostService {
	/**
	 * 返回某cost的详细信息
	 * @param id
	 * @return
	 */
	Cost detail(int id) throws ServiceException;
	
	/**
	 * 启用某资费
	 * @param id 该资费id
	 */
	void start(int id) throws ServiceException;
	
	/**
	 * 修改一条资费数据
	 * @param cost 该资费数据
	 * @throws ServiceException
	 */
	void update(Cost cost) throws ServiceException;
	
	/**
	 * 删除资费模块中的一条数据
	 * @param id 目标数据的id
	 * @throws ServiceException
	 */
	void delete(int id) throws ServiceException;
	
	/**
	 * 往数据库中插入一条资费数据
	 * @param cost
	 * @throws ServiceException
	 */
	void add(Cost cost) throws ServiceException;
	
	/**
	 * 按照分页找到一组资费数据
	 * @param page 页码
	 * @param pageSize 页面最大数据容量
	 * @return
	 * @throws ServiceException
	 */
	List<Cost> find(int page, int pageSize) throws ServiceException;
	
	/**
	 * 找到总页数
	 * @param pageSize 页面最大数据容量
	 * @return
	 * @throws ServiceException
	 */
	int findTotalPages(int pageSize) throws ServiceException;
}
