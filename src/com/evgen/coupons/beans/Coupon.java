package com.evgen.coupons.beans;

import com.evgen.coupons.enums.CouponType;


public class Coupon {

	private long id;
	private String title;
	private String startDate;
	private String endDate;
	private int amount;
	private String message;
	private float price;
	private CouponType type;
	private String image;
	private long companyId;
	public Coupon() {
		
		
	}
	public Coupon(long id, String title, String startDate, String endDate, int amount, String message, float price,
			String image, long companyId, CouponType type) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.message = message;
		this.price = price;
		this.image = image;
		this.companyId = companyId;
		this.type = type;
	}
	public Coupon(String title, String startDate, String endDate, int amount, String message, float price,
			String image, long companyId, CouponType type) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.message = message;
		this.price = price;
		this.image = image;
		this.companyId = companyId;
		this.type = type;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}


	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", message=" + message + ", price=" + price + ", type=" + type + ", image="
				+ image + ", companyId=" + companyId + "]";
	}

}
