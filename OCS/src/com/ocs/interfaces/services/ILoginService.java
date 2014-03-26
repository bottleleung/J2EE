package com.ocs.interfaces.services;

import java.io.InputStream;
import java.util.Map;

import com.ocs.services.ServiceException;

/**
 * 
 * @author Leslie Leung
 */
public interface ILoginService {
	/**
	 * 检查登录是否正确
	 * @param userCode 用户名
	 * @param password 密码
	 * @param checkCode 验证码
	 * @param session
	 * @return 0，用户名或密码或验证码错误；1，登录成功
	 * @throws ServiceException
	 */
	int check(String userCode, String password, String checkCode, Map<String, Object> session) 
			throws ServiceException;
	
	/**
	 * 生成验证码图片
	 * @param session 
	 * @return 将验证码图片转换为流形式用作输出
	 * @throws ServiceException
	 */
	InputStream generateCheckCode(Map<String, Object> session) throws ServiceException;
}
