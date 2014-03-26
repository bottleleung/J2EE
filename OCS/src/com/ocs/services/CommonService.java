package com.ocs.services;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.ocs.interfaces.services.ICommonService;
import com.ocs.utils.CommonUtil;
import com.ocs.vos.UserVO;

/**
 * 通用的业务逻辑
 * @author Leslie Leung
 */
@Scope("prototype")
@Service
public class CommonService extends CommonUtil implements ICommonService {
	
	/**
	 * 更新用户个人信息
	 * @param user
	 * @throws ServiceException
	 */
	public void updateUserInfo(UserVO user) 
			throws ServiceException {
		
		String sql = "UPDATE user_info SET username=?,telephone=?,email=? WHERE id=? ";
		Object[] params = {user.getName(), user.getTelephone(),
				user.getEmail(), user.getId()};
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("修改用户个人信息出错", e);
		} 
	}
		
	/**
	 * 修改用户密码
	 * @throws ServiceException
	 */
	public void updateUserPassword(String password, String userCode) 
			throws ServiceException {
		String sql = "UPDATE user_info SET password=? WHERE user_code=? ";
		
		//后端作正则表达式检验
		if(!password.matches("^[\u4E00-\u9FA5A-Za-z0-9_]{1,30}$")) {
			return;
		}
		
		Object[] params = {password, userCode};
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("修改用户密码出错", e);
		}
	}
}
