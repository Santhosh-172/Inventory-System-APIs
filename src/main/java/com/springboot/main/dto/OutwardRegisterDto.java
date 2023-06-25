package com.springboot.main.dto;

import org.springframework.stereotype.Component;

import com.springboot.main.model.Manager;

@Component
public class OutwardRegisterDto {

	private String productTitle;
	private int productQuantity;
	private String GodownLocation;
	private Manager godownManager;
	private String recieptNo;

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getGodownLocation() {
		return GodownLocation;
	}

	public void setGodownLocation(String godownLocation) {
		GodownLocation = godownLocation;
	}

	public Manager getGodownManager() {
		return godownManager;
	}

	public void setGodownManager(Manager manager) {
		this.godownManager = manager;
	}

	public String getRecieptNo() {
		return recieptNo;
	}

	public void setRecieptNo(String recieptNo) {
		this.recieptNo = recieptNo;
	}

}
