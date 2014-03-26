package com.ocs.mappers.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ocs.vos.BusinessVO;

/**
 * 
 * @author Leslie Leung
 *
 */
public class BusinessVOMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		BusinessVO vo = new BusinessVO();
		try {
			vo.setId(rs.getInt("id"));
			vo.setAccountId(rs.getInt("account_id"));
			vo.setUnixHost(rs.getString("unix_host"));
			vo.setOsUserName(rs.getString("os_username"));
			vo.setLoginPassword(rs.getString("login_passwd"));
			vo.setStatus(rs.getString("status"));
			vo.setCreateDate(rs.getDate("create_date"));
			vo.setPauseDate(rs.getDate("pause_date"));
			vo.setCloseDate(rs.getDate("close_date"));
			vo.setCostId(rs.getInt("cost_id"));
			vo.setIdCardNo(rs.getString("idcard_no"));
			vo.setRealName(rs.getString("real_name"));
			vo.setCostName(rs.getString("name"));
			vo.setCostDescr(rs.getString("descr"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException("¥¥Ω®BusinessVo ß∞‹", e);
		}
		
		return vo;
	}

}
