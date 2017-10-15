package com.evgen.coupons.logic;

import com.evgen.coupons.enums.ClientType;
import com.evgen.coupons.exceptions.ApplicationException;

public interface IClientController {
	
	public boolean login(String custName, String password, ClientType clientType) throws ApplicationException;
	
}
