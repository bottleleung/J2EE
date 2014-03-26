package com.ocs.vos;

import java.util.List;
import com.ocs.entities.User;

/**
 * User的VO
 * @author Leslie Leung
 */
public class UserVO extends User {
	private String roleNames;	//角色名字符串集合
	private List<Integer> roleIds;	//角色id集合
	
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
	
}
