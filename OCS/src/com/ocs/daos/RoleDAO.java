package com.ocs.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ocs.interfaces.daos.IRoleDAO;
import com.ocs.mappers.Role.RoleVOMapper;
import com.ocs.vos.RoleVO;

/**
 * 角色管理的DAO
 * @author Leslie Leung
 */
@Scope("prototype")
@Repository
public class RoleDAO extends JdbcDaoSupport implements IRoleDAO {
	
	/**
	 * 注入连接池
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * 通过id查找RoleVO
	 * @param id
	 * @return
	 */
	public RoleVO findById(int id) 
			throws DAOException {
		
		try {
			// 查询角色
			String sql = "SELECT * FROM role_info WHERE id=?";
			Object[] params = {id};
			RoleVO role = this.getJdbcTemplate().queryForObject(sql, params, new RoleVOMapper());
			
			// 查询权限
			String sql2 = "SELECT privilege_id FROM role_privilege WHERE role_id=?";
			Object[] params2 = {id};
			
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql2, params2);
			List<Integer> privilegeIdList = new ArrayList<Integer>();
			
			if(list != null && !list.isEmpty()) {
				for(Map<String, Object> privilegeIds: list) {
					int privilegeId = Integer.parseInt(privilegeIds.get("privilege_id").toString());
					privilegeIdList.add(privilegeId);
				}
			}
				
			role.setPrivilegeIds(privilegeIdList);

			return role;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("根据id查找role出错，修改role页面的显示会出现问题", e);
		}
	}

	/**
	 * 查找所有角色
	 * @return
	 * @throws DAOException
	 */
	public List<RoleVO> findAll() 
			throws DAOException {
		String sql = "SELECT * FROM role_info";
		try {
			List<RoleVO> roleVOs = this.getJdbcTemplate().query(sql, new RoleVOMapper());
			return roleVOs;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("查找所有角色名字出错，新增用户页面的显示上会有错误", e);
		} 
	}
	
	/**
	 * 检查数据库中是否存在这个角色名字
	 * @param roleName
	 * @return
	 */
	public boolean checkRoleName(String roleName) 
			throws DAOException {
		String sql = "SELECT id FROM role_info WHERE name=?";
		Object[] params = {roleName};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			if(list != null && !list.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("查找所有角色名字出错，新增用户页面的显示上会有错误", e);
		} 
	}	
}