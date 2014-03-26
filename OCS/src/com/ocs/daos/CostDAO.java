package com.ocs.daos;


import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.ocs.entities.Cost;
import com.ocs.interfaces.daos.ICostDAO;
import com.ocs.mappers.cost.CostMapper;


/**
 * DAO接口实现类CostDAO，处理资费
 * @author Leslie Leung
 */
@Scope("prototype")
@Repository
public class CostDAO extends JdbcDaoSupport implements ICostDAO {
	
	/**
	 * 注入连接池
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
		
	/**
	 * 通过名字查找某个Cost
	 * @param id 当前资费的id
	 * @param name
	 * @return
	 */
	public boolean findByName(Integer id, String name)
			throws DAOException {
		if(name == null || name.length() == 0) {
			return false;
		}
				
		String sql = "SELECT id FROM cost WHERE name=? ";
		Object[] params = {name};
		List<Map<String, Object>> rows = null;
		try {
			rows = this.getJdbcTemplate().queryForList(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("按名称查询资费数据失败！",e);
		}
				
		if(rows != null && !rows.isEmpty() && !id.equals(Integer.parseInt(rows.get(0).get("id").toString() ) ) ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据id查找cost
	 * @param id
	 * @return 一组资费数据
	 * @throws DAOException
	 */
	public Cost findById(int id) 
			throws DAOException {
		String sql = "SELECT * FROM cost WHERE id=? ";
		Object[] params = {id};
		
		try {
			Cost c = this.getJdbcTemplate().queryForObject(sql, params, new CostMapper());
			return c;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("根据id查询cost失败，修改cost页面的数据显示会出错", e);
		}
	}
	
	/**
	 * 找到所有的资费
	 * @return
	 */
	public List<Cost> findAll() 
			throws DAOException {
		
		String sql = "SELECT * FROM cost";
		try {
			List<Cost> costs = this.getJdbcTemplate().query(sql, new CostMapper());
			return costs;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("查找所有资费数据出错", e);
		}
	}

}
