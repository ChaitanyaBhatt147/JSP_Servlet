package com.rays.bean;

import java.util.Date;

public class OrderBean {
	private int id;
	private String shopeName;
	private String productName;
	private double price ;
	private Date dop;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShopeName() {
		return shopeName;
	}
	public void setShopeName(String shopeName) {
		this.shopeName = shopeName;
	}
	public String getProdectName() {
		return productName;
	}
	public void setProdectName(String prodectName) {
		this.productName = prodectName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDop() {
		return dop;
	}
	public void setDop(Date dop) {
		this.dop = dop;
	}
}
