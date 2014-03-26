package com.ocs.mappers.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ocs.entities.Business;

/**
 * 
 * @author Leslie Leung
 *
 */
public class BusinessMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Business b = new Business();
		try {
			b.setId(rs.getInt("id"));
			b.setAccountId(rs.getInt("account_id"));
			b.setUnixHost(rs.getString("unix_host"));
			b.setOsUserName(rs.getString("os_username"));
			b.setLoginPassword(rs.getString("login_passwd"));
			b.setStatus(rs.getString("status"));
			b.setCreateDate(rs.getDate("create_date"));
			b.setPauseDate(rs.getDate("pause_date"));
			b.setCloseDate(rs.getDate("close_date"));
			b.setCostId(rs.getInt("cost_id"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException("¥¥Ω®Business ß∞‹", e);
		}
		
		return b;
	}

}
