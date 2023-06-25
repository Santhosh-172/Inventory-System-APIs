package com.springboot.main.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReturnRegister {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String reason;
	
	private Product product;
	
	private LocalDate dateOfDelivery;
	
	private LocalDate dateOfReturn;
	
	private Godown godown;
	
	private int quantity;
	
	private String invoiceNumber;
	
	private Customer returnedBy;
	
	private int receiptNo;
	
	private double billValue;
	
	private Manager checkedBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDate getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(LocalDate dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	public LocalDate getDateOfReturn() {
		return dateOfReturn;
	}

	public void setDateOfReturn(LocalDate dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

	public Godown getGodown() {
		return godown;
	}

	public void setGodown(Godown godown) {
		this.godown = godown;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Customer getReturnedBy() {
		return returnedBy;
	}

	public void setReturnedBy(Customer returnedBy) {
		this.returnedBy = returnedBy;
	}

	public int getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}

	public double getBillValue() {
		return billValue;
	}

	public void setBillValue(double billValue) {
		this.billValue = billValue;
	}

	public Manager getCheckedBy() {
		return checkedBy;
	}

	public void setCheckedBy(Manager checkedBy) {
		this.checkedBy = checkedBy;
	}
	
}