package com.ocs.services;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.ocs.entities.Account;
import com.ocs.interfaces.services.IAccountService;
import com.ocs.mappers.account.AccountMapper;

/**
 * 账户账号业务逻辑
 * @author Leslie Leung
 */
@Scope("prototype")
@Service
public class AccountService extends JdbcDaoSupport implements IAccountService {
	
	/**
	 * 注入连接池
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * 根据条件查找account
	 * @param idCardNo 身份证
	 * @param realName 姓名
	 * @param loginName 登录名
	 * @param status 状态
	 * @page 某页
	 * @pageSize 某页最大地数据容量
	 * @return 查找出来的account的集合
	 * @throws ServiceException
	 */
	public List<Account> findByCondition(String idCardNo, String realName,
			String loginName, String status, int page, int pageSize) 
					throws ServiceException {
		
		//拼接SQL
		String sql = "SELECT * FROM (" + 
				"SELECT a.*,rownum r FROM account a WHERE 1=1 ";
		List<Object> params = new ArrayList<Object>();
		
		if(idCardNo != null && idCardNo.length() > 0) {
			sql += "AND idcard_no like ? ";
			params.add("%" + idCardNo + "%");
		}
		if(realName != null && realName.length() > 0) {
			sql += "AND real_name like ? ";
			params.add("%" + realName + "%");
		}
		if(loginName != null && loginName.length() > 0) {
			sql += "AND login_name like ? ";
			params.add("%" + loginName + "%");
		}
		if(status != null && !status.equals("-1")) {
			sql += "AND status=? ";
			params.add(status);
		}
		
		sql += ") WHERE r<? AND r>? ";
		params.add(page * pageSize + 1);
		params.add((page - 1) * pageSize);
		
		try {
			//给参数赋值
			Object[] paramObjArray = params.toArray();
			
			List<Account> accounts = this.getJdbcTemplate().query(sql, paramObjArray, new AccountMapper());
			return accounts;
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("根据条件查询账户账号失败", e);
		} 
		
	}
	
	/**
	 * 暂停某账户账号
	 * @param id 该账户账号id
	 * @throws ServiceException
	 */
	public void pause(int id)
			throws ServiceException {
		String sql = "UPDATE account SET status='0',pause_date=sysdate where id=?";
		Object[] params = {id};
				
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("暂停账户账号失败", e);
		}
	}
	
	/**
	 * 删除某个账户账号
	 * @param id 该账户账号id
	 * @throws ServiceException
	 */
	public void delete(int id)
			throws ServiceException {
		String sql = "UPDATE account SET status='2',close_date=sysdate WHERE id=?";
		
		Object[] params = {id};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除账户账号失败", e);
		} 
	}

	/**
	 * 查找总页数
	 * @param idCardNo 身份证
	 * @param realName 姓名
	 * @param loginName 登录名
	 * @param status 状态
	 * @param pageSize 页面最大数据容量
	 * @return 总页数
	 * @throws ServiceException
	 */
	public int findTotalPages(String idCardNo, String realName,
			String loginName, String status, int pageSize) 
			throws ServiceException {
		List<Object> params = new ArrayList<Object>();
		String sql = "SELECT count(id) FROM account WHERE 1=1 ";
		
		if(idCardNo != null && idCardNo.length() > 0) {
			sql += "AND idcard_no like ? ";
			params.add("%" + idCardNo + "%");
		}
		if(realName != null && realName.length() > 0) {
			sql += "AND real_name like ? ";
			params.add("%" + realName + "%");
		}
		if(loginName != null && loginName.length() > 0) {
			sql += "AND login_name like ? ";
			params.add("%" + loginName + "%");
		}
		if(status != null && !status.equals("-1")) {
			sql += "AND status=? ";
			params.add(status);
		}
				
		try {
			Object[] paramObjArray = params.toArray();	
			int rows = this.getJdbcTemplate().queryForInt(sql, paramObjArray);

			//计算总页面
			if(rows % pageSize == 0) {
				return rows / pageSize;
			} else {
				return rows / pageSize + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("account模块查询总页数失败！",e);
		} 
	}
	
	/**
	 * 开通账务账号
	 * @param id 账务账号id
	 * @throws ServiceException
	 */
	public void start(int id) 
			throws ServiceException {
		String sql = "UPDATE account SET status='1'," +
				"pause_date=null WHERE id=? ";
		
		Object[] params = {id};
		
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("开通账户账号失败", e);			
		}
	}
	
	/**
	 * 添加一个account
	 * @param account
	 */
	public void add(Account account) 
			throws ServiceException {
		if(account == null) {
			return;
		}
		
		//后端作正则表达式验证(这是必填选项的正则检查)
		if(!account.getRealName().matches("^[\u4E00-\u9FA5A-Za-z0-9.]{1,20}$") ||
				!account.getIdCardNo().matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$") ||
				!account.getLoginName().matches("^[\u4E00-\u9FA5A-Za-z0-9_]{1,30}$") ||
				!account.getTelephone().matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$") ||
				!account.getLoginPassword().matches("^[\u4E00-\u9FA5A-Za-z0-9_]{1,30}$")) {
			return;
		}
		
		//可选项的正则表达式验证
		if(account.getEmail() != null && account.getEmail().length() > 0) {
			if(!account.getEmail().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
				return;
			}
		}
		if(account.getAddress() != null && account.getAddress().length() > 0) {
			if(!account.getAddress().matches("^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$")) {
				return;
			}
		}
		if(account.getZipCode() != null && account.getZipCode().length() > 0) {
			if(!account.getZipCode().matches("^\\d{6}$")) {
				return;
			}
		}
		if(account.getQq() != null && account.getQq().length() > 0) {
			if(!account.getQq().matches("^\\d{5,13}$")) {
				return;
			}
		}
					
		try {
			String sql = "INSERT INTO account " +
					"VALUES(account_seq.nextval,?,?,?,'1',sysdate,null,null," +
					"?,?,to_date(?, 'yyyyMMdd'),?,?,?,?,?,?,?,null,null)";
			
			Object[] params = {account.getRecommenderId(), account.getLoginName(),
					account.getLoginPassword(), account.getRealName(),
					account.getIdCardNo(), account.getBirthday(),
					account.getGender(), account.getOccupation(),
					account.getTelephone(), account.getEmail(),
					account.getAddress(), account.getZipCode(),
					account.getQq()};
			
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增账户账号失败！",e);	
		}
	}
	
	/**
	 * 修改account
	 * @param account
	 */
	public void update(Account account) 
			throws ServiceException {
		if(account == null) {
			return;
		}
		
		//正则表达式作判断（密码域）
		if(!account.getLoginPassword().matches("^[\u4E00-\u9FA5A-Za-z0-9_]{1,30}$")) {
			return;
		}
		
		//可选项的正则表达式验证
		if(account.getEmail() != null && account.getEmail().length() > 0) {
			if(!account.getEmail().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
				return;
			}
		}
		if(account.getAddress() != null && account.getAddress().length() > 0) {
			if(!account.getAddress().matches("^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$")) {
				return;
			}
		}
		if(account.getZipCode() != null && account.getZipCode().length() > 0) {
			if(!account.getZipCode().matches("^\\d{6}$")) {
				return;
			}
		}
		if(account.getQq() != null && account.getQq().length() > 0) {
			if(!account.getQq().matches("^\\d{5,13}$")) {
				return;
			}
		}

		try {
			String sql = "UPDATE account SET login_name=?," +
					"telephone=?,email=?," +
					"occupation=?,gender=?,address=?,zipcode=?,qq=?,login_passwd=? " +
					"WHERE id=?";
			
			Object[] params = {account.getLoginName(), account.getTelephone(),
					account.getEmail(), account.getOccupation(),
					account.getGender(), account.getAddress(),
					account.getZipCode(), account.getQq(),
					account.getLoginPassword(), account.getId()};
			
			this.getJdbcTemplate().update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改account失败", e);
		} 
	}
	
	/**
	 * 找到一个账户账号的详细信息
	 * @param id
	 * @return
	 */
	public Account detail(int id) 
			throws ServiceException {
		String sql = "SELECT * FROM account WHERE id=?";
		
		Object[] params = {id};
		
		try {
			Account a = this.getJdbcTemplate().queryForObject(sql, params, new AccountMapper());
			return a;
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("查看账户账号详情失败", e);
		}
	}
}
