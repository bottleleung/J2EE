package com.ocs.mappers.account;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ocs.entities.Account;

/**
 * 
 * @author Leslie Leung
 *
 */
public class AccountMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Account a = new Account();
		try {
			a.setId(rs.getInt("id"));
			a.setRecommenderId(rs.getInt("recommender_id"));
			a.setLoginName(rs.getString("login_name"));
			a.setLoginPassword(rs.getString("login_passwd"));
			a.setStatus(rs.getString("status"));
			a.setCreateDate(rs.getDate("create_date"));
			a.setPauseDate(rs.getDate("pause_date"));
			a.setCloseDate(rs.getDate("close_date"));
			a.setRealName(rs.getString("real_name"));
			a.setIdCardNo(rs.getString("idcard_no"));
			a.setBirthday(rs.getString("birthday"));
			a.setGender(rs.getInt("gender"));
			a.setOccupation(rs.getString("occupation"));
			a.setTelephone(rs.getString("telephone"));
			a.setEmail(rs.getString("email"));
			a.setAddress(rs.getString("address"));
			a.setZipCode(rs.getString("zipcode"));
			a.setQq(rs.getString("qq"));
			a.setLastLoginTime(rs.getDate("last_login_time"));
			a.setLastLoginIp(rs.getString("last_login_ip"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException("´´½¨accountÊµÀýÊ§°Ü", e);
		}
		
		return a;
	}

}
