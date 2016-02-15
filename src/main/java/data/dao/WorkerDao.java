package main.java.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.domain.business.Worker;

public class WorkerDao extends GenericDao<Worker>{
	
	public WorkerDao(){}
	
	private static final String TABLE_NAME = "WORKER";

	@Override
	public Worker build(ResultSet result) throws SQLException {
		Worker w = new Worker();
		w.setId(result.getInt("ID"));
		w.setName(result.getString("NAME"));
		return w;
	}

	@Override
	public void insert(Worker obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Worker obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	

}
