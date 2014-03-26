package com.ocs.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import com.ocs.interfaces.services.IRoleService;
import com.ocs.mappers.Role.RoleVOMapper;
import com.ocs.utils.PrivilegeReader;
import com.ocs.vos.RoleVO;

/**
 * 角色管理业务逻辑
 * @author Leslie Leung
 */
@Scope("prototype")
@Service
public class RoleService extends JdbcDaoSupport implements IRoleService {

	/**
	 * 注入连接池
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}

	/**
	 * 根据分页找到某页的所有数据
	 * @param page 某页
	 * @param pageSize 页面最大数据容量
	 * @return
	 * @throws ServiceException
	 */
	public List<RoleVO> find(int page, int pageSize) 
			throws ServiceException {
		String sql = "SELECT * FROM (" +
				" SELECT ro.*,rownum r FROM role_info ro WHERE rownum<?" +
				") WHERE r>? ";

		Object[] params = {page*pageSize+1, (page-1)*pageSize};

		try {
			List<RoleVO> roleVOs = this.getJdbcTemplate().query(sql, params, new RoleVOMapper());

			if(roleVOs == null) {
				return roleVOs;
			}

			String sql2 = "SELECT privilege_id FROM role_privilege WHERE role_id=?";

			for(RoleVO roleVO: roleVOs) {
				List<String> privilegeNameList = new ArrayList<String>();
				Object[] params2 = {roleVO.getId()};
				List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql2, params2);

				//获取权限名字
				if(list != null && !list.isEmpty()) {
					for(Map<String, Object> privilegeIds: list) {
						int privilegeId = Integer.parseInt(privilegeIds.get("privilege_id").toString());
						String privilegeName = PrivilegeReader.getPrivilegeNameById(privilegeId);
						privilegeNameList.add(privilegeName);
					}		
				}

				//将权限名字拼凑成字符串
				String nameStr = "";
				for(int i = 0;i < privilegeNameList.size(); i ++) {
					if(i == 0) {
						nameStr += privilegeNameList.get(i);
					} else {
						nameStr += "," + privilegeNameList.get(i);
					}
				}
				roleVO.setPrivilegeNames(nameStr);

			}
			return roleVOs;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查询角色失败！", e);
		} 

	}

	/**
	 * 找到总页数
	 * @param pageSize 页面最大数据容量
	 * @return
	 */
	public int findTotalPages(int pageSize) 
			throws ServiceException {

		try {
			String sql = "SELECT count(id) FROM role_info";

			int rows;
			try {
				rows = this.getJdbcTemplate().queryForInt(sql);
			} catch(Exception e) {
				e.printStackTrace();
				throw new ServiceException("Role模块查询总页数出错", e);
			}

			/* 根据总行数，查询总页数 */
			if(rows % pageSize == 0) {
				return rows / pageSize;
			} else {
				return rows / pageSize + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("Role模块查询总页数出错", e);
		} 
	}

	/**
	 * 新增role
	 * @param role
	 * @throws ServiceException
	 */
	public void add(final RoleVO role) 
			throws ServiceException {
		if (role == null) {
			return;
		}

		//后端作正则表达式检验
		if(!role.getName().matches("^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$")) {
			return;
		}

		try {
			// 新增角色
			final String sql = "INSERT INTO role_info VALUES(role_seq.nextval,?)";

			GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator() {  
				public PreparedStatement createPreparedStatement(Connection connection) 
						throws SQLException {  
					PreparedStatement ps = connection.prepareStatement(sql,  new String[] { "id" });  
					ps.setObject(1, role.getName());   
					return ps;  
				}  
			}, generatedKeyHolder);  

			final Integer roleId = generatedKeyHolder.getKey().intValue();

			// 新增权限
			String sql2 = "INSERT INTO role_privilege VALUES(?,?)";

			final List<Integer> privilegeIds = role.getPrivilegeIds();
			BatchPreparedStatementSetter setter = null;

			if (privilegeIds != null) {
				//批处理
				setter = new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) 
							throws SQLException {
						Integer privilegeId = privilegeIds.get(i);
						ps.setObject(1, roleId);
						ps.setObject(2, privilegeId);						
					}
					public int getBatchSize(){
						return privilegeIds.size();
					}
				};
			}
			this.getJdbcTemplate().batchUpdate(sql2, setter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增角色失败！", e);
		}
	}

	/**
	 * 删除一个角色
	 * @param id
	 * @throws ServiceException
	 */
	public void delete(int id)
			throws ServiceException {

		try {
			String sql = "DELETE FROM role_info WHERE id=?";
			Object[] params = {id};
			this.getJdbcTemplate().update(sql, params);

			String sql2 = "DELETE FROM role_privilege WHERE role_id=?";
			Object[] params2 = {id};
			this.getJdbcTemplate().update(sql2, params2);

			String sql3 = "DELETE FROM user_role WHERE role_id=?";
			Object[] params3 = {id};
			this.getJdbcTemplate().update(sql3, params3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("删除角色失败", e);
		}
	}

	/**
	 * 修改role
	 * @param r
	 * @throws ServiceException
	 */
	public void update(final RoleVO role) 
			throws ServiceException {
		if (role == null) {
			return;
		}

		//后端作正则表达式检验
		if(!role.getName().matches("^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$")) {
			return;
		}

		try {
			// 修改角色
			String sql = "UPDATE role_info SET name=? WHERE id=?";
			Object[] params = {role.getName(), role.getId()};
			this.getJdbcTemplate().update(sql, params);

			// 先删除该角色的所有权限
			String sql2 = "DELETE FROM role_privilege WHERE role_id=?";
			Object[] params2 = {role.getId()};
			this.getJdbcTemplate().update(sql2, params2);

			// 再新增权限
			String sql3 = "INSERT INTO role_privilege VALUES(?,?)";
			final List<Integer> privilegeIds = role.getPrivilegeIds();
			BatchPreparedStatementSetter setter = null;
			
			if (privilegeIds != null) {
				//批处理
				setter = new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps,int i) 
							throws SQLException {
						Integer privilegeId = privilegeIds.get(i);
						ps.setObject(1, role.getId());
						ps.setObject(2, privilegeId);
					}
					public int getBatchSize(){
						return privilegeIds.size();
					}
				};
				this.getJdbcTemplate().batchUpdate(sql3, setter);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改角色失败！", e);
		}
	}
}
