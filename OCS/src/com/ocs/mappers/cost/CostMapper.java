package com.ocs.mappers.cost;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ocs.entities.Cost;

/**
 * 
 * @author Leslie Leung
 *
 */
public class CostMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Cost c = new Cost();
		try {
			c.setId(rs.getInt("id"));
			c.setName(rs.getString("name"));
			c.setBasicDuration(rs.getInt("base_duration"));
			c.setBasicCost(rs.getDouble("base_cost"));
			c.setUnitCost(rs.getDouble("unit_cost"));
			c.setStatus(rs.getString("status"));
			c.setDescr(rs.getString("descr"));
			c.setCreateTime(rs.getDate("createtime"));
			c.setStartTime(rs.getDate("starttime"));
			c.setCostType(rs.getString("cost_type"));
		} catch(Exception e) {
			e.printStackTrace();
			throw new SQLException("创建资费失败", e);
		}	
		return c;
	}
}
