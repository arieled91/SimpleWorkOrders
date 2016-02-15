package main.java.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.data.util.Database;
import main.java.domain.business.Product;
import main.java.domain.business.Task;
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
		WorkOrder w = new WorkOrder();
		w.setId(new WorkOrderId(result.getInt("ID"),result.getInt("YEAR")));
		w.setDateCreate(result.getString("DATE_CREATE"));
		w.setDateFinish(result.getString("DATE_FINISH"));
		w.setProduct(Product.find(result.getInt("PRODUCT_ID")));
		w.setAmount(result.getDouble("AMOUNT"));
		w.setDescription(result.getString("DESCRIPTION"));
		w.setUrgent(result.getBoolean("URGENT"));
		w.setStatus(result.getString("STATUS"));
		w.setTasks(Task.list(w.getId()));
		
		return w;
	}

	@Override
	public void insert(WorkOrder workOrder) {
		final String insert = "INSERT INTO "+TABLE_NAME +" (YEAR, PRODUCT_ID, AMOUNT, DESCRIPTION, URGENT, STATUS, DATE_CREATE, DATE_FINISH) VALUES("
				+workOrder.getId().getYear()+", "+workOrder.getProduct().getId()+", "+workOrder.getAmount()+", "+ workOrder.getDescription()+", "+workOrder.isUrgent()+", "+
				workOrder.getStatus()+", "+workOrder.getDateCreateString()+", "+workOrder.getDateFinish()
				+")";

		Database.get().executeQuery(insert);
	}

	@Override
	public void update(WorkOrder workOrder) {
		if(workOrder.getId().getId()<=0 && workOrder.getId().getYear()<=0) {
			System.err.println("Invalid task id");
			return;
		}
		
		String update = "UPDATE "+TABLE_NAME+" SET"+
							 " PRODUCT_ID = "+workOrder.getProduct().getId()+
							", AMOUNT = "+workOrder.getAmount()+
							", DESCRIPTION = "+workOrder.getDescription()+
							", URGENT = "+workOrder.isUrgent()+
							", DATE_CREATE = "+workOrder.getDateCreateString()+
							", DATE_FINISH = "+workOrder.getDateFinishString()+
						" WHERE ID = "+workOrder.getId().getYear()+" AND YEAR = "+workOrder.getId().getYear();
				
		Database.get().executeUpdate(update);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
