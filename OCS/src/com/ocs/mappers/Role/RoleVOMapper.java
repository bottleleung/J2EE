package com.ocs.mappers.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ocs.vos.RoleVO;

/**
 * 
 * @author Leslie Leung
 *
 */
public class RoleVOMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		RoleVO r = new RoleVO();
		try {
			r.setId(rs.getInt("id"));
			r.setName(rs.getString("name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException("´´½¨RoleVOÊ§°Ü", e);
		}

		return r;
	}

}
