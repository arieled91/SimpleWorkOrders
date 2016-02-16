package main.java.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.common.Utils;
import main.java.data.util.Database;
import main.java.domain.business.Feedstock;
import main.java.domain.business.FeedstockDetail;
import main.java.domain.business.Task;
import main.java.domain.business.WorkOrderId;
import main.java.domain.business.Worker;

public class TaskDao extends GenericDao<Task>{
	
	private WorkOrderId workOrderId;
	
	public TaskDao(){}
	
	public TaskDao(WorkOrderId workOrderId){
		this.workOrderId=workOrderId;
	}
	
	private static final String TABLE_NAME = "ASSIGNED_TASK";
	
	@Override
	public String selectAllQuery() {
		if(workOrderId.getId()>0) return super.selectAllQuery()+" WHERE WORK_ORDER_ID = "+workOrderId.getId() + " AND WORK_ORDER_YEAR = "+workOrderId.getYear();
		return super.selectAllQuery();
	}
	

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public Task build(ResultSet result) throws SQLException {
		Task t = new Task();
		t.setId(result.getInt("ID"));
		t.setDescription(result.getString("DESCRIPTION"));
		t.setInExecution(result.getBoolean("IN_EXECUTION"));
		t.setDateFinish(result.getString("DATE_FINISH"));
		Feedstock feedstock = Feedstock.find(result.getInt("FEEDSTOCK_ID"));
		t.setFeedstock(new FeedstockDetail(feedstock, result.getDouble("AMOUNT")));
		t.setWorker(Worker.find(result.getInt("WORKER_ID")));
		
		return t;
	}

	@Override
	public void insert(Task task) {
		final String insert = "INSERT INTO "+TABLE_NAME +" (DESCRIPTION, WORKER_ID, WORK_ORDER_ID) VALUES("
						+task.getDescription()+", "+task.getWorker().getId()+", "+workOrderId
						+")";
		
		Database.get().executeQuery(insert);
	}

	@Override
	public void update(Task task) {
		if(task.getId()<=0) {
			System.err.println("Invalid task id");
			return;
		}
		
		String update = "UPDATE "+TABLE_NAME+" SET"+
							 " DESCRIPTION = "+task.getDescription()+
							", IN_EXECUTION = "+task.isInExecution()+
							", DATE_FINISH = "+task.getDateFinishString()+
							", WORKER_ID = "+task.getWorker().getId()+
						" WHERE ID = "+task.getId()+" AND WORK_ORDER_ID = "+workOrderId;
				
		Database.get().executeUpdate(update);
	}

	public static Task build(String text) {
		Task task = new Task();
		String[] arr = text.split(";");
		String description = arr[0];
		int feedstockId = Utils.parseInt(arr[1]);
		int amount = Utils.parseInt(arr[2]);
		task.setDescription(description);
		if(feedstockId>0 && amount>0){
			Feedstock feedstock = Feedstock.find(feedstockId);
			task.setFeedstock(new FeedstockDetail(feedstock, amount));
		}
		return task;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
