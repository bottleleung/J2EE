package com.ocs.daos;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ocs.entities.Account;
import com.ocs.interfaces.daos.IAccountDAO;
import com.ocs.mappers.account.AccountMapper;

/**
 * 处理account的DAO
 * @author Leslie Leung
 */
@Scope("prototype")
@Repository
public class AccountDAO extends JdbcDaoSupport implements IAccountDAO {
	
	/**
	 * 注入连接池
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * 根据id查询account
	 * @param id
	 * @return account
	 */
	public Account findById(int id) 
			throws DAOException {
		//查账务账号数据
		String sql = "SELECT * FROM account WHERE id=?";
		Object[] params = {id};
		try {
			Account a = this.getJdbcTemplate().queryForObject(sql, params, new AccountMapper());
			//查询推荐人身份证
			if(a.getRecommenderId() != null) {
				String sql2 = "SELECT idcard_no FROM account " +
						"WHERE id=?";
				Object[] params2 = {a.getRecommenderId()};
				
				List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql2, params2);
				
				if(list != null && !list.isEmpty()) {
					String idCardNo = list.get(0).get("idcard_no").toString();
					a.setRecommenderIdCardNo(idCardNo);
				}
			}
			return a;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("根据ID查询账务账号失败,账户账号模块跳转到修改页面时显示会出错！",e);
		}
	}

	/**
	 * 查找身份证号是否存在
	 * @param idCardNo 身份证号
	 * @return 
	 */
	public boolean findIdCardNo(String idCardNo)
			throws DAOException {
		if(idCardNo == null || idCardNo.length() == 0) {
			return false;
		}
		String sql = "SELECT idcard_no FROM account " +
				"WHERE idcard_no=?";
		
		Object[] params = {idCardNo};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			if(list != null && !list.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("查询身份证是否存在失败！",e);
		}
	}
	
	/**
	 * 根据身份证号查找id
	 * @param idCardNo
	 * @return
	 */
	public Integer findIdByIdCardNo(String idCardNo) 
			throws DAOException {
		if(idCardNo == null || idCardNo.length() == 0) {
			return null;
		}
		String sql = "SELECT id FROM account " +
				"WHERE idcard_no=?";
		
		Object[] params = {idCardNo};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			if(list != null && !list.isEmpty()) {
				return Integer.parseInt(list.get(0).get("id").toString());
			} else {
				return null;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("根据身份证号查询推荐人id失败！",e);
		}
	}
	
	/**
	 * 检查该业务账号对应的账户账号是否暂停或已删除
	 * @param id
	 */
	public boolean checkAccountPauseOrDelete(int id)
			throws DAOException {
			
		String sql = "SELECT status FROM account WHERE id=(" + 
				"SELECT account_id FROM business WHERE id=?)";
		
		Object[] params = {id};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			//如果账户账号处于暂停或删除状态
			if("0".equals(list.get(0).get("status").toString()) || "2".equals(list.get(0).get("status").toString()) ) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("根据id检查账户账号状态失败，业务账号的开通功能会受到影响", e);
		}
	}
	
	/**
	 * 查找姓名是否存在
	 * @param realName 姓名
	 * @return 
	 */
	public boolean findRealName(String realName)
			throws DAOException {
		if(realName == null || realName.length() == 0) {
			return false;
		}
		String sql = "SELECT id FROM account " +
				"WHERE real_name=?";
		
		Object[] params = {realName};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			if(list != null && !list.isEmpty()) {
				return true;
			} else {
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("账户账号查询真实姓名是否存在失败！",e);
		}
	}
	
	/**
	 * 查找登录名是否存在
	 * @param loginName 登录名
	 * @return 
	 */
	public boolean findLoginName(String loginName)
			throws DAOException {
		if(loginName == null || loginName.length() == 0) {
			return false;
		}
		String sql = "SELECT id FROM account " +
				"WHERE login_name=?";
		
		Object[] params = {loginName};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			if(list != null && !list.isEmpty()) {
				return true;
			} else {
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("账户账号查询登录名是否存在失败！",e);
		}
	}
	
	/**
	 * 查找电话是否存在
	 * @param loginName 登录名
	 * @return 
	 */
	public boolean findTelephone(String telephone)
			throws DAOException {
		if(telephone == null || telephone.length() == 0) {
			return false;
		}
		String sql = "SELECT id FROM account " +
				"WHERE telephone=?";
		
		Object[] params = {telephone};
		
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			if(list != null && !list.isEmpty()) {
				return true;
			} else {
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("账户账号查询电话号码是否存在失败！",e);
		}
	}
}
