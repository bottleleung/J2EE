package com.ocs.mappers.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.ocs.daos.DAOException;
import com.ocs.vos.UserVO;

/**
 * 
 * @author Leslie Leung
 *
 */
public class UserVOMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UserVO uvo = new UserVO();
		try {
			uvo.setId(rs.getInt("id"));
			uvo.setUserCode(rs.getString("user_code"));
			uvo.setPassword(rs.getString("password"));
			uvo.setName(rs.getString("username"));
			uvo.setTelephone(rs.getString("telephone"));
			uvo.setEmail(rs.getString("email"));
			uvo.setEnrollDate(rs.getDate("enrolldate"));
		} catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException("创建用户失败", e);
		}	
		return uvo;
	}

}
