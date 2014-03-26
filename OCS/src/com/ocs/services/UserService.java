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

import com.ocs.interfaces.services.IUserService;
import com.ocs.mappers.user.UserVOMapper;
import com.ocs.vos.UserVO;

/**
 * 用户模块业务逻辑
 * @author Leslie Leung
 */
@Scope("prototype")
@Service
public class UserService extends JdbcDaoSupport implements IUserService {

	/**
	 * 注入连接池
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}

	/**
	 * 查找用户列表
	 * @param privilegeId 权限id
	 * @param roleName 角色名字
	 * @param page 页码
	 * @param pageSize 页面最大数据量
	 * @return
	 * @throws ServiceException
	 */
	public List<UserVO> findByCondition(Integer privilegeId, String roleName,
			int page, int pageSize)
					throws ServiceException {

		List<Object> params = new ArrayList<Object>();

		String sql = "SELECT * FROM (SELECT u.*, rownum r FROM user_info u where id IN (SELECT u.id " +
				"FROM user_info u INNER JOIN user_role ur ON u.id=ur.user_id " +
				"INNER JOIN role_info ri ON ur.role_id=ri.id " + 
				"INNER JOIN role_privilege rp ON ri.id=rp.role_id " + 
				"WHERE 1=1 ";

		if(privilegeId != null && privilegeId.toString().length() > 0 && privilegeId != 0) {
			sql += "AND rp.privilege_id=? ";
			params.add(privilegeId);
		}

		if(roleName != null && roleName.length() > 0) {
			sql += "AND ri.name like ?";
			params.add("%" + roleName + "%");
		}

		sql += ") and rownum<?) WHERE r>? ";
		params.add(page * pageSize + 1);
		params.add((page - 1) * pageSize);

		Object[] paramsObjArray = params.toArray();
		try {	
			List<UserVO> userVOs = this.getJdbcTemplate().query(sql, paramsObjArray, new UserVOMapper());

			//查询某用户的角色
			String sql2 = "SELECT name FROM role_info WHERE id IN (";
			sql2 += "SELECT role_id FROM user_role where user_id=? ";
			sql2 += ")";

			for(UserVO userVO: userVOs) {
				String nameStr = "";
				Object[] params2 = {userVO.getId()};
				
				List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql2, params2);
				for(Map<String, Object> namesMap: list) {
					String name = namesMap.get("name").toString();
					nameStr += "," +name;
				}

				if(nameStr.length() > 0) {
					nameStr = nameStr.replaceFirst(",", "");
				}
				userVO.setRoleNames(nameStr);
			}

			return userVOs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("查询用户列表失败", e);
		} 		
	}

	/**
	 * 查找总页数
	 * @param privilegeId 权限id
	 * @param roleName 角色名字
	 * @param pageSize 页面最大数据容量
	 * @return
	 * @throws ServiceException
	 */
	public int findTotalPages(Integer privilegeId, String roleName, int pageSize)
			throws ServiceException {
		List<Object> params = new ArrayList<Object>();

		String sql = "SELECT count(id) FROM user_info WHERE 1=1 ";

		if(privilegeId != null && privilegeId.toString().length() > 0 && privilegeId != 0) {
			sql += "AND id IN( " +
					"SELECT ur.user_id FROM user_role ur " + 
					"INNER JOIN role_privilege rp ON ur.role_id=rp.role_id " + 
					"WHERE rp.privilege_id=?) ";

			params.add(privilegeId);
		}

		if(roleName != null && roleName.length() > 0) {
			sql += "AND id IN(" +
					"SELECT ur.user_id FROM user_role ur " +
					"INNER JOIN role_info ri ON ur.role_id=ri.id " +
					"WHERE ri.name like ?) ";
			params.add("%" + roleName + "%");
		}

		Object[] paramsObjArray = params.toArray();

		try {
			int rows = this.getJdbcTemplate().queryForInt(sql, paramsObjArray);

			if(rows % pageSize == 0) {
				return rows / pageSize;
			} else {
				return rows / pageSize + 1;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("用户模块查找总页数失败", e);
		} 
	}

	/**
	 * 添加一个用户
	 * @param user
	 */
	public void add(final UserVO user) 
			throws ServiceException {

		if(user == null) {
			return;
		}

		//后端作正则表达式检验
		if(!user.getName().matches("^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$") ||
				!user.getUserCode().matches("^[A-Za-z0-9_]{1,30}$") ||
				!user.getPassword().matches("^[A-Za-z0-9_]{1,30}$") ||
				!user.getTelephone().matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$") ||
				!user.getEmail().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
			return;
		}

		final String sql = "INSERT INTO user_info VALUES(user_seq.nextval,?,?,?,?,?,sysdate) ";
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		
		try {
			this.getJdbcTemplate().update(new PreparedStatementCreator() {  
				public PreparedStatement createPreparedStatement(Connection connection) 
						throws SQLException {  
					PreparedStatement ps = connection.prepareStatement(sql,new String[] { "id" });  
					ps.setObject(1, user.getUserCode());
					ps.setObject(2, user.getPassword());
					ps.setObject(3, user.getName());
					ps.setObject(4, user.getTelephone());
					ps.setObject(5, user.getEmail()); 
					return ps;  
				}  
			}, generatedKeyHolder);
			
			final int userId = generatedKeyHolder.getKey().intValue();

			//新增角色，要更新user_role表
			String sql2 = "INSERT INTO user_role VALUES(?,?) ";
			final List<Integer> roleIds = user.getRoleIds();
			BatchPreparedStatementSetter setter = null;

			if(roleIds != null) {
				//批处理
				setter = new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) 
							throws SQLException {
						Integer roleId = roleIds.get(i);
						ps.setObject(1, userId);
						ps.setObject(2, roleId);						
					}
					public int getBatchSize(){
						return roleIds.size();
					}
				};
			}
			this.getJdbcTemplate().batchUpdate(sql2, setter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("新建用户出错", e);
		}
	}

	/**
	 * 删除一个用户
	 * @param userId
	 */
	public void delete(int userId) 
			throws ServiceException {

		String sql = "DELETE FROM user_role WHERE user_id=? ";
		Object[] params = {userId};
		try {
			this.getJdbcTemplate().update(sql, params);

			String sql2 = "DELETE FROM user_info WHERE id=? ";
			Object[] params2 = {userId};
			
			this.getJdbcTemplate().update(sql2, params2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("删除用户出错", e);
		} 
	}

	/**
	 * 修改某个用户的信息
	 * @param user 某个用户
	 */
	public void update(final UserVO user) 
			throws ServiceException {
		if(user == null) {
			return;
		}

		//后端作正则表达式检验
		if(!user.getName().matches("^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$") ||
				!user.getTelephone().matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$") ||
				!user.getEmail().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
			return;
		}

		//把修改的数据插入到user_info表中
		String sql = "UPDATE user_info SET username=?,telephone=?,email=? WHERE id=?";
		Object[] params = {user.getName(), user.getTelephone(),
				user.getEmail(), user.getId()};
		try {
			this.getJdbcTemplate().update(sql, params);

			//先删除user_role表中相关的映射
			String sql2 = "DELETE FROM user_role WHERE user_id=?";
			Object[] params2 = {user.getId()};
			this.getJdbcTemplate().update(sql2, params2);

			//再根据修改的内容往user_role表中添加角色（对应的角色映射）
			String sql3 = "INSERT INTO user_role VALUES(?,?) ";
			final List<Integer> roleIds = user.getRoleIds();
			BatchPreparedStatementSetter setter = null;
			if(roleIds != null) {
				//批处理
				setter = new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) 
							throws SQLException {
						Integer roleId = roleIds.get(i);
						ps.setObject(1, user.getId());
						ps.setObject(2, roleId);						
					}
					public int getBatchSize(){
						return roleIds.size();
					}
				};
			}

			this.getJdbcTemplate().batchUpdate(sql3, setter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("修改用户出错", e);
		}
	}
}
