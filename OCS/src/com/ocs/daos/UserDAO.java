package com.ocs.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ocs.interfaces.daos.IUserDAO;
import com.ocs.mappers.user.UserVOMapper;
import com.ocs.vos.UserVO;

/**
 * DAO接口实现类，处理用户
 * @author Leslie Leung
 */
@Scope("prototype")
@Repository
public class UserDAO extends JdbcDaoSupport implements IUserDAO {

	/**
	 * 注入连接池
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}

	/**
	 * 通过id查找UserVO
	 * @param id
	 * @return
	 */
	public UserVO findById(int id)
			throws DAOException {

		String sql = "SELECT * FROM user_info WHERE id=?";
		Object[] params = {id};

		try {
			UserVO userVO = this.getJdbcTemplate().queryForObject(sql, params, new UserVOMapper());

			String sql2 = "SELECT role_id FROM user_role WHERE user_id=?";
			Object[] params2 = {id};
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql2, params2);

			List<Integer> roleIds = new ArrayList<Integer>();

			for(Map<String, Object> roleIdsMap: list) {
				roleIds.add(Integer.parseInt(roleIdsMap.get("role_id").toString()));
			}

			userVO.setRoleIds(roleIds);
			return userVO;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException("根据id查找UserVO失败，修改用户页面的数据显示会出错", e);
		}
	}

	/**
	 * 通过用户账号来查找用户
	 * @param userCode 用户名
	 * @return
	 */
	public UserVO findByUserCode(String userCode) 
			throws DAOException {
		if(userCode == null) {
			return null;
		}

		String sql = "SELECT * FROM user_info WHERE user_code=?";
		Object[] params = {userCode};
		try {
			UserVO userVO = this.getJdbcTemplate().queryForObject(sql, params, new UserVOMapper());

			//查找该用户的所有角色
			String sql2= "SELECT name FROM role_info ri INNER JOIN user_role ur ON ri.id= ur.role_id " +
					"INNER JOIN user_info ui ON ur.user_id= ui.id WHERE ui.user_code=?";

			Object[] params2 = {userCode};

			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql2, params2);
			List<String> roleNameList = new ArrayList<String>();

			for(Map<String, Object> namesMap: list) {
				roleNameList.add(namesMap.get("name").toString());
			}

			String roleNames = "";			
			for(int i = 0; i < roleNameList.size(); i ++) {
				if(i == roleNameList.size() - 1) {
					roleNames += roleNameList.get(i);
				} else {
					roleNames += roleNameList.get(i) + ",";
				}
			}
			userVO.setRoleNames(roleNames);

			return userVO;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException("根据userCode查找UserVO失败，修改用户个人信息和密码的页面的数据显示会出错", e);
		}
	}

	/**
	 * 检查用户账号是否重复
	 * @param userCode
	 * @return
	 * @throws DAOException
	 */
	public boolean checkUserCodeRepeat(String userCode) 
			throws DAOException {
		if(userCode == null) {
			return false;
		}


		String sql = "SELECT id FROM user_info WHERE user_code=?";
		Object[] params = {userCode};

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
			throw new DAOException("检查用户账号是否重复出错", e);
		} 
	}

	/**
	 * 检查用户的名字是否重复
	 * @param userName
	 * @return
	 */
	public boolean checkUserNameRepeat(String userName) 
			throws DAOException {
		if(userName == null) {
			return false;
		}

		String sql = "SELECT id FROM user_info WHERE username=?";
		Object[] params = {userName};

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
			throw new DAOException("检查用户姓名是否重复出错", e);
		}
	}

	/**
	 * 检查手机号码是否重复
	 * @param telephone
	 * @return
	 */
	public boolean checkTelephoneRepeat(String telephone)
			throws DAOException {
		if(telephone == null) {
			return false;
		}

		String sql = "SELECT id FROM user_info WHERE telephone=?";
		Object[] params = {telephone};
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
			throw new DAOException("检查用户手机号码是否重复出错", e);
		} 
	}

	/**
	 * 根据用户账号查找当前用户具有的权限id集合
	 * @param userCode
	 */
	public List<Integer> findUserPrivilegeIds(String userCode) 
			throws DAOException {

		String sql = "SELECT DISTINCT privilege_id FROM user_info ui INNER JOIN user_role ur ON ui.id= ur.user_id " + 
				"INNER JOIN role_privilege rp ON ur.role_id= rp.role_id " +
				"WHERE ui.user_code=?";

		Object[] params = {userCode};

		try {
			List<Integer> privilegeIds = new ArrayList<Integer>();
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);

			if(list != null && !list.isEmpty()) {
				for(Map<String, Object> privilegeIdMap: list) {
					int privilegeId = Integer.parseInt(privilegeIdMap.get("privilege_id").toString());
					privilegeIds.add(privilegeId);
				}
			}
			return privilegeIds;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException("根据用户账号查找当前用户具有的权限id集合出错", e);
		} 
	}
}
