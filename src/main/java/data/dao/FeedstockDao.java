package main.java.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.domain.business.Feedstock;

public class FeedstockDao extends GenericDao<Feedstock>{
	
	public FeedstockDao(){}
	
	private static final String TABLE_NAME = "FEEDSTOCK";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public Feedstock build(ResultSet result) throws SQLException {
		Feedstock f = new Feedstock();
		f.setId(result.getInt("ID"));
		f.setDescription(result.getString("DESCRIPTION"));
		return f;
	}

	@Override
	public void insert(Feedstock obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Feedstock obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
