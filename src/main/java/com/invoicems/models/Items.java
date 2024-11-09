package com.invoicems.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invoicems.models.*;

@Entity
@Table(name = "item",
uniqueConstraints = @UniqueConstraint(columnNames = "hsn")

		)
public class Items {
	
   @Id
   private String itemName;
   private String description;
   private Integer quantity;
   private String unit;
   private Float tax;
  
   private String hsn;
   private String sku;
   
   private Float saleUnitPrice;
   private String saleCurrency;
   private Float cess;
   private Float cess1;
   
   private Float percheaseUnitPrice;
   private String percheaseCurrency;
   private Float cess2;
   private Float cess3; 
   
   // Foreign key 
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
   @JsonIgnore
   private Customer customer;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")
   @JsonIgnore
   private Invoice invoice;
   
   
     
   
   public Invoice getInvoice() {
	return invoice;
}

public void setInvoice(Invoice invoice) {
	this.invoice = invoice;
}

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}

public void setPercheaseUnitPrice(Float percheaseUnitPrice) {
	this.percheaseUnitPrice = percheaseUnitPrice;
}

public Items() {
	// TODO Auto-generated constructor stub
   }
   
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}
public Float getTax() {
	return tax;
}
public void setTax(Float tax) {
	this.tax = tax;
}
public String getHsn() {
	return hsn;
}
public void setHsn(String hsn) {
	this.hsn = hsn;
}
public String getSku() {
	return sku;
}
public void setSku(String sku) {
	this.sku = sku;
}
public Float getSaleUnitPrice() {
	return saleUnitPrice;
}
public void setSaleUnitPrice(Float saleUnitPrice) {
	this.saleUnitPrice = saleUnitPrice;
}
public String getSaleCurrency() {
	return saleCurrency;
}
public void setSaleCurrency(String saleCurrency) {
	this.saleCurrency = saleCurrency;
}
public Float getCess() {
	return cess;
}
public void setCess(Float cess) {
	this.cess = cess;
}
public Float getCess1() {
	return cess1;
}
public void setCess1(Float cess1) {
	this.cess1 = cess1;
}
public Float getPercheaseUnitPrice() {
	return percheaseUnitPrice;
}
public void setPercheaseUnitPrise(Float percheaseUnitPrice) {
	this.percheaseUnitPrice = percheaseUnitPrice;
}
public String getPercheaseCurrency() {
	return percheaseCurrency;
}
public void setPercheaseCurrency(String percheaseCurrency) {
	this.percheaseCurrency = percheaseCurrency;
}
public Float getCess2() {
	return cess2;
}
public void setCess2(Float cess2) {
	this.cess2 = cess2;
}
public Float getCess3() {
	return cess3;
}
public void setCess3(Float cess3) {
	this.cess3 = cess3;
}
@Override
public String toString() {
	return "Items [itemName=" + itemName + ", description=" + description + ", quantity=" + quantity + ", unit=" + unit
			+ ", tax=" + tax + ", hsn=" + hsn + ", sku=" + sku + ", saleUnitPrise=" + saleUnitPrice + ", saleCurrency="
			+ saleCurrency + ", cess=" + cess + ", cess1=" + cess1 + ", percheaseUnitPrise=" + percheaseUnitPrice
			+ ", percheaseCurrency=" + percheaseCurrency + ", cess2=" + cess2 + ", cess3=" + cess3 + "]";
}
   
   
   
	
}
