package com.evgen.coupons.enums;


public enum ErrorType {
	COUPON_CREATE_ERROR ("Unable create coupon"),
	COUPON_DELETE_ERROR ("Unable delete coupon"),
	COUPON_UPDATE_ERROR ("Unable update coupon"),
	COUPON_RETREIVE_BY_ID_ERROR ("Unable retreive coupon"),
	UNABLE_TO_RETREIVE_ALL_COUPONS ("Unable to retreive all coupons"),
	UNABLE_TO_RETREIVE_COUPONS_BY_COMPANY("Unable to retreive coupons by company"),
	UNABLE_TO_RETREIVE_COUPONS_BY_CUSTOMER("Unable to retreive coupons by customer"),
	UNABLE_TO_RETREIVE_COUPONS_BY_TYPE("Unable to retreive coupons by type"),
	WRONG_COUPON_OR_DOESNT_EXIST ("Wrong coupon name or company does not exist"),
	COUPON_ALREADY_EXIST ("Coupon already exist"),
	
	USER_ERROR ("User doesn`t exist"),
	LOGIN_FAILED ("Login failed"),
	
	COMPANY_CREATE_ERROR ("Unable create company"),
	COMPANY_DELETE_ERROR ("Unable delete company"),
	COMPANY_UPDATE_ERROR ("Unable update company"),
	UNABLE_TO_RETREIVE_ALL_COMPANIES("Unable to retreive list of all companies"),
	COMPANY_RETREIVE_ERROR ("Unable retreive company"),
	WRONG_COMPANY_NAME_OR_DOESNT_EXIST ("Wrong company name or company does not exist"),

	CUSTOMER_CREATE_ERROR ("Unable create user"),
	CUSTOMER_DELETE_ERROR ("Unable delete user"),
	CUSTOMER_UPDATE_ERROR ("Unable update user"),
	UNABLE_TO_RETREIVE_ALL_CUSTOMERS("Unable to retreive list of all customers"),
	CUSTOMER_RETREIVE_ERROR ("Unable retreive user"),
	WRONG_CUSTOMER_NAME_OR_DOESNT_EXIST ("Wrong customer name or customer does not exist"),
	
	JOIN_COUPON_CREATE_ERROR ("Join coupon create error"),
	JOIN_COUPON_DELETE_ERROR ("Join coupon delete error"),
	
	DATABASE_CONNECTION_ERROR ("Unable to connect to database"),
	DATABASE_CONNECTION_CLOSING_ERROR ("Unable disconnect from database"),
	
	CAN_NOT_PURCHASE_THIS_COUPON("Can't Purchase This Coupon"),
	
	SCHEDULE_ERROR ("Scheduled removing error"),
	
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
