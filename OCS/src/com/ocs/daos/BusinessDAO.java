package com.ocs.daos;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ocs.entities.Business;
import com.ocs.interfaces.daos.IBusinessDAO;
import com.ocs.mappers.business.BusinessMapper;

/**
 * 业务账号模块的DAO
 * @author Leslie Leung
 */
@Scope("prototype")
@Repository
public class BusinessDAO extends JdbcDaoSupport implements IBusinessDAO {
	
	/**
	 * 注入连接池
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * 通过id查找Business
	 * @param id
	 * @return
	 */
	public Business findById(int id) 
			throws DAOException {
		String sql = "SELECT * FROM business WHERE id=?";
		
		Object[] params = {id};
		
		try {
			Business b = this.getJdbcTemplate().queryForObject(sql, params, new BusinessMapper());
			return b;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("通过id查找Business失败，修改业务账号页面显示数据会出错", e);
		} 
	}
	
	/**
	 * 检查前端传入的business是否重复
	 * @return
	 */
	public boolean checkBusinessRepeat(String unixHost, String osUserName) 
			throws DAOException {
			
		String sql = "SELECT id FROM business WHERE unix_host=? AND os_username=?";
		
		Object[] params = {unixHost, osUserName};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			if(list != null && !list.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException("检查业务账号是否重复出错，在新建业务账号时会出现重名的可能", e);
		} 
	}

}
