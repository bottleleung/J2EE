package com.ocs.entities;

/**
 * 角色实体类
 * @author Leslie Leung
 */
public class Role {
	private Integer id;		//角色id
	private String name;	//角色名字
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
