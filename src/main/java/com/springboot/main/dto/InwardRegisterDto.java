package com.springboot.main.dto;

import org.springframework.stereotype.Component;

@Component
public class InwardRegisterDto {
	private String productTitle;
	private int productQuantity;
	private String godownLocation;
	private String godownManager;
	private String supplierName;
	private String supplierCity;

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
		return godownLocation;
	}

	public void setGodownLocation(String godownLocation) {
		this.godownLocation = godownLocation;
	}

	public String getGodownManager() {
		return godownManager;
	}

	public void setGodownManager(String godownManager) {
		this.godownManager = godownManager;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCity() {
		return supplierCity;
	}

	public void setSupplierCity(String supplierCity) {
		this.supplierCity = supplierCity;
	}

}
