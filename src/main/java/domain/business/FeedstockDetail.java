package main.java.domain.business;

import java.util.List;

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
	

}
