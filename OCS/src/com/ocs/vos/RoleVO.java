package com.ocs.vos;

import java.util.List;
import com.ocs.entities.Role;

/**
 * 角色管理模块的VO
 * @author Leslie Leung
 */
public class RoleVO extends Role {
	private List<Integer> privilegeIds;	//权限id集合
	private String privilegeNames;		//权限名字拼起来的字符串
	
	public List<Integer> getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(List<Integer> privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	public String getPrivilegeNames() {
		return privilegeNames;
	}
	public void setPrivilegeNames(String privilegeNames) {
		this.privilegeNames = privilegeNames;
	}	
}
