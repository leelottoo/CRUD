package com.keduit;

public class Customer {

	private int custid;
	private String name;
	private String address;
	private String phone;

	Customer(int custid, String name, String address, String phone) {
		this.custid = custid;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public int getCustid() {
		return custid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "Customer(custid:" + custid + " name:" + name + " address:" + address + " phone:" + phone + ")";
	}

}
