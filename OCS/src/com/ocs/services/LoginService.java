package com.ocs.services;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.ocs.daos.DAOException;
import com.ocs.interfaces.daos.IUserDAO;
import com.ocs.interfaces.services.ILoginService;
import com.ocs.utils.CheckCodeUtil;

/**
 * 登录模块实现
 * @author Leslie Leung
 */
@Scope("prototype")
@Service
public class LoginService extends JdbcDaoSupport implements ILoginService {
	
	@Autowired
	private IUserDAO userDAO;
	
	/**
	 * 注入连接池
	 * @param jb
	 */
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}
	
	/**
	 * 检查登录是否正确
	 * @param userCode 用户名
	 * @param password 密码
	 * @param checkCode 验证码
	 * @param session
	 * @return 0，用户名或密码或验证码错误；1，登录成功
	 * @throws ServiceException
	 */
	public int check(String userCode, String password, String checkCode, Map<String, Object> session) 
			throws ServiceException {
		String imageCode = (String)session.get("checkCode");
		
		//检验验证码输入是否正确
		if(checkCode == null || imageCode == null || !checkCode.equalsIgnoreCase(imageCode)) {
			return 0;	
		}

		if(userCode == null || userCode.length() == 0) {
			return 0;
		}

		Object[] params = {userCode};
		
		try {
			String sql = "SELECT password FROM user_info WHERE user_code=?";
			
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, params);
			
			if(list != null && !list.isEmpty()) {
				if(password.equals(list.get(0).get("password").toString() ) ) {
					//把userCode放进session，用于网页超时控制
					session.put("user", userCode);
					
					//把该用户所拥有的权限放进session，用于权限控制
					List<Integer> privilegeIds = null;
					try {
						privilegeIds = userDAO.findUserPrivilegeIds(userCode);
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					session.put("userPrivileges", privilegeIds);
					
					return 1;
				}
			}
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("检验登录是否正确失败！",e);
		} 

	}

	/**
	 * 生成验证码图片
	 * @param session 
	 * @return 将验证码图片转换为流形式用作输出
	 * @throws ServiceException
	 */
	public InputStream generateCheckCode(Map<String, Object> session) 
			throws ServiceException {
		//生成验证码图片
		Map<String, BufferedImage> map = CheckCodeUtil.createImage();
		//通过遍历得到唯一生成的验证码
		String imageCode = map.keySet().iterator().next();
		//将验证码保存在session中，以在登录时使用
		session.put("checkCode", imageCode);
		//根据验证码获得验证码图片
		BufferedImage checkCodeImg = map.get(imageCode);
		//将图片转换为流，用作输出
		try {
			InputStream checkCodeStream = CheckCodeUtil.getInputStream(checkCodeImg);
			return checkCodeStream;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("生成验证码图片错误", e);
		}

	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
