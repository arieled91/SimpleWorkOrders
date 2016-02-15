package main.java.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.domain.business.WorkOrder;
import main.java.domain.business.WorkOrderId;

public class WorkOrderDao extends GenericDao<WorkOrder>{
	
	private static final String TABLE_NAME = "WORK_ORDER";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public String findQuery(String id){
		WorkOrderId workOrderId = WorkOrderId.fromString(id);
		return super.findQuery(workOrderId.getId()+" AND YEAR = "+workOrderId.getYear());
	}

	@Override
	public WorkOrder build(ResultSet result) throws SQLException {
		ProductDao productDao = new ProductDao();
		
		WorkOrder w = new WorkOrder();
		w.setId(new WorkOrderId(result.getInt("ID"),result.getInt("YEAR")));
		w.setDateCreate(result.getString("DATE_CREATE"));
		w.setDateFinish(result.getString("DATE_FINISH"));
		w.setProduct(productDao.find(result.getString("PRODUCT_ID")));
		w.setAmount(result.getDouble("AMOUNT"));
		w.setDescription(result.getString("DESCRIPTION"));
		w.setUrgent(result.getBoolean("URGENT"));
		w.setStatus(result.getString("STATUS"));
		

		TaskDao taskDao = new TaskDao(w.getId());
		w.setTasks(taskDao.getList());
		
		return null;
	}

	@Override
	public void insert(WorkOrder obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(WorkOrder obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
