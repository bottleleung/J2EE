package com.ocs.daos;

/**
 * 自定义异常，用于处理DAO产生的异常
 * @author Leslie Leung
 */
public class DAOException extends Exception {
	/**
	 * 构造方法，异常信息
	 * @param msg
	 * @param t
	 */
	public DAOException(String msg, Throwable t) {
		super(msg, t);
	}
}
