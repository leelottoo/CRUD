package com.keduit.je;

public class MiniProject {

	private String BusinessName;
	private String AddressRoad;
	private String AddressJibun;
	private String BusinessType;
	private String ContactNumber;

	MiniProject(String BusinessName, String AddressRoad, String AddressJibun, String BusinessType,
			String ContactNumber) {
		this.BusinessName = BusinessName;
		this.AddressRoad = AddressRoad;
		this.AddressJibun = AddressJibun;
		this.BusinessType = BusinessType;
		this.ContactNumber = ContactNumber;
	}

	public void setBusinessName(String BusinessName) {
		this.BusinessName = BusinessName;
	}

	public String getBusinessName() {
		return BusinessName;
	}

	public void setAddressRoad(String AddressRoad) {
		this.AddressRoad = AddressRoad;
	}

	public String getAddressRoad() {
		return AddressRoad;
	}

	public void setAddressJibun(String AddressJibun) {
		this.AddressJibun = AddressJibun;
	}

	public String getAddressJibun() {
		return AddressJibun;
	}

	public void setBusinessType(String BusinessType) {
		this.BusinessType = BusinessType;
	}

	public String getBusinessType() {
		return BusinessType;
	}

	public void setContactNumber(String ContactNumber) {
		this.ContactNumber = ContactNumber;
	}

	public String getContactNumber() {
		return ContactNumber;
	}

	@Override
	public String toString() {
		return "사업자명: " + BusinessName + "\n도로명: " + AddressRoad + "\n동주소: " + AddressJibun + "\n업종명: " + BusinessType
				+ "\n연락처: " + ContactNumber;
	}

}
