package main.java.domain;

import java.util.Calendar;

public class WorkOrder {
	
	private String id;
	private Calendar dateCreate;
	private Calendar dateFinish;
	private Product product;
	private double amount;
	private String description;
	private boolean urgent;
	private String status;
	
	
	public WorkOrder() {
	}

	public WorkOrder(String id, Calendar dateCreate, Calendar dateFinish, Product product, double amount,
			String description, boolean urgent, String status) {
		this.id = id;
		this.dateCreate = dateCreate;
		this.dateFinish = dateFinish;
		this.product = product;
		this.amount = amount;
		this.description = description;
		this.urgent = urgent;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Calendar getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Calendar dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Calendar getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Calendar dateFinish) {
		this.dateFinish = dateFinish;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
		
}
