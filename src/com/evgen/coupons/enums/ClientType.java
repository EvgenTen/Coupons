/*
 * This class shows kinds of clients 
 * 
 */

package com.evgen.coupons.enums;

public enum ClientType {
	
		ADMIN("admin"),
		COMPANY("company"),
		CUSTOMER("customer");

		private String name;

		ClientType(String name) {
			this.name = name();
		}

		public String getName() {
			return name;
		}

}
