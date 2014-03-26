package com.ocs.daos;

/**
 * 用于生产DAO的工厂
 * @author Leslie Leung
 */
public class DAOFactory {
	private static CostDAO costDAO;
	private static UserDAO userDAO;
	private static AccountDAO accountDAO;
	private static BusinessDAO businessDAO;
	private static RoleDAO roleDAO;
	private static CommonDAO commonDAO;
	
	private DAOFactory() {}
	
	/* 以下代码只执行一次 */
	static {
		costDAO = new CostDAO();
		userDAO = new UserDAO();
		accountDAO = new AccountDAO();
		businessDAO = new BusinessDAO();
		roleDAO = new RoleDAO();
		commonDAO = new CommonDAO();
	}
	
	public static BusinessDAO getBusinessDAO() {
		return businessDAO;
	}
	
	public static CostDAO getCostDAO() {
		return costDAO;
	}
	
	public static UserDAO getUserDAO() {
		return userDAO;
	}
	
	public static AccountDAO getAccountDAO() {
		return accountDAO;
	}
	
	public static RoleDAO getRoleDAO() {
		return roleDAO;
	}
	
	public static CommonDAO getCommonDAO() {
		return commonDAO;
	}
}