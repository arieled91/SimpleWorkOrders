package main.java.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.domain.business.Feedstock;
import main.java.domain.business.FeedstockDetail;

public class FeedstockDetailDao extends GenericDao<FeedstockDetail>{
	
	private int taskId = 0;
	
	private static final String TABLE_NAME = "FEEDSTOCK_DETAIL";
	
	public FeedstockDetailDao(){}
	
	public FeedstockDetailDao(int taskId){
		this.taskId = taskId;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public String selectAllQuery() {
		if(taskId>0) return super.selectAllQuery()+" WHERE TASK_ID = "+taskId;
		return super.selectAllQuery();
	}

	@Override
	public FeedstockDetail build(ResultSet result) throws SQLException {
		FeedstockDetail feedstockDetail = new FeedstockDetail();
		int feedstockId = result.getInt("FEEDSTOCK_ID");
		
		feedstockDetail.setFeedstock(Feedstock.find(feedstockId));
		feedstockDetail.setAmount(result.getDouble("AMOUNT"));
		
		return feedstockDetail;
	}

	@Override
	public void insert(FeedstockDetail obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(FeedstockDetail obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
