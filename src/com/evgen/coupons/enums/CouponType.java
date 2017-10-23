/*
 * This class shows kinds of coupons types  
 * that customers can order.
 * Stored in DB as String
 */

package com.evgen.coupons.enums;

public enum CouponType {

	 FOOD("food"),
	 RESTOURANTS("restourants"),  
	 HEALTH("health"),
	 SPORTS("sports"),
	 CAMPING("camping"),
	 TRAVELLING("travelling"),
	 CARS("cars"),
	 ELECTRIC("electric");
	
	private String name;

	private CouponType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
