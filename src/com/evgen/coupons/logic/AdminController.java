package com.evgen.coupons.logic;

import com.evgen.coupons.exceptions.ApplicationException;

public class AdminController {

	public boolean login(String name, String password) throws ApplicationException {

		if (name.equalsIgnoreCase("admin") && password == "1234") {
			return true;

		}
		return false;

	}
}
