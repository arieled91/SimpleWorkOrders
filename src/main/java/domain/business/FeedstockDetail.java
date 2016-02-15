package main.java.domain.business;

import java.util.List;

import main.java.data.dao.FeedstockDetailDao;

public class FeedstockDetail {
	
	private Feedstock feedstock;
	private double amount;
	
	
	
	public FeedstockDetail() {
	}

	public FeedstockDetail(Feedstock feedstock, double amount) {
		this.feedstock = feedstock;
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Feedstock getFeedstock() {
		return feedstock;
	}

	public void setFeedstock(Feedstock feedstock) {
		this.feedstock = feedstock;
	}
	
	public static FeedstockDetail find(int id) {
		FeedstockDetailDao dao = new FeedstockDetailDao();
		return dao.find(id+"");
	}
	
	public static List<FeedstockDetail> list(int taskId){
		FeedstockDetailDao dao = new FeedstockDetailDao(taskId);
		return dao.getList();
	}
	

}
