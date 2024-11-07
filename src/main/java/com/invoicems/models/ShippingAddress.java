package com.invoicems.models;


public class ShippingAddress {

	private String ship_address;
	private String ship_country;
	private String ship_state;
	private String ship_city;
	private Long ship_pincode;
	private String ship_companyName;
	private String ship_gstin;
	
	public ShippingAddress() { 
		// TODO Auto-generated constructor stub
	}

	public String getShip_address() {
		return ship_address;
	}

	public void setShip_address(String ship_address) {
		this.ship_address = ship_address;
	}

	public String getShip_country() {
		return ship_country;
	}

	public void setShip_country(String ship_country) {
		this.ship_country = ship_country;
	}

	public String getShip_state() {
		return ship_state;
	}

	public void setShip_state(String ship_state) {
		this.ship_state = ship_state;
	}

	public String getShip_city() {
		return ship_city;
	}

	public void setShip_city(String ship_city) {
		this.ship_city = ship_city;
	}

	public Long getShip_pincode() {
		return ship_pincode;
	}

	public void setShip_pincode(Long ship_pincode) {
		this.ship_pincode = ship_pincode;
	}

	public String getShip_companyName() {
		return ship_companyName;
	}

	public void setShip_companyName(String ship_companyName) {
		this.ship_companyName = ship_companyName;
	}

	public String getShip_gstin() {
		return ship_gstin;
	}

	public void setShip_gstin(String ship_gstin) {
		this.ship_gstin = ship_gstin;
	}

	@Override
	public String toString() {
		return "ShippingAddress [ship_address=" + ship_address + ", ship_country=" + ship_country + ", ship_state="
				+ ship_state + ", ship_city=" + ship_city + ", ship_pincode=" + ship_pincode + ", ship_companyName="
				+ ship_companyName + ", ship_gstin=" + ship_gstin + "]";
	}


	
	


	
}
