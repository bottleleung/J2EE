package com.ocs.services;

/**
 * 用于生产业务的工厂
 * @author Leslie Leung
 */
public class ServiceFactory {
	private static CostService cs;
	private static LoginService login;
	private static AccountService as;
	private static BusinessService bs;
	private static RoleService roleService;
	private static UserService us;
	private static CommonService common;
	
	private ServiceFactory() {}
	
	static {
		cs = new CostService();
		login = new LoginService();
		as = new AccountService();
		bs = new BusinessService();
		roleService = new RoleService();
		us = new UserService();
		common = new CommonService();
	}
	
	public static BusinessService getBusinessService() {
		return bs;
	}
	
	public static CostService getCostService() {
		return cs;
	}
	
	public static LoginService getLoginService() {
		return login;
	}
	
	public static AccountService getAccountService() {
		return as;
	}
	
	public static RoleService getRoleService() {
		return roleService;
	}
	
	public static UserService getUserService() {
		return us;
	}
	
	public static CommonService getCommonService() {
		return common;
	}
}
