package main.java.domain.business;

import java.util.Calendar;
import java.util.List;

import main.java.data.dao.WorkOrderDao;
import main.java.domain.util.Utils;

public class WorkOrder {
	
	
	private WorkOrderId id;
	private Calendar dateCreate = Calendar.getInstance();
	private Calendar dateFinish;
	private Product product;
	private double amount = 0;
	private String description = "";
	private boolean urgent = false;
	private Status status = Status.INICIATED;
	private List<Task> tasks;
	
	
	public WorkOrder() {
	}


	public WorkOrder(WorkOrderId id, Calendar dateCreate, Calendar dateFinish, Product product, double amount,
			String description, boolean urgent, Status status, List<Task> tasks) {
		this.id = id;
		this.dateCreate = dateCreate;
		this.dateFinish = dateFinish;
		this.product = product;
		this.amount = amount;
		this.description = description;
		this.urgent = urgent;
		this.status = status;
		this.tasks = tasks;
	}

	public WorkOrderId getId() {
		return id;
	}

	public void setId(WorkOrderId id) {
		this.id = id;
	}

	public Calendar getDateCreate() {
		return dateCreate;
	}
	
	public String getDateCreateString() {
		return Utils.toString(dateCreate);
	}

	public void setDateCreate(Calendar dateCreate) {
		this.dateCreate = dateCreate;
	}
	
	public void setDateCreate(String dateCreate) {
		this.dateCreate = Utils.fromString(dateCreate);
	}

	public Calendar getDateFinish() {
		return dateFinish;
	}
	
	public String getDateFinishString() {
		return Utils.toString(dateFinish);
	}

	public void setDateFinish(Calendar dateFinish) {
		this.dateFinish = dateFinish;
	}

	public void setDateFinish(String dateFinish) {
		this.dateFinish = Utils.fromString(dateFinish);
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void setStatus(String status) {
		this.status = Status.valueOf(status);
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public static WorkOrder find(int id, int year) {
		return find(new WorkOrderId(id, year));
	}
	
	public static WorkOrder find(WorkOrderId workOrderId) {
		WorkOrderDao dao = new WorkOrderDao();
		return dao.find(workOrderId.toString());
	}
	
	public static List<WorkOrder> list(){
		WorkOrderDao dao = new WorkOrderDao();
		return dao.getList();
	}
	
	enum Status{
		INICIATED("Iniciado"),
		ASSIGNED("Asignado"),
		IN_EXECUTION("En ejecución"),
		FINISHED("Cumplida");
		
		private String label;

		private Status(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}
		
}
