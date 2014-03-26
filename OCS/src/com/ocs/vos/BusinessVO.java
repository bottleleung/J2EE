package com.ocs.vos;

import com.ocs.entities.Business;

/**
 * 业务账号模块的VO，避免直接使用实体类
 * 来封装，以免导致实体类中代码混乱
 * @author Leslie Leung
 */
public class BusinessVO extends Business {
	private String idCardNo;	//对应的account身份证号
	private String realName;	//对应的account姓名
	private String costName;	//对应的cost的名字
	private String costDescr;	//对应的cost的描述信息
	
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCostName() {
		return costName;
	}
	public void setCostName(String costName) {
		this.costName = costName;
	}
	public String getCostDescr() {
		return costDescr;
	}
	public void setCostDescr(String costDescr) {
		this.costDescr = costDescr;
	}
}
