package com.ocs.entities;

import java.sql.Date;

/**
 * 资费实体类
 * @author Leslie Leung
 */
public class Cost {
	private Integer id;		//资费ID
	private String name;	//资费名称
	private Integer basicDuration;	//基本时长
	private Double basicCost;		//基本费用
	private Double unitCost;	//单位费用
	private String status;		//状态
	private String descr;		//描述
	private Date createTime;	//创建时间
	private Date startTime;		//开通时间
	private String costType;	//资费类型
	
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
	public Integer getBasicDuration() {
		return basicDuration;
	}	
	public void setBasicDuration(Integer basicDuration) {
		this.basicDuration = basicDuration;
	}	
	public Double getBasicCost() {
		return basicCost;
	}	
	public void setBasicCost(Double basicCost) {
		this.basicCost = basicCost;
	}	
	public Double getUnitCost() {
		return unitCost;
	}	
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}	
	public String getStatus() {
		return status;
	}	
	public void setStatus(String status) {
		this.status = status;
	}	
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
}
