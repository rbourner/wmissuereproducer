package com.redhat.brms.model;

public class Order {
	private String id;
	private Double amount;
	private Double discount;

	public Order(String anId) {
		this.id = anId;
	}

	public final String getId() {
		return this.id;
	}

	public Double getDiscount() {
		return this.discount;
	}
	
	public void setDiscount(Double disc) {
		this.discount = disc;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amt) {
		this.amount = amt;
	}

	@Override
	public String toString() {
		return "Order #"+this.id+": amount="+this.amount+", discount="+this.discount;
	}
}
