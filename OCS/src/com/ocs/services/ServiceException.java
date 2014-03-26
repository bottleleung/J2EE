package com.ocs.services;

/**
 * 自定义异常，负责处理业务层产生的异常
 * @author Leslie Leung
 */
public class ServiceException extends Exception {
	/**
	 * 构造方法，调用父类构造方法
	 * @param msg 异常提示信息
	 * @param t 
	 */
	public ServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}
