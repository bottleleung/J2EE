package com.ocs.services;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.ocs.entities.Cost;
import com.ocs.interfaces.services.ICostService;
import com.ocs.mappers.cost.CostMapper;

/**
 * 资费管理的业务逻辑
 * @author Leslie Leung
 */
@Scope("prototype")
@Service
public class CostService extends JdbcDaoSupport implements ICostService {
	
	/**
	 * 注入连接池
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * 返回某cost的详细信息
	 * @param id
	 * @return
	 */
	public Cost detail(int id) 
			throws ServiceException {
		String sql = "SELECT * FROM cost WHERE id=?";
		Object[] params = {id};
		Cost c = null;
		try {
			c = this.getJdbcTemplate().queryForObject(sql, params, new CostMapper());
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("查看资费详情失败", e);
		}
		return c;
	}
	
	/**
	 * 启用某资费
	 * @param id 该资费id
	 */
	public void start(int id) 
			throws ServiceException {
		String sql = "UPDATE cost SET status='1',starttime=sysdate WHERE id=?";
		Object[] params = {id};
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("开通某资费失败", e);
		}		
	}

	/**
	 * 修改一条资费数据
	 * @param cost 该资费数据
	 * @throws ServiceException
	 */
	public void update(Cost cost) 
			throws ServiceException {
		if(cost == null) {
			return;
		}
		
		//后端作正则表达式检验
		if(!cost.getName().matches("^[\u4E00-\u9FA5A-Za-z0-9_.]{1,50}$")) {
			return;
		}
		
		String sql = "UPDATE cost SET name=?," +
				"base_duration=?,base_cost=?," +
				"unit_cost=?,descr=?,cost_type=? " +
				"WHERE id=?";
		
		Object[] params = {cost.getName(), cost.getBasicDuration(),
				cost.getBasicCost(), cost.getUnitCost(), cost.getDescr(),
				cost.getCostType(), cost.getId()};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改cost数据失败", e);
		}		
	}
	
	/**
	 * 删除资费模块中的一条数据
	 * @param id 目标数据的id
	 * @throws ServiceException
	 */
	public void delete(int id)
			throws ServiceException {
		String sql = "DELETE FROM cost WHERE id=?";
		Object[] params = {id};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除资费数据失败", e);
		}
	}
	
	/**
	 * 往数据库中插入一条资费数据
	 * @param cost
	 * @throws ServiceException
	 */
	public void add(Cost cost) 
			throws ServiceException {
		
		if(cost == null) {
			return;
		}
		
		//后端作正则表达式检验
		if(!cost.getName().matches("^[\u4E00-\u9FA5A-Za-z0-9_.]{1,50}$")) {
			return;
		}
		
		String sql = "INSERT INTO cost " +
				"VALUES(cost_seq.nextval,?,?,?,?,'0',?,sysdate,null,?)";
		
		Object[] params = {cost.getName(), cost.getBasicDuration(),
				cost.getBasicCost(), cost.getUnitCost(),
				cost.getDescr(), cost.getCostType()};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增资费数据失败！",e);
		}		
	}
	
	/**
	 * 按照分页找到一组资费数据
	 * @param page 页码
	 * @param pageSize 页面最大数据容量
	 * @return
	 * @throws ServiceException
	 */
	public List<Cost> find(int page, int pageSize) 
			throws ServiceException {
		
		String sql = "SELECT * FROM (" +
				" SELECT c.*,rownum r FROM cost c WHERE rownum<?" +
				") WHERE r>? ";
		
		Object[] params = {page*pageSize+1, (page-1)*pageSize};
		
		try {
			List<Cost> costs = this.getJdbcTemplate().query(sql, params, new CostMapper());
			return costs;
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("Cost模块分页查询出错", e);
		}
	}
	
	/**
	 * 找到总页数
	 * @param pageSize 页面最大数据容量
	 * @return
	 * @throws ServiceException
	 */
	public int findTotalPages(int pageSize) 
			throws ServiceException {
		
		String sql = "SELECT count(id) FROM cost";
		int rows;
		try {
			rows = this.getJdbcTemplate().queryForInt(sql);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("Cost模块查询总页数出错", e);
		}
				
		/* 根据总行数，查询总页数 */
		if(rows % pageSize == 0) {
			return rows / pageSize;
		} else {
			return rows / pageSize + 1;
		}
	}
}
