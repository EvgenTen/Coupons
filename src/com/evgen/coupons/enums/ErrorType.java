package com.evgen.coupons.enums;


public enum ErrorType {
	COUPON_CREATION_ERROR ("Unable create coupon"),
	COUPON_DELETE_ERROR ("Unable delete coupon"),
	COUPON_UPDATE_ERROR ("Unable update coupon"),
	COUPON_RETREIVE_ERROR ("Unable retreive coupon"),
	UNABLE_TO_RETREIVE_ALL_COUPONS ("Unable to retreive all coupons"),

	USER_ERROR ("User doesn`t exist"),
//	GENERAL_ERROR,
	
	COMPANY_CREATION_ERROR ("Unable create company"),
	COMPANY_DELETE_ERROR ("Unable delete company"),
	COMPANY_UPDATE_ERROR ("Unable update company"),
	COMPANY_RETREIVE_ERROR ("Unable retreive company"),

	CUSTOMER_CREATION_ERROR ("Unable create user"),
	CUSTOMER_DELETE_ERROR ("Unable delete user"),
	CUSTOMER_UPDATE_ERROR ("Unable update user"),
	CUSTOMER_RETREIVE_ERROR ("Unable retreive user"),
	
	DATABASE_CONNECTION_ERROR ("Unable to connect to database"),
	DATABASE_CONNECTION_CLOSING_ERROR ("Unable disconnect from database"),
	
	
	CONNECTION_ERROR ("Connection ERROR");

	private String name;

	private ErrorType(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
